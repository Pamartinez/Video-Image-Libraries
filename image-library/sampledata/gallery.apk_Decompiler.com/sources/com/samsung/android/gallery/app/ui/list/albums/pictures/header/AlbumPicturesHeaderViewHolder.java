package com.samsung.android.gallery.app.ui.list.albums.pictures.header;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumPicturesHeaderViewHolder extends ListViewHolder {
    private ImageView mImage;

    public AlbumPicturesHeaderViewHolder(View view, int i2) {
        super(view, i2);
        ViewUtils.setViewShape(this.mImage, 1, view.getResources().getDimension(R.dimen.suggestions_item_round_radius));
        ViewUtils.setShapeBorder(this.mImage, view.getContext().getDrawable(R.drawable.suggestions_thumbnail_border));
    }

    public void bindImage(Bitmap bitmap) {
        int i2;
        this.mImage.setImageBitmap(bitmap);
        ImageView imageView = this.mImage;
        if (bitmap == null) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        ViewUtils.setVisibility(imageView, i2);
    }

    public void bindView(View view) {
        this.mImage = (ImageView) view.findViewById(R.id.thumbnail);
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mImage, 8);
    }

    public void setThumbnailBackgroundColor(int i2) {
        ViewUtils.setBackgroundColor(this.mImage, i2);
    }
}
