package com.example.dazuoye.userpage.wo;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazuoye.R;
import com.example.dazuoye.dialog;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.dingdan.chakandingdan;
import com.example.dazuoye.userpage.mainpage;

import java.util.HashMap;

public class wo2 extends Fragment {

    private Wo2ViewModel mViewModel;

    public static wo2 newInstance() {
        return new wo2();
    }

    private TextView zh;
    private TextView xm;
    private TextView bj;
    private TextView dz;
    private TextView ye;
    private TextView dh;
    private Button xgzl;
    private Button tcdl;
    private Button czfw;
    private ImageView tp;
    dialog dio;
    Context context;
    mainpage ma;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View av=inflater.inflate(R.layout.wo,container,false);
        context=getActivity();
        ma= (mainpage) context;
        return  av;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        zh = (TextView) view.findViewById(R.id.zh);
        xm = (TextView) view.findViewById(R.id.xm);
        bj = (TextView) view.findViewById(R.id.bj);
        dz = (TextView) view.findViewById(R.id.dz);
        ye = (TextView) view.findViewById(R.id.ye);
        dh = (TextView) view.findViewById(R.id.dh);
        xgzl = (Button) view.findViewById(R.id.xgzl);
        czfw = (Button) view.findViewById(R.id.czfw);
        tcdl = (Button) view.findViewById(R.id.tcdl);
        tp = (ImageView) view.findViewById(R.id.tp);
        tp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent a=new Intent(context,touxiang.class);
                a.putExtra("im",Integer.parseInt(mainpage.info.zlmap.get("im")));
                startActivity(a);
                return false;
            }
        });
        xgzl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, xiugai.class);
                mainpage mainpage=(mainpage)context;
                mainpage.startActivityForResult(intent,4);
            }
        });
        tcdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dio=new dialog(context, "Are you sure to logout", new dialog.oncl() {
                    @Override
                    public void onfou() {
                        dio.dismiss();
                    }
                    @Override
                    public void onshi() {
                        Intent a=new Intent(context,MainActivity.class);
                        mainpage s= (mainpage) context;
                        s.startActivity(a);
                        s.finish();
                    }
                });
                dio.show();

            }
        });
        czfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You have charged 10 dollar. ",Toast.LENGTH_SHORT).show();
                Integer y=Integer.parseInt(mainpage.info.zlmap.get("ye"));
                y=y+10;
                mainpage.info.zlmap.put("ye",y.toString());
                ye.setText(mainpage.info.zlmap.get("ye"));
                MainActivity.sjk.userupdate(mainpage.info.zlmap.get("zh"),mainpage.info.zlmap);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        zh.setText(mainpage.info.zlmap.get("zh"));
        xm.setText(mainpage.info.zlmap.get("xm"));
        bj.setText(mainpage.info.zlmap.get("bj"));
        dz.setText(mainpage.info.zlmap.get("dz"));
        ye.setText(mainpage.info.zlmap.get("ye"));
        dh.setText(mainpage.info.zlmap.get("dh"));
        tp.setImageResource(Integer.parseInt(mainpage.info.zlmap.get("im")));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Wo2ViewModel.class);
        // TODO: Use the ViewModel
    }

}
