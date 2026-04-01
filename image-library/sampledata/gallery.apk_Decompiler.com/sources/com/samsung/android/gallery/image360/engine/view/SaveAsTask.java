package com.samsung.android.gallery.image360.engine.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.engine.texture.StatusHandler;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.ExifUtils;
import java.io.File;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SaveAsTask extends BitmapTask {
    private final WeakReference<Context> mContext;
    private final String mSourceFilePath;
    private final String mTargetFilePath;

    public SaveAsTask(Context context, int i2, int i7, String str, StatusHandler statusHandler, String str2) {
        super(i2, i7, statusHandler);
        this.mContext = new WeakReference<>(context);
        this.mTargetFilePath = str;
        this.mSourceFilePath = str2;
    }

    public void onPostExecute(Object obj) {
        if (obj instanceof Boolean) {
            Context context = this.mContext.get();
            if (context != null) {
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(this.mTargetFilePath))));
                this.mStatusHandler.onSaveCompleted(this.mTargetFilePath, IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_SUCCESS);
                return;
            }
            Log.e("SaveAsTask", "null context");
            return;
        }
        Log.e("SaveAsTask", "File could not be saved. " + this.mTargetFilePath);
        this.mStatusHandler.onSaveCompleted(this.mTargetFilePath, IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_FAIL);
    }

    public Boolean doInBackground(int[]... iArr) {
        Bitmap bitmap = (Bitmap) super.doInBackground(iArr);
        if (bitmap == null) {
            return Boolean.FALSE;
        }
        boolean saveAsJpeg = BitmapUtils.saveAsJpeg(bitmap, this.mTargetFilePath, 100);
        ExifUtils.copyDateLocationIfAbsent(this.mSourceFilePath, this.mTargetFilePath);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return Boolean.valueOf(saveAsJpeg);
    }
}
