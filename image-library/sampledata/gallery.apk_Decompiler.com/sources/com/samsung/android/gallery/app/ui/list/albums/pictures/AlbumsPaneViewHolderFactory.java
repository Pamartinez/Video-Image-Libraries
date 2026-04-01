package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsPaneViewHolderFactory extends AlbumsViewHolderFactory {
    public AlbumsPaneViewHolderFactory(Context context) {
        super(context);
    }

    public int getGridLayoutId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_all_albums_pane_image_layout;
        }
        return R.layout.recycler_item_albums_pane_image_layout;
    }

    public int getGridRoundRadiusId() {
        return R.dimen.albums_pane_corner_radius;
    }
}
