package com.jeongseok.petcare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CalendarFragment extends Fragment {

    private View v;
    MaterialCalendarView materialCalendarView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calendar,container,false);
        materialCalendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        materialCalendarView.setDynamicHeightEnabled(true);
        materialCalendarView.setSelectedDate(CalendarDay.today());

        //캘린더 팁 초기화 코드 -> 주석풀고 실행 후 -> 다시실행할때는 주석처리
        DataAdapter mDbHelper = new DataAdapter(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.deleteMyTipTable();
        mDbHelper.close();
        //

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
                calendarDayList.add(date);
                EventDecorator eventDecorator = new EventDecorator(Color.BLACK,calendarDayList,getActivity());
                materialCalendarView.addDecorators(eventDecorator);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Log.i("선택한날짜", format.format(date.getDate()));

                DataAdapter mDbHelper = new DataAdapter(getContext());
                mDbHelper.createDatabase();
                mDbHelper.open();
                if(mDbHelper.checkMyImage(format.format(date.getDate()))==true) {
                    Intent next_intent = new Intent(getActivity(), BeforeTipActivty.class);
                    next_intent.putExtra("today",date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDay()+"일"+"");
                    next_intent.putExtra("selectday",format.format(date.getDate()));
                    startActivity(next_intent);
                }else{
                    Intent next_intent = new Intent(getActivity(), CalendarInActivity.class);
                    next_intent.putExtra("selectday",format.format(date.getDate()));
                    startActivity(next_intent);
                }
                mDbHelper.close();

            }
        });


        return v;
    }



}
