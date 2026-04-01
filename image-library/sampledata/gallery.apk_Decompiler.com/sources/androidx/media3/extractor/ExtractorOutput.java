package androidx.media3.extractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ExtractorOutput {
    public static final ExtractorOutput PLACEHOLDER = new ExtractorOutput() {
        public void endTracks() {
            throw new UnsupportedOperationException();
        }

        public void seekMap(SeekMap seekMap) {
            throw new UnsupportedOperationException();
        }

        public TrackOutput track(int i2, int i7) {
            throw new UnsupportedOperationException();
        }
    };

    void endTracks();

    void seekMap(SeekMap seekMap);

    TrackOutput track(int i2, int i7);
}
