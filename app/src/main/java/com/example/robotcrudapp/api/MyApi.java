package com.example.robotcrudapp.api;

import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.responses.RobotGetAllResonse;
import com.example.robotcrudapp.responses.RobotResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApi {

    @GET("robots")
    Observable<RobotGetAllResonse> getAllRobots();

    @GET("robots/{id}")
    Observable<RobotResponse> getRobotById(@Path("id") int id);

    @POST("robots")
    Call<RobotResponse> addRobot(@Body Robot robot);

    @DELETE("robots/{id}")
    Call<Void> deleteRobot(@Path("id") int id);

    @PUT("robots/{id}")
    Call<RobotResponse> updateRobot(@Path("id") int id, @Body Robot robot);


}
