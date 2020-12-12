package com.example.kotlinhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.kotlinhomework.databinding.ActivityNewMainBinding
import com.example.kotlinhomework.databinding.ActivityNewSecondBinding

class NewSecondActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button

    private val binding: ActivityNewSecondBinding by lazy {
        val tmpBinding = ActivityNewSecondBinding.inflate(layoutInflater)
        setContentView(tmpBinding.root)
        tmpBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editText = binding.editTextTextPersonName
        button = binding.buttonName
        button.isEnabled = false
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to do
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nothing to do
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if(it.isNotEmpty()) button.isEnabled = true
                }

            }

        })

        button.setOnClickListener {
            val intent = Intent().apply {
                putExtra(NAME_KEY, editText.text.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val NAME_KEY = "com.example.kotlinhomework.SecondActivity.NAME_KEY"
        const val GET_NAME_REQUEST_CODE = 1234
    }
}