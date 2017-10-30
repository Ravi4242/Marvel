package com.example.ravin.marvel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ravin.marvel.Pojo.Pojo;
import com.example.ravin.marvel.R;
import com.example.ravin.marvel.feature.DetailsActivity;

import java.util.List;

/**
 * Created by ravin on 30-10-2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    List<Pojo> data;
    Context context;
    public RecycleAdapter(List<Pojo> data, Context context){
        this.data = data;
        this.context = context;
    }
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(data.get(position).getImageurl()).skipMemoryCache(false)
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.name.setText("Name: "+data.get(position).getName());
        holder.realname.setText("Real Name: "+data.get(position).getRealname());
        holder.team.setText("Team: "+data.get(position).getTeam());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsintent = new Intent(context, DetailsActivity.class);
                detailsintent.putExtra("imageurl",data.get(position).getImageurl());
                detailsintent.putExtra("name",data.get(position).getName());
                detailsintent.putExtra("realname",data.get(position).getRealname());
                detailsintent.putExtra("team",data.get(position).getTeam());
                detailsintent.putExtra("publisher",data.get(position).getPublisher());
                detailsintent.putExtra("bio",data.get(position).getBio());
                v.getContext().startActivity(detailsintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,realname,team;
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.iv_image);
            name= (TextView)itemView.findViewById(R.id.tv_name);
            realname= (TextView)itemView.findViewById(R.id.tv_Realname);
            team= (TextView)itemView.findViewById(R.id.tv_team);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
