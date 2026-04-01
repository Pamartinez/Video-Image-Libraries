package com.samsung.android.gallery.app.ui.list.stories.spotify;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        SpotifySlideshowAdapter spotifySlideshowAdapter = (SpotifySlideshowAdapter) obj;
        switch (this.d) {
            case 0:
                spotifySlideshowAdapter.destroy();
                return;
            default:
                spotifySlideshowAdapter.moveSmooth(0);
                return;
        }
    }
}
