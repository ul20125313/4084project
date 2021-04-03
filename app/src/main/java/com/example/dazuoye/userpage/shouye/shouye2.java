package com.example.dazuoye.userpage.shouye;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dazuoye.R;
import com.example.dazuoye.userpage.mainpage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.MapView;
import com.google.android.libraries.maps.MapsInitializer;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class shouye2 extends Fragment {

    private Shouye2ViewModel mViewModel;
    private RecyclerView r1;

    public static shouye2 newInstance() {
        return new shouye2();
    }

    private com.youth.banner.Banner lun1;
    private TextView t1;
    Context context;
    ArrayList<Integer> imgs;
    ArrayList<String> tes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View av = inflater.inflate(R.layout.shouye, container, false);
        init();
        return av;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lun1 = (com.youth.banner.Banner) view.findViewById(R.id.lun1);
        t1 = view.findViewById(R.id.t1);
        MapView mMvMap = view.findViewById(R.id.map_view);
        mMvMap.onCreate(savedInstanceState);
        mMvMap.onResume();

        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int errorCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this.getActivity());

        if (ConnectionResult.SUCCESS != errorCode) {
            GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), 0).show();
        } else {
            mMvMap.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                }
            });
        }
        setbanner(lun1, imgs, tes);
        r1 = (RecyclerView) view.findViewById(R.id.r1);
    }

    void setbanner(com.youth.banner.Banner lun1, ArrayList<Integer> imgs, final ArrayList<String> tes) {
        lun1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        lun1.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context.getApplicationContext()).load(path).into(imageView);
            }
        });
        lun1.setBannerTitles(tes);
        lun1.setDelayTime(1000);//time
        lun1.setBannerAnimation(Transformer.DepthPage);
        lun1.setImages(imgs).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context, tes.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        lun1.isAutoPlay(true);
        lun1.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE);
        lun1.start();
    }


    void init() {
        context = getActivity();
        imgs = new ArrayList<>();
        tes = new ArrayList<>();
        imgs.add(R.drawable.te1);
        imgs.add(R.drawable.te2);
        tes.add("Delicious");
        tes.add("Health");

    }

    @Override
    public void onResume() {
        super.onResume();
        t1.setText("Welcome to Flavor, " + mainpage.info.zlmap.get("xm") );
        RwebAdap adap = new RwebAdap(context, mainpage.info.webs);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false) {
            public boolean canScrollVertically() {
                return false;
            }
        };

        r1.setLayoutManager(gridLayoutManager);
        r1.setAdapter(adap);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Shouye2ViewModel.class);
        // TODO: Use the ViewModel
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }
    }
}
