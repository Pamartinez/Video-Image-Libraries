package androidx.media3.extractor;

import P.a;
import android.net.Uri;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = new a(0);

    /* access modifiers changed from: private */
    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[0];
    }

    Extractor[] createExtractors();

    Extractor[] createExtractors(Uri uri, Map<String, List<String>> map) {
        return createExtractors();
    }

    ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i2) {
        return this;
    }

    @Deprecated
    ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        return this;
    }

    ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return this;
    }
}
