package com.samsung.android.gallery.app.ui.list.albums.mx;

import E9.a;
import I4.b;
import I4.e;
import I4.f;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import com.samsung.android.gallery.app.controller.sharing.RequestSyncCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.mx.IMxAlbumsView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuVisibility;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsPresenter<V extends IMxAlbumsView> extends AlbumsPresenter<V> {
    private boolean mTryConnectSession;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$AlbumKind;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind[] r0 = com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$AlbumKind = r0
                com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind r1 = com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind.REPRESENTATIVE_ALBUMS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$AlbumKind     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind r1 = com.samsung.android.gallery.app.ui.list.albums.mx.AlbumKind.SHARED_ALBUMS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MxAlbumsMenuUpdater extends AlbumsBasePresenter<V>.AlbumsMenuUpdater {
        public MxAlbumsMenuUpdater(V v) {
            super(v);
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            boolean z3;
            boolean z7;
            boolean z9;
            super.updateOptionsMenuAction(menu, selectionMode);
            boolean z10 = false;
            if (((IMxAlbumsView) MxAlbumsPresenter.this.mView).getMediaData((String) null).getCount() < 1) {
                z = true;
            } else {
                z = false;
            }
            int selectedItemCountForMenuUpdate = getSelectedItemCountForMenuUpdate();
            boolean isSelectionMode = MxAlbumsPresenter.this.isSelectionMode();
            if (z || isSelectionMode) {
                z3 = false;
            } else {
                z3 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_select, z3);
            if (!((IMxAlbumsView) MxAlbumsPresenter.this.mView).isEssentialViewMode() || isSelectionMode) {
                z7 = false;
            } else {
                z7 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_manage_albums, z7);
            if (!((IMxAlbumsView) MxAlbumsPresenter.this.mView).isEssentialViewMode() || selectedItemCountForMenuUpdate <= 0) {
                z9 = false;
            } else {
                z9 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_remove_from_essential_albums, z9);
            if (!((IMxAlbumsView) MxAlbumsPresenter.this.mView).isEssentialViewMode() && !isSelectionMode) {
                z10 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_sortby_album, z10);
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            MediaItem[] selectedItems = MxAlbumsPresenter.this.getSelectedItems();
            if (selectedItems.length != 1 || !selectedItems[0].isSharing()) {
                updateOptionsMenuAction(menu, selectionMode);
                return;
            }
            setMenuVisibility(menu, (int) R.id.action_delete_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumDelete(selectedItems[0]));
            setMenuVisibility(menu, (int) R.id.action_rename_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumRename(selectedItems[0]));
            setMenuVisibility(menu, (int) R.id.action_leave_shared_album_in_list, PopupMenuVisibility.isActiveSharedAlbumLeave(selectedItems[0]));
        }

        public void updateVerizonCloudMenu() {
            boolean z;
            MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
            if (menuDataBinding != null) {
                if (((IMxAlbumsView) MxAlbumsPresenter.this.mView).isEssentialViewMode() || !Features.isEnabled(Features.SUPPORT_VERIZON_CLOUD)) {
                    z = false;
                } else {
                    z = true;
                }
                menuDataBinding.setVisible((int) R.id.action_verizon_cloud, z);
            }
        }
    }

    public MxAlbumsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewResume$0(MxAlbumsViewAdapter mxAlbumsViewAdapter) {
        new RequestSyncCmd().execute(this, getLocationKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewResume$1() {
        Optional.ofNullable(((IMxAlbumsView) this.mView).getAdapter()).ifPresent(new a(26, this));
    }

    private void launchSharedAlbum(int i2, MediaItem mediaItem) {
        AnalyticsEventId analyticsEventId;
        String groupId = MediaItemMde.getGroupId(mediaItem);
        getBlackboard().post("command://MoveURL", new UriBuilder("location://sharing/albums/fileList").appendArg("id", MediaItemMde.getSpaceId(mediaItem)).appendArg(Message.KEY_POSITION, i2 % 10000000).appendArg("groupId", groupId).appendArg("owner", MediaItemMde.isOwnedByMe(mediaItem)).build());
        getBlackboard().publish("data://albums/current", mediaItem);
        if (MdeGroupHelper.isSAFamilyGroup(groupId)) {
            analyticsEventId = AnalyticsEventId.EVENT_FAMILY_SHARED_VIEW_ENTER;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_VIEW_ENTER;
        }
        postAnalyticsLog(analyticsEventId);
        scrollToDataPosition(i2);
    }

    /* access modifiers changed from: private */
    public void onEssentialAlbumsChanged(Object obj, Bundle bundle) {
        ((IMxAlbumsView) this.mView).inflateEmptyView();
    }

    /* access modifiers changed from: private */
    public void onSharedAlbumsBadgeUpdated(Object obj, Bundle bundle) {
        ((IMxAlbumsView) this.mView).updateSharingBadge();
    }

    /* access modifiers changed from: private */
    public void updateTipCardPreference() {
        PreferenceCache preferenceCache = PreferenceCache.MxAlbumTipCardCount;
        int i2 = preferenceCache.getInt();
        if (i2 >= 0 && i2 <= 5) {
            if (i2 == 5) {
                Optional.ofNullable(((IMxAlbumsView) this.mView).getAdapter()).ifPresent(new b(3));
            }
            preferenceCache.setInt(i2 + 1);
        }
    }

    public MenuDataBinding createFullMenuDataBinding() {
        return MenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/albums/selectEssentialAlbums", new e(this, 0)).setWorkingOnUI());
    }

    public MenuDataBinding createMenuDataBinding() {
        try {
            Trace.beginSection("createMenuDataBinding");
            return MenuFactory.createMenuForFirstEntry();
        } finally {
            Trace.endSection();
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("data://shared_albums_updated", new e(this, 1)).setWorkingOnUI());
    }

    public void onDividerClicked(AlbumKind albumKind) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$mx$AlbumKind[albumKind.ordinal()];
        if (i2 == 1) {
            this.mBlackboard.post("command://MoveURL", "location://albums/all");
            ThreadUtil.postOnUiThreadDelayed(new f(this, 0), 500);
            postAnalyticsLog(AnalyticsEventId.EVENT_VIEW_ALL_MY_ALBUM);
        } else if (i2 == 2) {
            this.mBlackboard.post("command://MoveURL", "location://sharing/albums");
            postAnalyticsLog(AnalyticsEventId.EVENT_VIEW_ALL_SHARED_ALBUM);
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (((IMxAlbumsView) this.mView).getAlbumKind(i2) == AlbumKind.SHARED_ALBUMS) {
            launchSharedAlbum(i2, mediaItem);
        } else {
            super.onListItemClickInternal(i2, mediaItem);
        }
    }

    public void onNoItemClicked() {
        getBlackboard().post("command://MoveURL", "location://albums/manage");
    }

    public void onViewResume() {
        super.onViewResume();
        if (!this.mTryConnectSession) {
            ThreadUtil.postOnBgThreadDelayed(new f(this, 1), 1000);
            this.mTryConnectSession = true;
        }
    }

    public boolean showDeleteAllWarning() {
        return false;
    }

    public boolean supportFamilySharedAlbumCreation() {
        if (((IMxAlbumsView) this.mView).getAdapter() == null || !MdeGroupHelper.supportFamilySharedAlbumCreation(getContext(), ((IMxAlbumsView) this.mView).getAdapter().getFullData()) || !MdeSharingService.getInstance().isServiceSupported()) {
            return false;
        }
        return true;
    }

    public boolean supportSharedAlbumCreation() {
        if ((PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU || !DrawerUtil.supportDrawerLayout(getContext())) && MdeSharingService.getInstance().isServiceSupported()) {
            return true;
        }
        return false;
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new MxAlbumsMenuUpdater(v);
    }
}
