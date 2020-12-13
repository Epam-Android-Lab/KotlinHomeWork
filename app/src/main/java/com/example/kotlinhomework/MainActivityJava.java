package com.example.kotlinhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityJava extends AppCompatActivity {

    private String greetings;
    private String name;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetings = getString(R.string.hello);
        if (savedInstanceState != null && savedInstanceState.getString(NAME_KEY) != null) {
            name = savedInstanceState.getString(NAME_KEY);
        } else {
            name = getString(R.string.anon);
        }

        textView = findViewById(R.id.textViewHello);

        Button button = findViewById(R.id.buttonNameYourSelf);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityJava.this, SecondActivityJava.class);
                startActivityForResult(intent, SecondActivityJava.GET_NAME_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SecondActivityJava.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String nameFromData = data.getStringExtra(SecondActivityJava.NAME_KEY);

            if (nameFromData != null) {
                name = nameFromData;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(String.format("%s, %s!", greetings, name));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NAME_KEY, name);
    }

    private static final String NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY";
}