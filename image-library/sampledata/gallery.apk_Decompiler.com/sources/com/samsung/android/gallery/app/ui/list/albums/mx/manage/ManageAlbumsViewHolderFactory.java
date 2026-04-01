package com.samsung.android.gallery.app.ui.list.albums.mx.manage;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountReorderHolder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ManageAlbumsViewHolderFactory extends AlbumsViewHolderFactory {
    public ManageAlbumsViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder createGridViewHolder(View view, int i2, boolean z) {
        return new AlbumTitleCountReorderHolder(view, i2, false, z);
    }

    public ListViewHolder createListViewHolder(View view, int i2, boolean z) {
        return new AlbumTitleCountReorderHolder(view, i2, false, z);
    }

    public int getGridLayoutId() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_blur_albums_image_grid_layout;
        }
        return R.layout.recycler_item_mx_albums_image_grid_layout;
    }

    public int getListLayoutId() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            return R.layout.recycler_item_mx_blur_albums_image_list_layout;
        }
        return R.layout.recycler_item_mx_albums_image_list_layout;
    }
}
