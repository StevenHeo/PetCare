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
import com.jeongseok.petcare.localdbPet.myTipDisease;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;


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


        /*캘린더 팁 초기화 코드 -> 주석풀고 실행 후 -> 다시실행할때는 주석처리

        DataAdapter mDbHelper = new DataAdapter(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        List<myTipDisease> myTipDisease;
        myTipDisease =  mDbHelper.selectMyTipTable();

        mDbHelper.close();
         */
        DataAdapter mDbHelper = new DataAdapter(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        List<myTipDisease> list1 = mDbHelper.getAllElements();
        Log.d("listData", list1.toString());
        mDbHelper.close();

        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
        for(myTipDisease t : list1){
            if(t != null){
                String[] s = t.getTipTime().split("-");
                calendarDayList.add(CalendarDay.from(Integer.parseInt(s[0]),Integer.parseInt(s[1]) - 1,Integer.parseInt(s[2])));
            }
        }

        EventDecorator eventDecorator = new EventDecorator(R.drawable.bad_ic,calendarDayList,getActivity());
        materialCalendarView.addDecorators(eventDecorator);


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                DataAdapter mDbHelper = new DataAdapter(getContext());
                mDbHelper.createDatabase();
                mDbHelper.open();

                calendarDayList.add(date);
                Log.d("calList", calendarDayList.toString());
                EventDecorator eventDecorator = new EventDecorator(Color.BLACK,calendarDayList,getActivity());
                materialCalendarView.addDecorators(eventDecorator);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Log.i("선택한날짜", format.format(date.getDate()));


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
