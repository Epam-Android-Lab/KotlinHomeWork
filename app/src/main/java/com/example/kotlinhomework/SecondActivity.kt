package com.example.kotlinhomework


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val editText: EditText? = findViewById(R.id.editTextTextPersonName)
        val button: Button? = findViewById(R.id.buttonName)



        button?.isEnabled = false
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Nothing to do
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Nothing to do
            }

            override fun afterTextChanged(editable: Editable) {
                button?.isEnabled = !TextUtils.isEmpty(editable)
            }
        })
        button?.setOnClickListener {
            val intent = Intent()
            intent.putExtra(SecondActivity.NAME_KEY, editText?.getText().toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    companion object {
        const val NAME_KEY = "com.example.kotlinhomework.SecondActivity.NAME_KEY"
        const val GET_NAME_REQUEST_CODE = 1234
    }
}