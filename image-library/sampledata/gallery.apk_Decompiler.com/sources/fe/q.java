package fe;

import He.F;
import android.os.Parcel;
import ee.C0984q;
import ee.C0991y;
import ee.J;
import ee.M;
import ee.O;
import ee.a0;
import ee.b0;
import ge.M1;
import io.grpc.MethodDescriptor;
import io.grpc.a;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public final c f4363a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final M1 f4364c;
    public r d = r.INITIAL;
    public boolean e;
    public InputStream f;
    public ConcurrentLinkedQueue g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f4365h;

    /* renamed from: i  reason: collision with root package name */
    public int f4366i;

    /* renamed from: j  reason: collision with root package name */
    public int f4367j;
    public final MethodDescriptor k;
    public final M l;
    public final M1 m;

    public q(c cVar, int i2, MethodDescriptor methodDescriptor, M m4, M1 m12) {
        this.f4363a = cVar;
        this.b = i2;
        this.f4364c = m12;
        this.k = methodDescriptor;
        this.l = m4;
        this.m = m12;
    }

    public final boolean a() {
        ConcurrentLinkedQueue concurrentLinkedQueue = this.g;
        if (concurrentLinkedQueue != null) {
            return !concurrentLinkedQueue.isEmpty();
        }
        if (this.f == null || this.f4367j != 0) {
            return false;
        }
        return true;
    }

    public final void b(r rVar) {
        r rVar2 = this.d;
        int i2 = p.f4362a[rVar.ordinal()];
        boolean z = false;
        if (i2 == 2) {
            if (rVar2 == r.INITIAL) {
                z = true;
            }
            F.r(z);
        } else if (i2 == 3) {
            if (rVar2 == r.PREFIX_SENT) {
                z = true;
            }
            F.r(z);
        } else if (i2 == 4) {
            if (rVar2 == r.ALL_MESSAGES_SENT) {
                z = true;
            }
            F.r(z);
        } else if (i2 != 5) {
            throw new AssertionError();
        }
        this.d = rVar;
    }

    public final void c() {
        while (true) {
            int i2 = p.f4362a[this.d.ordinal()];
            boolean z = false;
            if (i2 == 1 ? this.e : i2 == 2 ? a() || this.f4365h : i2 == 3 && this.f4365h) {
                z = this.f4363a.k();
            }
            if (z) {
                try {
                    d();
                } catch (b0 e7) {
                    b(r.CLOSED);
                    throw e7;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[Catch:{ all -> 0x0038, all -> 0x00d5, IOException -> 0x00da }, LOOP:0: B:38:0x00b8->B:39:0x00ba, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c5 A[Catch:{ all -> 0x0038, all -> 0x00d5, IOException -> 0x00da }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d() {
        /*
            r7 = this;
            ge.M1 r0 = r7.f4364c
            ee.q[] r0 = r0.f4461a
            G0.a r1 = G0.a.f()     // Catch:{ IOException -> 0x00da }
            android.os.Parcel r2 = r1.c()     // Catch:{ all -> 0x0038 }
            r3 = 0
            r2.writeInt(r3)     // Catch:{ all -> 0x0038 }
            android.os.Parcel r2 = r1.c()     // Catch:{ all -> 0x0038 }
            int r4 = r7.f4366i     // Catch:{ all -> 0x0038 }
            int r5 = r4 + 1
            r7.f4366i = r5     // Catch:{ all -> 0x0038 }
            r2.writeInt(r4)     // Catch:{ all -> 0x0038 }
            int[] r2 = fe.p.f4362a     // Catch:{ all -> 0x0038 }
            fe.r r4 = r7.d     // Catch:{ all -> 0x0038 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0038 }
            r2 = r2[r4]     // Catch:{ all -> 0x0038 }
            r4 = 1
            if (r2 == r4) goto L_0x003d
            r4 = 2
            if (r2 == r4) goto L_0x003b
            r4 = 3
            if (r2 != r4) goto L_0x0032
            r2 = r3
            goto L_0x008b
        L_0x0032:
            java.lang.AssertionError r7 = new java.lang.AssertionError     // Catch:{ all -> 0x0038 }
            r7.<init>()     // Catch:{ all -> 0x0038 }
            throw r7     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r7 = move-exception
            goto L_0x00d1
        L_0x003b:
            r2 = r3
            goto L_0x0056
        L_0x003d:
            android.os.Parcel r2 = r1.c()     // Catch:{ all -> 0x0038 }
            int r2 = r7.f(r2)     // Catch:{ all -> 0x0038 }
            r2 = r2 | r4
            fe.r r4 = fe.r.PREFIX_SENT     // Catch:{ all -> 0x0038 }
            r7.b(r4)     // Catch:{ all -> 0x0038 }
            boolean r4 = r7.a()     // Catch:{ all -> 0x0038 }
            if (r4 != 0) goto L_0x0056
            boolean r4 = r7.f4365h     // Catch:{ all -> 0x0038 }
            if (r4 != 0) goto L_0x0056
            goto L_0x0095
        L_0x0056:
            int r4 = r7.f4367j     // Catch:{ all -> 0x0038 }
            if (r4 != 0) goto L_0x005d
            java.io.InputStream r4 = r7.f     // Catch:{ all -> 0x0038 }
            goto L_0x0069
        L_0x005d:
            java.util.concurrent.ConcurrentLinkedQueue r4 = r7.g     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0068
            java.lang.Object r4 = r4.peek()     // Catch:{ all -> 0x0038 }
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x0038 }
            goto L_0x0069
        L_0x0068:
            r4 = 0
        L_0x0069:
            if (r4 == 0) goto L_0x0077
            r2 = r2 | 2
            android.os.Parcel r5 = r1.c()     // Catch:{ all -> 0x0038 }
            int r4 = r7.e(r5, r4)     // Catch:{ all -> 0x0038 }
            r2 = r2 | r4
            goto L_0x007c
        L_0x0077:
            boolean r4 = r7.f4365h     // Catch:{ all -> 0x0038 }
            He.F.r(r4)     // Catch:{ all -> 0x0038 }
        L_0x007c:
            boolean r4 = r7.f4365h     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0095
            boolean r4 = r7.a()     // Catch:{ all -> 0x0038 }
            if (r4 != 0) goto L_0x0095
            fe.r r4 = fe.r.ALL_MESSAGES_SENT     // Catch:{ all -> 0x0038 }
            r7.b(r4)     // Catch:{ all -> 0x0038 }
        L_0x008b:
            r2 = r2 | 4
            r1.c()     // Catch:{ all -> 0x0038 }
            fe.r r4 = fe.r.SUFFIX_SENT     // Catch:{ all -> 0x0038 }
            r7.b(r4)     // Catch:{ all -> 0x0038 }
        L_0x0095:
            android.os.Parcel r4 = r1.c()     // Catch:{ all -> 0x0038 }
            int r5 = r4.dataPosition()     // Catch:{ all -> 0x0038 }
            r4.setDataPosition(r3)     // Catch:{ all -> 0x0038 }
            r4.writeInt(r2)     // Catch:{ all -> 0x0038 }
            r4.setDataPosition(r5)     // Catch:{ all -> 0x0038 }
            android.os.Parcel r2 = r1.c()     // Catch:{ all -> 0x0038 }
            int r2 = r2.dataSize()     // Catch:{ all -> 0x0038 }
            fe.c r4 = r7.f4363a     // Catch:{ all -> 0x0038 }
            int r7 = r7.b     // Catch:{ all -> 0x0038 }
            r4.m(r7, r1)     // Catch:{ all -> 0x0038 }
            long r4 = (long) r2     // Catch:{ all -> 0x0038 }
            int r7 = r0.length     // Catch:{ all -> 0x0038 }
            r2 = r3
        L_0x00b8:
            if (r2 >= r7) goto L_0x00c2
            r6 = r0[r2]     // Catch:{ all -> 0x0038 }
            r6.f(r4)     // Catch:{ all -> 0x0038 }
            int r2 = r2 + 1
            goto L_0x00b8
        L_0x00c2:
            int r7 = r0.length     // Catch:{ all -> 0x0038 }
        L_0x00c3:
            if (r3 >= r7) goto L_0x00cd
            r2 = r0[r3]     // Catch:{ all -> 0x0038 }
            r2.getClass()     // Catch:{ all -> 0x0038 }
            int r3 = r3 + 1
            goto L_0x00c3
        L_0x00cd:
            r1.close()     // Catch:{ IOException -> 0x00da }
            return
        L_0x00d1:
            r1.close()     // Catch:{ all -> 0x00d5 }
            goto L_0x00d9
        L_0x00d5:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ IOException -> 0x00da }
        L_0x00d9:
            throw r7     // Catch:{ IOException -> 0x00da }
        L_0x00da:
            r7 = move-exception
            ee.a0 r0 = ee.a0.n
            ee.a0 r7 = r0.f(r7)
            ee.b0 r0 = new ee.b0
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.q.d():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int e(android.os.Parcel r5, java.io.InputStream r6) {
        /*
            r4 = this;
            ge.M1 r0 = r4.f4364c
            ee.q[] r0 = r0.f4461a
            boolean r1 = r6 instanceof fe.s
            if (r1 != 0) goto L_0x005e
            int r1 = fe.f.f4346a
            byte[] r1 = fe.f.a(r1)
            int r2 = r6.read(r1)     // Catch:{ all -> 0x0019 }
            r3 = 0
            if (r2 > 0) goto L_0x001b
            r5.writeInt(r3)     // Catch:{ all -> 0x0019 }
            goto L_0x0028
        L_0x0019:
            r4 = move-exception
            goto L_0x005a
        L_0x001b:
            r5.writeInt(r2)     // Catch:{ all -> 0x0019 }
            r5.writeByteArray(r1, r3, r2)     // Catch:{ all -> 0x0019 }
            int r5 = r1.length     // Catch:{ all -> 0x0019 }
            if (r2 != r5) goto L_0x0028
            r5 = 1
            r2 = 128(0x80, float:1.794E-43)
            goto L_0x002a
        L_0x0028:
            r5 = r3
            r2 = r5
        L_0x002a:
            fe.f.b(r1)
            if (r5 != 0) goto L_0x0059
            r6.close()
            int r5 = r4.f4367j
            int r6 = r5 + 1
            r4.f4367j = r6
            if (r5 <= 0) goto L_0x0042
            java.util.concurrent.ConcurrentLinkedQueue r4 = r4.g
            r4.getClass()
            r4.poll()
        L_0x0042:
            int r4 = r0.length
            r5 = r3
        L_0x0044:
            if (r5 >= r4) goto L_0x004e
            r6 = r0[r5]
            r6.getClass()
            int r5 = r5 + 1
            goto L_0x0044
        L_0x004e:
            int r4 = r0.length
        L_0x004f:
            if (r3 >= r4) goto L_0x0059
            r5 = r0[r3]
            r5.getClass()
            int r3 = r3 + 1
            goto L_0x004f
        L_0x0059:
            return r2
        L_0x005a:
            fe.f.b(r1)
            throw r4
        L_0x005e:
            r5.dataPosition()
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.q.e(android.os.Parcel, java.io.InputStream):int");
    }

    public final int f(Parcel parcel) {
        int i2;
        MethodDescriptor methodDescriptor = this.k;
        parcel.writeString(methodDescriptor.b);
        M m4 = this.l;
        if (m4 != null) {
            int i7 = C0991y.f4316a;
            i2 = m4.b;
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            parcel.writeInt(0);
        } else {
            int i8 = C0991y.f4316a;
            Object[] objArr = new Object[(m4.b * 2)];
            int i10 = 0;
            while (i10 < m4.b) {
                int i11 = i10 * 2;
                Object[] objArr2 = m4.f4275a;
                objArr[i11] = (byte[]) objArr2[i11];
                int i12 = i11 + 1;
                Object obj = objArr2[i12];
                if (obj instanceof byte[]) {
                    objArr[i12] = obj;
                    i10++;
                } else {
                    ((J) obj).getClass();
                    throw null;
                }
            }
            parcel.writeInt(i2);
            for (int i13 = 0; i13 < i2; i13++) {
                int i14 = i13 * 2;
                byte[] bArr = (byte[]) objArr[i14];
                parcel.writeInt(bArr.length);
                parcel.writeByteArray(bArr);
                Object obj2 = objArr[i14 + 1];
                if (obj2 instanceof byte[]) {
                    byte[] bArr2 = (byte[]) obj2;
                    parcel.writeInt(bArr2.length);
                    parcel.writeByteArray(bArr2);
                } else if (!(obj2 instanceof s)) {
                    byte[] a7 = f.a(f.f4346a);
                    try {
                        InputStream inputStream = (InputStream) obj2;
                        int i15 = 0;
                        while (true) {
                            if (i15 >= a7.length) {
                                break;
                            }
                            int read = inputStream.read(a7, i15, a7.length - i15);
                            if (read == -1) {
                                break;
                            }
                            i15 += read;
                        }
                        if (i15 != a7.length) {
                            parcel.writeInt(i15);
                            if (i15 > 0) {
                                parcel.writeByteArray(a7, 0, i15);
                            }
                            f.b(a7);
                        } else {
                            throw new b0(a0.k.g("Metadata value too large"));
                        }
                    } catch (Throwable th) {
                        f.b(a7);
                        throw th;
                    }
                } else {
                    parcel.writeInt(-1);
                    parcel.dataPosition();
                    throw null;
                }
            }
        }
        for (C0984q qVar : this.m.f4461a) {
            ((a) qVar).getClass();
        }
        O o2 = methodDescriptor.f4620a;
        o2.getClass();
        if (o2 == O.UNARY || o2 == O.CLIENT_STREAMING) {
            return 16;
        }
        return 0;
    }

    public final synchronized String toString() {
        return q.class.getSimpleName() + "[S=" + this.d + "/NDM=" + this.f4367j + "]";
    }
}
