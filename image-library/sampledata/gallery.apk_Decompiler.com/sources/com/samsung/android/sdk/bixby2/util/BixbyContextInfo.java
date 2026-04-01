package com.samsung.android.sdk.bixby2.util;

import android.os.Bundle;
import com.samsung.android.sdk.bixby2.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyContextInfo {
    private static final String TAG = "BixbyContextInfo";
    private final String BIXBY_CLIENT_TASK_ID;
    private final String IS_MEDIA_CONTROL_ACTIVE;
    private final String IS_MUSIC_ACTIVE;
    private final String LOCALE;
    private Integer bixbyClientTaskId;
    private final boolean isMediaControlActive;
    private final boolean isMusicActive;
    private final String locale;

    public BixbyContextInfo(Bundle bundle) {
        this.LOCALE = "locale";
        this.IS_MUSIC_ACTIVE = "isMusicActive";
        this.IS_MEDIA_CONTROL_ACTIVE = "isMediaControlActive";
        this.BIXBY_CLIENT_TASK_ID = "bixbyClient_taskId";
        String string = bundle.getString("locale", "");
        this.locale = string;
        boolean z = bundle.getBoolean("isMusicActive", false);
        this.isMusicActive = z;
        boolean z3 = bundle.getBoolean("isMediaControlActive", false);
        this.isMediaControlActive = z3;
        if (bundle.containsKey("bixbyClient_taskId")) {
            this.bixbyClientTaskId = Integer.valueOf(bundle.getInt("bixbyClient_taskId"));
        }
        LogUtil.d(TAG, "BixbyContextInfo() :: locale - " + string + ", isMusicActive - " + z + ", isMediaControlActive - " + z3);
    }

    public Integer getBixbyClientTaskId() {
        return this.bixbyClientTaskId;
    }

    public String getLocale() {
        return this.locale;
    }

    public boolean isMediaControlActive() {
        return this.isMediaControlActive;
    }

    public boolean isMusicActive() {
        return this.isMusicActive;
    }

    public BixbyContextInfo() {
        this.LOCALE = "locale";
        this.IS_MUSIC_ACTIVE = "isMusicActive";
        this.IS_MEDIA_CONTROL_ACTIVE = "isMediaControlActive";
        this.BIXBY_CLIENT_TASK_ID = "bixbyClient_taskId";
        this.locale = "";
        this.isMusicActive = false;
        this.isMediaControlActive = false;
    }
}
