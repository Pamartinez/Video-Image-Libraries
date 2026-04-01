package ge;

import ee.C0970c;
import ee.C0971d;
import ee.C0990x;
import java.text.MessageFormat;
import java.util.logging.Level;

/* renamed from: ge.h0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1028h0 extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public C0990x f4515c;

    public final void b(C0970c cVar, String str) {
        C0990x xVar = this.f4515c;
        Level l = C1027h.l(cVar);
        if (C1036k.f4527c.isLoggable(l)) {
            C1036k.a(xVar, l, str);
        }
    }

    public final void c(C0970c cVar, String str, Object... objArr) {
        C0990x xVar = this.f4515c;
        Level l = C1027h.l(cVar);
        if (C1036k.f4527c.isLoggable(l)) {
            C1036k.a(xVar, l, MessageFormat.format(str, objArr));
        }
    }
}
