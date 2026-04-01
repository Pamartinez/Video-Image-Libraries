package com.samsung.android.gallery.module.remotegallery;

import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements MediaScannerListener {
    public final /* synthetic */ long d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ CountDownLatch f;

    public /* synthetic */ e(long j2, ArrayList arrayList, CountDownLatch countDownLatch) {
        this.d = j2;
        this.e = arrayList;
        this.f = countDownLatch;
    }

    public final void onCompleted() {
        ThreadUtil.postOnBgThreadDelayed(new f(this.d, this.e, this.f, 0), 1000);
    }
}
