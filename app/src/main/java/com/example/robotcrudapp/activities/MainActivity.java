package com.example.robotcrudapp.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.robotcrudapp.R;
import com.example.robotcrudapp.constants.Constant;
import com.example.robotcrudapp.fragments.FindByIdFragment;
import com.example.robotcrudapp.fragments.ListRobotFragment;
import com.example.robotcrudapp.fragments.UpdateRobotFragment;


public class MainActivity extends AppCompatActivity implements ListRobotFragment.OnMessageSendListener {
    public static FragmentManager fragmentManager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new ListRobotFragment()).commit();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_find_id:
                FindByIdFragment fragmentFindID = new FindByIdFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentFindID).addToBackStack(null);
                fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    //переопределил данный метод для взамодействия между фрагментами updateFragment и ListRobotFragment
    @Override
    public void sendRobotIdToUpdateFrag(String message) {
        Fragment updateFragment = new UpdateRobotFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ROBOT_ID, message);
        updateFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, updateFragment)
                .addToBackStack(null);
        fragmentTransaction.commit();


    }
}









