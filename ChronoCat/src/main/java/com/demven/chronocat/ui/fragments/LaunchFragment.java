package com.demven.chronocat.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.demven.chronocat.R;

/**
 * Fragment, that represents the start-screen, that shows application's load progress.
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class LaunchFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.launch_fragment, container, false);

        // Start clock-arrows rotate-animation
        ImageView hourArrow = (ImageView) rootView.findViewById(R.id.hour_arrow);
        ImageView minuteArrow = (ImageView) rootView.findViewById(R.id.minute_arrow);

        Animation hourArrowRotate = AnimationUtils.loadAnimation(this.getActivity(), R.anim.clock_hour_arrow_animation);
        Animation minuteArrowRotate = AnimationUtils.loadAnimation(this.getActivity(), R.anim.clock_minute_arrow_animation);

        hourArrow.startAnimation(hourArrowRotate);
        minuteArrow.startAnimation(minuteArrowRotate);
        return rootView;
    }
}
