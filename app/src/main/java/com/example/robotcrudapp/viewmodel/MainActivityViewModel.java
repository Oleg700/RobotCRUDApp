package com.example.robotcrudapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.repository.RobotRepository;
import io.reactivex.Observable;


public class MainActivityViewModel extends ViewModel {
    private RobotRepository mRepository;

    public Observable getObservableAllRobots(){
        return mRepository.getObservableAllRobots();
    }

    public Observable getObservableRobotById(int id){
        return mRepository.getObservableRobotById(id);
    }

    public void init() {
        mRepository = RobotRepository.getInstance();
    }

    public void deleteRobotById(int id) {
        mRepository.deleteRobotById(id);
    }

    public void addRobot(Robot robot) {
        mRepository.addRobot(robot);
    }

    public void updateRobot(int id, Robot robot) {
        mRepository.updateRobot(id, robot);
    }
}
