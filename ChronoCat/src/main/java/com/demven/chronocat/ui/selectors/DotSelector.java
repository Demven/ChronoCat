package com.demven.chronocat.ui.selectors;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Convenient class for selecting dots.
 * @author Dmitry Salnikov (Demven)
 * @since 29.03.2014
 */
public class DotSelector implements Selector{

    private HashMap<String, SelectorHolder> dots = new HashMap<String, SelectorHolder>();
    private ArrayList<String> tags = new ArrayList<String>();

    private int currentPosition = 0;

    @Override
    public void select(String viewTag) {
        SelectorHolder dot = dots.get(viewTag);
        dot.select();

        // unselect previous
        if(currentPosition != tags.indexOf(viewTag)){
            SelectorHolder oldDot = dots.get(tags.get(currentPosition));
            oldDot.unselect();
        }

        currentPosition = tags.indexOf(viewTag);
    }

    public void selectNext(){
        if(currentPosition < tags.size() -1){
            SelectorHolder dot = dots.get(tags.get(currentPosition + 1));
            dot.select();

            // unselect previous
            SelectorHolder oldDot = dots.get(tags.get(currentPosition));
            oldDot.unselect();

            currentPosition++;
        }
    }

    public void selectPrevious(){
        if(currentPosition > 0){
            SelectorHolder dot = dots.get(tags.get(currentPosition - 1));
            dot.select();

            // unselect previous
            SelectorHolder oldDot = dots.get(tags.get(currentPosition));
            oldDot.unselect();

            currentPosition--;
        }
    }

    public void addDot(String viewTag, View defaultView, View highlightedView){
        dots.put(viewTag, new SelectorHolder(defaultView, highlightedView));
        tags.add(viewTag);
    }

    public void removeDot(String viewTag){
        dots.remove(viewTag);
        if(currentPosition == tags.indexOf(viewTag)){
            currentPosition = 0;
        }
        tags.remove(viewTag);
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

    public String getCurrentSelected(){
        return tags.get(currentPosition);
    }
}
