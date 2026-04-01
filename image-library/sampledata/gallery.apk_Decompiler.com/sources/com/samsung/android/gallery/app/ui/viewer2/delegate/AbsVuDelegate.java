package com.samsung.android.gallery.app.ui.viewer2.delegate;

import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsVuDelegate<V extends IVuDelegateView> extends AbsDelegate<V, ContainerModel> {
    public AbsVuDelegate(V v) {
        super(v);
    }

    public void onMediaDataChanged(int i2, int i7) {
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
    }

    public void onTableModeChanged(boolean z, int i2) {
    }
}
