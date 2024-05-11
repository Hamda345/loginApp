package com.example.loginapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.loginapp.databinding.ActivityLoginBinding
import com.example.loginapp.databinding.ActivityMainBinding
import com.example.loginapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var loginNow: TextView
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
        firebaseAuth = FirebaseAuth.getInstance()
        loginNow = findViewById(R.id.loginNow)

        loginNow.setOnClickListener {
            val intent = Intent(this, ActivityLoginBinding::class.java)
            startActivity(intent)
        }
        binding.registerBtn.setOnClickListener{
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val intent = Intent(this, ActivityMainBinding::class.java)
                        startActivity(intent)
                    } else{
                        Log.d(TAG, "createUserWithEmail:failure", it.exception)
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            } else Toast.makeText(this, "Empty Fields Are Not Allowed !", Toast.LENGTH_SHORT).show()
        }
    }
}