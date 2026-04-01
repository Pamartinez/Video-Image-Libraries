package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchDataPublisher f3052a;
    public final /* synthetic */ Cursor[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f3053c;
    public final /* synthetic */ long d;
    public final /* synthetic */ String e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ P(SearchDataPublisher searchDataPublisher, Cursor[] cursorArr, CountDownLatch countDownLatch, long j2, String str, boolean z) {
        this.f3052a = searchDataPublisher;
        this.b = cursorArr;
        this.f3053c = countDownLatch;
        this.d = j2;
        this.e = str;
        this.f = z;
    }

    public final void accept(Object obj, Object obj2) {
        SearchDataPublisher.q0(this.d, this.f3052a, (Integer) obj, this.e, (String) obj2, this.f3053c, this.f, this.b);
    }
}
