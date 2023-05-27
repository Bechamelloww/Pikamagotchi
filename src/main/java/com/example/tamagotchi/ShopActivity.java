package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {
    private ImageButton backButton;
    private Button buy1;
    private Button buy2;
    private Button buy3;
    private Button buy4;
    private Integer money;
    private Integer foodNb;
    private TextView moneystring;
    private boolean luxe;
    private boolean champi;
    private boolean meat;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.shop_page);

        this.backButton = findViewById(R.id.backButtonn);
        this.buy1 = findViewById(R.id.buy1);
        this.buy2 = findViewById(R.id.buy2);
        this.buy3 = findViewById(R.id.buy3);
        this.buy4 = findViewById(R.id.buy4);
        this.moneystring = findViewById(R.id.moneylayshop);
        Intent intent = getIntent();
        if (intent != null) {
            money = intent.getIntExtra("money", 0);
            luxe = intent.getBooleanExtra("luxe", false);
            champi = intent.getBooleanExtra("champi", false);
            meat = intent.getBooleanExtra("meat", false);
            foodNb = intent.getIntExtra("foodNb", 0);
        }
        ActiveOrNot();
        moneystring.setText("" + money);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1, intent);
                finish();
            }
        });

        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (champi == false && money >= 250) {
                    money -= 250;
                    moneystring.setText("" + money);
                    champi = true;
                    ShowToast("Le CHAMPI est ACTIF !");
                    buy1.setText("ACTIF");
                    intent.putExtra("champi", champi);
                    intent.putExtra("money", money);
                } else if (champi == true) {
                    ShowToast("Vous avez déjà acheté cet objet !");
                } else {
                    ShowToast("Vous n'avez pas assez de coins !");
                }
            }
        });
        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (luxe == false && money >= 9000) {
                    money -= 9000;
                    moneystring.setText("" + money);
                    luxe = true;
                    ShowToast("La LUXE BALL est ACTIVE !");
                    buy2.setText("ACTIVE");
                    intent.putExtra("luxe", luxe);
                    intent.putExtra("money", money);
                } else if (luxe == true) {
                    ShowToast("Vous avez déjà acheté cet objet !");
                } else {
                    ShowToast("Vous n'avez pas assez de coins !");
                }
            }
        });
        buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meat == false && money >= 9500) {
                    money -= 9500;
                    moneystring.setText("" + money);
                    meat = true;
                    ShowToast("La VIANDE DIVINE est ACTIVE !");
                    buy3.setText("ACTIVE");
                    intent.putExtra("meat", meat);
                    intent.putExtra("money", money);
                } else if (meat == true) {
                    ShowToast("Vous avez déjà acheté cet objet !");
                } else {
                    ShowToast("Vous n'avez pas assez de coins !");
                }
            }
        });
        buy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money >= 175) {
                    money -= 175;
                    moneystring.setText("" + money);
                    foodNb++;
                    ShowToast("Vous avez acheté 1 Magicarpe Braisé !");
                    intent.putExtra("foodNb", foodNb);
                    intent.putExtra("money", money);
                } else {
                    ShowToast("Vous n'avez pas assez de coins !");
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

    private void ActiveOrNot() {
        if (luxe) {
            buy2.setText("ACTIVE");
        }
        if (champi) {
            buy1.setText("ACTIF");
        }
        if (meat) {
            buy3.setText("ACTIVE");
        }
    }
}