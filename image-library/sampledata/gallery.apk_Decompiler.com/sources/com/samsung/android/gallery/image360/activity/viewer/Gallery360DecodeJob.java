package com.samsung.android.gallery.image360.activity.viewer;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Gallery360DecodeJob implements ThreadPool.Job<Bitmap> {
    private final String mFilePath;
    private final int mTargetImageSize;

    public Gallery360DecodeJob(String str, int i2) {
        this.mFilePath = str;
        this.mTargetImageSize = i2;
    }

    public Bitmap run(ThreadPool.JobContext jobContext) {
        return BitmapUtils.getDecodedBitmap(this.mFilePath, 0, this.mTargetImageSize, true);
    }
}
