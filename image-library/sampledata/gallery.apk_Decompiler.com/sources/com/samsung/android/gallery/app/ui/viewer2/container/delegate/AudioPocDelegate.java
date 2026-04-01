package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioPocDelegate extends AbsVuDelegate<IVuContainerView> {
    public AudioPocDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void onAudioMuteChanged(Object... objArr) {
        if (PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteUntilAppDestroy)) {
            this.mBlackboard.publish("poc_viewer_video_un_mute_state", Boolean.valueOf(((ContainerModel) this.mModel).isAudioMute()));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteAlways)) {
            ((ContainerModel) this.mModel).setAudioMute(false);
        } else if (PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteUntilAppDestroy)) {
            ((ContainerModel) this.mModel).setAudioMute(((Boolean) this.mBlackboard.read("poc_viewer_video_un_mute_state", Boolean.TRUE)).booleanValue());
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        if (PocFeatures.isEnabled(PocFeatures.SetAudioUnMuteUntilAppDestroy)) {
            actionInvoker.add(ViewerAction.AUDIO_MUTE_CHANGED, new d(16, this));
        }
    }
}
