package com.example.bankaldam.View.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankaldam.Helper.GPSTracker;
import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.Helper.MyLocationProvider;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.homeCycle.RequestFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements LocationListener,
        OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_Access_GPS = 500;
    @BindView(R.id.get)
    Button getLocation;


    private GoogleMap mMap;
    private Location location;
    private MapView mapView;
    private Marker marker = null;
    private MyLocationProvider locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        if (isGPSPermissionAllowed()) {
            //call your  function
            initializeGPS();
        } else {
            //request permisssion
            requestLocationPermision();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GPSTracker gpsTracker = new GPSTracker(this);
        //location = gpsTracker.getLocation();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setMyLocation();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                mMap.addMarker(markerOptions);
            }
        });
    }

    public void setMyLocation() {
        if (location != null && mMap != null) {
            LatLng latLng = new LatLng
                    (location.getLatitude(), location.getLongitude());
            if (marker == null) {
                marker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("My Location")
                );
            } else marker.setPosition(latLng);


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    public boolean isGPSPermissionAllowed() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;

    }


    public void initializeGPS() {
        Toast.makeText(this, "GPS ALLOWED", Toast.LENGTH_SHORT).show();
        locationProvider = new MyLocationProvider(this);
        location = locationProvider.getBestLastKnownLocation();

        if (location == null) {
            Toast.makeText(this, "cannot get your location", Toast.LENGTH_SHORT).show();
        } else {
//            locationTextView.setText ( location.getLatitude ()+""+location.getLongitude () );
//            Log.e ( "location",location.toString () );
            Toast.makeText(this, "" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        locationProvider.trackLocation(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private void requestLocationPermision() {


        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            //showDialog

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.warning)
                    .setTitle(R.string.message_request_GPS_reason)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityCompat.requestPermissions(MapsActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_Access_GPS);
                        }
                    }).show();

        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_Access_GPS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


    }

    @OnClick(R.id.get)
    public void onViewClicked() {

        RequestFragment fragment = new RequestFragment();
        HelperMethod.ReplaceFragment(getSupportFragmentManager(),fragment,R.id.fram_homecycle,null,null);

    }
}
