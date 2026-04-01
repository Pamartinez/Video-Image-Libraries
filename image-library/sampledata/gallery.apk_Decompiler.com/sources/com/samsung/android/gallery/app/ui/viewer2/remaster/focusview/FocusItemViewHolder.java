package com.samsung.android.gallery.app.ui.viewer2.remaster.focusview;

import Ob.a;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FocusItemViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = getClass().getSimpleName();
    private Bitmap mBitmap;
    private final ImageView mFocusedImageView;
    private MediaItem mItem;

    public FocusItemViewHolder(View view) {
        super(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.focused_image_view);
        this.mFocusedImageView = imageView;
        applyRound(imageView);
    }

    private void applyRound(ImageView imageView) {
        imageView.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), ((float) view.getWidth()) / 2.0f);
            }
        });
        imageView.setClipToOutline(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCropRectRatio$0(RectF rectF) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null) {
            Log.e(this.TAG, "failed to set image matrix, bitmap is null");
            return;
        }
        this.mFocusedImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mFocusedImageView, false).withCropRect(RectUtils.getSmartCropRect(bitmap, rectF, this.mItem.getOrientation(), false)).withOrientation(this.mItem.getOrientation())));
    }

    public void setCropRectRatio(RectF rectF) {
        this.mFocusedImageView.setImageMatrix(new Matrix());
        ViewUtils.post(this.mFocusedImageView, new a(18, this, rectF));
    }

    public void setImage(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mFocusedImageView.setImageBitmap(bitmap);
        this.mFocusedImageView.setVisibility(0);
    }

    public void setItem(MediaItem mediaItem) {
        this.mItem = mediaItem;
    }
}
