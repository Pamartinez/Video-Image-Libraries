package com.samsung.android.gallery.app.ui.list.albums.virtual;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteCmd;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PrivateAlbumPicturesPresenter extends VirtualAlbumPicturesPresenter<IPicturesView> {
    public PrivateAlbumPicturesPresenter(Blackboard blackboard, IPicturesView iPicturesView) {
        super(blackboard, iPicturesView);
    }

    /* access modifiers changed from: private */
    public boolean onMenuSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_delete) {
            new DeleteCmd().addConfig(DeleteCmd.DELETE_FROM_SELECTION_VIEW).execute(this, getSelectedItems());
            return true;
        } else if (itemId == R.id.action_move) {
            new MoveFilesOnPrivateCmd().execute(this, "move_from_secured", getSelectedItems());
            return true;
        } else if (itemId != R.id.action_trash) {
            return false;
        } else {
            this.mBlackboard.publish("command://MoveURL", "location://private/trash");
            AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_TRASH.toString());
            return true;
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_private_album);
        menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_trash, true));
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new ListMenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                if (PrivateAlbumPicturesPresenter.this.onMenuSelected(menuItem) || super.onItemSelected(eventContext, menuItem)) {
                    return true;
                }
                return false;
            }
        };
    }

    public int[] getAlbumCount() {
        return PrivateDatabase.getInstance().loadPrivateAlbumCount();
    }

    public int getTitleResource() {
        return R.string.private_album_header;
    }
}
