package com.samsung.android.gallery.settings.ui.abstaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBaseView {
    Activity getActivity();

    Context getContext();

    String getScreenId();

    boolean isDestroyed();

    boolean onBackPressed() {
        return false;
    }

    boolean onHandleBroadcastEvent(Object obj, Bundle bundle);

    boolean onHandleEvent(Object obj, Bundle bundle);

    void postAnalyticsLog(String str);

    boolean setInputBlock(String str, int i2);
}
