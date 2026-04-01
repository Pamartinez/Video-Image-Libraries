package com.samsung.android.gallery.app.ui.viewer2.unlock;

import B7.d;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnlockHandler extends ViewerObject {
    /* access modifiers changed from: private */
    public void onUnLockScreen(Object... objArr) {
        new RequestDismissKeyGuardCmd().execute(this.mModel.getContainerModel().getEventContext(), new Object[0]);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.REQUEST_UNLOCK_SCREEN, new d(10, this));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
    }
}
