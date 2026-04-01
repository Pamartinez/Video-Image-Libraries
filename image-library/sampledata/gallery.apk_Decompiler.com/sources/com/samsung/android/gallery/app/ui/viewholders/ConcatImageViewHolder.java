package com.samsung.android.gallery.app.ui.viewholders;

import B2.i;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConcatImageViewHolder extends ListViewHolder {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768 && ConcatImageViewHolder.this.mListener != null) {
                ConcatImageViewHolder.this.mListener.onAccessibilityClicked(view, ConcatImageViewHolder.this.getViewType(), ConcatImageViewHolder.this.getViewPosition());
            }
        }
    };
    private Bitmap mBitmap;
    private ImageView mImageView;
    /* access modifiers changed from: private */
    public ListViewHolder.OnConcatThumbListener mListener;

    public ConcatImageViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ListViewHolder.OnConcatThumbListener onConcatThumbListener;
        if (motionEvent.getAction() != 1 || (onConcatThumbListener = this.mListener) == null || this.mImageView == null) {
            return false;
        }
        onConcatThumbListener.onClicked(getViewType(), getViewPosition(), motionEvent.getX(), motionEvent.getY(), motionEvent.getRawX(), ((FrameLayout) view.getParent()).getY());
        return false;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.itemView.setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public void bindImage(Bitmap bitmap) {
        ImageView imageView;
        if (this.mMediaItem == null || (imageView = this.mImageView) == null) {
            Log.w(this.TAG, "bindImage but mMediaItem is null or mImageView is null");
        } else if (bitmap != null && bitmap == this.mBitmap) {
            imageView.invalidate();
        } else if (bitmap != null) {
            this.mBitmap = bitmap;
            imageView.setImageBitmap(bitmap);
        }
    }

    public void bindView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.mImageView = imageView;
        imageView.setOnTouchListener(new i(15, this));
        seslSetViewHolderRecoilEffectEnabled(false);
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public ImageView getImage() {
        return this.mImageView;
    }

    public ThumbKind getThumbKind() {
        if (ViewHolderValue.isYear(getViewType())) {
            return ThumbKind.MINI_KIND;
        }
        return ThumbKind.SMALL_CROP_KIND;
    }

    public void recycle() {
        this.mImageView.setImageDrawable((Drawable) null);
        this.mImageView.setImageMatrix((Matrix) null);
        this.mImageView.setTransitionName((String) null);
        this.mImageView.setVisibility(0);
        this.itemView.setAccessibilityDelegate((View.AccessibilityDelegate) null);
        this.mBitmap = null;
        this.mListener = null;
        super.recycle();
    }

    public boolean requireThumbnail() {
        return true;
    }

    public void setOnConcatThumbListener(ListViewHolder.OnConcatThumbListener onConcatThumbListener) {
        this.mListener = onConcatThumbListener;
    }
}
