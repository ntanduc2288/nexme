package com.nexme.presentation.ui.explore

import android.Manifest
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment
import pub.devrel.easypermissions.EasyPermissions

class ExploreFragment: BaseLiveDataFragment(), OnMapReadyCallback {

    private lateinit var exploreViewModel: ExploreViewModel
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }

    override fun registerViewModels() {
        exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
    }

    override fun getCurrentViewModel() = exploreViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.explorer_layout

    override fun setupViews() {

        var mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment

        mapFragment.getMapAsync(this)

        if (!EasyPermissions.hasPermissions(getCurrentActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            EasyPermissions.requestPermissions(this, getString(R.string.location_required),
                1, Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 10398 NE 17th St. Unit 302 Bellevue, WA 98004
        val sydney = LatLng(47.626110, -122.202280)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Bellevue"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12f))
    }
}