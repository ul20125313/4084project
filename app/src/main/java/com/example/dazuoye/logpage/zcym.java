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
    private TextView zh;
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
        setContentView(R.layout.activity_zcym);
        getSupportActionBar().hide();
        init();
        lis();
    }
    void init(){
        zh = (TextView) findViewById(R.id.zh);
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

                tpassword =password.getText().toString();
                txm=xm.getText().toString();
                tdh =dh.getText().toString();
                tbj=bj.getText().toString();
                tdz =dz.getText().toString();
                if(tpassword.length()<6){
                    Toast.makeText(zcym.this,"The password must be no less than 6 numbers",Toast.LENGTH_SHORT).show();
                }
                else if(txm.length()==0||tbj.length()==0||tdh.length()==0||tbj.length()==0){
                    Toast.makeText(zcym.this,"Some information not filled in",Toast.LENGTH_SHORT).show();
                }
                else {
                    td=new dialog(zcym.this, "Confirmation of registration", new dialog.oncl() {
                        @Override
                        public void onfou() {
                            td.dismiss();
                        }
                        @Override
                        public void onshi() {
                            HashMap<String,String>zl=new HashMap<>();
                            zl.put("dingdanid","0");
                            zl.put("xm",txm);
                            zl.put("bj",tbj);
                            zl.put("password",tpassword);
                            zl.put("dz",tdz);
                            zl.put("ye","0");
                            zl.put("im",new Integer(R.drawable.tx).toString());
                            zl.put("zh",zh.getText().toString());
                            zl.put("dh",tdh);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference userDb = database.getReference("user");
                            userDb.setValue(zl);
                            MainActivity.sjk.userinsert(zl);
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
        });

    }
}
