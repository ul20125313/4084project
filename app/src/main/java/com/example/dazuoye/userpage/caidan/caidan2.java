package com.example.dazuoye.userpage.caidan;
////This class is used for menu home page interaction
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dazuoye.R;
import com.example.dazuoye.userpage.mainpage;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class caidan2 extends Fragment {

    private Caidan2ViewModel mViewModel;

    public static caidan2 newInstance() {
        return new caidan2();
    }
    //Set the private property class and store the data in the arraylist
    private Banner lun1;
    private RecyclerView r1;
    private Button zf;
    ArrayList<Integer> imgs;
    ArrayList<String>tes;
    Context context;
    static TextView ddje;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {//Reference function to add layout for menu
        View av=inflater.inflate(R.layout.caidan,container,false);
        init();

        return  av;
    }
    void addmap(Integer r,String ms,Integer je){//Add map
        HashMap<String,String> map=new HashMap<>();
        map.put("im",r.toString());
        map.put("ms",ms);
        map.put("je",je.toString());
        mainpage.info.caidan.add(map);
    }
    void init(){//Setting text properties for menus
        context=getActivity();
        imgs=new ArrayList<>();
        tes=new ArrayList<>();
        imgs.add(R.drawable.xg1);
        imgs.add(R.drawable.xg2);
        imgs.add(R.drawable.xg3);
        imgs.add(R.drawable.xg4);
        imgs.add(R.drawable.xg5);
        tes.add("Resturant1");
        tes.add("Resturant2");
        tes.add("Resturant3");
        tes.add("Resturant4");
        tes.add("Resturant5");
        mainpage.info.caidan=new ArrayList<>();
        addmap(R.drawable.c1,"Roast Meat",6);
        addmap(R.drawable.c2,"Beaf",10);
        addmap(R.drawable.c3,"Satay",3);
        addmap(R.drawable.c4,"Spaghetti",11);
        addmap(R.drawable.c5,"Shrimp",5);
        addmap(R.drawable.c6,"Cake",4);
        addmap(R.drawable.c7,"Bouilli",7);
        addmap(R.drawable.c8,"Roast Sausage",2);
        addmap(R.drawable.c9,"Cheese Prawn",6);
        addmap(R.drawable.c10,"Roast Chicken",11);
        addmap(R.drawable.c11,"Sushi",10);
        addmap(R.drawable.c12,"Orleans Drumsticks",7);
        addmap(R.drawable.c13,"Shredded Meat",7);
        addmap(R.drawable.c14,"Chicken Nugget",5);
        addmap(R.drawable.c15,"Tofu",4);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {//Add layout and show the effect of the broadcast
        super.onViewCreated(view, savedInstanceState);
        lun1 = (Banner) view.findViewById(R.id.lun1);
        r1 = (RecyclerView) view.findViewById(R.id.r1);
        zf = (Button) view.findViewById(R.id.zf);
        ddje = (TextView) view.findViewById(R.id.ddje);
        setbanner(lun1,imgs,tes);
        lis();
    }
    static void setValue(String value){
        ddje.setText(value);
    }
    void setbanner(com.youth.banner.Banner lun1, ArrayList<Integer> imgs, final ArrayList<String> tes){
        lun1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        lun1.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context.getApplicationContext()).load(path).into(imageView);
            }
        });
        lun1.setBannerTitles(tes);
        lun1.setDelayTime(2000);//time
        lun1.setBannerAnimation(Transformer.ZoomOutSlide);
        lun1.setImages(imgs).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context,tes.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        lun1.isAutoPlay(true);
        lun1.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE);
        lun1.start();
    }

    @Override
    public void onResume() {//Add layout and show the effect of the broadc
        super.onResume();
        RAdap adap=new RAdap(context,mainpage.info.caidan);
        r1.setLayoutManager(new LinearLayoutManager(context));
        r1.setAdapter(adap);
        setValue(mainpage.info.jsje());
    }

    void lis(){
        zf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(mainpage.info.jsje())!=0){
                    ArrayList<HashMap<String,String>>vs=new ArrayList<>();
                    for(int i=0;i<mainpage.info.caidan.size();i++){
                        if(mainpage.info.tmuen[i]!=0) {//If the options in the menu are not equal to 0
                            HashMap<String, String> map=mainpage.info.caidan.get(i);
                            int a=Integer.parseInt(map.get("je"));
                            map.put("sl",new Integer(mainpage.info.tmuen[i]/a).toString());//Make a menu selection
                            vs.add(map);
                        }
                    }
                    mainpage mainpage=(com.example.dazuoye.userpage.mainpage)context;
                    Intent intent=new Intent(context, ddxq.class);
                    intent.putExtra("maps",vs);
                    mainpage.startActivityForResult(intent,2);
                }
                else{//Otherwise, the display has no options
                    Toast.makeText(context,"No selection",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Caidan2ViewModel.class);
        // TODO: Use the ViewModel
    }

}
