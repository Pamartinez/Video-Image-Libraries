package com.adobe.internal.xmp;

import com.adobe.internal.xmp.impl.XMPDateTimeImpl;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPDateTimeFactory {
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    private XMPDateTimeFactory() {
    }

    public static XMPDateTime convertToLocalTime(XMPDateTime xMPDateTime) {
        long timeInMillis = xMPDateTime.getCalendar().getTimeInMillis();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMillis);
        return new XMPDateTimeImpl((Calendar) gregorianCalendar);
    }

    public static XMPDateTime convertToUTCTime(XMPDateTime xMPDateTime) {
        long timeInMillis = xMPDateTime.getCalendar().getTimeInMillis();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(UTC);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeInMillis(timeInMillis);
        return new XMPDateTimeImpl((Calendar) gregorianCalendar);
    }

    public static XMPDateTime create() {
        return new XMPDateTimeImpl();
    }

    public static XMPDateTime createFromCalendar(Calendar calendar) {
        return new XMPDateTimeImpl(calendar);
    }

    public static XMPDateTime createFromISO8601(String str) {
        return new XMPDateTimeImpl(str);
    }

    public static XMPDateTime getCurrentDateTime() {
        return new XMPDateTimeImpl((Calendar) new GregorianCalendar());
    }

    public static XMPDateTime setLocalTimeZone(XMPDateTime xMPDateTime) {
        Calendar calendar = xMPDateTime.getCalendar();
        calendar.setTimeZone(TimeZone.getDefault());
        return new XMPDateTimeImpl(calendar);
    }

    public static XMPDateTime create(int i2, int i7, int i8) {
        XMPDateTimeImpl xMPDateTimeImpl = new XMPDateTimeImpl();
        xMPDateTimeImpl.setYear(i2);
        xMPDateTimeImpl.setMonth(i7);
        xMPDateTimeImpl.setDay(i8);
        return xMPDateTimeImpl;
    }

    public static XMPDateTime create(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        XMPDateTimeImpl xMPDateTimeImpl = new XMPDateTimeImpl();
        xMPDateTimeImpl.setYear(i2);
        xMPDateTimeImpl.setMonth(i7);
        xMPDateTimeImpl.setDay(i8);
        xMPDateTimeImpl.setHour(i10);
        xMPDateTimeImpl.setMinute(i11);
        xMPDateTimeImpl.setSecond(i12);
        xMPDateTimeImpl.setNanoSecond(i13);
        return xMPDateTimeImpl;
    }
}
