package io.grpc;

import B1.a;
import E2.k;
import He.F;
import com.google.protobuf.MessageLite;
import ee.N;
import ee.O;
import he.C1076a;
import he.C1077b;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MethodDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public final O f4620a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f4621c;
    public final N d;
    public final N e;
    public final boolean f;

    public MethodDescriptor(O o2, String str, C1077b bVar, C1077b bVar2, boolean z) {
        String str2;
        new AtomicReferenceArray(2);
        F.n(o2, "type");
        this.f4620a = o2;
        F.n(str, "fullMethodName");
        this.b = str;
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf == -1) {
            str2 = null;
        } else {
            str2 = str.substring(0, lastIndexOf);
        }
        this.f4621c = str2;
        F.n(bVar, "requestMarshaller");
        this.d = bVar;
        F.n(bVar2, "responseMarshaller");
        this.e = bVar2;
        this.f = z;
    }

    public static String a(String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        F.n(str, "fullServiceName");
        sb2.append(str);
        sb2.append("/");
        F.n(str2, "methodName");
        sb2.append(str2);
        return sb2.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(4:4|5|6|(1:8)(2:9|10))|11|12|(2:14|(1:(2:36|62))(5:18|(1:24)|25|(1:(1:59)(2:27|(2:60|29)(1:30)))|(9:32|(1:39)|40|(1:(1:43)(2:44|45))|46|47|48|49|61)(2:33|34)))|37|(0)|40|(0)|46|47|48|49|61) */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ca, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d0, code lost:
        throw new java.lang.RuntimeException(r6);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[Catch:{ IOException -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.protobuf.MessageLite b(java.io.InputStream r7) {
        /*
            r6 = this;
            ee.N r6 = r6.e
            he.b r6 = (he.C1077b) r6
            r6.getClass()
            boolean r0 = r7 instanceof he.C1076a
            if (r0 == 0) goto L_0x0024
            r0 = r7
            he.a r0 = (he.C1076a) r0
            com.google.protobuf.Parser r0 = r0.e
            com.google.protobuf.Parser r1 = r6.f4580a
            if (r0 != r1) goto L_0x0024
            r0 = r7
            he.a r0 = (he.C1076a) r0     // Catch:{ IllegalStateException -> 0x0024 }
            com.google.protobuf.MessageLite r0 = r0.d     // Catch:{ IllegalStateException -> 0x0024 }
            if (r0 == 0) goto L_0x001c
            return r0
        L_0x001c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ IllegalStateException -> 0x0024 }
            java.lang.String r1 = "message not available"
            r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x0024 }
            throw r0     // Catch:{ IllegalStateException -> 0x0024 }
        L_0x0024:
            boolean r0 = r7 instanceof ee.C0992z     // Catch:{ IOException -> 0x00ca }
            r1 = 0
            if (r0 == 0) goto L_0x008e
            int r0 = r7.available()     // Catch:{ IOException -> 0x00ca }
            if (r0 <= 0) goto L_0x0089
            r2 = 4194304(0x400000, float:5.877472E-39)
            if (r0 > r2) goto L_0x0089
            java.lang.ThreadLocal r2 = he.C1077b.d     // Catch:{ IOException -> 0x00ca }
            java.lang.Object r3 = r2.get()     // Catch:{ IOException -> 0x00ca }
            java.lang.ref.Reference r3 = (java.lang.ref.Reference) r3     // Catch:{ IOException -> 0x00ca }
            if (r3 == 0) goto L_0x0048
            java.lang.Object r3 = r3.get()     // Catch:{ IOException -> 0x00ca }
            byte[] r3 = (byte[]) r3     // Catch:{ IOException -> 0x00ca }
            if (r3 == 0) goto L_0x0048
            int r4 = r3.length     // Catch:{ IOException -> 0x00ca }
            if (r4 >= r0) goto L_0x0052
        L_0x0048:
            byte[] r3 = new byte[r0]     // Catch:{ IOException -> 0x00ca }
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference     // Catch:{ IOException -> 0x00ca }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00ca }
            r2.set(r4)     // Catch:{ IOException -> 0x00ca }
        L_0x0052:
            r2 = r0
        L_0x0053:
            if (r2 <= 0) goto L_0x0061
            int r4 = r0 - r2
            int r4 = r7.read(r3, r4, r2)     // Catch:{ IOException -> 0x00ca }
            r5 = -1
            if (r4 != r5) goto L_0x005f
            goto L_0x0061
        L_0x005f:
            int r2 = r2 - r4
            goto L_0x0053
        L_0x0061:
            if (r2 != 0) goto L_0x0068
            com.google.protobuf.j r0 = com.google.protobuf.CodedInputStream.f(r3, r1, r0, r1)     // Catch:{ IOException -> 0x00ca }
            goto L_0x008f
        L_0x0068:
            int r6 = r0 - r2
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ IOException -> 0x00ca }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ca }
            r1.<init>()     // Catch:{ IOException -> 0x00ca }
            java.lang.String r2 = "size inaccurate: "
            r1.append(r2)     // Catch:{ IOException -> 0x00ca }
            r1.append(r0)     // Catch:{ IOException -> 0x00ca }
            java.lang.String r0 = " != "
            r1.append(r0)     // Catch:{ IOException -> 0x00ca }
            r1.append(r6)     // Catch:{ IOException -> 0x00ca }
            java.lang.String r6 = r1.toString()     // Catch:{ IOException -> 0x00ca }
            r7.<init>(r6)     // Catch:{ IOException -> 0x00ca }
            throw r7     // Catch:{ IOException -> 0x00ca }
        L_0x0089:
            if (r0 != 0) goto L_0x008e
            com.google.protobuf.MessageLite r6 = r6.b     // Catch:{ IOException -> 0x00ca }
            goto L_0x00b5
        L_0x008e:
            r0 = 0
        L_0x008f:
            if (r0 != 0) goto L_0x0095
            com.google.protobuf.CodedInputStream r0 = com.google.protobuf.CodedInputStream.g(r7)
        L_0x0095:
            int r7 = r6.f4581c
            if (r7 < 0) goto L_0x00aa
            if (r7 < 0) goto L_0x009e
            r0.b = r7
            goto L_0x00aa
        L_0x009e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Recursion limit cannot be negative: "
            java.lang.String r7 = c0.C0086a.i(r7, r0)
            r6.<init>(r7)
            throw r6
        L_0x00aa:
            com.google.protobuf.Parser r6 = r6.f4580a     // Catch:{ F -> 0x00b8 }
            com.google.protobuf.ExtensionRegistryLite r7 = he.C1078c.f4582a     // Catch:{ F -> 0x00b8 }
            com.google.protobuf.GeneratedMessageLite r6 = r6.a(r0, r7)     // Catch:{ F -> 0x00b8 }
            r0.a(r1)     // Catch:{ F -> 0x00b6 }
        L_0x00b5:
            return r6
        L_0x00b6:
            r6 = move-exception
            throw r6     // Catch:{ F -> 0x00b8 }
        L_0x00b8:
            r6 = move-exception
            ee.a0 r7 = ee.a0.n
            java.lang.String r0 = "Invalid protobuf byte sequence"
            ee.a0 r7 = r7.g(r0)
            ee.a0 r6 = r7.f(r6)
            ee.c0 r6 = r6.a()
            throw r6
        L_0x00ca:
            r6 = move-exception
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.MethodDescriptor.b(java.io.InputStream):com.google.protobuf.MessageLite");
    }

    public final C1076a c(Object obj) {
        C1077b bVar = (C1077b) this.d;
        bVar.getClass();
        return new C1076a((MessageLite) obj, bVar.f4580a);
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.b, "fullMethodName");
        V.a(this.f4620a, "type");
        V.c("idempotent", false);
        V.c("safe", false);
        V.c("sampledToLocalTracing", this.f);
        V.a(this.d, "requestMarshaller");
        V.a(this.e, "responseMarshaller");
        V.a((Object) null, "schemaDescriptor");
        V.b = true;
        return V.toString();
    }
}
