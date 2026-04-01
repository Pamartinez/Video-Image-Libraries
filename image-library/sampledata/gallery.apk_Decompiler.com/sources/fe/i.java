package fe;

import G0.a;
import He.F;
import android.os.Parcel;
import ee.C0969b;
import ee.C0984q;
import ee.M;
import ee.a0;
import ee.b0;
import ge.C1054s;
import ge.C1056t;
import ge.M1;
import io.grpc.binder.e;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final c f4350a;
    public final C0969b b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4351c;
    public q d;
    public M1 e;
    public C1056t f;
    public e g;

    /* renamed from: h  reason: collision with root package name */
    public int f4352h;

    /* renamed from: i  reason: collision with root package name */
    public int f4353i;

    /* renamed from: j  reason: collision with root package name */
    public ArrayList f4354j;
    public boolean k;
    public int l;
    public int m;
    public j n = j.UNINITIALIZED;

    /* renamed from: o  reason: collision with root package name */
    public int f4355o;

    /* renamed from: p  reason: collision with root package name */
    public int f4356p;
    public boolean q;
    public boolean r;
    public final boolean s;
    public a0 t;
    public M u;

    public i(c cVar, C0969b bVar, int i2, boolean z) {
        this.f4350a = cVar;
        this.b = bVar;
        this.f4351c = i2;
        this.s = z;
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [fe.e, java.io.InputStream] */
    public final e a() {
        e eVar;
        int i2 = this.f4353i;
        this.f4353i = 0;
        if (i2 == 1) {
            k kVar = (k) this.f4354j.remove(0);
            int i7 = kVar.b;
            eVar = new e(kVar.f4357a);
        } else {
            byte[][] bArr = new byte[i2][];
            int i8 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                byte[] bArr2 = ((k) this.f4354j.remove(0)).f4357a;
                bArr[i10] = bArr2;
                i8 += bArr2.length;
            }
            ? inputStream = new InputStream();
            inputStream.d = bArr;
            inputStream.f4344h = i8;
            if (i2 > 0) {
                inputStream.e = bArr[0];
            }
            eVar = inputStream;
        }
        this.f4352h += i2;
        f();
        return eVar;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [ee.M, java.lang.Object] */
    public final void b(a0 a0Var, a0 a0Var2, boolean z) {
        boolean z3;
        a f5;
        j jVar = this.n;
        j jVar2 = j.CLOSED;
        if (jVar != jVar2) {
            if (jVar != j.UNINITIALIZED) {
                z3 = true;
            } else {
                z3 = false;
            }
            h(jVar2);
            if (z3) {
                M1 m12 = this.e;
                if (m12.b.compareAndSet(false, true)) {
                    for (C0984q qVar : m12.f4461a) {
                        qVar.getClass();
                    }
                }
            }
            if (!z) {
                c cVar = this.f4350a;
                int i2 = this.f4351c;
                try {
                    f5 = a.f();
                    f5.c().writeInt(0);
                    Parcel c5 = f5.c();
                    int c6 = a0Var.f4290a.c() << 16;
                    String str = a0Var.b;
                    if (str != null && str.length() > 1000) {
                        str = str.substring(0, 1000);
                    }
                    if (str != null) {
                        c6 |= 32;
                        c5.writeString(str);
                    }
                    Parcel c8 = f5.c();
                    int i7 = c6 | 8;
                    int dataPosition = c8.dataPosition();
                    c8.setDataPosition(0);
                    c8.writeInt(i7);
                    c8.setDataPosition(dataPosition);
                    cVar.m(i2, f5);
                    f5.close();
                } catch (b0 e7) {
                    c.v.log(Level.FINER, "Failed sending oob close transaction", e7);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (z3) {
                this.f.u0(a0Var2, C1054s.PROCESSED, new Object());
            }
            k();
            return;
        }
        return;
        throw th;
    }

    public final void c() {
        if (!this.q) {
            this.q = true;
            while (true) {
                int[] iArr = h.f4349a;
                int i2 = iArr[this.n.ordinal()];
                if (i2 == 1) {
                    if (this.f != null && !this.r) {
                        if (this.g == null && this.f4353i <= 0) {
                            if (this.k) {
                                if (this.f4352h < this.l) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else if (this.f4356p == 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else if (i2 == 2) {
                    if (this.f != null) {
                        if (!this.k) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
                int i7 = iArr[this.n.ordinal()];
                if (i7 != 1) {
                    if (i7 != 2) {
                        throw new AssertionError();
                    }
                } else if (!this.r) {
                    if (this.g != null || this.f4353i > 0) {
                        this.r = true;
                        this.f.h0(this);
                    } else if (this.k) {
                        h(j.ALL_MESSAGES_DELIVERED);
                    }
                }
                if (this.k) {
                    h(j.SUFFIX_DELIVERED);
                    for (C0984q qVar : this.e.f4461a) {
                        ((io.grpc.a) qVar).getClass();
                    }
                    M1 m12 = this.e;
                    if (m12.b.compareAndSet(false, true)) {
                        for (C0984q qVar2 : m12.f4461a) {
                            qVar2.getClass();
                        }
                    }
                    h(j.CLOSED);
                    this.f.u0(this.t, C1054s.PROCESSED, this.u);
                    k();
                }
            }
            this.q = false;
        }
    }

    public final void d(int i2, int i7, Parcel parcel) {
        boolean z;
        if ((i2 & 64) == 0) {
            int readInt = parcel.readInt();
            byte[] a7 = f.a(readInt);
            if (readInt > 0) {
                parcel.readByteArray(a7);
            }
            boolean z3 = false;
            if ((i2 & 128) != 0) {
                z = false;
            } else {
                z = true;
            }
            if (this.f4354j == null) {
                if (this.f4355o == 0 && z && i7 == this.f4352h) {
                    if (this.g == null) {
                        z3 = true;
                    }
                    F.r(z3);
                    this.g = new e(a7);
                    i();
                    return;
                }
                this.f4354j = new ArrayList(16);
            }
            k kVar = new k(a7, readInt, z);
            int i8 = i7 - this.f4352h;
            if (i8 < this.f4354j.size()) {
                this.f4354j.set(i8, kVar);
                f();
            } else if (i8 > this.f4354j.size()) {
                do {
                    this.f4354j.add((Object) null);
                } while (i8 > this.f4354j.size());
                this.f4354j.add(kVar);
            } else {
                this.f4354j.add(kVar);
                f();
            }
        } else {
            e eVar = (e) this.b.f4292a.get(c.f4336x);
            throw new b0(a0.f4287i.g("Parcelable messages not allowed"));
        }
    }

    public final void e(Parcel parcel) {
        M i02 = C0246a.i0(parcel, this.b);
        for (C0984q qVar : this.e.f4461a) {
            ((io.grpc.a) qVar).getClass();
        }
        this.f.z(i02);
    }

    public final void f() {
        k kVar;
        if (this.f4353i == 0) {
            int i2 = 0;
            while (i2 < this.f4354j.size() && (kVar = (k) this.f4354j.get(i2)) != null) {
                if (kVar.f4358c) {
                    this.f4353i = i2 + 1;
                    i();
                    return;
                }
                i2++;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001d A[Catch:{ all -> 0x000b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.io.InputStream g() {
        /*
            r5 = this;
            monitor-enter(r5)
            fe.e r0 = r5.g     // Catch:{ all -> 0x000b }
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x000d
            r5.g = r3     // Catch:{ all -> 0x000b }
            goto L_0x0023
        L_0x000b:
            r0 = move-exception
            goto L_0x0050
        L_0x000d:
            int r4 = r5.f4356p     // Catch:{ all -> 0x000b }
            if (r4 <= 0) goto L_0x0022
            if (r0 != 0) goto L_0x001a
            int r0 = r5.f4353i     // Catch:{ all -> 0x000b }
            if (r0 <= 0) goto L_0x0018
            goto L_0x001a
        L_0x0018:
            r0 = r2
            goto L_0x001b
        L_0x001a:
            r0 = r1
        L_0x001b:
            if (r0 == 0) goto L_0x0022
            fe.e r0 = r5.a()     // Catch:{ all -> 0x000b }
            goto L_0x0023
        L_0x0022:
            r0 = r3
        L_0x0023:
            if (r0 == 0) goto L_0x002b
            int r2 = r5.f4356p     // Catch:{ all -> 0x000b }
            int r2 = r2 - r1
            r5.f4356p = r2     // Catch:{ all -> 0x000b }
            goto L_0x004e
        L_0x002b:
            r5.r = r2     // Catch:{ all -> 0x000b }
            boolean r3 = r5.k     // Catch:{ all -> 0x000b }
            if (r3 == 0) goto L_0x0039
            int r3 = r5.f4352h     // Catch:{ all -> 0x000b }
            int r4 = r5.l     // Catch:{ all -> 0x000b }
            if (r3 < r4) goto L_0x0039
            r3 = r1
            goto L_0x003a
        L_0x0039:
            r3 = r2
        L_0x003a:
            if (r3 == 0) goto L_0x004e
            fe.j r3 = r5.n     // Catch:{ all -> 0x000b }
            fe.j r4 = fe.j.CLOSED     // Catch:{ all -> 0x000b }
            if (r3 != r4) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r1 = r2
        L_0x0044:
            if (r1 != 0) goto L_0x004e
            fe.j r1 = fe.j.ALL_MESSAGES_DELIVERED     // Catch:{ all -> 0x000b }
            r5.h(r1)     // Catch:{ all -> 0x000b }
            r5.c()     // Catch:{ all -> 0x000b }
        L_0x004e:
            monitor-exit(r5)
            return r0
        L_0x0050:
            monitor-exit(r5)     // Catch:{ all -> 0x000b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.i.g():java.io.InputStream");
    }

    public final void h(j jVar) {
        j jVar2 = this.n;
        int i2 = h.f4349a[jVar.ordinal()];
        boolean z = false;
        if (i2 == 1) {
            if (jVar2 == j.INITIALIZED || jVar2 == j.UNINITIALIZED) {
                z = true;
            }
            F.s(z, jVar2, jVar);
        } else if (i2 == 2) {
            if (jVar2 == j.PREFIX_DELIVERED) {
                z = true;
            }
            F.s(z, jVar2, jVar);
        } else if (i2 == 3) {
            if (jVar2 == j.UNINITIALIZED) {
                z = true;
            }
            F.s(z, jVar2, jVar);
        } else if (i2 == 4) {
            if (jVar2 == j.ALL_MESSAGES_DELIVERED) {
                z = true;
            }
            F.s(z, jVar2, jVar);
        } else if (i2 != 5) {
            throw new AssertionError();
        }
        this.n = jVar;
    }

    public final void i() {
        this.e.getClass();
        for (C0984q qVar : this.e.f4461a) {
            qVar.getClass();
        }
        for (C0984q qVar2 : this.e.f4461a) {
            qVar2.getClass();
        }
        this.f4355o++;
    }

    public final void j(int i2) {
        int i7 = this.m + i2;
        this.m = i7;
        M1 m12 = this.e;
        if (m12 != null && i7 != 0) {
            for (C0984q qVar : m12.f4461a) {
                qVar.getClass();
            }
            for (C0984q qVar2 : this.e.f4461a) {
                qVar2.getClass();
            }
            this.m = 0;
        }
    }

    public final void k() {
        boolean z = this.s;
        c cVar = this.f4350a;
        if (z && cVar.r.decrementAndGet() == 0) {
            cVar.t.g(false);
        }
        ConcurrentHashMap concurrentHashMap = cVar.e;
        if (concurrentHashMap.remove(Integer.valueOf(this.f4351c)) != null && concurrentHashMap.isEmpty()) {
            cVar.b.execute(new C0998a(cVar, 0));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String toString() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r0.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.Class<fe.i> r1 = fe.i.class
            java.lang.String r1 = r1.getSimpleName()     // Catch:{ all -> 0x0052 }
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "[SfxA="
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            boolean r1 = r4.k     // Catch:{ all -> 0x0052 }
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "/De="
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            fe.j r1 = r4.n     // Catch:{ all -> 0x0052 }
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "/Msg="
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            fe.e r1 = r4.g     // Catch:{ all -> 0x0052 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0035
            int r1 = r4.f4353i     // Catch:{ all -> 0x0052 }
            if (r1 <= 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r1 = r3
            goto L_0x0036
        L_0x0035:
            r1 = r2
        L_0x0036:
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "/Lis="
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            ge.t r1 = r4.f     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r2 = r3
        L_0x0044:
            r0.append(r2)     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "]"
            r0.append(r1)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0052 }
            monitor-exit(r4)
            return r0
        L_0x0052:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0052 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.i.toString():java.lang.String");
    }
}
