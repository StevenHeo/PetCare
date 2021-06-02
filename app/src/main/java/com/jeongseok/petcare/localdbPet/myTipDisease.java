package com.jeongseok.petcare.localdbPet;

import java.util.Date;

public class myTipDisease {
    private String disease;
    private String currentState;
    private String result1;
    private String result2;
    private String tip;
    private String memo;
    private String image;
    private String tipTime;

    private myTipDisease myTipDisease=null;

    private static myTipDisease TipVO = new myTipDisease();

    public static myTipDisease getInstance(){
        if(TipVO ==null)
            TipVO = new myTipDisease();
        return TipVO;
    }

    @Override
    public String toString() {
        return "myTipDisease{" +
                "disease='" + disease + '\'' +
                ", currentState='" + currentState + '\'' +
                ", result1='" + result1 + '\'' +
                ", result2='" + result2 + '\'' +
                ", tip='" + tip + '\'' +
                ", memo='" + memo + '\'' +
                ", image='" + image + '\'' +
                ", tipTime=" + tipTime +
                ", myTipDisease=" + myTipDisease +
                '}';
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTipTime() {
        return tipTime;
    }

    public void setTipTime(String tipTime) {
        this.tipTime = tipTime;
    }


}
