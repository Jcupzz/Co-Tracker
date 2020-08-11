package com.jcupzz.mycoviddiary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class Main_Vp_Adapter extends PagerAdapter {
    Context context;
    List<Main_Vp_Names> Main_Vp_names_list;
    LayoutInflater layoutInflater;

    public Main_Vp_Adapter(Context context, List<Main_Vp_Names> Main_Vp_names_list){
        this.context = context;
        this.Main_Vp_names_list = Main_Vp_names_list;
    }

    @Override
    public int getCount() {
        return Main_Vp_names_list.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = layoutInflater.from(context);
        View main_vp_view = layoutInflater.inflate(R.layout.main_vp_elements, container, false);

        TextView main_name_tv,main_tv_description;
        LottieAnimationView lottieAnimationView;
        lottieAnimationView = main_vp_view.findViewById(R.id.lottie_view_id);
        main_name_tv = main_vp_view.findViewById(R.id.category_name_tv_id);
        main_tv_description = main_vp_view.findViewById(R.id.main_tv_description_id);
        lottieAnimationView.setAnimation(Main_Vp_names_list.get(position).getVp_image());
        main_name_tv.setText(Main_Vp_names_list.get(position).getVp_name());
        main_tv_description.setText(Main_Vp_names_list.get(position).getVp_description());
        container.addView(main_vp_view,0);

        main_vp_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(position==1)
                {
                    Intent intent1 = new Intent(v.getContext(),TrackerActivity.class);
                    v.getContext().startActivity(intent1);
                    MainActivity.j=1;
                    Intent intent = new Intent(v.getContext(),DisplayActivity.class);
                    v.getContext().startActivity(intent);
                }
                if(position==2)
                {
                    Intent intent1 = new Intent(v.getContext(),QRCode_Generator.class);
                    v.getContext().startActivity(intent1);
                }
                if(position==3)
                {
                    Intent i = new Intent(v.getContext(),QRCode_Scanner.class);
                    v.getContext().startActivity(i);
                }
                if(position==4)
                {
                    Intent i = new Intent(v.getContext(),Covid_Today.class);
                    v.getContext().startActivity(i);
                }
                if(position==5)
                {
                    Intent intent1 = new Intent(v.getContext(),AddPlacesManually.class);
                    v.getContext().startActivity(intent1);
                }
                if(position==6)
                {
                    Intent intent1 = new Intent(v.getContext(),Footprints_Category.class);
                    v.getContext().startActivity(intent1);
                }
            }
        });

        return main_vp_view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
