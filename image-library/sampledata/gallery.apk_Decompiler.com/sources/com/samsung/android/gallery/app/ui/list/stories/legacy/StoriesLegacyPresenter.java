package com.samsung.android.gallery.app.ui.list.stories.legacy;

import android.view.Menu;
import android.view.MenuItem;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.MenuFactory;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesLegacyPresenter<V extends IStoriesBaseView> extends StoriesBasePresenter<V> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class StoriesMenuUpdater extends ListMenuUpdater {
        public StoriesMenuUpdater(IBaseListView iBaseListView, ListMenuUpdater.IMenuDelegation iMenuDelegation) {
            super(iBaseListView, iMenuDelegation);
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            MenuItem findItem = menu.findItem(R.id.action_rename_story_album_in_list);
            if (findItem != null && getSelectedItemCountForMenuUpdate() > 1) {
                findItem.setVisible(false);
                findItem.setEnabled(false);
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            updateOptionsMenuAction(menu, selectionMode);
        }
    }

    public StoriesLegacyPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.createLegacy();
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        int albumID = mediaItem.getAlbumID();
        String build = new UriBuilder(C0086a.i(albumID, "location://story/albums/fileList/")).appendArg("id", albumID).appendArg(Message.KEY_POSITION, i2).build();
        Log.d(this.TAG, "onListItemClickInternal ");
        this.mBlackboard.post("command://MoveURL", build);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new StoriesMenuUpdater(v, this);
    }
}
