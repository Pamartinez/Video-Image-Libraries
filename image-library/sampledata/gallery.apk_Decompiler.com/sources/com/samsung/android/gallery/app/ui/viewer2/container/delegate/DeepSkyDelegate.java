package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import C3.i;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeepSkyDelegate extends AbsVuDelegate<IVuContainerView> {
    public DeepSkyDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    public void onCreate(Bundle bundle) {
        DeepSkyHelper.openTextExtraction();
        DeepSkyHelper.openObjectCapture();
    }

    public void onDestroy() {
        DeepSkyHelper.releaseTextExtraction();
        DeepSkyHelper.releaseObjectCapture();
        DeepSkyHelper.closeTextExtractionDrawHelper(((ContainerModel) this.mModel).toString());
        if (DeepSkyHelper.isObjectCaptureReleased()) {
            DeepSkyHelper.post(new i(22));
            this.mBlackboard.erase("data://object_capture_reset");
        }
    }
}
