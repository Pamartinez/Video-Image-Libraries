package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.app.Activity;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import g6.g;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerTableModeDelegate extends AbsVuDelegate<IVuContainerView> {
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        public void onScreenChanged(FoldableScreen foldableScreen, FoldableScreen foldableScreen2) {
            ViewerTableModeDelegate.this.mIsMainScreen = FoldableScreen.MAIN.equals(foldableScreen2);
            if (!((IVuContainerView) ViewerTableModeDelegate.this.mView).isDestroyed()) {
                ViewerTableModeDelegate.this.postAnalyticsLog();
                ViewerTableModeDelegate.this.mActionInvoker.invoke(ViewerAction.FOLD_STATE_CHANGED, new Object[0]);
            }
        }

        public void onTableModeChanged(boolean z, int i2) {
            ViewerTableModeDelegate.this.mIsTableMode = z;
            ((ContainerModel) ViewerTableModeDelegate.this.mModel).setTableMode(ViewerTableModeDelegate.this.isTableModeAvailable());
            if (!((IVuContainerView) ViewerTableModeDelegate.this.mView).isDestroyed()) {
                ((IVuContainerView) ViewerTableModeDelegate.this.mView).onTableModeChanged(z, i2);
                ViewerTableModeDelegate.this.showTableModeToast();
                ViewerTableModeDelegate.this.postAnalyticsLog();
                ViewerTableModeDelegate.this.mActionInvoker.invoke(ViewerAction.FOLD_STATE_CHANGED, new Object[0]);
            }
        }
    };
    private FoldStateManager mFoldStateManager;
    /* access modifiers changed from: private */
    public boolean mIsMainScreen;
    /* access modifiers changed from: private */
    public boolean mIsTableMode;

    public ViewerTableModeDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private FoldStateManager getFoldStateManager() {
        if (this.mFoldStateManager == null) {
            this.mFoldStateManager = FoldStateManager.getInstance(this.mBlackboard);
        }
        return this.mFoldStateManager;
    }

    private boolean isMainScreen(Activity activity) {
        if (activity == null || SeApiCompat.isMainScreen(activity.getResources().getConfiguration())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isTableModeAvailable() {
        if (!this.mIsTableMode || ((IVuContainerView) this.mView).isInMultiWindowMode() || ((IVuContainerView) this.mView).isDexMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
        this.mIsTableMode = foldStateManager.isTableMode();
        ((ContainerModel) this.mModel).setTableMode(isTableModeAvailable());
        this.mIsMainScreen = isMainScreen(((IVuContainerView) this.mView).getActivity());
    }

    /* access modifiers changed from: private */
    public void postAnalyticsLog() {
        AnalyticsLogger.getInstance().postLog(((IVuContainerView) this.mView).getScreenId());
    }

    /* access modifiers changed from: private */
    public void showTableModeToast() {
        boolean z;
        MediaItem currentItem = ((IVuContainerView) this.mView).getEventContext().getCurrentItem();
        if (currentItem == null || !currentItem.isImage()) {
            z = false;
        } else {
            z = true;
        }
        if (isTableModeAvailable() && z && PreferenceCache.ViewerTableModeToast.compareAndSet(true, false)) {
            Utils.showToast(((IVuContainerView) this.mView).getContext(), R.string.table_mode_toast, 49, 0, ((IVuContainerView) this.mView).getToolbar().getHeight() + (DeviceInfo.getDisplayHeight(((IVuContainerView) this.mView).getContext()) / 2));
        }
    }

    private void updateTableModeWhenMultiWindowChanged() {
        if (this.mIsTableMode && !((IVuContainerView) this.mView).isDestroyed()) {
            ((IVuContainerView) this.mView).onTableModeChanged(isTableModeAvailable(), 1);
            ((ContainerModel) this.mModel).setTableMode(isTableModeAvailable());
        }
    }

    public String getAnalyticsScreenId(String str) {
        if (this.mIsTableMode) {
            return C0212a.A(str, "_F1");
        }
        if (!this.mIsMainScreen) {
            return C0212a.A(str, "_S1");
        }
        return str;
    }

    public void onApplyWindowInsets() {
        if (this.mFoldStateManager != null && ((IVuContainerView) this.mView).isViewResumed()) {
            this.mFoldStateManager.onApplyWindowInsets();
        }
    }

    public void onCreate(Bundle bundle) {
        Optional.ofNullable(getFoldStateManager()).ifPresent(new g(15, this));
    }

    public void onDestroy() {
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        updateTableModeWhenMultiWindowChanged();
    }

    public void onResume() {
        this.mFoldStateManager.resume();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
    }
}
