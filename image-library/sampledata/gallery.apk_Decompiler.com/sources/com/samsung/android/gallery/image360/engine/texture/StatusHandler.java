package com.samsung.android.gallery.image360.engine.texture;

import N2.j;
import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StatusHandler {
    private Consumer<Bitmap> mBitmapCreatedListener;
    private int mCurrentErrorCode = 0;
    private Callable<Void> mErrorListener;
    private Callable<Void> mSaveCompletedListener;
    private final HashMap<String, IGallery360Viewer.SaveStatus> mSaveStatus = new HashMap<>();

    public int getErrorCode() {
        int i2 = this.mCurrentErrorCode;
        this.mCurrentErrorCode = 0;
        return i2;
    }

    public IGallery360Viewer.SaveStatus getSaveStatus(String str) {
        if (str == null || str.isEmpty() || !this.mSaveStatus.containsKey(str)) {
            return IGallery360Viewer.SaveStatus.INVALID_FILE_NAME;
        }
        IGallery360Viewer.SaveStatus saveStatus = this.mSaveStatus.get(str);
        if (saveStatus != IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_PROGRESS) {
            this.mSaveStatus.remove(str);
        }
        return saveStatus;
    }

    public void onBitmapCreated(Bitmap bitmap) {
        this.mBitmapCreatedListener.accept(bitmap);
    }

    public void onDestroy() {
        this.mSaveStatus.clear();
    }

    public void onError(int i2) {
        this.mCurrentErrorCode = i2;
        Callable<Void> callable = this.mErrorListener;
        if (callable != null && i2 != 0) {
            try {
                callable.call();
                Log.e("StatusHandler", "onError: App notified. Error code:" + this.mCurrentErrorCode);
            } catch (Exception e) {
                j.D(e, new StringBuilder("Error in notifying: "), "StatusHandler");
            }
        }
    }

    public void onSaveCompleted(String str, IGallery360Viewer.SaveStatus saveStatus) {
        if (str == null || str.isEmpty()) {
            Log.e("StatusHandler", "filename null.");
            return;
        }
        if (saveStatus == null) {
            Log.e("StatusHandler", "saveStatus null. Default to FAIL");
            saveStatus = IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_FAIL;
        }
        this.mSaveStatus.put(str, saveStatus);
        Callable<Void> callable = this.mSaveCompletedListener;
        if (callable != null) {
            try {
                callable.call();
            } catch (Exception e) {
                j.D(e, new StringBuilder("Error in notifying: "), "StatusHandler");
            }
        }
    }

    public void set360BitmapCreatedListener(Consumer<Bitmap> consumer) {
        this.mBitmapCreatedListener = consumer;
    }

    public void setErrorListener(Callable<Void> callable) {
        this.mCurrentErrorCode = 0;
        this.mErrorListener = callable;
    }

    public void setSaveCompletedListener(String str, Callable<Void> callable) {
        if (str != null && !str.isEmpty()) {
            this.mSaveStatus.put(str, IGallery360Viewer.SaveStatus.SCREEN_CAPTURE_PROGRESS);
            this.mSaveCompletedListener = callable;
        }
    }
}
