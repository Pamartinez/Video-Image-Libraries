package androidx.transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        init();
    }

    private void init() {
        setOrdering(1);
        addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}
