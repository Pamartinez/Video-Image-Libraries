package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[][] e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f3046h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Bundle f3047i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ SearchDataPublisher f3048j;

    public /* synthetic */ M(SearchDataPublisher searchDataPublisher, Cursor[][] cursorArr, String str, String str2, String str3, Bundle bundle, int i2) {
        this.d = i2;
        this.f3048j = searchDataPublisher;
        this.e = cursorArr;
        this.f = str;
        this.g = str2;
        this.f3046h = str3;
        this.f3047i = bundle;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.f3048j.lambda$publishWithFilterRequest$2(this.e, this.f, this.g, this.f3046h, this.f3047i);
                return;
            default:
                ((SearchDataPublisherV2) this.f3048j).lambda$publishWithoutFilterRequest$0(this.e, this.f, this.g, this.f3046h, this.f3047i);
                return;
        }
    }
}
