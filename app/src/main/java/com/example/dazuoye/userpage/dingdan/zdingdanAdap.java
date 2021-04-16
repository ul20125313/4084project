package com.example.dazuoye.userpage.dingdan;

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
import com.example.dazuoye.userpage.mainpage;

import java.util.ArrayList;
import java.util.HashMap;

public class zdingdanAdap extends RecyclerView.Adapter<zdingdanAdap.MyH> {
    ArrayList<ArrayList<HashMap<String, String>>> maps;
    Context context;

    /**
     * Construction Method
     * Transfer Data Collection
     * @param context
     * @param maps
     */
    public zdingdanAdap(Context context, ArrayList<ArrayList<HashMap<String, String>>> maps) {
        this.maps = maps;
        this.context = context;
    }

    /**
     * Bind the corresponding control layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public zdingdanAdap.MyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View av = LayoutInflater.from(context).inflate(R.layout.dingdanddview, parent, false);
        MyH my = new MyH(av);
        return my;
    }

    /**
     * Assign data to the interface
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MyH holder, final int position) {
        HashMap<String, String> info = maps.get(position).get(maps.get(position).size() - 1);
        /**
         * Here's copying the data to the interface to get values for fields like im zje date in the map
         */
        holder.p1.setImageResource(Integer.parseInt(maps.get(position).get(0).get("im")));
        holder.dingdanjine.setText("$ " + info.get("zje"));
        holder.dingdanxinxi.setText(info.get("total"));
        holder.date.setText(info.get("date"));
        holder.zt.setText(info.get("zt"));
        holder.xq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Jump to another interface and pass values
                 */
                Intent a = new Intent(context, chakandingdan.class);
                a.putExtra("maps", maps.get(position));
                a.putExtra("pos", position);
                mainpage s = (mainpage) context;
                s.startActivityForResult(a, 3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return maps.size();
    }

    /**
     * Custom viwholder
     */
    class MyH extends RecyclerView.ViewHolder {
        private ImageView p1;
        private TextView dingdanxinxi;
        private TextView dingdanjine;
        private TextView date;
        private TextView zt;
        private Button xq;


        public MyH(@NonNull View itemView) {
            super(itemView);
            /**
             * Bind Control
             */
            p1 = (ImageView) itemView.findViewById(R.id.p1);
            dingdanxinxi = (TextView) itemView.findViewById(R.id.dingdanxinxi);
            dingdanjine = (TextView) itemView.findViewById(R.id.dingdanjine);
            date = (TextView) itemView.findViewById(R.id.date);
            zt = (TextView) itemView.findViewById(R.id.zt);
            xq = (Button) itemView.findViewById(R.id.xq);
        }
    }
}
