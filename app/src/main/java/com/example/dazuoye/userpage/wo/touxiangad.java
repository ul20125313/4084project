package com.example.dazuoye.userpage.wo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dazuoye.R;
import com.example.dazuoye.logpage.MainActivity;
import com.example.dazuoye.userpage.mainpage;

import java.util.ArrayList;
import java.util.HashMap;

public class touxiangad extends RecyclerView.Adapter<touxiangad.MyH> {
    ArrayList<HashMap<String,String>>maps;
    Context context;

    /**
     * Construction Method Pass Value
     * @param context
     * @param maps
     */
    public touxiangad(Context context, ArrayList<HashMap<String,String>>maps){
        this.maps=maps;
        this.context=context;
    }

    /**
     * Load XML layout and bind
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public touxiangad.MyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View av= LayoutInflater.from(context).inflate(R.layout.tx1,parent,false);
        MyH my=new MyH(av);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyH holder, final int position) {
        holder.img=Integer.parseInt(maps.get(position).get("im"));
        holder.im.setImageResource(holder.img);
        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Modify the avatar data source and set it up
                 */
                touxiang.img=maps.get(position).get("im");
                touxiang.tx.setImageResource(holder.img);

            }
        });
    }

    /**
     * Return data length
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
        private ImageView im;
        private int img;
        public MyH(@NonNull View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.im);
        }
    }
}
