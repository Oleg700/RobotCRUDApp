package com.example.robotcrudapp.dagger;


import com.example.robotcrudapp.fragments.ListRobotFragment;

import dagger.Component;

@Component
public interface AppComponent {

    void inject(ListRobotFragment listRobotFragment);




}
