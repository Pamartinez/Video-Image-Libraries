package x0;

import android.content.Context;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2062a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2063c;
    public final /* synthetic */ String d;

    public /* synthetic */ k(Context context, String str, String str2, int i2) {
        this.f2062a = i2;
        this.b = context;
        this.f2063c = str;
        this.d = str2;
    }

    /* JADX WARNING: type inference failed for: r10v20, types: [G0.e, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d5, code lost:
        if (r0 != null) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00f7, code lost:
        if ((r0.getResponseCode() / 100) == 2) goto L_0x00fb;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0154 A[SYNTHETIC, Splitter:B:107:0x0154] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r10 = this;
            int r0 = r10.f2062a
            switch(r0) {
                case 0: goto L_0x0010;
                default: goto L_0x0005;
            }
        L_0x0005:
            android.content.Context r0 = r10.b
            java.lang.String r1 = r10.f2063c
            java.lang.String r10 = r10.d
            x0.B r10 = x0.n.b(r0, r1, r10)
            return r10
        L_0x0010:
            android.content.Context r2 = r10.b
            java.lang.String r3 = r10.f2063c
            java.lang.String r6 = r10.d
            G0.e r10 = x0.C0326d.b
            if (r10 != 0) goto L_0x0056
            java.lang.Class<G0.e> r1 = G0.e.class
            monitor-enter(r1)
            G0.e r10 = x0.C0326d.b     // Catch:{ all -> 0x0052 }
            if (r10 != 0) goto L_0x0055
            G0.e r10 = new G0.e     // Catch:{ all -> 0x0052 }
            android.content.Context r0 = r2.getApplicationContext()     // Catch:{ all -> 0x0052 }
            G0.c r4 = x0.C0326d.f2050c     // Catch:{ all -> 0x0052 }
            if (r4 != 0) goto L_0x004a
            java.lang.Class<G0.c> r4 = G0.c.class
            monitor-enter(r4)     // Catch:{ all -> 0x0052 }
            G0.c r5 = x0.C0326d.f2050c     // Catch:{ all -> 0x0042 }
            if (r5 != 0) goto L_0x0045
            G0.c r5 = new G0.c     // Catch:{ all -> 0x0042 }
            D9.b r7 = new D9.b     // Catch:{ all -> 0x0042 }
            r8 = 9
            r7.<init>(r0, r8)     // Catch:{ all -> 0x0042 }
            r0 = 0
            r5.<init>((int) r0, (java.lang.Object) r7)     // Catch:{ all -> 0x0042 }
            x0.C0326d.f2050c = r5     // Catch:{ all -> 0x0042 }
            goto L_0x0045
        L_0x0042:
            r0 = move-exception
            r10 = r0
            goto L_0x0048
        L_0x0045:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            r4 = r5
            goto L_0x004a
        L_0x0048:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            throw r10     // Catch:{ all -> 0x0052 }
        L_0x004a:
            r10.<init>()     // Catch:{ all -> 0x0052 }
            r10.d = r4     // Catch:{ all -> 0x0052 }
            x0.C0326d.b = r10     // Catch:{ all -> 0x0052 }
            goto L_0x0055
        L_0x0052:
            r0 = move-exception
            r10 = r0
            goto L_0x0058
        L_0x0055:
            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
        L_0x0056:
            r1 = r10
            goto L_0x005a
        L_0x0058:
            monitor-exit(r1)     // Catch:{ all -> 0x0052 }
            throw r10
        L_0x005a:
            r10 = 2
            r4 = 1
            r5 = 0
            if (r6 == 0) goto L_0x009d
            java.lang.Object r0 = r1.d
            G0.c r0 = (G0.c) r0
            java.io.File r0 = r0.t(r3)     // Catch:{ FileNotFoundException -> 0x0069 }
            if (r0 != 0) goto L_0x006b
        L_0x0069:
            r0 = r5
            goto L_0x009b
        L_0x006b:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0069 }
            r7.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0069 }
            java.lang.String r8 = r0.getAbsolutePath()
            java.lang.String r9 = ".zip"
            boolean r8 = r8.endsWith(r9)
            if (r8 == 0) goto L_0x007f
            G0.b r8 = G0.b.ZIP
            goto L_0x0090
        L_0x007f:
            java.lang.String r8 = r0.getAbsolutePath()
            java.lang.String r9 = ".gz"
            boolean r8 = r8.endsWith(r9)
            if (r8 == 0) goto L_0x008e
            G0.b r8 = G0.b.GZIP
            goto L_0x0090
        L_0x008e:
            G0.b r8 = G0.b.JSON
        L_0x0090:
            r0.getAbsolutePath()
            J0.b.a()
            android.util.Pair r0 = new android.util.Pair
            r0.<init>(r8, r7)
        L_0x009b:
            if (r0 != 0) goto L_0x009f
        L_0x009d:
            r0 = r5
            goto L_0x00d7
        L_0x009f:
            java.lang.Object r7 = r0.first
            G0.b r7 = (G0.b) r7
            java.lang.Object r0 = r0.second
            java.io.InputStream r0 = (java.io.InputStream) r0
            int[] r8 = G0.d.f281a
            int r7 = r7.ordinal()
            r7 = r8[r7]
            if (r7 == r4) goto L_0x00ca
            if (r7 == r10) goto L_0x00b8
            x0.B r0 = x0.n.c(r6, r0)
            goto L_0x00d3
        L_0x00b8:
            java.util.zip.GZIPInputStream r7 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x00c2 }
            r7.<init>(r0)     // Catch:{ IOException -> 0x00c2 }
            x0.B r0 = x0.n.c(r6, r7)     // Catch:{ IOException -> 0x00c2 }
            goto L_0x00d3
        L_0x00c2:
            r0 = move-exception
            x0.B r7 = new x0.B
            r7.<init>((java.lang.Throwable) r0)
            r0 = r7
            goto L_0x00d3
        L_0x00ca:
            java.util.zip.ZipInputStream r7 = new java.util.zip.ZipInputStream
            r7.<init>(r0)
            x0.B r0 = x0.n.f(r2, r7, r6)
        L_0x00d3:
            x0.j r0 = r0.f2042a
            if (r0 == 0) goto L_0x009d
        L_0x00d7:
            if (r0 == 0) goto L_0x00e0
            x0.B r10 = new x0.B
            r10.<init>((x0.C0332j) r0)
            goto L_0x0144
        L_0x00e0:
            J0.b.a()
            java.lang.String r7 = "LottieFetchResult close failed "
            J0.b.a()
            G0.a r8 = ie.c.f(r3)     // Catch:{ Exception -> 0x0131 }
            java.lang.Object r0 = r8.e     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            r5 = 0
            int r9 = r0.getResponseCode()     // Catch:{ IOException -> 0x00fa }
            int r9 = r9 / 100
            if (r9 != r10) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r4 = r5
        L_0x00fb:
            if (r4 == 0) goto L_0x011f
            java.io.InputStream r4 = r0.getInputStream()     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            java.lang.String r5 = r0.getContentType()     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            x0.B r10 = r1.K(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            x0.j r0 = r10.f2042a     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            J0.b.a()     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
        L_0x010e:
            r8.close()     // Catch:{ IOException -> 0x0112 }
            goto L_0x0144
        L_0x0112:
            r0 = move-exception
            J0.b.c(r7, r0)
            goto L_0x0144
        L_0x0117:
            r0 = move-exception
            r10 = r0
            r5 = r8
            goto L_0x0152
        L_0x011b:
            r0 = move-exception
            r10 = r0
            r5 = r8
            goto L_0x0133
        L_0x011f:
            x0.B r10 = new x0.B     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            java.lang.String r1 = r8.a()     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            r10.<init>((java.lang.Throwable) r0)     // Catch:{ Exception -> 0x011b, all -> 0x0117 }
            goto L_0x010e
        L_0x012e:
            r0 = move-exception
            r10 = r0
            goto L_0x0152
        L_0x0131:
            r0 = move-exception
            r10 = r0
        L_0x0133:
            x0.B r1 = new x0.B     // Catch:{ all -> 0x012e }
            r1.<init>((java.lang.Throwable) r10)     // Catch:{ all -> 0x012e }
            if (r5 == 0) goto L_0x0143
            r5.close()     // Catch:{ IOException -> 0x013e }
            goto L_0x0143
        L_0x013e:
            r0 = move-exception
            r10 = r0
            J0.b.c(r7, r10)
        L_0x0143:
            r10 = r1
        L_0x0144:
            if (r6 == 0) goto L_0x0151
            x0.j r0 = r10.f2042a
            if (r0 == 0) goto L_0x0151
            C0.h r1 = C0.h.b
            androidx.collection.LruCache r1 = r1.f98a
            r1.put(r6, r0)
        L_0x0151:
            return r10
        L_0x0152:
            if (r5 == 0) goto L_0x015c
            r5.close()     // Catch:{ IOException -> 0x0158 }
            goto L_0x015c
        L_0x0158:
            r0 = move-exception
            J0.b.c(r7, r0)
        L_0x015c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.k.call():java.lang.Object");
    }
}
