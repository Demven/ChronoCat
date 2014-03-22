package com.demven.chronocat.ui.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.data.beans.Category;
import com.demven.chronocat.data.beans.Work;
import com.demven.chronocat.managers.FontManager;
import com.demven.chronocat.ui.adapters.WorkListAdapter;

import java.util.ArrayList;

/**
 * Fragment, that represents the list of done works for the one day.
 * Originally it shows done works for the current day.
 * @author Dmitry Salnikov (Demven)
 * @since 16.03.2014
 */
public class WorkListFragment extends Fragment {

    private TextView listDate;
    private TextView workingTimeTitle;
    private TextView totalTimeTitle;
    private TextView workingTimeValue;
    private TextView totalTimeValue;

    private Button showPreviousButton;
    private Button showNextButton;

    private ListView workList;
    private WorkListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.work_list_fragment, container, false);

        if(rootView != null){
            // Init list
            workList = (ListView) rootView.findViewById(R.id.work_list);

            // Init buttons
            showPreviousButton = (Button) rootView.findViewById(R.id.button_show_previous_day);
            showNextButton = (Button) rootView.findViewById(R.id.button_show_next_day);

            // Set custom font to all textviews
            listDate = (TextView) rootView.findViewById(R.id.list_date);
            workingTimeTitle = (TextView) rootView.findViewById(R.id.working_time_title);
            totalTimeTitle = (TextView) rootView.findViewById(R.id.total_time_title);
            workingTimeValue = (TextView) rootView.findViewById(R.id.working_time_value);
            totalTimeValue = (TextView) rootView.findViewById(R.id.total_time_value);

            FontManager fontManager = ((ChronoCatApplication)getActivity().getApplication()).getFontManager();
            Typeface digitFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, getActivity());
            Typeface titleFont = fontManager.getTypeface(FontManager.ARL_NARROW, getActivity());

            workingTimeTitle.setTypeface(titleFont);
            totalTimeTitle.setTypeface(titleFont);

            listDate.setTypeface(digitFont);
            workingTimeValue.setTypeface(digitFont);
            totalTimeValue.setTypeface(digitFont);
        }

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        formList();
    }

    private void formList(){
        Category workCategory = new Category(1l, "Работа", Color.parseColor("#096322"));
        Category beginCategory = new Category(2l, "Начало рабочего дня", Color.parseColor("#ffffff"));
        Category chatCategory = new Category(3l, "Болтовня с друзьями", Color.parseColor("#4a47d2"));
        Category dinnerCategory = new Category(4l, "Обед", Color.parseColor("#ffffff"));

        ArrayList<Work> works = new ArrayList<Work>();
        works.add(new Work(1l, beginCategory, "", "08:12", "09:54", 3, 10, 2014));
        works.add(new Work(2l, workCategory, "Android EPIS project", "09:54", "11:30", 3, 10, 2014));
        works.add(new Work(3l, chatCategory, "С Диной и Максом", "11:30", "12:15", 3, 10, 2014));
        works.add(new Work(4l, dinnerCategory, "", "12:15", "13:47", 3, 10, 2014));
        works.add(new Work(5l, workCategory, "Изучение Spring", "13:47", "14:20", 3, 10, 2014));
        works.add(new Work(6l, chatCategory, "С Кристиной", "14:20", "14:40", 3, 10, 2014));
        works.add(new Work(7l, workCategory, "Android EPIS project", "14:40", "15:15", 3, 10, 2014));

        if(adapter == null){
            adapter = new WorkListAdapter(this.getActivity(), works);
            workList.setAdapter(adapter);
        }
    }
}