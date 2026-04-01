package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.MediaScanner;
import java.util.function.IntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3160a;

    public /* synthetic */ D(int i2) {
        this.f3160a = i2;
    }

    public final Object apply(int i2) {
        switch (this.f3160a) {
            case 0:
                return MediaScanner.FileMediaScanner.lambda$scan$0(i2);
            case 1:
                return MediaScanner.lambda$scanFiles$1(i2);
            default:
                return String.valueOf(i2);
        }
    }
}
