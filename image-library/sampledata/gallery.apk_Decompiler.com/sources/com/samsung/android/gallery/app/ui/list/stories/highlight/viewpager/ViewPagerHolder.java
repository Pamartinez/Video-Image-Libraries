package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerHolder extends PreviewViewHolder implements View.OnApplyWindowInsetsListener, ILargeImage {
    private boolean mApplyOriginScale;
    private ImageView mBackUpImageView;
    private ViewGroup mBlurContainer;
    private ImageView mBlurView;
    private boolean mIsFilterMode;
    public final CopyOnWriteArrayList<Integer> mLargeBitmapHash = new CopyOnWriteArrayList<>();
    private boolean mOriginImageLoaded;
    private ViewGroup mTransformLayout;
    private ViewGroup mTransformParent;

    public ViewPagerHolder(View view, int i2) {
        super(view, i2);
    }

    private int getOrientation(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isVideo() || mediaItem.isBroken()) {
            return 0;
        }
        return mediaItem.getOrientation();
    }

    private boolean isBitmapRecyclable() {
        return !this.mIsFilterMode;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$layoutTransformView$0(Drawable drawable) {
        updateTransformView(this.itemView, drawable);
    }

    private void layoutTransformView(Drawable drawable) {
        View findViewById;
        if (this.itemView.getWidth() > 0) {
            updateTransformView(this.itemView, drawable);
        } else if (this.itemView.getParent() != null && ((View) this.itemView.getParent()).getWidth() > 0) {
            updateTransformView((View) this.itemView.getParent(), drawable);
        } else if (!(getContext() instanceof Activity) || (findViewById = ((Activity) getContext()).findViewById(R.id.motion_viewpager)) == null || findViewById.getWidth() <= 0) {
            this.itemView.post(new e(29, this, drawable));
            Log.d(this.TAG, "not measure yet", Integer.valueOf(getAbsoluteAdapterPosition()));
        } else {
            updateTransformView(findViewById, drawable);
        }
    }

    private void layoutView(View view, int i2, int i7) {
        view.layout(0, 0, i2, i7);
        ViewGroup.LayoutParams layoutParams = this.mBackUpImageView.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i7;
        view.setLayoutParams(layoutParams);
    }

    private void setViewMatrixForFit(ImageView imageView) {
        imageView.setImageMatrix(ImageMatrix.createFitCenter(ImageMatrix.MatrixParam.create(imageView, false).withOrientation(getOrientation(this.mMediaItem)).withOrientationTag(this.mMediaItem.getOrientationTag())));
    }

    private boolean setViewMatrixWithCropRect(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return false;
        }
        StoryHighlightRect.RectInfo build = new StoryHighlightRect.RectBuilder(this.mMediaItem).setScreenRect(new Rect(0, 0, this.itemView.getWidth(), this.itemView.getHeight())).setImageSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).build();
        Rect rect = build.imageCropRect;
        drawDebugView(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), build);
        if (RectUtils.isValidRect(rect)) {
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, false).withCropRect(rect).withOrientation(getOrientation(this.mMediaItem)).withOrientationTag(this.mMediaItem.getOrientationTag())));
            if (!PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE || !this.mApplyOriginScale) {
                return true;
            }
            ZoomOriginHelper.applyOriginScale(this, false);
            return true;
        }
        Log.w((CharSequence) this.TAG, "invalid crop rect", Integer.valueOf(getAbsoluteAdapterPosition()), Integer.valueOf(this.itemView.getWidth()), Integer.valueOf(this.itemView.getHeight()));
        return false;
    }

    private void updateTransformView(View view, Drawable drawable) {
        updateTransformView(view.getWidth(), view.getHeight(), drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public void bind(MediaItem mediaItem) {
        MediaItem mediaItem2 = this.mMediaItem;
        super.bind(mediaItem);
        this.itemView.setOnApplyWindowInsetsListener(this);
        this.itemView.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        ViewUtils.setVisibility(this.mBackUpImageView, 8);
        if (mediaItem2 != null && !MediaItemUtil.equals(mediaItem, mediaItem2)) {
            Log.d(this.TAG, "bind without recycle");
            this.mOriginImageLoaded = false;
        }
    }

    public void bindBackupThumbnail(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i2) {
        if (!(bitmap2 == null || bitmap == null || this.itemView.getWidth() <= 0)) {
            ViewUtils.setVisibility(this.mBackUpImageView, 0);
            updateTransformView(this.itemView, bitmap);
            ViewGroup.LayoutParams layoutParams = this.mTransformParent.getLayoutParams();
            layoutView(this.mBackUpImageView, layoutParams.width, layoutParams.height);
            this.mBackUpImageView.setImageBitmap(bitmap2);
            this.mBackUpImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mBackUpImageView, true).withOrientation(i2)));
        }
        if (bitmap3 != null) {
            bindBlurBitmap(this.mMediaItem, bitmap3);
        }
    }

    public void bindBlurBitmap(MediaItem mediaItem, Bitmap bitmap) {
        MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 != null && mediaItem == mediaItem2) {
            this.mBlurView.setImageBitmap(bitmap);
            this.mImageView.setBackground((Drawable) null);
        }
    }

    public void bindImage(Bitmap bitmap) {
        if (!isOriginImageLoaded()) {
            super.bindImage(bitmap);
            this.mBackUpImageView.setImageDrawable((Drawable) null);
            ViewUtils.setVisibility(this.mBackUpImageView, 8);
            Drawable drawable = this.mImageView.getDrawable();
            if (drawable != null) {
                layoutTransformView(drawable);
            }
        }
    }

    public void bindOriginImage(Bitmap bitmap) {
        if (this.mBitmap != bitmap) {
            this.mOriginImageLoaded = false;
            bindImage(bitmap);
            if (bitmap != null) {
                this.mLargeBitmapHash.add(Integer.valueOf(bitmap.hashCode()));
            }
            this.mOriginImageLoaded = true;
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mBackUpImageView = (ImageView) view.findViewById(R.id.backup_thumbnail);
        this.mTransformParent = (ViewGroup) view.findViewById(R.id.transform_parent_layout);
        this.mTransformLayout = (ViewGroup) view.findViewById(R.id.transform_layout);
        this.mBlurView = (ImageView) view.findViewById(R.id.blur_view);
        this.mBlurContainer = (ViewGroup) view.findViewById(R.id.blur_container);
    }

    public View getBlurContainer() {
        return this.mBlurContainer;
    }

    public Drawable getBlurDrawable() {
        return this.mBlurView.getDrawable();
    }

    public View getDecoView(int i2) {
        if (i2 == 35) {
            return this.mTransformLayout;
        }
        if (i2 == 36) {
            return this.mTransformParent;
        }
        return super.getDecoView(i2);
    }

    public View getTransformParentLayout() {
        return this.mTransformParent;
    }

    public boolean isOriginImageLoaded() {
        return this.mOriginImageLoaded;
    }

    public void onFilterChanged() {
        this.mOriginImageLoaded = false;
    }

    public void recycle() {
        super.recycle();
        this.mOriginImageLoaded = false;
        ViewUtils.setVisibility(this.mImageView, 0);
        this.mBackUpImageView.setImageDrawable((Drawable) null);
        ViewUtils.setVisibility(this.mBackUpImageView, 8);
        this.mBlurView.setImageDrawable((Drawable) null);
        this.itemView.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        this.mIsFilterMode = false;
        this.mApplyOriginScale = false;
        resetViewProperty();
    }

    public void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !this.mLargeBitmapHash.remove(Integer.valueOf(bitmap.hashCode())) && isBitmapRecyclable()) {
            super.recycleBitmap(bitmap);
        }
    }

    public void resetViewProperty() {
        resetViewProperty(this.mTransformParent);
        resetViewProperty(this.mTransformLayout);
        resetViewProperty(this.itemView);
    }

    public void setFilterMode(boolean z) {
        this.mIsFilterMode = z;
    }

    public void setOriginScale(boolean z, boolean z3) {
        if (PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE) {
            this.mApplyOriginScale = z;
            if (z3 && this.mImageView.getWidth() > 0 && this.mImageView.getDrawable() != null) {
                setViewMatrix();
                this.mImageView.invalidate();
            }
        }
    }

    public void setViewMatrix() {
        setViewMatrix(this.mImageView);
    }

    private void updateTransformView(View view, Bitmap bitmap) {
        updateTransformView(view.getWidth(), view.getHeight(), bitmap.getWidth(), bitmap.getHeight());
    }

    public void setViewMatrix(ImageView imageView) {
        if (this.mMediaItem != null && imageView.getWidth() > 0) {
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSmartCrop) || !setViewMatrixWithCropRect(imageView)) {
                setViewMatrixForFit(imageView);
            }
        }
    }

    public void updateTransformView(int i2, int i7, int i8, int i10) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            updateTransformView(new StoryHighlightRect.RectBuilder(mediaItem).setScreenRect(new Rect(0, 0, i2, i7)).setImageSize(i8, i10).withSmartCrop(PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSmartCrop)).build().displayRect);
        }
    }

    private void resetViewProperty(View view) {
        ViewUtils.setAlpha(view, 1.0f);
        ViewUtils.setTranslationX(view, 0.0f);
        ViewUtils.setTranslationY(view, 0.0f);
        ViewUtils.setScale(view, 1.0f, 1.0f);
        ViewUtils.resetPivot(view);
    }

    public void updateTransformView(Rect rect) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTransformParent.getLayoutParams();
        if (!(marginLayoutParams.width == rect.width() && marginLayoutParams.height == rect.height())) {
            marginLayoutParams.width = rect.width();
            marginLayoutParams.height = rect.height();
            this.mTransformParent.setLayoutParams(marginLayoutParams);
            this.mTransformLayout.layout(0, 0, marginLayoutParams.width, marginLayoutParams.height);
        }
        setViewMatrix();
    }

    public void onViewIn() {
    }

    public void onViewOut() {
    }

    public void setPositionSupplier(Supplier<Integer> supplier) {
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return windowInsets;
    }

    private void drawDebugView(int i2, int i7, StoryHighlightRect.RectInfo rectInfo) {
    }
}
