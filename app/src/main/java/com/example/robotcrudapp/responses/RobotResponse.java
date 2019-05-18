package com.example.robotcrudapp.responses;

import com.example.robotcrudapp.model.Robot;
import com.google.gson.annotations.SerializedName;

public class RobotResponse {

    @SerializedName("data")
    private Robot robot;

    public Robot getRobot() {
        return robot;
    }

    @Override
    public String toString() {
        return "RobotResponse{" +
                "robot=" + robot +
                '}';
    }
}
