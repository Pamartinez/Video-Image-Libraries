package com.samsung.android.gallery.app.ui.menu.popmenu;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.sec.android.gallery3d.R;
import java.util.HashSet;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimplePopupMenuData implements PopupMenuData {
    private final Set<Integer> mAddedMenu = new HashSet();
    protected final PopupMenuParams mParams;

    public SimplePopupMenuData(PopupMenuParams popupMenuParams) {
        this.mParams = popupMenuParams;
        initMenu();
        buildMenu();
    }

    private void addKnoxMenuInternal(int i2, KnoxUtil.MoveType moveType) {
        addMenu(i2, KnoxUtil.getInstance().isKnoxMoveOn(moveType), KnoxUtil.getInstance().getKnoxContainerName(moveType));
    }

    private void buildMenu() {
        if (isItemAreaClicked()) {
            buildItemAreaMenu();
        } else if (isEmptyAreaClicked()) {
            buildEmptyAreaMenu();
        }
    }

    private void initMenu() {
        for (int i2 = 0; i2 < this.mParams.getMenu().size(); i2++) {
            MenuItem item = this.mParams.getMenu().getItem(i2);
            if (item != null) {
                item.setVisible(false);
            }
        }
        initGroup();
    }

    private boolean isEmptyAreaClicked() {
        if (this.mParams.getInputType() == 0) {
            return true;
        }
        return false;
    }

    private boolean isItemAreaClicked() {
        if (this.mParams.getInputType() == 1) {
            return true;
        }
        return false;
    }

    private boolean supportSetAsWallpaper() {
        if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || Features.isEnabled(Features.IS_KNOX_MODE) || Features.isEnabled(Features.IS_UPSM) || Features.isEnabled(Features.IS_GED)) {
            return false;
        }
        return true;
    }

    public void addKnoxMenu() {
        addKnoxMenuInternal(R.id.action_move_to_knox, KnoxUtil.MoveType.MOVE_TO_KNOX);
        addKnoxMenuInternal(R.id.action_move_to_secure_folder, KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
        addKnoxMenuInternal(R.id.action_remove_from_knox, KnoxUtil.MoveType.REMOVE_FROM_KNOX);
        addKnoxMenuInternal(R.id.action_remove_from_secure_folder, KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER);
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            addMenu(R.id.action_move_to_private_album);
        }
    }

    public void addMenu(int i2) {
        addMenu(i2, true);
    }

    public void addSelectedBaseMenu() {
        addMenu(R.id.action_share);
        addMenu(R.id.action_delete);
        addMenu(R.id.action_copy_to_album);
        addMenu(R.id.action_move_to_album);
        addMenu(R.id.action_create);
        addMenu(R.id.action_add_to_shared_album);
        addMenu(R.id.action_add_favorite_in_list);
        addMenu(R.id.action_add_tag);
        addMenu(R.id.action_download);
        addMenu(R.id.action_copy_to_clipboard);
        addMenu(R.id.action_edit_date_and_time);
        addMenu(R.id.action_edit_location, Features.isEnabled(Features.SUPPORT_LOCATION));
        addMenu(R.id.action_export_motion_photo_clip);
        addMenu(R.id.action_merge_motion_photo_clip);
        addMenu(R.id.action_delete_motion_photo_clip);
        addMenu(R.id.action_set_as_container, supportSetAsWallpaper());
    }

    public void addSharingAlbumMenu() {
        addMenu(R.id.action_delete_shared_album_in_list);
        addMenu(R.id.action_rename_shared_album_in_list);
        addMenu(R.id.action_leave_shared_album_in_list);
    }

    public abstract void buildEmptyAreaMenu();

    public abstract void buildItemAreaMenu();

    public Menu getMenu() {
        return this.mParams.getMenu();
    }

    public boolean isAddedItem(int i2) {
        return this.mAddedMenu.contains(Integer.valueOf(i2));
    }

    public boolean isNormalGroup(int i2) {
        if (i2 == R.id.normal_mode) {
            return true;
        }
        return false;
    }

    public boolean isPickerGroup(int i2) {
        if (i2 == R.id.picker_mode) {
            return true;
        }
        return false;
    }

    public boolean isPickerMode() {
        return this.mParams.isPickerMode();
    }

    public boolean isSelectModeGroup(int i2) {
        if (i2 == R.id.select_mode || i2 == R.id.select_mode_bottom || i2 == R.id.select_mode_knox) {
            return true;
        }
        return false;
    }

    public void operate(int i2) {
        boolean z;
        for (int i7 = 0; i7 < this.mParams.getMenu().size(); i7++) {
            MenuItem item = this.mParams.getMenu().getItem(i7);
            boolean z3 = true;
            if (!item.isVisible() || !isAddedItem(item.getItemId())) {
                z = false;
            } else {
                z = true;
            }
            if (isSelectModeGroup(item.getGroupId())) {
                if (i2 != 1 || !z) {
                    z3 = false;
                }
                item.setVisible(z3);
            } else if (isNormalGroup(item.getGroupId())) {
                if (i2 != 0 || !z) {
                    z3 = false;
                }
                item.setVisible(z3);
            } else if (isPickerGroup(item.getGroupId())) {
                item.setVisible(z);
            } else {
                item.setVisible(false);
            }
        }
    }

    public void addMenu(int i2, boolean z) {
        addMenu(i2, z, (String) null);
    }

    public void addMenu(int i2, boolean z, String str) {
        MenuItem findItem = this.mParams.getMenu().findItem(i2);
        if (findItem == null) {
            return;
        }
        if (z) {
            findItem.setVisible(true);
            if (!TextUtils.isEmpty(str)) {
                findItem.setTitle(str);
            }
            this.mAddedMenu.add(Integer.valueOf(i2));
            return;
        }
        this.mParams.getMenu().removeItem(findItem.getItemId());
    }

    public void initGroup() {
    }
}
