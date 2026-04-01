package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A4.C0385u;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import k7.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemBarDelegate extends AbsVuDelegate<IVuContainerView> {
    boolean mNeedUpdateScreenModeOnPageSelected = false;
    private int mPrevDetailsState = 0;

    public SystemBarDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        ((ContainerModel) this.mModel).getSystemUi().setCustomHasNoStatusBar(new C0385u(19, this));
    }

    private boolean hasNoStatusBarInDetails(int i2) {
        ContentModel contentModel = ((ContainerModel) this.mModel).getContentModel();
        if (contentModel != null && ((ContainerModel) this.mModel).getStateHelper().supportEditDetailsFitsToDetails() && contentModel.getOverlayViewState() == OverlayViewState.edit_details) {
            return true;
        }
        if ((contentModel == null || !OverlayViewState.isShow((OverlayViewState.StateListener) contentModel)) && ((IVuContainerView) this.mView).isViewResumed() && SheetBehaviorCompat.isExpanded(i2) && !((IVuContainerView) this.mView).isDexMode()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0() {
        return hasNoStatusBarInDetails(((ContainerModel) this.mModel).getDetailsState());
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue()) {
            int intValue = objArr[0].intValue();
            if (hasNoStatusBarInDetails(this.mPrevDetailsState) || hasNoStatusBarInDetails(intValue)) {
                this.mActionInvoker.invoke(ViewerAction.SET_SCREEN_MODE, new Object[0]);
            }
            this.mPrevDetailsState = intValue;
        }
    }

    /* access modifiers changed from: private */
    public void onOverlayViewStateChanged(Object... objArr) {
        if (!((IVuContainerView) this.mView).isDexMode()) {
            this.mActionInvoker.invoke(ViewerAction.SET_SCREEN_MODE, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onRequestSetScreenModeOnPageSelected(Object... objArr) {
        this.mNeedUpdateScreenModeOnPageSelected = true;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageSelected(i2, viewerObjectComposite);
        if (this.mNeedUpdateScreenModeOnPageSelected) {
            this.mNeedUpdateScreenModeOnPageSelected = false;
            this.mActionInvoker.invoke(ViewerAction.SET_SCREEN_MODE, new Object[0]);
        }
    }

    public void onResume() {
        super.onResume();
        this.mActionInvoker.invoke(ViewerAction.SET_SCREEN_MODE, new Object[0]);
        ((IVuContainerView) this.mView).enableOsd(((ContainerModel) this.mModel).isOsdVisible());
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        actionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new r(this, 0));
        actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new r(this, 1));
        actionInvoker.add(ViewerAction.REQUEST_UPDATE_SCREEN_MODE_ON_PAGE_SELECTED, new r(this, 2));
    }
}
