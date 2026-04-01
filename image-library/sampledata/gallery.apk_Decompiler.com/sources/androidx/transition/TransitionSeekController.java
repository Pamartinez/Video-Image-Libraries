package androidx.transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TransitionSeekController {
    void animateToEnd();

    void animateToStart(Runnable runnable);

    long getDurationMillis();

    boolean isReady();

    void setCurrentPlayTimeMillis(long j2);
}
