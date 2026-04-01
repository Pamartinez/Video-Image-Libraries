package com.samsung.android.sdk.mobileservice.social.share;

import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Quota {
    private boolean mIsUnlimited;
    private long mLimitCountUsage;
    private LimitType mLimitType;
    private long mLimitUsage;
    private long mTotalCountUsage;
    private long mTotalUsage;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LimitType {
        SIZE("size"),
        COUNT("count"),
        UNLIMITED("unlimited");
        
        private String limitType;

        private LimitType(String str) {
            this.limitType = str;
        }

        public static LimitType toLimitType(String str) {
            if (TextUtils.isEmpty(str)) {
                return SIZE;
            }
            for (LimitType limitType2 : values()) {
                if (str.equals(limitType2.getLimitType())) {
                    return limitType2;
                }
            }
            return SIZE;
        }

        public String getLimitType() {
            return this.limitType;
        }
    }

    public Quota(long j2, long j3, boolean z) {
        this.mTotalCountUsage = -1;
        this.mLimitCountUsage = -1;
        this.mLimitType = LimitType.SIZE;
        this.mTotalUsage = j2;
        this.mLimitUsage = j3;
        this.mIsUnlimited = z;
    }

    public long getLimitCountUsage() {
        return this.mLimitCountUsage;
    }

    public LimitType getLimitType() {
        return this.mLimitType;
    }

    public long getLimitUsage() {
        return this.mLimitUsage;
    }

    public long getTotalCountUsage() {
        return this.mTotalCountUsage;
    }

    public long getTotalUsage() {
        return this.mTotalUsage;
    }

    @Deprecated
    public boolean isUnlimited() {
        return this.mIsUnlimited;
    }

    public Quota(LimitType limitType, long j2, long j3, long j8, long j10, boolean z) {
        this.mTotalCountUsage = -1;
        this.mLimitCountUsage = -1;
        LimitType limitType2 = LimitType.SIZE;
        this.mLimitType = limitType;
        this.mTotalUsage = j2;
        this.mLimitUsage = j3;
        this.mTotalCountUsage = j8;
        this.mLimitCountUsage = j10;
        this.mIsUnlimited = z;
    }
}
