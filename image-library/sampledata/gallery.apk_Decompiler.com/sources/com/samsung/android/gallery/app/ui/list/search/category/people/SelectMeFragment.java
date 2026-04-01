package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.list.search.category.people.SelectMePresenter;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectMeFragment<V extends ICreatureSelectView, P extends SelectMePresenter<V>> extends CreatureSelectFragment<V, P> {
    public void bindView(View view) {
        super.bindView(view);
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureSelectItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true) {
            public int getSelectedCountOnCurrentView() {
                return ((SelectMePresenter) SelectMeFragment.this.mPresenter).getSelectedCount();
            }

            public int getMediaItemPosition(int i2) {
                return i2;
            }

            public int getViewPosition(int i2) {
                return i2;
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_select_me_layout;
    }

    public void openMediaData() {
        if (LocationKey.isSelectMeMatch(getLocationKey())) {
            this.mBlackboard.publish(DataKey.DATA(getLocationKey()), this.mBlackboard.pop("data://user/selected"));
        }
        super.openMediaData();
    }

    public SelectMePresenter createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new SelectMePresenter(this.mBlackboard, iCreatureSelectView);
    }
}
