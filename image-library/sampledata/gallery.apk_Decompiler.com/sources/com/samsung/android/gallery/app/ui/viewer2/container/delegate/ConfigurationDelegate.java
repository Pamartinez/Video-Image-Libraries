package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.content.res.Configuration;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConfigurationDelegate extends AbsVuDelegate<IVuContainerView> {
    public ConfigurationDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        super.onConfigurationChanged(configuration, z, z3, z7, z9);
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && z3) {
            currentViewer.onResolutionChanged();
        }
    }
}
