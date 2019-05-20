package com.example.robotcrudapp.dagger;

import com.example.robotcrudapp.service.RetrofitClient;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RetrofitModule {


    @Provides
    Retrofit providesRetrofit(){
        return RetrofitClient.getRetrofitInstance();
    }


}
