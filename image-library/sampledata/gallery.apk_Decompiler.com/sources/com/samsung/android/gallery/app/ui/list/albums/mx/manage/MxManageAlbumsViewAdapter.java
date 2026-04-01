package com.samsung.android.gallery.app.ui.list.albums.mx.manage;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.IMxManageAlbumsView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxManageAlbumsViewAdapter<V extends IMxManageAlbumsView> extends AlbumsViewAdapter<V> {
    public MxManageAlbumsViewAdapter(V v, String str) {
        super(v, str);
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new ManageAlbumsViewHolderFactory(context);
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        super.onSelected(i2, z, z3);
        ((IMxManageAlbumsView) this.mView).onItemSelected(i2, z);
    }
}
