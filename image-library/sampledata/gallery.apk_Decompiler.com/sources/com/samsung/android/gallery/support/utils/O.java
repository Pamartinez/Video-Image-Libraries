package com.samsung.android.gallery.support.utils;

import java.lang.ref.SoftReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ O(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                SafeClipboard.lambda$getPrimaryClip$2((Boolean) obj);
                return;
            case 1:
                SafeClipboard.lambda$getPrimaryClipDescription$4((Boolean) obj);
                return;
            default:
                ((SoftReference) obj).clear();
                return;
        }
    }
}
