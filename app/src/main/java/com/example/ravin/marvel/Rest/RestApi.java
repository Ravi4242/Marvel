package com.example.ravin.marvel.Rest;

import com.example.ravin.marvel.Pojo.Pojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ravin on 30-10-2017.
 */

public interface RestApi {
    @GET("demos/marvel/")
    Call<List<Pojo>> getmarveldata();
}
