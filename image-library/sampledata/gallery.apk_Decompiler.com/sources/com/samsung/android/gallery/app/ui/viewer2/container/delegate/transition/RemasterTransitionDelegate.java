package com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition;

import B7.d;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;
import com.samsung.android.gallery.widget.remaster.RemasterLayoutFactory;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterTransitionDelegate extends TransitionDelegate {
    public RemasterTransitionDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private int getRemasterPhotoBottomMargin(int i2, int i7) {
        AbstractRemasterLayout create = new RemasterLayoutFactory().create(((IVuContainerView) this.mView).getActivity(), (ViewGroup) ((IVuContainerView) this.mView).getView());
        create.updateAttrs();
        return create.getBottomMargin(i2, i7, ((IVuContainerView) this.mView).isLandscape());
    }

    private String getTransitionName(TransitionInfo transitionInfo) {
        if (LocationKey.isRemasterSingle(((IVuContainerView) this.mView).getLocationKey())) {
            return SharedTransition.getTransitionName("remaster/", transitionInfo.item);
        }
        return SharedTransition.getTransitionName(transitionInfo.item);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReadyRemasterView$0() {
        ViewUtils.setVisibleOrGone(this.mPreview, false);
    }

    /* access modifiers changed from: private */
    public void onReadyRemasterView(Object... objArr) {
        ThreadUtil.postOnUiThread(new t(24, this));
    }

    public int getEnterSharedTransitionResourceId() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS || !LocationKey.isRemasterSingle(((IVuContainerView) this.mView).getLocationKey())) {
            return super.getEnterSharedTransitionResourceId();
        }
        return R.transition.oneui60_ondemand_remaster_enter_transition;
    }

    public MarginParams getPhotoPreViewMargin() {
        MarginParams marginParams = new MarginParams();
        if (((IVuContainerView) this.mView).isInMultiWindowMode() || !((ContainerModel) this.mModel).isTableMode()) {
            View view = ((IVuContainerView) this.mView).getView();
            if (view != null) {
                View rootView = view.getRootView();
                marginParams.bottomMargin = getRemasterPhotoBottomMargin(rootView.getWidth(), rootView.getHeight());
            }
            return marginParams;
        }
        marginParams.bottomMargin = DeviceInfo.getDisplayHeight(((IVuContainerView) this.mView).getContext()) / 2;
        return marginParams;
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        this.mActionInvoker.add(ViewerAction.ON_READY_REMASTER_VIEW, new d(29, this));
    }

    public boolean startPreview(TransitionInfo transitionInfo) {
        if (transitionInfo == null || transitionInfo.item == null) {
            Log.st(this.TAG, "no transition info");
            return false;
        }
        SharedTransition.setTransitionName(this.mPreview, getTransitionName(transitionInfo));
        updateItemSizeInfo(transitionInfo.item);
        this.mPreview.setScaledTransitionInfo(transitionInfo.scale, transitionInfo.point);
        this.mPreview.setLogTag(transitionInfo.dataPosition);
        this.mPreview.setBasicInfo(transitionInfo.bitmap, transitionInfo.item, getPhotoPreViewMargin());
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_PV);
        transitionInfo.recycle();
        startPostponedEnterTransition();
        return true;
    }

    public void onEnterTransitionEnd() {
    }

    public void onResume() {
    }
}
