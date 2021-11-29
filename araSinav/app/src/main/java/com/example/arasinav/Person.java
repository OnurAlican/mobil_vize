package com.example.arasinav;

import android.graphics.Bitmap;

public class Person {

    private Bitmap photo;
    private String name;
    private String phone;

    public Person(Bitmap photo, String name, String phone) {
        this.photo = photo;
        this.name = name;
        this.phone = phone;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
