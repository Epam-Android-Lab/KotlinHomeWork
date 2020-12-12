package com.example.kotlinhomework

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.kotlinhomework.R
import com.example.kotlinhomework.MainActivity
import android.content.Intent
import com.example.kotlinhomework.SecondActivity
import android.app.Activity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var greetings: String? = null
    private var name: String? = null
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greetings = getString(R.string.hello)
        name = if (savedInstanceState?.getString(NAME_KEY) != null) {
            savedInstanceState.getString(NAME_KEY)
        } else {
            getString(R.string.anon)
        }
        textView = findViewById(R.id.textViewHello)
        val button = findViewById<Button>(R.id.buttonNameYourSelf)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val nameFromData = data.getStringExtra(SecondActivity.NAME_KEY)
            if (nameFromData != null) {
                name = nameFromData
            }
        }
    }

    override fun onResume() {
        super.onResume()
        textView!!.text = String.format("%s, %s!", greetings, name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }

    companion object {
        private const val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }
}