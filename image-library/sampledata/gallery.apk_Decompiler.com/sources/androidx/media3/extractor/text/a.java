package androidx.media3.extractor.text;

import androidx.media3.common.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ SubtitleTranscodingTrackOutput d;
    public final /* synthetic */ long e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput, long j2, int i2) {
        this.d = subtitleTranscodingTrackOutput;
        this.e = j2;
        this.f = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$sampleMetadata$0(this.e, this.f, (CuesWithTiming) obj);
    }
}
