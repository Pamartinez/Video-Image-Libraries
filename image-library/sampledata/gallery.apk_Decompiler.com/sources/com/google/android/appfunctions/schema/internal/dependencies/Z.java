package com.google.android.appfunctions.schema.internal.dependencies;

import L2.a;
import a.C0068a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Z {

    /* renamed from: a  reason: collision with root package name */
    public static final a f1218a;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        if (java.lang.System.getenv("PROTOBUF_DISABLE_UNSAFE_UTF8_PROCESSOR_FOR_TESTING") != null) goto L_0x001e;
     */
    static {
        /*
            java.lang.String r0 = "PROTOBUF_DISABLE_UNSAFE_UTF8_PROCESSOR_FOR_TESTING"
            java.lang.String r0 = java.lang.System.getenv(r0)     // Catch:{ SecurityException -> 0x0009 }
            if (r0 == 0) goto L_0x0009
            goto L_0x001e
        L_0x0009:
            boolean r0 = com.google.android.appfunctions.schema.internal.dependencies.W.e
            if (r0 == 0) goto L_0x001e
            boolean r0 = com.google.android.appfunctions.schema.internal.dependencies.W.d
            if (r0 == 0) goto L_0x001e
            boolean r0 = com.google.android.appfunctions.schema.internal.dependencies.b0.a()
            if (r0 != 0) goto L_0x001e
            com.google.android.appfunctions.schema.internal.dependencies.X r0 = new com.google.android.appfunctions.schema.internal.dependencies.X
            r1 = 1
            r0.<init>(r1)
            goto L_0x0024
        L_0x001e:
            com.google.android.appfunctions.schema.internal.dependencies.X r0 = new com.google.android.appfunctions.schema.internal.dependencies.X
            r1 = 0
            r0.<init>(r1)
        L_0x0024:
            f1218a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.Z.<clinit>():void");
    }

    public static int a(String str) {
        int length = str.length();
        int i2 = 0;
        int i7 = 0;
        while (i7 < length && str.charAt(i7) < 128) {
            i7++;
        }
        int i8 = length;
        while (true) {
            if (i7 >= length) {
                break;
            }
            char charAt = str.charAt(i7);
            if (charAt < 2048) {
                i8 += (127 - charAt) >>> 31;
                i7++;
            } else {
                try {
                    int length2 = str.length();
                    while (i7 < length2) {
                        char charAt2 = str.charAt(i7);
                        if (charAt2 < 2048) {
                            i2 += (127 - charAt2) >>> 31;
                        } else {
                            i2 += 2;
                            if (charAt2 >= 55296 && charAt2 <= 57343) {
                                if (Character.codePointAt(str, i7) >= 65536) {
                                    i7++;
                                } else {
                                    throw new Exception(C0068a.g0(length2, i7, (byte) 32, "Unpaired surrogate at index ", " of "));
                                }
                            }
                        }
                        i7++;
                    }
                    i8 += i2;
                } catch (Y unused) {
                    return str.getBytes(C0106p.f1226a).length;
                }
            }
        }
        if (i8 >= length) {
            return i8;
        }
        long j2 = ((long) i8) + 4294967296L;
        StringBuilder sb2 = new StringBuilder(String.valueOf(j2).length() + 34);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(j2);
        throw new IllegalArgumentException(sb2.toString());
    }
}
