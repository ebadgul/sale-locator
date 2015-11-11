package ie.wit.www.salelocator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ShopControl extends AppCompatActivity {

    protected EditText cShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final EditText name = (EditText) findViewById(R.id.cName);

//        nameFromP.setText();



        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                     String rName = object.getString("username");
                        name.setText("rName");
                    int score = object.getInt("score");
                    String playerName = object.getString("playerName");
                    boolean cheatMode = object.getBoolean("cheatMode");
                } else {
                    // something went wrong
                    Toast.makeText(getApplicationContext(), "something is wrong", Toast.LENGTH_LONG).show();
                }
            }
        });



//        cShopName


/*

// Locate the class table named "Country" in Parse.com
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                "Country");


        // Specify which class to query
        ParseQuery<ShopControl> query = ParseQuery.getQuery(ShopControl.class);
// Specify the object id
        query.getInBackground("aFuEsvjoHt", new GetCallback<ShopControl>() {
            public void done(ShopControl item, ParseException e) {
                if (e == null) {
                    // Access data using the `get` methods for the object
                    String body = item.getBody();
                    // Access special values that are built-in to each object
                    String objectId = item.getObjectId();
                    Date updatedAt = item.getUpdatedAt();
                    Date createdAt = item.getCreatedAt();
                    // Do whatever you want with the data...
                    Toast.makeText(TodoItemsActivity.this, body, Toast.LENGTH_SHORT).show();
                } else {
                    // something went wrong
                }
            }
        });
*/



    }

}
