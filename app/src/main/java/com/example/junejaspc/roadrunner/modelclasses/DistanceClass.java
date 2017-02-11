package com.example.junejaspc.roadrunner.modelclasses;

/**
 * Created by junejaspc on 2/11/2017.
 */

public class DistanceClass {
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public DistanceClass(String email, double distance) {

        this.email = email;
        this.distance = distance;
    }

    public double distance;
}
