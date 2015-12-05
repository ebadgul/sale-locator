package ie.wit.www.salelocator;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private MarkerOptions markerOptions;
    private Marker marker;
    private ParseUser parseUser = new ParseUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        parseUser =  ParseUser.getCurrentUser();
//        final EditText name = (EditText) findViewById(R.id.cName);

//        nameFromP.setText();
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

    public void registerBtn(View view) {

//        ParseUser user = new ParseUser();
       // parseUser.put("latitude", SaleLocatorApp.currentShopUser.latitude);


        Intent intent = new Intent(MapsActivity.this, ShopControl.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50000, 5, this);

        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            public void onMapClick(LatLng point) {
                if (marker != null) {
                    marker.remove();
                }
                markerOptions = new MarkerOptions()
                        .position(new LatLng(point.latitude, point.longitude))
                        .title("")
                        .snippet("")
                        .draggable(true);
                    Log.v("Latitude :", "" +point.latitude);
                Log.v("Longitude :", "" +point.longitude);

//                System.out.println(point.latitude + "" + point.longitude);
//                SaleLocatorApp.currentShopUser.latitude = point.latitude;
//                SaleLocatorApp.currentShopUser.longitude = point.longitude;


                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                marker = googleMap.addMarker(markerOptions);
                ParseGeoPoint pg = new ParseGeoPoint(point.latitude, point.longitude);
                parseUser.put("latitude", pg);
                parseUser.saveInBackground();
//                SaleLocatorApp.currentShopUser.latitude = point.latitude;
//                SaleLocatorApp.currentShopUser.longitude = point.longitude;
            }

        });


        // Add a marker in Sydney and move the camera

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                final boolean enabledGPS = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // if (!enabledGPS) {
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MapsActivity.this);
                alertDialogBuilder.setTitle("Location services disabled");

                alertDialogBuilder
                        .setMessage("Sale Locator needs access to your location. Please turn on location access.")
                        .setCancelable(false)
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);


                            }
                        }).setNegativeButton("Ignore", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
                if (!enabledGPS) {
                    android.app.AlertDialog alerDialog = alertDialogBuilder.create();
                    alerDialog.show();
                    return true;
                }
                return false;

            }
        });

    }

    //**************LocationListener abstract implemented methods*******
    //**************LocationListener abstract implemented methods*******
    //**************LocationListener abstract implemented methods*******
    @Override
    public void onLocationChanged(Location location) {
        LatLng userLocation = null;
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            userLocation = new LatLng(lat, lng);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10.0f));

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
}
