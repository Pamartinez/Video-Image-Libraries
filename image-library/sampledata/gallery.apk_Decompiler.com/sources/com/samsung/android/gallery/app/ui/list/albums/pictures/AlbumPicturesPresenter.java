package com.samsung.android.gallery.app.ui.list.albums.pictures;

import A4.C0368c;
import A4.C0371f;
import A4.C0375j;
import A4.C0381p;
import A8.C0545b;
import B8.d;
import H3.l;
import H7.q;
import J6.c;
import K5.a;
import L5.b;
import M4.h;
import M4.i;
import M4.j;
import M4.k;
import M4.m;
import M4.n;
import M4.o;
import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.app.controller.externals.LaunchAlbumViewCmd;
import com.samsung.android.gallery.app.controller.internals.AlbumPicturesSearchCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuVisibility;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverFiles;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.share.QuickSharePrivacy;
import com.samsung.android.gallery.module.voc.FindHiddenFiles;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPicturesPresenter<V extends IAlbumPicturesView> extends PicturesPresenter<V> {
    private static final MediaItem EMPTY_ALBUM = new MediaItem();
    private final boolean SUPPORT_COVER_CHANGE = (!Features.isEnabled(Features.IS_UPSM));
    /* access modifiers changed from: private */
    public MediaItem mAlbumItem;
    private int mAlbumSyncMenuIndex = -1;
    private int mCloudService = CloudStateCompat.getService();
    /* access modifiers changed from: private */
    public boolean mImmediateToggleSplitState;
    private final AtomicBoolean mInitSubtitle = new AtomicBoolean(false);
    private boolean mIsCloudOnlyAlbum;
    private QuickSharePrivacyTip mQuickSharePrivacyTip;
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();
    /* access modifiers changed from: private */
    public boolean mSupportSearchIcon;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AlbumPicturesMenuUpdater extends ListMenuUpdater {
        public AlbumPicturesMenuUpdater(V v) {
            super(v, AlbumPicturesPresenter.this);
        }

        private boolean isExistSearchableItem() {
            MediaData mediaData = AlbumPicturesPresenter.this.getMediaData();
            if (mediaData == null || mediaData.getCount() <= 0) {
                return false;
            }
            return true;
        }

        private boolean isSearchableAlbum(MediaItem mediaItem) {
            if (mediaItem.isMergedAlbum() || mediaItem.isMyAlbum() || mediaItem.isLegacyFolder() || AlbumPicturesPresenter.this.isFavoriteAlbum() || AlbumPicturesPresenter.this.isRecentAlbum()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateOptionsMenuAction$1(MenuItem menuItem) {
            int i2;
            Context context = AlbumPicturesPresenter.this.getContext();
            if (context != null) {
                if (((IAlbumPicturesView) AlbumPicturesPresenter.this.mView).isDrawerMode()) {
                    menuItem.setVisible(false);
                    return;
                }
                if (AlbumPicturesPresenter.this.mImmediateToggleSplitState) {
                    i2 = R.string.album_pictures_view_mode_standard;
                } else {
                    i2 = R.string.album_pictures_view_mode_split;
                }
                menuItem.setTitle(context.getString(i2));
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: supportAddItems */
        public boolean lambda$updateOptionsMenuAction$2(MediaItem mediaItem) {
            if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || mediaItem.isVirtualAlbum() || AlbumPicturesPresenter.this.isEmptyAlbum()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportAlbumSearch */
        public boolean lambda$updateOptionsMenuAction$8(MediaItem mediaItem) {
            if (!AlbumPicturesPresenter.this.mSupportSearchIcon || !PreferenceFeatures.OneUi5x.MX_ALBUMS || !Features.isEnabled(Features.SUPPORT_SCS_ALBUM_PICTURES_SEARCH) || !isSearchableAlbum(mediaItem) || !isExistSearchableItem()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportAlbumSetting */
        public boolean lambda$updateOptionsMenuAction$5(MediaItem mediaItem) {
            if (Features.isEnabled(Features.IS_UPSM) || !PreferenceFeatures.OneUi5x.MX_ALBUMS || mediaItem.isVirtualAlbum() || AlbumPicturesPresenter.this.isEmptyAlbum()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportChangeCover */
        public boolean lambda$updateOptionsMenuAction$3(MediaItem mediaItem) {
            if (Features.isEnabled(Features.IS_UPSM) || PreferenceFeatures.OneUi5x.MX_ALBUMS || mediaItem.isReadOnlyAlbum() || mediaItem.isMergedAlbum() || AlbumPicturesPresenter.this.isEmptyAlbum()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportQuickSharePrivateTransfer */
        public boolean lambda$updateOptionsMenuAction$7(MediaItem mediaItem) {
            if (mediaItem.getAlbumID() != FileUtils.getBucketId(StorageInfo.getDefault().quickShare) || !QuickSharePrivacy.getInstance().isSupported()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportRename */
        public boolean lambda$updateOptionsMenuAction$4(MediaItem mediaItem) {
            if (Features.isEnabled(Features.IS_UPSM) || PreferenceFeatures.OneUi5x.MX_ALBUMS || AlbumPicturesPresenter.this.isPredefinedAlbums() || mediaItem.isReadOnlyAlbum() || mediaItem.isMergedAlbum() || AlbumPicturesPresenter.this.isEmptyAlbum()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: supportSortBy */
        public boolean lambda$updateOptionsMenuAction$6(MediaItem mediaItem) {
            if (MediaItemUtil.isRecentAlbum(mediaItem)) {
                return false;
            }
            boolean isFavouriteAlbum = MediaItemUtil.isFavouriteAlbum(mediaItem);
            AlbumPicturesPresenter albumPicturesPresenter = AlbumPicturesPresenter.this;
            if (isFavouriteAlbum) {
                if (!albumPicturesPresenter.isEmptyVirtualAlbum()) {
                    return true;
                }
                return false;
            } else if (!albumPicturesPresenter.isEmptyAlbum()) {
                return true;
            } else {
                return false;
            }
        }

        private void updateSelectionMenu(Menu menu) {
            boolean z;
            boolean z3 = false;
            if (!AlbumPicturesPresenter.this.isSelectMenuEnabled() || AlbumPicturesPresenter.this.isSelectionMode()) {
                z = false;
            } else {
                z = true;
            }
            setMenuVisibility(menu, (int) R.id.action_select, z);
            if (supportCreateMenu() && AlbumPicturesPresenter.this.isSelectMenuEnabled() && !AlbumPicturesPresenter.this.isSelectionMode()) {
                z3 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_create, z3);
        }

        public boolean supportAddFavoriteInList() {
            MediaItem currentItem = AlbumPicturesPresenter.this.getCurrentItem();
            if (!super.supportAddFavoriteInList() || ((IAlbumPicturesView) AlbumPicturesPresenter.this.mView).isAlbumChanged() || MediaItemUtil.isFavouriteAlbum(currentItem)) {
                return false;
            }
            return true;
        }

        public boolean supportPasteFromClipboard(MenuDataBinding.SelectionMode selectionMode) {
            ClipData primaryClip;
            MediaItem currentItem = AlbumPicturesPresenter.this.getCurrentItem();
            if (SdkConfig.lessThan(SdkConfig.SEM.U) || selectionMode != MenuDataBinding.SelectionMode.NORMAL || MediaItemUtil.isRecentAlbum(currentItem) || MediaItemUtil.isFavouriteAlbum(currentItem) || currentItem.isAutoAlbum() || (primaryClip = new SafeClipboard(AlbumPicturesPresenter.this.getContext()).getPrimaryClip()) == null || primaryClip.getItemCount() <= 0 || primaryClip.getItemAt(0).getUri() == null) {
                return false;
            }
            return true;
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            MediaItem r0 = AlbumPicturesPresenter.this.getCurrentAlbum();
            if (!PocFeatures.isEnabled(PocFeatures.WifiGalleryClient) || !RemoteGalleryUtil.isRemoteAlbum(r0)) {
                if (PocFeatures.SUPPORT_LOCKED_ALBUM && selectionMode == MenuDataBinding.SelectionMode.NORMAL) {
                    setMenuVisibility(menu, (int) R.id.action_unlock_album, (BooleanSupplier) new n(AlbumPicturesPresenter.this.getCurrentAlbum(), 0));
                }
                Optional.ofNullable(menu.findItem(R.id.action_view_mode)).ifPresent(new a(7, this));
                if (selectionMode == MenuDataBinding.SelectionMode.NORMAL) {
                    MediaItem currentItem = AlbumPicturesPresenter.this.getCurrentItem();
                    setMenuVisibility(menu, (int) R.id.action_add_items_in_album, (BooleanSupplier) new o(this, currentItem, 0));
                    setMenuVisibility(menu, (int) R.id.action_change_cover_image, (BooleanSupplier) new o(this, currentItem, 1));
                    setMenuVisibility(menu, (int) R.id.action_rename_album, (BooleanSupplier) new o(this, currentItem, 2));
                    setMenuVisibility(menu, (int) R.id.action_album_settings, (BooleanSupplier) new o(this, currentItem, 3));
                    setMenuVisibility(menu, (int) R.id.action_sortby, (BooleanSupplier) new o(this, currentItem, 4));
                    setMenuVisibility(menu, (int) R.id.action_quick_share_privacy, (BooleanSupplier) new o(this, currentItem, 5));
                    setMenuVisibility(menu, (int) R.id.action_album_search, (BooleanSupplier) new o(this, currentItem, 6));
                    updateSelectionMenu(menu);
                }
                AlbumPicturesPresenter albumPicturesPresenter = AlbumPicturesPresenter.this;
                if (albumPicturesPresenter.supportRemoveFromAutoAlbum(albumPicturesPresenter.getCurrentAlbum())) {
                    setMenuVisibility(menu, (int) R.id.action_delete, false);
                }
                super.updateOptionsMenuAction(menu, selectionMode);
                return;
            }
            int size = menu.size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = menu.getItem(i2);
                if ((item.getItemId() == R.id.action_download && selectionMode == MenuDataBinding.SelectionMode.SELECT) || item.getItemId() == R.id.action_upload || item.getItemId() == R.id.action_reload) {
                    z = true;
                } else {
                    z = false;
                }
                item.setVisible(z);
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            updateOptionsMenuAction(menu, selectionMode);
            if (i2 == 0) {
                setMenuVisibility(menu, (int) R.id.action_rename_album, PopupMenuVisibility.isActiveAlbumRename(AlbumPicturesPresenter.this.getCurrentAlbum()));
            }
        }
    }

    public AlbumPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        Blackboard blackboard2 = getBlackboard();
        MediaItem mediaItem = EMPTY_ALBUM;
        MediaItem mediaItem2 = (MediaItem) blackboard2.read("data://albums/current", mediaItem);
        this.mAlbumItem = mediaItem2;
        if (mediaItem2 == mediaItem && getBlackboard().read("data://albums_backup/current", mediaItem) != mediaItem) {
            this.mAlbumItem = (MediaItem) getBlackboard().pop("data://albums_backup/current", mediaItem);
            getBlackboard().publish("data://albums/current", this.mAlbumItem);
        }
        this.mImmediateToggleSplitState = v.loadSplitModeFromPreference();
    }

    /* access modifiers changed from: private */
    /* renamed from: findMatchedAlbumAndUpdate */
    public void lambda$turnOnMergedAlbum$17(MediaItem mediaItem, MediaItem mediaItem2, ArrayList<Integer> arrayList) {
        for (MediaItem next : mediaItem.getChildList()) {
            if (next != null && next.getAlbumID() == mediaItem2.getAlbumID()) {
                Stream<R> filter = mediaItem.getChildList().stream().map(new Gb.a(7)).filter(new j(1));
                Objects.requireNonNull(arrayList);
                filter.forEach(new d(arrayList, 9));
                ThreadUtil.postponeOnUiThread(new c(this, mediaItem, arrayList, 10));
                return;
            }
        }
    }

    private int[] getAlbumCount(IntSupplier intSupplier, Supplier<int[]> supplier) {
        int argValue;
        if (!this.mInitSubtitle.getAndSet(true) && (argValue = ArgumentsUtil.getArgValue(getLocationKey(), "count", -1)) >= 0) {
            try {
                int asInt = intSupplier.getAsInt();
                return new int[]{argValue - asInt, asInt};
            } catch (Exception e) {
                A.a.r(e, new StringBuilder("getAlbumCount(v) failed e="), this.TAG);
            }
        }
        try {
            return supplier.get();
        } catch (Exception e7) {
            A.a.r(e7, new StringBuilder("getAlbumCount(i,v) failed e="), this.TAG);
            return null;
        }
    }

    private int getAlbumSyncMenuIndex() {
        boolean z;
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            z = CloudStateCompat.isEnabledInPref();
        } else {
            z = CloudStateCompat.isEnabled();
        }
        if (!z || !CloudStateCompat.isSyncOnInPref()) {
            return 0;
        }
        if (CloudData.of(getCurrentAlbum()).albumSyncStatus == 0) {
            return 2;
        }
        return 1;
    }

    private String getAlbumTitle(MediaItem mediaItem) {
        return AlbumTitleHelper.getAlbumTitle(mediaItem.getAlbumID(), mediaItem.getDisplayName());
    }

    /* access modifiers changed from: private */
    public MediaItem getCurrentAlbum() {
        return (MediaItem) getBlackboard().read("data://albums/current", EMPTY_ALBUM);
    }

    private MediaItem getCurrentFolder() {
        return (MediaItem) getBlackboard().read("data://current_folder", null);
    }

    private void handleBixbyAction() {
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (this.mBixbyProxy != null && launchIntent != null) {
            if (launchIntent.getBixbyShareViaTVIsSet()) {
                this.mBixbyProxy.handleCommand(Uri.parse("SHARE_VIA_TV"));
            } else if (launchIntent.getBixbyAlbumSlideshowIsSet()) {
                this.mBixbyProxy.handleCommand(Uri.parse("SHOW_ALBUM_SLIDE_SHOW_VIEW"));
            }
        }
    }

    private boolean isClusterAlbumItem() {
        return UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(getLocationKey(), "cluster_album_item", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
    }

    /* access modifiers changed from: private */
    public boolean isEmptyAlbum() {
        MediaData mediaData = getMediaData();
        if (mediaData == null || this.mAlbumItem == null || mediaData.getCount() != 0 || !this.mAlbumItem.isEmptyAlbum()) {
            return false;
        }
        return true;
    }

    private boolean isEmptyAlbumHandlingRequired() {
        MediaItem mediaItem = this.mAlbumItem;
        if (mediaItem == null || mediaItem.getCount() > 1 || !this.mAlbumItem.isEmptyAlbum()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isEmptyVirtualAlbum() {
        MediaData mediaData = getMediaData();
        if (mediaData == null || mediaData.getCount() >= 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isPredefinedAlbums() {
        MediaItem currentAlbum = getCurrentAlbum();
        if (!TextUtils.isEmpty(currentAlbum.getPath()) && !BucketUtils.contains(currentAlbum.getAlbumID()) && !BucketUtils.isRoot(currentAlbum.getAlbumID())) {
            return false;
        }
        return true;
    }

    private boolean isSameLocationKey(String str) {
        String locationKey = getLocationKey();
        if (locationKey == null || !locationKey.equals(str)) {
            return false;
        }
        return true;
    }

    private boolean isToolbarTitleVisible() {
        boolean z;
        if (!((IAlbumPicturesView) this.mView).isSplitBlocked() || !ArgumentsUtil.getArgValue(getLocationKey(), "album_split_blocked", false)) {
            z = false;
        } else {
            z = true;
        }
        boolean booleanValue = ((Boolean) Optional.ofNullable(((IAlbumPicturesView) this.mView).getAppbarLayout()).map(new b(4)).orElse(Boolean.FALSE)).booleanValue();
        if ((!((IAlbumPicturesView) this.mView).isLandscape() || ((IAlbumPicturesView) this.mView).isTabletDpi() || booleanValue) && !z) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$14(Object obj, Bundle bundle) {
        ((IAlbumPicturesView) this.mView).resetScreenShotFilter();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$findMatchedAlbumAndUpdate$18(MediaItem mediaItem, ArrayList arrayList) {
        updateMergedAlbums(mediaItem, new UriBuilder("location://albums/fileList").appendArg("mergedAlbumId", mediaItem.getAlbumID()).appendArg("ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList)).build());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$23() {
        MediaItem mediaItem = this.mAlbumItem;
        if (mediaItem != null) {
            CloudData.of(mediaItem).albumSyncStatus = SamsungCloudCompat.getAlbumSyncStatus(getContext(), this.mAlbumItem.getAlbumID(), this.mAlbumItem.getTitle());
        }
        updateAlbumSyncMenu(this.mIsCloudOnlyAlbum);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isToolbarTitleVisible$24(GalleryAppBarLayout galleryAppBarLayout) {
        boolean z;
        if (galleryAppBarLayout.seslGetHeightProPortion() > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyDataChanged$1(int i2) {
        if (!isDestroyed()) {
            PreferenceAnalytics preferenceAnalytics = PreferenceAnalytics.BigAlbumCount;
            if (i2 > preferenceAnalytics.getInteger()) {
                preferenceAnalytics.setInteger(i2);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAlbumItemChanged$15() {
        ((IAlbumPicturesView) this.mView).updateHeaderView(true, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onTipCardCloseClicked$10(ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(AlbumHelper.getInstance().setAlbumInfoShown(this.mAlbumItem.getAlbumID()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTipCardCloseClicked$11() {
        this.mAlbumItem.setAlbumShowInfo(false);
        ((IAlbumPicturesView) this.mView).updateHeaderView(true, false);
        ShortcutHelper.getInstance().updateHomeScreenShortcut(getContext(), this.mAlbumItem);
        postAnalyticsLog(AnalyticsEventId.EVENT_DISMISS_AUTO_MERGED_TIP_CARD);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTipCardCloseClicked$12(Future future) {
        ThreadUtil.postOnUiThread(new i(this, 4));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$refreshAlbumSetting$2(MediaItem mediaItem, MediaItem mediaItem2) {
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        Integer valueOf = Integer.valueOf(mediaItem2.getAlbumID());
        if (mediaItem == null) {
            mediaItem = mediaItem2;
        }
        applicationInstance.post("global://album/setting/dataChanged", new Object[]{valueOf, mediaItem});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$turnOffMergedAlbum$19(MediaItem mediaItem) {
        return !mediaItem.getChildAlbums().isEmpty();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$turnOffMergedAlbum$21(MediaItem mediaItem) {
        updateMergedAlbums(mediaItem, new UriBuilder("location://albums/fileList").appendArg("id", mediaItem.getAlbumID()).build());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$turnOffMergedAlbum$22(MediaItem mediaItem) {
        ThreadUtil.postponeOnUiThread(new H.a(28, this, mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCoverOnAppbar$16() {
        ((IAlbumPicturesView) this.mView).updateCustomCover(0, getCurrentItem());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0031 A[Catch:{ Exception -> 0x001a }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060 A[Catch:{ Exception -> 0x001a }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$updateSubTitle$3(androidx.appcompat.widget.Toolbar r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.CharSequence r0 = r3.getSubtitle()     // Catch:{ Exception -> 0x001a }
            r1 = 0
            if (r4 == 0) goto L_0x001c
            if (r0 == 0) goto L_0x001c
            boolean r0 = r4.contentEquals(r0)     // Catch:{ Exception -> 0x001a }
            if (r0 != 0) goto L_0x0010
            goto L_0x001c
        L_0x0010:
            boolean r0 = r2.isToolbarTitleVisible()     // Catch:{ Exception -> 0x001a }
            if (r0 != 0) goto L_0x0026
            r3.setSubtitle((java.lang.CharSequence) r1)     // Catch:{ Exception -> 0x001a }
            goto L_0x0026
        L_0x001a:
            r3 = move-exception
            goto L_0x0069
        L_0x001c:
            boolean r0 = r2.isToolbarTitleVisible()     // Catch:{ Exception -> 0x001a }
            if (r0 == 0) goto L_0x0023
            r1 = r4
        L_0x0023:
            r3.setSubtitle((java.lang.CharSequence) r1)     // Catch:{ Exception -> 0x001a }
        L_0x0026:
            V r0 = r2.mView     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView r0 = (com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView) r0     // Catch:{ Exception -> 0x001a }
            r1 = 2
            boolean r0 = r0.updateCustomCover(r1, r4)     // Catch:{ Exception -> 0x001a }
            if (r0 != 0) goto L_0x0052
            V r0 = r2.mView     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView r0 = (com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView) r0     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout r0 = r0.getAppbarLayout()     // Catch:{ Exception -> 0x001a }
            java.lang.CharSequence r0 = r0.getSubTitle()     // Catch:{ Exception -> 0x001a }
            if (r4 == 0) goto L_0x0047
            if (r0 == 0) goto L_0x0047
            boolean r0 = r4.contentEquals(r0)     // Catch:{ Exception -> 0x001a }
            if (r0 != 0) goto L_0x0052
        L_0x0047:
            V r0 = r2.mView     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView r0 = (com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumPicturesView) r0     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout r0 = r0.getAppbarLayout()     // Catch:{ Exception -> 0x001a }
            r0.setSubtitle(r4)     // Catch:{ Exception -> 0x001a }
        L_0x0052:
            com.samsung.android.gallery.module.share.QuickSharePrivacy r4 = com.samsung.android.gallery.module.share.QuickSharePrivacy.getInstance()     // Catch:{ Exception -> 0x001a }
            com.samsung.android.gallery.module.data.MediaItem r0 = r2.getCurrentAlbum()     // Catch:{ Exception -> 0x001a }
            boolean r4 = r4.needTip(r0)     // Catch:{ Exception -> 0x001a }
            if (r4 == 0) goto L_0x0068
            com.samsung.android.gallery.app.ui.list.albums.pictures.QuickSharePrivacyTip r4 = r2.getQuickSharePrivacyTip()     // Catch:{ Exception -> 0x001a }
            r0 = 0
            r4.show(r3, r0)     // Catch:{ Exception -> 0x001a }
        L_0x0068:
            return
        L_0x0069:
            com.samsung.android.gallery.support.utils.StringCompat r2 = r2.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "updateSubtitle failed e="
            r4.<init>(r0)
            A.a.r(r3, r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter.lambda$updateSubTitle$3(androidx.appcompat.widget.Toolbar, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$0(Toolbar toolbar, MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar, mediaItem);
    }

    /* access modifiers changed from: private */
    public void moveToNextAlbum() {
        if (!((IAlbumPicturesView) this.mView).isAlbumPaneAvailable()) {
            Log.d(this.TAG, "moveToNextAlbum: AlbumPane is inactive");
            MediaItem mediaItem = (MediaItem) this.mBlackboard.pop("data://albums/moveTo/target", null);
            if (mediaItem != null) {
                refreshAlbumSetting(mediaItem);
                String build = new UriBuilder("location://albums/fileList").appendArg("id", mediaItem.getAlbumID()).appendArg("type", mediaItem.getAlbumType().toInt()).appendArg("count", mediaItem.getCount()).build();
                this.mBlackboard.publish("location://variable/currentv1", build);
                this.mBlackboard.publish("data://albums/current", mediaItem);
                this.mBlackboard.postEvent(EventMessage.obtain(2001, build));
                this.mBlackboard.postEvent(EventMessage.obtain(2004, mediaItem.getAlbumID(), new Object[]{Boolean.TRUE, mediaItem.getPath(), mediaItem}));
                Log.d(this.TAG, "moveToNextAlbum: refresh album");
                return;
            }
            this.mBlackboard.publish("command://RemoveChildFragment", Boolean.FALSE);
        } else if (this.mBlackboard.isEmpty("data://albums/moveTo/target")) {
            this.mBlackboard.post("command://albums/moveTo/next", (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void onAlbumItemChanged(Object obj, Bundle bundle) {
        this.mAlbumItem = (MediaItem) obj;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onAlbumItemChanged " + MediaItemUtil.getDebugLog(this.mAlbumItem));
        updateCoverOnAppbar(0);
        ThreadUtil.postOnUiThread(new i(this, 1));
    }

    /* access modifiers changed from: private */
    public void onCloudServiceChanged(Object obj, Bundle bundle) {
        updateAlbumSyncMenu(this.mIsCloudOnlyAlbum);
    }

    /* access modifiers changed from: private */
    public void onCloudSyncOnOffChanged(Object obj, Bundle bundle) {
        updateAlbumSyncMenu(this.mIsCloudOnlyAlbum);
    }

    /* access modifiers changed from: private */
    public void onCoverChanged(Object obj, Bundle bundle) {
        String str;
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onCoverChanged(");
        if (((Object[]) obj)[1] == null) {
            str = "R";
        } else {
            str = "C";
        }
        sb2.append(str);
        sb2.append(")");
        Log.d(stringCompat, sb2.toString());
        updateCoverOnAppbar(200);
        refreshAlbumSetting((MediaItem) null);
    }

    /* access modifiers changed from: private */
    public void onMergeAlbumsChanged(Object obj, Bundle bundle) {
        MediaData open;
        try {
            open = MediaDataFactory.getInstance(getBlackboard()).open("location://albums/all", true);
            registerAlbumDataChanged(open);
            if (open != null) {
                open.close();
                return;
            }
            return;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "onMergeAlbumsChanged failed = ", (Throwable) e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void prepareAlbumSyncMenu(MenuDataBinding menuDataBinding) {
        boolean z;
        MenuDataBinding.MenuData menuData = menuDataBinding.getMenuData(R.id.action_cloud_sync_album);
        if (menuData != null) {
            boolean supportAlbumSyncMenu = supportAlbumSyncMenu(this.mIsCloudOnlyAlbum);
            if (supportAlbumSyncMenu && this.mAlbumSyncMenuIndex >= 0) {
                menuData.updateTitleArray();
                menuData.setIndex(this.mAlbumSyncMenuIndex);
            }
            if (this.mAlbumSyncMenuIndex < 0 || !supportAlbumSyncMenu) {
                z = false;
            } else {
                z = true;
            }
            menuData.setVisible(z);
        }
    }

    private void readAlbumItemByPosition() {
        final MediaData open = MediaDataFactory.getInstance(getBlackboard()).open("location://albums", true);
        try {
            if (open.isDataAvailable()) {
                int argValue = ArgumentsUtil.getArgValue(getLocationKey(), Message.KEY_POSITION, -1);
                if (argValue > -1) {
                    this.mAlbumItem = open.read(argValue);
                    getBlackboard().publish("data://albums/current", this.mAlbumItem);
                }
            } else {
                open.register(new MediaData.SimpleDataChangeListener(true) {
                    public void onDataChanged() {
                        int argValue = ArgumentsUtil.getArgValue(AlbumPicturesPresenter.this.getLocationKey(), Message.KEY_POSITION, -1);
                        if (argValue > -1) {
                            AlbumPicturesPresenter.this.mAlbumItem = open.read(argValue);
                            AlbumPicturesPresenter.this.getBlackboard().publish("data://albums/current", AlbumPicturesPresenter.this.mAlbumItem);
                        }
                    }
                });
            }
            open.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void refreshAlbumSetting(MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            Optional.ofNullable((MediaItem) this.mBlackboard.read("data://albums/current")).ifPresent(new G6.a(mediaItem, 1));
        }
    }

    private void refreshData(String str) {
        if (str != null && !str.equals(getLocationKey())) {
            setLocationKeyOnly(str);
            ((IAlbumPicturesView) this.mView).onChangeAlbum(str);
        }
    }

    private void registerAlbumDataChanged(final MediaData mediaData) {
        if (mediaData != null) {
            mediaData.register(new MediaData.SimpleDataChangeListener() {
                public void onDataChanged() {
                    mediaData.unregister(this);
                    if (PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbumsMergeNames)) {
                        AlbumPicturesPresenter.this.turnOnMergedAlbum(mediaData.getAllData());
                    } else {
                        AlbumPicturesPresenter.this.turnOffMergedAlbum();
                    }
                }
            });
        }
    }

    private boolean supportAlbumSyncMenu(boolean z) {
        if (!supportAlbumSync(this.mAlbumItem) || z) {
            return false;
        }
        return true;
    }

    private boolean supportRemoveFavoriteInList(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || ((IAlbumPicturesView) this.mView).isAlbumChanged() || !MediaItemUtil.isFavouriteAlbum(mediaItem) || mediaItem.getCount() <= 0 || isOnAdvancedMouseFocused()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean supportRemoveFromAutoAlbum(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM) || ((IAlbumPicturesView) this.mView).isAlbumChanged() || !mediaItem.isAutoAlbum() || mediaItem.getCount() <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void turnOffMergedAlbum() {
        Optional.of(getCurrentAlbum()).filter(new C0375j(7)).filter(new j(0)).map(new b(3)).ifPresent(new a(6, this));
    }

    /* access modifiers changed from: private */
    public void turnOnMergedAlbum(ArrayList<MediaItem> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        arrayList.stream().filter(new C0375j(7)).forEach(new C0371f((Object) this, getCurrentAlbum(), (Object) arrayList2, 9));
    }

    private void updateCoverOnAppbar(long j2) {
        if (((IAlbumPicturesView) this.mView).hasCustomCover()) {
            ThreadUtil.postOnBgThreadDelayed(new i(this, 0), j2);
        }
    }

    private void updateMergedAlbums(MediaItem mediaItem, String str) {
        this.mBlackboard.publish("location://variable/currentv1", str);
        this.mBlackboard.publish("data://albums/current", mediaItem);
        this.mBlackboard.postEvent(EventMessage.obtain(2001, str));
        ((IAlbumPicturesView) this.mView).changeAlbum(str);
        ((IAlbumPicturesView) this.mView).updateHeaderView(false, true);
    }

    private Object updateSubTitle(Toolbar toolbar, MediaItem mediaItem) {
        String str;
        int[] albumCount = getAlbumCount(mediaItem);
        if (albumCount != null) {
            str = makeSubtitle(albumCount[0], albumCount[1]);
        } else {
            str = null;
        }
        ThreadUtil.postOnUiThread(new c(this, toolbar, str, 9));
        return null;
    }

    public void addItemToAlbum() {
        new FileOpCmd().execute(this, FileOpCmdHelper.CmdType.TYPE_ADD_TO_ITEMS, getCurrentAlbum());
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR)) {
            arrayList.add(new SubscriberInfo("global://event/cloud/service/changed", new h(this, 4)));
            arrayList.add(new SubscriberInfo("cloud/sync/on/off/changed", new h(this, 5)));
        }
        arrayList.add(new SubscriberInfo("global://setting/albums/mergeAlbums", new h(this, 6)));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("data://albums/current", new h(this, 0)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo("command://AlbumPicturesViewChanged", new h(this, 1)).setWorkingOnUI());
        if (this.SUPPORT_COVER_CHANGE) {
            arrayList.add(new SubscriberInfo("data://useralbum_cover_change", new h(this, 2)).setWorkingOnUI());
        }
        arrayList.add(new SubscriberInfo("command:///ScreenshotFilterOrderChanged", new h(this, 3)));
    }

    public void finishFragment() {
        if (LocationKey.isShortcutAlbum(getLocationKey())) {
            new LaunchAlbumViewCmd().execute(this, new Object[0]);
        } else {
            ((IAlbumPicturesView) this.mView).finish();
        }
    }

    public MediaItem getCurrentItem() {
        return this.mAlbumItem;
    }

    public Object getEventContextData(String str) {
        if ("searchToolbarFocus".equals(str)) {
            return Boolean.valueOf(this.mSearchToolbarDelegate.hasFocus());
        }
        return super.getEventContextData(str);
    }

    public String getLabelForAccessibility(Context context) {
        return getAlbumTitle(getCurrentAlbum());
    }

    public QuickSharePrivacyTip getQuickSharePrivacyTip() {
        if (this.mQuickSharePrivacyTip == null) {
            this.mQuickSharePrivacyTip = new QuickSharePrivacyTip();
        }
        return this.mQuickSharePrivacyTip;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1001) {
            if (i2 == 2001) {
                this.mInitSubtitle.set(false);
                if (!isSameLocationKey((String) eventMessage.obj)) {
                    ((IAlbumPicturesView) this.mView).setLocationKey((String) eventMessage.obj);
                    refreshData((String) eventMessage.obj);
                }
            } else if (i2 != 2006) {
                if (i2 != 8520) {
                    if (i2 == 2013) {
                        forceReloadData();
                        return true;
                    } else if (i2 == 2014) {
                        ((IAlbumPicturesView) this.mView).resetScreenShotFilter();
                        return true;
                    }
                } else if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                    new FloatingRecommendationLauncher((IBaseListView) this.mView).captureScreenAndExecuteFloatingRecommendation(eventMessage.obj);
                    return true;
                }
                if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
                    return true;
                }
                return false;
            } else if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_MENU_ON_TOOL_BAR)) {
                ThreadUtil.postOnBgThread(new i(this, 5));
                return true;
            }
            return true;
        }
        ((IAlbumPicturesView) this.mView).toggleSplitMode();
        return true;
    }

    public boolean isConsumeBackPress() {
        Boolean bool = (Boolean) this.mSearchToolbarDelegate.getData(SearchToolbarDataKey.IS_BACK_PRESS_CONSUMED);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public boolean isFavoriteAlbum() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !BucketUtils.isFavourite(this.mAlbumItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public boolean isRecentAlbum() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !BucketUtils.isRecent(this.mAlbumItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public boolean isVirtualAlbum() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !this.mAlbumItem.isVirtualAlbum()) {
            return false;
        }
        return true;
    }

    public void moveToSearch() {
        new AlbumPicturesSearchCmd().execute(this, getCurrentAlbum());
    }

    public void notifyDataChanged(MediaData mediaData) {
        long j2;
        MediaItem mediaItem;
        MediaItem mediaItem2;
        updateToolbar(((IAlbumPicturesView) this.mView).getToolbar());
        int count = mediaData.getCount();
        if (LocationKey.isMxVirtualAlbum(getLocationKey()) && (mediaItem = this.mAlbumItem) != null && mediaItem.getFileId() < 0) {
            if (count > 0) {
                mediaItem2 = mediaData.read(0);
            } else {
                mediaItem2 = null;
            }
            MediaItem mediaItem3 = this.mAlbumItem;
            if (mediaItem2 != null) {
                this.mAlbumItem = mediaItem2;
                mediaItem2.setAlbumID(mediaItem3.getAlbumID());
                this.mAlbumItem.setAlbumType(mediaItem3.getAlbumType());
                this.mAlbumItem.setTitle(mediaItem3.getTitle());
                this.mAlbumItem.setDisplayName(mediaItem3.getDisplayName());
                this.mAlbumItem.setVirtualAlbum(mediaItem3.getVirtualAlbum());
            } else {
                this.mAlbumItem = MediaItemBuilder.createVirtualAlbum(mediaItem3.getAlbumID(), AlbumTitleHelper.getAlbumTitle(mediaItem3.getAlbumID()));
            }
            if (!((IAlbumPicturesView) this.mView).isViewHidden()) {
                getBlackboard().publish("data://albums/current", this.mAlbumItem);
            }
            this.mAlbumItem.setCount(count);
        }
        if (count != 0 || !mediaData.isFullyLoaded() || !isEmptyAlbumHandlingRequired() || isVirtualAlbum()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "notifyDataChanged " + count);
        } else {
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "notifyDataChanged empty album " + MediaItemUtil.getDebugLog(this.mAlbumItem));
            if ((!((IAlbumPicturesView) this.mView).isSplitMode() || getCurrentFolder() != null) && !((IAlbumPicturesView) this.mView).isDrawerMode()) {
                if (((IAlbumPicturesView) this.mView).isDisplayWithDrawer()) {
                    this.mBlackboard.publish("command://RemoveChildFragment", Boolean.FALSE);
                } else {
                    ThreadUtil.postOnUiThread(new i(this, 3));
                }
            } else if (((IAlbumPicturesView) this.mView).isEnterTransition() || (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER && this.mAlbumItem.isCustomCover())) {
                i iVar = new i(this, 2);
                if (((IAlbumPicturesView) this.mView).isEnterTransition()) {
                    j2 = 100;
                } else {
                    j2 = 50;
                }
                ThreadUtil.postOnUiThreadDelayed(iVar, j2);
            } else {
                moveToNextAlbum();
            }
        }
        if (count > 10000) {
            ThreadUtil.postOnBgThreadDelayed(new C0368c(this, count, 10), 1000);
        }
    }

    public void onDataPrepared() {
        boolean z;
        super.onDataPrepared();
        if (isPredefinedAlbums() || Features.isEnabled(Features.IS_UPSM)) {
            z = false;
        } else {
            z = true;
        }
        updateMenuVisibility(R.id.action_rename_album, z, true);
        handleBixbyAction();
    }

    public void onDestroy() {
        super.onDestroy();
        PocFeatures pocFeatures = PocFeatures.WifiGalleryClient;
        if (PocFeatures.isEnabled(pocFeatures)) {
            pocFeatures.setEnabled(false);
        }
        this.mSearchToolbarDelegate.onDestroy();
    }

    public void onDrawerSizeChanged(int i2) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(19, Integer.valueOf(i2)));
    }

    public void onLocationKeyAssigned() {
        if (PocFeatures.TBD.RECOVER_LAST_STACK && this.mAlbumItem == EMPTY_ALBUM) {
            readAlbumItemByPosition();
        }
        this.mSupportSearchIcon = ArgumentsUtil.getArgValue(getLocationKey(), "supportSearchIcon", true);
    }

    public void onNavigationPressed(View view) {
        if (LocationKey.isShortcutAlbum(getLocationKey())) {
            new LaunchAlbumViewCmd().execute(this, new Object[0]);
            Log.d(this.TAG, "onNavigationPressed : launch album view.");
            return;
        }
        super.onNavigationPressed(view);
    }

    public void onTipCardCloseClicked() {
        ThreadPool.getInstance().submit(new C0381p(3, this), new K4.a(9, this));
    }

    public void onTopOverScroll(int i2) {
        FindHiddenFiles.execute();
        RecoverFiles.execute();
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildForAlbumPictures(this);
        }
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            MediaItem currentItem = getCurrentItem();
            menuDataBinding.setVisible((int) R.id.action_remove_from_auto_album, supportRemoveFromAutoAlbum(currentItem));
            menuDataBinding.setVisible((int) R.id.action_remove_favorite_in_list, supportRemoveFavoriteInList(currentItem));
            prepareAlbumSyncMenu(menuDataBinding);
        }
        super.prepareOptionsMenu(menu);
    }

    public void setImmediateToggleSplitState(boolean z) {
        this.mImmediateToggleSplitState = z;
    }

    public boolean skipEmptyView() {
        MediaData mediaData = getMediaData();
        if (mediaData == null || mediaData.getCount() != 0 || !mediaData.isFullyLoaded() || !isEmptyAlbumHandlingRequired() || isVirtualAlbum()) {
            return false;
        }
        return true;
    }

    public boolean supportAlbumSync(MediaItem mediaItem) {
        boolean z;
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            z = CloudStateCompat.isMigrationSupportedInPref();
        } else if (CloudStateCompat.isEnabled() || CloudStateCompat.isOneDriveLinkRequired()) {
            z = true;
        } else {
            z = false;
        }
        if (!z || mediaItem == null || isVirtualAlbum() || mediaItem.isAutoAlbum() || mediaItem.isMergedAlbum() || FileUtils.isInRemovableStorage(mediaItem.getDataPath())) {
            return false;
        }
        return true;
    }

    public boolean supportSubTitle() {
        return true;
    }

    public void updateAlbumSyncMenu(boolean z) {
        int i2;
        int service = CloudStateCompat.getService();
        if (supportAlbumSyncMenu(z)) {
            i2 = getAlbumSyncMenuIndex();
        } else {
            i2 = 0;
        }
        if (this.mCloudService != service || i2 != this.mAlbumSyncMenuIndex || z != this.mIsCloudOnlyAlbum) {
            this.mCloudService = service;
            this.mAlbumSyncMenuIndex = i2;
            this.mIsCloudOnlyAlbum = z;
            IAlbumPicturesView iAlbumPicturesView = (IAlbumPicturesView) this.mView;
            Objects.requireNonNull(iAlbumPicturesView);
            ThreadUtil.runOnUiThread(new l(28, iAlbumPicturesView));
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        MediaItem currentAlbum = getCurrentAlbum();
        if (currentAlbum != EMPTY_ALBUM) {
            String albumTitle = getAlbumTitle(currentAlbum);
            GalleryAppBarLayout appbarLayout = ((IAlbumPicturesView) this.mView).getAppbarLayout();
            if (albumTitle != null) {
                if (!((IAlbumPicturesView) this.mView).updateCustomCover(1, albumTitle) && (appbarLayout.getTitle() == null || !albumTitle.contentEquals(appbarLayout.getTitle()))) {
                    appbarLayout.setTitle((CharSequence) albumTitle);
                }
                String str = null;
                if (toolbar.getTitle() == null || !albumTitle.contentEquals(toolbar.getTitle())) {
                    if (!isToolbarTitleVisible()) {
                        albumTitle = null;
                    }
                    toolbar.setTitle((CharSequence) albumTitle);
                    if (supportSubTitle()) {
                        str = " ";
                    }
                    toolbar.setSubtitle((CharSequence) str);
                } else if (!isToolbarTitleVisible()) {
                    toolbar.setTitle((CharSequence) null);
                }
            }
            if (!((IAlbumPicturesView) this.mView).isDrawerMode() || isClusterAlbumItem()) {
                setNavigationUpButton(toolbar);
            }
            if (supportSubTitle()) {
                ThreadPool.getInstance().submit(new E5.a(this, toolbar, currentAlbum, 2));
            }
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new AlbumPicturesMenuUpdater(v);
    }

    private int[] getAlbumCount(MediaItem mediaItem) {
        if (mediaItem == null || isEmptyAlbum()) {
            this.mInitSubtitle.set(true);
            return null;
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (MediaItemUtil.isFavouriteAlbum(mediaItem)) {
                QueryParams queryParams = new QueryParams();
                if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    queryParams.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
                }
                return new MpHelper(queryParams).getAlbumVirtualFavoriteCount();
            } else if (mediaItem.isMergedAlbum()) {
                MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
                int[] array = albumsInFolder != null ? Stream.of(albumsInFolder).mapToInt(new C0545b(2)).toArray() : null;
                if (array == null && ArgumentsUtil.getArgValue(getLocationKey(), "shortcut_album", false)) {
                    String str = (String) mediaItem.getTag("merged-album-ids", "");
                    int[] intArray = StringCompat.toIntArray(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    Log.d(this.TAG, "getAlbumCount#shortcut-album", Integer.valueOf(mediaItem.getAlbumID()), C0212a.m("[", str, "]"));
                    array = intArray;
                }
                if (array == null || array.length <= 0) {
                    return null;
                }
                QueryParams addAlbumIds = new QueryParams(GroupType.BURST, GroupType.SINGLE_TAKEN).addAlbumIds(array);
                return getAlbumCount(new k(0, addAlbumIds), new M4.l(0, addAlbumIds));
            } else if (mediaItem.isAutoAlbum()) {
                return getAlbumCount(new q(3, mediaItem), new m(mediaItem, 0));
            }
        }
        QueryParams addAlbumId = new QueryParams(GroupType.BURST, GroupType.SINGLE_TAKEN).addAlbumId(mediaItem.getAlbumID());
        if (PocFeatures.SUPPORT_LOCKED_ALBUM && MediaItemUtil.isRecentAlbum(mediaItem)) {
            addAlbumId.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
        }
        return getAlbumCount(new k(1, addAlbumId), new M4.l(1, addAlbumId));
    }
}
