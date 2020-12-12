package com.example.kotlinhomework;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.buttonName);
        button.setEnabled(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nothing to do
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nothing to do
            }

            @Override
            public void afterTextChanged(Editable editable) {
                button.setEnabled(!TextUtils.isEmpty(editable));
            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(NAME_KEY, editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public static final String NAME_KEY = "com.example.kotlinhomework.SecondActivity.NAME_KEY";
    public static final int GET_NAME_REQUEST_CODE = 1234;
}