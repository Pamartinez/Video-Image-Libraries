package com.samsung.android.gallery.app.ui.container.factory;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new ListDrawerChildFragmentFactoryImpl();
            case 1:
                return new BottomTabChildFragmentFactoryImpl();
            default:
                return new BottomTabPickerChildFragmentFactoryImpl();
        }
    }
}
