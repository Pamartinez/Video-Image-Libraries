package com.samsung.android.gallery.app.ui.map.factory;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements InstantSubscriberListener {
    public final /* synthetic */ Fragment d;
    public final /* synthetic */ FragmentActivity e;
    public final /* synthetic */ EventContext f;

    public /* synthetic */ a(Fragment fragment, FragmentActivity fragmentActivity, EventContext eventContext) {
        this.d = fragment;
        this.e = fragmentActivity;
        this.f = eventContext;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        GalleryMapFactory.lambda$downloadGalleryPlugins$1(this.d, this.e, this.f, obj, bundle);
    }
}
