package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class D {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicReference f1449a = new AtomicReference();

    public static Calendar a(Calendar calendar) {
        Calendar c5 = c(calendar);
        Calendar c6 = c((Calendar) null);
        c6.set(c5.get(1), c5.get(2), c5.get(5));
        return c6;
    }

    public static Calendar b() {
        C c5 = (C) f1449a.get();
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.setTimeZone(TimeZone.getTimeZone("UTC"));
        return instance;
    }

    public static Calendar c(Calendar calendar) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        if (calendar == null) {
            instance.clear();
            return instance;
        }
        instance.setTimeInMillis(calendar.getTimeInMillis());
        return instance;
    }
}
