package com.example.ravin.marvel.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.example.ravin.marvel.utils.ConnectionReciever;

/**
 * Created by ravin on 30-10-2017.
 */

public class App extends Application {
    static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
  public static  synchronized App getInstance(){
      return instance;
  }
  public void setConnectivityListener(ConnectionReciever.ConnectivityRecieverListener listener){
      ConnectionReciever.connectivityReceiverListener = listener;
  }
  }

