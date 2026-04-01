package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CacheDelegate extends AbsVuDelegate<IVuContainerView> {
    public CacheDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void clearCurrentItemCache() {
        ThreadUtil.runOnBgThread(new b(1, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clearCurrentItemCache$0() {
        MediaItem currentMediaItem;
        if (this.mView != null && (currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem()) != null) {
            ThumbnailLoader.getInstance().removeCache(currentMediaItem);
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3055) {
            return super.onHandleEvent(eventMessage);
        }
        clearCurrentItemCache();
        return true;
    }
}
