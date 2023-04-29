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

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private MutableLiveData<List<Goal>> goals;

    public ViewModel(@NonNull Application application) {
        super(application);
        goals = new MutableLiveData<>();
        appDatabase = DatabaseClient.getInstance(getApplication()).getAppDatabase();
    }

    public LiveData<List<Goal>> getRequests() {
        return goals;
    }
    public void addGoal(@NonNull String goalName, @Nullable String goalDescription, String question,String frequency, String startHour,String endHour) {
        if (!TextUtils.isEmpty(goalName)) {
            AsyncTask.execute(() -> {
                appDatabase.goalDao().insert(new Goal(goalName, goalDescription, question,frequency,startHour,endHour));
                refreshGoalList();
            });
        } else {
            Toast.makeText(getApplication(), "There are no goals", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteGoal(Goal goal){
        AsyncTask.execute(()->{
            appDatabase.goalDao().delete(goal);
            refreshGoalList();
        });
    }
    private void refreshGoalList() {
        List<Goal> updateList = appDatabase.goalDao().getAllGoals();
        goals.postValue(updateList);
    }

    public void readRequest() {
        AsyncTask.execute(() -> {
            refreshGoalList();
        });
    }
    public void updateGoal(Goal goal) {
        AsyncTask.execute(() -> {
            appDatabase.goalDao().updateGoal(goal);
            refreshGoalList();
        });
    }


//    public void updateQuestion(int goalId, String question) {
//        AsyncTask.execute(() -> {
//            appDatabase.goalDao().updateQuestion(goalId, question);
//            refreshGoalList();
//        });
//    }

//    public class ViewModel extends AndroidViewModel {
//        private AppDatabase appDatabase;
//        private MutableLiveData<List<Goal>> goals;
//
//        public ViewModel(@NonNull Application application) {
//            super(application);
//            goals = new MutableLiveData<>();
//            appDatabase = DatabaseClient.getInstance(getApplication()).getAppDatabase();
//        }
//
//        public LiveData<List<Goal>> getGoals() {
//            return goals;
//        }
//
//        public void addGoal(@NonNull String goalName, @Nullable String goalDescription, List<String> tasks) {
//            if (!TextUtils.isEmpty(goalName) && tasks != null && !tasks.isEmpty()) {
//                AsyncTask.execute(() -> {
//                    appDatabase.goalDao().insert(new Goal(goalName, goalDescription, tasks));
//                    refreshGoalList();
//                });
//            } else {
//                Toast.makeText(getApplication(), "There are no goals", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        public void deleteGoal(Goal goal){
//            AsyncTask.execute(()->{
//                appDatabase.goalDao().delete(goal);
//                refreshGoalList();
//            });
//        }
//
//        private void refreshGoalList() {
//            goals.postValue(appDatabase.goalDao().getAllGoals());
//        }
//
//        public void readRequest() {
//            AsyncTask.execute(() -> {
//                refreshGoalList();
//            });
//        }
//
//        public LiveData<List<Goal>> getGoals() {
//            return goals;
//        }
//    }

}
