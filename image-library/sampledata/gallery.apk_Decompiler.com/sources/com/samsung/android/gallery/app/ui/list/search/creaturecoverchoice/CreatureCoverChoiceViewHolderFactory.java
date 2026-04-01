package com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoiceViewHolderFactory extends PicturesViewHolderFactory {
    public CreatureCoverChoiceViewHolderFactory(Context context) {
        super(context);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_creature_choice_image_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        return new CreatureCoverChoiceViewHolder(view, i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreatureCoverChoiceViewHolder extends ImageViewHolder {
        public CreatureCoverChoiceViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bindView(View view) {
            super.bindView(view);
            setThumbnailShape(1, (float) this.itemView.getResources().getDimensionPixelSize(R.dimen.creature_cover_choice_thumbnail_radius));
            View view2 = this.itemView;
            ViewMarginUtils.setPadding(view2, view2.getResources().getDimensionPixelSize(R.dimen.creature_cover_choice_thumbnail_padding));
        }

        public void setViewMatrix() {
            int i2;
            int i7;
            if (this.mBitmap != null) {
                RectF rotatedRectFRatio = RectUtils.getRotatedRectFRatio(this.mMediaItem.getFaceRect(), this.mMediaItem.getOrientation());
                if (this.mMediaItem.isVideo()) {
                    i2 = 0;
                } else {
                    i2 = this.mMediaItem.getOrientation();
                }
                if (this.mMediaItem.isVideo()) {
                    i7 = 0;
                } else {
                    i7 = this.mMediaItem.getOrientationTag();
                }
                this.mImageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mImageView, false).withCropRect(RectUtils.getSmartCropRect(this.mBitmap, rotatedRectFRatio, i2, true, 1)).withOrientation(i2).withOrientationTag(i7)));
                return;
            }
            super.setViewMatrix();
        }

        public void setDecoViewVisibility(MediaItem mediaItem) {
        }
    }
}
