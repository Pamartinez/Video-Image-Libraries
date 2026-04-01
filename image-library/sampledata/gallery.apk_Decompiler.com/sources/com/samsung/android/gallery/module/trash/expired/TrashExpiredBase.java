package com.samsung.android.gallery.module.trash.expired;

import A.a;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashExpiredBase implements TrashExpiredType {
    final int mDay = getDay();

    public abstract int getDay();

    public String getExpiredSimpleDate(long j2) {
        return TimeUtil.getIsoLocalDateTime(j2);
    }

    public long getExpiredTime() {
        return TimeUtil.getDaysAgo(this.mDay);
    }

    public String getExpiryTimeCondition(long j2) {
        return a.f("__deleteTime < ", j2);
    }
}
