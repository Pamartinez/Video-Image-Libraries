package androidx.constraintlayout.motion.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Easing {
    public static String[] NAMED_EASING = {"standard", "accelerate", "decelerate", "linear"};
    static Easing sDefault = new Easing();
    String str = "identity";

    public String toString() {
        return this.str;
    }
}
