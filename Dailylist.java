package com.example.dailyeco;

import java.text.DateFormat;
import java.util.Date;

public class Dailylist {

    private int id_user;
    private String name_user;
    private Date doc_date;
    private int id_goal;
    private String name_goal;
    private String daily_count;
    private int yellow_count;
    private int green_count;
    private int total_count;

    public Dailylist(){

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public Date getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(Date doc_date) {
        this.doc_date = doc_date;
    }

    public int getId_goal() {
        return id_goal;
    }

    public void setId_goal(int id_goal) {
        this.id_goal = id_goal;
    }

    public String getName_goal() {
        return name_goal;
    }

    public void setName_goal(String name_goal) {
        this.name_goal = name_goal;
    }

    public String getDaily_count() { return daily_count; }

    public void setDaily_count(String daily_count) {
        this.daily_count = daily_count;
    }

    public int getYellow_count() {
        return yellow_count;
    }

    public void setYellow_count(int yellow_count) {
        this.yellow_count = yellow_count;
    }

    public int getGreen_count() {
        return green_count;
    }

    public void setGreen_count(int green_count) {
        this.green_count = green_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
