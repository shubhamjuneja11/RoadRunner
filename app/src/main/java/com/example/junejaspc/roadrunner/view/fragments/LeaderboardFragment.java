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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {

    List<Data_Leaderboard> data;
    RecyclerView recyclerView;

    public LeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboard, container, false);

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
