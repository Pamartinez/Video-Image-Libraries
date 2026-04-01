package com.samsung.android.gallery.module.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.drm.DrmManager;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GifAnimation {
    protected final String TAG = tag();
    protected BitmapOptions mBitmapOptions;
    protected AnimationFrameStartListener mFrameStartListener;
    protected AnimationFrameUpdateListener mFrameUpdateListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnimationFrameStartListener {
        void onAnimationFrameStarted();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnimationFrameUpdateListener {
        void onAnimationFrameUpdated(Bitmap bitmap);
    }

    private boolean decodeInternal(Context context, FileItemInterface fileItemInterface) {
        if (fileItemInterface.isBroken()) {
            Log.e(this.TAG, "decode failed. broken image");
            return false;
        } else if (fileItemInterface.isDrm()) {
            byte[] byteArray = DrmManager.getInstance().getByteArray(getPath(fileItemInterface));
            if (byteArray != null && byteArray.length != 0) {
                return decodeByteArray(byteArray, 0, byteArray.length);
            }
            Log.e(this.TAG, "decode failed. DRM decrypt error");
            return false;
        } else if (fileItemInterface.isMtp()) {
            MtpClient instance = MtpClient.getInstance(context);
            byte[] object = instance.getObject(instance.getDeviceNameFromPath(getPath(fileItemInterface)), (int) fileItemInterface.getFileId(), (int) fileItemInterface.getFileSize());
            if (object != null && object.length != 0) {
                return decodeByteArray(object, 0, object.length);
            }
            Log.e(this.TAG, "decode failed. MtpClient#getObject error");
            return false;
        } else if (fileItemInterface.isUriItem()) {
            return decodeUri(context, Uri.parse(getPath(fileItemInterface)));
        } else {
            return decodeFile(getPath(fileItemInterface));
        }
    }

    public boolean decode(Context context, FileItemInterface fileItemInterface) {
        try {
            if (decodeInternal(context, fileItemInterface)) {
                String str = this.TAG;
                Log.d(str, "getGifAnimation#decode " + this);
                return true;
            }
            String str2 = this.TAG;
            Log.e(str2, "getGifAnimation#decode failed " + this);
            return false;
        } catch (Exception e) {
            release();
            String str3 = this.TAG;
            Log.e((CharSequence) str3, "getGifAnimation#decode failed " + this, (Throwable) e);
            return false;
        } catch (OutOfMemoryError e7) {
            release();
            String str4 = this.TAG;
            Log.e(str4, "getGifAnimation#decode failed " + this + " e=" + e7.getMessage());
            new InternalException("getGifAnimation#decode failed. not enough memory").post();
            return false;
        }
    }

    public abstract boolean decodeByteArray(byte[] bArr, int i2, int i7);

    public abstract boolean decodeFile(String str);

    public abstract boolean decodeUri(Context context, Uri uri);

    public abstract void drawFrame(Bitmap bitmap, Canvas canvas);

    public String getPath(FileItemInterface fileItemInterface) {
        return MediaItemMde.getHiddenFilePath(fileItemInterface, fileItemInterface.getPath());
    }

    public abstract boolean isAnimation();

    public final void notifyListener(Bitmap bitmap) {
        if (this.mFrameUpdateListener != null) {
            BitmapOptions bitmapOptions = this.mBitmapOptions;
            if (bitmapOptions != null) {
                SeApiCompat.addBitmapTag(bitmap, "options", bitmapOptions);
            }
            this.mFrameUpdateListener.onAnimationFrameUpdated(bitmap);
        }
    }

    public void release() {
        stop();
        this.mFrameStartListener = null;
        this.mFrameUpdateListener = null;
    }

    public final GifAnimation setAnimationFrameStartListener(AnimationFrameStartListener animationFrameStartListener) {
        this.mFrameStartListener = animationFrameStartListener;
        return this;
    }

    public final GifAnimation setAnimationFrameUpdateListener(AnimationFrameUpdateListener animationFrameUpdateListener) {
        this.mFrameUpdateListener = animationFrameUpdateListener;
        return this;
    }

    public abstract void start();

    public abstract void stop();

    public abstract String tag();
}
