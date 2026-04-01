package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.animation.ArgbEvaluator;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;
import k7.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomSheetDelegate extends AbsVuDelegate<IVuContainerView> {
    public BottomSheetDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager != null && viewPager.getScrollState() == 0) {
            updateViewPagerTouch();
        }
    }

    /* access modifiers changed from: private */
    public void onDetailsSlide(Object... objArr) {
        float floatValue = objArr[1].floatValue();
        if (!((ContainerModel) this.mModel).isOsdVisible()) {
            updateRootBackgroundColor(floatValue);
        }
    }

    /* access modifiers changed from: private */
    public void onOverlayViewStateChanged(Object... objArr) {
        updateViewPagerTouch();
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onSingleTakenSelectModeChanged(Object... objArr) {
        updateViewPagerTouch();
    }

    private void setRootBackgroundColor(int i2) {
        View view = ((IVuContainerView) this.mView).getView();
        if (view != null) {
            view.setBackgroundColor(i2);
        }
    }

    private void updateRootBackgroundColor(float f) {
        FragmentActivity activity = ((IVuContainerView) this.mView).getActivity();
        if (activity != null) {
            setRootBackgroundColor(((Integer) new ArgbEvaluator().evaluate(Math.min(f, 1.0f), Integer.valueOf(activity.getColor(R.color.black_color)), Integer.valueOf(activity.getColor(R.color.daynight_default_background)))).intValue());
        }
    }

    private void updateViewPagerTouch() {
        ViewPagerDelegate viewPagerDelegate = (ViewPagerDelegate) getDelegate(ViewPagerDelegate.class);
        if (viewPagerDelegate != null) {
            viewPagerDelegate.updateViewPagerTouch();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new e(this, 0));
        actionInvoker.add(ViewerAction.DETAILS_SLIDE, new e(this, 1));
        actionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new e(this, 2));
        actionInvoker.add(ViewerAction.SINGLE_TAKEN_SELECT_MODE_CHANGED, new e(this, 3));
    }
}
