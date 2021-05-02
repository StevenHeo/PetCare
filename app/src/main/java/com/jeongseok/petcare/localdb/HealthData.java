package com.jeongseok.petcare.localdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class HealthData {
   @PrimaryKey
   private Date date;

   private List<Integer> checkList;
   private String memo;

    public HealthData(Date date, List<Integer> checkList, String memo) {
        this.date = date;
        this.checkList = checkList;
        this.memo = memo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Integer> checkList) {
        this.checkList = checkList;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
