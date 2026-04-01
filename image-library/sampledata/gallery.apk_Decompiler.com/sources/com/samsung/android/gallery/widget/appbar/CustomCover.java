package com.samsung.android.gallery.widget.appbar;

import B8.e;
import O3.b;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import bc.d;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import j4.a;
import java.util.function.Supplier;
import kb.C0697a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CustomCover {
    protected final String TAG = getClass().getSimpleName();
    protected Bitmap mBitmap;
    protected Supplier<Boolean> mCoverVisibilitySupplier = new a(16);
    protected ImageView mImageView;
    protected MediaItem mMediaItem;
    protected View.OnClickListener mOnItemClickListener;
    protected ViewStub mStub;
    protected CharSequence mSubTitle;
    protected TextView mSubTitleView;
    protected CharSequence mTitle;
    protected TextView mTitleView;
    protected View mView;

    public CustomCover(View view) {
        if (view instanceof ViewStub) {
            ViewStub viewStub = (ViewStub) view;
            this.mStub = viewStub;
            this.mView = viewStub.inflate();
        } else {
            this.mView = view;
        }
        bindViews(this.mView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$2(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) mediaItem, (Object) bitmap, 28));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$3(ImageView imageView, Bitmap bitmap, MediaItem mediaItem) {
        if (this.mImageView != null) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            imageView.setImageBitmap(bitmap);
            imageView.setBackground((Drawable) null);
            updateImageMatrix(imageView, mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSubTitle$5(CharSequence charSequence) {
        updateTextView(this.mSubTitleView, charSequence);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTitle$4(CharSequence charSequence) {
        updateTextView(this.mTitleView, charSequence);
    }

    private void setSubTitle(CharSequence charSequence) {
        if (this.mSubTitleView == null) {
            return;
        }
        if (!TextUtils.equals(this.mSubTitle, charSequence) || charSequence == null) {
            this.mSubTitle = charSequence;
            ThreadUtil.runOnUiThread(new C0697a(this, charSequence, 1));
        }
    }

    private void setTitle(CharSequence charSequence) {
        if (this.mTitleView != null && !TextUtils.equals(this.mTitle, charSequence)) {
            this.mTitle = charSequence;
            ThreadUtil.runOnUiThread(new C0697a(this, charSequence, 0));
        }
    }

    private void updateTextSize(TextView textView, boolean z) {
        int i2;
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR && textView != null) {
            if (z) {
                i2 = R$dimen.custom_cover_title_font_small;
            } else {
                i2 = R$dimen.custom_cover_title_font_normal;
            }
            textView.setTextSize(0, textView.getResources().getDimension(i2));
        }
    }

    public void bindImage(MediaItem mediaItem) {
        if (mediaItem != null && !equalsItem(this.mMediaItem, mediaItem)) {
            this.mMediaItem = mediaItem.clone();
            Log.d(this.TAG, "bindItem", MediaItemUtil.getSimpleLog(mediaItem));
            ThumbnailLoader.getInstance().getOrLoad(mediaItem, getThumbKind(), new e(mediaItem, 1), new b(29, this, mediaItem));
        }
    }

    public abstract void bindViews(View view);

    public Matrix createImageMatrix(ImageView imageView, MediaItem mediaItem) {
        return ViewMatrixUtils.createImageMatrix(imageView, mediaItem, false);
    }

    public void destroy() {
        this.mView = null;
        this.mStub = null;
        this.mTitleView = null;
        this.mSubTitleView = null;
        this.mMediaItem = null;
        this.mTitle = null;
        this.mSubTitle = null;
        this.mBitmap = null;
        this.mOnItemClickListener = null;
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!MediaItemUtil.equals(mediaItem, mediaItem2) || !RectUtils.equalsRectF(mediaItem.getCropRectRatio(), mediaItem2.getCropRectRatio()) || mediaItem.isCustomCover() != mediaItem2.isCustomCover()) {
            return false;
        }
        return true;
    }

    public ThumbKind getThumbKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void handleDensityChange(int i2) {
        boolean z;
        if (this.mStub != null) {
            View view = this.mView;
            if (view == null || view.getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.replaceView(this.mView, this.mStub);
            View inflate = this.mStub.inflate();
            this.mView = inflate;
            bindViews(inflate);
            if (!z) {
                this.mView.setVisibility(8);
            }
        }
        updateViews();
    }

    public abstract void handleOrientationChange(int i2);

    public void setAlphaOnCoverView(float f) {
        if (ViewUtils.isVisible(this.mView)) {
            ViewUtils.setAlpha(this.mView, f);
        }
    }

    public void setCoverVisibilitySupplier(Supplier<Boolean> supplier) {
        this.mCoverVisibilitySupplier = supplier;
    }

    public void setEnabled(boolean z) {
        int i2;
        View view = this.mView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public void setImageVisibility(boolean z) {
        int i2;
        ImageView imageView = this.mImageView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(imageView, i2);
    }

    public void updateDecorView(int i2, Object obj) {
        if (i2 == 0) {
            bindImage((MediaItem) obj);
        } else if (i2 == 1) {
            setTitle((CharSequence) obj);
        } else if (i2 != 2) {
            A.a.B(i2, "updateDecorView not supported #", this.TAG);
        } else {
            setSubTitle((CharSequence) obj);
        }
    }

    public void updateImageMatrix(ImageView imageView, MediaItem mediaItem) {
        if (imageView != null && imageView.getDrawable() != null && mediaItem != null) {
            imageView.setImageMatrix(createImageMatrix(imageView, mediaItem));
        }
    }

    public void updateImageView(boolean z) {
        setImageVisibility(z);
    }

    public void updateShape(ImageView imageView) {
        float dimension = imageView.getResources().getDimension(R$dimen.album_view_corner_radius_list);
        Drawable drawable = imageView.getResources().getDrawable(R$drawable.custom_cover_image_border, (Resources.Theme) null);
        ViewUtils.setViewShape(imageView, 1, dimension);
        ViewUtils.setShapeBorder(imageView, drawable);
    }

    public void updateTextView(TextView textView, CharSequence charSequence) {
        int i2;
        if (textView != null) {
            textView.setText(charSequence);
            if (charSequence == null) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            textView.setVisibility(i2);
        }
    }

    public abstract void updateViewLayout(int i2);

    public void updateViews() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            this.mMediaItem = null;
            bindImage(mediaItem);
        }
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            this.mTitle = null;
            setTitle(charSequence);
        }
        CharSequence charSequence2 = this.mSubTitle;
        if (charSequence2 != null) {
            this.mSubTitle = null;
            setSubTitle(charSequence2);
        }
    }

    /* renamed from: bindImage */
    public void lambda$bindImage$1(MediaItem mediaItem, Bitmap bitmap) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            this.mBitmap = bitmap;
            boolean z = false;
            updateImageView(bitmap != null);
            TextView textView = this.mTitleView;
            if (bitmap != null) {
                z = true;
            }
            updateTextSize(textView, z);
            if (bitmap != null) {
                updateShape(imageView);
                updateBlur();
                imageView.post(new a8.d(this, imageView, bitmap, mediaItem));
            }
        }
    }

    public CustomCover(View view, int i2) {
        if (view instanceof ViewStub) {
            ViewStub viewStub = (ViewStub) view;
            this.mStub = viewStub;
            viewStub.setLayoutResource(i2);
            this.mView = this.mStub.inflate();
        } else {
            this.mView = view;
        }
        bindViews(this.mView);
    }

    public void updateBlur() {
    }
}
