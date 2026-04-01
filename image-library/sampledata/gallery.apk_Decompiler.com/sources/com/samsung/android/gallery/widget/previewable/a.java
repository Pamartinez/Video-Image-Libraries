package com.samsung.android.gallery.widget.previewable;

import android.media.PlaybackParams;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaPlayerCompat.OnPlayBackCompleteListener {
    public final /* synthetic */ PlaybackPreviewVideo d;
    public final /* synthetic */ AtomicInteger e;
    public final /* synthetic */ MediaPlayerCompat f;
    public final /* synthetic */ AtomicInteger g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PlaybackParams f3219h;

    public /* synthetic */ a(PlaybackPreviewVideo playbackPreviewVideo, AtomicInteger atomicInteger, MediaPlayerCompat mediaPlayerCompat, AtomicInteger atomicInteger2, PlaybackParams playbackParams) {
        this.d = playbackPreviewVideo;
        this.e = atomicInteger;
        this.f = mediaPlayerCompat;
        this.g = atomicInteger2;
        this.f3219h = playbackParams;
    }

    public final void onPlaybackComplete(MediaPlayerCompat mediaPlayerCompat) {
        this.d.lambda$preparePlayback$0(this.e, this.f, this.g, this.f3219h, mediaPlayerCompat);
    }
}
