package com.samsung.android.gallery.app.remote.v2;

import D7.a;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import b4.C0421a;
import b4.C0422b;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryHighResPresentation extends GalleryBasePresentation {
    protected static String TAG = "GalleryHighResPresentation";

    public GalleryHighResPresentation(Context context, Display display, int i2, IVuDispatcher iVuDispatcher) {
        super(context, display, i2, iVuDispatcher);
    }

    private Point fillSize(int i2, int i7, int i8, int i10) {
        Point point = new Point();
        if (((float) i8) / ((float) i10) > ((float) i2) / ((float) i7)) {
            point.x = (i2 * i10) / i7;
            point.y = i10;
            return point;
        }
        point.x = i8;
        point.y = (i7 * i8) / i2;
        return point;
    }

    private Point findMaximalSize(int i2, int i7, int i8, int i10, boolean z, int i11, int i12) {
        boolean z3;
        int i13;
        if (z) {
            int i14 = i10;
            i10 = i8;
            i8 = i14;
        }
        Point fillSize = fillSize(i8, i10, i2, i7);
        int i15 = fillSize.x;
        int i16 = fillSize.y;
        if (i15 * i16 <= i11 * i12) {
            return fillSize;
        }
        if (((float) i2) / ((float) i7) > 1.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            i13 = i11;
        } else {
            i13 = i12;
        }
        if (z3) {
            i11 = i12;
        }
        return fillSize(i15, i16, i13, i11);
    }

    private Point getDisplaySize() {
        Point point = new Point();
        if (getDisplay() != null) {
            getDisplay().getSize(point);
        }
        return point;
    }

    private boolean isRotate(MediaItem mediaItem) {
        if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePhotoView$0(MediaItem mediaItem, Bitmap bitmap, Bitmap bitmap2, PhotoViewMotionControl photoViewMotionControl, int i2) {
        IPresentationView iPresentationView = this.mView;
        if (bitmap == null) {
            bitmap = bitmap2;
        }
        iPresentationView.updateViews(mediaItem, bitmap, photoViewMotionControl, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePhotoView$1(MediaItem mediaItem, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i2) {
        this.mView.updateViews(mediaItem, bitmap, photoViewMotionControl, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePhotoView$2(MediaItem mediaItem, int i2, Point point, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i7) {
        int i8 = i2;
        Point point2 = point;
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(i8));
        if (decodedBitmap != null) {
            Bitmap createScaledBitmap = BitmapUtils.createScaledBitmap(decodedBitmap, point2.x, point2.y, true);
            String str = TAG;
            Log.rm(str, "updatePhotoView get highRes bitmap with width {" + point2.x + ArcCommonLog.TAG_COMMA + point2.y + " , sample size {" + i8 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            ThreadUtil.runOnUiThread(new C0422b(this, mediaItem, createScaledBitmap, bitmap, photoViewMotionControl, i7, 0));
            return;
        }
        Log.rm(TAG, "updatePhotoView get highRes bitmap null");
        ThreadUtil.runOnUiThread(new a(this, mediaItem, bitmap, photoViewMotionControl, i7));
    }

    private boolean notSupportedSize(MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        boolean z3;
        boolean z7;
        if (Math.min(bitmap.getWidth(), bitmap.getHeight()) >= 3840) {
            z = true;
        } else {
            z = false;
        }
        if (Math.max(bitmap.getWidth(), bitmap.getHeight()) >= Math.max(mediaItem.getWidth(), mediaItem.getHeight())) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (getDisplaySize().x < 3840) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (z || z3 || z7) {
            return true;
        }
        return false;
    }

    private boolean notSupportedType(MediaItem mediaItem) {
        if (mediaItem.isBroken() || mediaItem.isVideo() || mediaItem.isMotionPhoto()) {
            return true;
        }
        return false;
    }

    private void sendHighResolutionModeRequest(boolean z) {
        Intent intent = new Intent("com.samsung.android.smartmirroring.HIGH_RESOLUTION_MODE");
        intent.putExtra("enable", z);
        try {
            AppResources.getAppContext().sendBroadcast(intent);
            String str = TAG;
            Log.rm(str, "sendHighResolutionModeRequest : " + z);
        } catch (Exception e) {
            String str2 = TAG;
            Log.rme(str2, "sendHighResolutionModeRequest failed e=" + e.getMessage());
        }
    }

    private void turnOffHighResolutionMode() {
        sendHighResolutionModeRequest(false);
    }

    private void turnOnHighResolutionMode() {
        sendHighResolutionModeRequest(true);
    }

    public void dismiss() {
        super.dismiss();
        turnOffHighResolutionMode();
    }

    public void hide() {
        super.hide();
        turnOffHighResolutionMode();
    }

    public void show() {
        super.show();
        turnOnHighResolutionMode();
    }

    public void updatePhotoView(MediaItem mediaItem, PhotoViewMotionControl photoViewMotionControl, Bitmap bitmap, int i2) {
        if (notSupportedType(mediaItem) || notSupportedSize(mediaItem, bitmap)) {
            super.updatePhotoView(mediaItem, photoViewMotionControl, bitmap, i2);
            return;
        }
        Point displaySize = getDisplaySize();
        int i7 = displaySize.x;
        int i8 = i7;
        int i10 = displaySize.y;
        int i11 = i8;
        Point findMaximalSize = findMaximalSize(i11, i10, mediaItem.getWidth(), mediaItem.getHeight(), isRotate(mediaItem), 3840, 2160);
        SimpleThreadPool.getInstance().execute(new C0421a(this, mediaItem, Math.max(findMaximalSize.x, findMaximalSize.y), findMaximalSize, bitmap, photoViewMotionControl, i2));
    }
}
