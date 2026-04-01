package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z0 implements InstantSubscriberListener {
    public final /* synthetic */ MediaDataTimeline d;
    public final /* synthetic */ Cursor[] e;

    public /* synthetic */ z0(MediaDataTimeline mediaDataTimeline, Cursor[] cursorArr) {
        this.d = mediaDataTimeline;
        this.e = cursorArr;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$swap$6(this.e, obj, bundle);
    }
}
