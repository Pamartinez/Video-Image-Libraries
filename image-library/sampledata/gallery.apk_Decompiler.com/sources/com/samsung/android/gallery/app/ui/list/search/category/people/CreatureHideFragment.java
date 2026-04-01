package com.samsung.android.gallery.app.ui.list.search.category.people;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureHideFragment<V extends ICreatureSelectView, P extends CreatureHidePresenter> extends CreatureSelectFragment<V, P> {
    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureHideItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        ((CreatureHidePresenter) this.mPresenter).restoreChangedCreatureMapIfNeeded();
    }

    public boolean useAdvancedMouseDragAndDrop() {
        return false;
    }

    public CreatureHidePresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new CreatureHidePresenter(this.mBlackboard, iCreatureSelectView);
    }

    public int getMediaItemPosition(int i2) {
        return i2;
    }
}
