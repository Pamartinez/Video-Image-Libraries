package com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager;

import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewHolderStateDelegate extends AbsVuDelegate<IVuDelegateView> implements VuContainerAdapter.ViewChangeListener {
    public ViewHolderStateDelegate(IVuDelegateView iVuDelegateView) {
        super(iVuDelegateView);
    }

    public void onViewConfirm(int i2, ViewerObjectComposite viewerObjectComposite) {
        viewerObjectComposite.attachActionInvoker(((IVuDelegateView) this.mView).getActionInvoker());
        ((IVuDelegateView) this.mView).onPageSelected(i2, viewerObjectComposite);
    }

    public void onViewInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        ((IVuDelegateView) this.mView).onPageInvalidate(i2, viewerObjectComposite);
    }
}
