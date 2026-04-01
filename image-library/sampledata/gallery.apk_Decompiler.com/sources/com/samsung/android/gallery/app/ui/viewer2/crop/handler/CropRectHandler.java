package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Rect;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropRectHandler extends CropHandler {
    private String mOutputCropRect;

    public CropRectHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    private int getOrientation() {
        if (this.mMediaItem.isVideo()) {
            return 0;
        }
        return this.mMediaItem.getOrientation();
    }

    private String getScaledCropRectString(Rect rect) {
        float f;
        float f5;
        float f8;
        float f10;
        int i2;
        float f11;
        float f12;
        int i7;
        float f13;
        int i8;
        int i10;
        int orientation = getOrientation();
        if (orientation != 90) {
            if (orientation == 180) {
                int i11 = this.mBitmapWidth;
                f13 = ((float) (i11 - rect.right)) / ((float) i11);
                i8 = this.mBitmapHeight;
                f8 = ((float) (i8 - rect.bottom)) / ((float) i8);
                f5 = ((float) (i11 - rect.left)) / ((float) i11);
                i10 = i8 - rect.top;
            } else if (orientation != 270) {
                int i12 = this.mBitmapWidth;
                f10 = ((float) rect.left) / ((float) i12);
                i2 = this.mBitmapHeight;
                f11 = ((float) rect.top) / ((float) i2);
                f12 = ((float) rect.right) / ((float) i12);
                i7 = rect.bottom;
            } else {
                int i13 = this.mBitmapWidth;
                f13 = ((float) (i13 - rect.bottom)) / ((float) i13);
                i8 = this.mBitmapHeight;
                f8 = ((float) rect.left) / ((float) i8);
                f5 = ((float) (i13 - rect.top)) / ((float) i13);
                i10 = rect.right;
            }
            f = ((float) i10) / ((float) i8);
            f10 = f13;
            return "" + f10 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f8 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f;
        }
        int i14 = this.mBitmapWidth;
        f10 = ((float) rect.top) / ((float) i14);
        i2 = this.mBitmapHeight;
        f11 = ((float) (i2 - rect.right)) / ((float) i2);
        f12 = ((float) rect.bottom) / ((float) i14);
        i7 = i2 - rect.left;
        f = ((float) i7) / ((float) i2);
        return "" + f10 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f8 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f;
    }

    public Intent buildIntent() {
        if (this.mOutputCropRect == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("key-get-rect-result", this.mOutputCropRect);
        return intent;
    }

    public boolean process(Rect rect) {
        this.mOutputCropRect = getScaledCropRectString(rect);
        return true;
    }
}
