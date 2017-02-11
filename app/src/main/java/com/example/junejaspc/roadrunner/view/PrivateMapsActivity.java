package com.example.junejaspc.roadrunner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.junejaspc.roadrunner.R;
import com.example.junejaspc.roadrunner.WalkActivity;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PrivateMapsActivity extends FragmentActivity implements OnMapReadyCallback {
LatLng s,e;
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    TextView dest,start,dist;
    private GoogleMap mMap;
    Firebase firebase;
    int f;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_maps);
        start=(TextView)findViewById(R.id.starting_point);
        dest=(TextView)findViewById(R.id.destination_point);
        dist=(TextView)findViewById(R.id.distance);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Constants.fun();
        firebase=new Firebase(Constants.leader);
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WalkActivity data=dataSnapshot.getValue(WalkActivity.class);
                //if(data)
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
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
try {
    LatLng Ludhiana = new LatLng(s.latitude, s.longitude);
    mMap.addMarker(new MarkerOptions().position(Ludhiana).title(start.getText().toString()));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(Ludhiana));


    LatLng Patiala = new LatLng(e.latitude, e.longitude);
    mMap.addMarker(new MarkerOptions().position(Patiala).title(dest.getText().toString()));

    mMap.moveCamera(CameraUpdateFactory.newLatLng(Patiala));
}catch (Exception e){}
    }
    public void fun(View view){

        try {if(view.getId()==R.id.starting_point)f=1;else f=2;
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }
    public void fun2(View view){
            onMapReady(mMap);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if(f==1){start.setText(place.getName());
                s=place.getLatLng();
                }
                else {dest.setText(place.getName());e=place.getLatLng();
                    double g=distance(s.latitude,s.longitude,e.latitude,e.longitude);
                    dist.setText(g+"");}
                //Log.i(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                //Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
