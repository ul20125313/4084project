package com.example.dazuoye.logpage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dazuoye.R;
import com.example.dazuoye.dialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class zcym extends AppCompatActivity {
    private TextView zh;// create the view variable
    private EditText password;
    private EditText xm;
    private EditText dh;
    private EditText bj;
    private EditText dz;
    private Button tc;
    private Button zc;
    String tpassword = "";
    String txm="";
    String tdh="";
    String tbj="";
    String tdz="";
    dialog td;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zcym);// connect with activity_zcym.xml
        getSupportActionBar().hide();
        init();// start to initialize
        lis();
    }
    void init(){
        zh = (TextView) findViewById(R.id.zh);// connect with the elements created in the activity_zcym.xml
        password = (EditText) findViewById(R.id.password);
        xm = (EditText) findViewById(R.id.xm);
        dh = (EditText) findViewById(R.id.dh);
        bj = (EditText) findViewById(R.id.bj);
        dz = (EditText) findViewById(R.id.dz);
        tc = (Button) findViewById(R.id.tc);
        zc = (Button) findViewById(R.id.zc);
        zh.setText(MainActivity.sjk.sqzh());
    }
    void lis(){

        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tpassword =password.getText().toString();// extract the data from the text box and change the values into string type
                txm=xm.getText().toString();
                tdh =dh.getText().toString();
                tbj=bj.getText().toString();
                tdz =dz.getText().toString();
                if(tpassword.length()<6){// check whether the lenght of password is less than six
                    Toast.makeText(zcym.this,"The password must be no less than 6 numbers",Toast.LENGTH_SHORT).show();// at toast tip
                }
                else if(txm.length()==0||tbj.length()==0||tdh.length()==0||tbj.length()==0){// check whether all of the details are filled out
                    Toast.makeText(zcym.this,"Some information not filled in",Toast.LENGTH_SHORT).show();
                }
                else {// if all of the person details are filled out successfully
                    td=new dialog(zcym.this, "Confirmation of registration", new dialog.oncl() {// a confirm dialog

                        @Override
                        public void onfou() {// if the user tap on No
                            td.dismiss();
                        }
                        @Override
                        public void onshi() {// if the user tap on Yes
                            HashMap<String,String>zl=new HashMap<>();
                            zl.put("dingdanid","0");//package all of the users's details
                            zl.put("xm",txm);
                            zl.put("bj",tbj);
                            zl.put("password",tpassword);
                            zl.put("dz",tdz);
                            zl.put("ye","0");
                            zl.put("im",new Integer(R.drawable.tx).toString());
                            zl.put("zh",zh.getText().toString());
                            zl.put("dh",tdh);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();// create an instance of firebase
                            DatabaseReference userDb = database.getReference("user");
                            userDb.setValue(zl);// upload the data on the firebase
                            MainActivity.sjk.userinsert(zl);// upload the data on the local database
                            MainActivity.sjk.addzh();
                            finish();
                        }
                    });
                    td.show();
                }
            }
        });
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//terminate

    }
}
