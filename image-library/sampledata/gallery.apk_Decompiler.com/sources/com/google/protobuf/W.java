package com.google.protobuf;

import B1.a;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class W {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1590a;

    static {
        char[] cArr = new char[80];
        f1590a = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static void a(StringBuilder sb2, int i2) {
        while (i2 > 0) {
            int i7 = 80;
            if (i2 <= 80) {
                i7 = i2;
            }
            sb2.append(f1590a, 0, i7);
            i2 -= i7;
        }
    }

    public static void b(StringBuilder sb2, int i2, String str, Object obj) {
        if (obj instanceof List) {
            for (Object b : (List) obj) {
                b(sb2, i2, str, b);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry b5 : ((Map) obj).entrySet()) {
                b(sb2, i2, str, b5);
            }
        } else {
            sb2.append(10);
            a(sb2, i2);
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
                sb2.append(a.t(ByteString.s((String) obj)));
                sb2.append('\"');
            } else if (obj instanceof ByteString) {
                sb2.append(": \"");
                sb2.append(a.t((ByteString) obj));
                sb2.append('\"');
            } else if (obj instanceof GeneratedMessageLite) {
                sb2.append(" {");
                c((GeneratedMessageLite) obj, sb2, i2 + 2);
                sb2.append("\n");
                a(sb2, i2);
                sb2.append("}");
            } else if (obj instanceof Map.Entry) {
                sb2.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i8 = i2 + 2;
                b(sb2, i8, "key", entry.getKey());
                b(sb2, i8, "value", entry.getValue());
                sb2.append("\n");
                a(sb2, i2);
                sb2.append("}");
            } else {
                sb2.append(": ");
                sb2.append(obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x018b, code lost:
        if (((java.lang.Integer) r7).intValue() == 0) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01a0, code lost:
        if (java.lang.Float.floatToRawIntBits(((java.lang.Float) r7).floatValue()) == 0) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01b6, code lost:
        if (java.lang.Double.doubleToRawLongBits(((java.lang.Double) r7).doubleValue()) == 0) goto L_0x018d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(com.google.protobuf.GeneratedMessageLite r21, java.lang.StringBuilder r22, int r23) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
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
            if (r9 >= r7) goto L_0x008c
            r14 = r6[r9]
            int r15 = r14.getModifiers()
            boolean r15 = java.lang.reflect.Modifier.isStatic(r15)
            if (r15 == 0) goto L_0x0036
            goto L_0x0089
        L_0x0036:
            java.lang.String r15 = r14.getName()
            int r15 = r15.length()
            if (r15 >= r13) goto L_0x0041
            goto L_0x0089
        L_0x0041:
            java.lang.String r13 = r14.getName()
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x0053
            java.lang.String r10 = r14.getName()
            r3.add(r10)
            goto L_0x0089
        L_0x0053:
            int r12 = r14.getModifiers()
            boolean r12 = java.lang.reflect.Modifier.isPublic(r12)
            if (r12 != 0) goto L_0x005e
            goto L_0x0089
        L_0x005e:
            java.lang.Class[] r12 = r14.getParameterTypes()
            int r12 = r12.length
            if (r12 == 0) goto L_0x0066
            goto L_0x0089
        L_0x0066:
            java.lang.String r12 = r14.getName()
            boolean r11 = r12.startsWith(r11)
            if (r11 == 0) goto L_0x0078
            java.lang.String r10 = r14.getName()
            r4.put(r10, r14)
            goto L_0x0089
        L_0x0078:
            java.lang.String r11 = r14.getName()
            boolean r10 = r11.startsWith(r10)
            if (r10 == 0) goto L_0x0089
            java.lang.String r10 = r14.getName()
            r5.put(r10, r14)
        L_0x0089:
            int r9 = r9 + 1
            goto L_0x0020
        L_0x008c:
            java.util.Set r6 = r5.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0203
            java.lang.Object r7 = r6.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r9 = r7.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r9 = r9.substring(r13)
            java.lang.String r14 = "List"
            boolean r15 = r9.endsWith(r14)
            if (r15 == 0) goto L_0x00e4
            java.lang.String r15 = "OrBuilderList"
            boolean r15 = r9.endsWith(r15)
            if (r15 != 0) goto L_0x00e4
            boolean r14 = r9.equals(r14)
            if (r14 != 0) goto L_0x00e4
            java.lang.Object r14 = r7.getValue()
            java.lang.reflect.Method r14 = (java.lang.reflect.Method) r14
            if (r14 == 0) goto L_0x00e4
            java.lang.Class r15 = r14.getReturnType()
            java.lang.Class<java.util.List> r13 = java.util.List.class
            boolean r13 = r15.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r7 = 4
            java.lang.String r7 = t1.C0280e.d(r7, r8, r9)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r14, r0, r9)
            b(r1, r2, r7, r9)
        L_0x00e2:
            r13 = 3
            goto L_0x0094
        L_0x00e4:
            java.lang.String r13 = "Map"
            boolean r14 = r9.endsWith(r13)
            if (r14 == 0) goto L_0x0127
            boolean r13 = r9.equals(r13)
            if (r13 != 0) goto L_0x0127
            java.lang.Object r13 = r7.getValue()
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r13 == 0) goto L_0x0127
            java.lang.Class r14 = r13.getReturnType()
            java.lang.Class<java.util.Map> r15 = java.util.Map.class
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0127
            java.lang.Class<java.lang.Deprecated> r14 = java.lang.Deprecated.class
            boolean r14 = r13.isAnnotationPresent(r14)
            if (r14 != 0) goto L_0x0127
            int r14 = r13.getModifiers()
            boolean r14 = java.lang.reflect.Modifier.isPublic(r14)
            if (r14 == 0) goto L_0x0127
            r14 = 3
            java.lang.String r7 = t1.C0280e.d(r14, r8, r9)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r13, r0, r9)
            b(r1, r2, r7, r9)
            goto L_0x00e2
        L_0x0127:
            java.lang.String r13 = r12.concat(r9)
            boolean r13 = r3.contains(r13)
            if (r13 != 0) goto L_0x0132
        L_0x0131:
            goto L_0x00e2
        L_0x0132:
            java.lang.String r13 = "Bytes"
            boolean r13 = r9.endsWith(r13)
            if (r13 == 0) goto L_0x0157
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r10)
            int r14 = r9.length()
            int r14 = r14 + -5
            java.lang.String r14 = r9.substring(r8, r14)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            boolean r13 = r5.containsKey(r13)
            if (r13 == 0) goto L_0x0157
            goto L_0x0131
        L_0x0157:
            java.lang.Object r7 = r7.getValue()
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.String r13 = r11.concat(r9)
            java.lang.Object r13 = r4.get(r13)
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r7 == 0) goto L_0x00e2
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Object r7 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r7, r0, r14)
            if (r13 != 0) goto L_0x01f0
            boolean r13 = r7 instanceof java.lang.Boolean
            r14 = 1
            if (r13 == 0) goto L_0x0180
            r13 = r7
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            r13 = r13 ^ r14
            goto L_0x01eb
        L_0x0180:
            boolean r13 = r7 instanceof java.lang.Integer
            if (r13 == 0) goto L_0x0191
            r13 = r7
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r13 != 0) goto L_0x018f
        L_0x018d:
            r13 = r14
            goto L_0x01eb
        L_0x018f:
            r13 = r8
            goto L_0x01eb
        L_0x0191:
            boolean r13 = r7 instanceof java.lang.Float
            if (r13 == 0) goto L_0x01a3
            r13 = r7
            java.lang.Float r13 = (java.lang.Float) r13
            float r13 = r13.floatValue()
            int r13 = java.lang.Float.floatToRawIntBits(r13)
            if (r13 != 0) goto L_0x018f
            goto L_0x018d
        L_0x01a3:
            boolean r13 = r7 instanceof java.lang.Double
            if (r13 == 0) goto L_0x01b9
            r13 = r7
            java.lang.Double r13 = (java.lang.Double) r13
            double r17 = r13.doubleValue()
            long r17 = java.lang.Double.doubleToRawLongBits(r17)
            r19 = 0
            int r13 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r13 != 0) goto L_0x018f
            goto L_0x018d
        L_0x01b9:
            boolean r13 = r7 instanceof java.lang.String
            if (r13 == 0) goto L_0x01c4
            java.lang.String r13 = ""
            boolean r13 = r7.equals(r13)
            goto L_0x01eb
        L_0x01c4:
            boolean r13 = r7 instanceof com.google.protobuf.ByteString
            if (r13 == 0) goto L_0x01cf
            com.google.protobuf.i r13 = com.google.protobuf.ByteString.e
            boolean r13 = r7.equals(r13)
            goto L_0x01eb
        L_0x01cf:
            boolean r13 = r7 instanceof com.google.protobuf.MessageLite
            if (r13 == 0) goto L_0x01dd
            r13 = r7
            com.google.protobuf.MessageLite r13 = (com.google.protobuf.MessageLite) r13
            com.google.protobuf.MessageLite r13 = r13.getDefaultInstanceForType()
            if (r7 != r13) goto L_0x018f
            goto L_0x018d
        L_0x01dd:
            boolean r13 = r7 instanceof java.lang.Enum
            if (r13 == 0) goto L_0x018f
            r13 = r7
            java.lang.Enum r13 = (java.lang.Enum) r13
            int r13 = r13.ordinal()
            if (r13 != 0) goto L_0x018f
            goto L_0x018d
        L_0x01eb:
            if (r13 != 0) goto L_0x01ee
            goto L_0x01fc
        L_0x01ee:
            r14 = r8
            goto L_0x01fc
        L_0x01f0:
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Object r13 = com.google.protobuf.GeneratedMessageLite.invokeOrDie(r13, r0, r14)
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r14 = r13.booleanValue()
        L_0x01fc:
            if (r14 == 0) goto L_0x00e2
            b(r1, r2, r9, r7)
            goto L_0x00e2
        L_0x0203:
            com.google.protobuf.UnknownFieldSetLite r0 = r0.unknownFields
            if (r0 == 0) goto L_0x0221
        L_0x0207:
            int r3 = r0.f1588a
            if (r8 >= r3) goto L_0x0221
            int[] r3 = r0.b
            r3 = r3[r8]
            r16 = 3
            int r3 = r3 >>> 3
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.Object[] r4 = r0.f1589c
            r4 = r4[r8]
            b(r1, r2, r3, r4)
            int r8 = r8 + 1
            goto L_0x0207
        L_0x0221:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.W.c(com.google.protobuf.GeneratedMessageLite, java.lang.StringBuilder, int):void");
    }
}
