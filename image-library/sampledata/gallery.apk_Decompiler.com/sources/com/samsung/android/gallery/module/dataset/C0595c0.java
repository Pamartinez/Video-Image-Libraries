package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/* renamed from: com.samsung.android.gallery.module.dataset.c0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0595c0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaDataRef f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Serializable f2968h;

    public /* synthetic */ C0595c0(MediaDataRef mediaDataRef, Object obj, int i2, Serializable serializable, int i7) {
        this.d = i7;
        this.f = mediaDataRef;
        this.g = obj;
        this.e = i2;
        this.f2968h = serializable;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataRemasterV2) this.f).lambda$loadFirst$8((MediaDataRemasterV2.DataInfo) this.g, this.e, (HashSet) this.f2968h);
                return;
            case 1:
                ((MediaDataStoriesV7) this.f).lambda$loadDataPartial$8((ArrayList) this.g, (ArrayList) this.f2968h, this.e);
                return;
            default:
                ((MediaDataTimeline2) this.f).lambda$read$1((MediaItem[]) this.g, this.e, (String) this.f2968h);
                return;
        }
    }

    public /* synthetic */ C0595c0(MediaDataStoriesV7 mediaDataStoriesV7, ArrayList arrayList, ArrayList arrayList2, int i2) {
        this.d = 1;
        this.f = mediaDataStoriesV7;
        this.g = arrayList;
        this.f2968h = arrayList2;
        this.e = i2;
    }
}
