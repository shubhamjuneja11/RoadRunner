package com.example.junejaspc.roadrunner.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junejaspc.roadrunner.R;
import com.example.junejaspc.roadrunner.controller.Recycler_View_Adapter_Leaderboard;
import com.example.junejaspc.roadrunner.model.Data_Leaderboard;
import com.example.junejaspc.roadrunner.view.Constants;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {

    ArrayList<Data_Leaderboard> data;
    RecyclerView recyclerView;
    Firebase firebase;
    Recycler_View_Adapter_Leaderboard adapter;
    public LeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        data=new ArrayList<>();
        adapter = new Recycler_View_Adapter_Leaderboard(data, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        Constants.fun();
        firebase=new Firebase(Constants.leader);
       // data.add(new Data_Leaderboard("ty","5.6"));
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(int i=0;i<data.size();i++)
                {
                    if(dataSnapshot.getValue(Double.class)<Double.valueOf(data.get(i).getTotal_distance()))
                        break;
                }
                data.add(new Data_Leaderboard(dataSnapshot.getKey(),dataSnapshot.getValue(String.class)));
            adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return v;
    }



}
