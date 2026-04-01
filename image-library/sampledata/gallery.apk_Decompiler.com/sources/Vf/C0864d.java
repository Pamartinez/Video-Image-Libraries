package Vf;

import kotlin.jvm.internal.j;

/* renamed from: Vf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0864d implements C0872i {

    /* renamed from: a  reason: collision with root package name */
    public final C0862c[] f3855a;

    public C0864d(C0862c[] cVarArr) {
        this.f3855a = cVarArr;
    }

    public final void a(Throwable th) {
        b();
    }

    public final void b() {
        C0862c[] cVarArr = this.f3855a;
        int length = cVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            O o2 = cVarArr[i2].f3852i;
            if (o2 != null) {
                o2.a();
                i2++;
            } else {
                j.k("handle");
                throw null;
            }
        }
    }

    public final String toString() {
        return "DisposeHandlersOnCancel[" + this.f3855a + ']';
    }
}
