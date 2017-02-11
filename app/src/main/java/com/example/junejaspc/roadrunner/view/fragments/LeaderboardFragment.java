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

    List<Data_Leaderboard> data;
    RecyclerView recyclerView;
    Firebase firebase;
    public LeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        firebase=new Firebase(Constants.leader);
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(int i=0;i<data.size();i++)
                {
                    if(dataSnapshot.getValue(Double.class)<Double.valueOf(data.get(i).getTotal_distance()))
                        break;
                }
                data.add(new Data_Leaderboard(dataSnapshot.getKey(),dataSnapshot.getValue(String.class)));
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
        data = fillWithData();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Recycler_View_Adapter_Leaderboard adapter = new Recycler_View_Adapter_Leaderboard(data, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        return v;
    }

    private List<Data_Leaderboard> fillWithData(){
        List<Data_Leaderboard> data = new ArrayList<>();

        for(int i = 1; i <= 12; i++){
            data.add(new Data_Leaderboard("one", "two"));
        }

        return data;
    }


}
