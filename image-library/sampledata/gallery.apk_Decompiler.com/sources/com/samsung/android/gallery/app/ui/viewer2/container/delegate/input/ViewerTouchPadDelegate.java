package com.samsung.android.gallery.app.ui.viewer2.container.delegate.input;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o7.g;
import o7.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerTouchPadDelegate extends AbsVuDelegate<IVuContainerView> {
    private View mTouchPad;
    private ViewStub mViewStub;
    private final Runnable showWithAnim = new h(this, 1);

    public ViewerTouchPadDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void hide() {
        boolean z;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        int i2 = 0;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.TextExtractionCapsule) || currentViewer == null || !currentViewer.getModel().isTextExtractionFullState() || !isEnabled()) {
            z = false;
        } else {
            z = true;
        }
        View view = this.mTouchPad;
        if (!z) {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    private void inflate() {
        if (ViewUtils.isViewStub(this.mViewStub)) {
            this.mTouchPad = this.mViewStub.inflate();
        }
    }

    private boolean isEnabled() {
        boolean z;
        boolean isTableMode = ((ContainerModel) this.mModel).isTableMode();
        boolean isOsdVisible = ((ContainerModel) this.mModel).isOsdVisible();
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || !BottomSheetState.Details.isExpanded(currentViewer.getModel())) {
            z = false;
        } else {
            z = true;
        }
        boolean isRevitalizationView = LocationKey.isRevitalizationView(((ContainerModel) this.mModel).getLocationKey());
        if (!isTableMode || !isOsdVisible || z || isRevitalizationView) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        setAlpha(objArr[0].floatValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        setVisibility(objArr[0].intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$2() {
        View view;
        if (isEnabled() && (view = this.mTouchPad) != null && view.getContext() != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTouchPad.getLayoutParams();
            marginLayoutParams.topMargin = (DeviceInfo.getDisplayHeight(this.mTouchPad.getContext()) / 2) + SystemUi.getToolBarHeightWithPadding(this.mTouchPad.getContext());
            marginLayoutParams.bottomMargin = ((ContainerModel) this.mModel).getOverlaySize().bottom + this.mTouchPad.getResources().getDimensionPixelSize(R.dimen.viewer_touch_pad_bottom_margin);
            this.mTouchPad.setLayoutParams(marginLayoutParams);
        }
    }

    private void setAlpha(float f) {
        if (isEnabled()) {
            ViewUtils.setAlpha(this.mTouchPad, f);
        }
    }

    private void setVisibility(int i2) {
        if (i2 == 0) {
            show();
            return;
        }
        ThreadUtil.removeCallbackOnUiThread(this.showWithAnim);
        hide();
    }

    private void show() {
        if (isEnabled()) {
            if (this.mTouchPad == null) {
                inflate();
            }
            ViewUtils.setVisibility(this.mTouchPad, 0);
            updateLayout();
        }
    }

    /* access modifiers changed from: private */
    public void showWithAnim() {
        show();
        if (this.mTouchPad != null) {
            new AlphaAnimator(this.mTouchPad, 0.0f, 1.0f).setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60)).setDuration(200).start();
        }
    }

    private void updateLayout() {
        ViewUtils.post(this.mTouchPad, new h(this, 0));
    }

    public void onDestroy() {
        this.mTouchPad = null;
        this.mViewStub = null;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        if (isEnabled() && ViewUtils.isShown(this.mTouchPad)) {
            updateLayout();
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        if (isEnabled()) {
            ThreadUtil.postOnUiThreadDelayed(this.showWithAnim, 300);
        } else {
            hide();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mViewStub = (ViewStub) view.findViewById(R.id.touch_pad_viewstub);
        show();
        if (ViewUtils.isTranslucent(this.mTouchPad)) {
            setAlpha(1.0f);
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_ALPHA, new g(this, 0));
        actionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_VISIBILITY, new g(this, 1));
    }
}
