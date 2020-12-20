package com.example.kotlinhomework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
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

        val button: Button = findViewById(R.id.buttonNameYourSelf)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult (requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val nameFromData: String? = data.getStringExtra(SecondActivity.NAME_KEY)
            if (nameFromData != null) {
                name = nameFromData
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        textView?.text = "$greetings, $name!"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }

    companion object {
        const val NAME_KEY: String = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }
}