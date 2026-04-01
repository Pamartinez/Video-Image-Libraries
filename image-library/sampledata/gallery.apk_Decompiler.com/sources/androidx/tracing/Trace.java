package androidx.tracing;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Trace {
    public static void beginAsyncSection(String str, int i2) {
        TraceApi29Impl.beginAsyncSection(truncatedTraceSectionLabel(str), i2);
    }

    public static void beginSection(String str) {
        TraceApi18Impl.beginSection(truncatedTraceSectionLabel(str));
    }

    public static void endAsyncSection(String str, int i2) {
        TraceApi29Impl.endAsyncSection(truncatedTraceSectionLabel(str), i2);
    }

    public static void endSection() {
        TraceApi18Impl.endSection();
    }

    public static boolean isEnabled() {
        return TraceApi29Impl.isEnabled();
    }

    private static String truncatedTraceSectionLabel(String str) {
        if (str.length() <= 127) {
            return str;
        }
        return str.substring(0, 127);
    }
}
