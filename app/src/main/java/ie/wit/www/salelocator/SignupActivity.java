package ie.wit.www.salelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    protected EditText usernameInput;
    protected EditText passwordInput;

    protected EditText passwordInputConf;
    protected EditText shopNameInput;
    protected EditText shopAddressInput;

    protected String shopName;
    protected String shopAddress;
    protected String passwordConfirm;


    protected Button signup_btn;
    protected String username;
    protected String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usernameInput = (EditText) findViewById(R.id.signup_username);
        shopNameInput = (EditText) findViewById(R.id.signup_shopname);
        shopAddressInput = (EditText) findViewById(R.id.signup_shopAddress);
        passwordInputConf = (EditText) findViewById(R.id.signup_passwordC);
        passwordInput = (EditText) findViewById(R.id.signup_password);
        signup_btn = (Button) findViewById(R.id.signupBtn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameInput.getText().toString();
                shopName = shopNameInput.getText().toString();
                shopAddress = shopAddressInput.getText().toString();
                password = passwordInput.getText().toString();
                passwordConfirm = passwordInputConf.getText().toString();



                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.put("shopName", shopName);
                user.put("shopAddress", shopAddress);
                user.setPassword(password);
                user.put("passwordConfirmation", passwordConfirm);

//                user.setShopname()

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please complete all the fields", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    /*    TextView linkToMap = (TextView) findViewById(R.id.selectLocation);
        linkToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });*/

    }

    public void selectLocation(View v){
        Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
        startActivity(intent);
    }

}
