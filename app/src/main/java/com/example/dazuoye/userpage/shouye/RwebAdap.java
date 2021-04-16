package com.example.dazuoye.userpage.shouye;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dazuoye.R;
import com.example.dazuoye.userpage.caidan.caidan2;
import com.example.dazuoye.userpage.mainpage;

import java.util.ArrayList;
import java.util.HashMap;

public class RwebAdap extends RecyclerView.Adapter<RwebAdap.MyH> {
    ArrayList<HashMap<String,String>>maps;
    Context context;

    /**
     * Construction Method Pass Value
     * @param context
     * @param maps
     */
    public RwebAdap(Context context, ArrayList<HashMap<String,String>>maps){
        this.maps=maps;
        this.context=context;
    }

    /**
     * Bind Load Item Layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RwebAdap.MyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View av= LayoutInflater.from(context).inflate(R.layout.webad,parent,false);
        MyH my=new MyH(av);
        return my;
    }

    /**
     * Assign each item
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MyH holder, final int position) {
        holder.imageView.setImageResource(Integer.parseInt(maps.get(position).get("im")));
        holder.t1.setText(maps.get(position).get("mz"));
        holder.sl.setText(maps.get(position).get("sl"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent a=new Intent(context,Web1.class);
                mainpage tmainpage= (mainpage) context;
                //a.putExtra("url",maps.get(position).get("url"));
                //tmainpage.startActivity(a);
            }
        });
    }

    /**
     * Length of returned data
     * @return
     */
    @Override
    public int getItemCount() {
        return maps.size();
    }

    /**
     * Bind Control
     */
    class MyH extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView t1;
        private TextView sl;
        public MyH(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            t1 = (TextView) itemView.findViewById(R.id.t1);
            sl = (TextView) itemView.findViewById(R.id.sl);
        }
    }
}
