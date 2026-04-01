package com.samsung.android.gallery.plugins.filebrowser;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BitmapHolder extends MediaInfo {
    Bitmap bitmap;
    ExifInterface exif;

    public boolean isHeif() {
        if ("image/heic".equals(this.mimeType) || "image/heif".equals(this.mimeType)) {
            return true;
        }
        return false;
    }

    public boolean isJpeg() {
        return "image/jpeg".equals(this.mimeType);
    }

    public String toString() {
        return "BitmapHolder[" + this.mimeType + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.width + "x" + this.height + Log.TAG_SEPARATOR + this.orientation + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.orientationTag + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.toSimpleString(this.bitmap) + "]";
    }
}
