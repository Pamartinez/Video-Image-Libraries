package com.samsung.android.gallery.app.ui.list.albums.hide;

import H4.a;
import android.content.Context;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.hide.IHideAlbumsView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsHidePresenter<V extends IHideAlbumsView> extends AlbumsBasePresenter<V> {
    public AlbumsHidePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        return null;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.hide_albums);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((int) R.string.hide_albums);
        toolbar.post(new a(toolbar, 0));
        setNavigationUpButton(toolbar);
    }
}
