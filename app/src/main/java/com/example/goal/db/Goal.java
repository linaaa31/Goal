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

    @ColumnInfo(name = "frequency")
    private String frequency;

    @ColumnInfo(name="startHour")
    private String startHour;

    @ColumnInfo(name ="endHour")
    private String endHour;


    public Goal(@NonNull String goalName, @Nullable String goalDescription, String question, String frequency, String startHour,String endHour) {
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.question = question;
        this.frequency = frequency;
        this.startHour = startHour;
        this.endHour = endHour;


    }

    public String getStartHour() {return startHour;}

   public String getEndHour() {return endHour;}

    public String getFrequency() {return frequency;}

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

