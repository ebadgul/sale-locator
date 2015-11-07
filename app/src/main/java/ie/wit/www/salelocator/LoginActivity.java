package ie.wit.www.salelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });





        TextView linktosignup = (TextView) findViewById(R.id.linkToSignup);
        linktosignup.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }

        });

    usernameInput = (EditText) findViewById(R.id.login_username);
    passwordInput = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.loginBtn);

        login_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if(parseUser != null){
                            Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }//end of OnCreate


}// end of the class

