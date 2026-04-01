package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import Fa.C0571z;
import M4.j;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.receiver.FamilyGroupMemberObserver;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.SharingPicturesMenuHandler;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuData;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuVisibility;
import com.samsung.android.gallery.module.account.FamilyGroupHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingPicturesPresenter extends PicturesPresenter<ISharingPicturesView> {
    private FamilyGroupMemberObserver mFamilyGroupMemberObserver;
    private MediaItem mHeaderItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SharingPicturesMenuUpdater extends ListMenuUpdater {
        public SharingPicturesMenuUpdater(ISharingPicturesView iSharingPicturesView) {
            super(iSharingPicturesView, SharingPicturesPresenter.this);
        }

        private boolean hasItems() {
            if (((ISharingPicturesView) SharingPicturesPresenter.this.mView).getItemCount() > 0) {
                return true;
            }
            return false;
        }

        private boolean hasOwnedItems() {
            return Arrays.stream(SharingPicturesPresenter.this.getSelectedItems()).filter(new C0571z(4)).allMatch(new j(18));
        }

        private boolean isGroupDetailMenuVisible() {
            if (MdeGroupHelper.isSAFamilyGroupId(MediaItemMde.getGroupId(SharingPicturesPresenter.this.getHeaderItem()))) {
                return FamilyGroupHelper.hasFamilyGroup(SharingPicturesPresenter.this.getContext());
            }
            return PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateOptionsMenuAction$0(boolean z, MenuItem menuItem) {
            boolean z3;
            if (getSelectedItemCountForMenuUpdate() <= 0 || (!z && !hasOwnedItems())) {
                z3 = false;
            } else {
                z3 = true;
            }
            menuItem.setVisible(z3);
            menuItem.setEnabled(menuItem.isVisible());
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateOptionsMenuAction$1(MenuItem menuItem) {
            boolean z;
            if (SharingPicturesPresenter.this.isFromStorageUsage() || getSelectedItemCountForMenuUpdate() <= 0) {
                z = false;
            } else {
                z = true;
            }
            menuItem.setVisible(z);
            menuItem.setEnabled(menuItem.isVisible());
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$2() {
            return !TextUtils.isEmpty(MediaItemMde.getWebLinkUrl(SharingPicturesPresenter.this.getHeaderItem()));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$3(boolean z) {
            if (SharingPicturesPresenter.this.isOwner() || z) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$4(boolean z) {
            if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || !z) {
                return false;
            }
            return true;
        }

        public boolean getCancelMenuVisibility() {
            if (!super.getCancelMenuVisibility() || SharingPicturesPresenter.this.isFromStorageUsage()) {
                return false;
            }
            return true;
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            boolean z3;
            MenuDataBinding menuDataBinding = SharingPicturesPresenter.this.getMenuDataBinding();
            if (menuDataBinding != null) {
                boolean isFamilyAlbum = SharingPicturesPresenter.this.isFamilyAlbum();
                if (selectionMode == MenuDataBinding.SelectionMode.SELECT) {
                    if (menuDataBinding.hasBinding(R.id.action_remove)) {
                        Optional.ofNullable(menu.findItem(R.id.action_remove)).ifPresent(new l(this, isFamilyAlbum));
                    }
                    if (menuDataBinding.hasBinding(R.id.action_download_in_sharing_album)) {
                        Optional.ofNullable(menu.findItem(R.id.action_download_in_sharing_album)).ifPresent(new c(2, this));
                    }
                } else {
                    if (menuDataBinding.hasBinding(R.id.action_select)) {
                        menuDataBinding.setVisible((int) R.id.action_select, hasItems());
                    }
                    boolean z7 = false;
                    if (menuDataBinding.hasBinding(R.id.action_change_sharing_cover_image)) {
                        if (!hasItems() || !SharingPicturesPresenter.this.isOwner() || PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                        menuDataBinding.setVisible((int) R.id.action_change_sharing_cover_image, z3);
                    }
                    if (menuDataBinding.hasBinding(R.id.action_delete_shared_album)) {
                        menuDataBinding.setVisible((int) R.id.action_delete_shared_album, SharingPicturesPresenter.this.isOwner());
                    }
                    if (menuDataBinding.hasBinding(R.id.action_rename_shared_album)) {
                        if (!SharingPicturesPresenter.this.isOwner() || PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
                            z = false;
                        } else {
                            z = true;
                        }
                        menuDataBinding.setVisible((int) R.id.action_rename_shared_album, z);
                    }
                    if (menuDataBinding.hasBinding(R.id.action_leave_shared_album)) {
                        if (!SharingPicturesPresenter.this.isOwner() && !isFamilyAlbum) {
                            z7 = true;
                        }
                        menuDataBinding.setVisible((int) R.id.action_leave_shared_album, z7);
                    }
                    if (menuDataBinding.hasBadge((int) R.id.action_sortby)) {
                        menuDataBinding.setVisible((int) R.id.action_sortby, Features.isEnabled(Features.SUPPORT_SHARED_SORT));
                    }
                    menuDataBinding.setVisible((int) R.id.action_show_group_detail, isGroupDetailMenuVisible());
                    if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
                        menuDataBinding.setVisible((int) R.id.action_share_link_to_album, (BooleanSupplier) new m(this));
                    }
                    if (menuDataBinding.hasBinding(R.id.action_album_settings)) {
                        menuDataBinding.setVisible((int) R.id.action_album_settings, (BooleanSupplier) new n(this, isFamilyAlbum));
                    }
                    if (menuDataBinding.hasBinding(R.id.action_preview_suggestion_to_add)) {
                        menuDataBinding.setVisible((int) R.id.action_preview_suggestion_to_add, (BooleanSupplier) new o(isFamilyAlbum));
                    }
                }
                if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE && SharingPicturesPresenter.this.isFromStorageUsage()) {
                    menu.removeGroup(R.id.no_item);
                }
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9 = false;
            if (i2 == 0) {
                setMenuVisibility(menu, (int) R.id.action_select, !SharingPicturesPresenter.this.isSelectionMode());
                if (SharingPicturesPresenter.this.isSelectionMode() || !PopupMenuVisibility.isActiveSharedAlbumRename(SharingPicturesPresenter.this.getHeaderItem())) {
                    z = false;
                } else {
                    z = true;
                }
                setMenuVisibility(menu, (int) R.id.action_rename_shared_album, z);
                setMenuVisibility(menu, (int) R.id.action_delete_shared_album, PopupMenuVisibility.isActiveSharedAlbumDelete(SharingPicturesPresenter.this.getHeaderItem()));
                if (!hasItems() || !PopupMenuVisibility.isActiveSharedAlbumChangeCover(SharingPicturesPresenter.this.getHeaderItem())) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                setMenuVisibility(menu, (int) R.id.action_change_sharing_cover_image, z3);
                setMenuVisibility(menu, (int) R.id.action_leave_shared_album, PopupMenuVisibility.isActiveSharedAlbumLeave(SharingPicturesPresenter.this.getHeaderItem()));
                if (SharingPicturesPresenter.this.isOwner() || SharingPicturesPresenter.this.isFamilyAlbum()) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                setMenuVisibility(menu, (int) R.id.action_album_settings, z7);
            }
            if (i2 == 1) {
                if (getSelectedItemCountForMenuUpdate() > 0 && (SharingPicturesPresenter.this.isFamilyAlbum() || hasOwnedItems())) {
                    z9 = true;
                }
                setMenuVisibility(menu, (int) R.id.action_remove, z9);
            }
        }
    }

    public SharingPicturesPresenter(Blackboard blackboard, ISharingPicturesView iSharingPicturesView) {
        super(blackboard, iSharingPicturesView);
    }

    private void clearUnreadCount() {
        String spaceId = getSpaceId();
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null) {
            return;
        }
        if ((MediaItemMde.getUnreadCount(mediaItem) > 0 || ArgumentsUtil.getArgValue(getLocationKey(), "isNewItemUpdated", false)) && !TextUtils.isEmpty(spaceId)) {
            MdeSharingHelper.clearUnreadCount(spaceId);
        }
    }

    /* access modifiers changed from: private */
    public void exit(Object obj, Bundle bundle) {
        this.mBlackboard.postEvent(EventMessage.obtain(1003));
        if (!((ISharingPicturesView) this.mView).isDestroyed()) {
            String str = (String) obj;
            if (TextUtils.equals(str, getSpaceId())) {
                new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceItem, str);
                this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            }
        }
    }

    private String getSpaceId() {
        String str;
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem != null) {
            str = MediaItemMde.getSpaceId(mediaItem);
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return ArgumentsUtil.getArgValue(getLocationKey(), "id");
        }
        return str;
    }

    /* access modifiers changed from: private */
    public boolean isOwner() {
        return MediaItemMde.isOwnedByMe(this.mHeaderItem);
    }

    private boolean isSupportAlbumLinkTip(int i2) {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK || !PreferenceCache.SharedAlbumLinkTip.getBoolean() || isSelectionMode() || isFromStorageUsage() || !isOwner() || i2 <= 0 || isFamilyAlbum()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewResume$3() {
        GalleryToolbar toolbar;
        V v = this.mView;
        if (v != null && (toolbar = ((ISharingPicturesView) v).getToolbar()) != null) {
            toolbar.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeaderItemCount$4(int[] iArr) {
        ((ISharingPicturesView) this.mView).updateCoverItemCount(iArr[0], iArr[1]);
    }

    /* access modifiers changed from: private */
    public void onFamilyGroupMemberInfoChanged(Object obj, Bundle bundle) {
        ((ISharingPicturesView) this.mView).updateEmptyView();
    }

    private void startGroupMemberSync() {
        String groupId = MediaItemMde.getGroupId(getHeaderItem());
        if (groupId == null) {
            return;
        }
        if (groupId.startsWith("UNM1") || MdeGroupHelper.isSAFamilyGroup(groupId)) {
            new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_SYNC, RequestSync.Types.GroupMember, groupId);
        }
    }

    private void startSharedAlbumItemsSync() {
        String spaceId = getSpaceId();
        if (!TextUtils.isEmpty(spaceId)) {
            new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceItem, spaceId);
        }
    }

    /* access modifiers changed from: private */
    public void syncSharedAlbum(Object obj, Bundle bundle) {
        String str = (String) obj;
        if (!TextUtils.isEmpty(str) && str.equals(getSpaceId())) {
            forceReloadData();
            startSharedAlbumItemsSync();
        }
    }

    /* access modifiers changed from: private */
    public void updateGroupMember(Object obj, Bundle bundle) {
        SharingPicturesViewAdapter sharingPicturesViewAdapter = (SharingPicturesViewAdapter) ((ISharingPicturesView) this.mView).getAdapter();
        if (sharingPicturesViewAdapter != null) {
            sharingPicturesViewAdapter.onGroupMemberDataChangedOnUi();
        }
    }

    /* access modifiers changed from: private */
    public void updateHeaderItemCount() {
        String spaceId = getSpaceId();
        if (spaceId != null) {
            ThreadUtil.postOnUiThread(new g(1, this, new MdeDatabase().getSharedItemCount(spaceId)));
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            arrayList.add(new SubscriberInfo("global://sharing/member/infoChanged", new k(this, 0)).setWorkingOnUI());
        }
    }

    public MenuHandler createMenuHandler() {
        return new SharingPicturesMenuHandler();
    }

    public PopupMenuData createPopupMenuData(Menu menu, int i2) {
        if (isFromStorageUsage()) {
            return null;
        }
        return super.createPopupMenuData(menu, i2);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://SharingPicturesViewChanged", new k(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://SharingPicturesItemsSync", new k(this, 2)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://SharingPicturesGroupMemberChanged", new k(this, 3)).setWorkingOnUI());
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            arrayList.add(new SubscriberInfo("command://FinishSharingPicturesStorageUse", new k(this, 4)).setWorkingOnUI());
        }
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public String getLabelForAccessibility(Context context) {
        return (String) Optional.ofNullable(((ISharingPicturesView) this.mView).getHeaderItem()).map(new h(1)).orElse((Object) null);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 6009) {
            Blackboard blackboard = this.mBlackboard;
            blackboard.post("command://MoveURL", MdeAlbumHelper.buildSharingPicturesSettingLocation(blackboard.getName(), ((ISharingPicturesView) this.mView).getFamilyAlbumId(), getHeaderItem()));
            return true;
        } else if (i2 != 6010) {
            return super.handleEvent(eventMessage);
        } else {
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_VIEW_SUGGESTED_PICTURES_AND_VIDEOS);
            launchPreviewSuggestions(((ISharingPicturesView) this.mView).getFamilyAlbumId(), getHeaderItem(), ((Boolean) eventMessage.obj).booleanValue());
            return true;
        }
    }

    public boolean isFamilyAlbum() {
        return MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(this.mHeaderItem));
    }

    public boolean isFromStorageUsage() {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE || !((Boolean) Optional.ofNullable(getLocationKey()).map(new h(0)).orElse(Boolean.FALSE)).booleanValue()) {
            return false;
        }
        return true;
    }

    public void launchPreviewSuggestions(int i2, MediaItem mediaItem, boolean z) {
        this.mBlackboard.post("command://MoveURL", MdeAlbumHelper.buildSharingPicturesSuggestionLocation(i2, MediaItemMde.getGroupId(mediaItem), MediaItemMde.getSpaceId(mediaItem), z));
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        ThreadUtil.postOnBgThread(new i(this, 1));
        if (isSupportAlbumLinkTip(getDataCount())) {
            ISharingPicturesView iSharingPicturesView = (ISharingPicturesView) this.mView;
            Objects.requireNonNull(iSharingPicturesView);
            ThreadUtil.postOnUiThread(new f(2, iSharingPicturesView));
        }
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        Optional.ofNullable(((ISharingPicturesView) this.mView).getCoverView()).ifPresent(new j(0));
        super.onEnterSelectionMode(obj, bundle);
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        Optional.ofNullable(((ISharingPicturesView) this.mView).getCoverView()).ifPresent(new j(1));
        super.onExitSelectionMode(obj, bundle);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (i2 < 0) {
            return;
        }
        if (((Boolean) this.mBlackboard.read("data://on_location_moving", Boolean.FALSE)).booleanValue()) {
            Log.she(this.TAG, "skip OnListItemClick. on location moving");
            return;
        }
        new VuLauncher(this.mBlackboard).launch(ArgumentsUtil.appendArgs(getLocationKey(), "owner", Boolean.toString(isOwner())), i2, mediaItem);
        this.mBlackboard.publish("command://RemoveSiblingsFragments", new String[]{"location://sharing/albums"});
    }

    public void onLocationKeyAssigned() {
        super.onLocationKeyAssigned();
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://sharing/albums");
        try {
            if (!isFromStorageUsage()) {
                this.mHeaderItem = open.read(UnsafeCast.toInt(ArgumentsUtil.getArgValue(getLocationKey(), Message.KEY_POSITION)));
            }
            if (open != null) {
                open.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        if (isFamilyAlbum() && Features.isEnabled(Features.SUPPORT_FAMILY_ACCOUNT_PROVIDER)) {
            FamilyGroupMemberObserver familyGroupMemberObserver = new FamilyGroupMemberObserver();
            this.mFamilyGroupMemberObserver = familyGroupMemberObserver;
            familyGroupMemberObserver.registerObserver(getApplicationContext());
        }
    }

    public void onViewDestroy() {
        FamilyGroupMemberObserver familyGroupMemberObserver = this.mFamilyGroupMemberObserver;
        if (familyGroupMemberObserver != null) {
            familyGroupMemberObserver.unregisterObserver(getApplicationContext());
        }
        super.onViewDestroy();
    }

    public void onViewResume() {
        super.onViewResume();
        startSharedAlbumItemsSync();
        startGroupMemberSync();
        clearUnreadCount();
        ThreadUtil.postOnUiThreadDelayed(new i(this, 0), 200);
    }

    public void updateHeaderItem(MediaItem mediaItem) {
        this.mHeaderItem = mediaItem;
    }

    public void updateToolbar(Toolbar toolbar) {
        String str;
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING || isSelectionMode()) {
            super.updateToolbar(toolbar);
            return;
        }
        MediaItem headerItem = ((ISharingPicturesView) this.mView).getHeaderItem();
        ISharingPicturesView iSharingPicturesView = (ISharingPicturesView) this.mView;
        if (headerItem != null) {
            str = headerItem.getTitle();
        } else {
            str = null;
        }
        iSharingPicturesView.updateCustomCover(1, str);
        setNavigationUpButton(toolbar);
        ThreadUtil.postOnBgThread(new i(this, 1));
    }

    public ListMenuUpdater createMenuUpdateDelegation(ISharingPicturesView iSharingPicturesView) {
        return new SharingPicturesMenuUpdater(iSharingPicturesView);
    }
}
