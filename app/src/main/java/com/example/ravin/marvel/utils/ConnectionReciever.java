package com.example.ravin.marvel.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ravin.marvel.base.App;


/**
 * Created by ravin on 30-10-2017.
 */

public class ConnectionReciever extends BroadcastReceiver {

    public static ConnectivityRecieverListener connectivityReceiverListener;

    public ConnectionReciever() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo!=null&&networkInfo.isConnectedOrConnecting();

    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) App.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public static void checkInternetConnectionManual(){
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkChanged(isConnected());
        }
    }
    public interface ConnectivityRecieverListener{
        void onNetworkChanged(boolean isConnected);
    }
}
