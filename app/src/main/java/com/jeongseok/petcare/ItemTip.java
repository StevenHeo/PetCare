package com.jeongseok.petcare;

import android.content.ClipData;

public class ItemTip {
    private String diseaseName;
    private String result1;
    private String result2;
    private String tip;

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public ItemTip(String diseaseName,String result1){
        this.diseaseName = diseaseName;
        this.result1 = result1;
    }
    public ItemTip(String diseaseName,String result1, String result2, String tip){
        this.diseaseName = diseaseName;
        this.result1 = result1;
        this.result2 = result2;
        this.tip = tip;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult2() {
        return result2;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }
}
