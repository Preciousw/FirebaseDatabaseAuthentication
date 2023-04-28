package com.example.firebasedatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var inpCarMake:EditText
    private lateinit var inpCarModel:EditText
    private lateinit var inpCarPrice:EditText

    private lateinit var uploadPhoto:Button
    private lateinit var uploadData:Button
    private lateinit var viewUploads:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inpCarMake = findViewById(R.id.txtCarmake)
        inpCarModel = findViewById(R.id.txtCarmodel)
        inpCarPrice = findViewById(R.id.txtCarprice)

        uploadPhoto = findViewById(R.id.btnphoto)
        uploadData = findViewById(R.id.btnData)
        viewUploads = findViewById(R.id.btnView)


        //initialise
        var database = FirebaseDatabase.getInstance()

        //creating a table inside the firebase database
        var databaseRef = database.getReference("cars")

        //uploading photo
        uploadPhoto.setOnClickListener {
           var carmake = inpCarMake.text.toString().trim()
           var carmodel = inpCarModel.text.toString().trim()
            var carprice = inpCarPrice.text.toString().trim()

            //validate
            if (carmake.isEmpty() || carmodel.isEmpty() || carprice.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            } else{
                //try to upload data to firebase
                //create a class, not an empty activity (a template to hold the data)
                //Proceed to save data

                var usercar = Car(carmake,carmodel,carprice)

                // Create a reference to FirebaseDatabase

                var ref = FirebaseDatabase.getInstance().getReference().child("cars")

                ref.setValue(usercar).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Car Data Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Failed to save car info", Toast.LENGTH_SHORT).show()
                    }
                }

            }




        }

        //uploading all data
        uploadData.setOnClickListener {



        }

        //viewing uploaded data
        viewUploads.setOnClickListener {



        }







    }
}