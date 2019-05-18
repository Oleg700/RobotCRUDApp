package com.example.robotcrudapp.repository;

import android.util.Log;

import com.example.robotcrudapp.api.MyApi;
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.responses.RobotGetAllResonse;
import com.example.robotcrudapp.responses.RobotResponse;
import com.example.robotcrudapp.service.RetrofitClient;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RobotRepository {
    private static RobotRepository instance;
    private MyApi api;
    private static final String TAG = "RobotRepository";
    private Observable observable;


    public Observable<RobotGetAllResonse> getObservableAllRobots(){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        MyApi api = retrofit.create(MyApi.class);
       return observable = api.getAllRobots().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public  Observable<RobotResponse> getObservableRobotById(int id){
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        MyApi api = retrofit.create(MyApi.class);
        return  api.getRobotById(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




    public static RobotRepository getInstance() {
        if (instance == null) {
            instance = new RobotRepository();
        }
        return instance;
    }


    public void deleteRobotById(int id) {
        api = RetrofitClient.getRetrofitInstance().create(MyApi.class);
        Call<Void> call = api.deleteRobot(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, "robot is deleted");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


    public void addRobot(Robot robot) {
        api = RetrofitClient.getRetrofitInstance().create(MyApi.class);
        Call<RobotResponse> call = api.addRobot(robot);
        call.enqueue(new Callback<RobotResponse>() {
            @Override
            public void onResponse(Call<RobotResponse> call, Response<RobotResponse> response) {
                Log.i(TAG, "robot is added");

            }

            @Override
            public void onFailure(Call<RobotResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });


    }

    public void updateRobot(int id, Robot robotToUpdate) {
        api = RetrofitClient.getRetrofitInstance().create(MyApi.class);
        Call<RobotResponse> call = api.updateRobot(id, robotToUpdate);

        call.enqueue(new Callback<RobotResponse>() {
            @Override
            public void onResponse(Call<RobotResponse> call, Response<RobotResponse> response) {
                Log.i(TAG, "robot is updated");
            }

            @Override
            public void onFailure(Call<RobotResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
