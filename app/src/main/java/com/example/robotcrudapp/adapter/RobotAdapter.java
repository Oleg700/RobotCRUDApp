package com.example.robotcrudapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.robotcrudapp.R;
import com.example.robotcrudapp.model.Robot;

import java.util.ArrayList;
import java.util.List;

public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.RobotViewHolder> {
   private Context context;
   private static List<Robot> robotList;

    public RobotAdapter(Context context, List<Robot> robots) {
        this.context = context;
        this.robotList = robots;
    }





    public static List<Robot> getRobotListStatic() {
        return robotList;
    }

    @NonNull
    @Override
    public RobotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.text_view_layout, parent, false);

        return new RobotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RobotViewHolder robotViewHolder, int position) {
        Robot robot = robotList.get(position);
        robot.toString();
        robotViewHolder.robotName.setText(robotList.get(position).toString());
        robotViewHolder.robotImage.setImageResource(R.drawable.r2d2);


    }


    @Override
    public int getItemCount() {
        return robotList.size();
    }
    class RobotViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private ImageView robotImage;
        private TextView robotName;
        private CardView cardView;

        public RobotViewHolder(@NonNull View itemView) {
            super(itemView);

            robotName = itemView.findViewById(R.id.robot_name);
            robotImage = itemView.findViewById(R.id.robot_image);
            cardView  = itemView.findViewById(R.id.mCardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select an Option");
            menu.add(this.getAdapterPosition(),120, 0, "Update robots");
            menu.add(this.getAdapterPosition(),121, 1, "Delete robot");
            menu.add(this.getAdapterPosition(),122, 2, "Update robot");
            menu.add(this.getAdapterPosition(),123, 3, "Find by id");

        }
    }

public void removeItem(int position){
        robotList.remove(position);
        notifyDataSetChanged();
}

public int getIdByPosition(int position){
        return robotList.get(position).getId();
}


public void updateList(List<Robot> newList){
        robotList = new ArrayList<>();
        robotList.addAll(newList);
        notifyDataSetChanged();
}




}
