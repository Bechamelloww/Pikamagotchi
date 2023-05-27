package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InventoryActivity extends AppCompatActivity {

    private boolean luxe;
    private boolean champi;
    private boolean meat;
    private ImageButton backButton;
    private ImageView meatimage;
    private ImageView luxeimage;
    private ImageView champimage;
    private int foodNb;
    private TextView foodText;

    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.inventory_page);

        this.backButton = findViewById(R.id.backButtonn);
        this.meatimage = findViewById(R.id.meatimage);
        this.luxeimage = findViewById(R.id.luxeimage);
        this.champimage = findViewById(R.id.champimage);
        this.foodText = findViewById(R.id.foodNb);
        Intent intent = getIntent();
        if (intent != null) {
            luxe = intent.getBooleanExtra("luxe", false);
            champi = intent.getBooleanExtra("champi", false);
            meat = intent.getBooleanExtra("meat", false);
            foodNb = intent.getIntExtra("foodNb", 0);
        }
        foodText.setText("" + foodNb);
        ActiveOrNot();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            luxeimage.setImageResource(R.drawable.luxeball);
        }
        if (champi) {
            champimage.setImageResource(R.drawable.champi);
        }
        if (meat) {
            meatimage.setImageResource(R.drawable.meat);
        }
    }
}