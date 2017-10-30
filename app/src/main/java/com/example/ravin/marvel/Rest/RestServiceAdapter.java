package com.example.ravin.marvel.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ravin on 30-10-2017.
 */

public class RestServiceAdapter {
private static RestApi getRestApiService;
    public static RestApi getClient() {
        OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
        if (getRestApiService == null) {
            httpclient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    /*This is request Type*/
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .header("token", "")
                            .build();
                 /*This is response type*/
                    Response response = chain.proceed(request);
                    String rawjson = response.body().string();
                    return response.newBuilder().body(ResponseBody.create(response.body().contentType(), rawjson)).build();
                }
            });

            httpclient.connectTimeout(20, TimeUnit.SECONDS);
            httpclient.readTimeout(20, TimeUnit.SECONDS);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Gson gson = new GsonBuilder()
                    .setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://simplifiedcoding.net/")
                    .client(httpclient.addInterceptor(httpLoggingInterceptor).build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            getRestApiService = retrofit.create(RestApi.class);
        }
        return getRestApiService;
    }
}
