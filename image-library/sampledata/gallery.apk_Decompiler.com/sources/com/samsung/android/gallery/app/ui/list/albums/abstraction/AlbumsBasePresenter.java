package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A4.A;
import A4.C0369d;
import A4.C0375j;
import A4.W;
import C4.h;
import C4.i;
import C4.j;
import C4.k;
import C4.l;
import C4.m;
import C4.n;
import Fa.C0571z;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.album.AlbumMoveCmd;
import com.samsung.android.gallery.app.controller.album.UpdateAlbumSyncStatusCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.LocalAlbumDBUpdater;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBasePresenter<V extends IAlbumsBaseView> extends BaseListPresenter<V> {
    private final boolean SUPPORT_COVER_CHANGE = (!Features.isEnabled(Features.IS_UPSM));

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AlbumsMenuUpdater extends ListMenuUpdater {
        public AlbumsMenuUpdater(V v) {
            super(v, AlbumsBasePresenter.this);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$checkMoveToKnoxMenu$10(boolean z) {
            if (!KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER) || !z) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$checkMoveToKnoxMenu$11(boolean z) {
            if (!KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_SECURE_FOLDER) || !z) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$checkMoveToKnoxMenu$8(boolean z) {
            if (!KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.MOVE_TO_KNOX) || !z) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$checkMoveToKnoxMenu$9(boolean z) {
            if (!KnoxUtil.getInstance().isKnoxMoveOn(KnoxUtil.MoveType.REMOVE_FROM_KNOX) || !z) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$0(MediaItem[] mediaItemArr) {
            return AlbumsBasePresenter.this.supportDeleteAlbum(mediaItemArr);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$1(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || !AlbumsBasePresenter.this.supportChangeCover(mediaItemArr)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$2(long j2) {
            if ((j2 & 32) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$3(long j2) {
            if ((j2 & 8) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$4(long j2) {
            if ((j2 & 16) == 0 || Features.isEnabled(Features.IS_UPSM)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$5(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || !AlbumsBasePresenter.this.supportRename(mediaItemArr)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$6(int i2, long j2) {
            if (i2 <= 0 || !supportAlbumShare(j2)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$updateOptionsMenuAction$7(int i2, MediaItem[] mediaItemArr) {
            if (i2 != 1 || mediaItemArr[0].isFolder() || mediaItemArr[0].isReadOnlyAlbum()) {
                return false;
            }
            return true;
        }

        public void checkMoveToKnoxMenu(Menu menu, int i2) {
            boolean z;
            if (i2 > 0) {
                z = true;
            } else {
                z = false;
            }
            setMenuVisibility(menu, (int) R.id.action_move_to_knox, (BooleanSupplier) new j(z, 2));
            setMenuVisibility(menu, (int) R.id.action_remove_from_knox, (BooleanSupplier) new j(z, 3));
            setMenuVisibility(menu, (int) R.id.action_move_to_secure_folder, (BooleanSupplier) new j(z, 0));
            setMenuVisibility(menu, (int) R.id.action_remove_from_secure_folder, (BooleanSupplier) new j(z, 1));
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            AlbumsMenuUpdater albumsMenuUpdater;
            if (((IAlbumsBaseView) AlbumsBasePresenter.this.mView).isMoveMode()) {
                AlbumsBasePresenter.this.setMoveMenu(menu);
            } else if (AlbumsBasePresenter.this.isSelectionMode() || AlbumsBasePresenter.this.isOnAdvancedMouseMultiFocused() || AlbumsBasePresenter.this.isPopupMenuItemFocused()) {
                int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
                if (LocationKey.isAlbumsMatch(getLocationKey()) || LocationKey.isAllAlbumsMatch(getLocationKey())) {
                    MediaItem[] selectedItems = ((IAlbumsBaseView) AlbumsBasePresenter.this.mView).getSelectedItems();
                    long supportForGroup = MenuSupportHelper.getSupportForGroup(selectedItems);
                    String str = this.TAG;
                    Log.d(str, "updateOptionsMenuAction " + Long.toHexString(supportForGroup));
                    setMenuVisibility(menu, (int) R.id.action_delete_album_in_list, (BooleanSupplier) new W(1, this, selectedItems));
                    setMenuVisibility(menu, (int) R.id.action_change_cover_image, (BooleanSupplier) new k(this, selectedItemCountForMenuUpdate, selectedItems, 0));
                    setMenuVisibility(menu, (int) R.id.action_move, (BooleanSupplier) new l(supportForGroup, 0));
                    setMenuVisibility(menu, (int) R.id.action_folder_grouping, (BooleanSupplier) new l(supportForGroup, 1));
                    setMenuVisibility(menu, (int) R.id.action_folder_ungrouping, (BooleanSupplier) new l(supportForGroup, 2));
                    setMenuVisibility(menu, (int) R.id.action_rename_album_folder, (BooleanSupplier) new k(this, selectedItemCountForMenuUpdate, selectedItems, 1));
                    albumsMenuUpdater = this;
                    albumsMenuUpdater.setMenuVisibility(menu, (int) R.id.action_share_album, (BooleanSupplier) new m(albumsMenuUpdater, selectedItemCountForMenuUpdate, supportForGroup, 0));
                    if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                        albumsMenuUpdater.setMenuVisibility(menu, (int) R.id.action_lock_album, (BooleanSupplier) new n(selectedItemCountForMenuUpdate, selectedItems, 0));
                    }
                } else {
                    albumsMenuUpdater = this;
                }
                albumsMenuUpdater.checkMoveToKnoxMenu(menu, selectedItemCountForMenuUpdate);
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            updateOptionsMenuAction(menu, selectionMode);
            setMenuVisibility(menu, (int) R.id.action_select, !AlbumsBasePresenter.this.isSelectionMode());
        }
    }

    public AlbumsBasePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private MediaItem getFirstItem(MediaItem[] mediaItemArr) {
        for (MediaItem mediaItem : mediaItemArr) {
            if (!mediaItem.isFolder()) {
                return mediaItem;
            }
            MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
            if (albumsInFolder.length > 0) {
                return albumsInFolder[0];
            }
        }
        return null;
    }

    private String getFolderId(String str) {
        return str.substring(str.lastIndexOf(47) + 1);
    }

    private boolean handleAlbum(int i2, MediaItem mediaItem) {
        String requestKey = getRequestKey(i2, mediaItem);
        this.mBlackboard.postEvent(EventMessage.obtain(2004, mediaItem.getAlbumID(), new Object[]{Boolean.TRUE, mediaItem.getPath(), mediaItem}));
        String DATA_CURSOR = DataKey.DATA_CURSOR("location://albums/fileList");
        if (!this.mBlackboard.isEmpty(DATA_CURSOR)) {
            this.mBlackboard.erase(DATA_CURSOR);
        }
        this.mBlackboard.publish("data://albums/current", mediaItem);
        this.mBlackboard.post("command://MoveURL", requestKey);
        scrollToDataPosition(i2);
        removeOtherTabs(getLocationKey());
        return true;
    }

    private boolean handleFolder(MediaItem mediaItem) {
        if (!mediaItem.isFolder()) {
            return false;
        }
        this.mBlackboard.post("command://MoveURL", ArgumentsUtil.appendArgs(PickerUtil.appendPickerArgs(this.mBlackboard, getFolderLocationKey() + "/" + mediaItem.getFolderID()), "id", String.valueOf(mediaItem.getFolderID())));
        return true;
    }

    private boolean hasEmptyAlbum(MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem != null && mediaItem.isEmptyAlbum()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasFolderAlbum(MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem != null && mediaItem.isFolder()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasMergedAlbum(MediaItem[] mediaItemArr) {
        return Arrays.stream(mediaItemArr).filter(new C0571z(4)).anyMatch(new C0375j(7));
    }

    private boolean hasPredefinedAlbum(MediaItem[] mediaItemArr) {
        if (mediaItemArr == null) {
            return false;
        }
        int length = mediaItemArr.length;
        int i2 = 0;
        while (i2 < length) {
            MediaItem mediaItem = mediaItemArr[i2];
            if (mediaItem == null) {
                i2++;
            } else if (BucketUtils.contains(mediaItem.getAlbumID()) || BucketUtils.isRoot(mediaItem.getAlbumID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean hasVirtualAlbum(MediaItem[] mediaItemArr) {
        return Arrays.stream(mediaItemArr).filter(new C0571z(4)).anyMatch(new C0375j(6));
    }

    private boolean isEnableMove(MediaItem[] mediaItemArr) {
        if (!(mediaItemArr == null || getCurrentItem() == null)) {
            if (getFolderId(getFolderLocationKey()).equals(getFolderId((String) this.mBlackboard.read("album_move_src_location")))) {
                return false;
            }
            for (MediaItem mediaItem : mediaItemArr) {
                if (mediaItem.isFolder() && (mediaItem.getAlbumID() == getCurrentItem().getAlbumID() || !isEnableMove(mediaItem.getItemsInFolder()))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$2(EventMessage eventMessage, AlbumsBaseViewAdapter albumsBaseViewAdapter) {
        int i2;
        if (eventMessage.obj == null || !((IAlbumsBaseView) this.mView).isViewResumed()) {
            i2 = -1;
        } else {
            i2 = ((Integer) eventMessage.obj).intValue();
        }
        if (i2 >= 0) {
            i2 = albumsBaseViewAdapter.getViewPosition(i2);
        }
        if (i2 >= 0) {
            albumsBaseViewAdapter.notifyItemRangeChanged(i2, 1);
        } else {
            albumsBaseViewAdapter.notifyItemRangeChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onListItemClickInternal$0(int i2, MediaItem mediaItem, Integer num) {
        if (num.intValue() == 0) {
            handleAlbum(i2, mediaItem);
        } else if (num.intValue() == 2) {
            handleAlbum(i2, mediaItem);
            BiometricPromptCompat.setupScreenLock(getActivity());
        }
    }

    private void moveItems() {
        if (((IAlbumsBaseView) this.mView).isMoveMode()) {
            new AlbumMoveCmd().execute(this, (MediaItem[]) this.mBlackboard.read("data://album_move"), getCurrentItem(), Boolean.valueOf(LocationKey.isAlbums(getLocationKey())));
        }
    }

    /* access modifiers changed from: private */
    public void onAlbumSyncStatusUpdated(Object obj, Bundle bundle) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS)) {
            Blackboard blackboard = this.mBlackboard;
            Boolean bool2 = Boolean.FALSE;
            if (((Boolean) blackboard.read("local_db_updating", bool2)).booleanValue() || ((Boolean) this.mBlackboard.read("album_sync_data_updating", bool2)).booleanValue() || Features.isEnabled(Features.IS_UPSM)) {
                Log.w(this.TAG, "onAlbumSyncStatusUpdated skip");
                return;
            }
            new UpdateAlbumSyncStatusCmd().execute(this, bool);
            this.mBlackboard.erase("album_sync_data_changed");
        }
    }

    /* access modifiers changed from: private */
    public void onCoverChanged(Object obj, Bundle bundle) {
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            int intValue = ((Integer) objArr[0]).intValue();
            String str = (String) objArr[1];
            String str2 = (String) objArr[2];
            MediaData mediaData = ((IAlbumsBaseView) this.mView).getMediaData(getLocationKey());
            if (mediaData != null) {
                int updateCoverItem = mediaData.updateCoverItem(intValue, str, str2);
                if (updateCoverItem >= 0) {
                    ((IAlbumsBaseView) this.mView).getAdapter().notifyItemChanged(((IAlbumsBaseView) this.mView).getAdapter().getViewPosition(updateCoverItem));
                    ShortcutHelper.getInstance().updateHomeScreenShortcut(getActivity(), mediaData.read(updateCoverItem));
                    return;
                }
                return;
            }
            Log.e(this.TAG, "onCoverChanged: mediaData is null");
        }
    }

    /* access modifiers changed from: private */
    public void onLocalDatabaseUpdated(Object obj, Bundle bundle) {
        if (obj == null || ((Boolean) this.mBlackboard.read("local_db_updating", Boolean.FALSE)).booleanValue()) {
            Log.e(this.TAG, "onLocalDatabaseUpdated skip null data or updating");
            return;
        }
        Context context = getContext();
        Object[] objArr = (Object[]) obj;
        if (objArr.length > 1 && context != null) {
            Log.d(this.TAG, "onLocalDatabaseUpdated");
            new LocalAlbumDBUpdater(this.mBlackboard).updateOnUi(context, (ArrayList) objArr[0], (ArrayList) objArr[1]);
            this.mBlackboard.erase("local_db_update_data");
        }
    }

    /* access modifiers changed from: private */
    public void onNewItemCreated(Object obj, Bundle bundle) {
        int i2;
        boolean z = false;
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            int intValue = ((Integer) objArr[2]).intValue();
            String str3 = (String) objArr[3];
            if (objArr.length > 4) {
                i2 = ((Integer) objArr[4]).intValue();
            } else {
                i2 = -1;
            }
            z = ((IAlbumsBaseView) this.mView).onNewItemCreated(str, str2, intValue, str3, i2);
        }
        if (!z) {
            forceReloadData();
        }
    }

    public final void addMoveBottomBar() {
        addMoveBottomBar(false);
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        if (this.SUPPORT_COVER_CHANGE) {
            arrayList.add(new SubscriberInfo("data://useralbum_cover_change", new h(this, 0)).setWorkingOnUI());
        }
        if (supportLocalDatabaseUpdate()) {
            arrayList.add(new SubscriberInfo("local_db_update_data", new h(this, 1)).setWorkingOnUI().setTriggerPreloadedAsync());
        }
        arrayList.add(new SubscriberInfo("album_sync_data_changed", new h(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://EnterMoveMode", new h(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://ExitMoveMode", new h(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("data://usernew_item_creation", new h(this, 5)).setWorkingOnUI());
    }

    public void enterMoveMode(Object obj, Bundle bundle) {
        this.mBlackboard.publish("data://album_move", (MediaItem[]) obj);
        this.mBlackboard.publish("album_move_src_location", getFolderLocationKey());
        Blackboard blackboard = this.mBlackboard;
        Boolean bool = Boolean.TRUE;
        blackboard.post("command://HideBottomBar", bool);
        this.mBlackboard.postEvent(EventMessage.obtain(1003, bool));
        addMoveBottomBar();
        ((IAlbumsBaseView) this.mView).onEnterMoveMode();
        updateToolbar(((IAlbumsBaseView) this.mView).getToolbar());
    }

    public void exitMoveMode(Object obj, Bundle bundle) {
        if (((IAlbumsBaseView) this.mView).isMoveMode() && ArgumentsUtil.equals(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), getLocationKey()) && ArgumentsUtil.equals(getFolderLocationKey(), (String) this.mBlackboard.read("album_move_tgt_location"))) {
            if (((Boolean) obj).booleanValue()) {
                moveItems();
            }
            this.mBlackboard.post("command://HideBottomMoveBar", Boolean.TRUE);
            this.mBlackboard.erase("data://album_move");
            this.mBlackboard.erase("album_move_src_location");
            this.mBlackboard.erase("album_move_tgt_location");
            this.mBlackboard.post("command:///OnBackPressInvokableStateChanged", (Object) null);
            ((IAlbumsBaseView) this.mView).onExitMoveMode();
        }
        updateToolbar(((IAlbumsBaseView) this.mView).getToolbar());
    }

    public int getCurrentViewDepth() {
        return ((IAlbumsBaseView) this.mView).getListView().getDepth();
    }

    public String getFolderLocationKey() {
        return "location://folder/root";
    }

    public int getMaxDepth() {
        return ((IAlbumsBaseView) this.mView).getListView().getMaxDepth();
    }

    public String getRequestKey(int i2, MediaItem mediaItem) {
        String requestKeyWithType = getRequestKeyWithType(mediaItem, "location://albums/fileList");
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
                requestKeyWithType = ArgumentsUtil.appendArgs(requestKeyWithType, "with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
            }
            requestKeyWithType = ArgumentsUtil.appendArgs(requestKeyWithType, "type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(requestKeyWithType, Message.KEY_POSITION, String.valueOf(i2)), "count", String.valueOf(mediaItem.getCount()));
    }

    public String getRequestKeyWithType(MediaItem mediaItem, String str) {
        HashSet<Integer> subAlbumIds;
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isMergedAlbum()) {
            return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "id", String.valueOf(mediaItem.getAlbumID())), "type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID : albumsInFolder) {
            arrayList.add(Integer.valueOf(albumID.getAlbumID()));
        }
        if (arrayList.size() == 0 && (subAlbumIds = AlbumInfo.getSubAlbumIds(mediaItem.getAlbumID())) != null) {
            arrayList.addAll(subAlbumIds);
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "mergedAlbumId", String.valueOf(mediaItem.getAlbumID())), "ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 104) {
            forceReloadData();
            return false;
        } else if (!PocFeatures.SUPPORT_LOCKED_ALBUM || i2 != 2009) {
            return super.handleEvent(eventMessage);
        } else {
            Optional.ofNullable(((IAlbumsBaseView) this.mView).getAdapter()).ifPresent(new A(6, (Object) this, (Object) eventMessage));
            return true;
        }
    }

    public boolean isEmptyFolder(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isFolder()) {
            return false;
        }
        MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
        if (albumsInFolder == null || albumsInFolder.length == 0) {
            return true;
        }
        return false;
    }

    public boolean needRestoreMoveBar() {
        return ((IAlbumsBaseView) this.mView).isMoveMode();
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (PocFeatures.SUPPORT_LOCKED_ALBUM && MediaItemUtil.containsLockedAlbum(mediaItem)) {
            new BiometricPromptCompat().setTitle(R.string.open_locked_album).setCallback(new i((Object) this, i2, (Object) mediaItem, 0)).authenticateCustom(getActivity());
        } else if (!handleFolder(mediaItem)) {
            handleAlbum(i2, mediaItem);
        }
    }

    public void onRequestBeamData(Object obj, Bundle bundle) {
        int i2;
        if (isSelectionMode() && PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            Object[] objArr = new Object[3];
            MediaItem[] selectedItems = getSelectedItems();
            if (selectedItems == null || selectedItems.length <= 0) {
                i2 = 0;
            } else {
                i2 = 0;
                for (MediaItem mediaItem : selectedItems) {
                    if (mediaItem != null) {
                        i2 += mediaItem.getCount();
                    }
                }
            }
            objArr[0] = Integer.valueOf(i2);
            if (i2 <= 500) {
                objArr[1] = getSelectedItems();
            }
            objArr[2] = Boolean.TRUE;
            this.mBlackboard.publish("data://user/Beam", objArr);
        }
    }

    public void onViewChanged(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "onViewChanged " + obj);
        changeViewDepth(((IAlbumsBaseView) this.mView).getListView(), ((Integer) obj).intValue());
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename_album_folder) {
                public boolean getDefaultExcluding() {
                    return isUpsm();
                }

                public boolean getDefaultVisibility() {
                    AlbumsBasePresenter albumsBasePresenter = AlbumsBasePresenter.this;
                    return albumsBasePresenter.supportRename(albumsBasePresenter.getSelectedItems());
                }
            });
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_move) {
                public boolean getDefaultExcluding() {
                    return isUpsm();
                }

                public boolean getDefaultVisibility() {
                    if (AlbumsBasePresenter.this.getSelectedItemCount() > 0) {
                        return true;
                    }
                    return false;
                }
            });
            menuDataBinding.addBinding(new MenuDataBinding.SimpleMenuData(R.id.action_lock_album, true, !PocFeatures.SUPPORT_LOCKED_ALBUM));
        }
        super.prepareOptionsMenu(menu);
    }

    public void restoreMoveBottomBar() {
        if (needRestoreMoveBar()) {
            addMoveBottomBar(true);
        } else if (PickerUtil.isNormalLaunchMode(this.mBlackboard)) {
            this.mBlackboard.post("command://RestoreBottomMoveBar", (Object) null);
        }
    }

    public void scrollToDataPosition(int i2) {
        Optional.ofNullable(((IAlbumsBaseView) this.mView).getAdapter()).ifPresent(new C0369d(i2, 2));
    }

    public void setMoveMenu(Menu menu) {
        menu.removeGroup(R.id.no_item);
        menu.removeGroup(R.id.select_mode);
        menu.removeGroup(R.id.select_mode_bottom);
        menu.removeGroup(R.id.select_mode_knox);
        menu.removeGroup(R.id.normal_mode);
        menu.setGroupVisible(R.id.move_mode, true);
        MenuItem findItem = menu.findItem(R.id.action_folder_add_folder);
        if (findItem != null) {
            findItem.setShowAsAction(2);
        }
    }

    public void showMoveBottomBar(boolean z) {
        boolean equals = getFolderLocationKey().equals(this.mBlackboard.read("album_move_src_location"));
        boolean z3 = !equals;
        if (!equals && DrawerUtil.supportDrawerLayout(this.mBlackboard)) {
            z3 = isEnableMove((MediaItem[]) this.mBlackboard.read("data://album_move"));
        }
        this.mBlackboard.publish("album_move_tgt_location", getFolderLocationKey());
        this.mBlackboard.post("command://ShowBottomMoveBar", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z3)});
    }

    public boolean supportChangeCover(MediaItem[] mediaItemArr) {
        if (Features.isEnabled(Features.IS_UPSM) || hasFolderAlbum(mediaItemArr) || hasEmptyAlbum(mediaItemArr) || hasVirtualAlbum(mediaItemArr)) {
            return false;
        }
        return true;
    }

    public boolean supportDeleteAlbum(MediaItem[] mediaItemArr) {
        if (mediaItemArr.length <= 0 || !Arrays.stream(mediaItemArr).filter(new C0571z(4)).noneMatch(new C0375j(6))) {
            return false;
        }
        return true;
    }

    public boolean supportLocalDatabaseUpdate() {
        return false;
    }

    public boolean supportRename(MediaItem[] mediaItemArr) {
        return (getSelectedItemCount() == 1 || supportRename(getBlackboard())) && !hasPredefinedAlbum(mediaItemArr) && !hasVirtualAlbum(mediaItemArr) && !hasMergedAlbum(mediaItemArr);
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setNavigationIcon((Drawable) null);
            toolbar.setSubtitle((CharSequence) null);
            return;
        }
        Log.e(this.TAG, "fail updateToolbar");
    }

    private void addMoveBottomBar(boolean z) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.read("data://album_move");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w(this.TAG, "addMoveBottomBar : items null");
            return;
        }
        int length = mediaItemArr.length;
        MediaItem firstItem = getFirstItem(mediaItemArr);
        if (firstItem == null) {
            firstItem = mediaItemArr[0];
        }
        this.mBlackboard.post(z ? "command://RestoreBottomMoveBar" : "command://AddBottomMoveBar", new Object[]{Integer.valueOf(length), firstItem, ((IAlbumsBaseView) this.mView).getListView()});
        showMoveBottomBar(!z);
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new AlbumsMenuUpdater(v);
    }

    private boolean supportRename(Blackboard blackboard) {
        return ((Boolean) Optional.ofNullable(blackboard.read("data://floating_pop_menu")).orElse(Boolean.FALSE)).booleanValue();
    }
}
