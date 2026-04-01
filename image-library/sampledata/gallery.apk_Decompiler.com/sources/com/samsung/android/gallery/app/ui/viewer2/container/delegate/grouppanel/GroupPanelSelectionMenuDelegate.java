package com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel;

import A4.C0373h;
import Fa.C0571z;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.fastoption.SingleTakeSelectionMenuHandler;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenLabel;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h4.C0464a;
import i.C0212a;
import java.util.ArrayList;
import java.util.function.Predicate;
import n7.c;
import n7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelSelectionMenuDelegate extends AbsVuDelegate<IVuContainerView> implements FastOptionItemView.ItemSelectedListener, EventContext {
    private int mBestItemIndex = ArgumentsUtil.getArgValue(getLocationKey(), "bestId", 0);
    private FastOptionView mFastOptionView;
    private boolean mIsBestItemSelected = false;
    private SingleTakeSelectionMenuHandler mMenuHandler;
    private PopupMenu mPopupMenu;
    private ArrayList<MediaItem> mSelectedItems = new ArrayList<>();

    public GroupPanelSelectionMenuDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void allItemSelected(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        this.mSelectedItems.clear();
        boolean z = true;
        if (booleanValue) {
            this.mSelectedItems = objArr[1];
        }
        boolean anyMatch = this.mSelectedItems.stream().anyMatch(new C0464a(18));
        if (!booleanValue || !anyMatch) {
            z = false;
        }
        this.mIsBestItemSelected = z;
        if (!ViewUtils.isVisible(this.mFastOptionView) && isSupported(((ContainerModel) this.mModel).isSelectMode(), ((ContainerModel) this.mModel).isTableMode())) {
            ViewUtils.setVisibility(this.mFastOptionView, 0);
        }
        updateDim();
    }

    private void createPopupMenu(MediaItem mediaItem, MotionEvent motionEvent) {
        ViewGroup viewGroup = (ViewGroup) this.mFastOptionView.getRootView();
        View createAnchorViewInEventPosition = ViewUtils.createAnchorViewInEventPosition(viewGroup, motionEvent);
        this.mPopupMenu = new PopupMenu(getContext(), createAnchorViewInEventPosition);
        getMenuHandler().addPopupMenu(this.mPopupMenu.getMenu(), mediaItem);
        this.mPopupMenu.setOnMenuItemClickListener(new C0373h(1, this, mediaItem));
        this.mPopupMenu.setOnDismissListener(new d(viewGroup, createAnchorViewInEventPosition));
        this.mPopupMenu.show();
    }

    private SingleTakeSelectionMenuHandler getMenuHandler() {
        if (this.mMenuHandler == null) {
            SingleTakeSelectionMenuHandler singleTakeSelectionMenuHandler = new SingleTakeSelectionMenuHandler(this, this.mActionInvoker, this.mFastOptionView);
            this.mMenuHandler = singleTakeSelectionMenuHandler;
            singleTakeSelectionMenuHandler.removeUnsupportedMenu(hasCloudOnly(), hasPrivateItem());
        }
        return this.mMenuHandler;
    }

    private boolean hasCloudOnly() {
        return ((ContainerModel) this.mModel).getMediaData().getAllData().stream().anyMatch(new C0571z(5));
    }

    private boolean hasPrivateItem() {
        return ((ContainerModel) this.mModel).getMediaData().getAllData().stream().anyMatch(new C0464a(16));
    }

    private void initFastOptionView() {
        getMenuHandler().addFastOptionViewItem();
    }

    private boolean isBestItemDim() {
        if (!supportChangeBestItem()) {
            return false;
        }
        if (this.mSelectedItems.size() != 1 || this.mIsBestItemSelected) {
            return true;
        }
        return false;
    }

    private boolean isSupported(boolean z, boolean z3) {
        if (!z) {
            return false;
        }
        if (!z3 || !((IVuContainerView) this.mView).isLandscape()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$allItemSelected$2(MediaItem mediaItem) {
        if (mediaItem.getBestImage() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createPopupMenu$0(MediaItem mediaItem, MenuItem menuItem) {
        this.mSelectedItems.add(mediaItem);
        getMenuHandler().onItemSelected(menuItem.getItemId(), (View) null);
        this.mSelectedItems.clear();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getBestItem$3(MediaItem mediaItem) {
        if (mediaItem.getBestImage() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void listItemSelected(Object... objArr) {
        MediaItem mediaItem = objArr[0];
        boolean booleanValue = objArr[1].booleanValue();
        if (booleanValue) {
            this.mSelectedItems.add(mediaItem);
        } else {
            this.mSelectedItems.remove(mediaItem);
        }
        if (mediaItem.getBestImage() == 1) {
            this.mIsBestItemSelected = booleanValue;
        }
        updateDim();
    }

    /* access modifiers changed from: private */
    public void onPrepareShareSheet(Object... objArr) {
        if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
            MediaItem[] allItems = getAllItems();
            MediaItem[] mediaItemArr = new MediaItem[allItems.length];
            for (int i2 = 0; i2 < allItems.length; i2++) {
                MediaItem clone = allItems[i2].clone();
                mediaItemArr[i2] = clone;
                clone.setCount(1);
            }
            ShareProvider.prepareExtendedShareList(((IVuContainerView) this.mView).getContext(), this.mBlackboard, mediaItemArr, (Runnable) null);
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public void onSecondaryClicked(Object... objArr) {
        if (!isSelectionMode()) {
            MediaItem mediaItem = objArr[0];
            Log.d(this.TAG, "onSecondaryClicked", mediaItem);
            createPopupMenu(mediaItem, objArr[1]);
        }
    }

    /* access modifiers changed from: private */
    public void onSelectModeChanged(Object... objArr) {
        int i2 = 0;
        boolean booleanValue = objArr[0].booleanValue();
        boolean isSupported = isSupported(booleanValue, ((ContainerModel) this.mModel).isTableMode());
        C0212a.x("onSelectModeChanged - isSelectMode: ", this.TAG, booleanValue);
        this.mIsBestItemSelected = false;
        if (isSupported) {
            initFastOptionView();
        } else {
            resetFastOption();
            this.mSelectedItems.clear();
        }
        FastOptionView fastOptionView = this.mFastOptionView;
        if (!isSupported) {
            i2 = 8;
        }
        ViewUtils.setVisibility(fastOptionView, i2);
        updateDim();
    }

    private void resetFastOption() {
        FastOptionView fastOptionView = this.mFastOptionView;
        if (fastOptionView != null && fastOptionView.getChildCount() > 0) {
            this.mFastOptionView.clear();
        }
        this.mMenuHandler = null;
    }

    private boolean supportChangeBestItem() {
        return Features.isEnabled(Features.SUPPORT_CHANGE_BEST_ITEM);
    }

    private void updateDim() {
        getMenuHandler().updateDim(isBestItemDim(), this.mSelectedItems.size());
    }

    public Activity getActivity() {
        return ((IVuContainerView) this.mView).getActivity();
    }

    public MediaItem[] getAllItems() {
        MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
        if (mediaData == null || mediaData.getCount() <= 0) {
            return new MediaItem[0];
        }
        return (MediaItem[]) mediaData.getAllData().toArray(new MediaItem[0]);
    }

    public Context getApplicationContext() {
        return ((IVuContainerView) this.mView).getApplicationContext();
    }

    public MediaItem getBestItem() {
        MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
        if (mediaData == null) {
            return null;
        }
        if (this.mBestItemIndex < 0) {
            this.mBestItemIndex = mediaData.findPositionBy((Predicate<MediaItem>) new C0464a(17));
        }
        int i2 = this.mBestItemIndex;
        if (i2 < 0 || i2 >= mediaData.getCount()) {
            return null;
        }
        return mediaData.getAllData().get(this.mBestItemIndex);
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public MediaItem getCurrentItem() {
        return ((ContainerModel) this.mModel).getCurrentMediaItem();
    }

    public String getLocationKey() {
        return ((IVuContainerView) this.mView).getLocationKey();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_SINGLE_TAKE_SELECT.toString();
    }

    public String getScreenLabel() {
        return AnalyticsScreenLabel.SCREEN_LABEL_SINGLE_TAKE.toString();
    }

    public int getSelectedItemCount() {
        return this.mSelectedItems.size();
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) this.mSelectedItems.toArray(new MediaItem[0]);
    }

    public boolean isSelectionMode() {
        return ((ContainerModel) this.mModel).isSelectMode();
    }

    public void onBindView(View view) {
        super.onBindView(view);
        FastOptionView fastOptionView = (FastOptionView) view.findViewById(R.id.fast_option_view);
        this.mFastOptionView = fastOptionView;
        fastOptionView.setItemSelectedListener(this);
        ViewUtils.setWidth(this.mFastOptionView, -2);
        FastOptionView fastOptionView2 = this.mFastOptionView;
        fastOptionView2.setElevation((float) fastOptionView2.getResources().getDimensionPixelSize(R.dimen.viewer_floating_fast_option_elevation));
        FastOptionView fastOptionView3 = this.mFastOptionView;
        ViewMarginUtils.setTopMargin(fastOptionView3, fastOptionView3.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_top_margin));
        FastOptionView fastOptionView4 = this.mFastOptionView;
        ViewMarginUtils.setBottomMargin(fastOptionView4, fastOptionView4.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_bottom_margin));
        this.mFastOptionView.updatePadding();
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    public void onItemSelected(int i2, View view) {
        getMenuHandler().onItemSelected(i2, view);
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageInvalidate(i2, viewerObjectComposite);
        this.mBestItemIndex = -1;
    }

    public void onTableModeChanged(boolean z, int i2) {
        int i7;
        super.onTableModeChanged(z, i2);
        boolean isSupported = isSupported(((ContainerModel) this.mModel).isSelectMode(), z);
        if (isSupported && this.mFastOptionView.getChildCount() == 0) {
            initFastOptionView();
            updateDim();
        }
        FastOptionView fastOptionView = this.mFastOptionView;
        if (isSupported) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        ViewUtils.setVisibility(fastOptionView, i7);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CHANGED, new c(this, 0));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_ITEM_SELECTED, new c(this, 1));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SELECT_ALL, new c(this, 2));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CLICKED, new c(this, 0));
        actionInvoker.add(ViewerAction.PREPARE_SINGLE_TAKEN_SHARE_SHEET, new c(this, 3));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SECONDARY_CLICK, new c(this, 4));
    }

    public void subscribeInstant(String str, InstantSubscriberListener instantSubscriberListener) {
        EventContext eventContext = ((ContainerModel) this.mModel).getEventContext();
        if (eventContext != null) {
            eventContext.subscribeInstant(str, instantSubscriberListener);
        }
    }
}
