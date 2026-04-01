package G0;

import He.F;
import android.os.Parcel;
import java.io.Closeable;
import java.net.HttpURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Closeable {
    public final /* synthetic */ int d = 1;
    public Object e;

    public /* synthetic */ a() {
    }

    public static a f() {
        Parcel obtain = Parcel.obtain();
        a aVar = new a();
        aVar.e = obtain;
        return aVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:15|21|22|23|24) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|18|19|20) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x005c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006b */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x006b=Splitter:B:23:0x006b, B:18:0x005c=Splitter:B:18:0x005c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a() {
        /*
            r3 = this;
            java.lang.Object r3 = r3.e
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3
            r0 = 0
            int r1 = r3.getResponseCode()     // Catch:{ IOException -> 0x000f }
            int r1 = r1 / 100
            r2 = 2
            if (r1 != r2) goto L_0x000f
            r0 = 1
        L_0x000f:
            if (r0 == 0) goto L_0x0013
            r3 = 0
            return r3
        L_0x0013:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c }
            r0.<init>()     // Catch:{ IOException -> 0x006c }
            java.lang.String r1 = "Unable to fetch "
            r0.append(r1)     // Catch:{ IOException -> 0x006c }
            java.net.URL r1 = r3.getURL()     // Catch:{ IOException -> 0x006c }
            r0.append(r1)     // Catch:{ IOException -> 0x006c }
            java.lang.String r1 = ". Failed with "
            r0.append(r1)     // Catch:{ IOException -> 0x006c }
            int r1 = r3.getResponseCode()     // Catch:{ IOException -> 0x006c }
            r0.append(r1)     // Catch:{ IOException -> 0x006c }
            java.lang.String r1 = "\n"
            r0.append(r1)     // Catch:{ IOException -> 0x006c }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x006c }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x006c }
            java.io.InputStream r3 = r3.getErrorStream()     // Catch:{ IOException -> 0x006c }
            r2.<init>(r3)     // Catch:{ IOException -> 0x006c }
            r1.<init>(r2)     // Catch:{ IOException -> 0x006c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c }
            r3.<init>()     // Catch:{ IOException -> 0x006c }
        L_0x0048:
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x0057 }
            if (r2 == 0) goto L_0x0059
            r3.append(r2)     // Catch:{ all -> 0x0057 }
            r2 = 10
            r3.append(r2)     // Catch:{ all -> 0x0057 }
            goto L_0x0048
        L_0x0057:
            r3 = move-exception
            goto L_0x0068
        L_0x0059:
            r1.close()     // Catch:{ Exception -> 0x005c }
        L_0x005c:
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x006c }
            r0.append(r3)     // Catch:{ IOException -> 0x006c }
            java.lang.String r3 = r0.toString()     // Catch:{ IOException -> 0x006c }
            return r3
        L_0x0068:
            r1.close()     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            throw r3     // Catch:{ IOException -> 0x006c }
        L_0x006c:
            r3 = move-exception
            java.lang.String r0 = "get error failed "
            J0.b.c(r0, r3)
            java.lang.String r3 = r3.getMessage()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: G0.a.a():java.lang.String");
    }

    public Parcel c() {
        boolean z;
        if (((Parcel) this.e) != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "get() after close()/release()");
        return (Parcel) this.e;
    }

    public final void close() {
        switch (this.d) {
            case 0:
                ((HttpURLConnection) this.e).disconnect();
                return;
            default:
                Parcel parcel = (Parcel) this.e;
                if (parcel != null) {
                    parcel.recycle();
                    this.e = null;
                    return;
                }
                return;
        }
    }

    public a(HttpURLConnection httpURLConnection) {
        this.e = httpURLConnection;
    }
}
