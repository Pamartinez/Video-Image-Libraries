package ge;

import G0.c;
import com.samsung.android.gallery.support.utils.MapUtil;
import ee.a0;
import io.grpc.a;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* renamed from: ge.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1049p implements Runnable {
    public final long d;
    public final /* synthetic */ C1051q e;

    public C1049p(C1051q qVar, long j2) {
        this.e = qVar;
        this.d = j2;
    }

    public final void run() {
        double d2;
        c cVar = new c(11);
        C1051q qVar = this.e;
        qVar.l.e(cVar);
        long j2 = this.d;
        long abs = Math.abs(j2);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long nanos = abs / timeUnit.toNanos(1);
        long abs2 = Math.abs(j2) % timeUnit.toNanos(1);
        StringBuilder sb2 = new StringBuilder("deadline exceeded after ");
        if (j2 < 0) {
            sb2.append('-');
        }
        sb2.append(nanos);
        Locale locale = Locale.US;
        sb2.append(String.format(locale, ".%09d", new Object[]{Long.valueOf(abs2)}));
        sb2.append("s. ");
        Long l = (Long) qVar.k.a(a.d);
        if (l == null) {
            d2 = MapUtil.INVALID_LOCATION;
        } else {
            d2 = ((double) l.longValue()) / C1051q.u;
        }
        sb2.append(String.format(locale, "Name resolution delay %.9f seconds. ", new Object[]{Double.valueOf(d2)}));
        sb2.append(cVar);
        qVar.l.v(a0.f4286h.b(sb2.toString()));
    }
}
