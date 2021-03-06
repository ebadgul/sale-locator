package ie.wit.www.salelocator;

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


                                              user.setUsername(username);
                                              user.put("shopName", shopName);
                                              user.put("shopAddress", shopAddress);
                                              user.setPassword(password);
                                              user.put("passwordConfirmation", passwordConfirm);


                                              user.signUpInBackground(new SignUpCallback() {
                                                                          @Override
                                                                          public void done(ParseException e) {
                                                                              if (e == null) {
                                                                                  Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                                                              } else if (!password.equals(passwordConfirm) || password.length() <= 5) {
                                                                                  Toast.makeText(getApplicationContext(), "Password doesn't match and shouldn't be less than 5 characters", Toast.LENGTH_LONG).show();

                                                                              } else if (username.equals("") || shopName.equals("") || shopAddress.equals("") || password.equals("") || passwordConfirm.equals("")) {
                                                                                  Toast.makeText(getApplicationContext(), "sorry man a field is empty",
                                                                                          Toast.LENGTH_LONG).show();
                                                                              } else {

                                                                                  // Sign up didn't succeed. Look at the ParseException
                                                                                  // to figure out what went wrong
                                                                                  switch (e.getCode()) {
                                                                                      case ParseException.USERNAME_TAKEN:
                                                                                          Toast.makeText(getApplicationContext(), "Sorry, the username is taken.", Toast.LENGTH_LONG).show();
                                                                                          break;
                                                                                      case ParseException.USERNAME_MISSING:
                                                                                          Toast.makeText(getApplicationContext(), "Sorry, you must supply a username to register.", Toast.LENGTH_LONG).show();
                                                                                          break;
                                                                                      case ParseException.PASSWORD_MISSING:
                                                                                          Toast.makeText(getApplicationContext(), "Sorry, you must supply a password to register.", Toast.LENGTH_LONG).show();
                                                                                          break;
                                                                                      default:
                                                                                          Toast.makeText(getApplicationContext(), "" + e.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                                                                                  }

                                                                              }
                                                                          }

                                                                      }

                                              );


                                          }

                                      }

        );


    }

    /*public void signupBtn(View v){
        Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
        startActivity(intent);
    }*/

}
