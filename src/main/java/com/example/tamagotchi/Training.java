package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Training extends AppCompatActivity {
    private ImageButton backButton;
    private Integer money;
    private Integer happiness;
    private Integer hunger;
    private boolean champi;
    private boolean raichu;
    private Button attack;
    private TextView moneylay;
    private ImageView pikachu;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.traininglay);

        this.backButton = findViewById(R.id.backButtonnnnn);
        this.attack = findViewById(R.id.attack);
        this.moneylay = findViewById(R.id.moneylayshop);
        this.pikachu = findViewById(R.id.pikachu);
        Intent intent = getIntent();
        if (intent != null) {
            happiness = intent.getIntExtra("happiness", 0);
            hunger = intent.getIntExtra("hunger", 0);
            money = intent.getIntExtra("money", 0);
            champi = intent.getBooleanExtra("champi", false);
            raichu = intent.getBooleanExtra("raichu", false);
        }
        if (raichu) {
            pikachu.setImageResource(R.drawable.raichu);
        }
        moneylay.setText("" + money);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hunger >= 20) {
                    hunger -= 20;
                    int min = 20;
                    int max = 100;
                    if (champi) {
                        min = 100;
                        max = 250;
                    }
                    if (raichu) {
                        min = 300;
                        max = 450;
                    }
                    if (raichu && champi) {
                        min = 600;
                        max = 850;
                    }
                    int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    money += random_int;
                    ShowToast("Vous gagnez " + random_int + " coins !");
                    if (money >= 10000) {
                        money = 9999;
                    }
                    moneylay.setText("" + money);
                    intent.putExtra("money", money);
                    intent.putExtra("hunger", hunger);
                } else {
                    ShowToast("Il a faim ! Il ne peut pas s'entraîner !");
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

}