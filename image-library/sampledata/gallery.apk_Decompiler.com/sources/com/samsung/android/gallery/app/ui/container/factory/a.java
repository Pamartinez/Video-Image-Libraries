package com.samsung.android.gallery.app.ui.container.factory;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomTabChildFragmentFactoryImpl e;

    public /* synthetic */ a(BottomTabChildFragmentFactoryImpl bottomTabChildFragmentFactoryImpl, int i2) {
        this.d = i2;
        this.e = bottomTabChildFragmentFactoryImpl;
    }

    public final Object get() {
        int i2 = this.d;
        BottomTabChildFragmentFactoryImpl bottomTabChildFragmentFactoryImpl = this.e;
        switch (i2) {
            case 0:
                return bottomTabChildFragmentFactoryImpl.createTimelineCompat();
            case 1:
                return bottomTabChildFragmentFactoryImpl.createAlbumsCompat();
            case 2:
                return bottomTabChildFragmentFactoryImpl.createStoriesCompat();
            case 3:
                return bottomTabChildFragmentFactoryImpl.createCollectionCompat();
            default:
                return bottomTabChildFragmentFactoryImpl.createMtpCompat();
        }
    }
}
