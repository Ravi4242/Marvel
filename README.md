# Marvel
This repository contains a simple Android application (compatible with minSDK version 15 and up) demonstrates the use of Retrofit,RecyclerView and SlidingupPanelLayout.
##### Working Functionality:
>Splash Screen

>User log in

>RecyclerView

>SlidingupPanelLayout

## Splash Screen

Splash Screen is implemented during the app launch. It is achieved by using an Handler Class and Overriding run() method.

``` 
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
      



