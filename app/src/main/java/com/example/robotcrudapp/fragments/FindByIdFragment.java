package com.example.robotcrudapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.robotcrudapp.R;
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.responses.RobotResponse;
import com.example.robotcrudapp.viewmodel.MainActivityViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FindByIdFragment extends Fragment {
    private TextView textView;
    private Button button;
    private EditText editText;

    private Observer mObserver;
    private Observable mObservable;
    private MainActivityViewModel mainActivityViewModel;
    private Robot robot;

    public FindByIdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_find_by_id, container, false);

        textView = view.findViewById(R.id.text_find_by_id);
        button = view.findViewById(R.id.bn_find_by_id);
        editText = view.findViewById(R.id.enter_find_by_id);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        if(savedInstanceState !=null){
            String robotValues =  savedInstanceState.getString("robot");
            textView.setText(robotValues);
        }

        mObserver = new Observer<RobotResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RobotResponse robotResponse) {
              robot =  robotResponse.getRobot();
              textView.setText(robot.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("FindViewById",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt( editText.getText().toString());
                mObservable = mainActivityViewModel.getObservableRobotById(id);
                mObservable.subscribe(mObserver);

                editText.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        if(robot != null)
        outState.putString("robot", robot.toString() );
        super.onSaveInstanceState(outState);
    }
}
