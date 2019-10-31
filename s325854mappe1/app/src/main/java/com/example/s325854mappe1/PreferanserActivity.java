package com.example.s325854mappe1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class PreferanserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferanser);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.mintoolbar);
        setActionBar(myToolbar);
    }

    public void setSpillLengde(View view){
        SharedPreferences sharedPref = PreferanserActivity.this.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        Button bt = (Button) view;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.game_length_key), Integer.valueOf(String.valueOf(bt.getText())));
        editor.apply();
    }

    public void tysk(View v) {
        String resultat="tysk";
        Bundle bundle =new Bundle();
        bundle.putString("fraPreferanser",resultat);
        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(RESULT_OK,mIntent);
        finish();
    }

    public void norsk(View v) {
        String resultat="norsk";
        Bundle bundle =new Bundle();
        bundle.putString("fraPreferanser",resultat);
        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(RESULT_OK,mIntent);
        finish();
    }


}
