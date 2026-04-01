package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoicePresenter;
import com.samsung.android.gallery.app.ui.list.picker.albums.IAlbumFolderPicker;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerPresenter<V extends IAlbumFolderPicker> extends AlbumFolderChoicePresenter<V> {
    public AlbumFolderPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void handleDone() {
        if (((IAlbumFolderPicker) this.mView).getSelectedItemCount() > 0) {
            getBlackboard().post("command://MultiplePickerItemsSelection", ((IAlbumFolderPicker) this.mView).getSelectedAlbumIds());
            return;
        }
        Log.e(this.TAG, "handleDone: but no selected album.");
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_album_multi_picker);
    }

    public int getNaviUpResourceId() {
        if (((IAlbumFolderPicker) this.mView).isFlipWidgetWithCoverScreen()) {
            return R.drawable.widget_album_navi_back_mtrl;
        }
        return super.getNaviUpResourceId();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_album_done || getMenuHandler() == null) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleDone();
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
        if (LocationKey.isFolder(getLocationKey())) {
            setNavigationUpButton(toolbar);
        }
        if (((IAlbumFolderPicker) this.mView).isFlipWidget()) {
            resetCurrentNaviUpResId();
            setNavigationUpButton(toolbar);
        }
        if (((IAlbumFolderPicker) this.mView).isFlipWidgetWithCoverScreen()) {
            toolbar.setBackgroundColor(toolbar.getContext().getColor(R.color.light_black_color));
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                boolean z;
                MenuItem findItem = menu.findItem(R.id.action_album_done);
                if (findItem != null) {
                    if (((IAlbumFolderPicker) AlbumFolderPickerPresenter.this.mView).getSelectedItemCount() > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    findItem.setVisible(z);
                    findItem.setEnabled(z);
                }
            }
        };
    }

    public void prepareBottomMenu(Menu menu) {
    }
}
