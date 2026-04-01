package com.google.protobuf;

import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m0 extends o0 {
    public final /* synthetic */ int b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m0(Unsafe unsafe, int i2) {
        super(unsafe);
        this.b = i2;
    }

    public final void c(long j2, long j3, byte[] bArr) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final boolean d(Object obj, long j2) {
        switch (this.b) {
            case 0:
                if (p0.f1626h) {
                    if (p0.h(obj, j2) == 0) {
                        return false;
                    }
                } else if (p0.i(obj, j2) == 0) {
                    return false;
                }
                return true;
            default:
                if (p0.f1626h) {
                    if (p0.h(obj, j2) == 0) {
                        return false;
                    }
                } else if (p0.i(obj, j2) == 0) {
                    return false;
                }
                return true;
        }
    }

    public final byte e(long j2) {
        switch (this.b) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final byte f(Object obj, long j2) {
        switch (this.b) {
            case 0:
                if (p0.f1626h) {
                    return p0.h(obj, j2);
                }
                return p0.i(obj, j2);
            default:
                if (p0.f1626h) {
                    return p0.h(obj, j2);
                }
                return p0.i(obj, j2);
        }
    }

    public final double g(Object obj, long j2) {
        switch (this.b) {
            case 0:
                return Double.longBitsToDouble(j(obj, j2));
            default:
                return Double.longBitsToDouble(j(obj, j2));
        }
    }

    public final float h(Object obj, long j2) {
        switch (this.b) {
            case 0:
                return Float.intBitsToFloat(i(obj, j2));
            default:
                return Float.intBitsToFloat(i(obj, j2));
        }
    }

    public final void m(Object obj, long j2, boolean z) {
        switch (this.b) {
            case 0:
                if (p0.f1626h) {
                    p0.l(obj, j2, z ? (byte) 1 : 0);
                    return;
                } else {
                    p0.m(obj, j2, z ? (byte) 1 : 0);
                    return;
                }
            default:
                if (p0.f1626h) {
                    p0.l(obj, j2, z ? (byte) 1 : 0);
                    return;
                } else {
                    p0.m(obj, j2, z ? (byte) 1 : 0);
                    return;
                }
        }
    }

    public final void n(Object obj, long j2, byte b5) {
        switch (this.b) {
            case 0:
                if (p0.f1626h) {
                    p0.l(obj, j2, b5);
                    return;
                } else {
                    p0.m(obj, j2, b5);
                    return;
                }
            default:
                if (p0.f1626h) {
                    p0.l(obj, j2, b5);
                    return;
                } else {
                    p0.m(obj, j2, b5);
                    return;
                }
        }
    }

    public final void o(Object obj, long j2, double d) {
        switch (this.b) {
            case 0:
                r(obj, j2, Double.doubleToLongBits(d));
                return;
            default:
                r(obj, j2, Double.doubleToLongBits(d));
                return;
        }
    }

    public final void p(Object obj, long j2, float f) {
        switch (this.b) {
            case 0:
                q(Float.floatToIntBits(f), j2, obj);
                return;
            default:
                q(Float.floatToIntBits(f), j2, obj);
                return;
        }
    }

    public final boolean u() {
        switch (this.b) {
            case 0:
                return false;
            default:
                return false;
        }
    }
}
