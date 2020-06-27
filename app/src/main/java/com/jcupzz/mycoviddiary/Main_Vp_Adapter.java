package com.jcupzz.mycoviddiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

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

        TextView textView;
        LottieAnimationView lottieAnimationView;
        lottieAnimationView = main_vp_view.findViewById(R.id.lottie_view_id);
        textView = main_vp_view.findViewById(R.id.category_name_tv_id);
        lottieAnimationView.setAnimation(Main_Vp_names_list.get(position).getVp_image());
        textView.setText(Main_Vp_names_list.get(position).getVp_name());
        container.addView(main_vp_view,0);

        main_vp_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });

        return main_vp_view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
