package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import C3.C0391a;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaViewPrivateAlbumPlayerHandler extends MediaViewPlayerHandler {
    private SubscriberListenerInfo mPrivateLockScreen;

    private Boolean isPrivateLockScreen() {
        return (Boolean) this.mModel.getBlackboard().read("command://LockPrivateAlbum", Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBind$0(Object obj, Bundle bundle) {
        onLockPrivateScreen(((Boolean) obj).booleanValue());
    }

    private void onLockPrivateScreen(boolean z) {
        if (!z && this.mModel.isFragmentResumed() && this.mModel.isViewConfirmed()) {
            Log.d(this.TAG, "onLockPrivateScreen updateState");
            updatePlaybackState();
        }
    }

    public void onBind(MediaItem mediaItem, int i2) {
        if (this.mPrivateLockScreen == null) {
            this.mPrivateLockScreen = subscribe("command://LockPrivateAlbum", new C0391a(6, this));
        }
        super.onBind(mediaItem, i2);
    }

    public void updatePlaybackState() {
        if (isPrivateLockScreen().booleanValue()) {
            Log.d(this.TAG, "updatePlaybackState isPrivateLockScreen");
        } else {
            super.updatePlaybackState();
        }
    }
}
