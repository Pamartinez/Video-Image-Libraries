package androidx.work;

import Ae.a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a3\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u0002\u0007\n\u0005\b20\u0001¨\u0006\b"}, d2 = {"T", "Landroidx/work/Tracer;", "", "label", "Lkotlin/Function0;", "block", "traced", "(Landroidx/work/Tracer;Ljava/lang/String;LAe/a;)Ljava/lang/Object;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TracerKt {
    public static final <T> T traced(Tracer tracer, String str, a aVar) {
        j.e(tracer, "<this>");
        j.e(str, "label");
        j.e(aVar, "block");
        boolean isEnabled = tracer.isEnabled();
        if (isEnabled) {
            try {
                tracer.beginSection(str);
            } catch (Throwable th) {
                if (isEnabled) {
                    tracer.endSection();
                }
                throw th;
            }
        }
        T invoke = aVar.invoke();
        if (isEnabled) {
            tracer.endSection();
        }
        return invoke;
    }
}
