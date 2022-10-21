package com.example.foodtruck_project.fragments

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.foodtruck_project.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class GoogleMapsFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    lateinit var nyttFragment : Fragment

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val stockholm = LatLng(59.31118, 18.03002)
        googleMap.addMarker(MarkerOptions().position(stockholm).title("Marker in Stockholm, It högskolan"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm,15f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_google_maps, container, false)
        //nyttFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        return v
    }
  /*  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_signup, container, false)

        woopTV1 = v.findViewById(R.id.WoopTV1)
        woopTV2 = v.findViewById(R.id.WoopTV2)

        registerTextView = v.findViewById(R.id.registerTextView)
        newUsernameEditText = v.findViewById(R.id.newUsernameEditText)
        newUserAddressEditText = v.findViewById(R.id.newUserAddressEditText)
        newUserPhoneNumberEditText = v.findViewById(R.id.newUserPhoneNumberEditText)
        newUserPasswordEditText = v.findViewById(R.id.newUserPasswordEditText)
        newUserSignupButton = v.findViewById(R.id.newUserSignupButton)

        return v*/
    //}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }




}