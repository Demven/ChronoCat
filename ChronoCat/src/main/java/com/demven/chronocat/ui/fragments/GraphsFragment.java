package com.demven.chronocat.ui.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.demven.chronocat.ChronoCatApplication;
import com.demven.chronocat.R;
import com.demven.chronocat.managers.FontManager;
import com.demven.chronocat.ui.selectors.TabSelector;
import com.demven.chronocat.ui.util.graphview.*;

/**
 * Fragment, that shows graphs for different data.
 * @author Dmitry Salnikov (Demven)
 * @since 27.03.2014
 */
public class GraphsFragment extends Fragment {

    private final String TAB_WEEK = "TAB_WEEK";
    private final String TAB_MONTH = "TAB_MONTH";

    private Button showPreviousButton;
    private Button showNextButton;

    private RelativeLayout tabWeek;
    private RelativeLayout tabMonth;

    private TextView graphDate;
    private TextView weekTabTitle;
    private TextView monthTabTitle;

    // fonts
    private Typeface digitFont;
    private Typeface titleFont;

    private TabSelector tabSelector = new TabSelector();

    // For graphs
    private RelativeLayout graph;
    private GraphView.GraphViewData[] weekValues;
    private GraphView.GraphViewData[] monthValues;
    private String[] weekLabels;
    private String[] monthLabels;
    private GraphViewSeries.GraphViewSeriesStyle seriesStyle;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Prepare fonts
        FontManager fontManager = ((ChronoCatApplication)getActivity().getApplication()).getFontManager();
        digitFont = fontManager.getTypeface(FontManager.BRIE_LIGHT, getActivity());
        titleFont = fontManager.getTypeface(FontManager.ARL_NARROW, getActivity());

        // Prepare graph style
        //TODO: int lineColor = getResources().getColor(R.color.dynamics_graph_line_color);
        int lineColor = Color.parseColor("#d4a635");
        int thickest = 3;
        seriesStyle = new GraphViewSeries.GraphViewSeriesStyle(lineColor, thickest);
        seriesStyle.setValueDependentColor(new ValueDependentColor() {
            @Override
            public int get(GraphViewDataInterface data) {
                // the higher the more lighter
                return Color.rgb(
                        (int)(162+(data.getY()*100)),
                        (int)(116+(data.getY()*100)),
                        (int)(3+(data.getY()*100)));
            }
        });

