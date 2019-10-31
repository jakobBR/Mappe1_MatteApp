package com.example.s325854mappe1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class StatistikkActivity extends AppCompatActivity {


    private static final String TAG = "e";
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistikk);
        displayStatisticsValues();
    }

    private void displayStatisticsValues(){
        sharedPref = StatistikkActivity.this.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        int antalriktig = sharedPref.getInt(getString(R.string.correct_total_key),0);
        Log.d(TAG, "displayStatisticsValues: antallriktig"+antalriktig);
        TextView correct = (TextView) findViewById(R.id.text_correctStatisticNumber);
        TextView incorrect = (TextView) findViewById(R.id.text_incorrectStatisticNumber);
        sharedPref = StatistikkActivity.this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        correct.setText(String.valueOf(sharedPref.getInt(getString(R.string.correct_total_key),0)));
        incorrect.setText(String.valueOf(sharedPref.getInt(getString(R.string.incorrect_total_key),0)));
    }
}
