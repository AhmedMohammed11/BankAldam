package com.example.bankaldam.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;

public class MyLocationProvider {
    Location location;
    LocationManager locationManager;
    boolean cangetLocation;

    Context context;
    final static long MIN_TIME_BETWEEN_UPDATES=5*1000;
    final static Float MIN_DISTANCE_BETWEEN_UPDATES=20.0F;



    public MyLocationProvider(Context context) {
        this.context = context;
        locationManager = (LocationManager)context.getSystemService ( Context.LOCATION_SERVICE );
        cangetLocation=false;
        location=null;
        getLocation ();
    }



    @SuppressLint("MissingPermission")
    private void getLocation(){
        boolean isNetworkEnabled = false;
        boolean isGPSEnabled = false;

        isNetworkEnabled =locationManager.isProviderEnabled ( LocationManager.NETWORK_PROVIDER );
        isGPSEnabled =locationManager.isProviderEnabled ( LocationManager.GPS_PROVIDER );

        if (!isNetworkEnabled&&!isGPSEnabled){
            cangetLocation=false;
            location=null;
            return;
        }
        String provider= null;
        if (isNetworkEnabled) provider = locationManager.NETWORK_PROVIDER;
        if (isGPSEnabled) provider = locationManager.GPS_PROVIDER;

        location=locationManager.getLastKnownLocation ( provider );

         if (location==null){
             location = getBestLastKnownLocation();
         }

    }


    @SuppressLint("MissingPermission")
    public Location getBestLastKnownLocation()
    {
        List<String> providers = locationManager.getProviders ( false );

        for (int i = 0; i < providers.size (); i++) {
            String provider = providers.get ( i );
            Location temp = locationManager.getLastKnownLocation ( provider );
            if (location == null) {
                location = temp;
                continue;

            }
           // location.getTime ();
            if (temp != null) {
                if (temp.getAccuracy () > location.getAccuracy ()) {
                    location = temp;
                }
            }

        }
        return location;
    }

    @SuppressLint("MissingPermission")
    public void trackLocation(LocationListener locationListener){
        boolean isNetworkEnabled = false;
        boolean isGPSEnabled = false;

        isNetworkEnabled =locationManager.isProviderEnabled ( LocationManager.NETWORK_PROVIDER );
        isGPSEnabled =locationManager.isProviderEnabled ( LocationManager.GPS_PROVIDER );

        if (!isNetworkEnabled&&!isGPSEnabled){
            cangetLocation=false;
            location=null;
            return;
        }
        String provider= null;
        if (isNetworkEnabled) provider = locationManager.NETWORK_PROVIDER;
        if (isGPSEnabled) provider = locationManager.GPS_PROVIDER;


        locationManager.requestLocationUpdates ( provider, MIN_TIME_BETWEEN_UPDATES
                , MIN_DISTANCE_BETWEEN_UPDATES, locationListener );

    }
}