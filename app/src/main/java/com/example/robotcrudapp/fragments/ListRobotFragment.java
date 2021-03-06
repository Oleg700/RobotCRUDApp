package com.example.robotcrudapp.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.robotcrudapp.R;
import com.example.robotcrudapp.adapter.RobotAdapter;
import com.example.robotcrudapp.model.Robot;
import com.example.robotcrudapp.viewmodel.MainActivityViewModel;
import com.example.robotcrudapp.responses.RobotGetAllResonse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListRobotFragment extends Fragment {
    private RecyclerView recyclerView;
    private RobotAdapter adapter;
    private MainActivityViewModel mMainActivityViewModel;
    private FloatingActionButton fab;
    private OnMessageSendListener messageSendListener;
    private Observable mObservable;
    private Observer mObserver;
    private final String LOG = "ListRobotFragment";

//Интерфейс для взаимодействия между фрагментами
    public interface OnMessageSendListener {
        void sendRobotIdToUpdateFrag(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        messageSendListener = (OnMessageSendListener) activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mObservable = mMainActivityViewModel.getObservableAllRobots();
        super.onCreate(savedInstanceState);
    }

    public ListRobotFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_robot, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);

        mObserver = new Observer<RobotGetAllResonse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RobotGetAllResonse robotGetAllResonse) {
                List<Robot> listOfRobots = robotGetAllResonse.getListOfRobots();
                adapter = new RobotAdapter(getActivity(), listOfRobots);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(LOG, e.getMessage());
            }

            @Override
            public void onComplete() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        };
        mObservable.subscribe(mObserver);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AddRobotFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                int id = adapter.getIdByPosition(item.getGroupId());
                messageSendListener.sendRobotIdToUpdateFrag(String.valueOf(id));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            int id = adapter.getIdByPosition(viewHolder.getAdapterPosition());
            mMainActivityViewModel.deleteRobotById(id);

           adapter.removeItem(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Robot with id " + id + "is deleted", Toast.LENGTH_SHORT).show();
        }
    };
}
