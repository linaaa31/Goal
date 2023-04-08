package com.example.goal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.goal.db.AppDatabase;
import com.example.goal.db.DatabaseClient;
import com.example.goal.db.Goal;

public class AddNewGoalActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal);

        final EditText goalName = findViewById(R.id.goal_name_edittext);
        final EditText goalDes = findViewById(R.id.goal_description_edittext);
        final EditText task = findViewById(R.id.task_edittext);

        Button saveButton = findViewById(R.id.save);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Reload the film list when the AddNewFilmActivity is finished
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            if (intent != null) {
                                boolean isDataChanged = intent.getBooleanExtra("isDataChanged", false);
                                if (isDataChanged) {
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }
                        }
                    }
                });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = goalName.getText().toString();
                String descrip = goalDes.getText().toString();
                String tasks = task.getText().toString();


                // Save the new film data to the database
               AppDatabase db = AppDatabase.getDBInstance(getApplicationContext());
               // DatabaseClient db = DatabaseClient.getInstance(getApplicationContext());
                Goal goal = new Goal(name,descrip,tasks);
                db.goalDao().insert(goal);


                // Create an intent to return the result to the MainActivity
                Intent intent = new Intent();
                intent.putExtra("isDataChanged", true);

                // Set the result and finish the AddNewFilmActivity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
