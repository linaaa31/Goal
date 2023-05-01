package com.example.goal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.goal.db.AppDatabase;
import com.example.goal.db.DatabaseClient;
import com.example.goal.db.Goal;

import java.util.Calendar;
import java.util.Locale;

public class AddNewGoalActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_goal);

        final EditText goalName = findViewById(R.id.goal_name_edittext);
        final EditText goalDes = findViewById(R.id.goal_description_edittext);
        final EditText questionText = findViewById(R.id.question_edittext);
        final EditText frequencyText = findViewById(R.id.frequency);
       final EditText startHourText = findViewById(R.id.start_hour);
        final EditText endHourText = findViewById(R.id.end_hour);


        Button saveButton = findViewById(R.id.save);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

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
               String question =  questionText.getText().toString();
              String frequency= frequencyText.getText().toString();
             String startHour = startHourText.getText().toString();
             String endHour = endHourText.getText().toString();

               AppDatabase db = AppDatabase.getDBInstance(getApplicationContext());

                Goal goal = new Goal(name,descrip,question,frequency,startHour,endHour);
                //db.goalDao().insert(goal);
                db.goalDao().insert(goal);
                Intent intent = new Intent();
                intent.putExtra("isDataChanged", true);

               AlarmHelper alarmHelper = new AlarmHelper();
              //  alarmHelper.scheduleAlarm(getApplicationContext(), goal);
                alarmHelper.scheduleAlarm(getApplicationContext(), goal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
//


// Show the TimePickerDialog when the corresponding button is clicked


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
