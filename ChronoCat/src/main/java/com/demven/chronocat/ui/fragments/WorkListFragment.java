package com.demven.chronocat.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.managers.FontManager;

/**
 * Fragment, that represents the list of done works for the one day.
 * Originally it shows done works for the current day.
 * @author Dmitry Salnikov (Demven)
 * @since 16.03.2014
 */
public class WorkListFragment extends Fragment {

    private TextView listDate;
    private TextView workingTimeValue;
    private TextView totalTimeValue;

    private Button showPreviousButton;
    private Button showNextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.work_list_fragment, container, false);

        // Init buttons
        showPreviousButton = (Button) rootView.findViewById(R.id.button_show_previous_day);
        showNextButton = (Button) rootView.findViewById(R.id.button_show_next_day);

        // Set custom font to all textviews
        listDate = (TextView) rootView.findViewById(R.id.list_date);
        workingTimeValue = (TextView) rootView.findViewById(R.id.working_time_value);
        totalTimeValue = (TextView) rootView.findViewById(R.id.total_time_value);

        FontManager fontManager = ((ChronoCatApplication)getActivity().getApplication()).getFontManager();
        Typeface brieFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, getActivity());

        listDate.setTypeface(brieFont);
        workingTimeValue.setTypeface(brieFont);
        totalTimeValue.setTypeface(brieFont);

        return rootView;
    }
}