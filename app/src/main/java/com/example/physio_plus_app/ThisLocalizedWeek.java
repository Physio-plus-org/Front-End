package com.example.physio_plus_app;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ThisLocalizedWeek {

    // Try and always specify the time zone you're working with
    private final static ZoneId TZ = ZoneId.of("Europe/Athens");

    private final Locale locale;
    private DayOfWeek firstDayOfWeek;
    private DayOfWeek lastDayOfWeek;


    public ThisLocalizedWeek(final Locale locale) {
        this.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.lastDayOfWeek = DayOfWeek.of(((this.firstDayOfWeek.getValue() + 282) % DayOfWeek.values().length) + 1);
        }
    }

    public LocalDate getFirstDay() {


            return YearMonth.now(TZ).atDay(1);

    }

    public LocalDate getLastDay() {

            return YearMonth.now(TZ).atEndOfMonth();

    }

    @NonNull
    @Override
    public String toString() {
        return String.format(   "The %s week starts on %s and ends on %s",
                this.locale.getDisplayName(),
                this.firstDayOfWeek,
                this.lastDayOfWeek);
    }
}
