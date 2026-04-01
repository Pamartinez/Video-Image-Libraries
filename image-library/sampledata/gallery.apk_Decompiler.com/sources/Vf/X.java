package Vf;

import cg.a;
import cg.o;
import ne.C1191i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class X extends C0886x {
    public static final /* synthetic */ int g = 0;
    public long d;
    public boolean e;
    public C1191i f;

    public final void l(boolean z) {
        long j2;
        long j3 = this.d;
        if (z) {
            j2 = 4294967296L;
        } else {
            j2 = 1;
        }
        long j8 = j3 - j2;
        this.d = j8;
        if (j8 <= 0 && this.e) {
            shutdown();
        }
    }

    public final C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        if (str != null) {
            return new o(this, str);
        }
        return this;
    }

    public final void m(K k) {
        C1191i iVar = this.f;
        if (iVar == null) {
            iVar = new C1191i();
            this.f = iVar;
        }
        iVar.addLast(k);
    }

    public abstract Thread n();

    public final void p(boolean z) {
        long j2;
        long j3 = this.d;
        if (z) {
            j2 = 4294967296L;
        } else {
            j2 = 1;
        }
        this.d = j2 + j3;
        if (!z) {
            this.e = true;
        }
    }

    public abstract long q();

    public final boolean r() {
        Object obj;
        C1191i iVar = this.f;
        if (iVar == null) {
            return false;
        }
        if (iVar.isEmpty()) {
            obj = null;
        } else {
            obj = iVar.removeFirst();
        }
        K k = (K) obj;
        if (k == null) {
            return false;
        }
        k.run();
        return true;
    }

    public void s(long j2, U u) {
        E.k.x(j2, u);
    }

    public abstract void shutdown();
}
