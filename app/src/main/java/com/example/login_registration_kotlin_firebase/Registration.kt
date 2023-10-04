package com.example.login_registration_kotlin_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonRegistration: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var loginView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonRegistration = findViewById(R.id.btn_registration)
        progressBar = findViewById(R.id.progressBar)
        loginView = findViewById(R.id.loginNow)

        auth = FirebaseAuth.getInstance() // Initialize FirebaseAuth

        loginView.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }

        buttonRegistration.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            progressBar.visibility = View.VISIBLE

            if (email.isEmpty()){
                Toast.makeText(baseContext,
                    "Enter email",
                    Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE // Hide progress bar
                return@setOnClickListener
            }

            if (password.isEmpty()){
                Toast.makeText(baseContext,
                    "Enter password",
                    Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE // Hide progress bar
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE // Hide progress bar
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext,
                            "Authentication successful.",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign-up fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}
