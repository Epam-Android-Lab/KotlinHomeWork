package com.example.kotlinhomework

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhomework.databinding.ActivityNewMainBinding

class NewMainActivity : AppCompatActivity() {

    private lateinit var greetings: String
    private lateinit var name: String
    private lateinit var textView: TextView

    private val binding: ActivityNewMainBinding by lazy {
        val tmpBinding = ActivityNewMainBinding.inflate(layoutInflater)
        setContentView(tmpBinding.root)
        tmpBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        greetings = getString(R.string.hello)
        name = getString(R.string.anon)

        savedInstanceState?.getString(NAME_KEY)?.let {
            name = it
        }

        textView = binding.textViewHello
        binding.buttonNameYourSelf.setOnClickListener {
            startActivityForResult(Intent(this, NewSecondActivity::class.java), NewSecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NewSecondActivity.GET_NAME_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            data.getStringExtra((NewSecondActivity.NAME_KEY))?.let{
                name = it
            }
        }
    }

    override fun onResume() {
        super.onResume()
        textView.text = getString(R.string.format_string, greetings, name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }


    companion object {
        const val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }
}