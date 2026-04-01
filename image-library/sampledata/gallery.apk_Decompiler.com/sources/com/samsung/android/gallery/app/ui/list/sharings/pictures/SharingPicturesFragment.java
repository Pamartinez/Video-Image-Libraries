package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import A4.C0385u;
import A4.C0387w;
import C3.i;
import Fa.C0571z;
import H7.A;
import J6.c;
import M5.a;
import O3.l;
import O5.b;
import O5.d;
import O5.e;
import O5.f;
import android.accounts.Account;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.controller.externals.OpenSamsungFamilyGroupCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.ISharingPicturesView;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesPresenter;
import com.samsung.android.gallery.module.account.FamilyGroupHelper;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeMediaItemHelper;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.appbar.BlurCustomCover;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.effects.ListProtectionGradient;
import com.samsung.android.gallery.widget.effects.ListProtectionGradientImpl;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPicturesFragment<V extends ISharingPicturesView, P extends SharingPicturesPresenter> extends PicturesFragment<V, P> implements ISharingPicturesView {
    private static final boolean SUPPORT_BLUR_COVER_ON_APPBAR;
    private static final boolean SUPPORT_CUSTOM_COVER_ON_APPBAR;
    private static final boolean SUPPORT_CUSTOM_COVER_ON_HEADER;
    private final SamsungAccountManager.OnAccountUpdatedListener mAccountUpdatedListener = new e(this);
    private LinearLayout mAddFamilyButton;
    private TextView mDescriptionView;
    private Integer mFamilyAlbumId;
    private boolean mHasAccount;
    private ISharingHeaderView mHeaderViewDelegate;
    private TextView mLabelView;
    private final SharedAlbumLinkTip mLinkTip = new SharedAlbumLinkTip(this);
    private View mOnePersonAlbumNoticeLayout;
    /* access modifiers changed from: private */
    public boolean mPendingDataChanged;
    private SharingCoverView mSharingCoverView;
    private final MediaData.OnDataChangeListener mSharingsDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            if (SharingPicturesFragment.this.isViewReady()) {
                SharingPicturesFragment.this.onSharingsDataChangedOnUi();
            } else {
                SharingPicturesFragment.this.mPendingDataChanged = true;
            }
        }

        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new f(1, this));
        }
    };
    private MediaData mSharingsMediaData;
    long startTime;

    static {
        boolean z;
        boolean z3 = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
        SUPPORT_BLUR_COVER_ON_APPBAR = z3;
        boolean z7 = true;
        if (z3 || PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT_CUSTOM_COVER_ON_APPBAR = z;
        if (z3 || !PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
            z7 = false;
        }
        SUPPORT_CUSTOM_COVER_ON_HEADER = z7;
    }

    public SharingPicturesFragment() {
        setLocationKey("location://sharing/albums/fileList");
        createHeaderView();
        MdeAlbumHelper.updateLastVisitedTime();
    }

    private void checkTipCard() {
        int i2;
        Pair<Integer, Integer> autoAlbumCount;
        Log.d(this.TAG, "checkTipCard");
        if (supportTipCard() && (i2 = PreferenceCache.SharedFamilyAlbumTipCardStep.getInt()) <= 3 && (autoAlbumCount = AutoAlbumHelper.getInstance().getAutoAlbumCount(getFamilyAlbumId())) != null) {
            if (((Integer) autoAlbumCount.second).intValue() + ((Integer) autoAlbumCount.first).intValue() >= i2 * 50) {
                ThreadUtil.postOnUiThread(new a(26, this, autoAlbumCount));
            }
        }
    }

    private boolean checkValidSpace() {
        if (this.mSharingsMediaData.readByKey(getSpaceId()) != null) {
            return true;
        }
        return false;
    }

    private SharingCoverView createCoverView(MediaItem mediaItem) {
        if (getAppbarLayout() == null || mediaItem == null) {
            return null;
        }
        View loadOrCreateView = loadOrCreateView(R.layout.recycler_item_sharing_pictures_header_layout);
        getAppbarLayout().setCoverView(loadOrCreateView);
        return new SharingCoverView(this, loadOrCreateView, mediaItem);
    }

    private void createHeaderView() {
        ISharingHeaderView iSharingHeaderView;
        if (SUPPORT_CUSTOM_COVER_ON_HEADER) {
            iSharingHeaderView = new SharingHeaderViewDelegateV2(this);
        } else {
            iSharingHeaderView = new SharingHeaderViewDelegate(this);
        }
        this.mHeaderViewDelegate = iSharingHeaderView;
    }

    private boolean equalsMediaItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId() || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(MediaItemMde.getSpaceCoverId(mediaItem), MediaItemMde.getSpaceCoverId(mediaItem2)) || !TextUtils.equals(MediaItemMde.getSpaceCoverRectRatio(mediaItem), MediaItemMde.getSpaceCoverRectRatio(mediaItem2))) {
            return false;
        }
        if (PreferenceFeatures.OneUi41.SHARING_LEADER_AUTHORITY_DELEGATION && !MdeMediaItemHelper.isSameOwner(mediaItem, mediaItem2)) {
            return false;
        }
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK || MdeMediaItemHelper.isSameSpaceWebLink(mediaItem, mediaItem2)) {
            return true;
        }
        return false;
    }

    private void exit() {
        onBackPressed();
        GalleryToolbar toolbar = getToolbar();
        if (toolbar != null && toolbar.isOverflowMenuShowing()) {
            toolbar.hideOverflowMenu();
        }
        ThreadUtil.postOnUiThreadDelayed(new d(this, 2), 500);
    }

    private void expandAppbar() {
        if (!hasCustomCover()) {
            return;
        }
        if (isPortrait() || isTabletDpi()) {
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(4));
        }
    }

    private int findPosition(String str) {
        MediaData mediaData;
        MediaItem read;
        int findPositionBy;
        int argValue = ArgumentsUtil.getArgValue(str, Message.KEY_POSITION, -1);
        String argValue2 = ArgumentsUtil.getArgValue(str, "id", (String) null);
        if (TextUtils.isEmpty(argValue2) || (mediaData = this.mSharingsMediaData) == null || !this.mHasAccount || (((read = mediaData.read(argValue)) != null && TextUtils.equals(MediaItemMde.getSpaceId(read), argValue2)) || (findPositionBy = this.mSharingsMediaData.findPositionBy((Object) argValue2)) < 0)) {
            return argValue;
        }
        return findPositionBy;
    }

    private int getNoItemStringRes() {
        P p6 = this.mPresenter;
        if (p6 == null || !((SharingPicturesPresenter) p6).isFamilyAlbum()) {
            return R.string.no_items_shared_by_you;
        }
        return R.string.no_shared_items;
    }

    private String getSpaceId() {
        return ArgumentsUtil.getArgs(getLocationKey()).getString("id");
    }

    private void initHeaderView() {
        this.mHeaderViewDelegate.initView();
    }

    private void initOverlayCoverView() {
        if (!SUPPORT_CUSTOM_COVER_ON_APPBAR && this.mSharingCoverView == null) {
            this.mSharingCoverView = createCoverView(getHeaderItem());
        }
    }

    private boolean isFromStorageUse() {
        P p6 = this.mPresenter;
        if (p6 == null || !((SharingPicturesPresenter) p6).isFromStorageUsage()) {
            return false;
        }
        return true;
    }

    private boolean isToolbarItemBgTransitionNeeded() {
        if (!isLandscape() || isTabletDpi()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$adjustToolbarLayout$5(GalleryToolbar galleryToolbar) {
        ViewParent parent = galleryToolbar.getParent();
        if (parent instanceof FloatingToolbarLayout) {
            ((CoordinatorLayout.LayoutParams) ((FloatingToolbarLayout) parent).getLayoutParams()).anchorGravity = 48;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkTipCard$21(Pair pair) {
        this.mHeaderViewDelegate.updateTipCard(true, pair);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createProtectionGradient$12() {
        return !isLandscape();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$exit$9() {
        if (getActivity() != null && this.mBlackboard != null) {
            if (getActivity().hasWindowFocus()) {
                BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
            } else {
                BlackboardUtils.publishBackKeyEventWithDelay(this.mBlackboard);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$8(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$needToAddFamilyMember$6(AtomicInteger atomicInteger, CountDownLatch countDownLatch) {
        atomicInteger.set(FamilyGroupHelper.getFamilyMemberCount(getContext()));
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Account account, boolean z) {
        boolean z3;
        if (account != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mHasAccount = z3;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$3(GalleryToolbar galleryToolbar) {
        ViewParent parent = galleryToolbar.getParent();
        if (parent instanceof FloatingToolbarLayout) {
            boolean isToolbarItemBgTransitionNeeded = isToolbarItemBgTransitionNeeded();
            ((FloatingToolbarLayout) parent).p(isToolbarItemBgTransitionNeeded);
            galleryToolbar.setFloatingMode(!isToolbarItemBgTransitionNeeded);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$11(GalleryToolbar galleryToolbar) {
        ViewParent parent = galleryToolbar.getParent();
        if (parent instanceof FloatingToolbarLayout) {
            boolean isToolbarItemBgTransitionNeeded = isToolbarItemBgTransitionNeeded();
            ((FloatingToolbarLayout) parent).p(isToolbarItemBgTransitionNeeded);
            galleryToolbar.setFloatingMode(!isToolbarItemBgTransitionNeeded);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreate$2() {
        SamsungAccountManager.getInstance().reload();
        ThreadUtil.runOnUiThread(new i(11));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPostResume$7() {
        if (this.mFamilyAlbumId == null) {
            loadFamilyAutoAlbumId();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCustomCover$13(CharSequence charSequence, GalleryToolbar galleryToolbar) {
        if (!isLandscape()) {
            charSequence = null;
        }
        galleryToolbar.setTitle(charSequence);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCustomCover$16(CharSequence charSequence, GalleryToolbar galleryToolbar) {
        if (!isLandscape()) {
            charSequence = null;
        }
        galleryToolbar.setSubtitle(charSequence);
    }

    /* access modifiers changed from: private */
    public void launchFamilyGroupDetail(View view) {
        postAnalyticsLog(AnalyticsEventId.EVENT_ADD_FAMILY_MEMBER);
        new OpenSamsungFamilyGroupCmd().execute(this.mPresenter, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void loadFamilyAutoAlbumId() {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_SUGGEST)) {
            String groupId = MediaItemMde.getGroupId(getHeaderItem());
            int familyAutoAlbumId = AutoAlbumHelper.getInstance().getFamilyAutoAlbumId(groupId);
            if (familyAutoAlbumId == -1) {
                familyAutoAlbumId = AutoAlbumHelper.getInstance().createFamilyAutoAlbum("Family", groupId);
            }
            this.mFamilyAlbumId = Integer.valueOf(familyAutoAlbumId);
            checkTipCard();
        }
    }

    private boolean needToAddFamilyMember() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadUtil.postOnBgThread(new c(this, atomicInteger, countDownLatch, 22));
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (atomicInteger.get() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onSharingsDataChangedOnUi() {
        if (!isDestroyed()) {
            MediaItem headerItem = getHeaderItem();
            MediaItem readByKey = this.mSharingsMediaData.readByKey(getSpaceId());
            if (readByKey != null) {
                if (!equalsMediaItem(headerItem, readByKey)) {
                    ((SharingPicturesPresenter) this.mPresenter).updateHeaderItem(readByKey);
                    updateSpace(readByKey);
                    if (headerItem == null && ((SharingPicturesPresenter) this.mPresenter).isFamilyAlbum()) {
                        ThreadUtil.postOnBgThread(new d(this, 1));
                    }
                    if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
                        Blackboard.getApplicationInstance().post("global://sharing/setting/dataChanged", MdeAlbumHelper.buildSharingPicturesSettingLocation(getBlackboard().getName(), getFamilyAlbumId(), readByKey));
                    }
                }
            } else if (headerItem == null) {
            } else {
                if ((!headerItem.isEmpty() && !checkValidSpace()) || headerItem.isEmpty()) {
                    exit();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeSharedAlbumNotifications() {
        String spaceId = getSpaceId();
        if (!TextUtils.isEmpty(spaceId)) {
            try {
                MdeNotificationManager.getInstance(getContext()).cancel(MediaItemMde.toUid(spaceId));
            } catch (NullPointerException unused) {
                Log.w(this.TAG, "removeSharedAlbumNotifications fail");
            }
        }
        String groupId = MediaItemMde.getGroupId(getHeaderItem());
        if (!TextUtils.isEmpty(groupId)) {
            try {
                MdeNotificationManager.getInstance(getContext()).cancel(MediaItemMde.toUid(groupId));
            } catch (NullPointerException unused2) {
                Log.w(this.TAG, "removeSharedAlbumNotifications fail");
            }
        }
    }

    private boolean supportTipCard() {
        if (isFromStorageUse() || getHeaderItem() == null || !MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(getHeaderItem())) || getFamilyAlbumId() == -1) {
            return false;
        }
        return true;
    }

    private void updateCustomCoverOnAppbar() {
        if (SUPPORT_CUSTOM_COVER_ON_APPBAR) {
            updateCustomCover(0, getHeaderItem());
        }
    }

    private void updateLayout() {
        initOverlayCoverView();
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.setSelectionMode(isSelectionMode());
        }
        this.mHeaderViewDelegate.updateLayout();
        SharingPicturesViewAdapter sharingPicturesViewAdapter = (SharingPicturesViewAdapter) getAdapter();
        if (sharingPicturesViewAdapter != null) {
            getLayoutManager().setDisplayCutLeft(0);
            getLayoutManager().setDisplayCutRight(0);
            getListView().setScrollerExtraPadding(0);
            sharingPicturesViewAdapter.onGroupDataChangedOnUi();
        } else {
            Log.she(this.TAG, "null adapter");
        }
        onDataChangedOnUi();
        Resources resources = getResources();
        this.mLabelView.setTextSize(0, resources.getDimension(R.dimen.noitem_main_text_size));
        this.mDescriptionView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.noitem_description_text_size));
    }

    private void updateOnePersonSharedAlbumNotice(MediaItem mediaItem) {
        boolean z;
        if (mediaItem == null) {
            Log.w(this.TAG, "updateOnePersonSharedAlbumNotice failed");
            return;
        }
        long albumExpiry = MediaItemMde.getAlbumExpiry(mediaItem);
        View view = getView();
        StringCompat stringCompat = this.TAG;
        if (view != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(stringCompat, "updateOnePersonSharedAlbumNotice", Boolean.valueOf(z), Long.valueOf(albumExpiry));
        if (view != null) {
            if (this.mOnePersonAlbumNoticeLayout == null) {
                View inflateViewStub = ViewUtils.inflateViewStub(view.findViewById(R.id.one_person_shared_album_notice_view_stub));
                if (inflateViewStub == null) {
                    Log.e(this.TAG, "failed, inflate of ViewStub");
                    return;
                }
                this.mOnePersonAlbumNoticeLayout = inflateViewStub;
            }
            if (albumExpiry != 0) {
                ((TextView) this.mOnePersonAlbumNoticeLayout.findViewById(R.id.shared_album_notice)).setText(getString(R.string.one_person_shared_album_notice, TimeUtil.getLocalizedDateTime(albumExpiry)));
            } else {
                ViewUtils.setVisibleOrGone(this.mOnePersonAlbumNoticeLayout, false);
            }
        }
    }

    private void updateSpace(MediaItem mediaItem) {
        initOverlayCoverView();
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.updateSpace(mediaItem);
        }
        updateCustomCoverOnAppbar();
        if (SUPPORT_CUSTOM_COVER_ON_HEADER) {
            this.mHeaderViewDelegate.updateView();
        }
    }

    public void adjustToolbarLayout(WindowInsets windowInsets) {
        super.adjustToolbarLayout(windowInsets);
        Optional.ofNullable(getToolbar()).ifPresent(new l(5));
    }

    public void bindView(View view) {
        GalleryAppBarLayout galleryAppBarLayout;
        super.bindView(view);
        this.mLabelView = (TextView) view.findViewById(R.id.label);
        this.mDescriptionView = (TextView) view.findViewById(R.id.description);
        if (SUPPORT_CUSTOM_COVER_ON_HEADER && (galleryAppBarLayout = this.mAppBarLayout) != null) {
            galleryAppBarLayout.setCustomHeightProportion(false, 0);
        }
    }

    public void closeMediaData() {
        MediaData mediaData = this.mSharingsMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mSharingsDataChangeListener);
            this.mSharingsMediaData.close();
            this.mSharingsMediaData = null;
        }
        super.closeMediaData();
    }

    public GridInfo.ClusterInfo createClusterInfo() {
        return null;
    }

    public CustomCover createCustomCover(View view) {
        if (SUPPORT_CUSTOM_COVER_ON_HEADER) {
            return null;
        }
        if (SUPPORT_BLUR_COVER_ON_APPBAR) {
            return new BlurCustomCover(view.findViewById(R.id.custom_cover), R.layout.blur_custom_cover_layout);
        }
        return new CustomSharingAlbumCoverImpl(view.findViewById(R.id.custom_cover), R.layout.custom_cover_sharing_album_layout);
    }

    public ListProtectionGradient createProtectionGradient() {
        if (!PickerUtil.isPickerMode(this.mBlackboard)) {
            return new ListProtectionGradientImpl(new C0385u(8, this));
        }
        return super.createProtectionGradient();
    }

    public void dismissAlbumLinkTip() {
        this.mLinkTip.dismiss(false);
    }

    public MediaItem[] getAllItems() {
        return (MediaItem[]) Arrays.stream(super.getAllItems()).filter(new C0571z(4)).toArray(new C0387w(25));
    }

    public SharingCoverView getCoverView() {
        return this.mSharingCoverView;
    }

    public int getDefaultSidePadding() {
        return getResources().getDimensionPixelOffset(R.dimen.album_pictures_side_spacing);
    }

    public PicturesViewDummyAdapter getDummyAdapter() {
        return new SharingPicturesViewDummyAdapter(getListView(), getColumnCount());
    }

    public int getFamilyAlbumId() {
        Integer num = this.mFamilyAlbumId;
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public MediaItem getHeaderItem() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((SharingPicturesPresenter) p6).getHeaderItem();
        }
        return null;
    }

    public int getLayoutId() {
        if (SUPPORT_CUSTOM_COVER_ON_APPBAR) {
            return R.layout.fragment_sharing_pictures_layout_v2;
        }
        return R.layout.fragment_sharing_pictures_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return super.getPinchAnimationManager();
    }

    public int getPreferenceDefault() {
        return getGridHelper().getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return getGridHelper().getPreferenceName();
    }

    public String getScreenId() {
        boolean z;
        P p6 = this.mPresenter;
        if (p6 == null || !((SharingPicturesPresenter) p6).isFamilyAlbum()) {
            z = false;
        } else {
            z = true;
        }
        if (isSelectionMode()) {
            if (z) {
                return AnalyticsScreenId.SCREEN_SHARED_FAMILY_DETAIL_VIEW_SELECTION.toString();
            }
            return AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_SELECTION.toString();
        } else if (z) {
            return AnalyticsScreenId.SCREEN_SHARED_FAMILY_DETAIL_VIEW_NORMAL.toString();
        } else {
            return AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_NORMAL.toString();
        }
    }

    public ArrayList<View> getSharedViewList(Blackboard blackboard) {
        return (ArrayList) blackboard.pop("data://shared_view/sharing");
    }

    public int getStartPinchDepth() {
        return getPinchDepthRecoveredIfAbsent();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        updateLayout();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        CustomCover customCover = this.mCustomCoverHolder;
        if (customCover != null) {
            customCover.handleOrientationChange(i2);
        }
        updateLayout();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (!isConfigStateChanged(2)) {
            updateLayout();
        }
    }

    public boolean hasCustomCover() {
        if (this.mCustomCoverHolder != null) {
            return true;
        }
        return false;
    }

    public void initView(View view) {
        super.initView(view);
        initOverlayCoverView();
        initHeaderView();
        if (this.mPendingDataChanged) {
            this.mPendingDataChanged = false;
            onSharingsDataChangedOnUi();
        }
    }

    public void initializeEmptyView() {
        super.initializeEmptyView();
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE && isFromStorageUse()) {
            this.mLabelView.setText(getNoItemStringRes());
            this.mDescriptionView.setText((CharSequence) null);
        }
    }

    public int[] loadPinchColumnResource() {
        return getGridHelper().getGridArray(getContext());
    }

    public void onAppbarVisibleRatio(float f) {
        if (SUPPORT_BLUR_COVER_ON_APPBAR) {
            CustomCover customCover = this.mCustomCoverHolder;
            if (customCover != null) {
                customCover.setAlphaOnCoverView(f);
                return;
            }
            return;
        }
        super.onAppbarVisibleRatio(f);
    }

    public boolean onBackPressed() {
        if (!PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE || !isFromStorageUse()) {
            SharedTransition.setReturnPosition(this.mBlackboard, findPosition(getLocationKey()));
            return super.onBackPressed();
        }
        new RequestSharedAlbumCmd().execute(getEventContext(), RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceItem, getSpaceId());
        if (isSelectionMode()) {
            exitSelectionMode(false);
        }
        return false;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable(getToolbar()).ifPresent(new f(this, 0));
        expandAppbar();
    }

    public void onConfigurationChanged(Configuration configuration) {
        String str;
        super.onConfigurationChanged(configuration);
        this.mLinkTip.onConfigurationChanged(getToolbar());
        Optional.ofNullable(getToolbar()).ifPresent(new f(this, 1));
        if (SUPPORT_BLUR_COVER_ON_APPBAR) {
            MediaItem headerItem = getHeaderItem();
            if (headerItem != null) {
                str = headerItem.getTitle();
            } else {
                str = null;
            }
            updateCustomCover(1, str);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SamsungAccountManager.getInstance().addListener(this.mAccountUpdatedListener);
        SimpleThreadPool.getInstance().execute(new i(10));
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE && isFromStorageUse() && !isSelectionMode() && getDataCount() > 0) {
            enterSelectionMode(0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.recycle();
            restoreLayout(R.layout.recycler_item_sharing_pictures_header_layout, this.mSharingCoverView.getView());
            this.mSharingCoverView = null;
        }
        this.mHeaderViewDelegate.recycle();
        SamsungAccountManager.getInstance().removeListener(this.mAccountUpdatedListener);
        this.mBlackboard.erase("data://albums/current");
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        updateEmptyView();
    }

    public void onEnterTransitionEndV2() {
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.setDimming(0.3f);
        }
        if (this.mPresenter == null && System.currentTimeMillis() - this.startTime < 50) {
            this.mBlackboard.postEvent(EventMessage.obtain(1060));
        }
    }

    public void onEnterTransitionStartV2() {
        this.startTime = System.currentTimeMillis();
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.setDimming(0.0f);
        }
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
            super.onGridChanged(i2, i7);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if ((!isInMultiWindowMode() || !isSelectionMode()) && super.onListItemLongClick(listViewHolder, i2, mediaItem)) {
            return true;
        }
        return false;
    }

    public void onPostResume() {
        super.onPostResume();
        P p6 = this.mPresenter;
        if (p6 == null || ((SharingPicturesPresenter) p6).isFamilyAlbum()) {
            ThreadUtil.postOnBgThread(new d(this, 0));
        }
    }

    public void onResume() {
        if (this.mSharingsMediaData.isDataAvailable() && !checkValidSpace()) {
            exit();
        }
        ThreadUtil.postOnBgThread(new d(this, 3));
        super.onResume();
    }

    public void onSelectionModeChanged(boolean z) {
        if (!SUPPORT_BLUR_COVER_ON_APPBAR) {
            super.onSelectionModeChanged(z);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateCustomCoverOnAppbar();
    }

    public void openMediaData() {
        super.openMediaData();
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://sharing/albums");
        this.mSharingsMediaData = open;
        open.register(this.mSharingsDataChangeListener);
    }

    public void showAlbumLinkTip() {
        this.mLinkTip.show(getToolbar(), false);
    }

    public void startEnlargeAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    public boolean supportDeleteAnimation() {
        return false;
    }

    public boolean supportEnterDefaultTransition() {
        return SUPPORT_CUSTOM_COVER_ON_APPBAR;
    }

    public boolean supportExitDefaultTransition() {
        return SUPPORT_CUSTOM_COVER_ON_APPBAR;
    }

    public void updateCoverItemCount(int i2, int i7) {
        String countString = StringResources.getCountString(i2, i7);
        if (SUPPORT_CUSTOM_COVER_ON_APPBAR) {
            updateCustomCover(2, countString);
        } else {
            Optional.ofNullable(getCoverView()).ifPresent(new c(1, countString));
        }
    }

    public boolean updateCustomCover(int i2, Object obj) {
        MediaItem mediaItem;
        String str;
        if (hasCustomCover()) {
            if (i2 == 0) {
                if (obj instanceof MediaItem) {
                    mediaItem = (MediaItem) obj;
                } else {
                    mediaItem = ((SharingPicturesPresenter) this.mPresenter).getHeaderItem();
                }
                this.mCustomCoverHolder.bindImage(mediaItem);
                if (mediaItem != null) {
                    str = mediaItem.getTitle();
                } else {
                    str = null;
                }
                obj = str;
            } else if (i2 != 1) {
                if (i2 != 2) {
                    return false;
                }
                CharSequence charSequence = (CharSequence) obj;
                this.mCustomCoverHolder.updateDecorView(i2, charSequence);
                if (SUPPORT_BLUR_COVER_ON_APPBAR) {
                    Optional.ofNullable(getToolbar()).ifPresent(new O5.c(this, charSequence, 0));
                    return true;
                }
                Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(1));
                Optional.ofNullable(getToolbar()).ifPresent(new b(charSequence, 1));
                return true;
            }
            CharSequence charSequence2 = (CharSequence) obj;
            this.mCustomCoverHolder.updateDecorView(1, charSequence2);
            if (SUPPORT_BLUR_COVER_ON_APPBAR) {
                Optional.ofNullable(getToolbar()).ifPresent(new O5.c(this, charSequence2, 1));
                return true;
            }
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(6));
            Optional.ofNullable(getToolbar()).ifPresent(new b(charSequence2, 0));
            return true;
        } else if (!SUPPORT_CUSTOM_COVER_ON_HEADER || isSelectionMode()) {
            return false;
        } else {
            Optional.ofNullable(getToolbar()).ifPresent(new l(2));
            Optional.ofNullable(getToolbar()).ifPresent(new l(3));
            return false;
        }
    }

    public void updateEmptyView() {
        int i2;
        int i7;
        P p6 = this.mPresenter;
        if (p6 == null || ((SharingPicturesPresenter) p6).isFamilyAlbum()) {
            boolean needToAddFamilyMember = needToAddFamilyMember();
            if (needToAddFamilyMember && isEmptyViewShowing() && this.mAddFamilyButton == null) {
                LinearLayout linearLayout = (LinearLayout) this.mEmptyView.findViewById(R.id.add_family_button_layout);
                this.mAddFamilyButton = linearLayout;
                linearLayout.setOnClickListener(new A(15, this));
            }
            ViewUtils.setVisibleOrGone(this.mAddFamilyButton, needToAddFamilyMember);
            TextView textView = this.mLabelView;
            if (needToAddFamilyMember) {
                i2 = R.string.no_family_members_title;
            } else {
                i2 = R.string.no_shared_items;
            }
            ViewUtils.setText(textView, getString(i2));
            TextView textView2 = this.mDescriptionView;
            if (needToAddFamilyMember) {
                i7 = R.string.no_family_members_description;
            } else {
                i7 = R.string.no_shared_item_description;
            }
            ViewUtils.setText(textView2, getString(i7));
        }
    }

    public void updateGroup(MediaItem mediaItem) {
        SharingCoverView sharingCoverView = this.mSharingCoverView;
        if (sharingCoverView != null) {
            sharingCoverView.updateGroup(mediaItem);
        }
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            updateOnePersonSharedAlbumNotice(mediaItem);
        }
    }

    public SharingPicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SharingPicturesViewAdapter(this, getLocationKey(), this.mHeaderViewDelegate.get(), true);
    }

    public SharingPicturesPresenter createPresenter(ISharingPicturesView iSharingPicturesView) {
        return new SharingPicturesPresenter(this.mBlackboard, iSharingPicturesView);
    }
}
