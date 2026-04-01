package com.samsung.android.gallery.bixby.bixbycard;

import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsDataFetcher {
    public long getTodayEndTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return instance.getTimeInMillis();
    }

    public long getTodayStartTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }
}
