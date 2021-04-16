package com.example.dazuoye.userpage.caidan;
////This class is used for order details page interaction
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazuoye.R;
import com.example.dazuoye.dialog;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.dingdan.chakandingdan;
import com.example.dazuoye.userpage.mainpage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ddxq extends AppCompatActivity {
    //Set property object
    private RecyclerView r1;
    private TextView zje;
    private Button qx;
    private Button tj;
    dialog dio;
    ArrayList<HashMap<String,String>> maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddxq);
        getSupportActionBar().hide();
        init();
        lis();
    }
    void init(){//Store the data in ArrayList
        Intent intent=getIntent();
        maps= (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("maps");
        r1 = (RecyclerView) findViewById(R.id.r1);
        zje = (TextView) findViewById(R.id.zje);
        qx = (Button) findViewById(R.id.qx);
        tj = (Button) findViewById(R.id.tj);

        ddxqAdap adap=new ddxqAdap(this,maps);
        r1.setLayoutManager(new LinearLayoutManager(this));
        r1.setAdapter(adap);
    }
    void lis(){//Set button text
        zje.setText(mainpage.info.jsje());

        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent();
                a.putExtra("finish",false);
                setResult(2,a);
                finish();
            }
        });

        tj.setOnClickListener(new View.OnClickListener() {//Set button monitoring
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(mainpage.info.zlmap.get("ye"))>=Integer.parseInt(mainpage.info.jsje())){
                    dio=new dialog(ddxq.this, "Pay or not", new dialog.oncl() {//Set text properties
                        @Override
                        public void onfou() {
                            dio.dismiss();
                        }
                        @Override
                        public void onshi() {
                            HashMap<String,String> x =new HashMap<>();
                            x.put("zje",mainpage.info.jsje());
                            x.put("total",mainpage.info.gettotalname());//Deposit the total amount of the order
                            Date NowDate = new Date();
                            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Show order time
                            String CurrentTime = formatter.format(NowDate);
                            x.put("date",CurrentTime);
                            x.put("zt","Merchants receive the order");//Successful order
                            x.put("dz",mainpage.info.zlmap.get("xm")+"\n"+mainpage.info.zlmap.get("dz")+"\n"+mainpage.info.zlmap.get("dh"));

                            Intent a=new Intent();
                            int s=Integer.parseInt(mainpage.info.zlmap.get("ye"))-Integer.parseInt(mainpage.info.jsje());
                            mainpage.info.zlmap.put("ye",new Integer(s).toString());
                            a.putExtra("finish",true);
                            setResult(2,a);
                            Toast.makeText(ddxq.this,"Payment success",Toast.LENGTH_SHORT).show();//Judge payment success
                            maps.add(x);
                            mainpage.info.dingdan.add(0,maps);
                            MainActivity.sjk.insertdingdans(maps);
                            MainActivity.sjk.userupdate(mainpage.info.zlmap.get("zh"),mainpage.info.zlmap);
                            finish();
                        }
                    });
                    dio.show();

                }
                else {
                    Toast.makeText(ddxq.this,"Insufficient balance, please top up",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

