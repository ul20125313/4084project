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
    private EditText t1;
    private EditText t2;
    private Button dl;
    private Button zc;
    public static shujukuinfo sjk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sjk = new shujukuinfo(this);
        init();
        lis();
    }

    void init() {

        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        dl = (Button) findViewById(R.id.dl);
        zc = (Button) findViewById(R.id.zc);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDb = database.getReference("user");
        userDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> value = (HashMap<String, String>) dataSnapshot.getValue();
                if (value != null && value.size() > 0) {
                    String password = value.get("password");
                    String account = value.get("zh");
                    t1.setText(account);
                    t2.setText(password);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("error", error.getMessage());
            }
        });
    }

    void lis() {
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> ztmap = sjk.userfind(t1.getText().toString());
                if (t2.getText().toString().equals(ztmap.get("password"))) {
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    mainpage.info.zlmap = ztmap;
                    mainpage.info.dingdan = sjk.dingdansfind(mainpage.info.zlmap.get("zh"));
                    Intent intent = new Intent(MainActivity.this, mainpage.class);
                    startActivity(intent);
                    t1.setText("");
                    t2.setText("");
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong password or account", Toast.LENGTH_SHORT).show();
                }
            }
        });
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, zcym.class);
                startActivity(a);
            }
        });
    }
}
