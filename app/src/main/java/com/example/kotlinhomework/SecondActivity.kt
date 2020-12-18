package com.example.kotlinhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        editText = findViewById<EditText>(R.id.editTextTextPersonName)
        button = findViewById<Button>(R.id.buttonName)

        button.apply {
            isEnabled = false
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing to do
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing to do
            }

            override fun afterTextChanged(editable: Editable?) {
                button.isEnabled = !TextUtils.isEmpty(editable)
            }
        })

        with(button) {
            setOnClickListener {
                var intent = Intent(this@SecondActivity, MainActivity::class.java)
                intent.putExtra(NAME_KEY, editText.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val NAME_KEY: String = "com.example.kotlinhomework.SecondActivity.NAME_KEY"
        const val GET_NAME_REQUEST_CODE = 1234
    }
}

