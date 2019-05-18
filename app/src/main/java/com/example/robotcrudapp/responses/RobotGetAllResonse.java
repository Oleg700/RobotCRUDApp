package com.example.robotcrudapp.responses;

import com.example.robotcrudapp.model.Robot;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RobotGetAllResonse {

    @SerializedName("data")
    private List<Robot> robots;

    public List<Robot> getListOfRobots() {
        return robots;
    }
}
