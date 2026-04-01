package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Fa.F;
import H7.w;
import H7.x;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageScroll3dEffect extends ViewerObject implements FragmentLifeCycle {
    private IMediaPlayerView mMediaView;
    private boolean mSet3dEffectPosition = false;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public void onPageScrollDone(Object... objArr) {
        if (this.mSet3dEffectPosition) {
            Optional.ofNullable(this.mMediaView).ifPresent(new F(26));
            this.mSet3dEffectPosition = false;
        }
    }

    /* access modifiers changed from: private */
    public void onPageScrolled(Object... objArr) {
        float f;
        if (!this.mModel.getContainerModel().isAudioMute()) {
            int intValue = objArr[0].intValue();
            float floatValue = objArr[1].floatValue();
            if (intValue == this.mModel.getPosition()) {
                f = floatValue * -1.0f;
            } else {
                f = 1.0f - floatValue;
            }
            Optional.ofNullable(this.mMediaView).ifPresent(new x(f, 0));
            this.mSet3dEffectPosition = true;
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new w(this, 0));
        this.mActionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLLED, new w(this, 1));
        this.mActionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLL_DONE, new w(this, 2));
    }

    public void onViewDetached() {
        if (this.mSet3dEffectPosition) {
            Optional.ofNullable(this.mMediaView).ifPresent(new F(26));
            this.mSet3dEffectPosition = false;
        }
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mSet3dEffectPosition = false;
    }
}
