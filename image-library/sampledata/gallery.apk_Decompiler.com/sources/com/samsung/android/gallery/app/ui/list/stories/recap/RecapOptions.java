package com.samsung.android.gallery.app.ui.list.stories.recap;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapOptions extends Options {
    private Boolean mDefaultMuted;

    private boolean supportSharedTransition() {
        return PocFeatures.RECAP_SHARED_TRANSITION;
    }

    public boolean isAudioDefaultMuted() {
        if (this.mDefaultMuted == null) {
            this.mDefaultMuted = Boolean.valueOf(GalleryPreference.getInstance().loadBoolean(PreferenceName.STORY_HIGHLIGHT_AUDIO_MUTED, false));
        }
        return this.mDefaultMuted.booleanValue();
    }

    public boolean isRecap() {
        return true;
    }

    public boolean useDefaultEnterTransition() {
        return !supportSharedTransition();
    }

    public boolean useDefaultExitTransition() {
        return !supportSharedTransition();
    }
}
