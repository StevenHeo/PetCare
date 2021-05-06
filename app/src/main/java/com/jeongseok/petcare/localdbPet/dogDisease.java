package com.jeongseok.petcare.localdbPet;

public class dogDisease {
    public int idx;
    public String disease;
    public int diseaseId;
    public String currentState;
    public String result1;
    public String result2;
    public String tip;

    @Override
    public String toString() {
        return "dogDisease{" +
                "idx=" + idx +
                ", disease='" + disease + '\'' +
                ", diseaseId=" + diseaseId +
                ", currentState='" + currentState + '\'' +
                ", result1='" + result1 + '\'' +
                ", result2='" + result2 + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getCurrentState() {
        return currentState;
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
}
