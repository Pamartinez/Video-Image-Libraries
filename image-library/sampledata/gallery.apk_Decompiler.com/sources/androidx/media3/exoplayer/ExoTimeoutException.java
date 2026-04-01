package androidx.media3.exoplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExoTimeoutException extends RuntimeException {
    public final int timeoutOperation;

    public ExoTimeoutException(int i2) {
        super(getErrorMessage(i2));
        this.timeoutOperation = i2;
    }

    private static String getErrorMessage(int i2) {
        if (i2 == 1) {
            return "Player release timed out.";
        }
        if (i2 == 2) {
            return "Setting foreground mode timed out.";
        }
        if (i2 != 3) {
            return "Undefined timeout.";
        }
        return "Detaching surface timed out.";
    }
}
