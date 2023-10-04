package com.example.login_registration_kotlin_firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView  // Import TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    private lateinit var textview: TextView  // Change Text to TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        button = findViewById(R.id.logout)
        textview = findViewById(R.id.user_details)

        val user = auth.currentUser // Retrieve the current user

        if (user == null) {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        } else {
            textview.text = user.email // Use text property to set text
        }

        button.setOnClickListener {
            auth.signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
