package androidx.media3.common;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoFrameProcessingException extends Exception {
    public final long presentationTimeUs;

    public VideoFrameProcessingException(String str) {
        this(str, -9223372036854775807L);
    }

    public static VideoFrameProcessingException from(Exception exc) {
        return from(exc, -9223372036854775807L);
    }

    public VideoFrameProcessingException(String str, long j2) {
        super(str);
        this.presentationTimeUs = j2;
    }

    public static VideoFrameProcessingException from(Exception exc, long j2) {
        if (exc instanceof VideoFrameProcessingException) {
            return (VideoFrameProcessingException) exc;
        }
        return new VideoFrameProcessingException((Throwable) exc, j2);
    }

    public VideoFrameProcessingException(Throwable th) {
        this(th, -9223372036854775807L);
    }

    public VideoFrameProcessingException(Throwable th, long j2) {
        super(th);
        this.presentationTimeUs = j2;
    }
}
