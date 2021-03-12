package com.example.dailyeco;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

public class Dailylist {

    private String doc_date;
    private int id_goal;
    private String name_goal;
    private int daily_count;
    private int yellow_count;
    private int green_count;
    private int total_count;

    public Dailylist(){

    }

    public String getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(String doc_date) {
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

    public int getDaily_count() { return daily_count; }

    public void setDaily_count(int daily_count) {
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
