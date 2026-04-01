package kg;

import ig.f;
import kotlin.jvm.internal.j;
import mg.e;

/* renamed from: kg.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1137s {
    public static final long[] e = new long[0];

    /* renamed from: a  reason: collision with root package name */
    public final f f4718a;
    public final e b;

    /* renamed from: c  reason: collision with root package name */
    public long f4719c;
    public final long[] d;

    public C1137s(f fVar, e eVar) {
        j.e(fVar, "descriptor");
        this.f4718a = fVar;
        this.b = eVar;
        int e7 = fVar.e();
        long j2 = 0;
        if (e7 <= 64) {
            this.f4719c = e7 != 64 ? -1 << e7 : j2;
            this.d = e;
            return;
        }
        this.f4719c = 0;
        int i2 = (e7 - 1) >>> 6;
        long[] jArr = new long[i2];
        if ((e7 & 63) != 0) {
            jArr[i2 - 1] = -1 << e7;
        }
        this.d = jArr;
    }
}
