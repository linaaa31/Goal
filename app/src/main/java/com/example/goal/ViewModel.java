package com.example.goal;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goal.db.AppDatabase;
import com.example.goal.db.DatabaseClient;
import com.example.goal.db.Goal;

import java.util.ArrayList;
import java.util.List;
public class ViewModel extends AndroidViewModel {
    private MutableLiveData<List<Goal>> allGoals;
    private MutableLiveData<List<Goal>> filteredGoals;
    private AlarmHelper alarmHelper;
    private AppDatabase appDatabase;

    public ViewModel(@NonNull Application application) {
        super(application);
        allGoals = new MutableLiveData<>();
        filteredGoals = new MutableLiveData<>();
        alarmHelper = new AlarmHelper();
        appDatabase = DatabaseClient.getInstance(getApplication()).getAppDatabase();
    }

    public LiveData<List<Goal>> getAllGoals() {
        return allGoals;
    }

    public LiveData<List<Goal>> getFilteredGoals(long lastNotificationTime) {
        List<Goal> allGoals = this.allGoals.getValue();
        List<Goal> filteredList = new ArrayList<>();
        if (allGoals != null) {
            for (Goal goal : allGoals) {
                long goalLastNotification = Long.parseLong(goal.getLastNotification());
                long frequencyInMillis = getFrequencyInMillis(Long.parseLong(goal.getFrequency()));
                if (goalLastNotification + frequencyInMillis <= lastNotificationTime) {
                    filteredList.add(goal);
                }
            }
        }
        filteredGoals.setValue(filteredList);
        return filteredGoals;
    }

    public void addGoal(@NonNull String goalName, @Nullable String goalDescription, String question, String frequency) {
        if (!TextUtils.isEmpty(goalName)) {
            AsyncTask.execute(() -> {
                Goal newGoal = new Goal(goalName, goalDescription, question, frequency);
                appDatabase.goalDao().insert(newGoal);
                allGoals.postValue(appDatabase.goalDao().getAllGoals());
                scheduleNotification(getApplication(), newGoal);
            });
        } else {
            Toast.makeText(getApplication(), "There are no goals", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateGoal(Goal goal) {
        AsyncTask.execute(() -> {
            appDatabase.goalDao().updateGoal(goal);
            allGoals.postValue(appDatabase.goalDao().getAllGoals());
            scheduleNotification(getApplication(), goal);
        });
    }

    public void deleteGoal(Goal goal) {
        AsyncTask.execute(() -> {
            appDatabase.goalDao().delete(goal);
            allGoals.postValue(appDatabase.goalDao().getAllGoals());
            alarmHelper.stopAlarm(getApplication(), goal.getGoalId());
        });
    }

    public void readRequest() {
        AsyncTask.execute(() -> {
            allGoals.postValue(appDatabase.goalDao().getAllGoals());
            long lastNotificationTime = System.currentTimeMillis() - getFrequencyInMillis(1);
            getFilteredGoals(lastNotificationTime);
        });
    }

    private long getFrequencyInMillis(long frequency) {
        return frequency * 3600000L;
    }

    public void scheduleNotification(Context context, Goal goal) {
        long frequencyInMillis = getFrequencyInMillis(Long.parseLong(goal.getFrequency()));
        alarmHelper.scheduleAlarm(context, goal, frequencyInMillis);
    }

    public void stopNotification(Goal goal) {
        alarmHelper.stopAlarm(getApplication(), goal.getGoalId());
    }
}
