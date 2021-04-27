package com.jeongseok.petcare;

import java.util.Date;

public class Profile {
    private String image;
    private String name;
    private Date birthDay;
    private boolean sex;
    private String breed;

    public Profile(String image, String name, Date birthDay, boolean sex, String breed) {
        this.image = image;
        this.name = name;
        this.birthDay = birthDay;
        this.sex = sex;
        this.breed = breed;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
