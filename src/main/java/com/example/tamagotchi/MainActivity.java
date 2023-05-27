package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final int RESULT_CODE = 0;
    private ImageView pikachu;
    private boolean raichu = false;
    private Integer happiness = 50;
    private Integer hunger = 50;
    private ProgressBar happinessBar;
    private ProgressBar hungerBar;
    private ImageButton playButton;
    private ImageButton inventoryButton;
    private ImageButton saveButton;
    private ImageButton shopButton;
    private ImageButton evolveButton;
    private TextView moneystring;
    private boolean luxe = false;
    private boolean champi = false;
    private boolean meat = false;

    private Integer money = 0;
    private Integer foodNb = 0;

    private Button feedBtn;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.homepage);

        this.pikachu = findViewById(R.id.pikachu);
        this.moneystring = findViewById(R.id.moneylay);
        this.happinessBar = findViewById(R.id.happiness);
        this.hungerBar = findViewById(R.id.hunger);
        this.playButton = findViewById(R.id.playButton);
        this.inventoryButton = findViewById(R.id.inventoryButton);
        this.shopButton = findViewById(R.id.shopButton);
        this.saveButton = findViewById(R.id.saveButton);
        this.evolveButton = findViewById(R.id.evolvebutton);
        this.feedBtn = findViewById(R.id.feedButton);
        happinessBar.setProgress(happiness);
        hungerBar.setProgress(hunger);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(MainActivity.this, ShopActivity.class);
                nextScreen.putExtra("money", money);
                nextScreen.putExtra("luxe", luxe);
                nextScreen.putExtra("champi", champi);
                nextScreen.putExtra("meat", meat);
                startActivityForResult(nextScreen, RESULT_CODE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(MainActivity.this, SaveLoadActivity.class);
                nextScreen.putExtra("money", money);
                nextScreen.putExtra("luxe", luxe);
                nextScreen.putExtra("champi", champi);
                nextScreen.putExtra("meat", meat);
                nextScreen.putExtra("raichu", raichu);
                nextScreen.putExtra("happiness", happiness);
                nextScreen.putExtra("hunger", hunger);
                nextScreen.putExtra("foodNb", foodNb);
                startActivityForResult(nextScreen, RESULT_CODE);
            }
        });
        feedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodNb >= 1) {
                    foodNb -= 1;
                    hunger += 35;
                    updateScreen();
                } else {
                    ShowToast("Vous n'avez plus de nourriture !");
                }
            }
        });
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(MainActivity.this, InventoryActivity.class);
                nextScreen.putExtra("luxe", luxe);
                nextScreen.putExtra("champi", champi);
                nextScreen.putExtra("meat", meat);
                nextScreen.putExtra("foodNb", foodNb);
                startActivity(nextScreen);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                happiness += 20;
                happinessBar.setProgress(happiness);
                moneystring.setText("" + money);
                Intent nextScreen = new Intent(MainActivity.this, GameSelectActivity.class);
                nextScreen.putExtra("money", money);
                nextScreen.putExtra("champi", champi);
                nextScreen.putExtra("raichu", raichu);
                nextScreen.putExtra("happiness", happiness);
                nextScreen.putExtra("hunger", hunger);
                startActivityForResult(nextScreen, RESULT_CODE);
                TrueOrNot();
            }
        });
        evolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money >= 300 && !raichu) {
                    money -= 300;
                    moneystring.setText("" + money);
                    ShowToast("Votre PIKACHU a évolué en RAICHU !");
                    pikachu.setImageResource(R.drawable.raichu);
                    evolveButton.setImageResource(R.drawable.evolved);
                    raichu = true;
                }
                if (raichu) {
                    ShowToast("PIKACHU a déjà évolué en RAICHU !");
                }
            }
        });
        pikachu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (raichu) {
                    if (hunger < 50 && happiness < 50) {
                        ShowToast("RAICHU a faim et est triste...");
                    } else if (hunger < 50) {
                        ShowToast("RAICHU a faim !");
                    } else if (happiness < 50) {
                        ShowToast("RAICHU est triste...");
                    } else {
                        ShowToast("RAICHU est en pleine forme !");
                    }
                } else {
                    if (hunger < 50 && happiness < 50) {
                        ShowToast("PIKACHU a faim et est triste...");
                    } else if (hunger < 50) {
                        ShowToast("PIKACHU a faim !");
                    } else if (happiness < 50) {
                        ShowToast("PIKACHU est triste...");
                    } else {
                        ShowToast("PIKACHU est en pleine forme !");
                    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        foodNb = data.getIntExtra("foodNb", foodNb);
        money = data.getIntExtra("money", money);
        raichu = data.getBooleanExtra("raichu", raichu);
        moneystring.setText("" + money);
        luxe = data.getBooleanExtra("luxe", luxe);
        champi = data.getBooleanExtra("champi", champi);
        meat = data.getBooleanExtra("meat", meat);
        happiness = data.getIntExtra("happiness", happiness);
        hunger = data.getIntExtra("hunger", hunger);
        foodNb = data.getIntExtra("foodNb", foodNb);
        TrueOrNot();
        updateScreen();
    }

    private void TrueOrNot() {
        if (raichu) {
            pikachu.setImageResource(R.drawable.raichu);
            evolveButton.setImageResource(R.drawable.evolved);
        }
        if (luxe == true) {
            if (happiness < 50) {
                happiness = 50;
            }
        }
        if (meat == true) {
            if (hunger < 50) {
                hunger = 50;
            }
        }
    }

    private void updateScreen() {
        hungerBar.setProgress(hunger);
        happinessBar.setProgress(happiness);
        if (money >= 10000) {
            money = 9999;
        }
        moneystring.setText("" + money);
    }

}