package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.res.Configuration;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import v7.g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorLayoutHandler extends ViewerObject implements FragmentLifeCycle {
    ConstraintLayout mBottomCenterLayout;
    ConstraintLayout mBottomLayout;
    ConstraintLayout mFixedTopLayout;
    ConstraintLayout mLayout;
    ConstraintLayout mTopCenterLayout;

    /* access modifiers changed from: private */
    public void initView(Object... objArr) {
        ConstraintLayout constraintLayout = objArr[0];
        this.mLayout = constraintLayout;
        this.mTopCenterLayout = (ConstraintLayout) constraintLayout.findViewById(R.id.top_center_decor_layout);
        this.mFixedTopLayout = (ConstraintLayout) this.mLayout.findViewById(R.id.fixed_top_decor_layout);
        this.mBottomLayout = (ConstraintLayout) this.mLayout.findViewById(R.id.bottom_decor_layout);
        this.mBottomCenterLayout = (ConstraintLayout) this.mLayout.findViewById(R.id.bottom_center_decor_layout);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        onDetailsSlide(objArr[1].floatValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        updateVisibility(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        updateVisibility();
    }

    private void onDetailsSlide(float f) {
        ViewUtils.setAlpha(this.mLayout, Math.max(0.0f, Math.min(1.0f, 1.0f - (f * 2.0f))));
        updateVisibility();
    }

    /* access modifiers changed from: private */
    public void updateBottomLayoutVisibility(Object... objArr) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        boolean booleanValue = objArr[0].booleanValue();
        boolean booleanValue2 = objArr[1].booleanValue();
        boolean isFromExpand = LocationKey.isFromExpand(this.mModel.getContainerModel().getLocationKey());
        if (!booleanValue || BottomSheetState.Details.isExpanded(this.mModel.getContainerModel()) || (LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey()) && this.mModel.getContainerModel().isTableMode())) {
            z = false;
        } else {
            z = true;
        }
        if (!z || isFromExpand || !ViewUtils.isOpaque(this.mBottomLayout)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z && !isFromExpand && ViewUtils.isOpaque(this.mBottomCenterLayout)) {
            z7 = true;
        }
        if (booleanValue2) {
            if (z3) {
                SimpleAnimator.setAlphaVisible(this.mBottomLayout, 150);
            } else {
                SimpleAnimator.setAlphaOutVisible(this.mBottomLayout, 200);
            }
            if (z7) {
                SimpleAnimator.setAlphaVisible(this.mBottomCenterLayout, 150);
            } else {
                SimpleAnimator.setAlphaOutVisible(this.mBottomCenterLayout, 200);
            }
        } else {
            ViewUtils.setVisibleOrGone(this.mBottomLayout, z3);
            ViewUtils.setVisibleOrGone(this.mBottomCenterLayout, z7);
        }
    }

    private void updateLayout() {
        int i2;
        int i7;
        boolean isTableMode = this.mModel.getContainerModel().isTableMode();
        boolean isLandscape = ResourceCompat.isLandscape(this.mModel.getContext());
        int i8 = this.mModel.getContainerModel().getOverlaySize().top;
        int toolBarHeightWithPadding = SystemUi.getToolBarHeightWithPadding(this.mModel.getContext());
        ViewMarginUtils.setBottomPadding(this.mLayout, this.mLayout.getResources().getDimensionPixelOffset(R.dimen.play_button_view_bottom_margin) + this.mModel.getBottomOverlayHeight() + this.mModel.getContainerModel().getOverlaySize().bottom);
        int start = RectUtils.getStart(this.mModel.getContainerModel().getSystemInsets());
        if (this.mModel.getContainerModel().getStateHelper().supportGroupPanelLandscapeMode()) {
            i2 = 0;
        } else {
            i2 = RectUtils.getEnd(this.mModel.getContainerModel().getSystemInsets());
        }
        ViewMarginUtils.setHorizontalRelativeMargin(this.mFixedTopLayout, start, i2);
        ViewMarginUtils.setHorizontalRelativeMargin(this.mBottomLayout, start, i2);
        if (isTableMode) {
            i7 = (ViewUtils.getHeight(this.mModel.getContainerModel().getView()) / 2) + toolBarHeightWithPadding;
        } else {
            i7 = i8 + toolBarHeightWithPadding;
        }
        if (!isTableMode) {
            i8 += toolBarHeightWithPadding;
        }
        ConstraintLayout constraintLayout = this.mTopCenterLayout;
        if (isLandscape && !this.mModel.getSystemUi().isTabletDpi()) {
            i7 = toolBarHeightWithPadding;
        }
        ViewMarginUtils.setTopPadding(constraintLayout, i7);
        ConstraintLayout constraintLayout2 = this.mFixedTopLayout;
        if (!isLandscape || this.mModel.getSystemUi().isTabletDpi()) {
            toolBarHeightWithPadding = i8;
        }
        ViewMarginUtils.setTopPadding(constraintLayout2, toolBarHeightWithPadding);
    }

    private void updateVisibility() {
        updateVisibility(true);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new g(this, 0));
        this.mActionInvoker.add(ViewerAction.DETAILS_SLIDE, new g(this, 1));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY, new g(this, 2));
        this.mActionInvoker.add(ViewerAction.REQUEST_UPDATE_DECOR_LAYOUT_VISIBILITY, new g(this, 3));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIEWER_DECOR_BOTTOM_LAYOUT_VISIBILITY, new g(this, 4));
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout();
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateLayout();
        updateVisibility();
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateLayout();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        ViewUtils.setAlpha(this.mLayout, 1.0f);
    }

    private void updateVisibility(boolean z) {
        boolean isFromExpand = LocationKey.isFromExpand(this.mModel.getContainerModel().getLocationKey());
        boolean z3 = false;
        boolean z7 = z && this.mModel.getContainerModel().isOsdVisible() && (this.mModel.getStateHelper().isDecorViewEnabled() || isFromExpand) && !BlackboardUtils.isViewerShrinkToCamera(getBlackboard());
        boolean isExpanded = BottomSheetState.isExpanded(this.mModel.getContainerModel());
        boolean z9 = !BottomSheetState.Details.isExpanded(this.mModel.getContainerModel()) && (!LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey()) || !this.mModel.getContainerModel().isTableMode());
        ConstraintLayout constraintLayout = this.mLayout;
        ViewUtils.setVisibleOrGone(constraintLayout, z7 && ViewUtils.isOpaque(constraintLayout));
        ConstraintLayout constraintLayout2 = this.mTopCenterLayout;
        ViewUtils.setVisibleOrGone(constraintLayout2, !isExpanded && !isFromExpand && ViewUtils.isOpaque(constraintLayout2));
        ConstraintLayout constraintLayout3 = this.mFixedTopLayout;
        ViewUtils.setVisibleOrGone(constraintLayout3, !isExpanded && ViewUtils.isOpaque(constraintLayout3));
        ConstraintLayout constraintLayout4 = this.mBottomLayout;
        ViewUtils.setVisibleOrGone(constraintLayout4, z9 && !isFromExpand && ViewUtils.isOpaque(constraintLayout4));
        ConstraintLayout constraintLayout5 = this.mBottomCenterLayout;
        if (z9 && !isFromExpand && ViewUtils.isOpaque(constraintLayout5)) {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(constraintLayout5, z3);
    }
}
