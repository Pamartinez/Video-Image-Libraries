package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction;

import Nb.c;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import java.lang.ref.WeakReference;
import java.util.Optional;
import q8.a;
import v7.w;
import y7.g;
import y7.h;
import y7.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionImageHandler extends TextExtractionHandler {
    Runnable mBitmapInvalidateDetect;
    int mDetectRetryCount;
    boolean mIsBitmapDirty;
    boolean mIsGIFAnimationMode;
    private final Runnable mPendedBitmapInvalidateDetect = new g(this, 5);
    private final Runnable mPendedBitmapLoadAndDetect = new g(this, 4);
    PhotoView mPhotoView;

    /* access modifiers changed from: private */
    public void bitmapInvalidateDetect() {
        this.mIsBitmapDirty = false;
        this.mIsDetecting = true;
        this.mTextHelper.clearDetectType(true);
        this.mTextHelper.clearVariables();
        clearTextSelection();
        Optional.ofNullable(this.mBitmapHolder).ifPresent(new w(12));
        this.mThread.runOnUiThread(new g(this, 1));
    }

    /* access modifiers changed from: private */
    public void bitmapLoadAndDetect() {
        if (!this.mModel.isBitmapLoaded()) {
            int i2 = this.mDetectRetryCount;
            if (i2 > 0) {
                this.mDetectRetryCount = i2 - 1;
                this.mThread.runOnUiThread(this.mPendedBitmapLoadAndDetect, 500);
            } else if (i2 == 0) {
                Log.e(this.TAG, "failed to detection, original bitmap is not prepared");
            }
        } else {
            detect();
        }
    }

    private boolean hasOcr(MediaItem mediaItem) {
        if (mediaItem == null || !DetailsData.of(mediaItem).hasOcr) {
            return false;
        }
        return true;
    }

    private boolean hasOrientation(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.getOrientation() != 0 || ExifUtils.isHorizontalMirror(mediaItem.getOrientationTag())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mIsBitmapDirty = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bitmapInvalidateDetect$4() {
        disableTextExtractionView(new Object[0]);
        detect();
        this.mBitmapInvalidateDetect = null;
        Log.d(this.TAG, "bitmap invalidate detect done");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$detectDone$5() {
        setState(this.mTextHelper, TextExtractionHelper.State.FULL_FILTER);
        extract(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onGIFAnimationMode$6() {
        this.mIsGIFAnimationMode = true;
        if (this.mTextExtractionViewVisible) {
            disableTextExtractionView(new Object[0]);
        }
        setButtonVisibility(false, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDetect$7() {
        if (!this.mPaused) {
            TextExtractionHelper textExtractionHelper = this.mTextHelper;
            if (textExtractionHelper == null || textExtractionHelper.isDisabled()) {
                this.mTextHelper = new TextExtractionHelper(this.mModel.getContext(), this);
            }
            if (!isDetected()) {
                this.mDetectRetryCount = 5;
                this.mThread.runOnUiThread(this.mPendedBitmapLoadAndDetect);
            } else {
                detectDone();
            }
            this.mDetectRunnable = null;
        }
    }

    /* access modifiers changed from: private */
    public void onCurrentItemChanged(Object... objArr) {
        long j2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("item changed");
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = 0;
        }
        sb2.append(Logger.v(Long.valueOf(j2), Boolean.valueOf(this.mIsBitmapDirty), Boolean.valueOf(this.mPaused), this.mTextHelper));
        Log.d(stringCompat, sb2.toString());
        if (this.mTextHelper == null) {
            return;
        }
        if ((mediaItem != null && mediaItem.getFileId() != this.mTextHelper.getFileId() && this.mTextHelper.getFileId() > 0) || this.mIsBitmapDirty) {
            if (this.mPaused) {
                this.mBitmapInvalidateDetect = this.mPendedBitmapInvalidateDetect;
            } else if (!DeepSkyHelper.hasCallbacks(this.mPendedBitmapInvalidateDetect)) {
                DeepSkyHelper.postDelayed(this.mPendedBitmapInvalidateDetect, 100);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onGIFAnimationMode(Object... objArr) {
        this.mThread.runOnUiThread(new g(this, 2));
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new i(this, 0));
        this.mActionInvoker.add(ViewerAction.GIF_ANIMATION_MODE, new i(this, 1));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new i(this, 2));
        this.mActionInvoker.add(ViewerAction.UPDATE_PHOTO_BITMAP, new i(this, 3));
    }

    public void bindTextExtractionView() {
        TextExtractionView textExtractionView;
        boolean z;
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            boolean z3 = this.mTextExtractionViewVisible;
            if (z3) {
                textExtractionView = this.mTextExtractionView;
            } else {
                textExtractionView = null;
            }
            if (!z3 || !this.mIsDirty) {
                z = false;
            } else {
                z = true;
            }
            photoView.bindCaptureView(textExtractionView, true, z);
        }
    }

    public void clearPendedBitmapLoadAndDetect() {
        this.mDetectRetryCount = 0;
        this.mThread.cancelUiThread(this.mPendedBitmapLoadAndDetect);
    }

    public void detect() {
        long j2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!isValid(mediaItem) || this.mPaused) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("detect req skip");
            if (mediaItem != null) {
                j2 = mediaItem.getFileId();
            } else {
                j2 = 0;
            }
            sb2.append(Logger.v(Long.valueOf(j2)));
            Log.d(stringCompat, sb2.toString());
            return;
        }
        if (this.mDetectTask == null) {
            this.mDetectTask = getTextExtractionTask(TextExtractionTask.Mode.DETECT, new h(this, 0));
        }
        if (!DeepSkyHelper.hasCallbacks(this.mDetectTask)) {
            DeepSkyHelper.postDelayed(this.mDetectTask, 50);
        }
    }

    public void detectDone() {
        super.detectDone();
        if (hasData() && supportFilterText()) {
            String searchFilterText = getSearchFilterText();
            if (!TextUtils.isEmpty(searchFilterText)) {
                StringCompat stringCompat = this.TAG;
                Log.i(stringCompat, "detectDone: filter = " + Logger.getEncodedString(searchFilterText));
                this.mThread.runOnUiThread(new g(this, 3));
            }
        }
    }

    public void extract(boolean z) {
        TextExtractionTask.Mode mode;
        if (isExtracted() && isDocumentScanned()) {
            extractDone();
        } else if (this.mPaused) {
            clearTextExtractionViewVisibilityChangeFlag();
        } else {
            if (z) {
                mode = TextExtractionTask.Mode.EXTRACT_BY_LONG_PRESS;
            } else {
                mode = TextExtractionTask.Mode.EXTRACT_BY_BUTTON;
            }
            TextExtractionTask textExtractionTask = getTextExtractionTask(mode, new h(this, 1));
            this.mExtractTask = textExtractionTask;
            DeepSkyHelper.post(textExtractionTask);
        }
    }

    public String getAnalyticsLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_TYPE_IMAGE.toString();
    }

    public Bitmap getBitmap(boolean z) {
        boolean z3;
        Bitmap bitmap;
        Bitmap bitmap2 = this.mModel.getBitmap();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!z || mediaItem == null || ((bitmap2 != null && Math.max(bitmap2.getWidth(), bitmap2.getHeight()) >= Math.max(mediaItem.getWidth(), mediaItem.getHeight())) || (bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(2500))) == null)) {
            z3 = false;
            bitmap = bitmap2;
        } else {
            z3 = true;
        }
        if (bitmap != null) {
            try {
                if (hasOrientation(mediaItem)) {
                    Bitmap bitmap3 = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
                    if (!z3 && bitmap3 != null) {
                        if (!bitmap3.isRecycled()) {
                            bitmap = bitmap3;
                        }
                    }
                    bitmap = new BitmapOperator(bitmap).rotate(mediaItem.getOrientation(), mediaItem.getOrientationTag()).apply();
                }
            } catch (Error | Exception e) {
                Log.e(this.TAG, "bitmap rotation failed, e=" + e.getMessage());
            } catch (Throwable th) {
                if (bitmap2 != bitmap) {
                    if (!z) {
                        this.mBitmapHolder = new WeakReference<>(bitmap.copy(Bitmap.Config.ARGB_8888, true));
                    }
                    ObjectDictionary.setTag(bitmap, "Recyclable", Boolean.TRUE);
                }
                throw th;
            }
        }
        if (!(bitmap == null || bitmap2 == bitmap)) {
            if (!z) {
                this.mBitmapHolder = new WeakReference<>(bitmap.copy(Bitmap.Config.ARGB_8888, true));
            }
            ObjectDictionary.setTag(bitmap, "Recyclable", Boolean.TRUE);
        }
        return bitmap;
    }

    public RectF getDisplayRect() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            return photoView.getDisplayRect();
        }
        return null;
    }

    public String getFilterText() {
        if (isFullFilterState()) {
            return getSearchFilterText();
        }
        return null;
    }

    public Bitmap getLowBitmap() {
        Bitmap bitmap = this.mModel.getBitmap();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (bitmap != null && hasOrientation(mediaItem)) {
            try {
                Bitmap bitmap2 = (Bitmap) Optional.ofNullable(this.mBitmapHolder).map(new a(26)).orElse((Object) null);
                if (bitmap2 != null) {
                    if (!bitmap2.isRecycled()) {
                        return bitmap2;
                    }
                }
                Bitmap apply = new BitmapOperator(bitmap).rotate(mediaItem.getOrientation(), mediaItem.getOrientationTag()).apply();
                ObjectDictionary.setTag(apply, "Recyclable", Boolean.TRUE);
                this.mBitmapHolder = new WeakReference<>(apply.copy(Bitmap.Config.ARGB_8888, true));
                return apply;
            } catch (Error | Exception e) {
                StringCompat stringCompat = this.TAG;
                Log.e(stringCompat, "bitmap rotation failed, e=" + e.getMessage());
            }
        }
        return bitmap;
    }

    public String getSearchFilterText() {
        if (supportFilterText()) {
            String locationKey = this.mModel.getContainerModel().getLocationKey();
            if (locationKey.startsWith("location://search/fileList/KeywordClusterPictures")) {
                if ("ocrtext".equals(ArgumentsUtil.getArgValue(locationKey, "term", ""))) {
                    return ArgumentsUtil.getArgValue(locationKey, "title");
                }
                return null;
            } else if (hasOcr(this.mModel.getMediaItem()) || hasOcr(this.mModel.getRepresentativeItem())) {
                return ArgumentsUtil.getArgValue(locationKey, "ocr_keyword");
            }
        }
        return null;
    }

    public int getTopMargin() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            return photoView.getTopMarginFromSupplier();
        }
        return 0;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (!MediaItemUtil.equalsWithDateModified(this.mModel.getMediaItem(), mediaItem)) {
            this.mIsBitmapDirty = true;
            onCurrentItemChanged(new Object[0]);
        }
        Log.d(this.TAG, "invalidate", Boolean.valueOf(this.mIsBitmapDirty), Boolean.valueOf(this.mPaused), Long.valueOf(mediaItem.getDateModified()), Optional.ofNullable(this.mModel.getMediaItem()).map(new a(29)).orElse(-1L));
    }

    public boolean isButtonViewable() {
        if (!super.isButtonViewable() || this.mIsGIFAnimationMode) {
            return false;
        }
        return true;
    }

    public boolean isCacheAvailable() {
        return true;
    }

    public void onPause() {
        super.onPause();
        clearPendedBitmapLoadAndDetect();
    }

    public void onResume() {
        super.onResume();
        Optional.ofNullable(this.mBitmapInvalidateDetect).ifPresent(new w(27));
        if (this.mTextHelper == null) {
            requestDetect();
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        requestDetect();
    }

    public void onViewDetached() {
        super.onViewDetached();
        clearPendedBitmapLoadAndDetect();
    }

    public boolean onViewLongPress(float f, float f5) {
        if (this.mIsGIFAnimationMode) {
            return false;
        }
        return super.onViewLongPress(f, f5);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsBitmapDirty = false;
        this.mIsGIFAnimationMode = false;
    }

    public void refreshCaptureView() {
        PhotoView photoView;
        if (!this.mTextExtractionViewVisible || (photoView = this.mPhotoView) == null) {
            this.mIsDirty = true;
        } else {
            photoView.post(new c(photoView, 3));
        }
    }

    public void refreshLayout() {
        updateMargin();
        updateDocumentScanButton();
        if (isTextHelperReady()) {
            refreshCaptureView();
        }
    }

    public void requestDetect() {
        if (this.mDetectRunnable == null) {
            this.mDetectRunnable = new g(this, 0);
        }
        if (!DeepSkyHelper.hasCallbacks(this.mDetectRunnable)) {
            DeepSkyHelper.post(this.mDetectRunnable);
        }
    }

    public void resetFilter() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.resetFilter();
            setState(this.mTextHelper, TextExtractionHelper.State.FULL);
            refreshCaptureView();
        }
    }

    public boolean supportFilterText() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster) || !LocationKey.isSearchPictures(this.mModel.getContainerModel().getLocationKey())) {
            return false;
        }
        return true;
    }
}
