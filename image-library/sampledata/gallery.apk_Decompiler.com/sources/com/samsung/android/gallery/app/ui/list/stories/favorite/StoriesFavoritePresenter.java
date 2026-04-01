package com.samsung.android.gallery.app.ui.list.stories.favorite;

import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.StoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.favorite.IStoriesFavoriteView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesFavoritePresenter<V extends IStoriesFavoriteView> extends StoriesPinchViewPresenter<V> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoriesFavoriteMenuUpdater extends StoriesPresenter.StoriesMenuUpdater {
        public StoriesFavoriteMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iBaseListView, iMenuDelegation);
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            super.updateOptionsMenuAction(menu, selectionMode);
            Optional.ofNullable(menu.findItem(R.id.action_favorite_view)).ifPresent(new a(0));
            Optional.ofNullable(menu.findItem(R.id.action_create_story_album)).ifPresent(new a(1));
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            updateOptionsMenuAction(menu, selectionMode);
        }
    }

    public StoriesFavoritePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void blockEvent(Object obj, Bundle bundle) {
        setInputBlock(this.TAG + "_blockEvent", 500);
    }

    public MenuDataBinding createMenuDataBinding() {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            return new MenuDataBinding(R.menu.menu_stories_category_list);
        }
        return super.createMenuDataBinding();
    }

    public int getTitleStringResId() {
        return R.string.favorites;
    }

    public void updateFavoriteInfo(MediaItem mediaItem, int i2) {
        new UpdateStoryFavoriteCmd().execute(this, new MediaItem[]{mediaItem}, 1, Integer.valueOf(i2));
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        toolbar.setTitle(getTitleStringResId());
        setNavigationUpButton(toolbar);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoriesFavoriteMenuUpdater(v, this);
    }
}
