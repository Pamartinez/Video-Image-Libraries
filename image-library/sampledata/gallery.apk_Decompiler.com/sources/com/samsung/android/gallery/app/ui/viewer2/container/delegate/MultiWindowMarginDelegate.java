package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiWindowMarginDelegate extends AbsVuDelegate<IVuContainerView> {
    public MultiWindowMarginDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void updateMargin() {
        View view = ((IVuContainerView) this.mView).getView();
        if (((IVuContainerView) this.mView).isInMultiWindowMode()) {
            Rect vuMultiWindowMargin = ((IVuContainerView) this.mView).getVuMultiWindowMargin();
            ViewMarginUtils.setMargin(view, Integer.valueOf(vuMultiWindowMargin.left), Integer.valueOf(vuMultiWindowMargin.top), Integer.valueOf(vuMultiWindowMargin.right), Integer.valueOf(vuMultiWindowMargin.bottom));
            return;
        }
        ViewMarginUtils.setMargin(view, 0);
    }

    public void bindView() {
        updateMargin();
    }

    public void onApplyWindowInsets() {
        updateMargin();
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        updateMargin();
    }
}
