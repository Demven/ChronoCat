package com.demven.chronocat.ui.fragments;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.data.beans.Category;
import com.demven.chronocat.managers.FontManager;
import com.demven.chronocat.receivers.MinuteReceiver;
import com.demven.chronocat.ui.adapters.TimeTrackerListAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Fragment, that represents the main-screen of the application.
 * Originally it shows the current time and controls to start or stop the time-tracker.
 * @author Dmitry Salnikov (Demven)
 * @since 11.03.2014
 */
public class TimeTrackerFragment extends Fragment {

    public static final int STATE_CANCELED = 1;
    public static final int STATE_STARTED = 2;
    private int currentState = STATE_CANCELED;

    private TextView startTime;
    private TextView currentTime;
    private TextView totalTime;

    private Button startButton;
    private Button stopButton;
    private Button backButton;
    private Button cancelButton;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    // For list of categories
    private ListView categoriesList;
    private RelativeLayout listContainer;
    private Button buttonAllWorks;
    private ArrayList<Category> categories = new ArrayList<Category>();
    private boolean isListVisible = false;
    private int currentListPosition = 0;

    // For BroadcastReceiver
    public final static String RETURN_ACTION_MINUTE =
            "com.demven.chronocat.ui.fragments.TimeTrackerFragment.RETURN_ACTION_MINUTE";
    private final IntentFilter intentFilter = new IntentFilter(RETURN_ACTION_MINUTE);
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            updateCurrentTime();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.time_tracker_fragment, container, false);

        // Init buttons
        startButton = (Button) rootView.findViewById(R.id.start_button);
        stopButton = (Button) rootView.findViewById(R.id.stop_button);
        backButton = (Button) rootView.findViewById(R.id.back_button);
        cancelButton = (Button) rootView.findViewById(R.id.cancel_button);

        // Set custom font to all textviews
        startTime = (TextView) rootView.findViewById(R.id.time_tracker_start_time);
        currentTime = (TextView) rootView.findViewById(R.id.time_tracker_current_time);
        totalTime = (TextView) rootView.findViewById(R.id.time_tracker_total_time);

        FontManager fontManager = ((ChronoCatApplication)getActivity().getApplication()).getFontManager();
        Typeface brieFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, getActivity());

        startTime.setTypeface(brieFont);
        currentTime.setTypeface(brieFont);
        totalTime.setTypeface(brieFont);

        // Show current time
        updateCurrentTime();

        // Prepare list and button over it
        categoriesList = (ListView) rootView.findViewById(R.id.categories_list);
        buttonAllWorks = (Button) rootView.findViewById(R.id.button_all_works);
        listContainer = (RelativeLayout) rootView.findViewById(R.id.categories_list_container);

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();

        getActivity().registerReceiver(broadcastReceiver, intentFilter);

        Intent intent = new Intent(getActivity(), MinuteReceiver.class);
        // In reality, you would want to have a static variable for the request code instead of 192837
        pendingIntent = PendingIntent.getBroadcast(
                getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        int seconds = Calendar.getInstance().get(Calendar.SECOND);
        Log.e("TimeTrackerFragment", "seconds=" + seconds);

        long triggerAtMillis = System.currentTimeMillis() + (60 - seconds) * 1000;
        Log.e("TimeTrackerFragment", "triggerAtMillis=" + triggerAtMillis);

        long intervalMillis = 60000;

        // Get the AlarmManager service
        alarmManager = (AlarmManager) getActivity().getSystemService(Activity.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, pendingIntent);


        // Setup listeners for buttons
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide start-button
                startButton.setVisibility(View.GONE);

                // show stop-button, back and cancel
                stopButton.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);

                // show text views with started time and total time
                startTime.setVisibility(View.VISIBLE);
                startTime.setText(currentTime.getText());
                totalTime.setVisibility(View.VISIBLE);

                // set state
                currentState = STATE_STARTED;
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide stop-button, back and cancel
                stopButton.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);

                // show start-button
                startButton.setVisibility(View.VISIBLE);

                // hide text views with started time and total time
                startTime.setVisibility(View.GONE);
                totalTime.setVisibility(View.GONE);

                // set state
                currentState = STATE_CANCELED;
            }
        });

        categories.add(new Category(1l, "Начало рабочего дня", Color.parseColor("#ffffff")));
        categories.add(new Category(2l, "Болтовня, чай", Color.parseColor("#4a47d2")));
        categories.add(new Category(3l, "Работа", Color.parseColor("#096322")));
        categories.add(new Category(4l, "Обед", Color.parseColor("#ffffff")));
        categories.add(new Category(5l, "Спорт", Color.parseColor("#ffffff")));
        categories.add(new Category(6l, "Страдание фигней", Color.parseColor("#4a47d2")));
        categories.add(new Category(3l, "Работа", Color.parseColor("#096322")));
        categories.add(new Category(4l, "Обед", Color.parseColor("#ffffff")));
        categories.add(new Category(5l, "Спорт", Color.parseColor("#ffffff")));
        categories.add(new Category(6l, "Страдание фигней", Color.parseColor("#4a47d2")));

        prepareList();
        prepareButtonOverSpinner();
    }

    @Override
    public void onStop(){
        super.onStop();
        alarmManager.cancel(pendingIntent);
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void prepareList(){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) categoriesList.getLayoutParams();
        Log.e("first height", "" + params.height);
        int defaultItemHeight = params.height/7;
        Log.e("defaultItemHeight", "" + defaultItemHeight);
        if(categories.size() < 7){
            // trim list to size of total items' height
            params.height = categories.size() * defaultItemHeight;
            Log.e("new height", "" + params.height);
        }
        categoriesList.setLayoutParams(params);

        TimeTrackerListAdapter listAdapter = new TimeTrackerListAdapter(getActivity(), categories);
        categoriesList.setAdapter(listAdapter);
        categoriesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentListPosition = position;
                buttonAllWorks.setText(categories.get(currentListPosition).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void prepareButtonOverSpinner(){
        // Set default title on the button, so that it will reflect current title in the spinner
        buttonAllWorks.setText(categories.get(currentListPosition).getName());
        buttonAllWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isListVisible){
                    listContainer.setVisibility(View.GONE);
                    isListVisible = false;
                } else{
                    listContainer.setVisibility(View.VISIBLE);
                    isListVisible = true;
                }
            }
        });
    }

    private void updateCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));

        if(hour.length() == 1){
            hour = "0" + hour;
        }
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        currentTime.setText(hour + ":" + minute);
    }
}
