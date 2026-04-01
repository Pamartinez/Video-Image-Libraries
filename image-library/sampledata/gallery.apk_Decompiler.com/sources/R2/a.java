package R2;

import U2.c;
import U2.f;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final int f645a;
    public final g b;

    /* renamed from: c  reason: collision with root package name */
    public final f f646c;
    public final int d;

    public a(int i2, g gVar, f fVar, int i7) {
        int i8;
        int i10;
        if (i2 >= 0) {
            try {
                if (!gVar.d) {
                    int length = gVar.e.length;
                    if (length != 0) {
                        int i11 = length - 2;
                        while (true) {
                            boolean z = true;
                            if (i11 >= 0) {
                                if (((f) gVar.d(i11)).d.e == 1) {
                                    i11--;
                                } else {
                                    throw new IllegalArgumentException(C0212a.j(i11, "insns[", "] is a branch or can throw"));
                                }
                            } else if (((f) gVar.d(length - 1)).d.e != 1) {
                                try {
                                    if (!fVar.d) {
                                        int i12 = -1;
                                        if (i7 >= -1) {
                                            if (i7 >= 0) {
                                                int i13 = fVar.f;
                                                if (!fVar.g) {
                                                    i8 = 0;
                                                    while (true) {
                                                        if (i8 >= i13) {
                                                            i8 = -i13;
                                                            break;
                                                        } else if (fVar.e[i8] == i7) {
                                                            break;
                                                        } else {
                                                            i8++;
                                                        }
                                                    }
                                                } else {
                                                    int i14 = -1;
                                                    i8 = i13;
                                                    while (i8 > i14 + 1) {
                                                        int i15 = ((i8 - i14) >> 1) + i14;
                                                        if (i7 <= fVar.e[i15]) {
                                                            i8 = i15;
                                                        } else {
                                                            i14 = i15;
                                                        }
                                                    }
                                                    if (i8 == i13) {
                                                        i10 = -i13;
                                                    } else if (i7 != fVar.e[i8]) {
                                                        i10 = -i8;
                                                    }
                                                    i8 = i10 - 1;
                                                }
                                                if (!((i8 >= 0 ? i8 : i12) < 0 ? false : z)) {
                                                    throw new IllegalArgumentException("primarySuccessor " + i7 + " not in successors " + fVar);
                                                }
                                            }
                                            this.f645a = i2;
                                            this.b = gVar;
                                            this.f646c = fVar;
                                            this.d = i7;
                                            return;
                                        }
                                        throw new IllegalArgumentException("primarySuccessor < -1");
                                    }
                                    throw new c("mutable instance", (Exception) null);
                                } catch (NullPointerException unused) {
                                    throw new NullPointerException("successors == null");
                                }
                            } else {
                                throw new IllegalArgumentException("insns does not end with a branch or throwing instruction");
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("insns.size() == 0");
                    }
                } else {
                    throw new c("mutable instance", (Exception) null);
                }
            } catch (NullPointerException unused2) {
                throw new NullPointerException("insns == null");
            }
        } else {
            throw new IllegalArgumentException("label < 0");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "{" + L2.a.D(this.f645a) + '}';
    }
}
