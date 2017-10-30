package com.example.ravin.marvel.feature;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.ravin.marvel.R;
import com.example.ravin.marvel.utils.SharedUtils;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_Realname)
    TextView tvRealname;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.tv_publisher)
    TextView tvPublisher;
    @BindView(R.id.tv_bio)
    TextView tvBio;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingLayout;
    @BindView(R.id.toolbarTiltle)
    TextView toolbarTiltle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String imageurl,name,realname,team, publisher, bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        slidingLayout.setPanelHeight(height / 2);
        slidingLayout.setScrollableView(scrollview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imageurl = bundle.getString("imageurl");
        name = bundle.getString("name");
        realname = bundle.getString("realname");
        team = bundle.getString("team");
        publisher = bundle.getString("publisher");
        bio = bundle.getString("bio");
        toolbarTiltle.setText(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(imageurl).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(ivImage);
        tvRealname.setText("Real Name: "+realname);
        tvTeam.setText("Team: "+team);
        tvPublisher.setText("Publisher: "+publisher);
        tvBio.setText("Bio: "+bio);

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
            case android.R.id.home:
                onBackPressed();
                return true;
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

                Intent intent = new Intent(DetailsActivity.this,LoginActivity.class);
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
