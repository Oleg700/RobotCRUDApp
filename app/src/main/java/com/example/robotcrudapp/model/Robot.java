package com.example.robotcrudapp.model;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Robot {

    private int id;

    private String name;

    private String type;

    private int year;



    public Robot() {
    }

    public Robot(int id, String name, String type, int year) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return
                "Id: " + id + '\n' +
                        "Name: " + name + '\n' +
                        "Type: " + type + '\n' +
                        "Year: " + year;
    }


    public boolean equals(Robot o) {
        return this.getId() == o.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
