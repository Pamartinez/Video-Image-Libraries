package com.samsung.android.gallery.app.ui.list.mtp.pictures;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MtpPicturesViewAdapter extends PicturesViewAdapter<IPicturesView> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MtpImageViewHolder extends ImageViewHolder {
        public MtpImageViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void setViewMatrix() {
            this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MtpPicturesViewHolderFactory extends PicturesViewHolderFactory {
        public MtpPicturesViewHolderFactory(Context context) {
            super(context);
        }

        public ListViewHolder getDataViewHolder(View view, int i2) {
            return new MtpImageViewHolder(view, i2);
        }
    }

    public MtpPicturesViewAdapter(IPicturesView iPicturesView, String str) {
        super(iPicturesView, str, false);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new MtpPicturesViewHolderFactory(context);
    }
}
