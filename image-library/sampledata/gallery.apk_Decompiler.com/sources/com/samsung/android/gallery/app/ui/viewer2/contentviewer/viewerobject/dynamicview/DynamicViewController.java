package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview;

import c4.C0431a;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioController;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicViewController extends AudioController implements FragmentLifeCycle {
    private final boolean SUPPORT_BGM_SERVICE = Features.isEnabled(Features.SUPPORT_BGM_SERVICE);

    private boolean supportFragmentedBackgroundMusic() {
        return SdkConfig.atLeast(SdkConfig.SEM.S);
    }

    private void updateToolbarTitle() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            this.mActionInvoker.invoke(ViewerAction.SET_TOOLBAR_TITLE, null, mediaItem.getTitle());
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateToolbarTitle();
    }

    public boolean supportAudioController(MediaItem mediaItem) {
        if (!this.SUPPORT_BGM_SERVICE || !supportFragmentedBackgroundMusic() || mediaItem == null || !((Boolean) Optional.ofNullable(DynamicViewData.of(mediaItem).dynamicViewPlayInfo).map(new C0431a(1)).orElse(Boolean.FALSE)).booleanValue()) {
            return false;
        }
        return true;
    }
}
