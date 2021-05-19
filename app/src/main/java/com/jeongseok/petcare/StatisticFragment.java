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
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;

import java.util.ArrayList;
import java.util.List;

public class StatisticFragment extends Fragment {
    private View v;
    public List<dogDisease> dogDiseaseList;
    private int count = 12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_statistic, container, false);

        BarChart barChart = v.findViewById(R.id.barchart);

        int[] group1 = {10,70,30,10};
        int[] group2 = {20,50,70,20};
        int[] group3 = {40,10,40,52};

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

        XAxis xAxis = barChart.getXAxis();
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