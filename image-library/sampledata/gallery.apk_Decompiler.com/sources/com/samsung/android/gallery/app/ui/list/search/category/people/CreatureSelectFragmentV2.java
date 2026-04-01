package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureSelectFragmentV2<V extends ICreatureSelectView, P extends CreatureSelectPresenterV2> extends CreatureSelectFragment<V, P> {
    private String mAutoAlbumTitles;

    private int getDescription() {
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            return R.string.no_photos_or_videos_of_creatures;
        }
        if (isTablet()) {
            return R.string.no_people_analyzed_on_your_tablet;
        }
        return R.string.no_people_analyzed_on_your_phone;
    }

    private int getLabel() {
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            return R.string.empty_set_description_no_items;
        }
        return R.string.no_people_found;
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new CreatureSelectItemAdapterV2(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public String getAutoAlbumTitles() {
        return this.mAutoAlbumTitles;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_AUTO_UPDATING_ALBUM_PEOPLE_SELECT_VIEW.toString();
    }

    public void initArguments(Bundle bundle) {
        super.initArguments(bundle);
        this.mAutoAlbumTitles = ArgumentsUtil.getArgValue(getLocationKey(), "autoAlbumTitles");
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        if (isEmptyViewShowing()) {
            if (this.mNoItemView == null) {
                this.mNoItemView = (NoItemView) this.mEmptyView.findViewById(R.id.no_item_view);
            }
            this.mNoItemView.setLabel(getString(getLabel()));
            this.mNoItemView.setDescription(getString(getDescription()));
        }
    }

    public CreatureSelectPresenterV2 createPresenter(ICreatureSelectView iCreatureSelectView) {
        return new CreatureSelectPresenterV2(this.mBlackboard, iCreatureSelectView);
    }

    public int getMediaItemPosition(int i2) {
        return i2;
    }
}
