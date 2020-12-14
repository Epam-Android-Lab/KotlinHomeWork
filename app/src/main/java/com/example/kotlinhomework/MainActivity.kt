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
        name = savedInstanceState?.getString(NAME_KEY) ?: getString(R.string.anon)
        textView = findViewById(R.id.textViewHello)
        val button = findViewById<Button>(R.id.buttonNameYourSelf)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, com.example.kotlinhomework.SecondActivity::class.java)
            startActivityForResult(intent, com.example.kotlinhomework.SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == com.example.kotlinhomework.SecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK)
            name = data?.getStringExtra(com.example.kotlinhomework.SecondActivity.NAME_KEY) ?: name
    }

    override fun onResume() {
        super.onResume()
        textView!!.text = String.format(
                "%s, %s!",
                greetings,
                name
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
                NAME_KEY,
                name,
        )
    }

    companion object {
        private const val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }
}