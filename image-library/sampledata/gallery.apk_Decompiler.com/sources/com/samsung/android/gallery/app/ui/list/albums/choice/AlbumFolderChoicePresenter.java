package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.choice.IFolderChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderChoicePresenter<V extends IFolderChoiceView> extends AlbumChoiceBasePresenter<V> {
    public AlbumFolderChoicePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_add_album);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_album) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (!mediaItem.isFolder()) {
            return;
        }
        if (isEmptyFolder(mediaItem)) {
            Utils.showToast(getContext(), (int) R.string.folder_is_empty);
        } else {
            onFolderClicked(mediaItem);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_add_album) {
            return super.onOptionsItemSelected(menuItem);
        }
        getBlackboard().publish("data://user/move/AlbumFolderChoice", new Object[]{((IFolderChoiceView) this.mView).getSelectedAlbumIds()});
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_GROUP_ADD);
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((int) R.string.add_albums);
        toolbar.setSubtitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this);
    }
}
