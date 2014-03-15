package com.demven.chronocat.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.demven.chronocat.ui.fragments.TimeTrackerFragment;

/**
 * Created by Администратор on 14.03.14.
 */
public class MinuteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Log.e("MinuteReceiver", "minute!");
        Toast.makeText(context, "minute", Toast.LENGTH_SHORT).show();
        Intent minuteIntent = new Intent(TimeTrackerFragment.RETURN_ACTION_MINUTE);
        context.sendBroadcast(minuteIntent);
    }
};
