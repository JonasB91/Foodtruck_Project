package com.example.foodtruck_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {


    lateinit var nameView: TextView
    lateinit var openHoursView: TextView
    lateinit var latitudeView: TextView
    lateinit var longitudeView: TextView
    lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    private var getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            nameView.text = (result.data?.getStringExtra("foodTruckName")).toString()
            openHoursView.text = (result.data?.getStringExtra("openHours")).toString()
            latitudeView.text = (result.data?.getStringExtra("latitude")).toString()
            longitudeView.text = (result.data?.getStringExtra("longitude")).toString()
            val item = items(
                name = nameView.text.toString(),
                openHours = openHoursView.text.toString(),
                latitude = (latitudeView.text as String).toDouble(),
                longitude = (longitudeView.text as String).toDouble()
            )

            val user = auth.currentUser

            if (user != null) {
                database.collection("users").document(user.uid).collection("Items").add(item)
                    .addOnCompleteListener {
                        Log.d("!!!", "add item")
                    }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        database = Firebase.firestore
        auth = Firebase.auth

        nameView = findViewById(R.id.nameView)
        openHoursView = findViewById(R.id.openHoursView)
        longitudeView = findViewById(R.id.longitudeView)
        latitudeView = findViewById(R.id.latitudeView)

        //här hämta värden från firebase och sätta in i textfälten ovan

        val editButton = findViewById<Button>(R.id.editButton)

        editButton.setOnClickListener {
            edit()
        }

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    fun edit() {

        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("foodTruckName", nameView.text)
        intent.putExtra("openHours", openHoursView.text)
        intent.putExtra("latitude", latitudeView.text)
        intent.putExtra("longitude", longitudeView.text)
        getContent.launch(intent)

    }

}



