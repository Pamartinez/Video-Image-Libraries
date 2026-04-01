package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import android.view.animation.Animation;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.bottomsheet.ViewerBottomSheetMarginHelper;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Supplier;
import r6.e;
import v7.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OverlayViewHandler extends ViewerObject implements FragmentLifeCycle {
    private final ActionInvokeListener mBackKeyListener = new q(this, 0);
    private ViewerBottomSheetMarginHelper mBottomSheetMarginHelper;
    /* access modifiers changed from: private */
    public View mOverlayView;
    /* access modifiers changed from: private */
    public Supplier<View> mViewSupplier;
    /* access modifiers changed from: private */
    public CoordinatorLayout mViewerLayout;

    private void attachOverlayView() {
        boolean z;
        CoordinatorLayout coordinatorLayout;
        if (this.mOverlayView.getParent() != null || (coordinatorLayout = this.mViewerLayout) == null) {
            StringCompat stringCompat = this.TAG;
            boolean z3 = false;
            if (this.mOverlayView.getParent() == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mViewerLayout != null) {
                z3 = true;
            }
            Log.e((CharSequence) stringCompat, "attachView failed", valueOf, Boolean.valueOf(z3));
            return;
        }
        coordinatorLayout.addView(this.mOverlayView);
        ViewUtils.setMatchParentView(this.mOverlayView);
    }

    private void clearView() {
        View view = this.mOverlayView;
        if (view != null) {
            ViewUtils.removeView(this.mViewerLayout, view);
            this.mOverlayView = null;
        }
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        if (actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
        this.mViewSupplier = null;
    }

    /* access modifiers changed from: private */
    public void exitOverlayView(Object... objArr) {
        int i2;
        Log.d(this.TAG, "exitOverlayView");
        OverlayViewState overlayViewState = this.mModel.getOverlayViewState();
        setOverlayViewState(OverlayViewState.hide);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        if (actionInvoker.hasExclusive(viewerAction, this.mBackKeyListener)) {
            this.mActionInvoker.removeExclusive(viewerAction, this.mBackKeyListener);
        }
        if (this.mOverlayView == null) {
            Log.e(this.TAG, "mOverLayView is null");
            return;
        }
        if (PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION) {
            getDepthBaseAnimViewList(overlayViewState).forEach(new e(28));
            i2 = R.anim.sesl_fragment_close_exit;
        } else {
            i2 = R.anim.depth_out_enter;
        }
        SimpleAnimator.create(this.mOverlayView, i2, new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                boolean z;
                if (OverlayViewHandler.this.mOverlayView == null || OverlayViewHandler.this.mViewerLayout == null) {
                    StringCompat access$000 = OverlayViewHandler.this.TAG;
                    boolean z3 = false;
                    if (OverlayViewHandler.this.mOverlayView != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Boolean valueOf = Boolean.valueOf(z);
                    if (OverlayViewHandler.this.mViewerLayout != null) {
                        z3 = true;
                    }
                    Log.e((CharSequence) access$000, "failed to removeView", valueOf, Boolean.valueOf(z3));
                    return;
                }
                ViewUtils.removeView(OverlayViewHandler.this.mViewerLayout, OverlayViewHandler.this.mOverlayView);
                OverlayViewHandler.this.mOverlayView = null;
                OverlayViewHandler.this.mViewSupplier = null;
            }
        });
    }

    private ArrayList<View> getDepthBaseAnimViewList(OverlayViewState overlayViewState) {
        boolean z;
        OverlayViewState overlayViewState2;
        ArrayList<View> arrayList = new ArrayList<>();
        if (this.mViewerLayout != null) {
            if (!this.mModel.getStateHelper().supportEditDetailsFitsToDetails() || !(overlayViewState == (overlayViewState2 = OverlayViewState.edit_details) || this.mModel.getOverlayViewState() == overlayViewState2)) {
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                arrayList.add(this.mViewerLayout.findViewById(R.id.content_container));
            }
            if (PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION && BottomSheetState.Details.isExpanded(this.mModel)) {
                arrayList.add(this.mViewerLayout.findViewById(R.id.more_info_root_container));
                if (!z) {
                    arrayList.add(this.mViewerLayout.findViewById(R.id.ai_edit_layout));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mBottomSheetMarginHelper = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$4() {
        int i2;
        View view;
        Rect systemInsets = this.mModel.getContainerModel().getSystemInsets();
        if (DeviceInfo.isDexMode(this.mModel.getContext())) {
            i2 = systemInsets.top;
        } else {
            i2 = Math.max(systemInsets.top, this.mModel.getSystemUi().getStatusBarHeight(this.mModel.getActivity()));
        }
        View view2 = this.mOverlayView;
        if (view2 != null) {
            view = view2.findViewById(R.id.root_layout);
        } else {
            view = null;
        }
        if (view == null) {
            view = this.mOverlayView;
        }
        ViewMarginUtils.setPadding(view, systemInsets.left, i2, systemInsets.right, systemInsets.bottom);
        if (this.mModel.getOverlayViewState() == OverlayViewState.edit_details) {
            updateEditDetailsLayout(view);
        }
    }

    private void replaceOverlayView(View view) {
        View view2 = this.mOverlayView;
        if (view2 == null) {
            Log.e(this.TAG, "replaceOverlayView skip: pre view  is null");
        } else if (view == null) {
            Log.e(this.TAG, "replaceOverlayView skip: view is null");
        } else {
            ViewUtils.removeView(this.mViewerLayout, view2);
            this.mOverlayView = view;
            attachOverlayView();
            updateLayout();
        }
    }

    private void setOverlayViewState(OverlayViewState overlayViewState) {
        this.mModel.setOverlayViewState(overlayViewState);
        this.mActionInvoker.invoke(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, overlayViewState);
    }

    private void showOverlayView(Object[] objArr) {
        int i2;
        if (this.mOverlayView != null) {
            Log.e(this.TAG, "OverlayView already existed");
            return;
        }
        View view = objArr[0];
        if (view == null) {
            Log.e(this.TAG, "OverlayView is null");
            return;
        }
        try {
            this.mOverlayView = view;
            OverlayViewState overlayViewState = objArr[1];
            boolean booleanValue = objArr[2].booleanValue();
            if (ViewUtils.isAttachedToWindow(this.mOverlayView)) {
                Log.e(this.TAG, "OverlayView skip: view is already attached");
                return;
            }
            Log.d(this.TAG, "Show OverlayView");
            setOverlayViewState(overlayViewState);
            attachOverlayView();
            updateLayout();
            if (!booleanValue) {
                this.mActionInvoker.addExclusive(ViewerAction.BACK_KEY_PRESSED, this.mBackKeyListener, this.TAG);
            }
            if (PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION) {
                getDepthBaseAnimViewList(OverlayViewState.hide).forEach(new e(27));
                i2 = R.anim.sesl_fragment_open_enter;
            } else {
                i2 = R.anim.depth_in_enter;
            }
            SimpleAnimator.create(this.mOverlayView, i2, (Animation.AnimationListener) null);
        } catch (ClassCastException e) {
            Log.e((CharSequence) this.TAG, "OverlayView invalid param", (Throwable) e);
        }
    }

    private void showOverlayViewSupplier(Object[] objArr) {
        Supplier<View> supplier = objArr[0];
        this.mViewSupplier = supplier;
        if (supplier != null) {
            showOverlayView(new Object[]{supplier.get(), objArr[1], objArr[2]});
        }
    }

    private void updateEditDetailsLayout(View view) {
        if (this.mModel.getStateHelper().supportEditDetailsFitsToDetails()) {
            Size targetSize = this.mBottomSheetMarginHelper.getTargetSize();
            if (!ResourceCompat.isLandscape(this.mModel.getContext()) || this.mModel.getContainerModel().isTableMode()) {
                ViewMarginUtils.setTopPadding(view, 0);
                ViewMarginUtils.setMargin(view, 0, Integer.valueOf(targetSize.getHeight()), (Integer) null, (Integer) null);
                return;
            }
            ViewMarginUtils.setLeftPadding(view, 0);
            ViewMarginUtils.setMargin(view, Integer.valueOf(targetSize.getWidth()), 0, (Integer) null, (Integer) null);
            return;
        }
        ViewMarginUtils.setMargin(view, 0, 0, (Integer) null, (Integer) null);
    }

    private void updateLayout() {
        ViewUtils.post(this.mOverlayView, new t8.e(15, this));
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new q(this, 1));
        this.mActionInvoker.add(ViewerAction.REQUEST_EXIT_OVERLAY_VIEW, new q(this, 0));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_MARGIN_HELPER, new q(this, 2));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3070) {
            showOverlayView((Object[]) eventMessage.obj);
            return true;
        } else if (i2 != 3071) {
            return false;
        } else {
            showOverlayViewSupplier((Object[]) eventMessage.obj);
            return true;
        }
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateLayout();
        Supplier<View> supplier = this.mViewSupplier;
        if (supplier != null) {
            replaceOverlayView(supplier.get());
        }
    }

    public void onFinalized() {
        clearView();
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateLayout();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        clearView();
    }
}
