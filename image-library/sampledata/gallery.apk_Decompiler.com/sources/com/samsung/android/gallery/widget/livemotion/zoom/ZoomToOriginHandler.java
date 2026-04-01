package com.samsung.android.gallery.widget.livemotion.zoom;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.ImageMatrixAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZoomToOriginHandler extends ZoomInOutHandler {
    private Matrix mFromMatrix;
    private final ListViewHolder mViewHolder;

    public ZoomToOriginHandler(ListViewHolder listViewHolder) {
        this.mViewHolder = listViewHolder;
    }

    private float[] getCenterPos(View view) {
        RectF viewRect = ViewUtils.getViewRect(view);
        return new float[]{(viewRect.left + viewRect.right) / 2.0f, (viewRect.top + viewRect.bottom) / 2.0f};
    }

    private void prepareZoom(ListViewHolder listViewHolder) {
        float[] centerPos = getCenterPos(listViewHolder.itemView);
        onScaleBegin(listViewHolder.itemView.findViewById(R$id.transform_parent_layout), centerPos[0], centerPos[1]);
        onScale(listViewHolder.getImage(), getMinScale());
    }

    public void restore(boolean z) {
        ImageView image = this.mViewHolder.getImage();
        if (!z || this.mFromMatrix == null) {
            image.invalidate();
            return;
        }
        PropertyAnimator duration = new ImageMatrixAnimator(image, this.mFromMatrix, image.getImageMatrix()).setDuration(MOCRLang.KHMER);
        duration.setStartPoint();
        duration.start();
    }

    public void setFromMatrix(Matrix matrix) {
        this.mFromMatrix = matrix;
    }

    public void zoom(ListViewHolder listViewHolder, boolean z) {
        prepareZoom(listViewHolder);
        ImageView image = listViewHolder.getImage();
        if (!z || this.mFromMatrix == null) {
            image.invalidate();
            return;
        }
        PropertyAnimator duration = new ImageMatrixAnimator(image, this.mFromMatrix, image.getImageMatrix()).setDuration(MOCRLang.KHMER);
        duration.setStartPoint();
        duration.start();
    }
}
