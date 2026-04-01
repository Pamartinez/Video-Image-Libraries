package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.C0366a;
import A4.C0384t;
import A4.E;
import A4.F;
import A4.G;
import A4.H;
import A4.I;
import A4.J;
import A4.K;
import A4.L;
import A4.r;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.MenuCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.appbar.SuggestAppbarDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuData;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuDataFactory;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuParams;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mtp.UsbStorageState;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenLabel;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.scroller.OverScrollDetector;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.OnSelectAllListener;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseListPresenter<V extends IBaseListView> extends MvpBasePresenter<V> implements OverScrollDetector.OverScrollListener, ListMenuUpdater.IMenuDelegation {
    protected ListViewServiceBixby mBixbyProxy = new ListViewServiceBixby(this);
    protected final ListMenuUpdater mMenuDelegation;
    private boolean mPendingReopenFloatingRecommendation;
    private EventContext.OnSelectionListener mSelectionListener;
    protected final SuggestAppbarDelegate<V> mSuggestAppbarDelegate;

    public BaseListPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        this.mMenuDelegation = createMenuUpdateDelegation(v);
        this.mSuggestAppbarDelegate = new SuggestAppbarDelegate<>(v);
    }

    private void activateShrink() {
        this.mBlackboard.erase("data://shrink_active");
    }

    private boolean allowCopyDeleteKeyFunction() {
        if (((IBaseListView) this.mView).getAdapter() == null || ((IBaseListView) this.mView).getAdapter().getSelectableChecker() != null || !this.mBlackboard.isEmpty("data://user/selection/ShareVia")) {
            return false;
        }
        return true;
    }

    private boolean completeSelectionMode() {
        EventContext.OnSelectionListener onSelectionListener = this.mSelectionListener;
        if (onSelectionListener == null || !onSelectionListener.onSelectionCompleted(this, getSelectedItems())) {
            return true;
        }
        this.mSelectionListener = null;
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1003));
        return true;
    }

    private void enterSelectionForCreatingMedia(EventMessage eventMessage) {
        BaseListViewAdapter adapter = ((IBaseListView) this.mView).getAdapter();
        if (adapter == null) {
            Log.e(this.TAG, "enterSelectionForCreatingMedia failed. adapter not ready");
            return;
        }
        Bundle data = eventMessage.getData();
        String string = data.getString("title", "");
        int i2 = data.getInt("maxCount");
        if (!this.mBlackboard.isEmpty("data://user/pick/ItemChecker")) {
            adapter.setSelectableChecker((SelectableChecker) this.mBlackboard.read("data://user/pick/ItemChecker"));
        }
        enterSelectionMode(string, i2, new G(adapter));
    }

    private boolean executeOptionOnKey(int i2) {
        MenuItem findMenuItem;
        if (getMenuDataBinding() == null || (findMenuItem = getMenuDataBinding().findMenuItem(i2)) == null) {
            return false;
        }
        if (findMenuItem.isVisible() || findMenuItem.isEnabled()) {
            return onOptionsItemSelected(findMenuItem);
        }
        return false;
    }

    private boolean executeOptionOnKeyAfterSelect(int i2) {
        if (getSelectedItemCount() > 0 || isOnAdvancedMouseFocused()) {
            return executeOptionOnKey(i2);
        }
        return false;
    }

    private String getPlayEventID(MediaItem mediaItem) {
        String shotMode = mediaItem.getShotMode();
        if (shotMode == null) {
            shotMode = mediaItem.getGroupMode();
        }
        if (shotMode != null) {
            return ShotMode.getShotModeEventId(shotMode);
        }
        if (mediaItem.isVideo()) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_VIDEO.toString();
        }
        if (mediaItem.isGif()) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_GIF.toString();
        }
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_NORMAL_IMAGE.toString();
    }

    private void inflateBottomBar() {
        this.mBlackboard.post("command://InflateBottomBar", (Object) null);
    }

    private boolean isDeactivatedShrink() {
        if (!this.mBlackboard.isEmpty("data://shrink_active") || SharedTransition.isInReturnTransition(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    private boolean isLocationKeyMatch() {
        String locationKey = getLocationKey();
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        if (locationKey == null || readLocationKeyCurrent == null) {
            return false;
        }
        return ArgumentsUtil.removeArgs(locationKey).equals(ArgumentsUtil.removeArgs(readLocationKeyCurrent));
    }

    private boolean isSupportedDone() {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null || !menuDataBinding.hasItem(R.id.action_done)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$3(Object obj, Bundle bundle) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null && menuDataBinding.hasItem(R.id.action_gallery_assistant)) {
            menuDataBinding.setVisible((int) R.id.action_gallery_assistant, Features.isEnabled(Features.SUPPORT_GALLERY_ASSISTANT));
            ((IBaseListView) this.mView).invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$4(Object obj, Bundle bundle) {
        this.mSuggestAppbarDelegate.updateUsbStorageCard();
        if (getMenuDataBinding() == null) {
            ThreadUtil.postOnUiThreadDelayed(new r(1, this), 200);
        } else {
            updateUsbStorageVolumeMenu();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        ((IBaseListView) this.mView).releaseInputBlocking();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        this.mSuggestAppbarDelegate.hideUsbStorageCard(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$2(Object obj, Bundle bundle) {
        ((IBaseListView) this.mView).predictiveBackCanceled();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onDeleteMotionPhotoVideoClipSelectionRequested$8(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            new MotionPhotoDeleteVideoCmd().execute(eventContext, new Object[0]);
            return false;
        }
        this.mBlackboard.erase("data://user/selection/DeleteMotionPhotoVideoClip");
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onSelectionRequested$6(String str, EventContext eventContext, MediaItem[] mediaItemArr) {
        this.mBlackboard.publish(str, mediaItemArr);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onShareViaSelectionRequested$7(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr != null) {
            new ShareViaCmd().execute(eventContext, mediaItemArr, null);
            return false;
        }
        this.mBlackboard.erase("data://user/selection/ShareVia");
        return true;
    }

    private void moveToFloatingRecommendation() {
        this.mBlackboard.postEvent(EventMessage.obtain(8020, new Object[]{Boolean.FALSE, null}));
    }

    private void onDeleteMotionPhotoVideoClipSelectionRequested(Bundle bundle) {
        setSelectableChecker();
        enterSelectionMode(bundle.getString("title"), -1, new K(0, this));
    }

    private void onShareViaSelectionRequested() {
        enterSelectionMode(AppResources.getString(R.string.share_short), -1, new K(1, this));
        this.mBlackboard.publish("data://user/selection/ShareVia", Boolean.TRUE);
    }

    private void removeItemFromClipboardView(EventMessage eventMessage) {
        BaseListViewAdapter adapter;
        Clipboard.getInstance(this.mBlackboard).saveRemovedClipboardItemId(Long.valueOf(((MediaItem) eventMessage.obj).getFileId()));
        GalleryListView listView = ((IBaseListView) this.mView).getListView();
        if (listView != null && (adapter = ((IBaseListView) this.mView).getAdapter()) != null) {
            adapter.notifySelectedItemChanged(listView.findFirstVisibleItemPositionCompat(), listView.findLastVisibleItemPositionCompat());
        }
    }

    private void setListViewClipToPadding(boolean z) {
        if (((IBaseListView) this.mView).getListView() != null) {
            ((IBaseListView) this.mView).getListView().setClipToPadding(z);
        }
    }

    private void setSelectableChecker() {
        if (!this.mBlackboard.isEmpty("data://user/pick/ItemChecker")) {
            BaseListViewAdapter adapter = ((IBaseListView) this.mView).getAdapter();
            if (adapter != null) {
                adapter.setSelectableChecker((SelectableChecker) this.mBlackboard.read("data://user/pick/ItemChecker"));
            }
            this.mBlackboard.erase("data://user/pick/ItemChecker");
        }
    }

    /* access modifiers changed from: private */
    public void startSelectionMode(Object obj, Bundle bundle) {
        if (((IBaseListView) this.mView).isViewActive()) {
            onEnterSelectionMode(obj, bundle);
            ((IBaseListView) this.mView).onSelectionModeChanged(true);
        }
    }

    /* access modifiers changed from: private */
    public void stopSelectionMode(Object obj, Bundle bundle) {
        if (((IBaseListView) this.mView).isViewActive()) {
            onExitSelectionMode(obj, bundle);
            ((IBaseListView) this.mView).onSelectionModeChanged(false);
        }
    }

    private void toggleSelectAll() {
        if (!isSelectionMode()) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SELECT_ALL_NORMAL_MODE);
            enterSelectionMode(0);
            ((OnSelectAllListener) this.mView).onSelectAll();
        } else if (isSelectAll()) {
            ((OnSelectAllListener) this.mView).onUnSelectAll();
            postAnalyticsLog(AnalyticsEventId.EVENT_DESELECT_ALL_EDIT_MODE);
        } else {
            ((OnSelectAllListener) this.mView).onSelectAll();
            postAnalyticsLog(AnalyticsEventId.EVENT_SELECT_ALL_EDIT_MODE);
        }
    }

    /* access modifiers changed from: private */
    public void updateListViewBottomPadding(Object obj, Bundle bundle) {
        if (((IBaseListView) this.mView).isViewActive()) {
            Object[] objArr = (Object[]) obj;
            int intValue = ((Integer) objArr[0]).intValue();
            boolean booleanValue = ((Boolean) objArr[1]).booleanValue();
            if (!((IBaseListView) this.mView).isMoveMode() || booleanValue) {
                ((IBaseListView) this.mView).updateListViewBottomPadding(intValue);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateUsbStorageVolumeMenu() {
        int i2;
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null) {
            Log.w(this.TAG, "updateStorageVolumeMenu skip. menu not ready");
        } else if (menuDataBinding.hasItem(R.id.action_usb_otg)) {
            boolean isMounted = UsbStorageState.getInstance().isMounted();
            if (menuDataBinding.setVisible((int) R.id.action_usb_otg, isMounted)) {
                Log.d(this.TAG, "updateStorageVolumeMenu", Boolean.valueOf(isMounted), UsbStorageState.getInstance().getVolumeName());
                if (isMounted) {
                    i2 = 0;
                } else {
                    i2 = 2;
                }
                Stream.of(new Integer[]{Integer.valueOf(R.id.action_cloud_sync_timeline), Integer.valueOf(R.id.action_create_albums)}).filter(new I(0, menuDataBinding)).forEach(new J((Object) menuDataBinding, i2, 0));
                IBaseListView iBaseListView = (IBaseListView) this.mView;
                Objects.requireNonNull(iBaseListView);
                ThreadUtil.runOnUiThread(new F(iBaseListView, 1));
            }
        }
    }

    public void addOverScrollListener() {
        RecyclerView.LayoutManager layoutManager = ((IBaseListView) this.mView).getLayoutManager();
        if (layoutManager instanceof GalleryGridLayoutManager) {
            ((GalleryGridLayoutManager) layoutManager).setOverScrollListener(this);
        }
    }

    public void changeViewDepth(GalleryListView galleryListView, int i2) {
        if (galleryListView != null && (galleryListView.getLayoutManager() instanceof GridLayoutManager)) {
            ((IBaseListView) this.mView).scrollListToPositionByDepth(i2);
            galleryListView.changeDepth((GridLayoutManager) galleryListView.getLayoutManager(), i2);
            galleryListView.onGridChanged();
        }
    }

    public void checkPreviewCandidate() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            Optional.ofNullable(((IBaseListView) this.mView).getAdapter()).ifPresent(new C0366a(14));
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/assistant/PackageChanged", new E(0, this)).setWorkingOnUI());
        if (!Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            return;
        }
        if (LocationKey.isTimeline(getLocationKey()) || LocationKey.isAlbumsMatch(getLocationKey())) {
            arrayList.add(new SubscriberInfo("event/UsbStorageVolumeChanged", new E(5, this)).setWorkingOnUI().setTriggerPreloadedAsync());
        }
    }

    public MenuHandler createMenuHandler() {
        return new ListMenuHandler();
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this);
    }

    public PopupMenuData createPopupMenuData(Menu menu, int i2) {
        return PopupMenuDataFactory.create(new PopupMenuParams.Builder(menu).setInputType(i2).setFocusedItem((MediaItem) this.mBlackboard.read("data://focused_item")).setLocationKey(getLocationKey()).setIsPickerMode(PickerUtil.isPickerMode(this.mBlackboard)).setIsSelectionMode(isSelectionMode()).build());
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://user/selection/#"), new E(6, this)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new E(7, this)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new E(8, this)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://UpdateListViewBottomPadding", new E(9, this)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://BeamDataReq", new E(10, this)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command://OperateClipboardArea", new E(1, this)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command:///ReleaseInputBlock", new E(2, this)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command:///UsbStorageTipDismiss", new E(3, this)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo("command:///OnPredictiveBackCanceledInFragment", new E(4, this)).setWorkingOnUI());
    }

    public void enterSelectionMode(CharSequence charSequence, int i2, EventContext.OnSelectionListener onSelectionListener) {
        this.mMenuDelegation.setSelectionDoneTitle(charSequence);
        this.mSelectionListener = onSelectionListener;
        enterSelectionMode(i2);
    }

    public void exitSelectionMode(boolean z) {
        Optional.ofNullable(((IBaseListView) this.mView).getListView()).ifPresent(new C0366a(15));
        EventContext.OnSelectionListener onSelectionListener = this.mSelectionListener;
        if (onSelectionListener != null) {
            onSelectionListener.onSelectionCompleted(this, (MediaItem[]) null);
            this.mSelectionListener = null;
        }
        if (!this.mBlackboard.isEmpty("data://user/pick/ItemChecker")) {
            this.mBlackboard.erase("data://user/pick/ItemChecker");
        }
        this.mMenuDelegation.setSelectionDoneTitle((CharSequence) null);
        this.mSelectionListener = null;
        ((IBaseListView) this.mView).exitSelectionMode(z);
    }

    public void forceReloadData() {
        if (!((IBaseListView) this.mView).isDestroyed()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "forceReloadData location=" + getLocationKey());
            MediaData mediaData = ((IBaseListView) this.mView).getMediaData(getLocationKey());
            if (mediaData != null) {
                mediaData.reopen(getLocationKey());
                if (((IBaseListView) this.mView).clearAdvancedMouseFocus()) {
                    ((IBaseListView) this.mView).invalidateOptionsMenu();
                }
            }
        }
    }

    public MediaItem[] getAllItems() {
        return ((IBaseListView) this.mView).getAllItems();
    }

    public ListViewServiceBixby getBixbyProxy() {
        return this.mBixbyProxy;
    }

    public View getBottomBar() {
        inflateBottomBar();
        return getActivity().findViewById(R.id.bottom_bar);
    }

    public View getBottomMoveBar() {
        this.mBlackboard.post("command://InflateBottomMoveBar", (Object) null);
        return getActivity().findViewById(R.id.bottom_move_bar);
    }

    public int getBottomTabHeight() {
        Context context = getContext();
        if (context == null || !((IBaseListView) this.mView).supportTabLayout() || ((IBaseListView) this.mView).isDrawerMode()) {
            return 0;
        }
        return context.getResources().getDimensionPixelOffset(R.dimen.bottom_tab_floating_height);
    }

    public int getDataCount() {
        return ((IBaseListView) this.mView).getDataCount();
    }

    public int getDividerButtonHeight() {
        return 0;
    }

    public int getFirstItemDataPositionFromSelected() {
        return ((IBaseListView) this.mView).getFirstItemDataPositionFromSelected();
    }

    public int getFocusedItemCount() {
        if (isOnAdvancedMouseFocused()) {
            return ((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager().getTotalCount();
        }
        return 0;
    }

    public int getImageCount() {
        return 0;
    }

    public int getItemCount() {
        return ((IBaseListView) this.mView).getItemCount();
    }

    public MediaData getMediaData() {
        return ((IBaseListView) this.mView).getMediaData(getLocationKey());
    }

    public int getSearchToolbarHeight() {
        return 0;
    }

    public final int getSelectedItemCount() {
        return ((IBaseListView) this.mView).getSelectedItemCount();
    }

    public MediaItem[] getSelectedItems() {
        return ((IBaseListView) this.mView).getSelectedItems();
    }

    public int getVideoCount() {
        return 0;
    }

    public String getViewerLocationKey() {
        return getLocationKey();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 101) {
            forceReloadData();
        } else if (i2 != 1027) {
            boolean z = false;
            if (i2 != 1053) {
                if (i2 == 1064) {
                    ((Boolean) eventMessage.obj).getClass();
                    setListViewClipToPadding(false);
                    if (!((IBaseListView) this.mView).isDrawerMode() || !((IBaseListView) this.mView).isDisplayWithDrawer()) {
                        return true;
                    }
                    return false;
                } else if (i2 == 1068) {
                    ((IBaseListView) this.mView).startDragAndDropOnAdvancedMouseAction(((Integer) eventMessage.obj).intValue(), (ListViewHolder) null);
                } else if (i2 == 1087) {
                    this.mMenuDelegation.updateVerizonCloudMenu();
                    return true;
                } else if (i2 == 3008) {
                    ListViewServiceBixby listViewServiceBixby = this.mBixbyProxy;
                    if (listViewServiceBixby != null) {
                        listViewServiceBixby.handleCommand((Uri) eventMessage.obj);
                    }
                } else if (i2 == 8020) {
                    new FloatingRecommendationLauncher((IBaseListView) this.mView).captureScreenAndExecuteFloatingRecommendation(eventMessage.obj);
                } else if (i2 == 8033) {
                    this.mPendingReopenFloatingRecommendation = true;
                } else if (i2 == 1002) {
                    postAnalyticsLog(AnalyticsEventId.EVENT_MENU_EDIT);
                    enterSelectionMode(0);
                } else if (i2 == 1003) {
                    Object obj = eventMessage.obj;
                    if (obj instanceof Boolean) {
                        z = ((Boolean) obj).booleanValue();
                    }
                    exitSelectionMode(z);
                } else if (!(i2 == 4001 || i2 == 4002)) {
                    switch (i2) {
                        case 1022:
                            toggleSelectAll();
                            break;
                        case 1023:
                            postAnalyticsLog(AnalyticsEventId.EVENT_REMOVE_SELECTED_ITEM);
                            removeItemFromClipboardView(eventMessage);
                            ((IBaseListView) this.mView).invalidateToolbar();
                            break;
                        case 1024:
                            changeViewDepth(((IBaseListView) this.mView).getListView(), ((Integer) eventMessage.obj).intValue());
                            updateViewHolderMargin();
                            break;
                        case 1025:
                            enterSelectionForCreatingMedia(eventMessage);
                            break;
                        default:
                            return super.handleEvent(eventMessage);
                    }
                }
            } else if (isViewActive()) {
                GalleryListView listView = ((IBaseListView) this.mView).getListView();
                if (!listView.isScrollIdle()) {
                    listView.stopScroll();
                }
                if (!listView.isOngoingPinchAnimation()) {
                    listView.smoothScrollToPositionJumpIfNeeded(0);
                }
            }
        } else {
            updateSelectedItem(eventMessage);
        }
        return true;
    }

    public boolean isAllowItemClick() {
        if (!setInputBlock(this.TAG + "_isAllowItemClick", StatusCodes.INPUT_MISSING) || !isDeactivatedShrink()) {
            return false;
        }
        return true;
    }

    public boolean isFloatingPopupMenu() {
        return ((Boolean) getBlackboard().read("data://floating_pop_menu", Boolean.FALSE)).booleanValue();
    }

    public boolean isLowStorageMode() {
        return StorageUtil.isLowStorage();
    }

    public boolean isOnAdvancedMouseFocused() {
        if (!((IBaseListView) this.mView).useAdvancedMouseDragAndDrop() || isSelectionMode() || ((IBaseListView) this.mView).getAdapter() == null || ((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager() == null || ((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager().getTotalCount() <= 0) {
            return false;
        }
        return true;
    }

    public boolean isOnAdvancedMouseMultiFocused() {
        if (!((IBaseListView) this.mView).useAdvancedMouseDragAndDrop() || isSelectionMode() || ((IBaseListView) this.mView).getAdapter() == null || ((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager() == null || ((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager().getTotalCount() <= 1) {
            return false;
        }
        return true;
    }

    public boolean isPopupMenuItemFocused() {
        if (!isFloatingPopupMenu() || getBlackboard().read("data://focused_item") == null) {
            return false;
        }
        return true;
    }

    public boolean isPopupMenuShowing() {
        return ((IBaseListView) this.mView).isPopupMenuShowing();
    }

    public boolean isSelectAll() {
        if (isOnAdvancedMouseFocused()) {
            if (((IBaseListView) this.mView).getAdapter().getAdvancedMouseFocusManager().getTotalCount() == ((IBaseListView) this.mView).getDataCount()) {
                return true;
            }
            return false;
        } else if (getSelectedItemCount() == ((IBaseListView) this.mView).getDataCount()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSelectionMode() {
        return ((IBaseListView) this.mView).isSelectionMode();
    }

    public boolean isViewActive() {
        return ((IBaseListView) this.mView).isViewActive();
    }

    public void moveToSearch() {
        MenuItem findMenuItemWithItemMode;
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null && (findMenuItemWithItemMode = menuDataBinding.findMenuItemWithItemMode(R.id.action_search, this.mMenuDelegation.getItemMode())) != null && findMenuItemWithItemMode.isVisible() && getMenuHandler() != null) {
            getMenuHandler().onOptionsItemSelected(this, findMenuItemWithItemMode);
        }
    }

    public boolean onCopyKey() {
        if (!allowCopyDeleteKeyFunction()) {
            return false;
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return executeOptionOnKeyAfterSelect(R.id.action_copy_to_clipboard);
        }
        return executeOptionOnKeyAfterSelect(R.id.action_copy_to_album);
    }

    public boolean onCreateKey() {
        int createMenuId;
        if (getMenuDataBinding() == null || (createMenuId = getMenuDataBinding().getCreateMenuId(getLocationKey())) == 0) {
            return false;
        }
        return executeOptionOnKey(createMenuId);
    }

    public boolean onCreatePopupMenu(Menu menu, MenuInflater menuInflater) {
        int intValue = ((Integer) Optional.ofNullable(getMenuDataBinding()).map(new C0384t(12)).orElse(0)).intValue();
        if (intValue <= 0) {
            return false;
        }
        menuInflater.inflate(intValue, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }

    public void onDataPrepared() {
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
    }

    public boolean onDeleteKey() {
        if (getMenuDataBinding() == null || !allowCopyDeleteKeyFunction()) {
            return false;
        }
        return executeOptionOnKeyAfterSelect(getMenuDataBinding().getDeleteMenuId(getLocationKey()));
    }

    public void onEnterSelectionMode(Object obj, Bundle bundle) {
        ((IBaseListView) this.mView).updateSelectionToolBar();
        ((IBaseListView) this.mView).updateFadingEdge();
        ((IBaseListView) this.mView).setFloatingToolbarScrollTransition(false);
        ((IBaseListView) this.mView).setSmartAlbumEnabled(false);
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            this.mSuggestAppbarDelegate.hideUsbStorageCard();
        }
    }

    public boolean onEscapeKey() {
        onNavigationPressed((View) null);
        return true;
    }

    public void onExitSelectionMode(Object obj, Bundle bundle) {
        ((IBaseListView) this.mView).updateSelectionToolBar();
        ((IBaseListView) this.mView).updateFadingEdge();
        if (!((IBaseListView) this.mView).isMoveMode()) {
            ((IBaseListView) this.mView).setFloatingToolbarScrollTransition(true);
        }
        if (((IBaseListView) this.mView).getToolbar() != null) {
            updateToolbar(((IBaseListView) this.mView).getToolbar());
            setNavigationUpClickListener(((IBaseListView) this.mView).getToolbar());
        }
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            this.mSuggestAppbarDelegate.showUsbStorageCard();
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && isSelectionMode()) {
            ((IBaseListView) this.mView).getListView().notifySelectedItemChanged();
        }
    }

    public final boolean onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        BaseListViewAdapter adapter;
        Bitmap bitmap;
        if (isAllowItemClick()) {
            onListItemClickInternal(i2, mediaItem);
            IBaseListView iBaseListView = (IBaseListView) this.mView;
            Objects.requireNonNull(iBaseListView);
            ThreadUtil.postOnUiThread(new F(iBaseListView, 0));
            if ((mediaItem.getWidth() <= 0 || mediaItem.getHeight() <= 0) && mediaItem.isVideo() && LocationKey.isPictures(getLocationKey()) && (bitmap = listViewHolder.getBitmap(false)) != null) {
                mediaItem.setSize(bitmap.getWidth(), bitmap.getHeight());
            }
            return true;
        }
        boolean isDeactivatedShrink = isDeactivatedShrink();
        boolean z = !isDeactivatedShrink;
        if (!((Boolean) this.mBlackboard.pop("data://gesture_on_double_tapped", Boolean.FALSE)).booleanValue()) {
            Log.st(this.TAG, "not allow click. shrinkActive : " + z);
            if (!isDeactivatedShrink) {
                if (BlackboardUtils.isViewerDragShrink(this.mBlackboard)) {
                    Log.st(this.TAG, "force release drag shrink by multi-touch condition.");
                    Optional.ofNullable(((IBaseListView) this.mView).getListView()).ifPresent(new C0366a(13));
                }
                activateShrink();
            }
            if (((IBaseListView) this.mView).useAdvancedMouseDragAndDrop() && isDexMode() && !isSelectionMode() && isDeactivatedShrink && (adapter = ((IBaseListView) this.mView).getAdapter()) != null && !adapter.isOnShiftKeyCombination() && adapter.getAdvancedMouseFocusManager() != null) {
                adapter.getAdvancedMouseFocusManager().clearViewPosition();
                adapter.getAdvancedMouseFocusManager().addViewPosition(listViewHolder.getViewPosition());
                adapter.notifyAdvancedMouseFocusedItemChanged();
            }
        }
        return false;
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (i2 < 0) {
            return;
        }
        if (((Boolean) this.mBlackboard.read("data://on_location_moving", Boolean.FALSE)).booleanValue()) {
            Log.e(this.TAG, "skip OnListItemClick. on location moving");
            return;
        }
        if (!getBlackboard().isEmpty("data://video_viewer_return_info")) {
            new VuLauncher(this.mBlackboard).disableBitmapRequest().launch(getViewerLocationKey(), i2, mediaItem);
        } else {
            new VuLauncher(this.mBlackboard).requestBitmapUrgent().launch(getViewerLocationKey(), i2, mediaItem);
        }
        removeOtherTabs();
        postAnalyticsLog(AnalyticsEventId.EVENT_TOUCH_THUMBNAIL, AnalyticsScreenLabel.SCREEN_LABEL_SINGLE_TAKE.toString(), getPlayEventID(mediaItem));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_done) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (!isSupportedDone() || !completeSelectionMode()) {
            return false;
        }
        return true;
    }

    public boolean onPreparePopupMenu(Menu menu, int i2) {
        PopupMenuData createPopupMenuData = createPopupMenuData(menu, i2);
        if (createPopupMenuData == null) {
            return false;
        }
        this.mMenuDelegation.updatePopupMenu(createPopupMenuData, this.mSelectionListener, i2);
        return true;
    }

    public void onSelectionRequested(Object obj, Bundle bundle) {
        if (!isLocationKeyMatch()) {
            Log.e(this.TAG, "do not start selection mode");
            return;
        }
        String string = bundle.getString("_SUBSCRIBE_KEY");
        if (CommandKey.DATA_REQUEST("data://user/selection/ShareVia").equals(string)) {
            onShareViaSelectionRequested();
        } else if (CommandKey.DATA_REQUEST("data://user/selection/DeleteMotionPhotoVideoClip").equals(string)) {
            onDeleteMotionPhotoVideoClipSelectionRequested(bundle);
        } else {
            String DATA_REQUEST_REVERT = CommandKey.DATA_REQUEST_REVERT(string);
            String string2 = bundle.getString("title");
            int i2 = UnsafeCast.toInt(bundle.getString("maxCount"), -1);
            setSelectableChecker();
            enterSelectionMode(string2, i2, new H(0, (Object) this, (Object) DATA_REQUEST_REVERT));
        }
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        addOverScrollListener();
        this.mSuggestAppbarDelegate.init();
    }

    public void onViewDestroy() {
        this.mBixbyProxy = null;
        super.onViewDestroy();
    }

    public void onViewPause() {
        super.onViewPause();
        stopAllPreview(false);
    }

    public void onViewResume() {
        BaseListViewAdapter adapter;
        super.onViewResume();
        if (this.mPendingReopenFloatingRecommendation) {
            this.mPendingReopenFloatingRecommendation = false;
            moveToFloatingRecommendation();
        }
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW && (adapter = ((IBaseListView) this.mView).getAdapter()) != null && !this.mBlackboard.isEmpty("lifecycle://on_thumbnail_load_done") && !((IBaseListView) this.mView).isViewHidden() && adapter.isPreviewAvailable()) {
            adapter.checkPreviewCandidate(600);
        }
    }

    public void operateClipboard(Object obj, Bundle bundle) {
        ((IBaseListView) this.mView).operateClipboard(((Boolean) obj).booleanValue());
    }

    public void prepareBottomMenu(Menu menu) {
        this.mMenuDelegation.updateBottomBarMenuAction(menu);
    }

    public void prepareExtendedShare() {
        ((IBaseListView) this.mView).prepareExtendedShare();
    }

    public void prepareOptionsMenu(Menu menu) {
        super.prepareOptionsMenu(menu);
        this.mMenuDelegation.updateOptionsMenu(menu, this.mSelectionListener);
    }

    public void removeOtherTabs() {
        String locationKey = getLocationKey();
        if (locationKey != null) {
            String removeArgs = ArgumentsUtil.removeArgs(locationKey);
            if (!LocationKey.isTimeline(removeArgs)) {
                if ("location://albums/fileList".equals(removeArgs)) {
                    removeArgs = "location://albums";
                } else {
                    removeArgs = LocationKey.isStoryPictures(removeArgs) ? "location://story/albums" : null;
                }
            }
            removeOtherTabs(removeArgs);
        }
    }

    public void selectOneItemOnEnterSelection() {
        if (PickerUtil.isNormalLaunchMode(this.mBlackboard) && ((IBaseListView) this.mView).getDataCount() == 1) {
            BaseListViewAdapter adapter = ((IBaseListView) this.mView).getAdapter();
            if (adapter == null) {
                Log.w(this.TAG, "adapter is null");
            } else if (adapter.isItemSelectable(adapter.getViewPosition(0)) == GalleryListAdapter.SelectableType.SUPPORT) {
                ((IBaseListView) this.mView).selectAll();
            }
        }
    }

    public void setOnSelectionListener(EventContext.OnSelectionListener onSelectionListener) {
        this.mSelectionListener = onSelectionListener;
    }

    public boolean showDeleteAllWarning() {
        return false;
    }

    public void stopAllPreview(boolean z) {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            Optional.ofNullable(((IBaseListView) this.mView).getAdapter()).ifPresent(new L(z, 0));
        }
    }

    public void updateKnoxMenuVisibility() {
        if (isSelectionMode()) {
            super.updateKnoxMenuVisibility();
        }
    }

    public void updateSelectedItem(EventMessage eventMessage) {
        boolean z;
        BaseListViewAdapter adapter = ((IBaseListView) this.mView).getAdapter();
        if (adapter == null) {
            Log.e(this.TAG, "updateSelectedItem failed. adapter not ready");
            return;
        }
        if (eventMessage.arg1 == 1) {
            z = true;
        } else {
            z = false;
        }
        adapter.selectItemWithSync(adapter.getViewPosition(eventMessage.arg2), z, true);
    }

    public void enterSelectionMode(int i2) {
        GalleryListView listView = ((IBaseListView) this.mView).getListView();
        if (!((IBaseListView) this.mView).isViewActive() || listView == null) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "view is null or fragment inactive = " + listView);
            return;
        }
        ((IBaseListView) this.mView).enterSelectionMode(i2);
        selectOneItemOnEnterSelection();
    }

    public void removeOtherTabs(String str) {
        if (str != null) {
            this.mBlackboard.publish("command://RemoveSiblingsFragments", new String[]{ArgumentsUtil.removeArgs(str)});
        }
    }

    public void handleDensityChange() {
    }

    public void onEnterMoveMode() {
    }

    public void onExitMoveMode() {
    }

    public void updateViewHolderMargin() {
    }

    public void notifyDataChanged(MediaData mediaData) {
    }

    public void onBottomOverScroll(int i2) {
    }

    public void onDrawerSizeChanged(int i2) {
    }

    public void onTopOverScroll(int i2) {
    }

    public void onRequestBeamData(Object obj, Bundle bundle) {
    }
}
