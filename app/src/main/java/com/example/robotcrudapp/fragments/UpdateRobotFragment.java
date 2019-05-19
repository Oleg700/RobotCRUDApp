package com.example.robotcrudapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.robotcrudapp.R;
import com.example.robotcrudapp.constants.Constant;
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.viewmodel.MainActivityViewModel;

public class UpdateRobotFragment extends Fragment {
    private EditText robotName, robotType, robotYear;
    private Button buttonUpdate;
    private MainActivityViewModel mMainActivityViewModel;

    public UpdateRobotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_robot, container, false);



        robotName = view.findViewById(R.id.txt_robot_name);
        robotType = view.findViewById(R.id.txt_robot_type);
        robotYear = view.findViewById(R.id.txt_robot_year);
        buttonUpdate = view.findViewById(R.id.bn_update_robot);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getArguments();
                int robotIdSet = Integer.parseInt(bundle.getString(Constant.ROBOT_ID));
                String robotNameSet = robotName.getText().toString();
                String robotTypeSet = robotType.getText().toString();
                int robotYearSet = Integer.parseInt(robotYear.getText().toString());

                Robot robotToInsert = new Robot();
                robotToInsert.setId(robotIdSet);
                robotToInsert.setName(robotNameSet);
                robotToInsert.setType(robotTypeSet);
                robotToInsert.setYear(robotYearSet);
                updateRobot(robotIdSet, robotToInsert);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ListRobotFragment())
                        .commit();
            }
        });
        return view;
    }

    private void updateRobot(int id, Robot robot) {
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.updateRobot(id, robot);
        Toast.makeText(getActivity(), "Robot with id " + "is updated", Toast.LENGTH_SHORT).show();
    }
}
