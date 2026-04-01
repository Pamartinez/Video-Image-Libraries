package com.samsung.android.gallery.app.ui.list.abstraction;

import A.a;
import A4.C0375j;
import A4.O;
import A4.T;
import A4.U;
import A4.V;
import A4.W;
import C4.k;
import Fa.C0571z;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.app.provider.LocalClipboard;
import com.samsung.android.gallery.app.ui.menu.BottomBarMenuResources;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.app.ui.menu.list.CreateMenuVisibility;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.bottom.BottomBarData;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListMenuUpdater {
    protected final String TAG = getClass().getSimpleName();
    protected final IMenuDelegation mInterface;
    private final q mNavigationItemSelectedListener = new O(2, this);
    private final HashMap<Integer, Boolean> mNewBadge = new HashMap<>();
    private CharSequence mSelectionDoneTitle;
    private final IBaseListView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IMenuDelegation {
        Blackboard getBlackboard();

        Context getContext();

        int getDataCount();

        int getFocusedItemCount();

        int getItemCount();

        String getLocationKey();

        MenuDataBinding getMenuDataBinding();

        int getSelectedItemCount();

        MediaItem[] getSelectedItems();

        boolean isFloatingPopupMenu();

        boolean isOnAdvancedMouseMultiFocused();

        boolean isPopupMenuItemFocused();

        boolean isPopupMenuShowing();

        boolean isSelectionMode();

        boolean onOptionsItemSelected(MenuItem menuItem);

        boolean setInputBlock(String str, int i2);

        void updateKnoxMenuVisibility();
    }

    public ListMenuUpdater(IBaseListView iBaseListView, IMenuDelegation iMenuDelegation) {
        this.mView = iBaseListView;
        this.mInterface = iMenuDelegation;
    }

    private boolean getBadgeStatus(int i2, boolean z) {
        Boolean bool = this.mNewBadge.get(Integer.valueOf(i2));
        if (bool == null || bool.booleanValue() != z) {
            this.mNewBadge.put(Integer.valueOf(i2), Boolean.valueOf(z));
        }
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    private MenuItem getCreateMenuItem(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            if (item.getItemId() == R.id.action_create && ((selectionMode == MenuDataBinding.SelectionMode.SELECT && item.getGroupId() == R.id.select_mode_bottom) || (selectionMode == MenuDataBinding.SelectionMode.NORMAL && item.getGroupId() == R.id.normal_mode))) {
                return item;
            }
        }
        return null;
    }

    private boolean getDownloadMenuVisibility(int i2) {
        if (i2 <= 0 || !Features.isEnabled(Features.SUPPORT_CLOUD) || !GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_SCLOUD_SYNC_ON, false)) {
            return false;
        }
        return true;
    }

    private MenuDataBinding.SelectionMode getSelectionMode(EventContext.OnSelectionListener onSelectionListener) {
        if (this.mInterface.isOnAdvancedMouseMultiFocused() || this.mInterface.isPopupMenuItemFocused()) {
            return MenuDataBinding.SelectionMode.SELECT;
        }
        if (!this.mInterface.isSelectionMode()) {
            return MenuDataBinding.SelectionMode.NORMAL;
        }
        if (onSelectionListener == null) {
            return MenuDataBinding.SelectionMode.SELECT;
        }
        return MenuDataBinding.SelectionMode.SELECT_DONE;
    }

    private boolean getSetAsWallpaperMenuVisibility() {
        MediaItem mediaItem;
        if (getSelectedItemCountForMenuUpdate() == 1) {
            if (this.mInterface.getSelectedItems().length > 0) {
                mediaItem = this.mInterface.getSelectedItems()[0];
            } else {
                mediaItem = null;
            }
            if (mediaItem == null || (!mediaItem.isImage() && (!mediaItem.isVideo() || !mediaItem.isLocal() || !supportVideoWallpaper()))) {
                return false;
            }
            return true;
        } else if (Features.isEnabled(Features.IS_MUM_MODE) || isDexMode()) {
            return false;
        } else {
            return true;
        }
    }

    private void hideBottomTabIfPossible() {
        Runnable runnable = (Runnable) getBlackboard().pop("command:///HideBottomTabCallback");
        if (runnable != null) {
            runnable.run();
        }
    }

    private boolean isBottomMoreGroup(MenuItem menuItem) {
        if ((menuItem.getGroupId() == R.id.select_mode || menuItem.getGroupId() == R.id.select_mode_knox || menuItem.getGroupId() == R.id.select_mode_effects) && menuItem.getItemId() != R.id.action_cancel) {
            return true;
        }
        return false;
    }

    private boolean isDexMode() {
        return DeviceInfo.isDexMode(this.mInterface.getContext());
    }

    private boolean isRemoveFromResultMenu(Resources resources) {
        if (this.mSelectionDoneTitle.equals(resources.getString(R.string.remove_from_result)) || this.mSelectionDoneTitle.equals(resources.getString(R.string.remove_items_from_collection)) || this.mSelectionDoneTitle.equals(resources.getString(R.string.remove_items_from_tag_group))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$0(MenuItem menuItem) {
        menuItem.setVisible(supportCreateMenu());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$1(MenuItem menuItem) {
        menuItem.setVisible(getSetAsWallpaperMenuVisibility());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$2(int i2, MenuItem menuItem) {
        menuItem.setVisible(getDownloadMenuVisibility(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$3(MenuItem menuItem) {
        menuItem.setVisible(supportAddFavoriteInList());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$4(int i2, MenuItem menuItem) {
        menuItem.setVisible(lambda$updateMotionPhotoMenu$11(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$5(int i2, MenuItem menuItem) {
        menuItem.setVisible(lambda$updateMotionPhotoMenu$12(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$6(int i2, MenuItem menuItem) {
        menuItem.setVisible(lambda$updateMotionPhotoMenu$13(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCommonPopupMenuAction$7(int i2, MenuItem menuItem) {
        menuItem.setVisible(supportAddToSharedAlbumMenuVisibility(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateOptionsMenuActionDone$8(View view) {
        ViewUtils.setText((TextView) view.findViewById(R.id.action_bar_item_textview), this.mSelectionDoneTitle);
    }

    private boolean needShowMoreOption(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_cancel) {
            return true;
        }
        return false;
    }

    private boolean supportAddToSharedAlbumMenuVisibility(int i2) {
        if (isUpsm() || !getAddToSharedAlbumVisibility(i2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: supportCopyEffects */
    public boolean lambda$updateEffectMenu$15(int i2) {
        MediaItem mediaItem;
        if (i2 == 1 && Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS)) {
            MediaItem[] selectedItems = this.mView.getSelectedItems();
            if (selectedItems == null || selectedItems.length <= 0) {
                mediaItem = null;
            } else {
                mediaItem = selectedItems[0];
            }
            if (mediaItem == null || !mediaItem.hasFilterAndTone() || mediaItem.isVideo() || mediaItem.isCloudOnly() || mediaItem.isDrm() || !FileUtils.exists(mediaItem.getPath())) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: supportCopyToClipboard */
    public boolean lambda$updateClipboardMenu$9(MenuDataBinding.SelectionMode selectionMode, int i2) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.U) || selectionMode != MenuDataBinding.SelectionMode.SELECT || i2 <= 0 || i2 > 500) {
            return false;
        }
        return true;
    }

    private boolean supportMotionPhotoOperation() {
        MediaItem[] selectedItems = this.mView.getSelectedItems();
        if (selectedItems == null || selectedItems.length == 0) {
            return false;
        }
        for (MediaItem mediaItem : selectedItems) {
            if (mediaItem == null || !mediaItem.isMotionPhoto() || mediaItem.isCloudOnly()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: supportPasteEffects */
    public boolean lambda$updateEffectMenu$16(int i2) {
        if (i2 > 0 && i2 <= 100 && Features.isEnabled(Features.SUPPORT_COPY_PASTE_EFFECTS)) {
            try {
                return LocalClipboard.isFilterAvailable();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "paste failed, error=", (Throwable) e);
            }
        }
        return false;
    }

    private boolean supportVideoWallpaper() {
        if (Features.isEnabled(Features.SUPPORT_CALL_BG_PROVIDER_PACKAGE)) {
            return true;
        }
        if ((Features.isEnabled(Features.USE_SET_AS_VIDEO_WALLPAPER) || Features.isEnabled(Features.USE_SET_AS_COVER_VIDEO_WALLPAPER)) && !isDexMode()) {
            return true;
        }
        return false;
    }

    private void updateAddFavoriteInListMenuVisibility(MenuDataBinding menuDataBinding) {
        menuDataBinding.setVisible((int) R.id.action_add_favorite_in_list, supportAddFavoriteInList());
    }

    private void updateCancelMenu(Menu menu) {
        setMenuVisibility(menu, (int) R.id.action_cancel, getCancelMenuVisibility());
    }

    private void updateClipboardMenu(Menu menu, MenuDataBinding menuDataBinding, MenuDataBinding.SelectionMode selectionMode, int i2) {
        if (menuDataBinding.hasBinding(R.id.action_copy_to_clipboard)) {
            setMenuVisibility(menu, (int) R.id.action_copy_to_clipboard, (BooleanSupplier) new k(this, selectionMode, i2));
        }
        if (menuDataBinding.hasBinding(R.id.action_paste_clipboard)) {
            setMenuVisibility(menu, (int) R.id.action_paste_clipboard, (BooleanSupplier) new W(0, this, selectionMode));
        }
    }

    private void updateCommonMenuAction(Menu menu, MenuDataBinding menuDataBinding, MenuDataBinding.SelectionMode selectionMode) {
        boolean z;
        boolean z3;
        updateCancelMenu(menu);
        if (menuDataBinding.hasBinding(R.id.action_create)) {
            updateCreateMenuVisibility(menuDataBinding);
        }
        if (menuDataBinding.hasBinding(R.id.action_set_as_container)) {
            updateSetAsWallpaperMenuVisibility(menuDataBinding);
        }
        if (menuDataBinding.hasBinding(R.id.action_add_favorite_in_list)) {
            updateAddFavoriteInListMenuVisibility(menuDataBinding);
        }
        boolean z7 = false;
        if (PocFeatures.SLIDESHOW_SELECTED_ITEMS && menuDataBinding.hasBinding(R.id.action_slideshow_with_selection)) {
            if (selectionMode == MenuDataBinding.SelectionMode.SELECT) {
                z3 = true;
            } else {
                z3 = false;
            }
            menuDataBinding.setVisible((int) R.id.action_slideshow_with_selection, z3);
        }
        if (PocFeatures.TBD.COMPARE_IMAGES && menuDataBinding.hasBinding(R.id.action_compare_images)) {
            int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
            if (selectedItemCountForMenuUpdate > 20 || (selectedItemCountForMenuUpdate <= 1 && (selectedItemCountForMenuUpdate != 1 || (!this.mInterface.getSelectedItems()[0].isBurstShot() && !this.mInterface.getSelectedItems()[0].isSimilarShot())))) {
                z = false;
            } else {
                z = true;
            }
            if (selectionMode == MenuDataBinding.SelectionMode.SELECT && z) {
                z7 = true;
            }
            menuDataBinding.setVisible((int) R.id.action_compare_images, z7);
        }
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            if (menuDataBinding.hasBinding(R.id.action_trash)) {
                menuDataBinding.setVisible((int) R.id.action_trash, !this.mView.isDrawerMode());
            }
            if (menuDataBinding.hasBinding(R.id.action_settings)) {
                menuDataBinding.setVisible((int) R.id.action_settings, !this.mView.isDrawerMode());
            }
        }
    }

    private void updateCommonPopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
        int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
        Optional.ofNullable(getCreateMenuItem(menu, selectionMode)).ifPresent(new T(this, 0));
        Optional.ofNullable(menu.findItem(R.id.action_set_as_container)).ifPresent(new T(this, 1));
        Optional.ofNullable(menu.findItem(R.id.action_download)).ifPresent(new U(this, selectedItemCountForMenuUpdate, 0));
        Optional.ofNullable(menu.findItem(R.id.action_add_favorite_in_list)).ifPresent(new T(this, 2));
        Optional.ofNullable(menu.findItem(R.id.action_merge_motion_photo_clip)).ifPresent(new U(this, selectedItemCountForMenuUpdate, 1));
        Optional.ofNullable(menu.findItem(R.id.action_export_motion_photo_clip)).ifPresent(new U(this, selectedItemCountForMenuUpdate, 2));
        Optional.ofNullable(menu.findItem(R.id.action_delete_motion_photo_clip)).ifPresent(new U(this, selectedItemCountForMenuUpdate, 3));
        if (!PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            setMenuVisibility(menu, (int) R.id.action_trash, !this.mView.isDrawerMode());
            setMenuVisibility(menu, (int) R.id.action_settings, !this.mView.isDrawerMode());
        }
        Optional.ofNullable(menu.findItem(R.id.action_add_to_shared_album)).ifPresent(new U(this, selectedItemCountForMenuUpdate, 4));
    }

    private void updateDownloadMenu(MenuDataBinding menuDataBinding, int i2) {
        menuDataBinding.setVisible((int) R.id.action_download, getDownloadMenuVisibility(i2));
    }

    private void updateEffectMenu(Menu menu, MenuDataBinding menuDataBinding, int i2) {
        if (menuDataBinding.hasBinding(R.id.action_copy_effects)) {
            setMenuVisibility(menu, (int) R.id.action_copy_effects, (BooleanSupplier) new V(this, i2, 0));
        }
        if (menuDataBinding.hasBinding(R.id.action_paste_effects)) {
            setMenuVisibility(menu, (int) R.id.action_paste_effects, (BooleanSupplier) new V(this, i2, 1));
        }
    }

    private void updateMotionPhotoMenu(MenuDataBinding menuDataBinding, int i2) {
        if (menuDataBinding.hasBinding(R.id.action_merge_motion_photo_clip)) {
            menuDataBinding.setVisible((int) R.id.action_merge_motion_photo_clip, (BooleanSupplier) new V(this, i2, 2));
        }
        if (menuDataBinding.hasBinding(R.id.action_export_motion_photo_clip)) {
            menuDataBinding.setVisible((int) R.id.action_export_motion_photo_clip, (BooleanSupplier) new V(this, i2, 3));
        }
        if (menuDataBinding.hasBinding(R.id.action_delete_motion_photo_clip)) {
            menuDataBinding.setVisible((int) R.id.action_delete_motion_photo_clip, (BooleanSupplier) new V(this, i2, 4));
        }
    }

    private void updatePocRemoveLiveFocusInfoMenu(MenuDataBinding menuDataBinding, MenuDataBinding.SelectionMode selectionMode) {
        boolean z;
        if (PocFeatures.isEnabled(PocFeatures.RemoveBackgroundEffectInfo) && menuDataBinding.hasBinding(R.id.action_remove_background_effect_info)) {
            boolean z3 = false;
            if (!Arrays.stream(this.mInterface.getSelectedItems()).allMatch(new C0375j(1)) || !Arrays.stream(this.mInterface.getSelectedItems()).noneMatch(new C0571z(5))) {
                z = false;
            } else {
                z = true;
            }
            if (selectionMode == MenuDataBinding.SelectionMode.SELECT && z) {
                z3 = true;
            }
            menuDataBinding.setVisible((int) R.id.action_remove_background_effect_info, z3);
        }
    }

    private void updateSetAsWallpaperMenuVisibility(MenuDataBinding menuDataBinding) {
        menuDataBinding.setVisible((int) R.id.action_set_as_container, getSetAsWallpaperMenuVisibility());
    }

    public final boolean getAddToSharedAlbumVisibility(int i2) {
        if (i2 > MdeSharingHelper.getMaxUploadCount(this.mInterface.getContext()) || !MdeSharingService.getInstance().isServiceSupported() || !MdeSharingService.getInstance().isShareServiceAvailable()) {
            return false;
        }
        return true;
    }

    public Blackboard getBlackboard() {
        return this.mInterface.getBlackboard();
    }

    public BottomBarData.InitListener getBottomBarInitListener() {
        return null;
    }

    public boolean getCancelMenuVisibility() {
        return this.mInterface.isSelectionMode();
    }

    public int getDataCount() {
        return this.mInterface.getDataCount();
    }

    public MenuDataBinding.ItemMode getItemMode() {
        if (getSelectedItemCountForMenuUpdate() > 0) {
            return MenuDataBinding.ItemMode.SELECTED_ITEM;
        }
        if (this.mInterface.getItemCount() > 0) {
            if (this.mView.getAdapter() == null || !this.mView.getAdapter().checkIfEmpty()) {
                return MenuDataBinding.ItemMode.ANY_ITEM;
            }
            return MenuDataBinding.ItemMode.NO_ITEM;
        } else if (this.mView.getAdapter() != null) {
            return MenuDataBinding.ItemMode.NO_ITEM;
        } else {
            return MenuDataBinding.ItemMode.NOT_DEFINED;
        }
    }

    public String getLocationKey() {
        return this.mInterface.getLocationKey();
    }

    public int getSelectedItemCountForMenuUpdate() {
        if (this.mInterface.isOnAdvancedMouseMultiFocused()) {
            return this.mInterface.getFocusedItemCount();
        }
        if (!this.mInterface.isFloatingPopupMenu() || this.mInterface.isSelectionMode()) {
            return this.mInterface.getSelectedItemCount();
        }
        if (getBlackboard().read("data://focused_item") == null) {
            return 0;
        }
        return 1;
    }

    public final IBaseListView getView() {
        return this.mView;
    }

    public final boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        IMenuDelegation iMenuDelegation = this.mInterface;
        if (!iMenuDelegation.setInputBlock(this.TAG + "_onNavigationItemSelected", 500)) {
            return false;
        }
        if (this.mInterface.getSelectedItemCount() == 0) {
            Log.w(this.TAG, "no selected item");
            return false;
        } else if (menuItem.getItemId() != R.id.action_create) {
            return this.mInterface.onOptionsItemSelected(menuItem);
        } else {
            new CreateNewDialogCmd().execute((EventContext) this.mInterface, new CreateMenuVisibility().getVisibilities(this.mInterface.getLocationKey()));
            return true;
        }
    }

    public final void setMenuVisibility(Menu menu, int i2, BooleanSupplier booleanSupplier) {
        MenuItem findItem = menu.findItem(i2);
        if (findItem != null) {
            boolean asBoolean = booleanSupplier.getAsBoolean();
            findItem.setEnabled(asBoolean);
            findItem.setVisible(asBoolean);
        }
    }

    public void setSelectionDoneTitle(CharSequence charSequence) {
        this.mSelectionDoneTitle = charSequence;
    }

    public boolean supportAddFavoriteInList() {
        if (!this.mView.isSelectionMode() || this.mView.getSelectedItemCount() <= 0 || this.mView.getPresenter().isOnAdvancedMouseFocused()) {
            return false;
        }
        return true;
    }

    public final boolean supportAlbumShare(long j2) {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.ShareAlbums) || (j2 & 64) == 0 || Features.isEnabled(Features.IS_UPSM)) {
            return false;
        }
        return true;
    }

    public boolean supportCreateMenu() {
        return MenuSupportHelper.supportCreate();
    }

    /* renamed from: supportMotionPhotoDelete */
    public boolean lambda$updateMotionPhotoMenu$13(int i2) {
        if (i2 <= 0 || i2 > 100 || !supportMotionPhotoOperation()) {
            return false;
        }
        return true;
    }

    /* renamed from: supportMotionPhotoExport */
    public boolean lambda$updateMotionPhotoMenu$12(int i2) {
        if (i2 <= 0 || i2 > 100 || !supportMotionPhotoOperation()) {
            return false;
        }
        return true;
    }

    /* renamed from: supportMotionPhotoMerge */
    public boolean lambda$updateMotionPhotoMenu$11(int i2) {
        if (i2 <= 1 || i2 > 100 || !supportMotionPhotoOperation()) {
            return false;
        }
        return true;
    }

    /* renamed from: supportPasteFromClipboard */
    public boolean lambda$updateClipboardMenu$10(MenuDataBinding.SelectionMode selectionMode) {
        return false;
    }

    public void updateBottomBarMenuAction(Menu menu) {
        int i2;
        if (this.mInterface.isSelectionMode() && this.mView.isViewActive()) {
            if (this.mInterface.getSelectedItemCount() == 0) {
                getBlackboard().post("command://HideBottomBar", Boolean.TRUE);
                hideBottomTabIfPossible();
                return;
            }
            if (this.mSelectionDoneTitle == null) {
                i2 = R.id.select_mode_bottom;
            } else {
                i2 = R.id.select_mode_with_done;
            }
            ArrayList<MenuItem> arrayList = new ArrayList<>();
            ArrayList<MenuItem> arrayList2 = new ArrayList<>();
            boolean z = false;
            for (int i7 = 0; i7 < menu.size(); i7++) {
                MenuItem item = menu.getItem(i7);
                if (item.isVisible() && item.isEnabled()) {
                    if (item.getGroupId() == i2) {
                        arrayList.add(item);
                    } else if (isBottomMoreGroup(item)) {
                        arrayList2.add(item);
                    }
                    item.setVisible(needShowMoreOption(item));
                }
            }
            if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
                BottomBarData bottomBarData = new BottomBarData();
                String locationKey = this.mInterface.getLocationKey();
                bottomBarData.locationKey = locationKey;
                bottomBarData.menuResId = BottomBarMenuResources.getMenuResourceId(locationKey);
                if (this.mInterface.getSelectedItemCount() == this.mView.getDataCount()) {
                    z = true;
                }
                bottomBarData.allSelected = z;
                bottomBarData.showDeleteAll = this.mView.getPresenter().showDeleteAllWarning();
                bottomBarData.items = arrayList;
                bottomBarData.itemsMore = arrayList2;
                bottomBarData.listener = this.mNavigationItemSelectedListener;
                bottomBarData.initListener = getBottomBarInitListener();
                bottomBarData.listView = this.mView.getListView();
                getBlackboard().post("command://AddBottomBar", bottomBarData);
                return;
            }
            getBlackboard().post("command://HideBottomBar", Boolean.TRUE);
        }
    }

    public void updateCreateMenuVisibility(MenuDataBinding menuDataBinding) {
        menuDataBinding.setVisible((int) R.id.action_create, supportCreateMenu());
    }

    public void updateNewBadgeForInvitation() {
        MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
        if (menuDataBinding != null) {
            boolean z = PreferenceCache.SharedNewNotificationBadge.getBoolean();
            if (z != getBadgeStatus(R.id.action_sharings_invitations, z)) {
                a.v("InvitationNewBadge{", "}", this.TAG, z);
            }
            menuDataBinding.setNewBadge(R.id.action_sharings_invitations, z);
            menuDataBinding.setNewBadge(R.id.action_sharings_invitations_no_item, z);
        }
    }

    public void updateOptionsMenu(Menu menu, EventContext.OnSelectionListener onSelectionListener) {
        boolean z;
        MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
        if (menuDataBinding != null) {
            MenuDataBinding.SelectionMode selectionMode = getSelectionMode(onSelectionListener);
            MenuDataBinding.ItemMode itemMode = getItemMode();
            int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
            if (this.mInterface.isOnAdvancedMouseMultiFocused() || (this.mInterface.isFloatingPopupMenu() && !this.mInterface.isPopupMenuShowing())) {
                z = true;
            } else {
                z = false;
            }
            Menu menu2 = menu;
            menuDataBinding.updateGroupVisible(menu2, selectionMode, itemMode, selectedItemCountForMenuUpdate, z);
            updateOptionsMenuActionDone(menu2, selectionMode, itemMode);
            updateOptionsMenuAction(menu2, selectionMode);
            updateCommonMenuAction(menu2, menuDataBinding, selectionMode);
        }
    }

    public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
        MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
        if (menuDataBinding != null) {
            int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
            if (selectionMode == MenuDataBinding.SelectionMode.SELECT) {
                if (menuDataBinding.hasBinding(R.id.action_add_to_shared_album)) {
                    menuDataBinding.setVisible((int) R.id.action_add_to_shared_album, supportAddToSharedAlbumMenuVisibility(selectedItemCountForMenuUpdate));
                }
                updateDownloadMenu(menuDataBinding, selectedItemCountForMenuUpdate);
                updateEffectMenu(menu, menuDataBinding, selectedItemCountForMenuUpdate);
            }
            updateClipboardMenu(menu, menuDataBinding, selectionMode, selectedItemCountForMenuUpdate);
            updateMotionPhotoMenu(menuDataBinding, selectedItemCountForMenuUpdate);
            updatePocRemoveLiveFocusInfoMenu(menuDataBinding, selectionMode);
            this.mInterface.updateKnoxMenuVisibility();
        }
    }

    public void updateOptionsMenuActionDone(Menu menu, MenuDataBinding.SelectionMode selectionMode, MenuDataBinding.ItemMode itemMode) {
        boolean z;
        MenuItem findItem = menu.findItem(R.id.action_done);
        if (findItem != null) {
            if (!TextUtils.isEmpty(this.mSelectionDoneTitle)) {
                findItem.setTitle(this.mSelectionDoneTitle);
                Resources resources = this.mInterface.getContext().getResources();
                if (this.mSelectionDoneTitle.equals(resources.getString(R.string.share_short))) {
                    findItem.setIcon(R.drawable.gallery_ic_share);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.create_movie_menu))) {
                    findItem.setIcon(R.drawable.ic_create_new_circle_dialog_movie);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.highlight_reel))) {
                    findItem.setIcon(R.drawable.ic_suggestions_create_popup_highlightreal);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.create_gif_menu))) {
                    findItem.setIcon(R.drawable.ic_create_new_circle_dialog_gif);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.sa_collage))) {
                    findItem.setIcon(R.drawable.ic_create_new_circle_dialog_collage);
                } else if (isRemoveFromResultMenu(resources)) {
                    findItem.setIcon(R.drawable.gallery_ic_bottombar_remove);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.meitu_poster))) {
                    findItem.setIcon(R.drawable.ic_create_new_circle_dialog_meitu);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.meitu_video_clip))) {
                    findItem.setIcon(R.drawable.ic_create_new_circle_dialog_meitu);
                } else if (this.mSelectionDoneTitle.equals(resources.getString(R.string.delete_video_clips_option))) {
                    findItem.setIcon(R.drawable.gallery_ic_search_motion_photo_delete_clip_videos);
                } else if (PocFeatures.isEnabled(PocFeatures.SaveAsPdf) && this.mSelectionDoneTitle.equals(resources.getString(R.string.save_as_pdf))) {
                    findItem.setIcon(R.drawable.ic_create_popup_album);
                }
            }
            if (selectionMode == MenuDataBinding.SelectionMode.SELECT_DONE && itemMode == MenuDataBinding.ItemMode.SELECTED_ITEM) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                Optional.ofNullable(findItem.getActionView()).ifPresent(new T(this, 3));
            }
            findItem.setVisible(z);
        }
    }

    public final void updatePopupMenu(PopupMenuData popupMenuData, EventContext.OnSelectionListener onSelectionListener, int i2) {
        MenuDataBinding.SelectionMode selectionMode = getSelectionMode(onSelectionListener);
        Menu menu = popupMenuData.getMenu();
        updatePopupMenuAction(menu, selectionMode, i2);
        updateCommonPopupMenuAction(menu, selectionMode);
        popupMenuData.operate(i2);
    }

    public void updateVerizonCloudMenu() {
        MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.setVisible((int) R.id.action_verizon_cloud, Features.isEnabled(Features.SUPPORT_VERIZON_CLOUD));
        }
    }

    public final void setMenuVisibility(Menu menu, int i2, boolean z) {
        MenuItem findItem = menu.findItem(i2);
        if (findItem != null) {
            findItem.setEnabled(z);
            findItem.setVisible(z);
        }
    }

    public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
    }
}
