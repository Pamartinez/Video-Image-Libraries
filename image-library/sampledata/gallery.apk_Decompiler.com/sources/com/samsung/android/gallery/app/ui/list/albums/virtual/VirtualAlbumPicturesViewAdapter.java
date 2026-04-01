package com.samsung.android.gallery.app.ui.list.albums.virtual;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VirtualAlbumPicturesViewAdapter<V extends IPicturesView> extends PicturesHeaderViewAdapter<V> {
    public VirtualAlbumPicturesViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    public void enableHeaderView(boolean z) {
        View view = this.mHeaderView;
        if (view != null) {
            ViewUtils.setAllViewEnabled(view.findViewById(R.id.tip_card_layout), z, true);
        }
    }
}
