package com.samsung.android.gallery.module.data;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScrollText {
    private String mDate = "";
    private String mDateRaw = "";
    private long mDateTaken;
    private String mLocation;

    public boolean equals(Object obj) {
        if (obj instanceof ScrollText) {
            ScrollText scrollText = (ScrollText) obj;
            if (!TextUtils.equals(this.mDate, scrollText.getDate()) || !TextUtils.equals(this.mLocation, scrollText.getLocation())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getDate() {
        if (TextUtils.isEmpty(this.mDate)) {
            this.mDate = TimeUtil.getDateString(this.mDateRaw, this.mDateTaken);
        }
        return this.mDate;
    }

    public long getDateTaken() {
        return this.mDateTaken;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public ScrollText setDate(String str) {
        this.mDate = str;
        return this;
    }

    public ScrollText setDateTaken(long j2) {
        this.mDateTaken = j2;
        return this;
    }

    public ScrollText setLocation(String str) {
        this.mLocation = str;
        return this;
    }
}
