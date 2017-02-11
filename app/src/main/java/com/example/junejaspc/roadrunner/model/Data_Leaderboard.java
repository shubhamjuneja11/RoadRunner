package com.example.junejaspc.roadrunner.model;

public class Data_Leaderboard {

    public String name;
    public String total_distance;

    public Data_Leaderboard(String name, String total_distance) {
        this.name = name;
        this.total_distance = total_distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(String total_distance) {
        this.total_distance = total_distance;
    }
}
