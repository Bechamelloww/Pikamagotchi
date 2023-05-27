package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameSelectActivity extends AppCompatActivity {
    static final int RESULT_CODE = 0;
    private ImageButton backButton;
    private ImageButton kicklee;
    private ImageButton jankenpon;
    private Integer money;
    private Integer happiness;
    private Integer hunger;
    private boolean champi;
    private boolean raichu;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.games);

        this.backButton = findViewById(R.id.backButtonnnn);
        this.kicklee = findViewById(R.id.kicklee);
        this.jankenpon = findViewById(R.id.jankenpon);
        Intent intent = getIntent();
        if (intent != null) {
            happiness = intent.getIntExtra("happiness", 0);
            hunger = intent.getIntExtra("hunger", 0);
            money = intent.getIntExtra("money", 0);
            champi = intent.getBooleanExtra("champi", false);
            raichu = intent.getBooleanExtra("raichu", false);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1, intent);
                finish();
            }
        });
        kicklee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(GameSelectActivity.this, Training.class);
                nextScreen.putExtra("money", money);
                nextScreen.putExtra("champi", champi);
                nextScreen.putExtra("raichu", raichu);
                nextScreen.putExtra("happiness", happiness);
                nextScreen.putExtra("hunger", hunger);
                startActivityForResult(nextScreen, RESULT_CODE);
            }
        });
        jankenpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(GameSelectActivity.this, JanKenPon.class);
                nextScreen.putExtra("money", money);
                nextScreen.putExtra("happiness", happiness);
                nextScreen.putExtra("hunger", hunger);
                startActivityForResult(nextScreen, RESULT_CODE);
            }
        });
    }

    private void ShowToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            money = data.getIntExtra("money", money);
            raichu = data.getBooleanExtra("raichu", raichu);
            champi = data.getBooleanExtra("champi", champi);
            happiness = data.getIntExtra("happiness", happiness);
            hunger = data.getIntExtra("hunger", hunger);

            // Créez un nouvel Intent pour retourner les valeurs à MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("money", money);
            resultIntent.putExtra("raichu", raichu);
            resultIntent.putExtra("champi", champi);
            resultIntent.putExtra("happiness", happiness);
            resultIntent.putExtra("hunger", hunger);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}