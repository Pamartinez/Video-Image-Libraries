package com.samsung.android.gallery.widget.videoview.mediaplayer;

import com.samsung.android.gallery.widget.capture.ClipViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IDelegateListener {
    ClipViewDelegate getClipViewDelegate();

    InstantSlowMoPlayDelegate getInstantSlowMoPlayDelegate();

    MediaPlayerDelegate getMediaPlayerDelegate();

    ScaleDelegate getScaleDelegate();

    int getSurfaceHeight();

    int getSurfaceWidth();
}
