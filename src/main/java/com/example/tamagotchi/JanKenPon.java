package com.example.tamagotchi;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class JanKenPon extends AppCompatActivity {
    private Button backButton;
    private Integer money;
    private Button buttonRock, buttonPaper, buttonScissors;
    private ImageView imageViewPlayer, imageViewComputer;
    private TextView textViewResult;

    private int playerChoice = 0;
    private int computerChoice = 0;
    private TextView moneylay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jankenlay);

        buttonRock = findViewById(R.id.button_rock);
        buttonPaper = findViewById(R.id.button_paper);
        buttonScissors = findViewById(R.id.button_scissors);
        imageViewPlayer = findViewById(R.id.image_view_player);
        imageViewComputer = findViewById(R.id.image_view_computer);
        textViewResult = findViewById(R.id.text_view_result);
        backButton = findViewById(R.id.backButtonnnnnnn);
        this.moneylay = findViewById(R.id.moneylayshopp);
        Intent intent = getIntent();
        money = intent.getIntExtra("money", 0);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("money", money);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = 0;
                playGame();
            }
        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = 1;
                playGame();
            }
        });

        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = 2;
                playGame();
            }
        });
    }

    private void playGame() {
        computerChoice = generateComputerChoice();

        setPlayerChoiceImage(playerChoice);
        setComputerChoiceImage(computerChoice);

        int result = determineWinner(playerChoice, computerChoice);
        displayResult(result);
    }

    private int generateComputerChoice() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private void setPlayerChoiceImage(int choice) {
        switch (choice) {
            case 0:
                imageViewPlayer.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageViewPlayer.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageViewPlayer.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private void setComputerChoiceImage(int choice) {
        switch (choice) {
            case 0:
                imageViewComputer.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageViewComputer.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageViewComputer.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private int determineWinner(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            return 0;
        } else if ((playerChoice == 0 && computerChoice == 2) ||
                (playerChoice == 1 && computerChoice == 0) ||
                (playerChoice == 2 && computerChoice == 1)) {
            return 1;
        } else {
            return -1;
        }
    }

    private void displayResult(int result) {
        if (result == 0) {
            textViewResult.setText("Égalité !");
        } else if (result == 1) {
            textViewResult.setText("Vous avez perdu !");
            if (money >= 20) {
                money -= 20;
            }
            moneylay.setText("" + money);
        } else {
            textViewResult.setText("Vous avez gagné !");
            money += 50;
            moneylay.setText("" + money);
        }
    }
}