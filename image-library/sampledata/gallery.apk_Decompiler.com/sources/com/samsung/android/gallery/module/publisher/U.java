package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class U implements FutureListener {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ Integer e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f3055h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f3056i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ String f3057j;
    public final /* synthetic */ boolean k;

    public /* synthetic */ U(long j2, SearchDataPublisher searchDataPublisher, Integer num, String str, String str2, CountDownLatch countDownLatch, boolean z, Cursor[] cursorArr) {
        this.d = searchDataPublisher;
        this.e = num;
        this.f = str;
        this.g = cursorArr;
        this.f3055h = countDownLatch;
        this.f3056i = j2;
        this.f3057j = str2;
        this.k = z;
    }

    public final void onFutureDone(Future future) {
        this.d.lambda$publishCategoriesData$4(this.e, this.f, this.g, this.f3055h, this.f3056i, this.f3057j, this.k, future);
    }
}
