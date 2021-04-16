package com.example.dazuoye.userpage.dingdan;
////View order page interaction
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dazuoye.R;
import com.example.dazuoye.userpage.mainpage;

public class dingdan2 extends Fragment {

    private Dingdan2ViewModel mViewModel;

    public static dingdan2 newInstance() {
        return new dingdan2();
    }

    private RecyclerView r1;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View av=inflater.inflate(R.layout.dingdan,container,false);
        context =getActivity();
        return  av;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        r1 = (RecyclerView)view.findViewById(R.id.r1);
    }
    @Override
    public void onResume() {//Adapter selection
        super.onResume();
        zdingdanAdap adap=new zdingdanAdap(context, mainpage.info.dingdan);
        r1.setLayoutManager(new LinearLayoutManager(context));
        r1.setAdapter(adap);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Dingdan2ViewModel.class);
        // TODO: Use the ViewModel
    }

}

