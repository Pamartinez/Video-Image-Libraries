package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.Def;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchCreatureSlideshow {
    private int mCurrentDataPosition;
    private MediaItem mCurrentItem;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                SearchCreatureSlideshow.this.slideshowNext();
            }
        }
    };
    private boolean mIsPickerMode;
    private boolean mIsRunning;
    private OnHeaderClickListener mListener;
    private MediaData mMediaData;
    private int mPosition;
    private final SlidePage[] mSlidePages;
    private final ViewFlipper mSlideshowView;
    private final View mTouchView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SlidePage {
        private int mHeight;
        MediaItem mItem;
        final ImageView mView;
        private int mVisibility;
        private int mWidth;

        public SlidePage(ImageView imageView) {
            this.mView = imageView;
            this.mWidth = imageView.getWidth();
            this.mHeight = imageView.getHeight();
            this.mVisibility = imageView.getVisibility();
        }

        public boolean isViewChanged() {
            if (this.mWidth == this.mView.getWidth() && this.mHeight == this.mView.getHeight() && this.mVisibility == this.mView.getVisibility()) {
                return false;
            }
            this.mWidth = this.mView.getWidth();
            this.mHeight = this.mView.getHeight();
            this.mVisibility = this.mView.getVisibility();
            return true;
        }
    }

    public SearchCreatureSlideshow(View view) {
        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.search_pictures_header_slideshow_view);
        this.mSlideshowView = viewFlipper;
        this.mTouchView = view.findViewById(R.id.touchable_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.search_pictures_header_slideshow_image1);
        ViewUtils.setViewShape(imageView, 1, (float) view.getResources().getDimensionPixelOffset(R.dimen.search_item_corner_radius));
        ImageView imageView2 = (ImageView) view.findViewById(R.id.search_pictures_header_slideshow_image2);
        ViewUtils.setViewShape(imageView2, 1, (float) view.getResources().getDimensionPixelOffset(R.dimen.search_item_corner_radius));
        this.mSlidePages = new SlidePage[]{new SlidePage(imageView), new SlidePage(imageView2)};
        imageView.setVisibility(0);
        imageView2.setVisibility(4);
        ViewUtils.setOnClickListener(viewFlipper, new e(0, this));
        viewFlipper.addOnLayoutChangeListener(new f(0, this));
        fitToScreen();
    }

    private void fitToScreen() {
        ViewFlipper viewFlipper = this.mSlideshowView;
        if (viewFlipper != null) {
            ViewGroup.LayoutParams layoutParams = viewFlipper.getLayoutParams();
            Resources resources = this.mSlideshowView.getResources();
            layoutParams.width = Math.min(Resources.getSystem().getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(R.dimen.search_creature_slideshow_side_margin) * 2), resources.getDimensionPixelSize(R.dimen.search_creature_slideshow_width));
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.search_creature_slideshow_height);
            this.mSlideshowView.setLayoutParams(layoutParams);
        }
        View view = this.mTouchView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.topMargin = this.mTouchView.getResources().getDimensionPixelSize(R.dimen.search_creature_slideshow_circle_thumb_top_margin);
            this.mTouchView.setLayoutParams(marginLayoutParams);
        }
    }

    private Bitmap getCurretBitmap() {
        Drawable drawable = this.mSlidePages[this.mCurrentDataPosition % 2].mView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    private boolean isDataAvailable() {
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(SlidePage slidePage) {
        if (slidePage.mView.getDrawable() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        Arrays.stream(this.mSlidePages).filter(new g(1)).filter(new g(2)).forEach(new h(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSlideshowClicked$3(OnHeaderClickListener onHeaderClickListener) {
        onHeaderClickListener.onHeaderClicked(this.mSlideshowView, this.mCurrentDataPosition, this.mCurrentItem, getCurretBitmap());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$slideshowNext$4(SlidePage slidePage, Bitmap bitmap, int i2) {
        setImageView(slidePage, bitmap, this.mCurrentItem);
        this.mSlideshowView.setDisplayedChild(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$slideshowNext$5(SlidePage slidePage, int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        SearchCreatureSlideshow searchCreatureSlideshow;
        if (bitmap != null) {
            bitmap.prepareToDraw();
            int i7 = i2;
            SlidePage slidePage2 = slidePage;
            searchCreatureSlideshow = this;
            ThreadUtil.runOnUiThread(new j(searchCreatureSlideshow, slidePage2, bitmap, i7, 0));
        } else {
            searchCreatureSlideshow = this;
        }
        if (!searchCreatureSlideshow.mIsPickerMode) {
            searchCreatureSlideshow.mHandler.sendEmptyMessageDelayed(100, Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    /* access modifiers changed from: private */
    public void onSlideshowClicked(View view) {
        Optional.ofNullable(this.mListener).ifPresent(new b(1, this));
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_FACE_TAG.toString(), AnalyticsEventId.EVENT_SEARCH_CREATURE_SELECT_SLIDESHOW.toString());
    }

    private void setImageView(SlidePage slidePage, Bitmap bitmap, MediaItem mediaItem) {
        slidePage.mItem = mediaItem;
        slidePage.mView.setImageBitmap(bitmap);
        ViewMatrixUtils.setViewMatrix(slidePage.mView, mediaItem, true);
    }

    /* access modifiers changed from: private */
    public void slideshowNext() {
        if (!isDataAvailable()) {
            stopSlideShow();
            Log.e("SearchCreatureSlideshow", "slideshowNext failed. empty data");
            return;
        }
        int i2 = this.mPosition;
        int i7 = i2 % 2;
        int count = i2 % this.mMediaData.getCount();
        this.mCurrentDataPosition = count;
        MediaItem read = this.mMediaData.read(count);
        this.mCurrentItem = read;
        if (read == null) {
            stopSlideShow();
            Log.e((CharSequence) "SearchCreatureSlideshow", "slideshowNext failed. empty item", Integer.valueOf(this.mCurrentDataPosition));
            return;
        }
        SlidePage slidePage = this.mSlidePages[i7];
        this.mPosition++;
        ThumbnailLoader.getInstance().loadThumbnail(this.mCurrentItem, ThumbKind.XLARGE_NC_KIND, new i(this, slidePage, i7, 0));
    }

    private void startSlideShow() {
        if (!this.mIsRunning && isDataAvailable()) {
            Log.d("SearchCreatureSlideshow", "startSlideShow");
            this.mIsRunning = true;
            this.mHandler.sendEmptyMessage(100);
        }
    }

    private void stopSlideShow() {
        this.mIsRunning = false;
        if (!this.mIsPickerMode) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    public void bindData(MediaData mediaData, boolean z) {
        if (mediaData == null || mediaData.getCount() == 0) {
            Log.e("SearchCreatureSlideshow", "bindData failed. " + mediaData);
            return;
        }
        this.mMediaData = mediaData;
        this.mIsPickerMode = z;
        Log.d("SearchCreatureSlideshow", "bindData", Integer.valueOf(mediaData.getCount()));
        startSlideShow();
    }

    public void handleResolutionChanged() {
        fitToScreen();
    }

    public void onDestroyView() {
        stopSlideShow();
        Arrays.stream(this.mSlidePages).filter(new g(0)).forEach(new h(0));
    }

    public void onPause() {
        stopSlideShow();
    }

    public void onResume() {
        startSlideShow();
    }

    public void setAlpha(float f) {
        ViewFlipper viewFlipper = this.mSlideshowView;
        if (viewFlipper != null) {
            viewFlipper.setAlpha(f);
        }
    }

    public void setOnClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mListener = onHeaderClickListener;
    }

    public void setVisibility(boolean z) {
        int i2;
        ViewFlipper viewFlipper = this.mSlideshowView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(viewFlipper, i2);
    }
}
