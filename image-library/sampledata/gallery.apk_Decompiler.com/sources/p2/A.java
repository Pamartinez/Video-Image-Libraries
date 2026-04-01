package P2;

import P0.b;
import U2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class A extends p implements Comparable {
    public final int d;
    public int e;
    public z f;
    public int g;

    public A(int i2, int i7) {
        if (i2 <= 0 || ((i2 - 1) & i2) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        } else if (i7 >= -1) {
            this.d = i2;
            this.e = i7;
            this.f = null;
            this.g = -1;
        } else {
            throw new IllegalArgumentException("writeSize < -1");
        }
    }

    public final int c() {
        int i2 = this.e;
        if (i2 >= 0) {
            return i2;
        }
        throw new UnsupportedOperationException("writeSize is unknown");
    }

    public final int compareTo(Object obj) {
        A a7 = (A) obj;
        if (this == a7) {
            return 0;
        }
        q b = b();
        q b5 = a7.b();
        if (b != b5) {
            return b.compareTo(b5);
        }
        return e(a7);
    }

    public final void d(C0056f fVar, b bVar) {
        bVar.a(this.d);
        try {
            if (this.e >= 0) {
                int f5 = f();
                if (bVar.b == f5) {
                    k(fVar, bVar);
                    return;
                }
                throw new c("expected cursor " + f5 + "; actual value: " + bVar.b, (Exception) null);
            }
            throw new UnsupportedOperationException("writeSize is unknown");
        } catch (RuntimeException e7) {
            throw c.a(e7, "...while writing " + this);
        }
    }

    public int e(A a7) {
        throw new UnsupportedOperationException("unsupported");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        A a7 = (A) obj;
        if (b() == a7.b() && e(a7) == 0) {
            return true;
        }
        return false;
    }

    public final int f() {
        int i2 = this.g;
        if (i2 >= 0) {
            z zVar = this.f;
            if (i2 >= 0) {
                int i7 = zVar.d;
                if (i7 >= 0) {
                    return i7 + i2;
                }
                throw new RuntimeException("fileOffset not yet set");
            }
            zVar.getClass();
            throw new IllegalArgumentException("relative < 0");
        }
        throw new RuntimeException("offset not yet known");
    }

    public final String g() {
        return "[" + Integer.toHexString(f()) + ']';
    }

    public final int h(z zVar, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset < 0");
        } else if (this.f == null) {
            int i7 = this.d - 1;
            int i8 = (i2 + i7) & (~i7);
            this.f = zVar;
            this.g = i8;
            i(zVar, i8);
            return i8;
        } else {
            throw new RuntimeException("already written");
        }
    }

    public final void j(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("writeSize < 0");
        } else if (this.e < 0) {
            this.e = i2;
        } else {
            throw new UnsupportedOperationException("writeSize already set");
        }
    }

    public abstract void k(C0056f fVar, b bVar);

    public void i(z zVar, int i2) {
    }
}
