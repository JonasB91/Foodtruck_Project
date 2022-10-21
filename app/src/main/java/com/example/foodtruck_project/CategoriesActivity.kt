package com.example.foodtruck_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruck_project.fragments.AccProfileFragment
import com.example.foodtruck_project.fragments.GoogleMapsFragment
import com.example.foodtruck_project.fragments.MapexploreFragment
import com.example.foodtruck_project.fragments.SearchprefFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoriesActivity : AppCompatActivity() {
    lateinit var dataManager: DataManager
    lateinit var navigationMenu: BottomNavigationView
    lateinit var GoogleMapsFragment : GoogleMapsFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        dataManager = DataManager()
        val allCategories = dataManager.getAllCategories()

        var recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AllCategoriesRecycleAdapter(this, allCategories)
        recyclerView.adapter = adapter

        navigationMenu = findViewById(R.id.bottom_navigation)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore ->
                    replaceFragment(GoogleMapsFragment)

                R.id.ic_searchpref -> {
                    val intent = Intent(this,CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val intent = Intent(this, SignUpActivity:: class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

}