package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationKeyDelegate extends AbsVuDelegate<IVuContainerView> {
    public LocationKeyDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void updateLocationKey(int i2) {
        this.mBlackboard.publish("location://variable/currentv1", new UriBuilder(((IVuContainerView) this.mView).getLocationKey()).appendArg(Message.KEY_POSITION, i2).build());
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        updateLocationKey(i2);
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        updateLocationKey(i2);
    }
}
