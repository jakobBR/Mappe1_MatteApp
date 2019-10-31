package com.example.s325854mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.Btn_start_spill:
                Intent intentStart = new Intent(StartActivity.this,SpillActivity.class);
                startActivity(intentStart);
                break;
            case R.id.Btn_se_statistikk:
                Intent intentStatistikk = new Intent(StartActivity.this,StatistikkActivity.class);
                startActivity(intentStatistikk);
                break;
            case R.id.Btn_preferanser:
                Intent intentPreferanser = new Intent(StartActivity.this,PreferanserActivity.class);
                startActivityForResult(intentPreferanser,555);

        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==555)
        { if(resultCode==RESULT_OK){
            String resultat=data.getStringExtra("fraPreferanser");
            if (resultat.equals("norsk")){
                norsk();
            } else if (resultat.equals("tysk")){
                tysk();
            }
        }
            if (resultCode==RESULT_CANCELED){
            }
        }
    }

    private void tysk() {
        settland("de");
        recreate();
    }

    private void norsk(){
        settland("no");
        recreate();
    }

    public void settland(String landskode){
        Resources res=getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        Configuration cf=res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf,dm);
    }
}
