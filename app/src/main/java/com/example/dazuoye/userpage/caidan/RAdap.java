package com.example.dazuoye.userpage.caidan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dazuoye.R;
import com.example.dazuoye.userpage.mainpage;

import java.util.ArrayList;
import java.util.HashMap;

public class RAdap extends RecyclerView.Adapter<RAdap.MyH> {
    ArrayList<HashMap<String,String>>maps;
    Context context;
    public RAdap(Context context, ArrayList<HashMap<String,String>>maps){
        this.maps=maps;
        this.context=context;
    }

    @NonNull
    @Override
    public RAdap.MyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View av= LayoutInflater.from(context).inflate(R.layout.caiview,parent,false);
        MyH my=new MyH(av);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyH holder, final int position) {
        holder.im.setImageResource(Integer.parseInt(maps.get(position).get("im")));
        holder.ms.setText(maps.get(position).get("ms"));
        holder.je.setText(maps.get(position).get("je"));
        String p=maps.get(position).get("je");
        int s=Integer.parseInt(p);
        Integer a= new Integer(mainpage.info.tmuen[position]/s);
        holder.sl.setText(a.toString());
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num= holder.sl.getText().toString();
                Integer sum=Integer.parseInt(num)+1;
                mainpage.info.tmuen[position]+=Integer.parseInt(maps.get(position).get("je"));
                caidan2.setValue(mainpage.info.jsje());
                holder.sl.setText(sum.toString());
            }
        });
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num= holder.sl.getText().toString();
                if(Integer.parseInt(num)!=0){
                    Integer sum=Integer.parseInt(num)-1;
                    mainpage.info.tmuen[position]-=Integer.parseInt(maps.get(position).get("je"));
                    caidan2.setValue(mainpage.info.jsje());
                    holder.sl.setText(sum.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return maps.size();
    }
    class MyH extends RecyclerView.ViewHolder{
        ImageView im;
        TextView ms;
        Button jia;
        TextView sl;
        Button jian;
        TextView je;
        public MyH(@NonNull View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.im);
            ms = (TextView) itemView.findViewById(R.id.ms);
            jia = (Button) itemView.findViewById(R.id.jia);
            sl = (TextView) itemView.findViewById(R.id.sl);
            jian = (Button) itemView.findViewById(R.id.jian);
            je = (TextView) itemView.findViewById(R.id.je);
        }
    }
}
