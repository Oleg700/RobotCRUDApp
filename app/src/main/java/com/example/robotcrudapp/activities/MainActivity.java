package com.example.robotcrudapp.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.robotcrudapp.R;
import com.example.robotcrudapp.adapter.RobotAdapter;
import com.example.robotcrudapp.constants.Constant;
import com.example.robotcrudapp.fragments.FindByIdFragment;
import com.example.robotcrudapp.fragments.ListRobotFragment;
import com.example.robotcrudapp.fragments.UpdateRobotFragment;
import com.example.robotcrudapp.model.Robot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ListRobotFragment.OnMessageSendListener, SearchView.OnQueryTextListener {
    public static FragmentManager fragmentManager;
    private Toolbar toolbar;
    private RobotAdapter adapter;


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


    //определяю item search в toolbarMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Enter id...");
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
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


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //в параметрах получаю значение id робота
    @Override
    public boolean onQueryTextChange(String id) {
        adapter = new RobotAdapter(this, RobotAdapter.getRobotListStatic());

        List<Robot> robotList = RobotAdapter.getRobotListStatic();
        List<Robot> listEmpty = new ArrayList<>();
        for (Robot robot : robotList) {
            if (!id.equals("") && String.valueOf(robot.getId()).contains(id)) {
                listEmpty.add(robot);

            }

        }
        if (listEmpty.size() != 0) {
            adapter.updateList(listEmpty);
        }

        return true;
    }


}









