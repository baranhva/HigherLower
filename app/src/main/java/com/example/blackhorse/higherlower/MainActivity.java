package com.example.blackhorse.higherlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //
    private TextView highScore;
    private TextView yourScore;
    //
    private List<String> mDiceText;
    private ArrayAdapter mAdapter;
    private ListView mListView;
    //
    private int[] mImageNames;
    private ImageView diceImage;
    private FloatingActionButton buttonHigher;
    private FloatingActionButton buttonLower;
    //
    private int currentDice;
    private int currentScore;
    private int currentHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highScore = findViewById(R.id.highScore);
        yourScore = findViewById(R.id.yourScore);

        mListView = findViewById(R.id.listView);
        mDiceText = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDiceText);
        mListView.setAdapter(mAdapter);

        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
        diceImage = findViewById(R.id.imageView);
        buttonHigher = findViewById(R.id.fabUp);
        buttonLower = findViewById(R.id.fabDown);

        // Sets random starting dice
        Dice dice = new Dice(randomNumber());
        diceImage.setImageResource(mImageNames[dice.getDiceNumberOfEyes()]);
        currentDice = dice.getDiceNumberOfEyes();

        // When the user clicks the higher button, it checks the dice before and based on that it gives the user a point or resets it.
        buttonHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dice dice = new Dice(randomNumber());
                if(dice.getDiceNumberOfEyes()>currentDice){
                    currentScore++;
                    Snackbar.make(v, "You won, you get one point", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    yourScore.setText("Your score: " + currentScore);
                } else if(dice.getDiceNumberOfEyes()<currentDice){
                    if (currentHighScore<currentScore){
                        currentHighScore = currentScore;
                        highScore.setText("Highscore: "+ currentHighScore);
                    }
                    currentScore=0;
                    Snackbar.make(v, "You lost, press to play again", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    yourScore.setText("Your score: " + currentScore);
                } else{
                    Snackbar.make(v, "The same, no points", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                currentDice = dice.getDiceNumberOfEyes();
                mDiceText.add(dice.toString());
                updateUI();
                diceImage.setImageResource(mImageNames[dice.getDiceNumberOfEyes()]);
            }
        });

        // Defines what happens when the user clicks the lower button
        buttonLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dice dice = new Dice(randomNumber());

                if(dice.getDiceNumberOfEyes()<currentDice){
                    currentScore++;
                    Snackbar.make(v, "You won, you get one point", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    yourScore.setText("Your score: " + currentScore);
                } else if(dice.getDiceNumberOfEyes()>currentDice){
                    //checks if score is the highscore
                    if (currentHighScore<currentScore){
                        currentHighScore = currentScore;
                        highScore.setText("Highscore: "+ currentHighScore);
                    }
                    currentScore=0;
                    Snackbar.make(v, "You lost, press to play again", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    yourScore.setText("Your score: " + currentScore);
                } else{
                    Snackbar.make(v, "The same, no points", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

                currentDice = dice.getDiceNumberOfEyes();
                mDiceText.add(dice.toString());
                updateUI();
                diceImage.setImageResource(mImageNames[dice.getDiceNumberOfEyes()]);


            }
        });
    }

    //creates random number between 0-5 zero and five included
    public int randomNumber(){
        Random rand = new Random();
        int randomNum = rand.nextInt(6);
        return randomNum;
    }

    //after any change, this will refresh/update the adapter
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDiceText);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}



