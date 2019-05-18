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
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.viewmodel.MainActivityViewModel;
public class AddRobotFragment extends Fragment {

    private EditText robotId, robotName, robotType, robotYear;
    private Button buttonSave;
    private MainActivityViewModel mMainActivityViewModel;

    public AddRobotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_robot, container, false);

        robotId = view.findViewById(R.id.txt_robot_id);
        robotName = view.findViewById(R.id.txt_robot_name);
        robotType = view.findViewById(R.id.txt_robot_type);
        robotYear = view.findViewById(R.id.txt_robot_year);
        buttonSave = view.findViewById(R.id.bn_save_robot);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int robotIdSet = Integer.parseInt(robotId.getText().toString());
                String robotNameSet = robotName.getText().toString();
                String robotTypeSet = robotType.getText().toString();
                int robotYearSet = Integer.parseInt(robotYear.getText().toString());
                Robot robotToInsert = new Robot(robotIdSet, robotNameSet, robotTypeSet, robotYearSet);
                addRobot(robotToInsert);
                Toast.makeText(getActivity(), "Robot is added", Toast.LENGTH_SHORT).show();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ListRobotFragment())
                        .commit();
            }
        });
        return view;
    }

    private void addRobot(Robot robot) {
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.addRobot(robot);
    }


}
