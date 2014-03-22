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
import com.demven.chronocat.data.beans.Work;
import com.demven.chronocat.managers.FontManager;

import java.util.ArrayList;

/**
 * Adapter for displaying list of works for a day
 * @author Dzmitry Salnikau
 * @since 17.03.2014
 */
public class WorkListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Work> works;
    private Typeface digitFont;
    private Typeface titlesFont;

    public WorkListAdapter(Activity activity, ArrayList<Work> works) {
        this.works = works;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FontManager fontManager = ((ChronoCatApplication)activity.getApplication()).getFontManager();
        digitFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, activity);
        titlesFont = fontManager.getTypeface(FontManager.ARL_NARROW, activity);
    }

    private static class ViewHolder {
        TextView category;
        TextView description;
        TextView period;
        TextView total;
    }

    @Override
    public int getCount() {
        return works.size();
    }

    @Override
    public Object getItem(int position) {
        return works.get(position);
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
            convertView = layoutInflater.inflate(R.layout.work_list_item, null);

            viewHolder = new ViewHolder();

            viewHolder.category = (TextView) convertView.findViewById(R.id.category);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.period = (TextView) convertView.findViewById(R.id.period);
            viewHolder.total = (TextView) convertView.findViewById(R.id.total);

            // Set proper fonts
            viewHolder.category.setTypeface(titlesFont);
            viewHolder.description.setTypeface(titlesFont);
            viewHolder.period.setTypeface(digitFont);
            viewHolder.total.setTypeface(digitFont);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Work itemWork = works.get(position);

        // Set values for an item
        viewHolder.category.setText(itemWork.getCategory().getName());
        viewHolder.category.setTextColor(itemWork.getCategory().getColor());
        String description = itemWork.getDescription().toUpperCase();
        if(!description.equals("")){
            viewHolder.description.setText(itemWork.getDescription().toUpperCase());
        } else{
            // hide description
            viewHolder.description.setVisibility(View.GONE);
        }
        viewHolder.period.setText(itemWork.getTimeStart() + " - " + itemWork.getTimeEnd());
        viewHolder.total.setText("0:25"); // TODO: total
        viewHolder.total.setTextColor(itemWork.getCategory().getColor());
        return convertView;
    }
}
