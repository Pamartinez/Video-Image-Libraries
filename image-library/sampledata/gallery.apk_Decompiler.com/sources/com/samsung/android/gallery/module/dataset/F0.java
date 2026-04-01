package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F0 implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ PppUpdater e;
    public final /* synthetic */ MediaDataRef f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2945h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2946i;

    public /* synthetic */ F0(PppUpdater pppUpdater, MediaItem mediaItem, MediaItem mediaItem2, MediaDataRef mediaDataRef, int i2) {
        this.e = pppUpdater;
        this.g = mediaItem;
        this.f2945h = mediaItem2;
        this.f = mediaDataRef;
        this.f2946i = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$replaceItem$0(this.g, this.f2945h, this.f, this.f2946i);
                return;
            default:
                this.e.lambda$updateItem$1(this.f, this.g, this.f2945h, this.f2946i);
                return;
        }
    }

    public /* synthetic */ F0(PppUpdater pppUpdater, MediaDataRef mediaDataRef, MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        this.e = pppUpdater;
        this.f = mediaDataRef;
        this.g = mediaItem;
        this.f2945h = mediaItem2;
        this.f2946i = i2;
    }
}
