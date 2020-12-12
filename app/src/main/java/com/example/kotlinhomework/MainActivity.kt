package com.example.kotlinhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.example.kotlinhomework.SecondActivity.NAME_KEY

class MainActivity: AppCompatActivity() {
    private var greetings: String? = null
    private var name: String? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetings = getString(R.string.hello)
        if (savedInstanceState != null && savedInstanceState.getString(NAME_KEY) != null) {
            name = savedInstanceState.getString(NAME_KEY)
        } else {
            name = "Anon"
        }

        textView = findViewById(R.id.textViewHello)
        val button = findViewById<Button>(R.id.buttonNameYourSelf)

        button.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            var nameFromData: String? = data.getStringExtra(SecondActivity.NAME_KEY)

            if (nameFromData != null)
                name = nameFromData
        }
    }

    override fun onResume() {
        super.onResume()
        textView?.text = "$greetings, $name!"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }

    companion object {
        var NAME_KEY: String = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }
}