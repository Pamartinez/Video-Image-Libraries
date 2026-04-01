package X2;

import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0062b extends u {
    public final /* synthetic */ int f;

    public /* synthetic */ C0062b(int i2) {
        this.f = i2;
    }

    public static void h(String str, ArrayList arrayList, ArrayList arrayList2) {
        int indexOf = str.indexOf(59);
        String str2 = null;
        if (indexOf < 0) {
            arrayList.add(str);
            arrayList2.add((Object) null);
            return;
        }
        arrayList.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            str2 = substring.substring(4);
        }
        arrayList2.add(str2);
    }

    public static String i(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr != null && bArr.length > 0) {
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    hexString = String.valueOf(0) + hexString;
                }
                stringBuffer.append(hexString.toUpperCase());
            }
        }
        return stringBuffer.toString();
    }

    public static String j(int i2, String str) {
        if (str.charAt(i2) != '(') {
            return null;
        }
        String substring = str.substring(i2 + 1);
        StringBuilder sb2 = new StringBuilder();
        for (int i7 = 0; i7 < substring.length(); i7++) {
            char charAt = substring.charAt(i7);
            if (charAt == ')') {
                return sb2.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb2.append(charAt);
        }
        return sb2.toString();
    }

    public static String[] k(String str, String str2) {
        String str3;
        ArrayList arrayList = null;
        for (int i2 = 1; i2 <= 3; i2++) {
            String[] b = u.b(str + i2 + NumericEnum.SEP, str2, 13, true);
            if (b == null) {
                str3 = null;
            } else {
                str3 = b[0];
            }
            if (str3 == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(str3);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(u.e);
    }

    public static String l(String str, String str2) {
        List k = B.k(str, str2, true, false);
        if (k == null || k.isEmpty()) {
            return null;
        }
        return (String) k.get(0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: java.lang.String} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x059a A[FALL_THROUGH] */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x059d  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x05aa  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x05b7  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x05c4  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x05d1  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x05de  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x05eb  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x05f8  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0605  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0612  */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x0620  */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x062e  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x063c  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x064a  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x0658  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x0666  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x0674  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0682  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x0690  */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x069e  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x06ac  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x06ba  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x06c8  */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x06d6  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x06e4  */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x06f2  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0700  */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x070e  */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x071a  */
    /* JADX WARNING: Removed duplicated region for block: B:358:0x0726  */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0732  */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x073e  */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x074a  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0756  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x0762  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x0770  */
    /* JADX WARNING: Removed duplicated region for block: B:378:0x0774  */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x0788  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x078f  */
    /* JADX WARNING: Removed duplicated region for block: B:384:0x0798  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x079f  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x07a2  */
    /* JADX WARNING: Removed duplicated region for block: B:387:0x07a5  */
    /* JADX WARNING: Removed duplicated region for block: B:388:0x07a8  */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x07ab  */
    /* JADX WARNING: Removed duplicated region for block: B:390:0x07ae  */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x07b0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X2.r e(D0.e r40) {
        /*
            r39 = this;
            r0 = r39
            r1 = r40
            int r0 = r0.f
            r2 = 12
            r3 = 8
            r4 = 58
            r5 = 63
            r6 = 59
            r8 = 3
            r9 = -1
            r10 = 7
            r11 = 6
            r12 = 2
            r13 = 5
            r14 = 4
            r15 = 1
            r7 = 0
            r16 = 0
            switch(r0) {
                case 0: goto L_0x07c4;
                case 1: goto L_0x051f;
                case 2: goto L_0x04f0;
                case 3: goto L_0x03b6;
                case 4: goto L_0x0343;
                case 5: goto L_0x02a9;
                case 6: goto L_0x0265;
                case 7: goto L_0x0214;
                case 8: goto L_0x01d7;
                case 9: goto L_0x0110;
                default: goto L_0x001e;
            }
        L_0x001e:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "WIFI:"
            boolean r1 = r0.startsWith(r1)
            java.lang.String r2 = "I:"
            java.lang.String r3 = "H:"
            if (r1 == 0) goto L_0x00a6
            X2.G r23 = X2.G.WIFI
            java.lang.String r1 = r0.substring(r13)
            java.lang.String r4 = "S:"
            java.lang.String r19 = X2.u.c(r4, r1, r6, r7)
            if (r19 == 0) goto L_0x010f
            boolean r4 = r19.isEmpty()
            if (r4 == 0) goto L_0x0044
            goto L_0x010f
        L_0x0044:
            java.lang.String r4 = "P:"
            java.lang.String[] r4 = X2.u.b(r4, r1, r6, r7)
            if (r4 != 0) goto L_0x004f
            r20 = r16
            goto L_0x0053
        L_0x004f:
            r4 = r4[r7]
            r20 = r4
        L_0x0053:
            java.lang.String r4 = "T:"
            java.lang.String[] r4 = X2.u.b(r4, r1, r6, r7)
            if (r4 != 0) goto L_0x005e
            r4 = r16
            goto L_0x0060
        L_0x005e:
            r4 = r4[r7]
        L_0x0060:
            if (r4 != 0) goto L_0x0064
            java.lang.String r4 = "nopass"
        L_0x0064:
            r18 = r4
            java.lang.String[] r4 = X2.u.b(r3, r1, r6, r7)
            if (r4 != 0) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r16 = r4[r7]
        L_0x006f:
            boolean r21 = java.lang.Boolean.parseBoolean(r16)
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x007a
            goto L_0x007c
        L_0x007a:
            r2 = r2[r7]
        L_0x007c:
            java.lang.String r2 = "A:"
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x0085
            goto L_0x0087
        L_0x0085:
            r2 = r2[r7]
        L_0x0087:
            java.lang.String r2 = "E:"
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r2 = r2[r7]
        L_0x0092:
            java.lang.String[] r1 = X2.u.b(r3, r1, r6, r7)
            if (r1 != 0) goto L_0x0099
            goto L_0x009b
        L_0x0099:
            r1 = r1[r7]
        L_0x009b:
            X2.F r17 = new X2.F
            r22 = r0
            r17.<init>(r18, r19, r20, r21, r22, r23)
        L_0x00a2:
            r16 = r17
            goto L_0x010f
        L_0x00a6:
            java.lang.String r1 = "DPP:"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x010f
            java.lang.String r1 = r0.substring(r14)
            java.lang.String r4 = "C:"
            java.lang.String r18 = X2.u.c(r4, r1, r6, r7)
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x00c1
            r20 = r16
            goto L_0x00c5
        L_0x00c1:
            r2 = r2[r7]
            r20 = r2
        L_0x00c5:
            java.lang.String r2 = "M:"
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x00d0
            r19 = r16
            goto L_0x00d4
        L_0x00d0:
            r2 = r2[r7]
            r19 = r2
        L_0x00d4:
            java.lang.String r2 = "K:"
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x00df
            r21 = r16
            goto L_0x00e3
        L_0x00df:
            r2 = r2[r7]
            r21 = r2
        L_0x00e3:
            if (r21 == 0) goto L_0x010f
            boolean r2 = r21.isEmpty()
            if (r2 == 0) goto L_0x00ec
            goto L_0x010f
        L_0x00ec:
            java.lang.String r2 = "V:"
            java.lang.String[] r2 = X2.u.b(r2, r1, r6, r7)
            if (r2 != 0) goto L_0x00f7
            r22 = r16
            goto L_0x00fb
        L_0x00f7:
            r2 = r2[r7]
            r22 = r2
        L_0x00fb:
            java.lang.String[] r1 = X2.u.b(r3, r1, r6, r7)
            if (r1 != 0) goto L_0x0104
        L_0x0101:
            r23 = r16
            goto L_0x0107
        L_0x0104:
            r16 = r1[r7]
            goto L_0x0101
        L_0x0107:
            X2.E r17 = new X2.E
            r24 = r0
            r17.<init>(r18, r19, r20, r21, r22, r23, r24)
            goto L_0x00a2
        L_0x010f:
            return r16
        L_0x0110:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "BEGIN:VEVENT"
            int r1 = r0.indexOf(r1)
            if (r1 >= 0) goto L_0x011e
            goto L_0x01d6
        L_0x011e:
            java.lang.String r1 = "SUMMARY"
            java.lang.String r18 = l(r1, r0)
            java.lang.String r1 = "DTSTART"
            java.lang.String r19 = l(r1, r0)
            if (r19 != 0) goto L_0x012e
            goto L_0x01d6
        L_0x012e:
            java.lang.String r1 = "DTEND"
            java.lang.String r20 = l(r1, r0)
            java.lang.String r1 = "DURATION"
            java.lang.String r21 = l(r1, r0)
            java.lang.String r1 = "LOCATION"
            java.lang.String r22 = l(r1, r0)
            java.lang.String r1 = "ORGANIZER"
            java.lang.String r1 = l(r1, r0)
            java.lang.String r2 = "MAILTO:"
            java.lang.String r3 = "mailto:"
            if (r1 == 0) goto L_0x015c
            boolean r4 = r1.startsWith(r3)
            if (r4 != 0) goto L_0x0158
            boolean r4 = r1.startsWith(r2)
            if (r4 == 0) goto L_0x015c
        L_0x0158:
            java.lang.String r1 = r1.substring(r10)
        L_0x015c:
            r23 = r1
            java.lang.String r1 = "ATTENDEE"
            java.util.ArrayList r1 = X2.B.m(r1, r0, r15, r7)
            if (r1 == 0) goto L_0x0187
            boolean r4 = r1.isEmpty()
            if (r4 == 0) goto L_0x016d
            goto L_0x0187
        L_0x016d:
            int r4 = r1.size()
            java.lang.String[] r5 = new java.lang.String[r4]
            r8 = r7
        L_0x0174:
            if (r8 >= r4) goto L_0x0189
            java.lang.Object r9 = r1.get(r8)
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r9.get(r7)
            java.lang.String r9 = (java.lang.String) r9
            r5[r8] = r9
            int r8 = r8 + 1
            goto L_0x0174
        L_0x0187:
            r5 = r16
        L_0x0189:
            if (r5 == 0) goto L_0x01a8
            r1 = r7
        L_0x018c:
            int r4 = r5.length
            if (r1 >= r4) goto L_0x01a8
            r4 = r5[r1]
            if (r4 == 0) goto L_0x01a3
            boolean r8 = r4.startsWith(r3)
            if (r8 != 0) goto L_0x019f
            boolean r8 = r4.startsWith(r2)
            if (r8 == 0) goto L_0x01a3
        L_0x019f:
            java.lang.String r4 = r4.substring(r10)
        L_0x01a3:
            r5[r1] = r4
            int r1 = r1 + 1
            goto L_0x018c
        L_0x01a8:
            java.lang.String r1 = "DESCRIPTION"
            java.lang.String r25 = l(r1, r0)
            java.lang.String r1 = "GEO"
            java.lang.String r0 = l(r1, r0)
            if (r0 != 0) goto L_0x01b7
            goto L_0x01cd
        L_0x01b7:
            int r1 = r0.indexOf(r6)
            if (r1 >= 0) goto L_0x01be
            goto L_0x01d6
        L_0x01be:
            java.lang.String r2 = r0.substring(r7, r1)     // Catch:{ NumberFormatException -> 0x01d6 }
            java.lang.Double.parseDouble(r2)     // Catch:{ NumberFormatException -> 0x01d6 }
            int r1 = r1 + r15
            java.lang.String r0 = r0.substring(r1)     // Catch:{ NumberFormatException -> 0x01d6 }
            java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x01d6 }
        L_0x01cd:
            X2.g r17 = new X2.g     // Catch:{  }
            r24 = r5
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{  }
            r16 = r17
        L_0x01d6:
            return r16
        L_0x01d7:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "tel:"
            boolean r2 = r0.startsWith(r1)
            java.lang.String r3 = "TEL:"
            if (r2 != 0) goto L_0x01ed
            boolean r2 = r0.startsWith(r3)
            if (r2 != 0) goto L_0x01ed
            goto L_0x0213
        L_0x01ed:
            boolean r2 = r0.startsWith(r3)
            if (r2 == 0) goto L_0x01fc
            java.lang.String r2 = r0.substring(r14)
            java.lang.String r1 = i.C0212a.l(r1, r2)
            goto L_0x01fd
        L_0x01fc:
            r1 = r0
        L_0x01fd:
            int r2 = r0.indexOf(r5, r14)
            if (r2 >= 0) goto L_0x0208
            java.lang.String r0 = r0.substring(r14)
            goto L_0x020c
        L_0x0208:
            java.lang.String r0 = r0.substring(r14, r2)
        L_0x020c:
            X2.x r2 = new X2.x
            r2.<init>(r0, r1)
            r16 = r2
        L_0x0213:
            return r16
        L_0x0214:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "smtp:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x022a
            java.lang.String r1 = "SMTP:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x022a
            goto L_0x0264
        L_0x022a:
            java.lang.String r0 = r0.substring(r13)
            int r1 = r0.indexOf(r4)
            if (r1 < 0) goto L_0x0254
            int r2 = r1 + 1
            java.lang.String r2 = r0.substring(r2)
            java.lang.String r0 = r0.substring(r7, r1)
            int r1 = r2.indexOf(r4)
            if (r1 < 0) goto L_0x0252
            int r3 = r1 + 1
            java.lang.String r16 = r2.substring(r3)
            java.lang.String r1 = r2.substring(r7, r1)
            r5 = r1
        L_0x024f:
            r6 = r16
            goto L_0x0257
        L_0x0252:
            r5 = r2
            goto L_0x024f
        L_0x0254:
            r5 = r16
            r6 = r5
        L_0x0257:
            X2.h r1 = new X2.h
            java.lang.String[] r2 = new java.lang.String[]{r0}
            r3 = 0
            r4 = 0
            r1.<init>(r2, r3, r4, r5, r6)
            r16 = r1
        L_0x0264:
            return r16
        L_0x0265:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "smsto:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x028b
            java.lang.String r1 = "SMSTO:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x028b
            java.lang.String r1 = "mmsto:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x028b
            java.lang.String r1 = "MMSTO:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x028b
            goto L_0x02a8
        L_0x028b:
            java.lang.String r0 = r0.substring(r11)
            int r1 = r0.indexOf(r4)
            if (r1 < 0) goto L_0x029f
            int r2 = r1 + 1
            java.lang.String r16 = r0.substring(r2)
            java.lang.String r0 = r0.substring(r7, r1)
        L_0x029f:
            r1 = r16
            X2.v r2 = new X2.v
            r2.<init>(r0, r1)
            r16 = r2
        L_0x02a8:
            return r16
        L_0x02a9:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "sms:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x02d0
            java.lang.String r1 = "SMS:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x02d0
            java.lang.String r1 = "mms:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x02d0
            java.lang.String r1 = "MMS:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x02d0
            goto L_0x0342
        L_0x02d0:
            java.util.HashMap r1 = X2.u.g(r0)
            if (r1 == 0) goto L_0x02f4
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x02f4
            java.lang.String r2 = "subject"
            java.lang.Object r2 = r1.get(r2)
            r16 = r2
            java.lang.String r16 = (java.lang.String) r16
            java.lang.String r2 = "body"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            r2 = r1
            r7 = r15
            r1 = r16
            goto L_0x02f7
        L_0x02f4:
            r1 = r16
            r2 = r1
        L_0x02f7:
            int r3 = r0.indexOf(r5, r14)
            if (r3 < 0) goto L_0x0305
            if (r7 != 0) goto L_0x0300
            goto L_0x0305
        L_0x0300:
            java.lang.String r0 = r0.substring(r14, r3)
            goto L_0x0309
        L_0x0305:
            java.lang.String r0 = r0.substring(r14)
        L_0x0309:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r15)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r15)
        L_0x0313:
            int r5 = r9 + 1
            r6 = 44
            int r6 = r0.indexOf(r6, r5)
            if (r6 <= r9) goto L_0x0326
            java.lang.String r5 = r0.substring(r5, r6)
            h(r5, r3, r4)
            r9 = r6
            goto L_0x0313
        L_0x0326:
            java.lang.String r0 = r0.substring(r5)
            h(r0, r3, r4)
            X2.v r0 = new X2.v
            java.lang.String[] r5 = X2.u.e
            java.lang.Object[] r3 = r3.toArray(r5)
            java.lang.String[] r3 = (java.lang.String[]) r3
            java.lang.Object[] r4 = r4.toArray(r5)
            java.lang.String[] r4 = (java.lang.String[]) r4
            r0.<init>(r3, r1, r4, r2)
            r16 = r0
        L_0x0342:
            return r16
        L_0x0343:
            java.lang.Object r0 = r1.f
            W2.a r0 = (W2.a) r0
            W2.a r4 = W2.a.UPC_A
            if (r0 == r4) goto L_0x0358
            W2.a r4 = W2.a.UPC_E
            if (r0 == r4) goto L_0x0358
            W2.a r4 = W2.a.EAN_8
            if (r0 == r4) goto L_0x0358
            W2.a r4 = W2.a.EAN_13
            if (r0 == r4) goto L_0x0358
            goto L_0x03b5
        L_0x0358:
            java.lang.String r1 = X2.u.a(r1)
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x03b5
            int r5 = r1.length()
            if (r4 != r5) goto L_0x03b5
            java.util.regex.Pattern r4 = X2.u.b
            java.util.regex.Matcher r4 = r4.matcher(r1)
            boolean r4 = r4.matches()
            if (r4 == 0) goto L_0x03b5
            W2.a r4 = W2.a.UPC_E
            if (r0 != r4) goto L_0x03ae
            int r0 = r1.length()
            if (r0 != r3) goto L_0x03ae
            char[] r0 = new char[r11]
            r1.getChars(r15, r10, r0, r7)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            r1.charAt(r7)
            char r2 = r0[r13]
            switch(r2) {
                case 48: goto L_0x039f;
                case 49: goto L_0x039f;
                case 50: goto L_0x039f;
                case 51: goto L_0x0398;
                case 52: goto L_0x0394;
                default: goto L_0x0390;
            }
        L_0x0390:
            r4.append(r0, r7, r13)
            goto L_0x03a5
        L_0x0394:
            r4.append(r0, r7, r14)
            goto L_0x03a5
        L_0x0398:
            r4.append(r0, r7, r8)
            r4.append(r0, r8, r12)
            goto L_0x03a5
        L_0x039f:
            r4.append(r0, r7, r12)
            r4.append(r0, r12, r8)
        L_0x03a5:
            int r0 = r1.length()
            if (r0 < r3) goto L_0x03ae
            r1.charAt(r10)
        L_0x03ae:
            X2.p r0 = new X2.p
            r0.<init>((java.lang.String) r1, (int) r12)
            r16 = r0
        L_0x03b5:
            return r16
        L_0x03b6:
            java.lang.Object r0 = r1.e
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "fido:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x03cc
            java.lang.String r1 = "FIDO:"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x03cc
            goto L_0x04ef
        L_0x03cc:
            java.lang.String r1 = r0.substring(r11)
            java.lang.String r2 = "^[-\\+]?[\\d]*$"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.regex.Matcher r2 = r2.matcher(r1)
            boolean r2 = r2.matches()
            if (r2 != 0) goto L_0x03e2
            goto L_0x04ef
        L_0x03e2:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = r1.length()
            r4 = r7
        L_0x03ec:
            if (r4 >= r3) goto L_0x0429
            int r5 = r4 + 17
            if (r5 <= r3) goto L_0x03f4
            r6 = r3
            goto L_0x03f5
        L_0x03f4:
            r6 = r5
        L_0x03f5:
            java.lang.String r4 = r1.substring(r4, r6)
            long r10 = java.lang.Long.parseLong(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r10)
            java.lang.String r6 = "%x"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r4 = java.lang.String.format(r6, r4)
            int r6 = r4.length()
            int r6 = r6 % r12
            if (r6 != r15) goto L_0x0418
            java.lang.String r6 = "0"
            java.lang.String r4 = r6.concat(r4)
        L_0x0418:
            int r6 = r4.length()
            int r6 = r6 - r12
        L_0x041d:
            if (r6 <= r9) goto L_0x0427
            int r8 = r6 + 2
            r2.append(r4, r6, r8)
            int r6 = r6 + -2
            goto L_0x041d
        L_0x0427:
            r4 = r5
            goto L_0x03ec
        L_0x0429:
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.toUpperCase()
            int r2 = r1.length()
            int r2 = r2 / r12
            char[] r1 = r1.toCharArray()
            byte[] r3 = new byte[r2]
            r4 = r7
        L_0x043d:
            if (r4 >= r2) goto L_0x045a
            int r5 = r4 * 2
            char r6 = r1[r5]
            java.lang.String r8 = "0123456789ABCDEF"
            int r6 = r8.indexOf(r6)
            byte r6 = (byte) r6
            int r6 = r6 << r14
            int r5 = r5 + r15
            char r5 = r1[r5]
            int r5 = r8.indexOf(r5)
            byte r5 = (byte) r5
            r5 = r5 | r6
            byte r5 = (byte) r5
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x043d
        L_0x045a:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ a -> 0x04bd }
            r2.<init>(r3)     // Catch:{ a -> 0x04bd }
            Df.n r3 = new Df.n     // Catch:{ a -> 0x04bd }
            r3.<init>(r2)     // Catch:{ a -> 0x04bd }
            java.util.LinkedList r2 = new java.util.LinkedList     // Catch:{ a -> 0x04bd }
            r2.<init>()     // Catch:{ a -> 0x04bd }
        L_0x046e:
            w0.e r4 = r3.c()     // Catch:{ a -> 0x04bd }
            if (r4 != 0) goto L_0x04b9
            java.lang.Object r2 = r2.get(r7)     // Catch:{ a -> 0x04bd }
            w0.e r2 = (w0.C0304e) r2     // Catch:{ a -> 0x04bd }
            w0.i r3 = r2.f1984a     // Catch:{ a -> 0x04bd }
            w0.i r4 = w0.C0308i.MAP     // Catch:{ a -> 0x04bd }
            if (r3 != r4) goto L_0x04c4
            w0.j r2 = (w0.C0309j) r2     // Catch:{ a -> 0x04bd }
            java.util.LinkedList r3 = r2.e     // Catch:{ a -> 0x04bd }
        L_0x0484:
            int r4 = r3.size()     // Catch:{ a -> 0x04bd }
            if (r7 >= r4) goto L_0x04c4
            java.lang.Object r4 = r3.get(r7)     // Catch:{ a -> 0x04bd }
            w0.e r4 = (w0.C0304e) r4     // Catch:{ a -> 0x04bd }
            java.util.LinkedHashMap r5 = r2.d     // Catch:{ a -> 0x04bd }
            java.lang.Object r4 = r5.get(r4)     // Catch:{ a -> 0x04bd }
            w0.e r4 = (w0.C0304e) r4     // Catch:{ a -> 0x04bd }
            w0.i r5 = r4.f1984a     // Catch:{ a -> 0x04bd }
            w0.i r6 = w0.C0308i.BYTE_STRING     // Catch:{ a -> 0x04bd }
            if (r5 != r6) goto L_0x04ab
            w0.c r4 = (w0.C0302c) r4     // Catch:{ a -> 0x04bd }
            byte[] r4 = r4.d     // Catch:{ a -> 0x04bd }
            if (r4 != 0) goto L_0x04a6
            r4 = r16
        L_0x04a6:
            java.lang.String r4 = i(r4)     // Catch:{ a -> 0x04bd }
            goto L_0x04af
        L_0x04ab:
            java.lang.String r4 = r4.toString()     // Catch:{ a -> 0x04bd }
        L_0x04af:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)     // Catch:{ a -> 0x04bd }
            r1.put(r5, r4)     // Catch:{ a -> 0x04bd }
            int r7 = r7 + 1
            goto L_0x0484
        L_0x04b9:
            r2.add(r4)     // Catch:{ a -> 0x04bd }
            goto L_0x046e
        L_0x04bd:
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "CborException"
            r2.println(r3)
        L_0x04c4:
            int r2 = r1.size()
            if (r2 != 0) goto L_0x04cb
            goto L_0x04ef
        L_0x04cb:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)
            java.lang.String r3 = ""
            java.lang.Object r2 = r1.getOrDefault(r2, r3)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "ga"
            boolean r3 = java.util.Objects.equals(r2, r3)
            if (r3 != 0) goto L_0x04e8
            java.lang.String r3 = "mc"
            boolean r2 = java.util.Objects.equals(r2, r3)
            if (r2 != 0) goto L_0x04e8
            goto L_0x04ef
        L_0x04e8:
            X2.p r2 = new X2.p
            r2.<init>((java.lang.String) r0, (java.util.HashMap) r1)
            r16 = r2
        L_0x04ef:
            return r16
        L_0x04f0:
            java.lang.Object r0 = r1.f
            W2.a r0 = (W2.a) r0
            W2.a r2 = W2.a.EAN_13
            if (r0 == r2) goto L_0x04f9
            goto L_0x051e
        L_0x04f9:
            java.lang.String r0 = X2.u.a(r1)
            int r1 = r0.length()
            r2 = 13
            if (r1 == r2) goto L_0x0506
            goto L_0x051e
        L_0x0506:
            java.lang.String r1 = "978"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x0517
            java.lang.String r1 = "979"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x0517
            goto L_0x051e
        L_0x0517:
            X2.p r1 = new X2.p
            r1.<init>((java.lang.String) r0, (int) r7)
            r16 = r1
        L_0x051e:
            return r16
        L_0x051f:
            java.lang.Object r0 = r1.f
            W2.a r0 = (W2.a) r0
            W2.a r4 = W2.a.RSS_EXPANDED
            if (r0 == r4) goto L_0x0529
            goto L_0x07c3
        L_0x0529:
            java.lang.String r0 = X2.u.a(r1)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r4 = r7
            r19 = r16
            r20 = r19
            r21 = r20
            r22 = r21
            r23 = r22
            r24 = r23
            r25 = r24
            r26 = r25
            r27 = r26
            r28 = r27
            r29 = r28
            r30 = r29
        L_0x054b:
            int r5 = r0.length()
            if (r4 >= r5) goto L_0x07b8
            java.lang.String r5 = j(r4, r0)
            if (r5 != 0) goto L_0x0559
            goto L_0x07c3
        L_0x0559:
            int r6 = r5.length()
            int r6 = r6 + r12
            int r6 = r6 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r2 = r0.substring(r6)
            r3 = r7
        L_0x0569:
            int r9 = r2.length()
            if (r3 >= r9) goto L_0x0589
            char r9 = r2.charAt(r3)
            r10 = 40
            if (r9 != r10) goto L_0x0582
            java.lang.String r9 = j(r3, r2)
            if (r9 == 0) goto L_0x057e
            goto L_0x0589
        L_0x057e:
            r4.append(r10)
            goto L_0x0585
        L_0x0582:
            r4.append(r9)
        L_0x0585:
            int r3 = r3 + 1
            r10 = 7
            goto L_0x0569
        L_0x0589:
            java.lang.String r2 = r4.toString()
            int r3 = r2.length()
            int r4 = r3 + r6
            int r3 = r5.hashCode()
            switch(r3) {
                case 1536: goto L_0x0762;
                case 1537: goto L_0x0756;
                case 1567: goto L_0x074a;
                case 1568: goto L_0x073e;
                case 1570: goto L_0x0732;
                case 1572: goto L_0x0726;
                case 1574: goto L_0x071a;
                case 1567966: goto L_0x070e;
                case 1567967: goto L_0x0700;
                case 1567968: goto L_0x06f2;
                case 1567969: goto L_0x06e4;
                case 1567970: goto L_0x06d6;
                case 1567971: goto L_0x06c8;
                case 1567972: goto L_0x06ba;
                case 1567973: goto L_0x06ac;
                case 1567974: goto L_0x069e;
                case 1567975: goto L_0x0690;
                case 1568927: goto L_0x0682;
                case 1568928: goto L_0x0674;
                case 1568929: goto L_0x0666;
                case 1568930: goto L_0x0658;
                case 1568931: goto L_0x064a;
                case 1568932: goto L_0x063c;
                case 1568933: goto L_0x062e;
                case 1568934: goto L_0x0620;
                case 1568935: goto L_0x0612;
                case 1568936: goto L_0x0605;
                case 1575716: goto L_0x05f8;
                case 1575717: goto L_0x05eb;
                case 1575718: goto L_0x05de;
                case 1575719: goto L_0x05d1;
                case 1575747: goto L_0x05c4;
                case 1575748: goto L_0x05b7;
                case 1575749: goto L_0x05aa;
                case 1575750: goto L_0x059d;
                default: goto L_0x059a;
            }
        L_0x059a:
            r3 = -1
            goto L_0x076d
        L_0x059d:
            java.lang.String r3 = "3933"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05a6
            goto L_0x059a
        L_0x05a6:
            r3 = 34
            goto L_0x076d
        L_0x05aa:
            java.lang.String r3 = "3932"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05b3
            goto L_0x059a
        L_0x05b3:
            r3 = 33
            goto L_0x076d
        L_0x05b7:
            java.lang.String r3 = "3931"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05c0
            goto L_0x059a
        L_0x05c0:
            r3 = 32
            goto L_0x076d
        L_0x05c4:
            java.lang.String r3 = "3930"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05cd
            goto L_0x059a
        L_0x05cd:
            r3 = 31
            goto L_0x076d
        L_0x05d1:
            java.lang.String r3 = "3923"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05da
            goto L_0x059a
        L_0x05da:
            r3 = 30
            goto L_0x076d
        L_0x05de:
            java.lang.String r3 = "3922"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05e7
            goto L_0x059a
        L_0x05e7:
            r3 = 29
            goto L_0x076d
        L_0x05eb:
            java.lang.String r3 = "3921"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x05f4
            goto L_0x059a
        L_0x05f4:
            r3 = 28
            goto L_0x076d
        L_0x05f8:
            java.lang.String r3 = "3920"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0601
            goto L_0x059a
        L_0x0601:
            r3 = 27
            goto L_0x076d
        L_0x0605:
            java.lang.String r3 = "3209"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x060e
            goto L_0x059a
        L_0x060e:
            r3 = 26
            goto L_0x076d
        L_0x0612:
            java.lang.String r3 = "3208"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x061c
            goto L_0x059a
        L_0x061c:
            r3 = 25
            goto L_0x076d
        L_0x0620:
            java.lang.String r3 = "3207"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x062a
            goto L_0x059a
        L_0x062a:
            r3 = 24
            goto L_0x076d
        L_0x062e:
            java.lang.String r3 = "3206"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0638
            goto L_0x059a
        L_0x0638:
            r3 = 23
            goto L_0x076d
        L_0x063c:
            java.lang.String r3 = "3205"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0646
            goto L_0x059a
        L_0x0646:
            r3 = 22
            goto L_0x076d
        L_0x064a:
            java.lang.String r3 = "3204"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0654
            goto L_0x059a
        L_0x0654:
            r3 = 21
            goto L_0x076d
        L_0x0658:
            java.lang.String r3 = "3203"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0662
            goto L_0x059a
        L_0x0662:
            r3 = 20
            goto L_0x076d
        L_0x0666:
            java.lang.String r3 = "3202"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0670
            goto L_0x059a
        L_0x0670:
            r3 = 19
            goto L_0x076d
        L_0x0674:
            java.lang.String r3 = "3201"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x067e
            goto L_0x059a
        L_0x067e:
            r3 = 18
            goto L_0x076d
        L_0x0682:
            java.lang.String r3 = "3200"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x068c
            goto L_0x059a
        L_0x068c:
            r3 = 17
            goto L_0x076d
        L_0x0690:
            java.lang.String r3 = "3109"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x069a
            goto L_0x059a
        L_0x069a:
            r3 = 16
            goto L_0x076d
        L_0x069e:
            java.lang.String r3 = "3108"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06a8
            goto L_0x059a
        L_0x06a8:
            r3 = 15
            goto L_0x076d
        L_0x06ac:
            java.lang.String r3 = "3107"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06b6
            goto L_0x059a
        L_0x06b6:
            r3 = 14
            goto L_0x076d
        L_0x06ba:
            java.lang.String r3 = "3106"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06c4
            goto L_0x059a
        L_0x06c4:
            r3 = 13
            goto L_0x076d
        L_0x06c8:
            java.lang.String r3 = "3105"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06d2
            goto L_0x059a
        L_0x06d2:
            r3 = 12
            goto L_0x076d
        L_0x06d6:
            java.lang.String r3 = "3104"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06e0
            goto L_0x059a
        L_0x06e0:
            r3 = 11
            goto L_0x076d
        L_0x06e4:
            java.lang.String r3 = "3103"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06ee
            goto L_0x059a
        L_0x06ee:
            r3 = 10
            goto L_0x076d
        L_0x06f2:
            java.lang.String r3 = "3102"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x06fc
            goto L_0x059a
        L_0x06fc:
            r3 = 9
            goto L_0x076d
        L_0x0700:
            java.lang.String r3 = "3101"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x070a
            goto L_0x059a
        L_0x070a:
            r3 = 8
            goto L_0x076d
        L_0x070e:
            java.lang.String r3 = "3100"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0718
            goto L_0x059a
        L_0x0718:
            r3 = 7
            goto L_0x076d
        L_0x071a:
            java.lang.String r3 = "17"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0724
            goto L_0x059a
        L_0x0724:
            r3 = r11
            goto L_0x076d
        L_0x0726:
            java.lang.String r3 = "15"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0730
            goto L_0x059a
        L_0x0730:
            r3 = r13
            goto L_0x076d
        L_0x0732:
            java.lang.String r3 = "13"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x073c
            goto L_0x059a
        L_0x073c:
            r3 = r14
            goto L_0x076d
        L_0x073e:
            java.lang.String r3 = "11"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0748
            goto L_0x059a
        L_0x0748:
            r3 = r8
            goto L_0x076d
        L_0x074a:
            java.lang.String r3 = "10"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0754
            goto L_0x059a
        L_0x0754:
            r3 = r12
            goto L_0x076d
        L_0x0756:
            java.lang.String r3 = "01"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x0760
            goto L_0x059a
        L_0x0760:
            r3 = r15
            goto L_0x076d
        L_0x0762:
            java.lang.String r3 = "00"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x076c
            goto L_0x059a
        L_0x076c:
            r3 = r7
        L_0x076d:
            switch(r3) {
                case 0: goto L_0x07ae;
                case 1: goto L_0x07ab;
                case 2: goto L_0x07a8;
                case 3: goto L_0x07a5;
                case 4: goto L_0x07b0;
                case 5: goto L_0x07a2;
                case 6: goto L_0x079f;
                case 7: goto L_0x0798;
                case 8: goto L_0x0798;
                case 9: goto L_0x0798;
                case 10: goto L_0x0798;
                case 11: goto L_0x0798;
                case 12: goto L_0x0798;
                case 13: goto L_0x0798;
                case 14: goto L_0x0798;
                case 15: goto L_0x0798;
                case 16: goto L_0x0798;
                case 17: goto L_0x078f;
                case 18: goto L_0x078f;
                case 19: goto L_0x078f;
                case 20: goto L_0x078f;
                case 21: goto L_0x078f;
                case 22: goto L_0x078f;
                case 23: goto L_0x078f;
                case 24: goto L_0x078f;
                case 25: goto L_0x078f;
                case 26: goto L_0x078f;
                case 27: goto L_0x0788;
                case 28: goto L_0x0788;
                case 29: goto L_0x0788;
                case 30: goto L_0x0788;
                case 31: goto L_0x0774;
                case 32: goto L_0x0774;
                case 33: goto L_0x0774;
                case 34: goto L_0x0774;
                default: goto L_0x0770;
            }
        L_0x0770:
            r1.put(r5, r2)
            goto L_0x07b0
        L_0x0774:
            int r3 = r2.length()
            if (r3 >= r14) goto L_0x077b
            goto L_0x07c3
        L_0x077b:
            java.lang.String r28 = r2.substring(r8)
            java.lang.String r30 = r2.substring(r7, r8)
            java.lang.String r29 = r5.substring(r8)
            goto L_0x07b0
        L_0x0788:
            java.lang.String r29 = r5.substring(r8)
            r28 = r2
            goto L_0x07b0
        L_0x078f:
            java.lang.String r27 = r5.substring(r8)
            java.lang.String r26 = "LB"
        L_0x0795:
            r25 = r2
            goto L_0x07b0
        L_0x0798:
            java.lang.String r27 = r5.substring(r8)
            java.lang.String r26 = "KG"
            goto L_0x0795
        L_0x079f:
            r24 = r2
            goto L_0x07b0
        L_0x07a2:
            r23 = r2
            goto L_0x07b0
        L_0x07a5:
            r22 = r2
            goto L_0x07b0
        L_0x07a8:
            r21 = r2
            goto L_0x07b0
        L_0x07ab:
            r19 = r2
            goto L_0x07b0
        L_0x07ae:
            r20 = r2
        L_0x07b0:
            r2 = 12
            r3 = 8
            r9 = -1
            r10 = 7
            goto L_0x054b
        L_0x07b8:
            X2.m r17 = new X2.m
            r18 = r0
            r31 = r1
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)
            r16 = r17
        L_0x07c3:
            return r16
        L_0x07c4:
            java.lang.String r0 = X2.u.a(r1)
            java.lang.String r1 = "MEMORY"
            boolean r1 = r0.contains(r1)
            if (r1 == 0) goto L_0x0854
            java.lang.String r1 = "\r\n"
            boolean r1 = r0.contains(r1)
            if (r1 != 0) goto L_0x07da
            goto L_0x0854
        L_0x07da:
            java.lang.String r1 = "NAME1:"
            r2 = 13
            java.lang.String[] r1 = X2.u.b(r1, r0, r2, r15)
            if (r1 != 0) goto L_0x07e7
            r1 = r16
            goto L_0x07e9
        L_0x07e7:
            r1 = r1[r7]
        L_0x07e9:
            java.lang.String r3 = "NAME2:"
            java.lang.String[] r3 = X2.u.b(r3, r0, r2, r15)
            if (r3 != 0) goto L_0x07f4
            r22 = r16
            goto L_0x07f8
        L_0x07f4:
            r3 = r3[r7]
            r22 = r3
        L_0x07f8:
            java.lang.String r3 = "TEL"
            java.lang.String[] r23 = k(r3, r0)
            java.lang.String r3 = "MAIL"
            java.lang.String[] r25 = k(r3, r0)
            java.lang.String r3 = "MEMORY:"
            java.lang.String[] r3 = X2.u.b(r3, r0, r2, r7)
            if (r3 != 0) goto L_0x080f
            r28 = r16
            goto L_0x0813
        L_0x080f:
            r3 = r3[r7]
            r28 = r3
        L_0x0813:
            java.lang.String r3 = "ADD:"
            java.lang.String[] r0 = X2.u.b(r3, r0, r2, r15)
            if (r0 != 0) goto L_0x081e
            r0 = r16
            goto L_0x0820
        L_0x081e:
            r0 = r0[r7]
        L_0x0820:
            if (r0 != 0) goto L_0x0825
            r29 = r16
            goto L_0x082b
        L_0x0825:
            java.lang.String[] r2 = new java.lang.String[r15]
            r2[r7] = r0
            r29 = r2
        L_0x082b:
            X2.d r17 = new X2.d
            java.lang.String[] r18 = X2.u.d(r1)
            r37 = 0
            r38 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r24 = 0
            r26 = 0
            r27 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
            r16 = r17
        L_0x0854:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.C0062b.e(D0.e):X2.r");
    }
}
