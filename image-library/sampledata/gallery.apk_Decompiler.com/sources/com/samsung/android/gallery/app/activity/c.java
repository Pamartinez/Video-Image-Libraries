package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.widget.bottom.BottomBar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BottomBar.OnStateChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GalleryActivityHandler.BottomBarHandlerImpl f2493a;

    public /* synthetic */ c(GalleryActivityHandler.BottomBarHandlerImpl bottomBarHandlerImpl) {
        this.f2493a = bottomBarHandlerImpl;
    }

    public final void a(boolean z) {
        this.f2493a.onStateChange(z);
    }
}
