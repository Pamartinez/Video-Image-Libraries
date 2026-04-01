package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCategoryListPresenter<V extends IStoriesPinchView> extends StoriesPinchViewPresenter<V> {
    public StoriesCategoryListPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_stories_category_list);
    }

    public int getTitleStringResId() {
        String locationKey = getLocationKey();
        if (LocationKey.isStoriesCategory(locationKey)) {
            return StoryCategory.getCategoryTitle(locationKey);
        }
        return super.getTitleStringResId();
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() == 0) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        } else {
            super.notifyDataChanged(mediaData);
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        boolean z;
        if (!SharedTransition.isInReturnTransition(getBlackboard())) {
            String storyCategoryKey = MediaItemStory.getStoryCategoryKey(mediaItem);
            StoryLauncher data = new StoryLauncher(this.mView).setData(mediaItem, i2);
            if (!PreferenceFeatures.OneUi8x.SUPPORT_RECAP || !MediaItemStory.isRecap(mediaItem)) {
                z = false;
            } else {
                z = true;
            }
            data.setRecap(z).setFromLocation(storyCategoryKey).appendArgs("story_trip_in_year", Boolean.valueOf(isStoryTripInYear())).execute();
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        toolbar.setTitle(getTitleStringResId());
        setNavigationUpButton(toolbar);
    }
}
