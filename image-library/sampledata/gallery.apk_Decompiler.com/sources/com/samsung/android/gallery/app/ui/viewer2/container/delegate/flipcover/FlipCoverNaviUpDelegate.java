package com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover;

import a6.C0419b;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewStub;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import m7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverNaviUpDelegate extends AbsVuDelegate<IVuContainerView> {
    private Toolbar mToolbar;

    public FlipCoverNaviUpDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void initNavigationUpButton(View view) {
        View findViewById = view.findViewById(R.id.flip_cover_toolbar_viewstub);
        if (findViewById instanceof ViewStub) {
            this.mToolbar = (Toolbar) ((ViewStub) findViewById).inflate().findViewById(R.id.flip_cover_toolbar);
        }
        this.mToolbar.seslSetUserTopPadding(0);
        this.mToolbar.setNavigationIcon((int) R.drawable.tw_ic_ab_back_mtrl_flip_cover);
        this.mToolbar.setNavigationContentDescription((int) R.string.navigate_up);
        this.mToolbar.setNavigationOnClickListener(new C0419b(24, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initNavigationUpButton$2(View view) {
        onNavigationUpPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        updateToolbarVisibility();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        updateToolbarVisibility(objArr[0]);
    }

    private void onNavigationUpPressed() {
        ((IVuContainerView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_UP_KEY);
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
        Log.majorEvent("onNavigationPressed : " + Logger.getEncodedString(ThreadUtil.getCallStack()));
    }

    private void updateLayout() {
        Rect cutouts = ((ContainerModel) this.mModel).getCutouts();
        ViewMarginUtils.setMargin(this.mToolbar, Integer.valueOf(cutouts.left), Integer.valueOf(cutouts.top), Integer.valueOf(cutouts.right), Integer.valueOf(cutouts.bottom));
    }

    private void updateToolbarVisibility() {
        updateToolbarVisibility((Boolean) null);
    }

    public void onApplyWindowInsets() {
        super.onApplyWindowInsets();
        updateLayout();
    }

    public void onBindView(View view) {
        super.onBindView(view);
        initNavigationUpButton(view);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        actionInvoker.add(ViewerAction.TOGGLE_OSD, new d(this, 0));
        actionInvoker.add(ViewerAction.SET_DECOR_VISIBILITY, new d(this, 1));
    }

    private void updateToolbarVisibility(Boolean bool) {
        ViewUtils.setVisibleOrGone(this.mToolbar, bool != null ? bool.booleanValue() : ((ContainerModel) this.mModel).isOsdVisible());
    }
}
