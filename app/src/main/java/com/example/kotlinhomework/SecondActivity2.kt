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

class SecondActivity2 : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button = findViewById(R.id.buttonName)
        editText = findViewById(R.id.editTextTextPersonName)

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Nothing to do
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Nothing to do
            }

            override fun afterTextChanged(editable: Editable?) {
                button.isEnabled = !TextUtils.isEmpty(editable)
            }

        })
        button.setOnClickListener{
            val intent = Intent()
            intent.putExtra(NAME_KEY, editText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    companion object{
        const val NAME_KEY: String = "com.example.kotlinhomework.SecondActivity.NAME_KEY"
        const val GET_NAME_REQUEST_CODE = 1234
    }

}