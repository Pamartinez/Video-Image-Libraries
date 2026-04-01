package com.samsung.android.gallery.image360.activity.options;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.gallery.image360.R$color;
import com.samsung.android.gallery.image360.R$dimen;
import com.samsung.android.gallery.image360.R$drawable;
import com.samsung.android.gallery.image360.activity.abstraction.IActivityView;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PlayBackOptionsPresenter {
    private int mCurDirection;
    private String mFilePath = null;
    private int mPrevDirection;

    private int calculateInSampleSize(BitmapFactory.Options options) {
        int i2 = options.outHeight;
        int i7 = options.outWidth;
        int i8 = 1;
        if (i2 <= 1024 && i7 <= 512) {
            return 1;
        }
        int i10 = i2 / 2;
        int i11 = i7 / 2;
        while (i10 / i8 >= 1024 && i11 / i8 >= 512) {
            i8 *= 2;
        }
        return i8;
    }

    private Bitmap decodeSampledBitmap(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private Bitmap joinImagesHorizontally(Bitmap bitmap, Bitmap bitmap2) {
        int i2;
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            return null;
        }
        int width = bitmap2.getWidth() + bitmap.getWidth();
        if (bitmap.getHeight() > bitmap2.getHeight()) {
            i2 = bitmap.getHeight();
        } else {
            i2 = bitmap2.getHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, (float) bitmap.getWidth(), 0.0f, (Paint) null);
        canvas.save();
        return createBitmap;
    }

    private void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW.toString(), analyticsEventId.toString());
    }

    private void postDefaultLensViewAnalyticsLog(String str) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW.toString(), AnalyticsEventId.EVENT_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW_DIRECTION.toString(), str);
    }

    private void saveDefaultDirection(IActivityView iActivityView, int i2) {
        if (XmpUtils.writeToXMP(this.mFilePath, IGallery360Viewer.DefaultPlaybackDirection.values()[i2].toString())) {
            iActivityView.setPlaybackDirection(i2);
        } else {
            Log.e("PlayBackOptionsPresenter", "XMP write failed.");
        }
    }

    public final Bitmap getBrokenBitmap(Context context) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(context, R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg);
    }

    public int getCurrentDirection() {
        return this.mCurDirection;
    }

    public Bitmap[] getDecodedBitmaps() {
        String str = this.mFilePath;
        if (str == null || str.isEmpty()) {
            Log.d("PlayBackOptionsPresenter", "setBitmap : filePath is null or empty");
            return null;
        }
        Bitmap decodeSampledBitmap = decodeSampledBitmap(this.mFilePath);
        if (decodeSampledBitmap != null && !decodeSampledBitmap.isRecycled()) {
            int width = decodeSampledBitmap.getWidth();
            int height = decodeSampledBitmap.getHeight();
            int i2 = width >> 2;
            Bitmap createBitmap = BitmapUtils.createBitmap(decodeSampledBitmap, i2, 0, width >> 1, height);
            Bitmap createBitmap2 = BitmapUtils.createBitmap(decodeSampledBitmap, 0, 0, i2, height);
            Bitmap createBitmap3 = BitmapUtils.createBitmap(decodeSampledBitmap, width - i2, 0, i2, height);
            Bitmap[] bitmapArr = {createBitmap, joinImagesHorizontally(createBitmap3, createBitmap2)};
            if (createBitmap2 != null) {
                BitmapUtils.putBitmap(createBitmap2);
            }
            if (createBitmap3 != null) {
                BitmapUtils.putBitmap(createBitmap3);
            }
            if (bitmapArr[0] == null || bitmapArr[1] == null) {
                return null;
            }
            return bitmapArr;
        }
        return null;
    }

    public boolean isSaveButtonEnabled() {
        if (this.mCurDirection != this.mPrevDirection) {
            return true;
        }
        return false;
    }

    public void loadSavedInstanceState(Bundle bundle) {
        if (bundle != null) {
            setInitValues(bundle.getString("saved_filepath"), bundle.getInt("direction_current"), bundle.getInt("direction_previous"));
        }
    }

    public void onCancelClicked(IActivityView iActivityView) {
        postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW_CANCEL);
        iActivityView.onBackPressed();
    }

    public void onSaveClicked(IActivityView iActivityView) {
        postAnalyticsLog(AnalyticsEventId.EVENT_360_PHOTO_VIEWER_DEFAULT_LENS_VIEW_SAVE);
        saveDefaultDirection(iActivityView, this.mCurDirection);
        iActivityView.onBackPressed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("direction_current", this.mCurDirection);
        bundle.putInt("direction_previous", this.mPrevDirection);
        bundle.putString("saved_filepath", this.mFilePath);
    }

    public Bitmap[] resizeBitmaps(Bitmap[] bitmapArr, Resources resources) {
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.gallery360viewer_playback_view_image_long_size);
        if (!(bitmapArr[0].getWidth() == dimensionPixelOffset && bitmapArr[0].getHeight() == dimensionPixelOffset)) {
            bitmapArr[0] = Bitmap.createScaledBitmap(bitmapArr[0], dimensionPixelOffset, dimensionPixelOffset, true);
        }
        if (bitmapArr[1].getWidth() == dimensionPixelOffset && bitmapArr[1].getHeight() == dimensionPixelOffset) {
            return bitmapArr;
        }
        bitmapArr[1] = Bitmap.createScaledBitmap(bitmapArr[1], dimensionPixelOffset, dimensionPixelOffset, true);
        return bitmapArr;
    }

    public void setCurrentDirection(int i2, String str) {
        this.mCurDirection = i2;
        postDefaultLensViewAnalyticsLog(str);
    }

    public void setInitValues(String str, int i2, int i7) {
        this.mFilePath = str;
        this.mCurDirection = i2;
        this.mPrevDirection = i7;
    }
}
