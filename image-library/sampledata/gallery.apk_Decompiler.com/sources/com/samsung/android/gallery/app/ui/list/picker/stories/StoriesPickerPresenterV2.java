package com.samsung.android.gallery.app.ui.list.picker.stories;

import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPickerPresenterV2<V extends IStoriesPinchView> extends StoriesPinchViewPresenter<V> {
    private final LaunchModeType mLaunchModeType = ((LaunchModeType) this.mBlackboard.read("data://launch_mode_type"));

    public StoriesPickerPresenterV2(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.createMultiPickMenu();
    }

    public MenuHandler createMenuHandler() {
        if (this.mLaunchModeType == LaunchModeType.ACTION_MULTIPLE_PICK) {
            return new PickerMenuHandler();
        }
        return null;
    }

    public LaunchModeType getLaunchMode() {
        return this.mLaunchModeType;
    }

    public boolean isPickerMode() {
        return true;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (PickerUtil.isSinglePickLaunchMode(getBlackboard())) {
            this.mBlackboard.post("command://SinglePickerItemSelection", mediaItem);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_done || getMenuHandler() == null) {
            return super.onOptionsItemSelected(menuItem);
        }
        getMenuHandler().onOptionsItemSelected(this, menuItem);
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                boolean z;
                MenuItem findItem = menu.findItem(R.id.action_done);
                if (findItem != null) {
                    if (StoriesPickerPresenterV2.this.getSelectedItemCount() > 0) {
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
