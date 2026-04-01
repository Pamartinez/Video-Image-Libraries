package com.samsung.android.sdk;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SsdkInterface {
    int getVersionCode();

    String getVersionName();

    void initialize(Context context);

    boolean isFeatureEnabled(int i2);
}
