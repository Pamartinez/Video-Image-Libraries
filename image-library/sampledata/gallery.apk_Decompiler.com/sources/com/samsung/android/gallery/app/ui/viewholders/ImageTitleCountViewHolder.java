package com.samsung.android.gallery.app.ui.viewholders;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTitleCountViewHolder extends ImageTitleViewHolder {
    private TextView mCountText;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IconViewHolder extends ImageTitleCountViewHolder {
        private ImageView mIconView;

        public IconViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bindIcon(int i2) {
            if (i2 > 0) {
                ImageView imageView = this.mIconView;
                imageView.setImageDrawable(imageView.getContext().getDrawable(i2));
                this.mIconView.setVisibility(0);
                return;
            }
            this.mIconView.setVisibility(8);
        }

        public void bindView(View view) {
            ImageTitleCountViewHolder.super.bindView(view);
            this.mIconView = (ImageView) view.findViewById(R.id.icon);
        }

        public void recycle() {
            ImageTitleCountViewHolder.super.recycle();
            this.mIconView.setImageDrawable((Drawable) null);
        }
    }

    public ImageTitleCountViewHolder(View view, int i2) {
        super(view, i2);
    }

    private int getItemCount(MediaItem mediaItem) {
        if (mediaItem != null) {
            return mediaItem.getCount();
        }
        return 0;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mCountText.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(getItemCount(mediaItem))}));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCountText = (TextView) view.findViewById(R.id.item_count);
    }

    public void recycle() {
        super.recycle();
        this.mCountText.setText((CharSequence) null);
    }
}
