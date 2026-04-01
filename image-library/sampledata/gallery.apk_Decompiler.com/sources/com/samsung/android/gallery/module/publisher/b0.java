package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b0 implements Runnable {
    public final /* synthetic */ SearchDataPublisherV2 d;
    public final /* synthetic */ Cursor[][] e;
    public final /* synthetic */ long f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bundle f3061h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f3062i;

    public /* synthetic */ b0(SearchDataPublisherV2 searchDataPublisherV2, Cursor[][] cursorArr, long j2, String str, Bundle bundle, String str2) {
        this.d = searchDataPublisherV2;
        this.e = cursorArr;
        this.f = j2;
        this.g = str;
        this.f3061h = bundle;
        this.f3062i = str2;
    }

    public final void run() {
        this.d.lambda$publishWithoutFilterRequest$1(this.e, this.f, this.g, this.f3061h, this.f3062i);
    }
}
