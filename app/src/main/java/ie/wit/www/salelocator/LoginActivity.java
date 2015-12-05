package ie.wit.www.salelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    protected EditText usernameInput;
    protected EditText passwordInput;
    protected Button login_btn;
    protected String username;
    protected String password;

    private SaleActivity sale = new SaleActivity();

    private EditText usrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // going back home screen button on the top left corner
//        getActionBar().setDisplayHomeAsUpEnabled(true);


        TextView linktosignup = (TextView) findViewById(R.id.linkToSignup);
        linktosignup.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }

        });

        usernameInput = (EditText) findViewById(R.id.login_username);
        passwordInput = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.loginBtn);

        login_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();


//                usrName = (EditText) findViewById(R.id.cName);
//
//                final ParseUser currUser = sale.currentParseUser;
//                String usName = usrName.getText().toString();


                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if (parseUser != null) {

//                            currUser.put("name", usrName.getText().toString());
                            String un = parseUser.getUsername();
                            String sn = parseUser.getString("shopName");
                            String sa = parseUser.getString("shopAddress");

                            Log.v("name", "" + un);
                            Log.v("shop name", "" + sn);
                            Log.v("shop name", "" + sa);

//                            double lat =  parseUser.getParseGeoPoint("latitude").getLatitude();
//                            double lng = parseUser.getParseGeoPoint("latitude").getLongitude();

//                            Log.v("Lat ", ""+lat);
//                            Log.v("Lng ", ""+lng);

                            SaleLocatorApp.currentShopUser = new ShopUser(un, sn, sa);
//
//                            SaleLocatorApp.currentShopUser.setLatLng(lat, lng);
//
//                            Log.v("name", "" + SaleLocatorApp.currentShopUser.shopUserName);
//                            Log.v("shop name", "" + SaleLocatorApp.currentShopUser.shopName);
//                            Log.v("shop name", "" + SaleLocatorApp.currentShopUser.shopAddress);

                            Intent intent = new Intent(v.getContext(), ShopControl.class);
                            startActivity(intent);
                            finish();
                            //Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
















    }//end of OnCreate

   /* public void loginBtn(View view){
        Intent intent = new Intent(LoginActivity.this, ShopControl.class);
        startActivity(intent);
    }*/


}// end of the class

