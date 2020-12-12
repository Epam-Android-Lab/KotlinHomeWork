package com.example.kotlinhomework
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

private var greetings: String? = null
private var name: String? = null
private var textView: TextView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetings = getString(R.string.hello)

        name = savedInstanceState?.getString(NAME_KEY) ?: "Anon"

        textView = findViewById(R.id.textViewHello)

        val button = findViewById<Button>(R.id.buttonNameYourSelf)
        button.setOnClickListener(){
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        name = data.getStringExtra(SecondActivity.NAME_KEY)
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

private val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"

        }