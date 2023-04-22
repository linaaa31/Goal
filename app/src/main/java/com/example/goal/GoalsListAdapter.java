package com.example.goal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goal.db.Goal;

import java.util.List;

public class GoalsListAdapter extends RecyclerView.Adapter<GoalsListAdapter.MyViewHolder>
{

    private Context context;
    private List<Goal> goalList;
//    private final LayoutInflater inflater;
//    private final ViewModel viewModel;

   // List<Goal> goalList, LayoutInflater inflater, ViewModel viewModel

    public GoalsListAdapter(Context context) {
        this.context = context;
//        this.goalList = goalList;
//        this.inflater = inflater;
//        this.viewModel = viewModel;
    }
//    public GoalsListAdapter(Context context) {
//        this.context = context;
//    }



    public void setGoalList (List < Goal > goalList){
        this.goalList = goalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                            int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder,int position){
        holder.goalText.setText(this.goalList.get(position).getGoalName());
        holder.descriptionText.setText(this.goalList.get(position).getGoalDescription());
        holder.question.setText(this.goalList.get(position).getQuestion());

    }

  //  private void updateGoal(Goal goal) {
    //    viewModel.updateGoal(goal);
  //  }

    @Override
    public int getItemCount () {
        return this.goalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView goalText;
        TextView descriptionText;
        TextView question;

        public MyViewHolder(View view) {
            super(view);
            goalText = view.findViewById(R.id.txt_goal);
            descriptionText = view.findViewById(R.id.txt_description);
            question= view.findViewById(R.id.question);
        }
    }
}