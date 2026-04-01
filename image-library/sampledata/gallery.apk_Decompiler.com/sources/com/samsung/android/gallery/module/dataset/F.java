package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Runnable {
    public final /* synthetic */ MediaDataList.MediaDataArray d;
    public final /* synthetic */ ConcurrentHashMap e;
    public final /* synthetic */ int f;
    public final /* synthetic */ boolean g;

    public /* synthetic */ F(MediaDataList.MediaDataArray mediaDataArray, ConcurrentHashMap concurrentHashMap, int i2, boolean z) {
        this.d = mediaDataArray;
        this.e = concurrentHashMap;
        this.f = i2;
        this.g = z;
    }

    public final void run() {
        this.d.lambda$notifyDataChanged$1(this.e, this.f, this.g);
    }
}
