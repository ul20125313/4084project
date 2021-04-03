package com.example.dazuoye.userpage.caidan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dazuoye.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ddxqAdap extends RecyclerView.Adapter<ddxqAdap.MyH> {
    ArrayList<HashMap<String,String>>maps;
    Context context;
    public ddxqAdap(Context context, ArrayList<HashMap<String,String>>maps){
        this.maps=maps;
        this.context=context;
    }

    @NonNull
    @Override
    public ddxqAdap.MyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View av= LayoutInflater.from(context).inflate(R.layout.ddxqview,parent,false);
        MyH my=new MyH(av);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyH holder, final int position) {
        holder.im.setImageResource(Integer.parseInt(maps.get(position).get("im")));
        holder.ms.setText(maps.get(position).get("ms"));
        holder.je.setText(maps.get(position).get("je"));
        holder.sl.setText(maps.get(position).get("sl"));
        int p= Integer.parseInt(maps.get(position).get("je"))*Integer.parseInt(maps.get(position).get("sl"));
        holder.hj.setText(new Integer(p).toString());
    }

    @Override
    public int getItemCount() {
        return maps.size();
    }
    class MyH extends RecyclerView.ViewHolder{
        private ImageView im;
        private TextView ms;
        private TextView je;
        private TextView hj;
        private TextView sl;

        public MyH(@NonNull View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.im);
            ms = (TextView) itemView.findViewById(R.id.ms);
            je = (TextView) itemView.findViewById(R.id.je);
            hj = (TextView) itemView.findViewById(R.id.hj);
            sl = (TextView) itemView.findViewById(R.id.sl);
        }
    }
}
