package com.samsung.android.gallery.support.utils;

import android.content.Context;
import com.samsung.android.gallery.support.utils.SystemEnvironment;

/* renamed from: com.samsung.android.gallery.support.utils.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0667e implements SystemEnvironment.EnvironmentChangeListener {
    public final void onEnvironmentChange(Context context) {
        BucketUtils.initialize();
    }
}
