package com.example.ravin.marvel.feature;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ravin.marvel.R;
import com.example.ravin.marvel.base.BaseClass;
import com.example.ravin.marvel.utils.Constants;
import com.example.ravin.marvel.utils.SharedUtils;

import static com.example.ravin.marvel.utils.ConnectionReciever.isConnected;

public class SplashActivity extends BaseClass {

    int secondsDelayed=1;
    ImageView splashpic;
    String LoginStatus="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashpic = (ImageView)findViewById(R.id.iv_splashpic);
        splashpic.setAdjustViewBounds(true);
        LoginStatus = SharedUtils.getString(getApplicationContext(), Constants.LOGINSTATUS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isConnected()){
            runSplash();
        }
    }

    public void runSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(LoginStatus.equalsIgnoreCase("Active")){
                    Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(homeActivity);
                    finish();
                }else{
                    Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(loginActivity);
                    finish();
                }

            }
        },secondsDelayed*2000);
    }
}
