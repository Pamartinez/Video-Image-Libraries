package com.samsung.android.gallery.module.map.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$id;
import com.samsung.android.gallery.module.R$layout;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MarkerIconManager implements IMarkerIconManager {
    protected boolean mEnableSelectedMarker = true;
    private final ViewHolder[] mHolders = new ViewHolder[2];
    private int mSelectBackgroundResId;
    private boolean mTitleVisible = true;
    private int mUnselectBackgroundResId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder {
        final ImageView background;
        final TextView count;
        final ImageView image;
        private final boolean mIsSmall;
        private final int mRadius;
        final View root;
        private final boolean selected;

        public ViewHolder(View view, boolean z) {
            this(view, z, false);
        }

        private int getPopupHeightResId() {
            if (this.mIsSmall) {
                return R$dimen.map_small_marker_popup_height;
            }
            return R$dimen.map_marker_popup_height;
        }

        private int getPopupWidthResId() {
            if (this.mIsSmall) {
                return R$dimen.map_small_marker_popup_width;
            }
            return R$dimen.map_marker_popup_width;
        }

        private static int getRadiusResId(boolean z) {
            if (z) {
                return R$dimen.map_small_marker_thumbnail_radius;
            }
            return R$dimen.map_marker_thumbnail_radius;
        }

        private void invalidate(View view) {
            int i2;
            int i7;
            try {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                view.measure(makeMeasureSpec, makeMeasureSpec);
                i7 = view.getMeasuredWidth();
                i2 = view.getMeasuredHeight();
            } catch (Exception e) {
                Log.e((CharSequence) "MarkerIconManager", "invalidate failed", (Throwable) e);
                i7 = view.getResources().getDimensionPixelSize(getPopupWidthResId());
                i2 = view.getResources().getDimensionPixelSize(getPopupHeightResId());
            }
            view.layout(0, 0, i7, i2);
        }

        private void setViewMatrix(ImageView imageView, ThumbnailInterface thumbnailInterface) {
            int i2;
            Rect rect;
            if (thumbnailInterface != null && imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
                if (thumbnailInterface.isVideo() || thumbnailInterface.isBroken()) {
                    i2 = 0;
                } else {
                    i2 = thumbnailInterface.getOrientation();
                }
                int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
                int intrinsicHeight = imageView.getDrawable().getIntrinsicHeight();
                RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
                if (!RectUtils.isValidRect(cropRectRatio) || intrinsicWidth == intrinsicHeight) {
                    rect = null;
                } else {
                    rect = RectUtils.getSmartCropRect(imageView.getDrawable(), cropRectRatio, i2, true);
                }
                imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, true).withCropRect(rect).withOrientation(i2).withOrientationTag(thumbnailInterface.getOrientationTag())));
            }
        }

        public Bitmap createBitmap(Bitmap bitmap, ThumbnailInterface thumbnailInterface, int i2) {
            TextView textView = this.count;
            if (textView != null && i2 >= 0) {
                textView.setText(StringCompat.toReadableCount(i2));
            }
            invalidate(this.root);
            if (thumbnailInterface == null || !thumbnailInterface.isTransparent()) {
                this.image.setBackground((Drawable) null);
            }
            Bitmap roundedCornerBitmap = BitmapUtils.getRoundedCornerBitmap(bitmap, (float) this.mRadius);
            this.image.setScaleType(ImageView.ScaleType.MATRIX);
            this.image.setImageBitmap(roundedCornerBitmap);
            setViewMatrix(this.image, thumbnailInterface);
            Bitmap createBitmap = BitmapUtils.createBitmap(this.root.getMeasuredWidth(), this.root.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(0);
            this.root.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        public String toString() {
            return "ViewHolder{" + this.root.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.root.getHeight() + "}";
        }

        public ViewHolder(View view, boolean z, boolean z3) {
            this.selected = z;
            this.root = view;
            ImageView imageView = (ImageView) view.findViewById(R$id.map_view_marker_background);
            this.background = imageView;
            this.image = (ImageView) view.findViewById(R$id.map_view_marker);
            this.count = (TextView) view.findViewById(R$id.map_view_marker_count);
            if (z) {
                imageView.setBackgroundResource(R$drawable.gallery_map_view_popup_normal_selected);
            }
            this.mIsSmall = z3;
            this.mRadius = (int) view.getResources().getDimension(getRadiusResId(z3));
        }
    }

    public MarkerIconManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.mHolders[0] = createViewHolder(context, false);
        this.mHolders[1] = createViewHolder(context, true);
    }

    public ViewHolder createViewHolder(Context context, boolean z) {
        return new ViewHolder(getMapViewPopupLayout(context), z);
    }

    public MarkerIconManager enableSelectedMarker(boolean z) {
        this.mEnableSelectedMarker = z;
        return this;
    }

    public View getMapViewPopupLayout(Context context) {
        return View.inflate(context, R$layout.map_view_popup_layout, (ViewGroup) null);
    }

    public Bitmap makeIcon(Bitmap bitmap, int i2, boolean z, ThumbnailInterface thumbnailInterface) {
        char c5;
        int i7;
        ImageView imageView;
        if (!z || !this.mEnableSelectedMarker) {
            c5 = 0;
        } else {
            c5 = 1;
        }
        ViewHolder viewHolder = this.mHolders[c5];
        if (c5 != 0) {
            i7 = this.mSelectBackgroundResId;
        } else {
            i7 = this.mUnselectBackgroundResId;
        }
        if (i7 > 0) {
            viewHolder.background.setBackgroundResource(i7);
        }
        if (bitmap == null) {
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(viewHolder.root.getContext(), R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg);
            Log.e("MarkerIconManager", "makeIcon : error icon");
        }
        if (!(!this.mTitleVisible || c5 == 0 || (imageView = viewHolder.image) == null)) {
            imageView.setColorFilter(new PorterDuffColorFilter(1073741824, PorterDuff.Mode.SRC_ATOP));
        }
        if (!this.mTitleVisible) {
            i2 = -1;
        }
        return viewHolder.createBitmap(bitmap, thumbnailInterface, i2);
    }

    public void setBackgroundResource(int i2, int i7) {
        this.mSelectBackgroundResId = i2;
        this.mUnselectBackgroundResId = i7;
    }

    public void setMarkerTitleVisibility(boolean z) {
        this.mTitleVisible = z;
    }

    public void updateSize(Context context) {
        init(context);
    }
}
