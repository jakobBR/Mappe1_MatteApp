package com.example.s325854mappe1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SpillActivity extends AppCompatActivity {

    int incorrectAnswers;
    int correctAnswers;
    int displayedProblems;
    int gameLength;
    TextView text_answer;
    TextView text_displayedProblem;
    ArrayList<String> problemsList;
    ArrayList<String> answersList;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        text_answer = findViewById(R.id.text_Answer);
        setValues();
        displayRegnestykker();
    }

    private void setValues(){
        displayedProblems = 0;
        correctAnswers = 0;
        incorrectAnswers = 0;
        text_displayedProblem = findViewById(R.id.text_displayedRegnestykke);
        problemsList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Regnestykker)));
        answersList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Svar)));
        sharedPref = SpillActivity.this.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        gameLength = sharedPref.getInt(getString(R.string.game_length_key),5);
    }

    protected void displayRegnestykker(){
        randomizeRegnestykkerOgSvar();
        text_displayedProblem.setText(problemsList.get(displayedProblems));
    }

    public void updateAnswer(View view){
        if((correctAnswers+incorrectAnswers)==gameLength){
            displayEndMessage();
        }
        Button clickedButton = (Button) view;
        text_answer.append(clickedButton.getText());
    }

    public void onAnswerGiven(View view){
        if((correctAnswers+incorrectAnswers) == gameLength ){
            displayEndMessage();
            return;
        }
        String answer = text_answer.getText().toString();
        processAnswer(answer);
        if (displayedProblems == (gameLength - 1)){
            System.out.println("RIKTIG "+ correctAnswers);
            updateStatistics();
            displayEndMessage();
        } else {
            nyttRegnestykke();
        }
    }

    private void updateStatistics(){
        SharedPreferences.Editor editor = sharedPref.edit();
        updateCorrectAnswersTotal(editor);
        updateIncorrectAnswersTotal(editor);
        editor.apply();
    }

    private void updateCorrectAnswersTotal(SharedPreferences.Editor editor){
        int oldTotal = sharedPref.getInt(getString(R.string.correct_total_key),0);
        editor.putInt(getString(R.string.correct_total_key), correctAnswers+oldTotal);
    }

    private void updateIncorrectAnswersTotal(SharedPreferences.Editor editor){
        int oldTotal = sharedPref.getInt(getString(R.string.incorrect_total_key),0);
        editor.putInt(getString(R.string.incorrect_total_key), incorrectAnswers+oldTotal);
    }

    private void processAnswer(String answer){
        if (answer.equals(answersList.get(displayedProblems))){
            correctAnswers++;
            TextView tv = (TextView) findViewById(R.id.text_correctNumber);
            tv.setText(String.valueOf(correctAnswers));
        } else {
            incorrectAnswers++;
            TextView tv = (TextView)findViewById(R.id.text_incorrectNumber);
            tv.setText(String.valueOf(incorrectAnswers));
        }
        text_answer.setText("");
    }

    private void nyttRegnestykke() {
        text_displayedProblem.setText(problemsList.get(++displayedProblems));
    }

    private void displayEndMessage() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.spillFerdig))
                .setMessage(getString(R.string.spillFerdigMelding1)+" "+correctAnswers+" "+
                        getString(R.string.spillFerdigMelding2)+" "+incorrectAnswers+" "+
                        getString(R.string.spillFerdigMelding3))
                .setNeutralButton(getResources().getString(R.string.avslutt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpillActivity.super.onBackPressed();
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        System.out.println("on button pressed pre if");
        if (displayedProblems <(gameLength -1)){
            System.out.println("on button pressed after if");
            (new AlertDialog.Builder(this))
                .setTitle(getResources().getString(R.string.bekreftelse))
                .setMessage(getResources().getString(R.string.avslutteSpill))
                .setPositiveButton(getResources().getString(R.string.ja), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpillActivity.super.onBackPressed();
                    }

                })
                .setNegativeButton(getResources().getString(R.string.nei), null)
                .show();
        }
    }

    public void removeDigit(View view){
        String svarString = text_answer.getText().toString();
        if (svarString.length() < 1) return;
        svarString = svarString.substring(0, svarString.length()-1);
        text_answer.setText(svarString);
    }



    private void randomizeRegnestykkerOgSvar() {
        int randomInt = new Random().nextInt();
        Collections.shuffle(problemsList,new Random(randomInt));
        Collections.shuffle(answersList,new Random(randomInt));
    }

    @Override
    protected void onDestroy() {
        System.out.println("----------onDESTROY");
        super.onDestroy();
    }
}
