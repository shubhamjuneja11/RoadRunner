package com.example.junejaspc.roadrunner.view;

public class Constants {
    public static String registered_users,from,requests,purchase,leader,publicf;

    public static void fun(){
        registered_users="https://appathon-bafab.firebaseio.com/registered";
        from="https://appathon-bafab.firebaseio.com/";
        requests="https://appathon-bafab.firebaseio.com/requests";
        purchase="https://appathon-bafab.firebaseio.com/purchase";
        leader="https://appathon-bafab.firebaseio.com/leader";
        publicf="https://appathon-bafab.firebaseio.com/public";
    }

    public static String convert1(String a)
    {
        String from=a;
        from = from.replaceAll("\\.", "-1-");
        from = from.replaceAll("\\$", "-2-");
        from = from.replaceAll("#", "-3-");
        return from;

    }
    public static String convert2(String a)
    {
        String key=a;
        key=key.replaceAll("-1-",".");
        key=key.replaceAll("-2-","$");
        key=key.replaceAll("-3-","#");
        return key;
    }
}
