package X2;

import D0.e;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class u {

    /* renamed from: a  reason: collision with root package name */
    public static final u[] f940a;
    public static final Pattern b = Pattern.compile("\\d+");

    /* renamed from: c  reason: collision with root package name */
    public static final Pattern f941c = Pattern.compile("&");
    public static final Pattern d = Pattern.compile("=");
    public static final String[] e = new String[0];

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: X2.u[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            X2.c r0 = new X2.c
            r1 = 2
            r0.<init>(r1)
            X2.c r2 = new X2.c
            r3 = 0
            r2.<init>(r3)
            X2.j r4 = new X2.j
            r4.<init>()
            X2.b r5 = new X2.b
            r5.<init>(r3)
            X2.B r6 = new X2.B
            r6.<init>()
            X2.c r7 = new X2.c
            r8 = 1
            r7.<init>(r8)
            X2.b r9 = new X2.b
            r10 = 9
            r9.<init>(r10)
            Z2.b r11 = new Z2.b
            r11.<init>()
            X2.i r12 = new X2.i
            r12.<init>()
            X2.b r13 = new X2.b
            r14 = 7
            r13.<init>(r14)
            X2.b r15 = new X2.b
            r16 = r10
            r10 = 8
            r15.<init>(r10)
            r17 = r10
            X2.b r10 = new X2.b
            r18 = r14
            r14 = 5
            r10.<init>(r14)
            r19 = r14
            X2.b r14 = new X2.b
            r20 = r3
            r3 = 6
            r14.<init>(r3)
            X2.o r21 = new X2.o
            r21.<init>()
            r22 = r3
            X2.b r3 = new X2.b
            r8 = 10
            r3.<init>(r8)
            X2.A r24 = new X2.A
            r24.<init>()
            Y2.b r25 = new Y2.b
            r25.<init>()
            X2.z r26 = new X2.z
            r26.<init>()
            r27 = r8
            X2.b r8 = new X2.b
            r8.<init>(r1)
            r28 = r1
            X2.b r1 = new X2.b
            r29 = r0
            r0 = 4
            r1.<init>(r0)
            r30 = r0
            X2.b r0 = new X2.b
            r31 = r1
            r1 = 1
            r0.<init>(r1)
            X2.D r23 = new X2.D
            r23.<init>()
            r32 = r1
            X2.b r1 = new X2.b
            r33 = r0
            r0 = 3
            r1.<init>(r0)
            r34 = r0
            r0 = 23
            X2.u[] r0 = new X2.u[r0]
            r0[r20] = r29
            r0[r32] = r2
            r0[r28] = r4
            r0[r34] = r5
            r0[r30] = r6
            r0[r19] = r7
            r0[r22] = r9
            r0[r18] = r11
            r0[r17] = r12
            r0[r16] = r13
            r0[r27] = r15
            r2 = 11
            r0[r2] = r10
            r2 = 12
            r0[r2] = r14
            r2 = 13
            r0[r2] = r21
            r2 = 14
            r0[r2] = r3
            r2 = 15
            r0[r2] = r24
            r2 = 16
            r0[r2] = r25
            r2 = 17
            r0[r2] = r26
            r2 = 18
            r0[r2] = r8
            r2 = 19
            r0[r2] = r31
            r2 = 20
            r0[r2] = r33
            r2 = 21
            r0[r2] = r23
            r2 = 22
            r0[r2] = r1
            f940a = r0
            java.lang.String r0 = "\\d+"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            b = r0
            java.lang.String r0 = "&"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            f941c = r0
            java.lang.String r0 = "="
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            d = r0
            r0 = r20
            java.lang.String[] r0 = new java.lang.String[r0]
            e = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.u.<clinit>():void");
    }

    public static String a(e eVar) {
        String str = (String) eVar.e;
        if (str.startsWith("﻿")) {
            return str.substring(1);
        }
        return str;
    }

    public static String[] b(String str, String str2, char c5, boolean z) {
        String str3 = str2;
        int length = str3.length();
        int i2 = 0;
        ArrayList arrayList = null;
        while (i2 < length) {
            String str4 = str;
            int indexOf = str3.indexOf(str4, i2);
            if (indexOf < 0) {
                break;
            }
            int length2 = str4.length() + indexOf;
            boolean z3 = true;
            ArrayList arrayList2 = arrayList;
            int i7 = length2;
            while (z3) {
                int indexOf2 = str3.indexOf(c5, i7);
                if (indexOf2 < 0) {
                    i7 = str3.length();
                } else {
                    int i8 = indexOf2 - 1;
                    int i10 = 0;
                    while (i8 >= 0 && str3.charAt(i8) == '\\') {
                        i10++;
                        i8--;
                    }
                    if (i10 % 2 != 0) {
                        i7 = indexOf2 + 1;
                    } else {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList(3);
                        }
                        String substring = str3.substring(length2, indexOf2);
                        int indexOf3 = substring.indexOf(92);
                        if (indexOf3 >= 0) {
                            int length3 = substring.length();
                            StringBuilder sb2 = new StringBuilder(length3 - 1);
                            sb2.append(substring.toCharArray(), 0, indexOf3);
                            boolean z7 = false;
                            while (indexOf3 < length3) {
                                char charAt = substring.charAt(indexOf3);
                                if (z7 || charAt != '\\') {
                                    sb2.append(charAt);
                                    z7 = false;
                                } else {
                                    z7 = true;
                                }
                                indexOf3++;
                            }
                            substring = sb2.toString();
                        }
                        if (z) {
                            substring = substring.trim();
                        }
                        if (!substring.isEmpty()) {
                            arrayList2.add(substring);
                        }
                        i7 = indexOf2 + 1;
                    }
                }
                z3 = false;
            }
            char c6 = c5;
            i2 = i7;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(e);
    }

    public static String c(String str, String str2, char c5, boolean z) {
        String[] b5 = b(str, str2, c5, z);
        if (b5 == null) {
            return null;
        }
        return b5[0];
    }

    public static String[] d(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    public static int f(char c5) {
        if (c5 >= '0' && c5 <= '9') {
            return c5 - '0';
        }
        if (c5 >= 'a' && c5 <= 'f') {
            return c5 - 'W';
        }
        if (c5 < 'A' || c5 > 'F') {
            return -1;
        }
        return c5 - '7';
    }

    public static HashMap g(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        for (String split : f941c.split(str.substring(indexOf + 1))) {
            String[] split2 = d.split(split, 2);
            if (split2.length == 2) {
                try {
                    hashMap.put(split2[0], URLDecoder.decode(split2[1], "UTF-8"));
                } catch (UnsupportedEncodingException e7) {
                    throw new IllegalStateException(e7);
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        return hashMap;
    }

    public abstract r e(e eVar);
}
