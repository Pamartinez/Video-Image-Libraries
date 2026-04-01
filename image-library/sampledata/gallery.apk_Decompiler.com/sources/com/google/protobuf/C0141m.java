package com.google.protobuf;

import java.io.IOException;
import java.util.List;

/* renamed from: com.google.protobuf.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0141m {

    /* renamed from: a  reason: collision with root package name */
    public final CodedInputStream f1619a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f1620c;
    public int d = 0;

    public C0141m(CodedInputStream codedInputStream) {
        D.a(codedInputStream, "input");
        this.f1619a = codedInputStream;
        codedInputStream.f1575c = this;
    }

    public static void w(int i2) {
        if ((i2 & 3) != 0) {
            throw F.f();
        }
    }

    public static void x(int i2) {
        if ((i2 & 7) != 0) {
            throw F.f();
        }
    }

    public final int a() {
        int i2 = this.d;
        if (i2 != 0) {
            this.b = i2;
            this.d = 0;
        } else {
            this.b = this.f1619a.z();
        }
        int i7 = this.b;
        if (i7 == 0 || i7 == this.f1620c) {
            return Integer.MAX_VALUE;
        }
        return i7 >>> 3;
    }

    public final void b(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int i2 = this.f1620c;
        this.f1620c = ((this.b >>> 3) << 3) | 4;
        try {
            schema.g(obj, this, extensionRegistryLite);
            if (this.b != this.f1620c) {
                throw F.f();
            }
        } finally {
            this.f1620c = i2;
        }
    }

    public final void c(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream codedInputStream = this.f1619a;
        int A10 = codedInputStream.A();
        if (codedInputStream.f1574a < codedInputStream.b) {
            int i2 = codedInputStream.i(A10);
            codedInputStream.f1574a++;
            schema.g(obj, this, extensionRegistryLite);
            codedInputStream.a(0);
            codedInputStream.f1574a--;
            codedInputStream.h(i2);
            return;
        }
        throw new IOException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final void d(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof C0134f;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            C0134f fVar = (C0134f) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    fVar.p(codedInputStream.j());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    fVar.p(codedInputStream.j());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Boolean.valueOf(codedInputStream.j()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Boolean.valueOf(codedInputStream.j()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final ByteString e() {
        v(2);
        return this.f1619a.k();
    }

    public final void f(List list) {
        int z;
        if ((this.b & 7) == 2) {
            do {
                list.add(e());
                CodedInputStream codedInputStream = this.f1619a;
                if (!codedInputStream.e()) {
                    z = codedInputStream.z();
                } else {
                    return;
                }
            } while (z == this.b);
            this.d = z;
            return;
        }
        throw F.c();
    }

    public final void g(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof C0144p;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            C0144p pVar = (C0144p) list;
            int i2 = this.b & 7;
            if (i2 == 1) {
                do {
                    pVar.p(codedInputStream.l());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int A10 = codedInputStream.A();
                x(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    pVar.p(codedInputStream.l());
                } while (codedInputStream.d() < d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 1) {
                do {
                    list.add(Double.valueOf(codedInputStream.l()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int A11 = codedInputStream.A();
                x(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Double.valueOf(codedInputStream.l()));
                } while (codedInputStream.d() < d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void h(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    b5.k(codedInputStream.m());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    b5.k(codedInputStream.m());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream.m()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Integer.valueOf(codedInputStream.m()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void i(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 2) {
                int A10 = codedInputStream.A();
                w(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    b5.k(codedInputStream.n());
                } while (codedInputStream.d() < d2);
            } else if (i2 == 5) {
                do {
                    b5.k(codedInputStream.n());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 2) {
                int A11 = codedInputStream.A();
                w(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Integer.valueOf(codedInputStream.n()));
                } while (codedInputStream.d() < d3);
            } else if (i7 == 5) {
                do {
                    list.add(Integer.valueOf(codedInputStream.n()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else {
                throw F.c();
            }
        }
    }

    public final void j(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof M;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            M m = (M) list;
            int i2 = this.b & 7;
            if (i2 == 1) {
                do {
                    m.p(codedInputStream.o());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int A10 = codedInputStream.A();
                x(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    m.p(codedInputStream.o());
                } while (codedInputStream.d() < d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 1) {
                do {
                    list.add(Long.valueOf(codedInputStream.o()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int A11 = codedInputStream.A();
                x(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Long.valueOf(codedInputStream.o()));
                } while (codedInputStream.d() < d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void k(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof C0151x;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            C0151x xVar = (C0151x) list;
            int i2 = this.b & 7;
            if (i2 == 2) {
                int A10 = codedInputStream.A();
                w(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    xVar.p(codedInputStream.p());
                } while (codedInputStream.d() < d2);
            } else if (i2 == 5) {
                do {
                    xVar.p(codedInputStream.p());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 2) {
                int A11 = codedInputStream.A();
                w(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Float.valueOf(codedInputStream.p()));
                } while (codedInputStream.d() < d3);
            } else if (i7 == 5) {
                do {
                    list.add(Float.valueOf(codedInputStream.p()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else {
                throw F.c();
            }
        }
    }

    public final void l(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    b5.k(codedInputStream.q());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    b5.k(codedInputStream.q());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream.q()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Integer.valueOf(codedInputStream.q()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void m(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof M;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            M m = (M) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    m.p(codedInputStream.r());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    m.p(codedInputStream.r());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream.r()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Long.valueOf(codedInputStream.r()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void n(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 2) {
                int A10 = codedInputStream.A();
                w(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    b5.k(codedInputStream.t());
                } while (codedInputStream.d() < d2);
            } else if (i2 == 5) {
                do {
                    b5.k(codedInputStream.t());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 2) {
                int A11 = codedInputStream.A();
                w(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Integer.valueOf(codedInputStream.t()));
                } while (codedInputStream.d() < d3);
            } else if (i7 == 5) {
                do {
                    list.add(Integer.valueOf(codedInputStream.t()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else {
                throw F.c();
            }
        }
    }

    public final void o(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof M;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            M m = (M) list;
            int i2 = this.b & 7;
            if (i2 == 1) {
                do {
                    m.p(codedInputStream.u());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int A10 = codedInputStream.A();
                x(A10);
                int d2 = codedInputStream.d() + A10;
                do {
                    m.p(codedInputStream.u());
                } while (codedInputStream.d() < d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 1) {
                do {
                    list.add(Long.valueOf(codedInputStream.u()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int A11 = codedInputStream.A();
                x(A11);
                int d3 = codedInputStream.d() + A11;
                do {
                    list.add(Long.valueOf(codedInputStream.u()));
                } while (codedInputStream.d() < d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void p(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    b5.k(codedInputStream.v());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    b5.k(codedInputStream.v());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream.v()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Integer.valueOf(codedInputStream.v()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void q(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof M;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            M m = (M) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    m.p(codedInputStream.w());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    m.p(codedInputStream.w());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream.w()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Long.valueOf(codedInputStream.w()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void r(List list, boolean z) {
        String str;
        int z3;
        int z7;
        if ((this.b & 7) == 2) {
            boolean z9 = list instanceof I;
            CodedInputStream codedInputStream = this.f1619a;
            if (!z9 || z) {
                do {
                    if (z) {
                        v(2);
                        str = codedInputStream.y();
                    } else {
                        v(2);
                        str = codedInputStream.x();
                    }
                    list.add(str);
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
                return;
            }
            I i2 = (I) list;
            do {
                i2.f(e());
                if (!codedInputStream.e()) {
                    z7 = codedInputStream.z();
                } else {
                    return;
                }
            } while (z7 == this.b);
            this.d = z7;
            return;
        }
        throw F.c();
    }

    public final void s(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof B;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            B b5 = (B) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    b5.k(codedInputStream.A());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    b5.k(codedInputStream.A());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream.A()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Integer.valueOf(codedInputStream.A()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void t(List list) {
        int z;
        int z3;
        boolean z7 = list instanceof M;
        CodedInputStream codedInputStream = this.f1619a;
        if (z7) {
            M m = (M) list;
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    m.p(codedInputStream.B());
                    if (!codedInputStream.e()) {
                        z3 = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z3 == this.b);
                this.d = z3;
            } else if (i2 == 2) {
                int d2 = codedInputStream.d() + codedInputStream.A();
                do {
                    m.p(codedInputStream.B());
                } while (codedInputStream.d() < d2);
                u(d2);
            } else {
                throw F.c();
            }
        } else {
            int i7 = this.b & 7;
            if (i7 == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream.B()));
                    if (!codedInputStream.e()) {
                        z = codedInputStream.z();
                    } else {
                        return;
                    }
                } while (z == this.b);
                this.d = z;
            } else if (i7 == 2) {
                int d3 = codedInputStream.d() + codedInputStream.A();
                do {
                    list.add(Long.valueOf(codedInputStream.B()));
                } while (codedInputStream.d() < d3);
                u(d3);
            } else {
                throw F.c();
            }
        }
    }

    public final void u(int i2) {
        if (this.f1619a.d() != i2) {
            throw F.g();
        }
    }

    public final void v(int i2) {
        if ((this.b & 7) != i2) {
            throw F.c();
        }
    }
}