        // Prepare graph data
        prepareGraphData();
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.graphs_fragment, container, false);

        // Init arrow-buttons and their listeners
        showPreviousButton = (Button) rootView.findViewById(R.id.button_show_previous);
        showPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevious();
            }
        });
        showNextButton = (Button) rootView.findViewById(R.id.button_show_next);
        showNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });

        // init TextViews and set fonts
        graphDate = (TextView) rootView.findViewById(R.id.graph_date);
        weekTabTitle = (TextView) rootView.findViewById(R.id.week_tab_title);
        monthTabTitle = (TextView) rootView.findViewById(R.id.month_tab_title);
        graphDate.setTypeface(digitFont);
        weekTabTitle.setTypeface(titleFont);
        monthTabTitle.setTypeface(titleFont);

        // Init Tab listeners
        tabWeek = (RelativeLayout) rootView.findViewById(R.id.week_tab);
        tabWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectWeekTab();
            }
        });
        tabMonth = (RelativeLayout) rootView.findViewById(R.id.month_tab);
        tabMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMonthTab();
            }
        });

        // Init TabSelector
        tabSelector.addTab(TAB_WEEK,
                rootView.findViewById(R.id.week_tab),
                rootView.findViewById(R.id.week_tab_highlighter));
        tabSelector.addTab(TAB_MONTH,
                rootView.findViewById(R.id.month_tab),
                rootView.findViewById(R.id.month_tab_highlighter));

        // Init graph
        graph = (RelativeLayout) rootView.findViewById(R.id.graph);

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        tabSelector.select(TAB_WEEK);

        renderGraph();
    }

    private void selectWeekTab(){
        if(!tabSelector.getCurrentSelected().equals(TAB_WEEK)){
            tabSelector.select(TAB_WEEK);
            renderGraph();
        }
    }

    private void selectMonthTab(){
        if(!tabSelector.getCurrentSelected().equals(TAB_MONTH)){
            tabSelector.select(TAB_MONTH);
            renderGraph();
        }
    }

    private void showNext(){
    }

    private void showPrevious(){
    }

    /**
     * Paint graph using received HashMap, that should contain data in pairs "date-rate"
     */
    private void renderGraph(){
        graph.removeAllViews();
        //LineGraphView graphView = new LineGraphView(getActivity(), "");
        BarGraphView graphView = new BarGraphView(getActivity(), "");
        graphView.setDrawValuesOnTop(true);
        graphView.getGraphViewStyle().setNumVerticalLabels(10);

        GraphViewSeries series = null;

        if (tabSelector.getCurrentSelected().equals(TAB_WEEK)) {
            series = new GraphViewSeries("some week", seriesStyle, weekValues);
            graphView.setHorizontalLabels(weekLabels);

        } else if (tabSelector.getCurrentSelected().equals(TAB_MONTH)) {
            series = new GraphViewSeries("some month", seriesStyle, monthValues);
            graphView.setHorizontalLabels(monthLabels);
            graphView.setViewPort(1, monthValues.length);
            graphView.setScrollable(true);
            graphView.setScalable(true);
        }

        if(series != null){
            graphView.addSeries(series);
            // TODO: graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.label_font_size));
            graphView.getGraphViewStyle().setTextSize(10);
            graphView.getGraphViewStyle().setNumVerticalLabels(1);
            graphView.getGraphViewStyle().setNumHorizontalLabels(1);
            graphView.getGraphViewStyle().setVerticalLabelsWidth(1);
            graph.addView(graphView);
        }
    }

    private void prepareGraphData(){
        weekLabels = new String[7];
        weekLabels[0] = "03-Пн";
        weekLabels[1] = "04-Вт";
        weekLabels[2] = "05-Ср";
        weekLabels[3] = "06-Чт";
        weekLabels[4] = "07-Пт";
        weekLabels[5] = "08-Сб";
        weekLabels[6] = "09-Вс";

        monthLabels = new String[30];
        monthLabels[0] = "01";
        monthLabels[1] = "02";
        monthLabels[2] = "03";
        monthLabels[3] = "04";
        monthLabels[4] = "05";
        monthLabels[5] = "06";
        monthLabels[6] = "07";

        monthLabels[7] = "08";
        monthLabels[8] = "09";
        monthLabels[9] = "10";
        monthLabels[10] = "11";
        monthLabels[11] = "12";
        monthLabels[12] = "13";
        monthLabels[13] = "14";

        monthLabels[14] = "15";
        monthLabels[15] = "16";
        monthLabels[16] = "17";
        monthLabels[17] = "18";
        monthLabels[18] = "19";
        monthLabels[19] = "20";
        monthLabels[20] = "21";

        monthLabels[21] = "22";
        monthLabels[22] = "23";
        monthLabels[23] = "24";
        monthLabels[24] = "25";
        monthLabels[25] = "26";
        monthLabels[26] = "27";
        monthLabels[27] = "28";

        monthLabels[28] = "29";
        monthLabels[29] = "30";

        weekValues = new GraphView.GraphViewData[7];
        weekValues[0] = new GraphView.GraphViewData(1, 0.458);
        weekValues[1] = new GraphView.GraphViewData(2, 0.519);
        weekValues[2] = new GraphView.GraphViewData(3, 0.638);
        weekValues[3] = new GraphView.GraphViewData(4, 0.327);
        weekValues[4] = new GraphView.GraphViewData(5, 0.598);
        weekValues[5] = new GraphView.GraphViewData(6, 0.000);
        weekValues[6] = new GraphView.GraphViewData(7, 0.000);

        monthValues = new GraphView.GraphViewData[30];
        monthValues[0] = new GraphView.GraphViewData(1, 0.458);
        monthValues[1] = new GraphView.GraphViewData(2, 0.519);
        monthValues[2] = new GraphView.GraphViewData(3, 0.638);
        monthValues[3] = new GraphView.GraphViewData(4, 0.327);
        monthValues[4] = new GraphView.GraphViewData(5, 0.598);
        monthValues[5] = new GraphView.GraphViewData(6, 0.000);
        monthValues[6] = new GraphView.GraphViewData(7, 0.000);

        monthValues[7] = new GraphView.GraphViewData(8, 0.458);
        monthValues[8] = new GraphView.GraphViewData(9, 0.519);
        monthValues[9] = new GraphView.GraphViewData(10, 0.638);
        monthValues[10] = new GraphView.GraphViewData(11, 0.327);
        monthValues[11] = new GraphView.GraphViewData(12, 0.598);
        monthValues[12] = new GraphView.GraphViewData(13, 0.000);
        monthValues[13] = new GraphView.GraphViewData(14, 0.000);

        monthValues[14] = new GraphView.GraphViewData(15, 0.458);
        monthValues[15] = new GraphView.GraphViewData(16, 0.519);
        monthValues[16] = new GraphView.GraphViewData(17, 0.638);
        monthValues[17] = new GraphView.GraphViewData(18, 0.327);
        monthValues[18] = new GraphView.GraphViewData(19, 0.598);
        monthValues[19] = new GraphView.GraphViewData(20, 0.000);
        monthValues[20] = new GraphView.GraphViewData(21, 0.000);

        monthValues[21] = new GraphView.GraphViewData(22, 0.458);
        monthValues[22] = new GraphView.GraphViewData(23, 0.519);
        monthValues[23] = new GraphView.GraphViewData(24, 0.638);
        monthValues[24] = new GraphView.GraphViewData(25, 0.327);
        monthValues[25] = new GraphView.GraphViewData(26, 0.598);
        monthValues[26] = new GraphView.GraphViewData(27, 0.000);
        monthValues[27] = new GraphView.GraphViewData(28, 0.000);

        monthValues[28] = new GraphView.GraphViewData(29, 0.598);
        monthValues[29] = new GraphView.GraphViewData(30, 0.327);
    }
}