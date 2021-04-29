package com.jeongseok.petcare;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String image;
    private String name;
    private String birthDay;
    private String sex;
    private String breed;

    public Profile(String image, String name, String birthDay, String sex, String breed) {
        this.image = image;
        this.name = name;
        this.birthDay = birthDay;
        this.sex = sex;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", sex='" + sex + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
