package com.samsung.android.gallery.app.ui.viewer2.details;

import H3.l;
import L7.o;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.details.DetailsBehavior;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsStateHandler extends ViewerObject implements FragmentLifeCycle, DetailsBehavior.BottomSheetCallback {
    private DetailsBehavior mBehavior;
    private DetailsView mDetailsView;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        DetailsBehavior detailsBehavior = objArr[0];
        this.mBehavior = detailsBehavior;
        detailsBehavior.addBottomSheetCallback(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mDetailsView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mBehavior.hide();
    }

    /* access modifiers changed from: private */
    public void onRequestDetailsShowOrHide(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        boolean booleanValue2 = objArr[1].booleanValue();
        if (booleanValue) {
            this.mBehavior.show(booleanValue2);
        } else {
            this.mBehavior.hide(booleanValue2);
        }
    }

    /* access modifiers changed from: private */
    public void updateState() {
        if (SheetBehaviorCompat.isClosed(this.mModel.getContainerModel().getDetailsState()) || !DetailsHandler.supportDetails(this.mModel.getMediaItem())) {
            this.mBehavior.hide(false);
            this.mModel.getContainerModel().setDetailsState(4);
        } else if (SheetBehaviorCompat.isExpanded(this.mModel.getContainerModel().getDetailsState())) {
            this.mBehavior.show(false);
            this.mModel.getContainerModel().setDetailsState(3);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.DETAILS_BEHAVIOR, new o(this, 0));
        this.mActionInvoker.add(ViewerAction.DETAILS_VIEW, new o(this, 1));
        this.mActionInvoker.add(ViewerAction.RESET_EXIT_GESTURE, new o(this, 2));
        this.mActionInvoker.add(ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE, new o(this, 3));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3057) {
            return false;
        }
        this.mBehavior.hide(((Boolean) eventMessage.obj).booleanValue());
        return false;
    }

    public void onResume() {
        if (FoldUtils.isFlipCoverScreen(this.mModel.getActivity()) && this.mBehavior.isExpanded()) {
            this.mBehavior.hide();
        }
    }

    public void onStateChanged(View view, int i2) {
        Log.d(this.TAG, "onStateChanged", Integer.valueOf(i2));
        this.mModel.setDetailsState(i2);
        this.mModel.getContainerModel().setDetailsState(i2);
        if (this.mModel.getParentModel() != null) {
            this.mModel.getParentModel().setDetailsState(i2);
        }
        this.mActionInvoker.invoke(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, Integer.valueOf(i2), Boolean.TRUE);
    }

    public void onViewAttached() {
        boolean z;
        if (this.mModel.getContainerModel().getViewPager().getScrollState() != 0) {
            z = true;
        } else {
            z = false;
        }
        if (!this.mBehavior.hasViewRef() || z) {
            ViewUtils.post(this.mDetailsView, new l(26, this));
        } else {
            updateState();
        }
    }
}
