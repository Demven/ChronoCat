package com.demven.chronocat.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.managers.FontManager;

/**
 * Fragment, that represents the main-screen of the application.
 * Originally it shows the current time and controls to start or stop the time-tracker.
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class TimeTrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.time_tracker_fragment, container, false);

        // Set custom font to all textviews
        TextView startTime = (TextView) rootView.findViewById(R.id.time_tracker_start_time);
        TextView currentTime = (TextView) rootView.findViewById(R.id.time_tracker_current_time);
        TextView totalTime = (TextView) rootView.findViewById(R.id.time_tracker_total_time);

        FontManager fontManager = ((ChronoCatApplication)getActivity().getApplication()).getFontManager();
        Typeface brieFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, getActivity());

        startTime.setTypeface(brieFont);
        currentTime.setTypeface(brieFont);
        totalTime.setTypeface(brieFont);

        return rootView;
    }
}
