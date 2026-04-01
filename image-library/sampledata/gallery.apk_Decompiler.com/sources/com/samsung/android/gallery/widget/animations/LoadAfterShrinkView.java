package com.samsung.android.gallery.widget.animations;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.livemotion.theme.Transform;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import e5.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LoadAfterShrinkView extends SimpleShrinkView {
    private Rect mSourceRect;
    private float mSourceViewWidth = 0.0f;
    private Rect mTargetRect;
    private float mTargetViewWidth = 0.0f;
    private String mTransformInfo = "";
    final float mTranslateValue;
    final TranslateChecker mTranslatechecker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LoadAfterShrinkImageView extends ImageView {
        private ShrinkViewOrientationListener mOrientationChangeListener;
        private int mRotation = -1;

        public LoadAfterShrinkImageView(Context context) {
            super(context);
        }

        /* access modifiers changed from: private */
        public void lambda$onConfigurationChanged$0() {
            ShrinkViewOrientationListener shrinkViewOrientationListener;
            Display display = getDisplay();
            if (display != null) {
                int rotation = display.getRotation();
                if (!(this.mRotation == rotation || (shrinkViewOrientationListener = this.mOrientationChangeListener) == null)) {
                    ((b) shrinkViewOrientationListener).f3193a.onOrientationChanged(this);
                }
                this.mRotation = rotation;
            }
        }

        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            post(new c(0, this));
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mOrientationChangeListener = null;
        }

        public void setOrientationChangeListener(ShrinkViewOrientationListener shrinkViewOrientationListener) {
            this.mOrientationChangeListener = shrinkViewOrientationListener;
        }
    }

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ShrinkViewOrientationListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TranslateChecker {
    }

    public LoadAfterShrinkView(Blackboard blackboard, TranslateChecker translateChecker, float f) {
        super(blackboard);
        this.mTranslatechecker = translateChecker;
        this.mTranslateValue = f;
    }

    private StoryHighlightRect.RectInfo getDisplayRectInfo(MediaItem mediaItem, ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable == null || mediaItem == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) imageView.getParent();
        return new StoryHighlightRect.RectBuilder(mediaItem).setScreenRect(new Rect(0, 0, viewGroup.getRight(), viewGroup.getBottom())).setImageSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).withSmartCrop(!PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE).build();
    }

    private Matrix getInterpolatedImageMatrix(MediaItem mediaItem, ImageView imageView, float f) {
        Rect rect;
        if (!RectUtils.isValidRect(this.mSourceRect) || !RectUtils.isValidRect(this.mTargetRect)) {
            rect = null;
        } else {
            Rect rect2 = this.mSourceRect;
            int i2 = rect2.left;
            Rect rect3 = this.mTargetRect;
            int i7 = i2 + ((int) (((float) (rect3.left - i2)) * f));
            int i8 = rect2.top;
            int i10 = i8 + ((int) (((float) (rect3.top - i8)) * f));
            int i11 = rect2.right;
            int i12 = rect2.bottom;
            rect = new Rect(i7, i10, i11 + ((int) (((float) (rect3.right - i11)) * f)), i12 + ((int) (((float) (rect3.bottom - i12)) * f)));
        }
        return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, true).withCropRect(rect).withOrientation(getOrientation(mediaItem)).withOrientationTag(mediaItem.getOrientationTag()));
    }

    private int getOrientation(MediaItem mediaItem) {
        if (mediaItem.isVideo() || mediaItem.isBroken() || mediaItem.isFolder()) {
            return 0;
        }
        return mediaItem.getOrientation();
    }

    private Matrix getStoryImageMatrix(MediaItem mediaItem, ImageView imageView) {
        Rect rect;
        int orientation = getOrientation(mediaItem);
        StoryHighlightRect.RectInfo displayRectInfo = getDisplayRectInfo(mediaItem, imageView);
        String[] split = this.mTransformInfo.split("#");
        float parseFloat = Float.parseFloat(split[0]);
        float parseFloat2 = Float.parseFloat(split[1]);
        float parseFloat3 = Float.parseFloat(split[2]);
        float parseFloat4 = Float.parseFloat(split[3]);
        float parseFloat5 = Float.parseFloat(split[4]);
        float parseFloat6 = Float.parseFloat(split[5]);
        int parseInt = Integer.parseInt(split[6]);
        if (displayRectInfo != null) {
            rect = displayRectInfo.imageCropRect;
        } else {
            rect = null;
        }
        if (!RectUtils.isValidRect(rect)) {
            return ImageMatrix.createFitCenter(ImageMatrix.MatrixParam.create(imageView, false).withOrientation(orientation).withOrientationTag(mediaItem.getOrientationTag()));
        }
        float abs = Math.abs(((float) rect.width()) - (((float) rect.width()) * parseFloat));
        float abs2 = Math.abs(((float) rect.height()) - (((float) rect.height()) * parseFloat2));
        ((d) this.mTranslatechecker).getClass();
        if (Transform.isTranslate(parseInt)) {
            rect.inset(Math.round(abs / 2.0f), Math.round(abs2 / 2.0f));
            float width = ((float) imageView.getWidth()) * this.mTranslateValue;
            float height = ((float) imageView.getHeight()) * this.mTranslateValue;
            rect.offset(Math.round(((float) rect.width()) * this.mTranslateValue * ((Math.abs(parseFloat3) - width) / width)), Math.round(((float) rect.height()) * this.mTranslateValue * ((Math.abs(parseFloat4) - height) / height)));
        } else {
            rect.left = Math.round(abs * parseFloat5) + rect.left;
            rect.top = Math.round(abs2 * parseFloat6) + rect.top;
            rect.right -= Math.round((1.0f - parseFloat5) * abs);
            rect.bottom -= Math.round((1.0f - parseFloat6) * abs2);
        }
        return ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, false).withCropRect(rect).withOrientation(orientation).withOrientationTag(mediaItem.getOrientationTag()));
    }

    private Rect getTargetCropRect(MediaItem mediaItem, ImageView imageView, int i2) {
        RectF viewOriginTargetCropRectRatio = MediaItemStory.getViewOriginTargetCropRectRatio(mediaItem);
        if (RectUtils.isValidRect(viewOriginTargetCropRectRatio)) {
            return RectUtils.getSmartCropRect(imageView.getDrawable(), viewOriginTargetCropRectRatio, i2, false);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onOrientationChanged(ImageView imageView) {
        Rect rect;
        ImageView imageView2 = this.mShrinkBackgroundView;
        if (this.mShrinkView == null || imageView2 == null) {
            Log.w(this.TAG, "no shrink view");
            return;
        }
        StoryHighlightRect.RectInfo displayRectInfo = getDisplayRectInfo(this.mMediaItem, imageView);
        if (displayRectInfo != null) {
            rect = displayRectInfo.displayRect;
        } else {
            rect = new Rect();
        }
        RectF viewRect = ViewUtils.getViewRect((ViewGroup) imageView.getParent());
        if ((rect.left == 0 && rect.top == 0) || PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            rect.set(new Rect((int) ((viewRect.width() * 0.5f) - (((float) rect.width()) * 0.5f)), (int) ((viewRect.height() * 0.5f) - (((float) rect.height()) * 0.5f)), (int) ((((float) rect.width()) * 0.5f) + (viewRect.width() * 0.5f)), (int) ((((float) rect.height()) * 0.5f) + (viewRect.height() * 0.5f))));
        }
        ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
        layoutParams.width = (int) viewRect.width();
        layoutParams.height = (int) viewRect.height();
        ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
        layoutParams2.width = rect.width();
        layoutParams2.height = rect.height();
        imageView.setX((float) rect.left);
        imageView.setY((float) rect.top);
    }

    public ImageView createShrinkBackgroundImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    public ImageView createShrinkImageView(Context context) {
        LoadAfterShrinkImageView loadAfterShrinkImageView = new LoadAfterShrinkImageView(context);
        loadAfterShrinkImageView.setOrientationChangeListener(new b(this));
        return loadAfterShrinkImageView;
    }

    public void recycle() {
        this.mBlackboard.erase("data://user/MoveTimelineViewRectInfo");
        super.recycle();
    }

    public LoadAfterShrinkView setTransformInfo(String str) {
        this.mTransformInfo = str;
        return this;
    }

    public LoadAfterShrinkView setTransitionInfo(TransitionInfo transitionInfo) {
        this.mTransitionInfo = transitionInfo;
        this.mMediaItem = transitionInfo.item;
        this.mBitmap = transitionInfo.bitmap;
        return this;
    }

    public void setViewMatrix(MediaItem mediaItem, ImageView imageView) {
        Object[] objArr;
        if (mediaItem != null) {
            Rect rect = null;
            if (!this.mBlackboard.isEmpty("data://user/MoveTimelineViewRectInfo")) {
                if (this.mSourceViewWidth == 0.0f && this.mTargetViewWidth == 0.0f && (objArr = (Object[]) this.mBlackboard.read("data://user/MoveTimelineViewRectInfo", null)) != null && objArr.length > 1) {
                    this.mSourceViewWidth = ((Float) objArr[0]).floatValue();
                    this.mTargetViewWidth = ((Float) objArr[1]).floatValue();
                }
                imageView.setImageMatrix(getInterpolatedImageMatrix(mediaItem, imageView, (this.mSourceViewWidth - ((float) imageView.getWidth())) / (this.mSourceViewWidth - this.mTargetViewWidth)));
            } else if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
                StoryHighlightRect.RectInfo displayRectInfo = getDisplayRectInfo(mediaItem, imageView);
                if (displayRectInfo != null) {
                    rect = displayRectInfo.imageCropRect;
                }
                this.mSourceRect = rect;
                this.mTargetRect = getTargetCropRect(mediaItem, imageView, getOrientation(mediaItem));
                imageView.setImageMatrix(getStoryImageMatrix(mediaItem, imageView));
            }
        }
    }
}
