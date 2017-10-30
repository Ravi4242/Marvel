package com.example.ravin.marvel.base;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


import com.example.ravin.marvel.utils.ConnectionReciever;

import static com.example.ravin.marvel.utils.ConnectionReciever.checkInternetConnectionManual;


/**
 * Created by ravin on 30-10-2017.
 */

public class BaseClass extends AppCompatActivity implements ConnectionReciever.ConnectivityRecieverListener {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistableBundle) {
        super.onCreate(savedInstanceState, persistableBundle);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().setConnectivityListener(this);
        checkInternetConnectionManual();
    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (!isConnected) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
            aBuilder.setMessage("please make sure your device is connected to internet");
            aBuilder.setCancelable(false);
            aBuilder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent networksettings = new Intent(Settings.ACTION_SETTINGS);
                    networksettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(networksettings);
                }
            });
            aBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog a = aBuilder.create();
            a.show();
        }
    }


}

