package com.demven.chronocat.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.data.beans.Category;
import com.demven.chronocat.managers.FontManager;

import java.util.ArrayList;

/**
 * Adapter for displaying list of work-categories in a spinner
 * @author Dzmitry Salnikau
 * @since 19.03.2014
 */
public class TimeTrackerListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Category> categories;
    private Typeface titlesFont;

    public TimeTrackerListAdapter(Activity activity, ArrayList<Category> categories) {
        this.categories = categories;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FontManager fontManager = ((ChronoCatApplication)activity.getApplication()).getFontManager();
        titlesFont = fontManager.getTypeface(FontManager.ARL_NARROW, activity);
    }

    private static class ViewHolder {
        TextView category;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            // Create new view for an item
            convertView = layoutInflater.inflate(R.layout.time_tracker_list_item, null);

            viewHolder = new ViewHolder();

            viewHolder.category = (TextView) convertView.findViewById(R.id.category_name);

            // Set proper fonts
            viewHolder.category.setTypeface(titlesFont);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Category category = categories.get(position);

        // Set value for an item and set proper colors
        viewHolder.category.setText(category.getName());
        //viewHolder.category.setTextColor(itemWork.getCategory().getColor());

        return convertView;
    }
}

