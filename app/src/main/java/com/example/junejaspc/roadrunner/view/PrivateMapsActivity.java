package com.example.junejaspc.roadrunner.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.junejaspc.roadrunner.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PrivateMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be us/ed.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Ludhiana = new LatLng(30.90, 75.85);
        mMap.addMarker(new MarkerOptions().position(Ludhiana).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Ludhiana));


        LatLng Patiala = new LatLng(30.33, 76.38);
        mMap.addMarker(new MarkerOptions().position(Patiala).title("Marker in Patiala"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Patiala));
    }
}
