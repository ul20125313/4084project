package com.example.dazuoye.userpage.dingdan;
//View order page interaction
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dazuoye.R;
import com.example.dazuoye.dialog;
import com.example.dazuoye.userpage.caidan.ddxqAdap;
import com.example.dazuoye.userpage.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class chakandingdan extends AppCompatActivity {//Inherit appcompatactivity class
    //Create private property class
    private RecyclerView r1;
    private TextView dz;
    private TextView date;
    private TextView zt;
    private Button fh;
    private Button sc;
    dialog dio;
    Integer pos;
    ArrayList<HashMap<String,String>>maps;//Save the map data in the ArrayList
    HashMap<String,String>info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chakandingdan);
        getSupportActionBar().hide();
        init();
        lis();
    }
    void init(){//Create a new object and insert data into it at the same time
        r1 = (RecyclerView) findViewById(R.id.r1);
        dz = (TextView) findViewById(R.id.dz);
        date = (TextView) findViewById(R.id.date);
        zt = (TextView) findViewById(R.id.zt);
        fh = (Button) findViewById(R.id.fh);
        sc = (Button) findViewById(R.id.sc);
        Intent d=getIntent();
        maps= (ArrayList<HashMap<String, String>>) d.getSerializableExtra("maps");
        info=maps.get(maps.size()-1);
        maps.remove(maps.size()-1);
        pos=d.getIntExtra("pos",0);
        ddxqAdap adap=new ddxqAdap(this,maps);
        r1.setLayoutManager(new LinearLayoutManager(this));
        r1.setAdapter(adap);
        dz.setText(info.get("dz"));
        zt.setText(info.get("zt"));
        date.setText(info.get("date"));

    }
    void lis(){//Mouse click listener
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent();
                a.putExtra("finish",false);//Set text properties
                setResult(3,a);
                finish();
            }
        });
        sc.setOnClickListener(new View.OnClickListener() {//Mouse click listener
            @Override
            public void onClick(View v) {
                dio=new dialog(chakandingdan.this, "Delete or not", new dialog.oncl() {//Set text properties
                    @Override
                    public void onfou() {
                        dio.dismiss();
                    }

                    @Override
                    public void onshi() {
                        Intent a=new Intent();
                        setResult(3,a);
                        a.putExtra("finish",true);//Set text properties
                        a.putExtra("pos",pos);
                        finish();
                    }
                });
                dio.show();

            }
        });
    }
}

