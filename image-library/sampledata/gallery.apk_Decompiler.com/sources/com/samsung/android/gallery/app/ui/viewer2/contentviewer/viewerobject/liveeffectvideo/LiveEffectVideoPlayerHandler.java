package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo;

import B7.d;
import E7.c;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveEffectVideoPlayerHandler extends MediaViewPlayerHandler {
    private boolean mIsLoopEnabled = true;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        onFilmStripStateChanged(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLoopEnabled$1(boolean z, IMediaPlayerView iMediaPlayerView) {
        iMediaPlayerView.setLooping(this.mIsLoopEnabled);
        if (z && this.mIsLoopEnabled && !this.mModel.isPlaying()) {
            resumeVideo();
        }
    }

    private void onFilmStripStateChanged(boolean z) {
        updateLoopEnabled(z, true);
    }

    private void updateLoopEnabled(boolean z, boolean z3) {
        if (this.mIsLoopEnabled == z) {
            this.mIsLoopEnabled = !z;
            Optional.ofNullable(this.mMediaPlayerView).ifPresent(new c(this, z3, 0));
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.FILM_STRIP_STATE_CHANGED, new d(3, this));
    }

    public boolean isLoopEnabled() {
        return this.mIsLoopEnabled;
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateLoopEnabled(this.mModel.getStateHelper().isFilmExpanded(this.mModel.getPosition()), false);
    }
}
