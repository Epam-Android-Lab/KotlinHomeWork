package com.example.kotlinhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhomework.SecondActivityJava.NAME_KEY

class MainActivity: AppCompatActivity() {
    private var greetings: String? = null;
    private var name: String? = null;
    private var textView: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetings = getString(R.string.hello)
        if (savedInstanceState != null && savedInstanceState.getString(NAME_KEY) != null) {
            name = savedInstanceState.getString(NAME_KEY)
        } else {
            name = getString(R.string.anon);
        }
        textView = findViewById(R.id.textViewHello)
        val button = findViewById<Button>(R.id.buttonNameYourSelf)
        /*button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity.class)
            startActivityForResult(intent, SecondActivityJava.GET_NAME_REQUEST_CODE)
        }*/
    }

    override protected fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val nameFromData = data!!.getStringExtra(SecondActivity.NAME_KEY)

            if (nameFromData != null) {
                name = nameFromData
            }
        }
    }

    override protected fun onResume() {
        super.onResume()
        textView!!.setText("${greetings}, ${name}!")
    }

    override protected fun onSaveInstanceState(@NonNull outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }

    companion object {
        private val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }


}