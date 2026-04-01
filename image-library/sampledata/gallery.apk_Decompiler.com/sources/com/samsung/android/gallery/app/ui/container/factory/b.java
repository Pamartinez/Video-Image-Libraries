package com.samsung.android.gallery.app.ui.container.factory;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ChildFragmentFactory e;

    public /* synthetic */ b(ChildFragmentFactory childFragmentFactory, int i2) {
        this.d = i2;
        this.e = childFragmentFactory;
    }

    public final Object get() {
        int i2 = this.d;
        ChildFragmentFactory childFragmentFactory = this.e;
        switch (i2) {
            case 0:
                return ((BottomTabPickerChildFragmentFactoryImpl) childFragmentFactory).createSearchPicturesPickerCompat();
            default:
                return ((ListDrawerChildFragmentFactoryImpl) childFragmentFactory).createSharingCompat();
        }
    }
}
