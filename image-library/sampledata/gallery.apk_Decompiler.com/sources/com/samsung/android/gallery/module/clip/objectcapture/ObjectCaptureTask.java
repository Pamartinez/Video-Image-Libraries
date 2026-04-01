package com.samsung.android.gallery.module.clip.objectcapture;

import A.a;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.clip.IClipInfo;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import x7.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureTask implements Runnable {
    private final IClipInfo mClipInfo;
    private final OnCaptureListener mListener;
    private final ObjectCaptureHelper mObjectCaptureHelper;
    private final float mX;
    private final float mY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCaptureListener {
    }

    public ObjectCaptureTask(ObjectCaptureHelper objectCaptureHelper, IClipInfo iClipInfo, float f, float f5, OnCaptureListener onCaptureListener) {
        this.mObjectCaptureHelper = objectCaptureHelper;
        this.mClipInfo = iClipInfo;
        this.mX = f;
        this.mY = f5;
        this.mListener = onCaptureListener;
    }

    public void run() {
        Trace.beginSection("ObjectCaptureTask run ");
        if (DeepSkyHelper.isObjectCaptureReleased()) {
            Log.d("ObjectCaptureTask", "object capture task skipped, object capture is already released");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ObjectCaptureHelper objectCaptureHelper = this.mObjectCaptureHelper;
        if (objectCaptureHelper == null) {
            objectCaptureHelper = new ObjectCaptureHelper(this.mClipInfo);
        }
        Bitmap lowBitmap = this.mClipInfo.getLowBitmap();
        if (lowBitmap == null || lowBitmap.isRecycled()) {
            Log.e("ObjectCaptureTask", "object capture task failed, bitmap is not valid");
        } else {
            objectCaptureHelper.setInitPoint(this.mX, this.mY);
            objectCaptureHelper.capture(lowBitmap);
        }
        a.A(new Object[]{MediaItemUtil.getSimpleLog(this.mClipInfo.getMediaItem()), Long.valueOf(currentTimeMillis)}, new StringBuilder("task#run"), "ObjectCaptureTask");
        OnCaptureListener onCaptureListener = this.mListener;
        if (onCaptureListener != null) {
            e eVar = (e) onCaptureListener;
            eVar.f2746a.lambda$onCapture$4(eVar.b, eVar.f2747c, objectCaptureHelper);
        }
        Trace.endSection();
    }
}
