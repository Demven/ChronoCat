package com.demven.chronocat.ui;

import android.view.View;

/**
 * Interface that declares all methods that used in layouts as onClick actions.
 * @author Dmitry Salnikov (Demven)
 * @since 16.03.2014
 */
public interface OnClickMethods {

    /**
     * Shows a screen with the list with all works for a day
     * @param view - Button
     */
    public void showList(View view);

    /**
     * Shows a screen with all graphs that can provide this app
     * @param view - Button
     */
    public void showGraphs(View view);
}
