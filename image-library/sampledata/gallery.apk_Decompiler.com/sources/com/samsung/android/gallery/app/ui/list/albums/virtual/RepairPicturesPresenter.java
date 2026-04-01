package com.samsung.android.gallery.app.ui.list.albums.virtual;

import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.AddShortcutCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeDateCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.controller.internals.ScanMediaFileCmd;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RepairPicturesPresenter extends VirtualAlbumPicturesPresenter<IPicturesView> {
    public RepairPicturesPresenter(Blackboard blackboard, IPicturesView iPicturesView) {
        super(blackboard, iPicturesView);
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_virtual_album_repair);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_add_shortcut) {
            public boolean getDefaultVisibility() {
                return !RepairPicturesPresenter.this.isShortcutAlbum();
            }
        });
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new MenuHandler() {
            public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.action_select) {
                    postEvent(eventContext, EventMessage.obtain(1002));
                    return true;
                } else if (itemId == R.id.action_fix_datetime) {
                    new FixDateTimeCmd().execute(eventContext, new Object[0]);
                    return true;
                } else if (itemId == R.id.action_change_date) {
                    new ChangeDateCmd().execute(eventContext, new Object[0]);
                    return true;
                } else if (itemId == R.id.action_change_location) {
                    new ChangeLocationCmd().execute(eventContext, new Object[0]);
                    return true;
                } else if (itemId == R.id.action_scan_media) {
                    new ScanMediaFileCmd().execute(eventContext, new Object[0]);
                    return true;
                } else if (itemId != R.id.action_add_shortcut) {
                    return true;
                } else {
                    new AddShortcutCmd().execute(eventContext, RepairPicturesPresenter.this.getCurrentItem());
                    return true;
                }
            }
        };
    }

    public MediaItem getCurrentItem() {
        MediaItem currentItem = super.getCurrentItem();
        if (currentItem != null) {
            currentItem.setAlbumID(BucketUtils.VirtualBucketHolder.repair);
        }
        return currentItem;
    }

    public int getTitleResource() {
        return R.string.fix_datetime;
    }

    public void onNavigationPressed(View view) {
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle(getTitleResource());
        if (!isSelectionMode()) {
            setNavigationUpButton(toolbar);
        }
    }
}
