package com.example.goal.db;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GoalDao {
    @Query("SELECT * FROM Goals")
    List<Goal> getAllGoals();

    @Insert
    void insert(Goal goal);

@Update
void updateGoal(Goal goal);
    @Delete
    void delete(Goal goal);
}

