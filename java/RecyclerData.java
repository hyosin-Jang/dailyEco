package com.example.dailyeco;

public class RecyclerData {

    private int imageIcon;
    private String textContent;
    private int imageNumBack;
    private String textNum;

    public RecyclerData(String textContent, String textNum){
        this.textContent=textContent;
        this.textNum=textNum;

    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextNum() {
        return textNum;
    }

    public void setTextNum(String textNum) {
        this.textNum = textNum;
    }
}
