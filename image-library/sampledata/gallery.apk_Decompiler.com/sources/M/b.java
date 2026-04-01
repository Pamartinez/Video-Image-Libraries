package M;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ExtractorsFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DefaultMediaSourceFactory f416a;
    public final /* synthetic */ Format b;

    public /* synthetic */ b(DefaultMediaSourceFactory defaultMediaSourceFactory, Format format) {
        this.f416a = defaultMediaSourceFactory;
        this.b = format;
    }

    public final Extractor[] createExtractors() {
        return this.f416a.lambda$createMediaSource$0(this.b);
    }
}
