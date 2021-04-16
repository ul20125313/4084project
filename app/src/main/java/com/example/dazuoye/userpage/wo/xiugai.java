package com.example.dazuoye.userpage.wo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dazuoye.R;
import com.example.dazuoye.dialog;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.mainpage;

public class xiugai extends AppCompatActivity {
    private EditText xm;
    private EditText bj;
    private EditText dz;
    private EditText dh;
    private Button tj;
    dialog dio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);
        getSupportActionBar().hide();
        init();
        lis();
    }
    void init(){
        xm = (EditText) findViewById(R.id.xm);
        bj = (EditText) findViewById(R.id.bj);
        dz = (EditText) findViewById(R.id.dz);
        dh = (EditText) findViewById(R.id.dh);
        tj = (Button) findViewById(R.id.tj);
    }
    void lis(){
        xm.setText(mainpage.info.zlmap.get("xm"));//Get the name information from the home page to populate the text
        bj.setText(mainpage.info.zlmap.get("bj"));//Get the gender information from the home page to populate the text
        dz.setText(mainpage.info.zlmap.get("dz"));//Get the address information from the home page to populate the text
        dh.setText(mainpage.info.zlmap.get("dh"));//Get the phone information from the home page to populate the text
        tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dio=new dialog(xiugai.this, "Edit or not", new dialog.oncl() {
                    @Override
                            public void onfou() {
                                dio.dismiss();
                            }
                    @Override
                    public void onshi() {
                        mainpage.info.zlmap.put("xm",xm.getText().toString());
                        mainpage.info.zlmap.put("bj",bj.getText().toString());
                        mainpage.info.zlmap.put("dz",dz.getText().toString());
                        mainpage.info.zlmap.put("dh",dh.getText().toString());//Populate the home page information from the modified content
                        MainActivity.sjk.userupdate(mainpage.info.zlmap.get("zh"),mainpage.info.zlmap);
                        Intent a=new Intent();
                        setResult(4,a);
                        finish();
                    }
                });
                dio.show();

            }
        });
    }
}
