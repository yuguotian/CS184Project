package edu.ucsb.cs.cs184.jiqi_wang.courseproject;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final int Access_Fine_Location = 1001;
    private final int Internet = 1002;
    private final int ERROR_DIALOG_REQUEST = 1003;
    private final int PERMISSIONS_REQUEST_ENABLE_GPS = 1004;
    private static boolean mLocationPermissionGranted = false;
    private static boolean ready = false;
    private FusedLocationProviderClient user_location_client;
    private Barcode.GeoPoint user_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Deal with fetch data issue
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //checkMapServices();
        checkPermissions();

        user_location_client = LocationServices.getFusedLocationProviderClient(this);
        updateUserLocation();

        FirebaseHelper.Initialize(this);

    }

    public void updateUserLocation(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return; //Permission check
        }
        user_location_client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                try{
                    if(task.isSuccessful()){
                        Location location = task.getResult();
                        user_location = new Barcode.GeoPoint(location.getLatitude(), location.getLongitude());
                        Log.d("Long Message", "onComplete: " + user_location.lat + " long " + user_location.lng);
                    }
                }catch (Exception e){
                    Log.e("Error", "onComplete: "+ e.getMessage());
                }
            }
        });
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
        FirebaseHelper.getMap(mMap);
        LatLng ucsb = new LatLng(34.412936, -119.847863);
        mMap.setMinZoomPreference(12);
        mMap.setMaxZoomPreference(20);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ucsb));
        //CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        //mMap.setInfoWindowAdapter(customInfoWindow);


        /*mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions marker = new MarkerOptions();
                marker.position(latLng);
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                marker.title("User Point");
                Marker m = mMap.addMarker(marker);
            }
        });*/

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().compareTo("User Point") == 0){
                    showUserPointDialog();
                }
                else{
                    showStopInfoDialog((StopInfoClass)marker.getTag());
                }
                return false;
            }
        });

        /*ArrayList<String[]> stop = Helper.getPositionData("https://openmobilitydata-data.s3-us-west-1.amazonaws.com/public/feeds/santa-barbara-mtd/1156/20191119/original/shapes.txt");
        PolylineOptions option = new PolylineOptions();
        PolylineOptions option2 = new PolylineOptions();
        option2.color(Color.CYAN);
        for(int i = 0; i < stop.size(); i++){
            if(stop.get(i)[0].compareTo("19213") == 0){
                LatLng ll = new LatLng(Double.parseDouble(stop.get(i)[1]), Double.parseDouble(stop.get(i)[2]));
                option.add(ll);
            }
            else{
                LatLng ll = new LatLng(Double.parseDouble(stop.get(i)[1]), Double.parseDouble(stop.get(i)[2]));
                option2.add(ll);
            }
        }
        mMap.addPolyline(option);
        mMap.addPolyline(option2);*/
    }

    //Dialogs
    public void showUserPointDialog(){
        UserMarkerDialog u = new UserMarkerDialog();
        u.show(getSupportFragmentManager(), "User");
    }

    public void showStopInfoDialog(StopInfoClass info){
        FragmentManager fm = getSupportFragmentManager();
        InfoWindowDialog info_window = InfoWindowDialog.newInstance(info);
        info_window.show(fm, "Info_Window");
    }

    private void checkPermissions(){
        if	(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                ==	PackageManager.PERMISSION_GRANTED){}
        else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, Access_Fine_Location);
        }

        if	(ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                ==	PackageManager.PERMISSION_GRANTED){}
        else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.INTERNET}, Internet);
        }
    }

    //Functionality check helper functions
    public boolean isServicesOK(){
        Log.d("Message", "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available == ConnectionResult.SUCCESS){
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){ //No google play service
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        if (requestCode == Access_Fine_Location) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(mLocationPermissionGranted){
                    ready = true;
                }
                else{
                    checkPermissions();
                }
            }
        }

    }

    private boolean checkMapServices(){
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true;
            }
        }
        return false;
    }
}
