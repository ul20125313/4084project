package com.example.dazuoye.userpage.wo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dazuoye.R;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.mainpage;
import com.example.dazuoye.userpage.shouye.RwebAdap;

public class touxiang extends AppCompatActivity {
    private RecyclerView r1;
    public static ImageView tx;
    private Button qr;
    public static String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touxiang);//set the avatar display module
        getSupportActionBar().hide();

        init();
        lis();
    }
    void init(){
        Intent a=getIntent();
        r1 = (RecyclerView) findViewById(R.id.r1);
        tx = (ImageView) findViewById(R.id.tx);
        qr = (Button) findViewById(R.id.qr);
        tx.setImageResource(a.getIntExtra("im",0));
        touxiangad adap=new touxiangad(touxiang.this,mainpage.info.tongxiangs);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(touxiang.this,5,GridLayoutManager.VERTICAL,false){
            public boolean canScrollVertically() {
                return false;
            }
        };

        r1.setLayoutManager(gridLayoutManager);
        r1.setAdapter(adap);
    }
    void lis(){
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage.info.zlmap.put("im",img);
                MainActivity.sjk.userupdate(mainpage.info.zlmap.get("zh"),mainpage.info.zlmap);
                finish();
            }
        });
    }
}
