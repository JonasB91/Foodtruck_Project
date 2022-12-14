package com.example.foodtruck_project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.foodtruck_project.*
import com.example.foodtruck_project.databinding.ActivitySignInBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var navigationMenu : BottomNavigationView

    lateinit var havingP : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()



        val user = auth.currentUser



        if (user != null) {
            // användaren signar in (getCurrentUser() kommer att bli null om inte signad in.

            val intent = Intent(this, ProfileActivity::class.java);
            startActivity(intent);
            finish();
        }

        binding = ActivitySignInBinding.inflate(layoutInflater)

        setContentView(binding.root)



        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView2.setOnClickListener {
            val intent = Intent(this, SignUpActivity:: class.java)
            startActivity(intent)
        }

        binding.button22.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {


                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, ProfileActivity:: class.java)

                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }



            } else {
                Toast.makeText(this, "there are empty fields", Toast.LENGTH_SHORT).show()
            }

        }



        navigationMenu = findViewById(R.id.bottom_navigation3)

        navigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_mapexplore -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_searchpref -> {
                    val intent = Intent(this, CategoriesActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_accountprofile -> {
                    val intent = Intent(this, SignUpActivity:: class.java)
                    startActivity(intent)
                }
            }
            true
        }




      /*  myView!!.havingP.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                data = Uri.parse("mailto:")
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "to@email.com")
                putExtra(Intent.EXTRA_SUBJECT, "Subject of Email")
            }
            if (intent.resolveActivity(activity!!.packageManager) != null) {
                intent.setPackage("com.google.android.gm")
                startActivity(intent)
            } else {
                Log.d(TAG, "No app available to send email.")
            }
        } */




        havingP = findViewById(R.id.havingP)


        havingP.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", "tecnicalSupport@email.com", null))

            startActivity(Intent.createChooser(emailIntent,"Send email"))
        }




    }


}
