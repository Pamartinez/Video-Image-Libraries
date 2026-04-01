package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.List;

/* renamed from: com.samsung.android.gallery.module.dataset.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0613s implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaDataRef f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0613s(MediaDataRef mediaDataRef, Object obj, int i2, int i7) {
        this.d = i7;
        this.f = mediaDataRef;
        this.g = obj;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataCollection) this.f).lambda$readAsync$4((MediaData.OnDataReadListener) this.g, this.e);
                return;
            default:
                ((MediaDataStoriesCategory) this.f).lambda$swap$0((List) this.g, this.e);
                return;
        }
    }
}
