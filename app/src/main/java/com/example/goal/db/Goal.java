package com.example.goal.db;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Goals")
public class Goal {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "goal_id")
    private int goalId;
    @NonNull
    @ColumnInfo(name = "goal_name")
    private String goalName;
    @Nullable
    @ColumnInfo(name = "goal_description")
    private String goalDescription;
    @ColumnInfo(name = "completed")
    private boolean completed;
//    @ColumnInfo(name = "tasks")
//    private List<String> tasks;

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    //    public List<String> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<String> tasks) {
//        this.tasks = tasks;
//    }
@ColumnInfo(name = "tasks")
private String tasks;

    public Goal(@NonNull String goalName, @Nullable String goalDescription,String tasks) {
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.tasks = tasks;
    }

    public int getGoalId() {
        return goalId;
    }

    @NonNull
    public String getGoalName() {
        return goalName;
    }

    @Nullable
    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setGoalName(@NonNull String goalName) {
        this.goalName = goalName;
    }

    public void setGoalDescription(@Nullable String goalDescription) {
        this.goalDescription = goalDescription;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

