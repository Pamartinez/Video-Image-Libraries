package com.samsung.android.gallery.app.ui.viewer2.container.delegate.input;

import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import o7.c;
import o7.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExitGestureDelegate extends AbsVuDelegate<IVuContainerView> {
    private boolean mEnabled = true;
    private final boolean mWasMultiWindowMode;

    public ExitGestureDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        this.mWasMultiWindowMode = iVuContainerView.isInMultiWindowMode();
    }

    private DataKey.ShrinkType getShrinkType(int i2) {
        if (i2 == 3019) {
            return DataKey.ShrinkType.PINCH;
        }
        if (i2 == 3020) {
            return DataKey.ShrinkType.DRAG;
        }
        switch (i2) {
            case 3046:
                return DataKey.ShrinkType.DRAG_CAMERA;
            case 3047:
                return DataKey.ShrinkType.PINCH_CAMERA;
            case 3048:
                return DataKey.ShrinkType.BACK_PRESSED_CAMERA;
            default:
                return DataKey.ShrinkType.NONE;
        }
    }

    private void handlePinchShrinkEvent(int i2) {
        DataKey.ShrinkType shrinkType = getShrinkType(i2);
        if (needShrinkActive(shrinkType)) {
            this.mBlackboard.publish("data://shrink_active", shrinkType);
        }
        if (i2 == 3014) {
            BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
        } else if (i2 == 3019 || i2 == 3020) {
            this.mBlackboard.publish("data://avoid_status_bar_color", Boolean.TRUE);
            BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
        } else {
            switch (i2) {
                case 3046:
                    handleShrinkToCamera(this.mBlackboard, QuickViewTransitionAnimation.QuickViewShrinkType.DRAG);
                    return;
                case 3047:
                    handleShrinkToCamera(this.mBlackboard, QuickViewTransitionAnimation.QuickViewShrinkType.PINCH);
                    return;
                case 3048:
                    handleShrinkToCamera(this.mBlackboard, QuickViewTransitionAnimation.QuickViewShrinkType.BACK);
                    return;
                default:
                    return;
            }
        }
    }

    private void handleShrinkToCamera(Blackboard blackboard, QuickViewTransitionAnimation.QuickViewShrinkType quickViewShrinkType) {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SHRINK_TO_CAMERA || isMultiWindowMode() || this.mWasMultiWindowMode || isQuickViewBackInfoAbsence(blackboard)) {
            blackboard.publish("data://shrink_active", DataKey.ShrinkType.BACK_PRESSED_CAMERA);
            BlackboardUtils.publishBackKeyEvent(blackboard);
            return;
        }
        this.mEnabled = false;
        new QuickViewTransitionAnimation(blackboard, quickViewShrinkType, isBlackBackground()).setConvertListener(new c(this, blackboard, 0)).setViewReadyListener(new d(this, 0)).setOnCancelListener(new c(this, blackboard, 1)).start();
    }

    private boolean isBlackBackground() {
        if (!((ContainerModel) this.mModel).isOsdVisible() || ((ContainerModel) this.mModel).isFlipCoverGallery()) {
            return true;
        }
        return false;
    }

    private boolean isMultiWindowMode() {
        boolean isInMultiWindowMode = ((IVuContainerView) this.mView).isInMultiWindowMode();
        if (!isInMultiWindowMode) {
            try {
                if (SeApiCompat.getSettingsGlobalInt(((IVuContainerView) this.mView).getContext(), "multi_window_menu_in_full_screen", 0) != 0) {
                    return true;
                }
                return false;
            } catch (Exception unused) {
                Log.e(this.TAG, "unable to get global setting");
            }
        }
        return isInMultiWindowMode;
    }

    private boolean isQuickViewBackInfoAbsence(Blackboard blackboard) {
        if (((LaunchIntent) blackboard.read("data://launch_intent")) == null || ((IVuContainerView) this.mView).isTabletDpi() || ((ContainerModel) this.mModel).isFlipCoverGallery()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShrinkToCamera$0(Blackboard blackboard) {
        blackboard.post("command:///ClearContentViewBackground", (Object) null);
        this.mActionInvoker.invoke(ViewerAction.PUBLISH_TRANSITION_INFO, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShrinkToCamera$1() {
        ViewUtils.setBackgroundResource(((IVuContainerView) this.mView).getView(), 17170445);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.HIDE_MAIN_VIEW;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool);
        ActionInvoker actionInvoker2 = this.mActionInvoker;
        ViewerAction viewerAction2 = ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED;
        Boolean bool2 = Boolean.FALSE;
        actionInvoker2.invoke(viewerAction2, bool2);
        if (((ContainerModel) this.mModel).isOsdVisible()) {
            this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, bool2);
            this.mActionInvoker.invoke(ViewerAction.HOLD_DECOR_VIEW_FOR_SLIDE_UP_VI, bool);
            if (((ContainerModel) this.mModel).getStateHelper().isFromCamera()) {
                this.mActionInvoker.invoke(ViewerAction.HIDE_NAVIGATION_BAR, new Object[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShrinkToCamera$2() {
        this.mActionInvoker.invoke(ViewerAction.RESET_EXIT_GESTURE, new Object[0]);
        this.mEnabled = true;
        Log.d(this.TAG, "reset");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleShrinkToCamera$3(Blackboard blackboard) {
        blackboard.erase("data://shared_original_bitmap");
        blackboard.erase("data://shrink_active");
        ((IVuContainerView) this.mView).updateMediaDataIfNeeded();
        Blackboard.publishGlobal("command:///RestoreContentViewBackground", (Object) null);
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool);
        ActionInvoker actionInvoker2 = this.mActionInvoker;
        ViewerAction viewerAction2 = ViewerAction.HOLD_DECOR_VIEW_FOR_SLIDE_UP_VI;
        Boolean bool2 = Boolean.FALSE;
        actionInvoker2.invoke(viewerAction2, bool2);
        ((ContainerModel) this.mModel).setOsdVisible(false);
        this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
        ViewUtils.setBackgroundResource(((IVuContainerView) this.mView).getView(), ((ContainerModel) this.mModel).getStateHelper().getBackgroundColorResource());
        this.mActionInvoker.invoke(ViewerAction.HIDE_MAIN_VIEW, bool2);
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, bool);
        ThreadUtil.postOnUiThreadDelayed(new d(this, 1), (long) (QuickViewTransitionAnimation.RETURN_DELAY + 50));
    }

    private boolean needShrinkActive(DataKey.ShrinkType shrinkType) {
        boolean argValue = ArgumentsUtil.getArgValue(((IVuContainerView) this.mView).getLocationKey(), "from_suggestion_pictures", false);
        if (DataKey.ShrinkType.NONE.equals(shrinkType) || argValue) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onExitGesture(Object... objArr) {
        if (!this.mEnabled) {
            Log.w(this.TAG, "disabled");
        } else if (objArr.length == 1 && (objArr[0] instanceof Integer) && shouldBackToList()) {
            int intValue = objArr[0].intValue();
            String str = this.TAG;
            Log.majorEvent(str, "OnExitGesture : " + getShrinkType(intValue));
            this.mActionInvoker.invoke(ViewerAction.SHOW_NAVIGATION_BAR, new Object[0]);
            handlePinchShrinkEvent(intValue);
        }
    }

    private boolean shouldBackToList() {
        boolean z;
        boolean z3;
        String str;
        String str2;
        String str3;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        boolean z7 = false;
        if (currentViewer == null || !BottomSheetState.Details.isExpanded(currentViewer.getModel())) {
            z = false;
        } else {
            z = true;
        }
        if (currentViewer == null || !BottomSheetState.Details.isInTransition(currentViewer.getModel())) {
            z3 = false;
        } else {
            z3 = true;
        }
        boolean isViewerShrink = BlackboardUtils.isViewerShrink(this.mBlackboard);
        if (!z && !z3 && !isViewerShrink) {
            z7 = true;
        }
        if (!z7) {
            String str4 = this.TAG;
            StringBuilder sb2 = new StringBuilder("can not back to list");
            if (z) {
                str = "V";
            } else {
                str = "v";
            }
            if (z3) {
                str2 = "T";
            } else {
                str2 = "t";
            }
            if (isViewerShrink) {
                str3 = "S";
            } else {
                str3 = "s";
            }
            sb2.append(Logger.v(str, str2, str3));
            Log.w(str4, sb2.toString());
        }
        return z7;
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        this.mActionInvoker.add(ViewerAction.HANDLE_EXIT_GESTURE, new B7.d(23, this));
    }
}
