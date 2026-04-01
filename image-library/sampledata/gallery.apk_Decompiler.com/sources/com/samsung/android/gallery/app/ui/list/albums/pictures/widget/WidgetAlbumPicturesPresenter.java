package com.samsung.android.gallery.app.ui.list.albums.pictures.widget;

import O3.l;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetAlbumPicturesPresenter<V extends IAlbumPicturesView> extends AlbumPicturesPresenter<V> {
    public WidgetAlbumPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void finishFragment() {
        onNavigationPressed((View) null);
    }

    public int getNaviUpResourceId() {
        return R.drawable.widget_album_navi_back_mtrl;
    }

    public boolean hasOptionsMenu() {
        return false;
    }

    public void onNavigationPressed(View view) {
        Optional.ofNullable(getActivity()).ifPresent(new l(18));
    }

    public void onViewChanged(Object obj, Bundle bundle) {
        int intValue = ((Integer) obj).intValue();
        int[] columns = ((IAlbumPicturesView) this.mView).getListView().getColumns();
        if (intValue >= 0 && intValue < columns.length) {
            super.onViewChanged(obj, bundle);
        }
    }

    public boolean supportSubTitle() {
        return false;
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        toolbar.setBackgroundColor(toolbar.getContext().getColor(R.color.light_black_color));
    }
}
