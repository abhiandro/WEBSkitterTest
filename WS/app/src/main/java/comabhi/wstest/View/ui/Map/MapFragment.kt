package comabhi.wstest.View.ui.Map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import comabhi.wstest.R
import comabhi.wstest.ViewModel.MapViewModel


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var notificationsViewModel: MapViewModel
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private val locationPermissionCode = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(MapViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_notifications, container, false)

        //locationPermission()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())


        initMapUi()
        return view
    }

    fun initMapUi(){
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        mMap.isMyLocationEnabled = true
        getLastLocation()

    }






    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            Log.e("ServiceLat", "" + mLastLocation.latitude)
            Log.e("ServiceLong", "" + mLastLocation.longitude)

        }
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        Log.e("GETLOCATION", "CALL")
            if (isLocationEnabled()) {
                if (checkPermissions()) {
                    mFusedLocationClient.lastLocation.addOnCompleteListener() { task ->
                        var location: Location? = task.result
                        if (location == null) {
                            requestNewLocationData()
                        } else {
                            Log.e("Lat", "" + location.latitude)
                            Log.e("Long", "" + location.longitude)

                            var myloc = LatLng(location.latitude, location.longitude);
                            mMap.addMarker(
                                MarkerOptions().position(myloc).title("My Location")
                            );
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
                            mMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    myloc,
                                    15f
                                )
                            )
                        }
                    }
                }

            }



    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }





}