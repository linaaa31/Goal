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

    @ColumnInfo(name = "question")
    private String question;




    public Goal(@NonNull String goalName, @Nullable String goalDescription, String question) {
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.question = question;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    @NonNull
    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(@NonNull String goalName) {
        this.goalName = goalName;
    }

    @Nullable
    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(@Nullable String goalDescription) {
        this.goalDescription = goalDescription;
    }

}

