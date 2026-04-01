package com.samsung.android.gallery.app.ui.list.search.category.people;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureSelectFragment<V extends ICreatureSelectView, P extends CreatureSelectPresenter> extends CategoryFragment<V, P> implements ICreatureSelectView {
    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureSelectItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public String getAutoAlbumTitles() {
        return null;
    }

    public ArrayList<String> getSelectedCreatures() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((CreatureSelectPresenter) p6).getSelectedCreatures();
        }
        return new ArrayList<>();
    }

    public CreatureSelectPresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new CreatureSelectPresenter(this.mBlackboard, iCreatureSelectView);
    }
}
