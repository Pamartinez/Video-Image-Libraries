package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WebvttCueInfo {
    public final Cue cue;
    public final long endTimeUs;
    public final long startTimeUs;

    public WebvttCueInfo(Cue cue2, long j2, long j3) {
        this.cue = cue2;
        this.startTimeUs = j2;
        this.endTimeUs = j3;
    }
}
