package com.jeongseok.petcare;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private HashSet<CalendarDay> dates;
    private int color;

    public EventDecorator(int color,Collection<CalendarDay> dates,Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_icon);
        this.color = color;
        this.dates = new HashSet<>(dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }


    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }

}
