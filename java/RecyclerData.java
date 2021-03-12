package com.example.dailyeco;

public class RecyclerData {

    private int imageIcon;
    private String textContent;
    private int imageNumBack;
    private int IntNum;

    public RecyclerData(String textContent, int IntNum){
        this.textContent=textContent;
        this.IntNum=IntNum;

    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getIntNum() {
        return IntNum;
    }

    public void setIntNum(int IntNum) {
        this.IntNum = IntNum;
    }
}
