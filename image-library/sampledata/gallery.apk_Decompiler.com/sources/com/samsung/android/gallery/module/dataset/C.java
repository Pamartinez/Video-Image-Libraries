package com.samsung.android.gallery.module.dataset;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ long g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaDataCursor f2941h;

    public /* synthetic */ C(MediaDataCursor mediaDataCursor, ArrayList arrayList, boolean z, long j2, int i2) {
        this.d = i2;
        this.f2941h = mediaDataCursor;
        this.e = arrayList;
        this.f = z;
        this.g = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataHideRule) this.f2941h).lambda$swap$0(this.e, this.f, this.g);
                return;
            case 1:
                ((MediaDataSuggestions) this.f2941h).lambda$swap$2(this.e, this.f, this.g);
                return;
            default:
                ((MediaDataSearchMyQueryCategory) this.f2941h).lambda$swap$0(this.e, this.f, this.g);
                return;
        }
    }
}
