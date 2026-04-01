package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPicturesViewHolderFactory extends PicturesViewHolderFactory {
    public SharingPicturesViewHolderFactory(Context context) {
        super(context);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_sharing_pictures_image_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        return new SharingPicturesItemViewHolder(view, i2);
    }
}
