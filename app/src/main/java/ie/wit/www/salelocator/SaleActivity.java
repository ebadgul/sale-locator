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
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseObject;
import com.parse.FindCallback;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SaleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private MarkerOptions markerOptions;
    private Marker marker;
    private static List<ShopUser> allUsers = new ArrayList<ShopUser>();
    public static ParseUser currentParseUser;

    private List<ParseObject> po = new ArrayList<>();
//    private ArrayList all = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //currentParseUser = ParseUser.getCurrentUser();
        /// parse key
        Parse.initialize(this, "cjKQ2tn7uROcrOX2nHI1Fvx1YcTnSHazDLfNbrM7", "cUpyCIZV5Uio5CB6NT5dgrt5bMmUBmREGsE0BtBO");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///current location
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50000, 5, this);

        //map start
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereNotEqualTo("shopName", "jjj");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {


                    for (int i = 0; i< userList.size(); i++){
//                        userList.get(i).getParseGeoPoint("latitude");
                        userList.get(i).getParseGeoPoint("latitude");


                        Log.v("all users", "" + userList.get(i).getParseGeoPoint("latitude"));
//                      allUsers = userList.get(i).getPa
                    }


                    Log.e("score", "Retrieved " + userList.size() + " Points");
                } else {
                    Log.e("score", "Error: " + e.getMessage());
                }
            }
        });



    }
    ////all the code goes here

    @Override
    public void onResume(){
        super.onResume();

        // Make sure that GPS is enabled on the device
        LocationManager mlocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled) {
            showDialogGPS();
        }

    }



    /**
     * Show a dialog to the user requesting to enable GPS
     */
    private void showDialogGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Enable GPS");
        builder.setMessage("Please enable GPS for Sale Locator");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



    //start of map method
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocaiton, 10.0f));
//        ParseGeoPoint pgp = currentParseUser.getParseGeoPoint("latitude");
//
//        pgp.getLatitude();
     /*      currentParseUser  = allUsers.get(i).getParseGeoPoint("latitude");
            po = allUsers.get(i).getParseGeoPoint("latitude");
//
//
        markerOptions = new MarkerOptions()
                .position(new LatLng(po.getLatitude(), lat.getLongitude()))
                .title(currentParseUser.getString("shopName"))
                .snippet(currentParseUser.getString("shopAddress"))
                .draggable(false);


        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker = googleMap.addMarker(markerOptions);*/




        // Add a marker in Sydney and move the camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50000, 5, this);

        }



        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                final boolean enabledGPS = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // if (!enabledGPS) {
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SaleActivity.this);
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
                if(!enabledGPS){
                    android.app.AlertDialog alerDialog = alertDialogBuilder.create();
                    alerDialog.show();
                    return true;
                }
                return false;

            }
        });

//         mMap.setMyLocationEnabled(true);
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    //end of map


// parse
    public ShopUser toUser(ParseUser pUser){
        String uName = pUser.getUsername();
        String shopName = pUser.getString("shopName");
        String shopAddress = pUser.getString("shopAddress");

        ShopUser user = new ShopUser(uName, shopName, shopAddress);
        return user;
    }











    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sale, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            startActivity(new Intent(this, LoginActivity.class));

//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /////******OnLocationListener implemented abstract methods...********
    /////******OnLocationListener implemented abstract methods...********
    /////******OnLocationListener implemented abstract methods...********
    @Override
    public void onLocationChanged(Location location) {

        LatLng userLocation = null;
        if(location != null){
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
