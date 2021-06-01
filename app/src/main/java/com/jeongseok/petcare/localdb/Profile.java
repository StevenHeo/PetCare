package com.jeongseok.petcare.localdb;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Bitmap image;
    private String name;
    private Date birthDay;
    private boolean gender; // 0: M, 1: F
    private String breed;

    public Profile(Bitmap image, String name, Date birthDay, boolean gender, String breed) {
        this.image = image;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
