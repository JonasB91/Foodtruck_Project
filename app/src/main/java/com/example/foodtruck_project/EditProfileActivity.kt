package com.example.foodtruck_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var openHoursEditText: EditText
    lateinit var latitudeEditText: EditText
    lateinit var longitudeEditText: EditText
    lateinit var categorySpinner: Spinner
    lateinit var menuEditText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val foodTruckName = intent.getStringExtra("foodTruckName")
        val openHours = intent.getStringExtra("openHours")
        val latitude = intent.getStringExtra("latitude")
        val longitude = intent.getStringExtra("longitude")
        val category = intent.getStringExtra("category")
        val menu = intent.getStringExtra("menu")

        nameEditText = findViewById(R.id.nameEditText)
        nameEditText.setText(foodTruckName)

        openHoursEditText = findViewById(R.id.openHoursEditText)
        openHoursEditText.setText(openHours)

        latitudeEditText = findViewById(R.id.latitudeEditText)
        latitudeEditText.setText(latitude.toString())

        longitudeEditText = findViewById(R.id.longitudeEditText)
        longitudeEditText.setText(longitude.toString())

        menuEditText = findViewById(R.id.menuEditText)
        menuEditText.text = menu

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }

        val saveButton = findViewById<Button>(R.id.editButton)
        saveButton.setOnClickListener {
            saveChanges()
        }
        val arraySpinner = arrayOf(
            "American", "Asian", "Fast food", "Indian", "Italian", "Mexican"
        )

        categorySpinner = findViewById(R.id.categorySpinner)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arraySpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        val spinnerPosition: Int = adapter.getPosition(category)

        categorySpinner.setSelection(spinnerPosition)


    }

    private fun saveChanges() {

        val intent = Intent()
        intent.putExtra("foodTruckName", nameEditText.text.toString())
        intent.putExtra("openHours", openHoursEditText.text.toString())
        intent.putExtra("latitude", latitudeEditText.text.toString())
        intent.putExtra("longitude", longitudeEditText.text.toString())
        intent.putExtra("menu", menuEditText.text.toString())
       setResult(Activity.RESULT_OK, intent)


        var category: String = categorySpinner.selectedItem.toString()
        intent.putExtra("category", category)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}