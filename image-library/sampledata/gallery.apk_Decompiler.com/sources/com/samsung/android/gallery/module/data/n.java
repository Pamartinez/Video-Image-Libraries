package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.module.data.StoriesPinData;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPinData.PinData e;

    public /* synthetic */ n(StoriesPinData.PinData pinData, int i2) {
        this.d = i2;
        this.e = pinData;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoriesPinData.PinData pinData = this.e;
        switch (i2) {
            case 0:
                pinData.lambda$build$0((List) obj);
                return;
            default:
                pinData.append((MediaItem) obj);
                return;
        }
    }
}
