package com.demven.chronocat;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new DummyFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.load_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A dummy fragment containing a simple view.
     */
    public static class DummyFragment extends Fragment {

        public DummyFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.load_screen_fragment, container, false);

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

}
