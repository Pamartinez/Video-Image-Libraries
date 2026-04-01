package androidx.media3.extractor.text;

import android.util.SparseArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SubtitleTranscodingExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput delegate;
    private boolean hasNonTextTracks;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final SparseArray<SubtitleTranscodingTrackOutput> textTrackOutputs = new SparseArray<>();

    public SubtitleTranscodingExtractorOutput(ExtractorOutput extractorOutput, SubtitleParser.Factory factory) {
        this.delegate = extractorOutput;
        this.subtitleParserFactory = factory;
    }

    public void endTracks() {
        this.delegate.endTracks();
        if (this.hasNonTextTracks) {
            for (int i2 = 0; i2 < this.textTrackOutputs.size(); i2++) {
                this.textTrackOutputs.valueAt(i2).shouldSuppressParsingErrors(true);
            }
        }
    }

    public void seekMap(SeekMap seekMap) {
        this.delegate.seekMap(seekMap);
    }

    public TrackOutput track(int i2, int i7) {
        if (i7 != 3) {
            this.hasNonTextTracks = true;
            return this.delegate.track(i2, i7);
        }
        SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput = this.textTrackOutputs.get(i2);
        if (subtitleTranscodingTrackOutput != null) {
            return subtitleTranscodingTrackOutput;
        }
        SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput2 = new SubtitleTranscodingTrackOutput(this.delegate.track(i2, i7), this.subtitleParserFactory);
        this.textTrackOutputs.put(i2, subtitleTranscodingTrackOutput2);
        return subtitleTranscodingTrackOutput2;
    }
}
