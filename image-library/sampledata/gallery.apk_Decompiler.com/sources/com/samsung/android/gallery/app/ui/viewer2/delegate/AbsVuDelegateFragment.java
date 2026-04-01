package com.samsung.android.gallery.app.ui.viewer2.delegate;

import android.graphics.Rect;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegateFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegatePresenter;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsVuDelegateFragment<V extends IVuDelegateView, P extends AbsVuDelegatePresenter> extends AbsDelegateFragment<V, P, ContainerModel, VuDelegateComposite> implements IVuDelegateView {
    public void changeScreenMode() {
        super.changeScreenMode();
        updateNavigationBar();
    }

    public Rect getVuMultiWindowMargin() {
        Rect rect;
        Rect rect2;
        WindowInsets windowInsets;
        WindowInsets windowInsets2 = this.mWindowInsets;
        WindowInsets windowInsets3 = null;
        if (windowInsets2 == null) {
            if (getView() != null) {
                windowInsets = getView().getRootWindowInsets();
            } else {
                windowInsets = null;
            }
            rect = WindowUtils.getSystemInsetsIgnoringVisibility(windowInsets);
        } else {
            rect = WindowUtils.getSystemInsetsIgnoringVisibility(windowInsets2);
        }
        WindowInsets windowInsets4 = this.mWindowInsets;
        if (windowInsets4 == null) {
            if (getView() != null) {
                windowInsets3 = getView().getRootWindowInsets();
            }
            rect2 = WindowUtils.getDisplayCutoutRect(windowInsets3);
        } else {
            rect2 = WindowUtils.getDisplayCutoutRect(windowInsets4);
        }
        return new Rect(Math.max(rect.left, rect2.left), Math.max(rect.top, rect2.top), Math.max(rect.right, rect2.right), Math.max(rect.bottom, rect2.bottom));
    }

    public void onMediaDataChanged(int i2, int i7) {
        ((VuDelegateComposite) this.mDelegateComposite).onMediaDataChanged(i2, i7);
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        ((VuDelegateComposite) this.mDelegateComposite).onPageInvalidate(i2, viewerObjectComposite);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        ((VuDelegateComposite) this.mDelegateComposite).onPageSelected(i2, viewerObjectComposite);
    }

    public void onTableModeChanged(boolean z, int i2) {
        ((VuDelegateComposite) this.mDelegateComposite).onTableModeChanged(z, i2);
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public abstract void updateNavigationBar();
}
