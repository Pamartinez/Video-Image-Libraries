package com.samsung.android.sdk.mobileservice.social;

import android.content.Intent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SocialServiceState {
    public static final int SERVICE_END = 5;
    public static final int SERVICE_NORMAL = 1;
    public static final int SERVICE_NOTICE = 2;
    public static final int SERVICE_PAUSE = 4;
    public static final int SERVICE_REAGREE = 3;
    private Intent mIntent;
    private int mState;

    public SocialServiceState(int i2) {
        this.mIntent = null;
        this.mState = i2;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getState() {
        return this.mState;
    }

    public SocialServiceState(int i2, Intent intent) {
        this.mState = i2;
        this.mIntent = intent;
    }
}
