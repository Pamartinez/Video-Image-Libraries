package com.samsung.android.sdk.scs.ai.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BoundaryDetector {
    private static final String TAG = "ScsApi@BoundaryDetector";
    private ImageServiceExecutor mServiceExecutor;

    public BoundaryDetector(Context context) {
        Log.d(TAG, "BoundaryDetector");
        this.mServiceExecutor = new ImageServiceExecutor(context);
    }

    public void close() {
        ImageServiceExecutor imageServiceExecutor = this.mServiceExecutor;
        if (imageServiceExecutor != null) {
            imageServiceExecutor.deInit();
        }
    }

    public Task<List<Boundary>> detect(Uri uri) {
        Log.d(TAG, "detect - Uri : " + uri.toString());
        DetectRunnable detectRunnable = new DetectRunnable(this.mServiceExecutor);
        detectRunnable.setUri(uri);
        this.mServiceExecutor.execute(detectRunnable);
        return detectRunnable.getTask();
    }

    public Task<Boundary> detectLargest(Uri uri) {
        Log.d(TAG, "detectLargest - Uri : " + uri.toString());
        DetectLargestRunnable detectLargestRunnable = new DetectLargestRunnable(this.mServiceExecutor);
        detectLargestRunnable.setUri(uri);
        this.mServiceExecutor.execute(detectLargestRunnable);
        return detectLargestRunnable.getTask();
    }

    public Task<List<Boundary>> detect(Bitmap bitmap) {
        Log.d(TAG, "detect(bitmap)");
        DetectRunnable detectRunnable = new DetectRunnable(this.mServiceExecutor);
        detectRunnable.setBitmap(bitmap);
        this.mServiceExecutor.execute(detectRunnable);
        return detectRunnable.getTask();
    }

    public Task<Boundary> detectLargest(Bitmap bitmap) {
        Log.d(TAG, "detectLargest(bitmap)");
        DetectLargestRunnable detectLargestRunnable = new DetectLargestRunnable(this.mServiceExecutor);
        detectLargestRunnable.setBitmap(bitmap);
        this.mServiceExecutor.execute(detectLargestRunnable);
        return detectLargestRunnable.getTask();
    }
}
