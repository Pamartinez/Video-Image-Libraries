package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropDataHandler extends CropUriHandler {
    private Bitmap mOutputBitmap;

    public CropDataHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    public Intent buildIntent() {
        if (this.mOutputBitmap == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.putExtra("data", this.mOutputBitmap);
        return intent;
    }

    public boolean process(Rect rect) {
        Bitmap croppedImage = getCroppedImage(rect, this.mIntent.getExtras());
        this.mOutputBitmap = croppedImage;
        if (croppedImage != null) {
            return true;
        }
        return false;
    }
}
