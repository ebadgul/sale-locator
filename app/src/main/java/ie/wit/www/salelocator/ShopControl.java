package ie.wit.www.salelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ShopControl extends AppCompatActivity {

    //    protected EditText cShopName;
    private SaleActivity sApp = new SaleActivity();

    private EditText _name;
    private EditText _shopName;
    private EditText _shopAddress;
//    private Switch _swt;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        _name = (EditText) findViewById(R.id.cName);
        _shopName = (EditText) findViewById(R.id.cShopName);
        _shopAddress = (EditText) findViewById(R.id.cShopAddress);
//        _swt = (Switch) findViewById(R.id.onOrOff);

                _name.setText(SaleLocatorApp.currentShopUser.shopUserName);
        _shopName.setText(SaleLocatorApp.currentShopUser.shopName);
        _shopAddress.setText(SaleLocatorApp.currentShopUser.shopAddress);


        /*ParseGeoPoint pgp = ParseUser.getCurrentUser().getParseGeoPoint("latitude");

        Log.v("Geo Point: ", ""+pgp);*/



//        ***********************



        Button saveChanges = (Button) findViewById(R.id.updateDetails);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser pu = sApp.currentParseUser;
                String newName = _name.getText().toString();

//                if (_swt.isChecked()){
//                    pu.put("saleOn", true);
//                }else {

                    pu.put("username", newName);
                    pu.put("username", _name.getText().toString());
                    Log.v("new", "" + newName);

                    Log.v("name", "" + _name.getText().toString());

                    pu.saveInBackground();
                }
//            }
        });



    }// end of onCreate


    public void setLocation(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        ParseUser.logOut();

        switch (item.getItemId()) {
            case R.id.action_sign_out:
                startActivity(new Intent(ShopControl.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }

}
