package com.samsung.android.gallery.widget.filmstrip3;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.databinding.Filmstrip3ImageItemLayoutBinding;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripImageViewHolder extends FilmStripViewHolder<Filmstrip3ImageItemLayoutBinding> {
    public FilmStripImageViewHolder(Filmstrip3ImageItemLayoutBinding filmstrip3ImageItemLayoutBinding) {
        super(filmstrip3ImageItemLayoutBinding);
    }

    public void bindView(MediaItem mediaItem, int i2) {
        super.bindView(mediaItem, i2);
        setDefaultImage(((Filmstrip3ImageItemLayoutBinding) this.mViewBinding).filmStripViewImage);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ((Filmstrip3ImageItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageDrawable((Drawable) null);
        ((Filmstrip3ImageItemLayoutBinding) this.mViewBinding).filmStripViewImage.setImageMatrix((Matrix) null);
    }
}
