package com.example.dazuoye;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class dialog extends AlertDialog {
    Context context;
    private TextView ms;
    private Button fou;//Create a button class called "No"
    private Button shi;//Create a button class called "Yes"
    oncl on;
    String mess;
    public dialog(Context context, String mess, oncl on) {
        super(context);
        this.context=context;
        this.mess=mess;
        this.on=on;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duihuakuang);
        init();
    }
    void init(){
        ms = (TextView) findViewById(R.id.ms);
        fou = (Button) findViewById(R.id.fou);
        shi = (Button) findViewById(R.id.shi);

        ms.setText(mess);
        fou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.onfou();
            }
        });
        shi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.onshi();
            }
        });
    }
    public  interface oncl{
        public void onfou();
        public void onshi();
    }
}
