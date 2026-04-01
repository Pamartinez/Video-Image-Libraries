package com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel;

import A4.C0385u;
import a6.C0419b;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelListAdapter;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelListAdapterListener;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelListViewTouchListener;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.SingleTakenListView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.toolbar.OnSelectAllListener;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import k6.b;
import m7.c;
import n7.C0494a;
import n7.C0495b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupItemPanelDelegate extends AbsVuDelegate<IVuContainerView> implements GroupPanelListAdapterListener {
    private final ActionInvokeListener mBackKeyListener = new C0495b(this, 3);
    private boolean mBackKeyListenerRegistered = false;
    private FrameLayout mCancelButton;
    private final View.OnClickListener mCancelButtonClickListener = new C0419b(26, this);
    protected LinearLayout mContentContainer;
    protected RelativeLayout mGroupItemPanelLayout;
    protected ViewStub mGroupItemPanelStub;
    /* access modifiers changed from: private */
    public GroupPanelListAdapter mGroupPanelListAdapter;
    private ViewGroup mHeaderContainer;
    private SingleTakenListView mListView;
    private final GroupPanelListViewTouchListener mOnDelegateTouchListener = new GroupPanelListViewTouchListener();
    public final OnSelectAllListener mSelectAllListener = new OnSelectAllListener() {
        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onSelectAll$0(MediaItem mediaItem, MediaItem mediaItem2) {
            if (mediaItem2.getFileId() == mediaItem.getFileId()) {
                return true;
            }
            return false;
        }

        public void onSelectAll() {
            if (GroupItemPanelDelegate.this.mGroupPanelListAdapter != null) {
                GroupItemPanelDelegate.this.mGroupPanelListAdapter.selectAll();
                ArrayList arrayList = new ArrayList(GroupItemPanelDelegate.this.mGroupPanelListAdapter.getAllMediaItems());
                ((IVuContainerView) GroupItemPanelDelegate.this.mView).getToolbar().setSelectedCountInfo(arrayList.size(), arrayList.size(), -1);
                MediaItem[] selectedItems = ((ContainerModel) GroupItemPanelDelegate.this.mModel).getSelectedItems();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    MediaItem mediaItem = (MediaItem) it.next();
                    boolean anyMatch = Arrays.stream(selectedItems).anyMatch(new a(mediaItem));
                    if (selectedItems.length == 0 || !anyMatch) {
                        ((ContainerModel) GroupItemPanelDelegate.this.mModel).addSelectedItem(mediaItem);
                    }
                }
                GroupItemPanelDelegate.this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SELECT_ALL, Boolean.TRUE, arrayList);
                GroupItemPanelDelegate.this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            }
        }

        public void onUnSelectAll() {
            if (GroupItemPanelDelegate.this.mGroupPanelListAdapter != null) {
                GroupItemPanelDelegate.this.mGroupPanelListAdapter.unSelectAll();
                ((IVuContainerView) GroupItemPanelDelegate.this.mView).getToolbar().setSelectedCountInfo(0, 0, -1);
                ((ContainerModel) GroupItemPanelDelegate.this.mModel).clearSelectedItems();
                GroupItemPanelDelegate.this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SELECT_ALL, Boolean.FALSE, null);
                GroupItemPanelDelegate.this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            }
        }
    };

    public GroupItemPanelDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        this.mActionInvoker.addExclusive(ViewerAction.TOGGLE_OSD, ActionInvokeListener.EMPTY_LISTENER, this.TAG);
    }

    private int findIndexOf(MediaItem mediaItem) {
        return ((ContainerModel) this.mModel).getMediaData().findPositionByFileId(mediaItem.getFileId());
    }

    private int getHeaderHeight(Resources resources) {
        int i2;
        int i7;
        if (LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
            i2 = R.dimen.group_panel_ai_edit_header_height;
        } else {
            i2 = R.dimen.group_panel_header_height;
        }
        if (LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
            i7 = R.dimen.group_panel_header_ai_edit_height_multi_window;
        } else {
            i7 = R.dimen.group_panel_header_height_multi_window;
        }
        if (((IVuContainerView) this.mView).isInMultiWindowMode()) {
            i2 = i7;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    private int getHorizontalPaddingResId() {
        if (SystemUi.isInMultiWindowMode((Activity) ((IVuContainerView) this.mView).getActivity())) {
            return R.dimen.group_panel_list_horizontal_padding_for_mw;
        }
        if (Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD)) {
            return R.dimen.group_panel_list_horizontal_padding_for_fold;
        }
        return R.dimen.group_panel_list_horizontal_padding;
    }

    private int getPanelLeftMargin() {
        int height;
        int paddingLeft;
        View view = (View) this.mGroupItemPanelLayout.getParent();
        if (((ContainerModel) this.mModel).isTableMode()) {
            return 0;
        }
        if (((ContainerModel) this.mModel).getStateHelper().isTargetViewHalfOfWidth(supportGroupPanelLandscapeMode())) {
            height = (((IVuContainerView) this.mView).getView().getWidth() / 2) - view.getLeft();
            paddingLeft = view.getPaddingLeft();
        } else if (!supportGroupPanelLandscapeMode()) {
            return 0;
        } else {
            height = ((IVuContainerView) this.mView).getView().getHeight() - view.getLeft();
            paddingLeft = view.getPaddingLeft();
        }
        return height - paddingLeft;
    }

    private void initFocusedPosition() {
        if (((ContainerModel) this.mModel).getPosition() != -1) {
            int position = ((ContainerModel) this.mModel).getPosition();
            if (isInvalidateThumbnail(((ContainerModel) this.mModel).getCurrentMediaItem(), position)) {
                this.mGroupPanelListAdapter.updateFocusedItemThumbnail();
            }
            this.mGroupPanelListAdapter.setFocusedPosition(position);
        }
    }

    private void initHeader(View view) {
        if (LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
            if (LocationKey.isSuperSlowGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
                updateHeaderViewResource(view, R.string.super_slow_mo, R.drawable.gallery_ic_header_superslow);
            } else if (LocationKey.isHighlightGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
                updateHeaderViewResource(view, R.string.highlights, R.drawable.gallery_ic_header_video_highlights);
            }
        }
        if (LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
            updateHeaderViewResource(view, R.string.single_take, R.drawable.gallery_ic_search_single_take);
            if (((ContainerModel) this.mModel).getMediaData() != null) {
                updateCountView(((ContainerModel) this.mModel).getMediaData().getCount());
            }
        }
    }

    private void initListAdapter() {
        if (this.mGroupPanelListAdapter == null) {
            GroupPanelListAdapter groupPanelListAdapter = new GroupPanelListAdapter(this.mBlackboard, ((IVuContainerView) this.mView).getEventContext(), this.mListView, this.mActionInvoker);
            this.mGroupPanelListAdapter = groupPanelListAdapter;
            groupPanelListAdapter.setAdapterListener(this);
            GroupPanelListAdapter groupPanelListAdapter2 = this.mGroupPanelListAdapter;
            ContainerModel containerModel = (ContainerModel) this.mModel;
            Objects.requireNonNull(containerModel);
            groupPanelListAdapter2.setTableModeSupplier(new C0385u(20, containerModel));
            this.mListView.setAdapter(this.mGroupPanelListAdapter);
            this.mListView.setOnDelegateTouchListener(this.mOnDelegateTouchListener);
            updateLayoutManager();
        }
        this.mGroupPanelListAdapter.setDataList(((ContainerModel) this.mModel).getMediaData().getAllData());
        initFocusedPosition();
    }

    private boolean isDetailsClosed() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || !BottomSheetState.Details.isClosed(currentViewer.getModel())) {
            return false;
        }
        return true;
    }

    private boolean isInvalidateThumbnail(MediaItem mediaItem, int i2) {
        MediaItem mediaItemSync = this.mGroupPanelListAdapter.getMediaItemSync(i2);
        if (mediaItemSync == null || mediaItem == null) {
            return false;
        }
        if (MediaItemUtil.isDifferentRemasterStatus(mediaItemSync, mediaItem) || MediaItemUtil.isDifferentPortraitsStatus(mediaItemSync, mediaItem)) {
            return true;
        }
        return false;
    }

    private boolean isLandScapeTableMode() {
        if (!((ContainerModel) this.mModel).isTableMode() || !((IVuContainerView) this.mView).isLandscape()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6(Object[] objArr) {
        if (((ContainerModel) this.mModel).isSelectMode()) {
            this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CLICKED, Boolean.FALSE);
            this.mGroupPanelListAdapter.exitSelect(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8(View view) {
        if (!((ContainerModel) this.mModel).isSelectMode()) {
            this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$2() {
        updateLayoutManager();
        updateLayout();
        playPreview();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTableModeChanged$3() {
        updateLayoutManager();
        updateLayout();
        playPreview();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1() {
        ViewUtils.post(this.mGroupItemPanelLayout, new C0494a(this, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        updateMainLayoutBg();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showGroupPanelWithAnim$5() {
        ViewUtils.setVisibility(this.mGroupItemPanelLayout, 0);
        Interpolator create = PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60);
        PropertyAnimator duration = new AlphaAnimator((View) this.mGroupItemPanelLayout, 0.0f, 1.0f).setInterpolator(create).setDuration(200);
        PropertyAnimator duration2 = new ScaleAnimator((View) this.mGroupItemPanelLayout, 0.6f, 1.0f).setInterpolator(create).setDuration(200);
        duration.start();
        duration2.start();
    }

    private void playPreview() {
        SingleTakenListView singleTakenListView = this.mListView;
        if (singleTakenListView != null) {
            singleTakenListView.post(new b(21, singleTakenListView));
        }
    }

    private void showGroupPanelWithAnim() {
        ViewUtils.setVisibility(this.mGroupItemPanelLayout, 4);
        ThreadUtil.postOnUiThreadDelayed(new C0494a(this, 2), 300);
    }

    private boolean supportGroupPanelLandscapeMode() {
        return ((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode();
    }

    private boolean supportHandle() {
        if (!LocationKey.isAiEditGroupPanelViewer(((IVuContainerView) this.mView).getLocationKey())) {
            return false;
        }
        if (LocationKey.isSuperSlowGroupPanelView(((IVuContainerView) this.mView).getLocationKey()) || LocationKey.isHighlightGroupPanelView(((IVuContainerView) this.mView).getLocationKey())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void updateAlphaByDecorView(Object... objArr) {
        float floatValue = objArr[0].floatValue();
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && !BottomSheetState.Details.isClosed(currentViewer.getModel())) {
            ViewUtils.setAlpha(this.mGroupItemPanelLayout, floatValue);
        }
    }

    private void updateContentContainer() {
        int i2;
        int i7;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ViewUtils.getLayoutParams(this.mContentContainer, RelativeLayout.LayoutParams.class);
        if (layoutParams != null) {
            if (supportGroupPanelLandscapeMode()) {
                layoutParams.removeRule(2);
            } else {
                layoutParams.addRule(2, R.id.fast_option_view_container);
            }
            if (supportGroupPanelLandscapeMode()) {
                i2 = -1;
            } else {
                i2 = -2;
            }
            layoutParams.height = i2;
            if (supportGroupPanelLandscapeMode()) {
                i7 = SystemUi.getToolBarHeight(getContext());
            } else {
                i7 = 0;
            }
            layoutParams.topMargin = i7;
            this.mContentContainer.setLayoutParams(layoutParams);
        }
    }

    private void updateCountView(int i2) {
        ViewGroup viewGroup = this.mHeaderContainer;
        if (viewGroup != null) {
            ViewUtils.setText((TextView) viewGroup.findViewById(R.id.group_panel_count_view), StringCompat.toReadableCount(i2));
            ViewUtils.setVisibility(this.mHeaderContainer.findViewById(R.id.group_panel_count_view), 0);
        }
    }

    private void updateHeaderContainer() {
        int i2;
        ViewGroup viewGroup = this.mHeaderContainer;
        if (supportGroupPanelLandscapeMode() || isLandScapeTableMode()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        ViewUtils.setVisibility(viewGroup, i2);
        ViewUtils.setHeight(this.mHeaderContainer, getHeaderHeight(this.mGroupItemPanelLayout.getResources()));
    }

    private void updateHeaderViewResource(View view, int i2, int i7) {
        String str;
        View findViewById = view.findViewById(R.id.group_panel_title_container);
        ViewUtils.setVisibleOrGone(findViewById, true);
        ((TextView) findViewById.findViewById(R.id.header_title)).setText(i2);
        ((ImageView) findViewById.findViewById(R.id.header_icon)).setImageResource(i7);
        boolean supportHandle = supportHandle();
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.handle_icon), supportHandle);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.cancel_button), !supportHandle);
        View findViewById2 = view.findViewById(R.id.group_panel_header_container);
        if (supportHandle) {
            str = view.getResources().getString(R.string.collapsed);
        } else {
            str = "";
        }
        ViewUtils.setContentDescription(findViewById2, str);
    }

    /* access modifiers changed from: private */
    public void updateLayout() {
        int i2;
        updatePanelLayout();
        updateContentContainer();
        SingleTakenListView singleTakenListView = this.mListView;
        if (supportGroupPanelLandscapeMode()) {
            i2 = this.mGroupItemPanelLayout.getResources().getDimensionPixelOffset(R.dimen.fast_menu_imageview_height);
        } else {
            i2 = 0;
        }
        ViewMarginUtils.setBottomPadding(singleTakenListView, i2);
        updateHeaderContainer();
        updateListContainerPadding();
        updateMainLayoutBg();
        Optional.ofNullable(((IVuContainerView) this.mView).getToolbar()).ifPresent(new c(16));
    }

    private void updateLayoutManager() {
        if (supportGroupPanelLandscapeMode()) {
            this.mListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else {
            this.mListView.setLayoutManager(new LinearLayoutManager(((IVuContainerView) this.mView).getContext(), 0, false));
        }
        this.mListView.getRecycledViewPool().clear();
        this.mGroupPanelListAdapter.notifyDataSetChanged();
    }

    private void updateListContainerPadding() {
        View view = (View) this.mListView.getParent();
        int dimensionPixelSize = ResourceCompat.getDimensionPixelSize(view, getHorizontalPaddingResId(), 0);
        ViewMarginUtils.setLeftPadding(view, dimensionPixelSize);
        ViewMarginUtils.setRightPadding(view, dimensionPixelSize);
    }

    private void updateMainLayoutBg() {
        View view = ((IVuContainerView) this.mView).getView();
        if (view == null) {
            return;
        }
        if (!supportGroupPanelLandscapeMode() || !BottomSheetState.isClosed((BottomSheetState.StateListener) this.mModel)) {
            ViewUtils.setBackground(view.findViewById(R.id.main_layout), (Drawable) null);
        } else {
            ViewUtils.setBackgroundColor(view.findViewById(R.id.main_layout), getContext().getColor(R.color.single_taken_list_bg_color));
        }
    }

    private void updatePanelLayout() {
        int i2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ViewUtils.getLayoutParams(this.mGroupItemPanelLayout, RelativeLayout.LayoutParams.class);
        if (layoutParams != null) {
            if (supportGroupPanelLandscapeMode()) {
                layoutParams.removeRule(2);
            } else {
                layoutParams.addRule(2, R.id.bottom_control);
            }
            if (supportGroupPanelLandscapeMode()) {
                i2 = -1;
            } else {
                i2 = -2;
            }
            layoutParams.height = i2;
            layoutParams.leftMargin = getPanelLeftMargin();
            this.mGroupItemPanelLayout.setLayoutParams(layoutParams);
        }
    }

    private void updateSelectionModeToolbar(boolean z) {
        int i2;
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar == null) {
            Log.w(this.TAG, "failed to update selection mode toolbar");
            return;
        }
        boolean z3 = false;
        if (z) {
            GroupPanelSelectionMenuDelegate groupPanelSelectionMenuDelegate = (GroupPanelSelectionMenuDelegate) getDelegate(GroupPanelSelectionMenuDelegate.class);
            toolbar.enterSelectionMode(this.mSelectAllListener, -1, true, false);
            toolbar.showSelectAll(true);
            if (groupPanelSelectionMenuDelegate != null) {
                i2 = groupPanelSelectionMenuDelegate.getSelectedItemCount();
            } else {
                i2 = 1;
            }
            toolbar.setSelectedCountInfo(i2, ((ContainerModel) this.mModel).getMediaData().getCount(), -1);
            ViewUtils.post(toolbar, new b(20, toolbar));
        } else {
            toolbar.exitSelectionMode(false);
        }
        if (!z || isLandScapeTableMode()) {
            z3 = true;
        }
        updateToolbarMenuVisibility(z3);
    }

    private void updateToolbarMenuVisibility(boolean z) {
        if (((IVuContainerView) this.mView).getToolbar() != null) {
            Menu menu = ((IVuContainerView) this.mView).getToolbar().getMenu();
            for (int i2 = 0; i2 < menu.size(); i2++) {
                menu.getItem(i2).setVisible(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateVisibility(Object... objArr) {
        int i2 = 0;
        int intValue = objArr[0].intValue();
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && BottomSheetState.Details.isExpanded(currentViewer.getModel())) {
            intValue = 4;
        } else if (currentViewer != null && BottomSheetState.Details.isClosed(currentViewer.getModel())) {
            if (!((ContainerModel) this.mModel).isOsdVisible()) {
                i2 = 4;
            }
            intValue = i2;
        }
        ViewUtils.setVisibility(this.mGroupItemPanelLayout, intValue);
    }

    public GalleryListView getListView() {
        return this.mListView;
    }

    public boolean isAvailableToPlay() {
        MediaItem currentMediaItem;
        if (!((ContainerModel) this.mModel).getStateHelper().isDecorViewEnabled() || !((IVuContainerView) this.mView).isViewResumed() || (currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem()) == null || !currentMediaItem.isLocal()) {
            return false;
        }
        return true;
    }

    public boolean isSupportSelectMode() {
        if (this.mOnDelegateTouchListener == null || LocationKey.isAiEditGroupPanelViewer(((ContainerModel) this.mModel).getLocationKey()) || !isDetailsClosed()) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.group_item_panel);
        this.mGroupItemPanelStub = viewStub;
        RelativeLayout relativeLayout = (RelativeLayout) ViewUtils.inflateViewStub(viewStub);
        this.mGroupItemPanelLayout = relativeLayout;
        this.mListView = (SingleTakenListView) relativeLayout.findViewById(R.id.single_taken_list_view);
        this.mHeaderContainer = (ViewGroup) view.findViewById(R.id.group_panel_header_container);
        this.mContentContainer = (LinearLayout) view.findViewById(R.id.group_panel_content_container);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.cancel_button);
        this.mCancelButton = frameLayout;
        frameLayout.setOnClickListener(this.mCancelButtonClickListener);
        initListAdapter();
        initHeader(view);
        ViewUtils.post(this.mGroupItemPanelLayout, new C0494a(this, 3));
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        ViewUtils.postOnGlobalLayout(this.mGroupItemPanelLayout, new C0494a(this, 0));
    }

    public void onExitSelectModeListener() {
        ((ContainerModel) this.mModel).setSelectMode(false);
        ((ContainerModel) this.mModel).clearSelectedItems();
        this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CHANGED, Boolean.FALSE);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool);
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, bool);
        if (this.mBackKeyListenerRegistered) {
            this.mActionInvoker.removeExclusive(ViewerAction.BACK_KEY_PRESSED, this.mBackKeyListener);
            this.mBackKeyListenerRegistered = false;
        }
        updateSelectionModeToolbar(false);
        if (!supportHandle()) {
            ViewUtils.setVisibility(this.mCancelButton, 0);
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1003) {
            if (i2 != 3032) {
                return super.onHandleEvent(eventMessage);
            }
            this.mActionInvoker.invoke(ViewerAction.SET_VIEWPAGER_POS, Integer.valueOf(findIndexOf((MediaItem) eventMessage.obj)), Boolean.FALSE);
        }
        GroupPanelListAdapter groupPanelListAdapter = this.mGroupPanelListAdapter;
        if (groupPanelListAdapter == null || !groupPanelListAdapter.isSelectionMode()) {
            return true;
        }
        this.mGroupPanelListAdapter.exitSelect(false);
        return true;
    }

    public void onItemClickListener(int i2, MediaItem mediaItem) {
        if (isDetailsClosed()) {
            this.mActionInvoker.invoke(ViewerAction.SET_VIEWPAGER_POS, Integer.valueOf(i2), Boolean.FALSE);
            playPreview();
        }
    }

    public void onMediaDataChanged(int i2, int i7) {
        MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
        if (mediaData == null || mediaData.getCount() <= 1) {
            this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            return;
        }
        this.mGroupPanelListAdapter.setDataList(mediaData.getAllData());
        updateCountView(mediaData.getCount());
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        try {
            this.mGroupPanelListAdapter.updateFocusedBorder(i2);
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
        this.mBlackboard.postEvent(EventMessage.obtain(3053, i2, (Object) null));
    }

    public void onResume() {
        playPreview();
    }

    public void onSelectedListener(int i2, MediaItem mediaItem, boolean z) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || currentViewer.getModel().isPppLoading()) {
            String str = this.TAG;
            Log.w(str, "skip to select item in group panel, " + currentViewer);
            return;
        }
        ((IVuContainerView) this.mView).getToolbar().setSelectedCountInfo(i2, ((ContainerModel) this.mModel).getMediaData().getCount(), -1);
        if (z) {
            ((ContainerModel) this.mModel).addSelectedItem(mediaItem);
        } else {
            ((ContainerModel) this.mModel).removeSelectedItem(mediaItem);
        }
        this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_ITEM_SELECTED, mediaItem, Boolean.valueOf(z));
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    public void onStartSelectModeListener() {
        ((ContainerModel) this.mModel).setSelectMode(true);
        this.mActionInvoker.invoke(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CHANGED, Boolean.TRUE);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY;
        Boolean bool = Boolean.FALSE;
        actionInvoker.invoke(viewerAction, bool);
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, bool);
        if (!this.mBackKeyListenerRegistered) {
            this.mActionInvoker.addExclusive(ViewerAction.BACK_KEY_PRESSED, this.mBackKeyListener, this.TAG);
            this.mBackKeyListenerRegistered = true;
        }
        updateSelectionModeToolbar(true);
        if (!supportHandle()) {
            ViewUtils.setVisibility(this.mCancelButton, 8);
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        ViewUtils.postOnGlobalLayout(this.mGroupItemPanelLayout, new C0494a(this, 1));
        if (ViewUtils.isVisible(this.mGroupItemPanelLayout) && ((IVuContainerView) this.mView).isLandscape()) {
            showGroupPanelWithAnim();
        }
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    public void onViewCreated(View view, Bundle bundle) {
        ViewUtils.postOnGlobalLayout(this.mGroupItemPanelLayout, new C0494a(this, 4));
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_ALPHA, new C0495b(this, 0));
        actionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_VISIBILITY, new C0495b(this, 1));
        actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new C0495b(this, 2));
    }
}
