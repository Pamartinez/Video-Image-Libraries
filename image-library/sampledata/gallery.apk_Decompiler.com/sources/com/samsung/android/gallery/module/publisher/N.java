package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class N implements Runnable {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ Cursor[][] e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Bundle g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f3049h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f3050i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ long f3051j;
    public final /* synthetic */ String k;

    public /* synthetic */ N(SearchDataPublisher searchDataPublisher, Cursor[][] cursorArr, boolean z, Bundle bundle, AtomicReference atomicReference, String str, long j2, String str2) {
        this.d = searchDataPublisher;
        this.e = cursorArr;
        this.f = z;
        this.g = bundle;
        this.f3049h = atomicReference;
        this.f3050i = str;
        this.f3051j = j2;
        this.k = str2;
    }

    public final void run() {
        this.d.lambda$publishWithFilterRequest$3(this.e, this.f, this.g, this.f3049h, this.f3050i, this.f3051j, this.k);
    }
}
