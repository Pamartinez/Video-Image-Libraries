package androidx.media3.common;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline timeline2, int i2, long j2) {
        this.timeline = timeline2;
        this.windowIndex = i2;
        this.positionMs = j2;
    }
}
