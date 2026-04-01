package com.samsung.android.gallery.app.ui.list.picker.search.creature;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureSelectView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreaturePickPresenter<V extends ICreatureSelectView> extends CreatureSelectPresenter<V> {
    /* access modifiers changed from: private */
    public final ArrayList<MediaItem> mSelectedItems = new ArrayList<>();

    public CreaturePickPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.createMultiPickMenu();
    }

    public MenuHandler createMenuHandler() {
        return new PickerMenuHandler();
    }

    public String getDefaultToolbarTitle(Context context) {
        return PickerUtil.getUsageTitle(getBlackboard());
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) this.mSelectedItems.toArray(new MediaItem[0]);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_done || getMenuHandler() == null) {
            return super.onOptionsItemSelected(menuItem);
        }
        getMenuHandler().onOptionsItemSelected(this, menuItem);
        return true;
    }

    public void select(MediaItem mediaItem) {
        super.select(mediaItem);
        this.mSelectedItems.add(mediaItem);
    }

    public boolean skipInitMenu() {
        return false;
    }

    public void unselect(MediaItem mediaItem) {
        super.unselect(mediaItem);
        this.mSelectedItems.remove(mediaItem);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) getTitle());
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                MenuItem findItem = menu.findItem(R.id.action_done);
                if (findItem != null) {
                    boolean z = !CreaturePickPresenter.this.mSelectedItems.isEmpty();
                    findItem.setVisible(z);
                    findItem.setEnabled(z);
                }
            }
        };
    }

    public void prepareBottomMenu(Menu menu) {
    }
}
