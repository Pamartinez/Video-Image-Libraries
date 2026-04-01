package androidx.tracing;

import android.os.Trace;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TraceApi29Impl {
    public static void beginAsyncSection(String str, int i2) {
        Trace.beginAsyncSection(str, i2);
    }

    public static void endAsyncSection(String str, int i2) {
        Trace.endAsyncSection(str, i2);
    }

    public static boolean isEnabled() {
        return Trace.isEnabled();
    }
}
