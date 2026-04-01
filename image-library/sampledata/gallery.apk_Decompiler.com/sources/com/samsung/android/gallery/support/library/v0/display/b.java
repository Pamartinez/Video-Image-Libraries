package com.samsung.android.gallery.support.library.v0.display;

import com.samsung.android.gallery.support.library.abstraction.DisplayStatusListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ b(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int i7 = this.e;
        DisplayStatusListener displayStatusListener = (DisplayStatusListener) obj;
        switch (i2) {
            case 0:
                displayStatusListener.onScreenSharingStatusChanged(i7);
                return;
            case 1:
                displayStatusListener.onQosLevelChanged(i7);
                return;
            default:
                displayStatusListener.onConnectionStatusChanged(i7);
                return;
        }
    }
}
