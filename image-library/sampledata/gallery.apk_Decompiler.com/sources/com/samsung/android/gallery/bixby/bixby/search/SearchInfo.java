package com.samsung.android.gallery.bixby.bixby.search;

import N2.j;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchInfo implements Serializable {
    private String mContentType = "";
    private String mCountry = "";
    private String mCountryCode = "";
    private long mFrom = 0;
    private String mLocation = "";
    private String mOrderType = "";
    private String mPoi = "";
    private String mTag = "";
    private String mTime = "";
    private String mTitle = "";
    private long mTo = 0;
    private String mUri = null;
    private String mUtterance = "";

    private long calculateTime(long j2, Calendar calendar, Calendar calendar2) {
        if (Features.isEnabled(Features.SUPPORT_BIXBY_LOCAL_TIME_SEARCH)) {
            j2 = TimeUtil.toLocalTimeInMillis(j2);
        }
        calendar.setTimeInMillis(j2);
        Calendar calendar3 = calendar2;
        calendar3.set(calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
        calendar3.set(14, calendar.get(14));
        return calendar3.getTimeInMillis();
    }

    private void convertTimeValue(long j2, long j3, Calendar calendar, Calendar calendar2) {
        long j8;
        StringBuilder j10 = j.j(j2, "server: from - ", ", to - ");
        j10.append(j3);
        Log.bx("SearchInfo", j10.toString());
        if (j2 == j3) {
            long calculateTime = calculateTime(j2, calendar, calendar2);
            this.mFrom = calculateTime;
            if (calendar2.get(12) == 0) {
                j8 = 3540000;
            } else {
                j8 = 59000;
            }
            this.mTo = calculateTime + j8;
        } else {
            this.mFrom = calculateTime(j2, calendar, calendar2);
            this.mTo = calculateTime(j3, calendar, calendar2);
        }
        Log.bx("SearchInfo", "ut zone: from - " + this.mFrom + ", mTo - " + this.mTo);
    }

    public String getContentType() {
        return this.mContentType;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public long getFrom() {
        return this.mFrom;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public String getOrderType() {
        return this.mOrderType;
    }

    public String getPoi() {
        return this.mPoi;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getTime() {
        return this.mTime;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public long getTo() {
        return this.mTo;
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public boolean isCountryFirst() {
        if (TextUtils.isEmpty(this.mUri) || this.mUri.indexOf("KEY_COUNTRY") >= this.mUri.indexOf("KEY_LOCATION")) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        if (!this.mLocation.isEmpty() || !this.mPoi.isEmpty() || !this.mTitle.isEmpty() || !this.mTag.isEmpty() || !this.mContentType.isEmpty() || !this.mCountry.isEmpty() || !this.mTime.isEmpty() || !this.mUtterance.isEmpty()) {
            return false;
        }
        return true;
    }

    public void setContentType(String str) {
        if (str != null) {
            this.mContentType = str;
        }
    }

    public void setCountry(String str, String str2) {
        if (str != null) {
            this.mCountry = str;
        }
        if (str2 != null) {
            this.mCountryCode = str2;
        }
    }

    public void setLocation(String str) {
        if (str != null) {
            this.mLocation = str;
        }
    }

    public void setOrderType(String str) {
        if (str != null) {
            this.mOrderType = str;
        }
    }

    public void setPoi(String str) {
        if (str != null) {
            this.mPoi = str;
        }
    }

    public void setTag(String str) {
        if (str != null) {
            this.mTag = str;
        }
    }

    public void setTime(String str) {
        if (str != null) {
            this.mTime = str;
        }
    }

    public void setTimeInfo(long[] jArr) {
        convertTimeValue(jArr[0], jArr[1], Calendar.getInstance(TimeZone.getDefault()), Calendar.getInstance(TimeZone.getDefault()));
    }

    public void setTitle(String str) {
        if (str != null) {
            this.mTitle = str;
        }
    }

    public void setUri(String str) {
        this.mUri = str;
    }

    public void setUtterance(String str) {
        if (str != null) {
            this.mUtterance = str;
        }
    }
}
