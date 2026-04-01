package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverSelectModeDelegate extends SelectModeDelegate {
    public FlipCoverSelectModeDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    public void setVisibility(int i2) {
        int i7;
        if (((ContainerModel) this.mModel).isOsdVisible()) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        super.setVisibility(i7);
    }
}
