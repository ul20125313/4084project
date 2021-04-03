package com.example.dazuoye.userpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.example.dazuoye.R;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.wo.wo2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class mainpage extends AppCompatActivity {
    private BottomNavigationView line1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        getSupportActionBar().hide();
        info.tmuen=new int[2000];
        init();
        lis();
        info.clear();
    }


    void init(){
        line1 = (BottomNavigationView) findViewById(R.id.line1);
        NavController navController= Navigation.findNavController(this,R.id.fragment);
        AppBarConfiguration configuration =new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(line1,navController);
    }
    void lis(){
        info.webs=new ArrayList<>();
        info.tongxiangs=new ArrayList<>();


        addtx(R.drawable.tx);
        addtx(R.drawable.tx2);
        addtx(R.drawable.tx3);
        addtx(R.drawable.tx4);
        addtx(R.drawable.tx5);
        addtx(R.drawable.tx6);
        addtx(R.drawable.tx7);
        addtx(R.drawable.tx8);
        addtx(R.drawable.tx9);
        addtx(R.drawable.tx10);
        addtx(R.drawable.tx11);
        addtx(R.drawable.tx12);
        addtx(R.drawable.tx13);
        addtx(R.drawable.tx14);
        addtx(R.drawable.tx15);
    }
    void addweb(Integer im,String mz,Integer sl,String url){
        HashMap<String,String>vw=new HashMap<>();
        vw.put("im",im.toString());
        vw.put("mz",mz);
        vw.put("sl",sl.toString());
        vw.put("url",url);
        info.webs.add(vw);
    }

    void addtx(Integer im){
        HashMap<String,String>vw=new HashMap<>();
        vw.put("im",im.toString());
        info.tongxiangs.add(vw);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==2){
            if(data.getBooleanExtra("finish",true)==true){
                info.clear();
            }
        }
        if(requestCode==1&&resultCode==1){
            if(data.getBooleanExtra("finish",true)==true){

            }
        }
        if(requestCode==3&&resultCode==3){
            if(data.getBooleanExtra("finish",true)==true){
                HashMap<String,String> dding=info.dingdan.get(data.getIntExtra("pos",0)).get(info.dingdan.get(data.getIntExtra("pos",0)).size()-1);
                MainActivity.sjk.dingdandelete(info.zlmap.get("zh"),dding.get("id"));
                info.dingdan.remove(data.getIntExtra("pos",0));
            }
        }
        if(requestCode==4&&resultCode==4){

        }
    }
    public static class info{
        public static int [] tmuen;
        public static HashMap<String,String> zlmap;
        public static ArrayList<HashMap<String,String>> caidan;
        public static ArrayList<ArrayList<HashMap<String,String>>>dingdan;
        public static ArrayList<HashMap<String,String>> webs ;
        public static ArrayList<HashMap<String,String>> tongxiangs;
        public  static String gettotalname(){
            String total="";
            int f=0;
            for(int i=0;i<mainpage.info.caidan.size();i++){
                if(mainpage.info.tmuen[i]!=0) {
                    if(f==0){
                        total=total+caidan.get(i).get("ms");
                        f=1;
                    }
                    else{
                        total=total+","+caidan.get(i).get("ms");
                    }
                }
            }
            return total;
        }
        public static void clear(){
            info.tmuen=new int[2000];
            for(int i=0;i<2000;i++){
                info.tmuen[i]=0;
            }
        }
        public static String jsje(){
            int s = 0;
            for(int i=0;i<2000;i++){
                s=s+info.tmuen[i];
            }
            Integer sum= new Integer(s);
            return sum.toString();
        }
    }
}
