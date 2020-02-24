package com.lofter.nonalaberin_rosettastone.myapplication;

public class RateItem {

    private int id;
    private String curName;
    private String curRate;

    public RateItem() {
        this.curRate = "";
        this.curName = "";
    }

    public RateItem(String curRate, String curName) {
        this.curRate = curRate;
        this.curName = curName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getCurRate() {
        return curRate;
    }

    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }

}
