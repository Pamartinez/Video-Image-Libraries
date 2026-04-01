package com.samsung.android.gallery.app.controller.viewer;

import U5.c;
import U9.b;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.photoview.AliveZoomScheduler;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveCaptureCompatCmd extends SaveCaptureCmd {
    private final Handler mResultHandler = new Handler();
    private final int mScale;
    private final int mSceneType;
    private boolean mUseNormalQuickCrop;

    public SaveCaptureCompatCmd(int i2, int i7) {
        this.mScale = i2;
        this.mSceneType = i7;
    }

    /* access modifiers changed from: private */
    public void OnLoadAiZoomCompleted(Bitmap bitmap) {
        this.mResultHandler.post(new c(14, this, bitmap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$OnLoadAiZoomCompleted$0(Bitmap bitmap) {
        try {
            saveCapturedFileWithBitmap(this.mMediaItem, bitmap);
        } catch (IOException e) {
            String str = this.TAG;
            Log.e(str, "saveCapturedFile failed e=" + e.getMessage());
            isNullThenSendEvent((Object) null);
        }
    }

    public Long getAnalyticsValue() {
        return Long.valueOf((long) this.mScale);
    }

    public String getEventId() {
        if (this.mUseNormalQuickCrop) {
            return super.getEventId();
        }
        return AnalyticsEventId.EVENT_QUICK_CROP_ALIVE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        if (this.mScale < 2) {
            z = true;
        } else {
            z = false;
        }
        this.mUseNormalQuickCrop = z;
        if (Features.isEnabled(Features.SUPPORT_PHOTO_REMASTER_UPSCALE_ULTRA)) {
            this.mUseNormalQuickCrop = true;
        }
        super.onExecute(eventContext, objArr);
    }

    public void saveCapturedFile(MediaItem mediaItem, File file, Rect rect) {
        Bitmap bitmap;
        if (this.mUseNormalQuickCrop) {
            super.saveCapturedFile(mediaItem, file, rect);
            return;
        }
        ByteBuffer quickCropStream = SeApiCompat.getQuickCropStream(file, rect);
        if (quickCropStream == null || !quickCropStream.hasArray()) {
            bitmap = null;
        } else {
            bitmap = BitmapUtils.getDecodedBitmap(quickCropStream.array(), 0);
        }
        Bitmap bitmap2 = bitmap;
        if (!isNullThenSendEvent(bitmap2)) {
            AliveZoomScheduler.getInstance().quickCropZoom(bitmap2, this.mScale, new Rect(0, 0, 0, 0), this.mSceneType, new b(6, this));
        }
    }
}
