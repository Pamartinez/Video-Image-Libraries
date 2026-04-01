package ge;

import A0.l;
import ee.C0971d;
import ee.M;
import ee.a0;
import fe.e;
import fe.i;
import ie.C1097a;
import ie.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1039l extends C1068z {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1039l(C1051q qVar, C0971d dVar, String str) {
        super(qVar.f4536h);
        this.f = dVar;
        this.g = str;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [ee.M, java.lang.Object] */
    public final void a() {
        switch (this.e) {
            case 0:
                a0 a0Var = a0.n;
                ((C0971d) this.f).d(a0Var.g("Unable to find compressor by name " + ((String) this.g)), new Object());
                return;
            case 1:
                l lVar = (l) this.g;
                b.b();
                try {
                    b.a();
                    b.f4600a.getClass();
                    if (((a0) lVar.f) == null) {
                        ((C0971d) lVar.e).f((M) this.f);
                    }
                } catch (Throwable th) {
                    try {
                        b.f4600a.getClass();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
                b.f4600a.getClass();
                return;
            default:
                b.b();
                try {
                    b.a();
                    C1097a aVar = b.f4600a;
                    aVar.getClass();
                    b();
                    aVar.getClass();
                    return;
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                    break;
                }
        }
        throw th;
    }

    public void b() {
        InputStream g3;
        i iVar = (i) this.f;
        l lVar = (l) this.g;
        C1051q qVar = (C1051q) lVar.g;
        if (((a0) lVar.f) != null) {
            Logger logger = Z.f4489a;
            while (true) {
                InputStream g10 = iVar.g();
                if (g10 != null) {
                    try {
                        ((e) g10).close();
                    } catch (IOException e7) {
                        Z.f4489a.log(Level.WARNING, "exception caught in closeQuietly", e7);
                    }
                } else {
                    return;
                }
            }
        } else {
            while (true) {
                try {
                    g3 = iVar.g();
                    if (g3 != null) {
                        ((C0971d) lVar.e).g(qVar.d.b(g3));
                        g3.close();
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    Logger logger2 = Z.f4489a;
                    while (true) {
                        InputStream g11 = iVar.g();
                        if (g11 != null) {
                            try {
                                ((e) g11).close();
                            } catch (IOException e8) {
                                Z.f4489a.log(Level.WARNING, "exception caught in closeQuietly", e8);
                            }
                        } else {
                            a0 g12 = a0.f.f(th).g("Failed to read message.");
                            lVar.f = g12;
                            qVar.l.v(g12);
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1039l(l lVar, M m) {
        super(((C1051q) lVar.g).f4536h);
        this.g = lVar;
        this.f = m;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1039l(l lVar, i iVar) {
        super(((C1051q) lVar.g).f4536h);
        this.g = lVar;
        this.f = iVar;
    }
}
