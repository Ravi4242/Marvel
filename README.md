# Marvel
This repository contains a simple Android application (compatible with minSDK version 15 and up) demonstrates the use of Retrofit,RecyclerView and SlidingupPanelLayout.

##### Working Functionality:
>Splash Screen

>User log in

>RecyclerView

>SlidingupPanelLayout

>AlertDialog for logout

>Alert Dialog for Internet Validation


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
    
```
![screenshot_2017-10-30-20-55-56-312 1](https://user-images.githubusercontent.com/33225889/32188415-bd4c5c70-bdcd-11e7-818c-5b98ead2bd3d.jpg)


## User Login
Basic Login Screen with two EditTexts and one button

![screenshot_2017-10-30-20-56-10-458](https://user-images.githubusercontent.com/33225889/32188430-cc991682-bdcd-11e7-81ae-e6ffebcbff9c.jpg)


## RecyclerView
RecyclerView can display large datasets that can be scrolled efficiently by recycling a limited number of views. Click listeners can be defined when ViewHolder views are instantiated. RecyclerView is available in the v7 Support Library, thus compatible with API level 7 and above.
### Dependencies
```
compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:cardview-v7:25.3.1'
```
### Using Retrofit fetchind data onto RecyclerView
Retrofit is a type-safe REST client for Android (or just Java) developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.

This library makes downloading JSON or XML data from a web API fairly straightforward. Once the data is downloaded then it is parsed into a Plain Old Java Object (POJO) which must be defined for each "resource" in the response.

### Set up
Make sure to require Internet permissions in your ```AndroidManifest.xml``` file:
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.INTERNET" />
</manifest>
```
Add the following to your ```app/build.gradle``` file:
```
 compile group: 'com.squareup.retrofit2', name: 'converter-jackson', version: '2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.retrofit2:retrofit-mock:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.3.1'
    
  ```
  If you intend to use RxJava with Retrofit 2, you will also need to include the RxJava adapter:
  ```
  dependencies {
  compile 'io.reactivex:rxjava:1.1.6'
  compile 'io.reactivex:rxandroid:1.2.1'
  compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
}
```
## Creating Retrofit Instance
To send out network requests to an API, we need to use the [Retrofit builder] class and specify the base URL for the service.
```
 Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://simplifiedcoding.net/")
                    .client(httpclient.addInterceptor(httpLoggingInterceptor).build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            getRestApiService = retrofit.create(RestApi.class);
```
 ## Define the Endpoints
 With Retrofit 2, endpoints are defined inside of an interface using special retrofit annotations to encode details about the parameters and request method. In addition, the return value is always a parameterized Call<T> object such as Call<User>. If you do not need any type-specific response, you can specify return value as simply Call<ResponseBody>.
```
public interface RestApi {
    @GET("demos/marvel/")
    Call<List<Pojo>> getmarveldata();
}
```
            
Implement a callback() method in the activity where the data to be fetched

![1yhva3](https://user-images.githubusercontent.com/33225889/32191107-e5b9552a-bdd5-11e7-96c4-da4b9d25e55b.gif)


## SlidingUpPanelLayout
SlidingupPanelLayout is a library that provides a simple way to add a draggable sliding up panel.
## Dependencies
```
compile 'com.sothree.slidinguppanel:library:3.3.1'
```
## Usage:
-Include com.sothree.slidinguppanel.SlidingUpPanelLayout as the root element in your activity layout.
-The layout must have gravity set to either top or bottom.
-Make sure that it has two children. The first child is your main layout. The second child is your layout for the sliding up panel.
-The main layout should have the width and the height set to match_parent.
-The sliding layout should have the width set to match_parent and the height set to either match_parent, wrap_content or the max desireable height. If you would like to define the height as the percetange of the screen, set it to match_parent and also define a layout_weight attribute for the sliding view.
-By default, the whole panel will act as a drag region and will intercept clicks and drag events. You can restrict the drag area to a specific view by using the setDragView method or umanoDragView attribute.
For instance, the interface defines each endpoint in the following way:

For smooth interaction with the ActionBar, make sure that windowActionBarOverlay is set to true in your styles:
```
<style name="AppTheme">
    <item name="android:windowActionBarOverlay">true</item>
</style>
```
![1yhvlx](https://user-images.githubusercontent.com/33225889/32188462-ec493282-bdcd-11e7-9c21-7148f7a1f9b5.gif)
 
 
 ## AlertDialog for logout
 A Dialog is small window that prompts the user to a decision or enter additional information.
 
 In order to build an AlertDialog we have create an object of AlertDialogBuilder which is an inner class of AlertDialog.
 ```
 AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
 ```
 Now set the positive (yes) or negative (no) button using the object of the AlertDialogBuilder class. Its syntax is
 ```
  alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                SharedUtils.clearData(getApplicationContext());
                startActivity(intent);

            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
```
![screenshot_2017-10-30-20-56-26-153](https://user-images.githubusercontent.com/33225889/32188504-07e320ac-bdce-11e7-846c-bda98eb0e3d1.jpg)


## AlertDialog for Internet Validation:
To check the internet connection class should extends the BroadcastReceiver class as shown below
```
public class ConnectionReciever extends BroadcastReceiver {
.
.
.
}
```
Inside onReceive() method of BroadcastReceiver class create an object of ConnectivityManager class and get the status of the internet by using NetworkInfo object.
```
  ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo!=null&&networkInfo.isConnectedOrConnecting();
```
![screenshot_2017-10-30-20-56-37-127](https://user-images.githubusercontent.com/33225889/32188523-16940364-bdce-11e7-8959-a3f1ab17bdbc.jpg)


## Other factors included like:
-Butterknife dependencies

-one time login for user

-Shared preferences

-options Menu


## Conclusion:
Marvel is an Android Application gives the information about the super heros of Marvel studios. It is very user friendly.
