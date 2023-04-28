package com.example.firebasedatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var emailRegister:EditText
    private lateinit var passwordRegister:EditText
    private lateinit var createAccount:Button
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name = findViewById(R.id.edtName)
        emailRegister = findViewById(R.id.edtEmail)
        passwordRegister =  findViewById(R.id.edtPassword)
        createAccount = findViewById(R.id.btnCreateAcc)
        auth = FirebaseAuth.getInstance()


        createAccount.setOnClickListener {
            var name = name.text.toString().trim()
            var email = emailRegister.text.toString().trim()
            var password = passwordRegister.text.toString().trim()


            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Cannnot submit empty field", Toast.LENGTH_SHORT).show()

            }
            else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    if(it.isSuccessful){
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                        //navigate to login
                        var gotologin = Intent(this, LoginActivity::class.java)
                        startActivity(gotologin)

                    }
                    else{
                        Toast.makeText(this, "Failed to create the account", Toast.LENGTH_SHORT).show()
                    }



                }



            }



            
        }


    }
}