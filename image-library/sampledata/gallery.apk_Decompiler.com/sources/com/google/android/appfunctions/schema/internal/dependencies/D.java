package com.google.android.appfunctions.schema.internal.dependencies;

import L1.d;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class D {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1199a;

    static {
        char[] cArr = new char[80];
        f1199a = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static void a(StringBuilder sb2, int i2, String str, Object obj) {
        if (obj instanceof List) {
            for (Object a7 : (List) obj) {
                a(sb2, i2, str, a7);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry a10 : ((Map) obj).entrySet()) {
                a(sb2, i2, str, a10);
            }
        } else {
            sb2.append(10);
            c(sb2, i2);
            if (!str.isEmpty()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(Character.toLowerCase(str.charAt(0)));
                for (int i7 = 1; i7 < str.length(); i7++) {
                    char charAt = str.charAt(i7);
                    if (Character.isUpperCase(charAt)) {
                        sb3.append("_");
                    }
                    sb3.append(Character.toLowerCase(charAt));
                }
                str = sb3.toString();
            }
            sb2.append(str);
            if (obj instanceof String) {
                sb2.append(": \"");
                g0 g0Var = f0.e;
                sb2.append(d.A(new g0(((String) obj).getBytes(C0106p.f1226a))));
                sb2.append('\"');
            } else if (obj instanceof f0) {
                sb2.append(": \"");
                sb2.append(d.A((f0) obj));
                sb2.append('\"');
            } else if (obj instanceof C0102l) {
                sb2.append(" {");
                b((C0102l) obj, sb2, i2 + 2);
                sb2.append("\n");
                c(sb2, i2);
                sb2.append("}");
            } else if (obj instanceof Map.Entry) {
                int i8 = i2 + 2;
                sb2.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                a(sb2, i8, "key", entry.getKey());
                a(sb2, i8, "value", entry.getValue());
                sb2.append("\n");
                c(sb2, i2);
                sb2.append("}");
            } else {
                sb2.append(": ");
                sb2.append(obj);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(com.google.android.appfunctions.schema.internal.dependencies.C0102l r19, java.lang.StringBuilder r20, int r21) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.TreeMap r5 = new java.util.TreeMap
            r5.<init>()
            java.lang.Class r6 = r0.getClass()
            java.lang.reflect.Method[] r6 = r6.getDeclaredMethods()
            int r7 = r6.length
            r8 = 0
            r9 = r8
        L_0x0020:
            java.lang.String r10 = "get"
            java.lang.String r11 = "has"
            java.lang.String r12 = "set"
            r13 = 3
            if (r9 >= r7) goto L_0x0089
            r14 = r6[r9]
            int r15 = r14.getModifiers()
            boolean r15 = java.lang.reflect.Modifier.isStatic(r15)
            if (r15 == 0) goto L_0x0036
            goto L_0x0086
        L_0x0036:
            java.lang.String r15 = r14.getName()
            int r15 = r15.length()
            if (r15 < r13) goto L_0x0086
            java.lang.String r13 = r14.getName()
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x0052
            java.lang.String r10 = r14.getName()
            r3.add(r10)
            goto L_0x0086
        L_0x0052:
            int r12 = r14.getModifiers()
            boolean r12 = java.lang.reflect.Modifier.isPublic(r12)
            if (r12 == 0) goto L_0x0086
            java.lang.Class[] r12 = r14.getParameterTypes()
            int r12 = r12.length
            if (r12 != 0) goto L_0x0086
            java.lang.String r12 = r14.getName()
            boolean r11 = r12.startsWith(r11)
            if (r11 == 0) goto L_0x0075
            java.lang.String r10 = r14.getName()
            r4.put(r10, r14)
            goto L_0x0086
        L_0x0075:
            java.lang.String r11 = r14.getName()
            boolean r10 = r11.startsWith(r10)
            if (r10 == 0) goto L_0x0086
            java.lang.String r10 = r14.getName()
            r5.put(r10, r14)
        L_0x0086:
            int r9 = r9 + 1
            goto L_0x0020
        L_0x0089:
            java.util.Set r6 = r5.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0091:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x020a
            java.lang.Object r7 = r6.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r9 = r7.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r9 = r9.substring(r13)
            java.lang.String r14 = "List"
            boolean r15 = r9.endsWith(r14)
            if (r15 == 0) goto L_0x00e9
            java.lang.String r15 = "OrBuilderList"
            boolean r15 = r9.endsWith(r15)
            if (r15 != 0) goto L_0x00e9
            boolean r14 = r9.equals(r14)
            if (r14 != 0) goto L_0x00e9
            java.lang.Object r14 = r7.getValue()
            java.lang.reflect.Method r14 = (java.lang.reflect.Method) r14
            if (r14 == 0) goto L_0x00e9
            java.lang.Class r15 = r14.getReturnType()
            r16 = r13
            java.lang.Class<java.util.List> r13 = java.util.List.class
            boolean r13 = r15.equals(r13)
            if (r13 == 0) goto L_0x00eb
            int r7 = r9.length()
            int r7 = r7 + -4
            java.lang.String r7 = r9.substring(r8, r7)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.appfunctions.schema.internal.dependencies.C0102l.d(r14, r0, r9)
            a(r1, r2, r7, r9)
        L_0x00e6:
            r13 = r16
            goto L_0x0091
        L_0x00e9:
            r16 = r13
        L_0x00eb:
            java.lang.String r13 = "Map"
            boolean r14 = r9.endsWith(r13)
            if (r14 == 0) goto L_0x0133
            boolean r13 = r9.equals(r13)
            if (r13 != 0) goto L_0x0133
            java.lang.Object r13 = r7.getValue()
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r13 == 0) goto L_0x0133
            java.lang.Class r14 = r13.getReturnType()
            java.lang.Class<java.util.Map> r15 = java.util.Map.class
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0133
            java.lang.Class<java.lang.Deprecated> r14 = java.lang.Deprecated.class
            boolean r14 = r13.isAnnotationPresent(r14)
            if (r14 != 0) goto L_0x0133
            int r14 = r13.getModifiers()
            boolean r14 = java.lang.reflect.Modifier.isPublic(r14)
            if (r14 == 0) goto L_0x0133
            int r7 = r9.length()
            int r7 = r7 + -3
            java.lang.String r7 = r9.substring(r8, r7)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.appfunctions.schema.internal.dependencies.C0102l.d(r13, r0, r9)
            a(r1, r2, r7, r9)
            goto L_0x00e6
        L_0x0133:
            java.lang.String r13 = r12.concat(r9)
            boolean r13 = r3.contains(r13)
            if (r13 == 0) goto L_0x00e6
            java.lang.String r13 = "Bytes"
            boolean r13 = r9.endsWith(r13)
            if (r13 == 0) goto L_0x015d
            int r13 = r9.length()
            int r13 = r13 + -5
            java.lang.String r13 = r9.substring(r8, r13)
            java.lang.String r13 = java.lang.String.valueOf(r13)
            java.lang.String r13 = r10.concat(r13)
            boolean r13 = r5.containsKey(r13)
            if (r13 != 0) goto L_0x00e6
        L_0x015d:
            java.lang.Object r7 = r7.getValue()
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.String r13 = r11.concat(r9)
            java.lang.Object r13 = r4.get(r13)
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r7 == 0) goto L_0x00e6
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Object r7 = com.google.android.appfunctions.schema.internal.dependencies.C0102l.d(r7, r0, r14)
            if (r13 != 0) goto L_0x01f7
            boolean r13 = r7 instanceof java.lang.Boolean
            if (r13 == 0) goto L_0x0186
            r13 = r7
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00e6
            goto L_0x0205
        L_0x0186:
            boolean r13 = r7 instanceof java.lang.Integer
            if (r13 == 0) goto L_0x0195
            r13 = r7
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r13 == 0) goto L_0x00e6
            goto L_0x0205
        L_0x0195:
            boolean r13 = r7 instanceof java.lang.Float
            if (r13 == 0) goto L_0x01a7
            r13 = r7
            java.lang.Float r13 = (java.lang.Float) r13
            float r13 = r13.floatValue()
            int r13 = java.lang.Float.floatToRawIntBits(r13)
            if (r13 == 0) goto L_0x00e6
            goto L_0x0205
        L_0x01a7:
            boolean r13 = r7 instanceof java.lang.Double
            if (r13 == 0) goto L_0x01bd
            r13 = r7
            java.lang.Double r13 = (java.lang.Double) r13
            double r13 = r13.doubleValue()
            long r13 = java.lang.Double.doubleToRawLongBits(r13)
            r17 = 0
            int r13 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r13 == 0) goto L_0x00e6
            goto L_0x0205
        L_0x01bd:
            boolean r13 = r7 instanceof java.lang.String
            if (r13 == 0) goto L_0x01c8
            java.lang.String r13 = ""
            boolean r13 = r7.equals(r13)
            goto L_0x01d2
        L_0x01c8:
            boolean r13 = r7 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r13 == 0) goto L_0x01d5
            com.google.android.appfunctions.schema.internal.dependencies.g0 r13 = com.google.android.appfunctions.schema.internal.dependencies.f0.e
            boolean r13 = r7.equals(r13)
        L_0x01d2:
            if (r13 != 0) goto L_0x00e6
            goto L_0x0205
        L_0x01d5:
            boolean r13 = r7 instanceof com.google.android.appfunctions.schema.internal.dependencies.A
            if (r13 == 0) goto L_0x01e9
            r13 = r7
            com.google.android.appfunctions.schema.internal.dependencies.A r13 = (com.google.android.appfunctions.schema.internal.dependencies.A) r13
            com.google.android.appfunctions.schema.internal.dependencies.l r13 = (com.google.android.appfunctions.schema.internal.dependencies.C0102l) r13
            com.google.android.appfunctions.schema.internal.dependencies.k r14 = com.google.android.appfunctions.schema.internal.dependencies.C0101k.zzf
            java.lang.Object r13 = r13.b(r14)
            com.google.android.appfunctions.schema.internal.dependencies.l r13 = (com.google.android.appfunctions.schema.internal.dependencies.C0102l) r13
            if (r7 == r13) goto L_0x00e6
            goto L_0x0205
        L_0x01e9:
            boolean r13 = r7 instanceof java.lang.Enum
            if (r13 == 0) goto L_0x0205
            r13 = r7
            java.lang.Enum r13 = (java.lang.Enum) r13
            int r13 = r13.ordinal()
            if (r13 == 0) goto L_0x00e6
            goto L_0x0205
        L_0x01f7:
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Object r13 = com.google.android.appfunctions.schema.internal.dependencies.C0102l.d(r13, r0, r14)
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00e6
        L_0x0205:
            a(r1, r2, r9, r7)
            goto L_0x00e6
        L_0x020a:
            r16 = r13
            com.google.android.appfunctions.schema.internal.dependencies.O r0 = r0.unknownFields
            if (r0 == 0) goto L_0x0228
        L_0x0210:
            int r3 = r0.f1213a
            if (r8 >= r3) goto L_0x0228
            int[] r3 = r0.b
            r3 = r3[r8]
            int r3 = r3 >>> 3
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.Object[] r4 = r0.f1214c
            r4 = r4[r8]
            a(r1, r2, r3, r4)
            int r8 = r8 + 1
            goto L_0x0210
        L_0x0228:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.D.b(com.google.android.appfunctions.schema.internal.dependencies.l, java.lang.StringBuilder, int):void");
    }

    public static void c(StringBuilder sb2, int i2) {
        while (i2 > 0) {
            int i7 = 80;
            if (i2 <= 80) {
                i7 = i2;
            }
            sb2.append(f1199a, 0, i7);
            i2 -= i7;
        }
    }
}
