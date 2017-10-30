package com.example.ravin.marvel.feature;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.ravin.marvel.Adapters.RecycleAdapter;
import com.example.ravin.marvel.Pojo.Pojo;
import com.example.ravin.marvel.R;
import com.example.ravin.marvel.Rest.RestApi;
import com.example.ravin.marvel.Rest.RestServiceAdapter;
import com.example.ravin.marvel.base.BaseClass;
import com.example.ravin.marvel.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseClass {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    RecyclerView.LayoutManager layoutManager;
    RecycleAdapter adapter;

    List<Pojo> data;
    @BindView(R.id.toolbarTiltle)
    TextView toolbarTiltle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbarTiltle.setText("Heros");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        marveldata();
    }

    public void marveldata() {
        RestApi restApi = RestServiceAdapter.getClient();
        restApi.getmarveldata().enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {
                data = new ArrayList<Pojo>();
                data = response.body();
                adapter = new RecycleAdapter(data, HomeActivity.this);
                recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
              logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setMessage("Are you sure you want to logout");
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

        AlertDialog a = alertDialogBuilder.create();
        a.show();
    }

}
