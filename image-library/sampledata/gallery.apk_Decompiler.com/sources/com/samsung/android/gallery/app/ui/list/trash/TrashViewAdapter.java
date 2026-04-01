package com.samsung.android.gallery.app.ui.list.trash;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TrashViewAdapter<V extends IPicturesView> extends PicturesHeaderViewAdapter<V> {
    public TrashViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new TrashViewHolderFactory(context);
    }

    public int getDividerIndex(int i2) {
        return -1;
    }

    public int getHeaderDescriptionViewId() {
        return R.id.trash_description;
    }

    public int getHeaderDescriptionWidthOffset() {
        return 0;
    }
}
