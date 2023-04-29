package com.example.goal;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.goal.db.AppDatabase;
import com.example.goal.db.Goal;

import java.util.List;

public class GoalsActivity extends AppCompatActivity {
    private GoalsListAdapter goalsListAdapter;
    private final ActivityResultLauncher<Intent> addNewGoalLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    loadGoalList();
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);


        Button addNewGoalButton = findViewById(R.id.addNewGoal);
        addNewGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(GoalsActivity.this, AddNewGoalActivity.class);
            addNewGoalLauncher.launch(intent);
        });

        initRecyclerView();

        loadGoalList();

    }
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        goalsListAdapter = new GoalsListAdapter(this);
        recyclerView.setAdapter(goalsListAdapter);
    }
    private void loadGoalList() {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List <Goal> goalsList = db.goalDao().getAllGoals();
        goalsListAdapter.setGoalList(goalsList);
    }
    }
