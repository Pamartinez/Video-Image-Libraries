package com.samsung.android.gallery.module.thumbnail;

import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ ThumbnailLoader d;
    public final /* synthetic */ CountDownLatch e;

    public /* synthetic */ a(ThumbnailLoader thumbnailLoader, CountDownLatch countDownLatch) {
        this.d = thumbnailLoader;
        this.e = countDownLatch;
    }

    public final void run() {
        ThumbnailHandler.lambda$trimOriginalDecodingQueue$1(this.d, this.e);
    }
}
