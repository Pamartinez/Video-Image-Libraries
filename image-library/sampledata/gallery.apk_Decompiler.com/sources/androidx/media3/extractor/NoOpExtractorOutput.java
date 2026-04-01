package androidx.media3.extractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NoOpExtractorOutput implements ExtractorOutput {
    public TrackOutput track(int i2, int i7) {
        return new DiscardingTrackOutput();
    }

    public void endTracks() {
    }

    public void seekMap(SeekMap seekMap) {
    }
}
