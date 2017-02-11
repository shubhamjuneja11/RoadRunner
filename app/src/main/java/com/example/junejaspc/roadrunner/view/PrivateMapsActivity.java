package com.example.junejaspc.roadrunner.view;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.junejaspc.roadrunner.MainActivity;
import com.example.junejaspc.roadrunner.R;
import com.example.junejaspc.roadrunner.WalkActivity;
import com.example.junejaspc.roadrunner.model.Data;
import com.example.junejaspc.roadrunner.modelclasses.DistanceClass;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class PrivateMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LatLng s, e;
    double x = 0, g = 0;
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    TextView dest, start, dist;
    private GoogleMap mMap;
    Firebase firebase, firebase2;
    int f;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    String decide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decide = getIntent().getStringExtra("decide");
        setContentView(R.layout.activity_private_maps);
        start = (TextView) findViewById(R.id.starting_point);
        dest = (TextView) findViewById(R.id.destination_point);
        dist = (TextView) findViewById(R.id.distance);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Constants.fun();
        firebase = new Firebase(Constants.leader);
        firebase2 = new Firebase(Constants.publicf);
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("abcd", dataSnapshot.getKey());
                Log.e("abcd", dataSnapshot.getValue().toString());
                Double data = dataSnapshot.getValue(Double.class);
                Log.e("ljk", MainActivity.email);
                Log.e("ljk", Constants.convert2(dataSnapshot.getKey()));
                if (MainActivity.email.equals(Constants.convert2(dataSnapshot.getKey()))) {
                    Log.e("hihi", "");
                    x = data;
                }
                //if(data)
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Double data = dataSnapshot.getValue(Double.class);
                if (MainActivity.email.equals(Constants.convert2(dataSnapshot.getKey()))) {
                    Log.e("hihi", "");
                    x = data;
                }
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        } catch (Exception e) {
        }
    }

    public void fun(View view) {

        try {
            if (view.getId() == R.id.starting_point) f = 1;
            else f = 2;
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

    public void fun2(View view) {
        onMapReady(mMap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if (f == 1) {
                    start.setText(place.getAddress());
                    s = place.getLatLng();
                } else {
                    e = place.getLatLng();
                    dest.setText(place.getAddress());
                    e = place.getLatLng();
                    if (e != null && s != null)
                        g = distance(s.latitude, s.longitude, e.latitude, e.longitude);
                    dist.setText(g + "");
                }
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

    public void fun3(View view) {
        Log.e("pip", MainActivity.email);
        firebase.child(Constants.convert1(MainActivity.email)).setValue(x + g);
        if (decide!=null&&decide.equals("public"))
            firebase2.push().setValue(new Data(MainActivity.email, start.getText().toString(), dest.getText().toString(),
                    dist.getText().toString()));
    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PrivateMaps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
