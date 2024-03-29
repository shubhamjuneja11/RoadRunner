package com.example.junejaspc.roadrunner.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.junejaspc.roadrunner.R;

public class View_Holder_Leaderboard extends RecyclerView.ViewHolder{

    Context context;
    public TextView name;
    public TextView total_distance;

    public View_Holder_Leaderboard(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        total_distance = (TextView) itemView.findViewById(R.id.total_distance);
        context = itemView.getContext();
        itemView.setTag(itemView);
    }
}
