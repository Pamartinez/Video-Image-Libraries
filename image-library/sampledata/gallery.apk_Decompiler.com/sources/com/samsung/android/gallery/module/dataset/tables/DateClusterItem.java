package com.samsung.android.gallery.module.dataset.tables;

import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DateClusterItem extends AbsClusterItem {
    public static int DAY = 0;
    public static int MONTH = 1;
    public static int YEAR = 2;
    private List<String> mAddressList;
    private String mAddressResult;
    protected int mClusterType;
    protected String mDate;
    private int mFinalCount;
    private String mFromItemDateRaw;
    private String mLatListString;
    private String mLongListString;
    private long mOldItemDateTaken;
    private String mToItemDateRaw;

    public DateClusterItem(MediaItem mediaItem, int i2, List<String> list, int i7, String str, String str2) {
        this.mAddressResult = "";
        this.mOldItemDateTaken = mediaItem.getDateTaken();
        String dateRaw = mediaItem.getDateRaw();
        this.mFromItemDateRaw = dateRaw;
        this.mToItemDateRaw = dateRaw;
        this.mClusterType = i2;
        this.mAddressList = list;
        this.mFinalCount = i7;
        this.mLatListString = str;
        this.mLongListString = str2;
        this.mDate = makeDate(mediaItem.getDate());
    }

    private long getDateTimeMillis(String[] strArr) {
        return getTimeOfDay(TimeUtil.getDateTimeMillis(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]) - 1, Integer.parseInt(strArr[2]), 0, 0, 0));
    }

    private long getTimeOfDay(long j2) {
        return (j2 / 1000) * 1000;
    }

    public int getCount() {
        return this.mFinalCount;
    }

    public String getDate() {
        return this.mDate;
    }

    public String getDateRaw() {
        if (PreferenceFeatures.OneUi30.YEAR_CLUSTERING) {
            return this.mToItemDateRaw;
        }
        return "";
    }

    public Pair<String, String> getDateRawRange() {
        return new Pair<>(this.mFromItemDateRaw, this.mToItemDateRaw);
    }

    public long getDateTaken() {
        return this.mOldItemDateTaken;
    }

    public Pair<Long, Long> getDateTimeRange() {
        int i2 = this.mClusterType;
        if (i2 == MONTH) {
            long[] yearMonthInMills = TimeUtil.getYearMonthInMills(this.mOldItemDateTaken);
            return new Pair<>(Long.valueOf(yearMonthInMills[0]), Long.valueOf(yearMonthInMills[1]));
        } else if (i2 == YEAR) {
            long[] yearInMills = TimeUtil.getYearInMills(this.mOldItemDateTaken);
            return new Pair<>(Long.valueOf(yearInMills[0]), Long.valueOf(yearInMills[1]));
        } else {
            long dateTimeMillis = getDateTimeMillis(this.mFromItemDateRaw.split("-"));
            long dateTimeMillis2 = getDateTimeMillis(this.mToItemDateRaw.split("-"));
            return new Pair<>(Long.valueOf(Math.min(dateTimeMillis, dateTimeMillis2)), Long.valueOf(Math.max(dateTimeMillis, dateTimeMillis2) + 86399999));
        }
    }

    public String getLatitudeList() {
        return this.mLatListString;
    }

    public String getLocation() {
        String str;
        String str2;
        if (TextUtils.isEmpty(this.mAddressResult)) {
            if (this.mAddressList.isEmpty()) {
                str = "";
            } else {
                str = this.mAddressList.get(0);
            }
            Features features = Features.IS_JAPAN_DEVICE;
            String str3 = Features.isEnabled(features) ? "、" : GlobalPostProcInternalPPInterface.SPLIT_REGEX;
            if (Features.isEnabled(features)) {
                str2 = "...";
            } else {
                str2 = "&...";
            }
            if (this.mAddressList.size() != 1 || TextUtils.isEmpty(str) || str.endsWith(str2) || !str.contains(str3)) {
                this.mAddressResult = MediaItemBuilder.makeAddressString(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mAddressList));
            } else {
                this.mAddressResult = C0212a.p(new StringBuilder(), this.mAddressList.get(0), str2);
            }
            this.mAddressList.clear();
        }
        return this.mAddressResult;
    }

    public String getLongitudeList() {
        return this.mLongListString;
    }

    public boolean isMonth() {
        if (this.mClusterType == MONTH) {
            return true;
        }
        return false;
    }

    public String makeDate(String str) {
        int i2 = this.mClusterType;
        if (i2 == DAY) {
            return str;
        }
        if (i2 == MONTH) {
            return TimeUtil.toLocalDate(this.mOldItemDateTaken, "YM");
        }
        return TimeUtil.toLocalDate(this.mOldItemDateTaken, "Y");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MediaItem{d=");
        sb2.append(getDate());
        sb2.append(", dt=");
        sb2.append(getDateTaken());
        sb2.append(", c=");
        sb2.append(getCount());
        sb2.append(", tdr=");
        sb2.append(this.mToItemDateRaw);
        sb2.append(", fdr=");
        return C0212a.p(sb2, this.mFromItemDateRaw, "}");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DateClusterItem(ArrayList<MediaItem> arrayList, int i2, List<String> list, int i7, String str, String str2) {
        this(arrayList.get(0), i2, list, i7, str, str2);
        if (this.mClusterType == DAY && arrayList.size() > 1) {
            long dateTaken = arrayList.get(0).getDateTaken();
            long dateTaken2 = ((MediaItem) C0212a.i(arrayList, 1)).getDateTaken();
            this.mDate = TimeUtil.getEventDatePeriod(Math.min(dateTaken2, dateTaken), Math.max(dateTaken2, dateTaken), 50, !TimeUtil.isInThisYear(getDateTaken()));
            this.mFromItemDateRaw = ((MediaItem) C0212a.i(arrayList, 1)).getDateRaw();
        }
    }
}
