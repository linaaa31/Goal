package com.example.goal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

        private TextView questionTextView;
        private Button yesButton;
        private Button noButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            String question = getIntent().getStringExtra("question");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.activity_question, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();


            questionTextView = dialogView.findViewById(R.id.questionTextView);
            questionTextView.setText(question);


            yesButton = dialogView.findViewById(R.id.yesButton);
            noButton = dialogView.findViewById(R.id.noButton);

            yesButton.setOnClickListener(v -> {
                dialog.dismiss();
            });

            noButton.setOnClickListener(v -> {
                dialog.dismiss();
            });

            dialog.show();
        }
    }
