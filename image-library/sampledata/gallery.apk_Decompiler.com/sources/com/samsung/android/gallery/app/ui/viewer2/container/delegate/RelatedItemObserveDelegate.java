package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedItemObserveDelegate extends AbsVuDelegate<IVuContainerView> {
    private final long mRelatedFromId;

    public RelatedItemObserveDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        this.mRelatedFromId = ArgumentsUtil.getArgValue(iVuContainerView.getLocationKey(), "related_from_id", -1);
    }

    public void onMediaDataChanged(int i2, int i7) {
        super.onMediaDataChanged(i2, i7);
        this.mBlackboard.postEvent(EventMessage.obtain(3058, Long.valueOf(this.mRelatedFromId)));
    }
}
