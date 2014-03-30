package com.demven.chronocat.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.demven.chronocat.R;
import com.demven.chronocat.ui.OnClickMethods;
import com.demven.chronocat.ui.fragments.GraphsFragment;
import com.demven.chronocat.ui.fragments.LaunchFragment;
import com.demven.chronocat.ui.fragments.TimeTrackerFragment;
import com.demven.chronocat.ui.fragments.WorkListFragment;

public class MainActivity extends FragmentActivity implements OnClickMethods{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLaunch();
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
     * Shows launch screen
     */
    public void showLaunch(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new LaunchFragment())
                .commit();
    }

    /**
     * Shows screen with time-tracker
     */
    public void showTimeTracker(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TimeTrackerFragment())
                .commit();
    }


    /* Implementation of OnClickMethods */

    @Override
    public void showList(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new WorkListFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showGraphs(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new GraphsFragment())
                .addToBackStack(null)
                .commit();
    }
}
