package com.samsung.android.gallery.widget.dragdrop.dragshadow;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragShadowInfo {
    public Paint getBorderPaint(Context context) {
        Paint paint = new Paint();
        paint.setColor(context.getColor(R$color.preview_border_color));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(context.getResources().getDimension(R$dimen.drag_and_drop_border_stroke));
        return paint;
    }

    public int getCountIconSidePadding(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.header_count_side_padding);
    }

    public int getCountIconSize(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.header_count_size);
    }

    public int getLayerOffset(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.popup_preview_layer_offset);
    }

    public float getRadius(Context context) {
        return context.getResources().getDimension(R$dimen.drag_and_drop_thumb_radius);
    }

    public int getSecondLayerColor(Context context) {
        return context.getColor(R$color.preview_second_layer_bg);
    }

    public int getThirdLayerColor(Context context) {
        return context.getColor(R$color.preview_third_layer_bg);
    }

    public int getThumbSize(Context context, ListViewHolder listViewHolder) {
        View view;
        if (listViewHolder.getImage() != null) {
            view = listViewHolder.getImage();
        } else {
            view = listViewHolder.itemView;
        }
        int min = Math.min(view.getWidth(), view.getHeight());
        return Math.min(context.getResources().getDimensionPixelSize(R$dimen.drag_and_drop_thumb_size_max), Math.max(context.getResources().getDimensionPixelSize(R$dimen.drag_and_drop_thumb_size_min), min));
    }

    public boolean isRtl() {
        return Features.isEnabled(Features.IS_RTL);
    }
}
