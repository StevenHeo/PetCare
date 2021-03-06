package com.jeongseok.petcare;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private HashSet<CalendarDay> dates;
    private int color;

    public EventDecorator(int color,Collection<CalendarDay> dates,Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_icon);

        //drawable = drawable;
        this.color = color;
        this.dates = new HashSet<>(dates);

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }


    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));

       //view.setSelectionDrawable(drawable);
    }

}
