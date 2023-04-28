package com.example.firebasedatabaseapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

//make sure to tick the launcher when you are making the activity,this will make it the first page someone sees after launching

class LoginActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    private lateinit var register:Button
    private lateinit var auth:FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.txtEmail)
        password = findViewById(R.id.txtPassword)
        login = findViewById(R.id.btnLogin)
        register = findViewById(R.id.btnRegister)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {

            //navigate user to the registration page

            var gotoregister = Intent(this, RegisterActivity::class.java)
            startActivity(gotoregister)
        }
        login.setOnClickListener {

            var edt_email = email.text.toString()
            var edt_password = password.text.toString()

            if(edt_email.isEmpty() || edt_password.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            } else{
                auth.signInWithEmailAndPassword(edt_email,edt_password).addOnCompleteListener(this){
                    if(it.isSuccessful){
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        var gotodash = Intent(this, MainActivity::class.java)
                        startActivity(gotodash)
                        finish()
                    } else{
                        Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show()
                    }


                }
            }






        }




    }
}