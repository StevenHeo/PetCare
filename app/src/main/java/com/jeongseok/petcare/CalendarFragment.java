package com.jeongseok.petcare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class CalendarFragment extends Fragment {
    private Button button;
    private View v;
    MaterialCalendarView materialCalendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calendar,container,false);
        materialCalendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        materialCalendarView.setDynamicHeightEnabled(true);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
                calendarDayList.add(date);

                EventDecorator eventDecorator = new EventDecorator(Color.BLACK,calendarDayList,getActivity());

                materialCalendarView.addDecorators(eventDecorator);

                Intent next_intent = new Intent(getActivity(),CalendarInActivity.class);
                startActivity(next_intent);
            }
        });







        return v;
    }



}
