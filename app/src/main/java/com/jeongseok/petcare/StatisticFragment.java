package com.jeongseok.petcare;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.google.android.material.slider.LabelFormatter;
import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;
import com.jeongseok.petcare.localdbPet.myTipDisease;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticFragment extends Fragment {
    private View v;
    public List<dogDisease> dogDiseaseList;
    private int count = 12;
    BarChart mChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_statistic, container, false);
        mChart = (BarChart) v.findViewById(R.id.barchart);
        mChart.setDrawBarShadow(false);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

        // empty labels so that the names are spread evenly
        String[] labels = {"", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월", "", ""};
        IAxisValueFormatter xAxisFormatter = new LabelFormatter(mChart, labels);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(2);
        leftAxis.setLabelCount(5, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(true);

        DataAdapter mDbHelper = new DataAdapter(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        List<myTipDisease> list1 = mDbHelper.getAllElements();
        mDbHelper.close();
// Test Value
//        float[] val1 = {10, 20, 30, 40, 50, 10, 20, 30, 40, 50, 11, 12};
//        float[] val2 = {60, 50, 40, 30, 20, 10, 20, 30, 40, 50, 11, 12};
//        float[] val3 = {20, 10, 30, 60, 40, 10, 20, 30, 40, 50, 11, 12};
        float[] val1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        float[] val2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        float[] val3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Log.d("Test", list1.toString());
        for(myTipDisease t : list1){
            if(t != null && "poo".equals(t.getDisease())){
                String[] s = t.getTipTime().split("-");
                if("나쁨".equals(t.getImage())){
                    val1[Integer.parseInt(s[1])-1]++;
                }else if("보통".equals(t.getImage())) {
                    val2[Integer.parseInt(s[1])-1]++;
                }else if("좋음".equals(t.getImage())) {
                    val3[Integer.parseInt(s[1])-1]++;
                }

            }
        }

        ArrayList<BarEntry> bar1 = new ArrayList<>();
        ArrayList<BarEntry> bar2 = new ArrayList<>();
        ArrayList<BarEntry> bar3 = new ArrayList<>();


        for (int i = 0; i < val1.length; i++) {
            bar1.add(new BarEntry(i, val1[i]));
            bar2.add(new BarEntry(i, val2[i]));
            bar3.add(new BarEntry(i, val3[i]));
        }

        BarDataSet set1 = new BarDataSet(bar1, "나쁨");
        set1.setColor(Color.parseColor("#F58D6E"));
        BarDataSet set2 = new BarDataSet(bar2, "보통");
        set2.setColor(Color.parseColor("#CFE04E"));
        BarDataSet set3 = new BarDataSet(bar3, "좋음");
        set3.setColor(Color.parseColor("#47D086"));


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData data = new BarData(dataSets);
        float groupSpace = 0.1f;
        float barSpace = 0f;
        float barWidth = 0.3f;
        // (barSpace + barWidth) * 5 + groupSpace = 1
        // multiplied by 5 because there are 5 five bars
        // labels will be centered as long as the equation is satisfied
        data.setBarWidth(barWidth);
        // so that the entire chart is shown when scrolled from right to left
        xAxis.setAxisMaximum(labels.length - 1.1f);

        Legend legend = mChart.getLegend();


        mChart.setData(data);
        mChart.setScaleEnabled(false);
        mChart.setVisibleXRangeMaximum(2f);
        mChart.groupBars(1f, groupSpace, barSpace);
        mChart.invalidate();
        mChart.animateY(500);

        return v;
    }

    private class LabelFormatter implements IAxisValueFormatter {
        String[] labels;
        BarLineChartBase<?> chart;

        LabelFormatter(BarLineChartBase<?> chart, String[] labels) {
            this.chart = chart;
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return labels[(int) value];
        }

        @Override
        public int getDecimalDigits() {
            return 0;
        }
    }
}
        /*
        BarChart barChart = v.findViewById(R.id.barchart);

        int[] group1 = {10,70,30,10};
        int[] group2 = {20,50,70,20};
        int[] group3 = {40,10,40,52};

        final HashMap<Integer, String> numMap = new HashMap<>();
        numMap.put(0, "zero");
        numMap.put(1, "first");
        numMap.put(2, "second");
        numMap.put(3, "third");
        numMap.put(4, "fourth");
        numMap.put(5, "fifth");
        numMap.put(6, "sixth");
        numMap.put(7, "sixth");
        numMap.put(8, "sixth");
        numMap.put(9, "sixth");
        numMap.put(10, "sixth");
        numMap.put(11, "sixth");
        numMap.put(12, "sixth");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return numMap.get((int)value);
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();

        for(int i = 0; i < group1.length; i++) {
            entriesGroup1.add(new BarEntry(i, group1[i]));
            entriesGroup2.add(new BarEntry(i, group2[i]));
            entriesGroup3.add(new BarEntry(i, group3[i]));
        }

        BarDataSet set1 = new BarDataSet(entriesGroup1, "나쁨");
        set1.setColor(Color.parseColor("#F58D6E"));
        BarDataSet set2 = new BarDataSet(entriesGroup2, "보통");
        set1.setColor(Color.parseColor("#CFE04E"));
        BarDataSet set3 = new BarDataSet(entriesGroup3, "좋음");
        set3.setColor(Color.parseColor("#47D086"));

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.25f; // x2 dataset

//        XAxis xAxis = barChart.getXAxis();
        xAxis.setCenterAxisLabels(true);

        BarData data = new BarData(set1, set2, set3);
        data.setBarWidth(barWidth); // set the width of each bar
        barChart.setData(data);
        barChart.groupBars(0f, groupSpace, barSpace); // perform the "explicit" grouping
        //barChart.invalidate(); // refresh
        barChart.animateY(500);


        return v;
    }

}
*/
