package com.samsung.android.gallery.module.data;

import android.text.TextUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataStamp {
    private static final boolean SUPPORT_DETAILS = SdkConfig.atLeast(SdkConfig.GED.T);
    long cloudTimeStamp;
    int count;
    long id;
    String mRaw;
    long modified;
    int pppCount;

    public DataStamp(String str) {
        this.mRaw = str;
        if (SUPPORT_DETAILS && !TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            if (split.length > 1) {
                this.count = UnsafeCast.toInt(split[0]);
                this.id = UnsafeCast.toLong(split[1]);
            }
            if (split.length == 5) {
                this.cloudTimeStamp = UnsafeCast.toLong(split[2]);
                this.modified = UnsafeCast.toLong(split[3]);
                this.pppCount = UnsafeCast.toInt(split[4]);
            } else if (split.length == 4) {
                this.modified = UnsafeCast.toLong(split[2]);
                this.pppCount = UnsafeCast.toInt(split[3]);
            }
        }
    }

    public boolean equals(DataStamp dataStamp) {
        if (dataStamp == null || !this.mRaw.equals(dataStamp.mRaw)) {
            return false;
        }
        return true;
    }

    public String getRaw() {
        return this.mRaw;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.mRaw);
    }

    public boolean isPppChanged(DataStamp dataStamp) {
        if (SUPPORT_DETAILS && dataStamp != null && dataStamp.count == this.count && dataStamp.id == this.id && this.pppCount - dataStamp.pppCount == 1) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.mRaw;
    }
}
