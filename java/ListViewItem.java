package com.example.dailyeco;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private String num ;
    private String content ;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setNum(String num) {
        this.num = num ;
    }
    public void setContent(String content) {
        this.content = content ;
    }
    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getNum() {
        return this.num ;
    }
    public String getContent() {
        return this.content ;
    }
}
