package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleSelectFragment<V extends ICreatureSelectView, P extends PeopleSelectPresenter> extends CreatureSelectFragment<V, P> {
    private int getDescription() {
        if (isTablet()) {
            return R.string.no_people_analyzed_on_your_tablet;
        }
        return R.string.no_people_analyzed_on_your_phone;
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new PeopleSelectItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_AUTO_UPDATING_ALBUM_PEOPLE_SELECT_VIEW.toString();
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        if (isEmptyViewShowing()) {
            if (this.mNoItemView == null) {
                this.mNoItemView = (NoItemView) this.mEmptyView.findViewById(R.id.no_item_view);
            }
            this.mNoItemView.setLabel(getString(R.string.no_people_found));
            this.mNoItemView.setDescription(getString(getDescription()));
        }
    }

    public PeopleSelectPresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new PeopleSelectPresenter(this.mBlackboard, iCreatureSelectView);
    }

    public int getMediaItemPosition(int i2) {
        return i2;
    }
}
