package ae;

import D1.f;
import Dd.C0731b;
import a.C0068a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import i.C0212a;

/* renamed from: ae.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0902a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3987a;
    public static final Uri b = Uri.parse("content://com.sec.android.log.diagmonagent/");

    /* renamed from: c  reason: collision with root package name */
    public static int f3988c = -1;

    static {
        String str;
        StringBuilder sb2 = new StringBuilder("DIAGMON_SDK[");
        try {
            str = String.valueOf(C0731b.f3330a);
        } catch (Exception unused) {
            str = "";
        }
        f3987a = C0212a.p(sb2, str, "]");
    }

    public static int a(Context context) {
        int i2;
        if (f3988c == -1) {
            int x9 = C0068a.x(context);
            if (x9 >= 600000000) {
                i2 = 2;
            } else if (x9 == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            f3988c = i2;
            Log.i(f3987a, "DiagMonAgent type: " + f3988c);
        }
        return f3988c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001c, code lost:
        if (r0 < 314572800) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b() {
        /*
            java.io.File r0 = android.os.Environment.getDataDirectory()
            long r0 = r0.getTotalSpace()
            r2 = 5
            long r0 = r0 * r2
            r2 = 100
            long r0 = r0 / r2
            r2 = 1073741824(0x40000000, double:5.304989477E-315)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0017
        L_0x0015:
            r0 = r2
            goto L_0x001f
        L_0x0017:
            r2 = 314572800(0x12c00000, double:1.554196136E-315)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x001f
            goto L_0x0015
        L_0x001f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Storage size threshold : "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = " bytes"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            D1.f.n(r2)
            java.io.File r2 = android.os.Environment.getDataDirectory()
            long r2 = r2.getUsableSpace()
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0061
            java.lang.String r4 = "insufficient storage"
            D1.f.L(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "usableSpace: "
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r2 = ", threshold: "
            r4.append(r2)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            D1.f.L(r0)
            r0 = 1
            return r0
        L_0x0061:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ae.C0902a.b():boolean");
    }

    public static void c(Bundle bundle) {
        try {
            String string = bundle.getString("result");
            String string2 = bundle.getString("cause");
            if (string2 == null) {
                f.A("Results : " + string);
                return;
            }
            f.A("Results : " + string + ", Cause : " + string2);
        } catch (Exception e) {
            Log.e(f3987a, e.getMessage());
        }
    }
}
