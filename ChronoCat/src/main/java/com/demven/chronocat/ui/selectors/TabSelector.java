package com.demven.chronocat.ui.selectors;

import android.view.View;

import java.util.HashMap;

/**
 * Convenient class for selecting tabs.
 * @author Dmitry Salnikov (Demven)
 * @since 29.03.2014
 */
public class TabSelector implements Selector{

    private HashMap<String, SelectorHolder> tabs = new HashMap<String, SelectorHolder>();

    private String currentSelected = null;

    @Override
    public void select(String viewTag) {
        SelectorHolder tab = tabs.get(viewTag);
        tab.select();

        if(currentSelected != null){
            SelectorHolder oldTab = tabs.get(currentSelected);
            oldTab.unselect();
        }

        currentSelected = viewTag;
    }

    public void addTab(String viewTag, View defaultView, View highlightedView){
        tabs.put(viewTag, new SelectorHolder(defaultView, highlightedView));
    }

    public void removeTab(String viewTag){
        tabs.remove(viewTag);
        if(currentSelected.equals(viewTag)){
            currentSelected = null;
        }
    }

    public String getCurrentSelected(){
        return currentSelected;
    }
}
