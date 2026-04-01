package com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition;

import A4.C0385u;
import B7.d;
import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelTransitionDelegate extends TransitionDelegate {
    public GroupPanelTransitionDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReadyView$0() {
        ViewUtils.setVisibleOrGone(this.mPreview, false);
    }

    /* access modifiers changed from: private */
    public void onReadyView(Object... objArr) {
        ThreadUtil.postOnUiThread(new t(23, this));
    }

    /* access modifiers changed from: private */
    public boolean supportGroupPanelLandscapeMode() {
        return ((ContainerModel) this.mModel).getStateHelper().supportGroupPanelLandscapeMode();
    }

    public MarginParams getPhotoPreViewMargin() {
        int i2;
        MarginParams marginParams = new MarginParams();
        if (((IVuContainerView) this.mView).isInMultiWindowMode() || !((ContainerModel) this.mModel).isTableMode()) {
            View view = ((IVuContainerView) this.mView).getView();
            if (view != null) {
                View rootView = view.getRootView();
                if (supportGroupPanelLandscapeMode()) {
                    if (((ContainerModel) this.mModel).getStateHelper().isTargetViewHalfOfWidth(ResourceCompat.isLandscape((Context) ((IVuContainerView) this.mView).getActivity()))) {
                        i2 = rootView.getWidth() / 2;
                    } else {
                        i2 = rootView.getWidth() - rootView.getHeight();
                    }
                    marginParams.rightMargin = i2;
                    marginParams.bottomMargin = 0;
                    marginParams.topMargin = 0;
                    return marginParams;
                }
                marginParams.rightMargin = 0;
                marginParams.bottomMargin = ((ContainerModel) this.mModel).getOverlaySize().bottom;
                marginParams.topMargin = SystemUi.getToolBarHeightWithPadding(((IVuContainerView) this.mView).getContext()) + ((ContainerModel) this.mModel).getSystemUi().getStatusBarHeight(((IVuContainerView) this.mView).getActivity());
            }
            return marginParams;
        }
        marginParams.bottomMargin = DeviceInfo.getDisplayHeight(((IVuContainerView) this.mView).getContext()) / 2;
        return marginParams;
    }

    public void onEnterTransitionEnd() {
        onReadyView(new Object[0]);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new d(28, this));
    }

    public boolean startPreview(TransitionInfo transitionInfo) {
        if (transitionInfo == null || transitionInfo.item == null) {
            Log.st(this.TAG, "no transition info");
            return false;
        }
        SharedTransition.setTransitionName(this.mPreview, "groupPanel/");
        updateItemSizeInfo(transitionInfo.item);
        this.mPreview.setCenterCrop(new C0385u(25, this));
        this.mPreview.setScaledTransitionInfo(transitionInfo.scale, transitionInfo.point);
        this.mPreview.setLogTag(transitionInfo.dataPosition);
        this.mPreview.setBasicInfo(transitionInfo.bitmap, transitionInfo.item, getPhotoPreViewMargin());
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_PV);
        transitionInfo.recycle();
        startPostponedEnterTransition();
        return true;
    }
}
