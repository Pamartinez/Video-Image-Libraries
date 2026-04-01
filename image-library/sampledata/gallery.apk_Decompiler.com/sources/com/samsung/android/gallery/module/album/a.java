package com.samsung.android.gallery.module.album;

import android.content.Context;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f2919a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2920c;
    public final /* synthetic */ CountDownLatch d;
    public final /* synthetic */ String[] e;

    public /* synthetic */ a(String str, Context context, int i2, CountDownLatch countDownLatch, String[] strArr) {
        this.f2919a = str;
        this.b = context;
        this.f2920c = i2;
        this.d = countDownLatch;
        this.e = strArr;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return AlbumDelegate.lambda$saveCoverFile$0(this.f2919a, this.b, this.f2920c, this.d, this.e, jobContext);
    }
}
