package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveLoadActivity extends AppCompatActivity {

    private boolean luxe;
    private boolean champi;
    private boolean meat;
    private boolean raichu;
    private Integer happiness;
    private Integer hunger;
    private Integer money;
    private ImageButton backButton;
    private Integer foodNb;
    private Integer first; // sinon la string ne détecte pas la séparation pour la première valeur donc j'en rajoute un avant la première comme ça c réglé

    private Button loadingButton;
    private Button savingButton;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.savelayout);

        this.backButton = findViewById(R.id.backButtonnn);
        this.savingButton = findViewById(R.id.savingButton);
        this.loadingButton = findViewById(R.id.loadingButton);
        Intent intent = getIntent();
        if (intent != null) {
            money = intent.getIntExtra("money", 0);
            first = 1;
            luxe = intent.getBooleanExtra("luxe", false);
            champi = intent.getBooleanExtra("champi", false);
            meat = intent.getBooleanExtra("meat", false);
            raichu = intent.getBooleanExtra("raichu", false);
            happiness = intent.getIntExtra("happiness", 0);
            hunger = intent.getIntExtra("hunger", 0);
            foodNb = intent.getIntExtra("foodNb", 0);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1, intent);
                finish();
            }
        });
        savingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = String.format("first:%d|foodNb:%d|luxe:%b|money:%d|champi:%b|meat:%b|raichu:%b|happiness:%d|hunger:%d",
                        first,foodNb, luxe, money, champi, meat, raichu, happiness, hunger);
                writeToFile(data, getApplicationContext());
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedData = readFromFile(getApplicationContext());
                if (!savedData.isEmpty()) {
                    String[] values = savedData.split("\\|");

                    for (String value : values) {
                        String[] pair = value.split(":");
                        String key = pair[0];
                        String val = pair[1];

                        switch (key) {
                            case "money":
                                money = Integer.parseInt(val);
                                intent.putExtra("money", money);
                                break;
                            case "foodNb":
                                foodNb = Integer.parseInt(val);
                                intent.putExtra("foodNb", foodNb);
                                break;
                            case "luxe":
                                luxe = Boolean.parseBoolean(val);
                                intent.putExtra("luxe", luxe);
                                break;
                            case "champi":
                                champi = Boolean.parseBoolean(val);
                                intent.putExtra("champi", champi);
                                break;
                            case "meat":
                                meat = Boolean.parseBoolean(val);
                                intent.putExtra("meat", meat);
                                break;
                            case "raichu":
                                raichu = Boolean.parseBoolean(val);
                                intent.putExtra("raichu", raichu);
                                break;
                            case "happiness":
                                happiness = Integer.parseInt(val);
                                intent.putExtra("happiness", happiness);
                                break;
                            case "hunger":
                                hunger = Integer.parseInt(val);
                                intent.putExtra("hunger", hunger);
                                break;
                        }
                    }

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private void ShowToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void writeToFile(String data, Context context) {
        try {
            File file = new File(context.getFilesDir(), "config.txt");
            file.createNewFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            ShowToast("SAUVEGARDE EFFECTUÉE");
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            ShowToast("SAUVEGARDE ECHOUEE");
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                ShowToast("CHARGEMENT TERMINÉ");
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            ShowToast("CHARGEMENT RATE");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            ShowToast("CHARGEMENT RATE");
        }

        return ret;
    }
}