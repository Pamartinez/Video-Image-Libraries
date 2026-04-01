package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* renamed from: com.samsung.android.gallery.module.publisher.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0644y implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListDataPublisher e;
    public final /* synthetic */ QueryParams f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f3074h;

    public /* synthetic */ C0644y(ListDataPublisher listDataPublisher, QueryParams queryParams, Cursor[] cursorArr, long j2, int i2) {
        this.d = i2;
        this.e = listDataPublisher;
        this.f = queryParams;
        this.g = cursorArr;
        this.f3074h = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishTimelineData$4(this.f, this.g, this.f3074h);
                return;
            default:
                this.e.lambda$publishTimelineFakeData$2(this.f, this.g, this.f3074h);
                return;
        }
    }
}
