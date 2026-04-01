package U2;

import java.io.PrintStream;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c extends RuntimeException {
    public final StringBuffer d;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(java.lang.String r3, java.lang.Exception r4) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0003
            goto L_0x000b
        L_0x0003:
            if (r4 == 0) goto L_0x000a
            java.lang.String r3 = r4.getMessage()
            goto L_0x000b
        L_0x000a:
            r3 = 0
        L_0x000b:
            r2.<init>(r3, r4)
            boolean r3 = r4 instanceof U2.c
            r0 = 200(0xc8, float:2.8E-43)
            if (r3 == 0) goto L_0x002c
            U2.c r4 = (U2.c) r4
            java.lang.StringBuffer r3 = r4.d
            java.lang.String r3 = r3.toString()
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            int r1 = r3.length()
            int r1 = r1 + r0
            r4.<init>(r1)
            r2.d = r4
            r4.append(r3)
            return
        L_0x002c:
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>(r0)
            r2.d = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: U2.c.<init>(java.lang.String, java.lang.Exception):void");
    }

    public static c a(Exception exc, String str) {
        c cVar;
        if (exc instanceof c) {
            cVar = (c) exc;
        } else {
            cVar = new c((String) null, exc);
        }
        StringBuffer stringBuffer = cVar.d;
        if (str != null) {
            stringBuffer.append(str);
            if (!str.endsWith("\n")) {
                stringBuffer.append(10);
            }
            return cVar;
        }
        throw new NullPointerException("str == null");
    }

    public final void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        printStream.println(this.d);
    }

    public final void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        printWriter.println(this.d);
    }
}
