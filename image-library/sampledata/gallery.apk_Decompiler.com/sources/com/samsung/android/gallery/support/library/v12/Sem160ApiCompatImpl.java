package com.samsung.android.gallery.support.library.v12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.SemBlurInfo;
import android.view.View;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v12.media.Sec160MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v12.media.Sem160MediaCaptureCompat;
import com.samsung.android.media.SemBitmapFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem160ApiCompatImpl extends Sem150ApiCompatImpl {
    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new Sec160MediaPlayerCompat(i2);
    }

    public MediaCaptureCompat getMediaCaptureCompat() {
        return new Sem160MediaCaptureCompat();
    }

    public Bitmap getThumbnailHeif(String str, BitmapFactory.Options options) {
        try {
            return SemBitmapFactory.decodeThumbnailFile(str, options);
        } catch (Error | Exception e) {
            String str2 = this.TAG;
            Log.w(str2, "getHeifThumbnail failed. e=" + e.getMessage());
            return null;
        }
    }

    public void setBlurPreset(View view, int i2, float f, int i7) {
        if (view != null) {
            SemBlurInfo.Builder colorCurvePreset = new SemBlurInfo.Builder(i2).setColorCurvePreset(i7);
            if (f > 0.0f) {
                colorCurvePreset.setBackgroundCornerRadius(f);
            }
            view.semSetBlurInfo(colorCurvePreset.build());
        }
    }
}
