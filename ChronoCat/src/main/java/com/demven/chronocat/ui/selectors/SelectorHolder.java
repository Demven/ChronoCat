package com.demven.chronocat.ui.selectors;

import android.view.View;

/**
 * Class, that encapsulates logic of a selecting view group and holds it's views
 */
public class SelectorHolder {
    private View defaultView;
    private View highlightedView;

    private boolean selected = false;

    /** If true - means that when is selected - defaultView will be hidden. */
    private boolean isHideDefault = false;

    public SelectorHolder(View defaultView, View highlightedView){
        this.defaultView = defaultView;
        this.highlightedView = highlightedView;
    }

    /**
     * Highlights view showing highlightedView
     */
    public void select(){
        highlightedView.setVisibility(View.VISIBLE);
        if(isHideDefault){
            defaultView.setVisibility(View.GONE);
        }
        selected = true;
    }

    /**
     * Hides highlightedView
     */
    public void unselect(){
        highlightedView.setVisibility(View.GONE);
        if(isHideDefault){
            defaultView.setVisibility(View.VISIBLE);
        }
        selected = false;
    }

    public void setIsHideDefault(boolean isHideDefault){
        this.isHideDefault = isHideDefault;
    }

    public boolean isHideDefault() {
        return isHideDefault;
    }

    public View getDefaultView() {
        return defaultView;
    }

    public boolean isSelected(){
        return selected;
    }

    public View getHighlightedView() {
        return highlightedView;
    }

}
