package com.adobe.internal.xmp;

import java.util.Calendar;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface XMPDateTime extends Comparable {
    Calendar getCalendar();

    int getDay();

    int getHour();

    String getISO8601String();

    int getMinute();

    int getMonth();

    int getNanoSecond();

    int getSecond();

    TimeZone getTimeZone();

    int getYear();

    boolean hasDate();

    boolean hasTime();

    boolean hasTimeZone();

    void setDay(int i2);

    void setHour(int i2);

    void setMinute(int i2);

    void setMonth(int i2);

    void setNanoSecond(int i2);

    void setSecond(int i2);

    void setTimeZone(TimeZone timeZone);

    void setYear(int i2);
}
