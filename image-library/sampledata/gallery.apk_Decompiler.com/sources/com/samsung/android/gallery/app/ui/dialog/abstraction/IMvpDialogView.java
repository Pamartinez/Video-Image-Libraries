package com.samsung.android.gallery.app.ui.dialog.abstraction;

import android.app.Activity;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMvpDialogView {
    void dismissDialog();

    Activity getActivity();

    Blackboard getBlackboard();

    void postAnalyticsLog(AnalyticsEventId analyticsEventId);

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str);
}
