package com.samsung.android.gallery.app.ui.viewer2.container.abstraction;

import android.graphics.Rect;
import com.samsung.android.gallery.app.remote.v2.IVuDispatcher;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IVuContainerView extends IVuDelegateView, IVuDispatcher {
    void enableOsd(boolean z);

    ViewerObjectComposite getCurrentViewer();

    Rect getVuMultiWindowMargin();

    void hideNavigationBar();

    void onMediaDataUpdated(MediaData mediaData);

    void requestForceRotate();

    void setCurrentViewer(ViewerObjectComposite viewerObjectComposite);

    void setPosition(int i2, boolean z);

    void updateBackgroundColor(boolean z);
}
