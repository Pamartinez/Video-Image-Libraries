package com.samsung.android.gallery.support.library.v9;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v9.media.Sec115MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v9.media.Sem115MediaPlayerCompat;
import com.samsung.android.media.imagecrop.SemCroppedImageInfo;
import com.samsung.android.media.imagecrop.SemImageCrop;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem115ApiCompatImpl extends Sem111ApiCompatImpl {
    private int getPopoverPositionFlags(RectF rectF, Point point) {
        int i2;
        rectF.offsetTo(0.0f, 0.0f);
        int i7 = point.x;
        float f = rectF.right;
        if (((float) i7) < f / 3.0f) {
            i2 = 16;
        } else if (((float) i7) < (f * 2.0f) / 3.0f) {
            i2 = 64;
        } else {
            i2 = 32;
        }
        int i8 = point.y;
        float f5 = rectF.bottom;
        if (((float) i8) < f5 / 3.0f) {
            return i2 | 1;
        }
        if (((float) i8) < (f5 * 2.0f) / 3.0f) {
            return i2 | 4;
        }
        return i2 | 2;
    }

    public MediaPlayerCompat createMediaPlayer(int i2) {
        return new Sem115MediaPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new Sec115MediaPlayerCompat(i2);
    }

    public ActivityOptions getPopoverActivityOptions(RectF rectF, Point point) {
        if (rectF == null || point == null || rectF.width() <= 0.0f) {
            Log.w(this.TAG, "getPopoverActivityOptions invalid param");
            return null;
        }
        try {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.semSetChooserPopOverPosition(getPopoverPositionFlags(rectF, point));
            return makeBasic;
        } catch (NoSuchMethodError | NullPointerException e) {
            C0212a.y(e, new StringBuilder("getPopoverActivityOptions failed e="), this.TAG);
            return null;
        }
    }

    public ByteBuffer getQuickCropStream(File file, Rect rect) {
        FileInputStream fileInputStream;
        ByteBuffer byteBuffer;
        try {
            fileInputStream = new FileInputStream(file);
            SemCroppedImageInfo crop = SemImageCrop.getInstance().crop(fileInputStream.getFD(), rect);
            if (crop == null) {
                byteBuffer = null;
            } else {
                byteBuffer = crop.getByteBuffer();
            }
            fileInputStream.close();
            return byteBuffer;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getQuickCropStream failed e="), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setPopoverDialogStyle(Dialog dialog, View view, boolean z) {
        if (z) {
            try {
                dialog.semSetAnchor(view, 1);
            } catch (NoSuchMethodError | NullPointerException e) {
                C0212a.y(e, new StringBuilder("setPopoverDialogStyle failed. e="), this.TAG);
            }
        } else {
            dialog.semSetAnchor(view);
        }
    }
}
