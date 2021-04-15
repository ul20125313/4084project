package com.example.dazuoye.logpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dazuoye.R;
import com.example.dazuoye.shujuku.shujukuinfo;
import com.example.dazuoye.userpage.mainpage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText t1; // create a EditText view variable for t1
    private EditText t2; // create a EditText view variable for t2
    private Button dl;  // create a Button view variable for d1
    private Button zc; // create a  Button view variable for zc
    public static shujukuinfo sjk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // connect with the activity_main layout view
        getSupportActionBar().hide(); // hide the action bar
        sjk = new shujukuinfo(this); // create an instance of the shujukuinfo class
        init(); //start to initialize
        lis();
    }

    void init() {

        t1 = (EditText) findViewById(R.id.t1); // connect with the edittext t1
        t2 = (EditText) findViewById(R.id.t2);// connect with the edittext t2
        dl = (Button) findViewById(R.id.dl);// connect with the button t1
        zc = (Button) findViewById(R.id.zc);// connect with the button t2
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // create a instance of firebase
        DatabaseReference userDb = database.getReference("user");
        userDb.addValueEventListener(new ValueEventListener() { // create the listener for the userDb
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {// if a user register an account, it will trigger this listener
                HashMap<String, String> value = (HashMap<String, String>) dataSnapshot.getValue();// use the hashmap to store the data in firebase
                if (value != null && value.size() > 0) {
                    String password = value.get("password");// by using the key  to obtain the value
                    String account = value.get("zh");
                    t1.setText(account);//insert the value from firebase into the text box
                    t2.setText(password);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("error", error.getMessage());
            }// if it can't connect with firebase
        });
    }

    void lis() {
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// set a click listener for the button dl
                HashMap<String, String> ztmap = sjk.userfind(t1.getText().toString());// call the database function userfind
                if (t2.getText().toString().equals(ztmap.get("password"))) {// if the password is correct
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    mainpage.info.zlmap = ztmap;
                    mainpage.info.dingdan = sjk.dingdansfind(mainpage.info.zlmap.get("zh"));
                    Intent intent = new Intent(MainActivity.this, mainpage.class);// create a new activity
                    startActivity(intent);//access to the main page
                    t1.setText("");//initialize the text box
                    t2.setText("");
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong password or account", Toast.LENGTH_SHORT).show();
                }
            }
        });
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// the registration button click listener
                Intent a = new Intent(MainActivity.this, zcym.class);
                startActivity(a);// access to the registration interface
            }
        });
    }
}
