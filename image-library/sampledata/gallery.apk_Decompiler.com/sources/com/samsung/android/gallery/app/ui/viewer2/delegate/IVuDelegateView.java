package com.samsung.android.gallery.app.ui.viewer2.delegate;

import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IVuDelegateView extends IDelegateView<ContainerModel> {
    void onMediaDataChanged(int i2, int i7);

    void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite);

    void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite);

    void onTableModeChanged(boolean z, int i2);

    void printLog(String str, String str2);

    void updateMediaDataIfNeeded();
}
