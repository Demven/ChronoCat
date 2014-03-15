package com.demven.chronocat.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.demven.chronocat.R;
import com.demven.chronocat.ui.activities.MainActivity;

/**
 * Fragment, that represents the start-screen, that shows application's load progress.
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class LaunchFragment extends Fragment{

    private final int LAUNCH_DELAY = 3000; // milliseconds

    private Handler handler = new Handler();

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

    @Override
    public void onStart(){
        super.onStart();

        handler.postDelayed(new Runnable() {
            public void run() {
                ((MainActivity) getActivity()).showTimeTracker();
            }
        }, LAUNCH_DELAY);
    }
}
