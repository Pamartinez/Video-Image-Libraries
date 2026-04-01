package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.ISlideshowV2View;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowOptions<T extends ISlideshowV2View> extends Options {
    private static final boolean SUPPORT_BGM = PocFeatures.isEnabled(PocFeatures.SlideshowV2Bgm);
    final T mView;

    public SlideshowOptions(T t) {
        this.mView = t;
    }

    public boolean isAudioDefaultMuted() {
        if (this.mView.fromSelection() || !TextUtils.isEmpty(this.mView.getBgmName())) {
            return false;
        }
        return true;
    }

    public boolean isSlideshow() {
        return true;
    }

    public boolean supportBgm() {
        if (!Features.isEnabled(Features.SUPPORT_BGM_SERVICE)) {
            return false;
        }
        if (SUPPORT_BGM || !TextUtils.isEmpty(this.mView.getBgmName()) || this.mView.fromSelection()) {
            return true;
        }
        return false;
    }

    public boolean supportFilter() {
        return !TextUtils.isEmpty(this.mView.getFilterName());
    }
}
