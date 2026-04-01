package X2;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends u {
    public static final Pattern f = Pattern.compile("BEGIN:VCARD", 2);
    public static final Pattern g = Pattern.compile("\\d{4}[-|.]?\\d{2}[-|.]?\\d{2}");

    /* renamed from: h  reason: collision with root package name */
    public static final Pattern f900h = Pattern.compile("-+\\d{1,2}-?\\d{1,2}");

    /* renamed from: i  reason: collision with root package name */
    public static final Pattern f901i = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z");

    /* renamed from: j  reason: collision with root package name */
    public static final Pattern f902j = Pattern.compile("\r\n[ \t]");
    public static final Pattern k = Pattern.compile("\\\\[nN]");
    public static final Pattern l = Pattern.compile("\\\\([,;\\\\])");
    public static final Pattern m = Pattern.compile("=");
    public static final Pattern n = Pattern.compile(";");

    /* renamed from: o  reason: collision with root package name */
    public static final Pattern f903o = Pattern.compile("(?<!\\\\);+");

    /* renamed from: p  reason: collision with root package name */
    public static final Pattern f904p = Pattern.compile("(?<!\\\\);+?");
    public static final Pattern q = Pattern.compile(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
    public static final Pattern r = Pattern.compile("[;,]");
    public static final Pattern s = Pattern.compile("(?<=X-CUSTOM\\()(.*?)(?=\\))");
    public static final Pattern t = Pattern.compile("(?<=X-)(.*?)(?=$)");

    static {
        Pattern.compile("(?<=\")(.*?)(?=\")");
    }

    public static String h(String str) {
        String[] split = q.split(str);
        String str2 = null;
        boolean z = false;
        for (String split2 : split) {
            String[] split3 = m.split(split2, 2);
            if (split3.length > 1) {
                String str3 = split3[0];
                String str4 = split3[1];
                if ("ENCODING".equalsIgnoreCase(str3) && "QUOTED-PRINTABLE".equalsIgnoreCase(str4)) {
                    z = true;
                } else if ("CHARSET".equalsIgnoreCase(str3)) {
                    str2 = str4;
                } else {
                    "VALUE".equalsIgnoreCase(str3);
                }
            }
        }
        if (!z || split[split.length - 1] == null) {
            return str;
        }
        return i(split[split.length - 1], str2);
    }

    public static String i(String str, String str2) {
        char charAt;
        int length = str.length();
        StringBuilder sb2 = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 < length) {
            char charAt2 = str.charAt(i2);
            if (!(charAt2 == 10 || charAt2 == 13)) {
                if (charAt2 != '=') {
                    o(byteArrayOutputStream, str2, sb2);
                    sb2.append(charAt2);
                } else if (!(i2 >= length - 2 || (charAt = str.charAt(i2 + 1)) == 13 || charAt == 10)) {
                    i2 += 2;
                    char charAt3 = str.charAt(i2);
                    int f5 = u.f(charAt);
                    int f8 = u.f(charAt3);
                    if (f5 >= 0 && f8 >= 0) {
                        byteArrayOutputStream.write((f5 << 4) + f8);
                    }
                }
            }
            i2++;
        }
        o(byteArrayOutputStream, str2, sb2);
        return sb2.toString();
    }

    public static boolean j(CharSequence charSequence) {
        if (charSequence == null || g.matcher(charSequence).matches() || f900h.matcher(charSequence).matches() || f901i.matcher(charSequence).matches()) {
            return true;
        }
        return false;
    }

    public static List k(String str, String str2, boolean z, boolean z3) {
        ArrayList m4 = m(str, str2, z, z3);
        if (m4 == null || m4.isEmpty()) {
            return null;
        }
        return (List) m4.get(0);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [X2.e, java.lang.Object] */
    public static ArrayList l(String str, String str2) {
        int i2;
        String str3;
        int i7;
        int indexOf;
        String str4;
        String str5;
        String str6;
        String str7 = str;
        String str8 = str2;
        int i8 = 1;
        ArrayList arrayList = new ArrayList(1);
        int length = str8.length();
        int i10 = 0;
        int i11 = 0;
        while (i11 < length) {
            int i12 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + str7 + "(?:;([^:]*))?:", 2).matcher(str8);
            if (i11 > 0) {
                i11--;
            }
            if (!matcher.find(i11)) {
                return arrayList;
            }
            int end = matcher.end(i10);
            String group = matcher.group(i8);
            ? obj = new Object();
            if ("X-SAMSUNGADR".equals(str7)) {
                Matcher matcher2 = Pattern.compile("(?:^|\n)ADR(?:;([^:]*))?:", 2).matcher(str8);
                if (matcher2.find(end)) {
                    group = matcher2.group(i8);
                }
            }
            if (group != null) {
                String[] split = n.split(group);
                if (!(split == null || split.length <= 0 || (str6 = split[i10]) == null)) {
                    int indexOf2 = str6.indexOf(61);
                    if (indexOf2 >= 0) {
                        if ("TYPE".equalsIgnoreCase(str6.substring(i10, indexOf2))) {
                            str6 = str6.substring(indexOf2 + 1);
                        } else {
                            str6 = null;
                        }
                    }
                    if (str6 != null) {
                        Matcher matcher3 = s.matcher(str6);
                        Matcher matcher4 = t.matcher(str6);
                        if (matcher3.find()) {
                            str6 = h(matcher3.group());
                        } else if (matcher4.find()) {
                            str6 = h(matcher4.group());
                        }
                    }
                    obj.f917a = str6;
                }
                int length2 = split.length;
                int i13 = i10;
                i7 = i13;
                str3 = null;
                while (true) {
                    i2 = i10;
                    if (i13 < length2) {
                        String[] split2 = m.split(split[i13], i12);
                        if (split2.length > i8) {
                            String str9 = split2[i2];
                            String str10 = split2[i8];
                            if ("ENCODING".equalsIgnoreCase(str9) && "QUOTED-PRINTABLE".equalsIgnoreCase(str10)) {
                                i7 = i8;
                            } else if ("CHARSET".equalsIgnoreCase(str9)) {
                                str3 = str10;
                            } else {
                                "VALUE".equalsIgnoreCase(str9);
                            }
                        }
                        i13++;
                        i10 = i2;
                        i12 = 2;
                    }
                }
            } else {
                i2 = i10;
                i7 = i2;
                str3 = null;
            }
            int i14 = end;
            while (true) {
                indexOf = str8.indexOf(10, i14);
                if (indexOf >= 0) {
                    if (indexOf < str8.length() - i8) {
                        int i15 = indexOf + 1;
                        if (str8.charAt(i15) == ' ' || str8.charAt(i15) == 9) {
                            i14 = indexOf + 2;
                        }
                    }
                    if (i7 != 0) {
                        if (indexOf < i8 || str8.charAt(indexOf - 1) != '=') {
                            if (indexOf >= 2) {
                                if (str8.charAt(indexOf - 2) != '=') {
                                }
                            }
                        }
                        i14 = indexOf + 1;
                    }
                }
            }
            if (indexOf < 0) {
                i11 = length;
            } else {
                if (indexOf > end) {
                    if (indexOf >= i8 && str8.charAt(indexOf - 1) == 13) {
                        indexOf--;
                    }
                    String trim = str8.substring(end, indexOf).trim();
                    if (i7 != 0) {
                        trim = i(trim, str3);
                    }
                    Matcher matcher5 = f904p.matcher(trim);
                    int i16 = i2;
                    int i17 = i16;
                    while (true) {
                        boolean find = matcher5.find();
                        Pattern pattern = l;
                        Pattern pattern2 = k;
                        Pattern pattern3 = f902j;
                        if (find) {
                            int start = matcher5.start();
                            if (start - i16 <= 0) {
                                str5 = null;
                            } else {
                                str5 = pattern.matcher(pattern2.matcher(pattern3.matcher(trim.substring(i16, start)).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                            }
                            switch (i17) {
                                case 0:
                                    obj.b = str5;
                                    break;
                                case 1:
                                    obj.f918c = str5;
                                    break;
                                case 2:
                                    obj.d = str5;
                                    break;
                                case 3:
                                    obj.e = str5;
                                    break;
                                case 4:
                                    obj.f = str5;
                                    break;
                                case 5:
                                    obj.g = str5;
                                    break;
                                case 6:
                                    obj.f919h = str5;
                                    break;
                            }
                            i16 = start + 1;
                            i17++;
                            String str11 = str;
                        } else {
                            if (trim.length() - i16 == 0) {
                                str4 = null;
                            } else {
                                str4 = pattern.matcher(pattern2.matcher(pattern3.matcher(trim.substring(i16, trim.length())).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                            }
                            if (str4 != null) {
                                switch (i17) {
                                    case 0:
                                        obj.b = str4;
                                        break;
                                    case 1:
                                        obj.f918c = str4;
                                        break;
                                    case 2:
                                        obj.d = str4;
                                        break;
                                    case 3:
                                        obj.e = str4;
                                        break;
                                    case 4:
                                        obj.f = str4;
                                        break;
                                    case 5:
                                        obj.g = str4;
                                        break;
                                    case 6:
                                        obj.f919h = str4;
                                        break;
                                }
                            }
                            if (!(obj.f917a == null && obj.b == null && obj.f918c == null && obj.d == null && obj.e == null && obj.f == null && obj.g == null && obj.f919h == null)) {
                                arrayList.add(obj);
                            }
                        }
                    }
                }
                i11 = indexOf + 1;
            }
            str7 = str;
            i10 = i2;
            i8 = 1;
        }
        return arrayList;
    }

    public static ArrayList m(String str, String str2, boolean z, boolean z3) {
        int i2;
        String str3;
        String str4;
        int i7;
        ArrayList arrayList;
        int indexOf;
        int i8;
        int i10;
        String str5;
        String str6 = str2;
        int length = str6.length();
        int i11 = 0;
        int i12 = 0;
        ArrayList arrayList2 = null;
        while (i12 < length) {
            int i13 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + str + "(?:;([^:]*))?:", 2).matcher(str6);
            if (i12 > 0) {
                i12--;
            }
            if (!matcher.find(i12)) {
                break;
            }
            int end = matcher.end(i11);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = n.split(group);
                int length2 = split.length;
                int i14 = i11;
                i7 = i14;
                arrayList = null;
                str4 = null;
                str3 = null;
                while (i14 < length2) {
                    String str7 = split[i14];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str7);
                    int i15 = i11;
                    String[] split2 = m.split(str7, i13);
                    if (split2.length > 1) {
                        String str8 = split2[i15];
                        String str9 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str8) && "QUOTED-PRINTABLE".equalsIgnoreCase(str9)) {
                            i7 = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str8)) {
                            str4 = str9;
                        } else if ("VALUE".equalsIgnoreCase(str8)) {
                            str3 = str9;
                        }
                    }
                    i14++;
                    i11 = i15;
                    i13 = 2;
                }
                i2 = i11;
            } else {
                i2 = i11;
                i7 = i2;
                arrayList = null;
                str4 = null;
                str3 = null;
            }
            int i16 = end;
            while (true) {
                indexOf = str6.indexOf(10, i16);
                if (indexOf < 0) {
                    break;
                }
                if (indexOf < str6.length() - 1) {
                    int i17 = indexOf + 1;
                    if (str6.charAt(i17) == ' ' || str6.charAt(i17) == 9) {
                        i16 = indexOf + 2;
                    }
                }
                if (i7 == 0) {
                    break;
                }
                if (indexOf < 1 || str6.charAt(indexOf - 1) != '=') {
                    if (indexOf >= 2) {
                        if (str6.charAt(indexOf - 2) != '=') {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i16 = indexOf + 1;
            }
            if (indexOf < 0) {
                i10 = length;
                i8 = i2;
            } else {
                if (indexOf > end) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    if (indexOf >= 1 && str6.charAt(indexOf - 1) == 13) {
                        indexOf--;
                    }
                    String substring = str6.substring(end, indexOf);
                    if (z) {
                        substring = substring.trim();
                    }
                    Pattern pattern = f903o;
                    if (i7 != 0) {
                        str5 = i(substring, str4);
                        if (z3) {
                            str5 = pattern.matcher(str5).replaceAll("\n").trim();
                        }
                    } else {
                        if (z3) {
                            substring = pattern.matcher(substring).replaceAll("\n").trim();
                        }
                        str5 = l.matcher(k.matcher(f902j.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                    }
                    if (OCRServiceConstant.KEY_PARAM_URI.equals(str3)) {
                        try {
                            str5 = URI.create(str5).getSchemeSpecificPart();
                        } catch (IllegalArgumentException unused) {
                        }
                    }
                    if (arrayList == null) {
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(str5);
                        arrayList2.add(arrayList3);
                    } else {
                        i8 = i2;
                        arrayList.add(i8, str5);
                        arrayList2.add(arrayList);
                        i10 = indexOf + 1;
                    }
                }
                i8 = i2;
                i10 = indexOf + 1;
            }
            i11 = i8;
            i12 = i10;
        }
        return arrayList2;
    }

    public static void n(StringBuilder sb2, String str) {
        if (str != null && !str.isEmpty()) {
            if (sb2.length() > 0) {
                sb2.append(' ');
            }
            sb2.append(str);
        }
    }

    public static void o(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb2) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb2.append(str2);
        }
    }

    public static String[] p(ArrayList arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            List list = (List) it.next();
            String str = (String) list.get(0);
            if (str != null && !str.isEmpty()) {
                int i2 = 1;
                String str2 = null;
                while (true) {
                    if (i2 >= list.size()) {
                        break;
                    }
                    String str3 = (String) list.get(i2);
                    int indexOf = str3.indexOf(61);
                    if (indexOf >= 0) {
                        if ("TYPE".equalsIgnoreCase(str3.substring(0, indexOf))) {
                            str3 = str3.substring(indexOf + 1);
                        } else {
                            str3 = null;
                        }
                    }
                    if (str3 != null) {
                        Matcher matcher = s.matcher(str3);
                        Matcher matcher2 = t.matcher(str3);
                        if (matcher.find()) {
                            str2 = h(matcher.group());
                            break;
                        } else if (matcher2.find()) {
                            str2 = h(matcher2.group());
                            break;
                        } else if (!str3.equals("PREF") && !str3.equals("pref")) {
                            if (str2 == null || str2.length() == 0) {
                                str2 = str3;
                            } else {
                                str2 = C0212a.B(str2, "_", str3);
                            }
                        }
                    }
                    i2++;
                }
                arrayList2.add(str2);
            }
        }
        return (String[]) arrayList2.toArray(u.e);
    }

    public static String q(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    public static String[] r(ArrayList arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) ((List) it.next()).get(0);
            if (str != null && !str.isEmpty()) {
                arrayList2.add(str);
            }
        }
        return (String[]) arrayList2.toArray(u.e);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v19, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v21, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v23, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v24, resolved type: X2.q} */
    /* JADX WARNING: type inference failed for: r8v17, types: [X2.l, java.lang.Object, X2.w] */
    /* JADX WARNING: type inference failed for: r1v37, types: [X2.l, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v5, types: [java.lang.Object, X2.f] */
    /* JADX WARNING: type inference failed for: r3v19, types: [X2.q, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v39, types: [X2.t, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x037b, code lost:
        if (r0.charAt(r10) == 9) goto L_0x0380;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x039c, code lost:
        if (r0.charAt(r5 - 1) != '=') goto L_0x039e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010f, code lost:
        if (r0.charAt(r2 - 1) != '=') goto L_0x0111;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X2.r e(D0.e r52) {
        /*
            r51 = this;
            java.lang.String r0 = X2.u.a(r52)
            java.util.regex.Pattern r1 = f
            java.util.regex.Matcher r1 = r1.matcher(r0)
            boolean r2 = r1.find()
            if (r2 == 0) goto L_0x0016
            int r1 = r1.start()
            if (r1 == 0) goto L_0x001a
        L_0x0016:
            r51 = 0
            goto L_0x08b9
        L_0x001a:
            int r1 = r0.length()
            r4 = 0
            r5 = 0
        L_0x0020:
            java.util.regex.Pattern r6 = f904p
            java.lang.String r11 = "VALUE"
            java.lang.String r12 = "CHARSET"
            java.lang.String r13 = "QUOTED-PRINTABLE"
            java.lang.String r14 = "ENCODING"
            java.util.regex.Pattern r15 = m
            r51 = 0
            java.util.regex.Pattern r3 = f902j
            java.lang.String r7 = ""
            java.util.regex.Pattern r8 = k
            java.lang.String r9 = "\n"
            java.util.regex.Pattern r10 = l
            java.lang.String r2 = "$1"
            r20 = r5
            java.util.regex.Pattern r5 = n
            r21 = r2
            if (r4 >= r1) goto L_0x0059
            java.lang.String r2 = "(?:^|\n)N(?:;([^:]*))?:"
            r24 = r1
            r1 = 2
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r1)
            java.util.regex.Matcher r1 = r2.matcher(r0)
            if (r4 <= 0) goto L_0x0053
            int r4 = r4 + -1
        L_0x0053:
            boolean r2 = r1.find(r4)
            if (r2 != 0) goto L_0x005d
        L_0x0059:
            r1 = r21
            goto L_0x023b
        L_0x005d:
            r2 = 0
            int r4 = r1.end(r2)
            r2 = 1
            java.lang.String r1 = r1.group(r2)
            X2.q r2 = new X2.q
            r2.<init>()
            if (r1 == 0) goto L_0x00d5
            java.lang.String[] r1 = r5.split(r1)
            int r5 = r1.length
            r27 = r51
            r28 = r27
            r25 = r1
            r1 = 0
            r26 = 0
        L_0x007c:
            if (r1 >= r5) goto L_0x00d0
            r29 = r1
            r1 = r25[r29]
            if (r27 != 0) goto L_0x008f
            r30 = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r31 = r2
            r2 = 1
            r5.<init>(r2)
            goto L_0x0096
        L_0x008f:
            r31 = r2
            r30 = r5
            r2 = 1
            r5 = r27
        L_0x0096:
            r5.add(r1)
            r2 = 2
            java.lang.String[] r1 = r15.split(r1, r2)
            int r2 = r1.length
            r27 = r1
            r1 = 1
            if (r2 <= r1) goto L_0x00c7
            r19 = 0
            r2 = r27[r19]
            r23 = r1
            r1 = r27[r23]
            boolean r27 = r14.equalsIgnoreCase(r2)
            if (r27 == 0) goto L_0x00bb
            boolean r27 = r13.equalsIgnoreCase(r1)
            if (r27 == 0) goto L_0x00bb
            r26 = 1
            goto L_0x00c7
        L_0x00bb:
            boolean r27 = r12.equalsIgnoreCase(r2)
            if (r27 == 0) goto L_0x00c4
            r28 = r1
            goto L_0x00c7
        L_0x00c4:
            r11.equalsIgnoreCase(r2)
        L_0x00c7:
            int r1 = r29 + 1
            r27 = r5
            r5 = r30
            r2 = r31
            goto L_0x007c
        L_0x00d0:
            r1 = r28
        L_0x00d2:
            r31 = r2
            goto L_0x00da
        L_0x00d5:
            r1 = r51
            r26 = 0
            goto L_0x00d2
        L_0x00da:
            r2 = r4
        L_0x00db:
            r5 = 10
            int r2 = r0.indexOf(r5, r2)
            if (r2 < 0) goto L_0x0123
            int r5 = r0.length()
            r23 = 1
            int r5 = r5 + -1
            if (r2 >= r5) goto L_0x0102
            int r5 = r2 + 1
            char r11 = r0.charAt(r5)
            r12 = 32
            if (r11 == r12) goto L_0x00ff
            char r5 = r0.charAt(r5)
            r11 = 9
            if (r5 != r11) goto L_0x0102
        L_0x00ff:
            int r2 = r2 + 2
            goto L_0x00db
        L_0x0102:
            if (r26 == 0) goto L_0x0123
            r5 = 1
            if (r2 < r5) goto L_0x0113
            int r5 = r2 + -1
            char r5 = r0.charAt(r5)
            r11 = 61
            if (r5 == r11) goto L_0x0120
        L_0x0111:
            r5 = 2
            goto L_0x0116
        L_0x0113:
            r11 = 61
            goto L_0x0111
        L_0x0116:
            if (r2 < r5) goto L_0x0123
            int r5 = r2 + -2
            char r5 = r0.charAt(r5)
            if (r5 != r11) goto L_0x0123
        L_0x0120:
            int r2 = r2 + 1
            goto L_0x00db
        L_0x0123:
            if (r2 >= 0) goto L_0x012b
            r5 = r20
            r4 = r24
            goto L_0x0237
        L_0x012b:
            if (r2 <= r4) goto L_0x0232
            if (r20 != 0) goto L_0x0136
            java.util.ArrayList r5 = new java.util.ArrayList
            r11 = 1
            r5.<init>(r11)
            goto L_0x0139
        L_0x0136:
            r11 = 1
            r5 = r20
        L_0x0139:
            if (r2 < r11) goto L_0x0147
            int r11 = r2 + -1
            char r11 = r0.charAt(r11)
            r12 = 13
            if (r11 != r12) goto L_0x0147
            int r2 = r2 + -1
        L_0x0147:
            java.lang.String r4 = r0.substring(r4, r2)
            java.lang.String r4 = r4.trim()
            if (r26 == 0) goto L_0x0155
            java.lang.String r4 = i(r4, r1)
        L_0x0155:
            java.util.regex.Matcher r1 = r6.matcher(r4)
            r6 = 0
            r11 = 0
        L_0x015b:
            boolean r12 = r1.find()
            r13 = 4
            if (r12 == 0) goto L_0x01be
            int r12 = r1.start()
            int r14 = r12 - r6
            if (r14 > 0) goto L_0x016f
            r6 = r51
            r14 = r21
            goto L_0x018d
        L_0x016f:
            java.lang.String r6 = r4.substring(r6, r12)
            java.util.regex.Matcher r6 = r3.matcher(r6)
            java.lang.String r6 = r6.replaceAll(r7)
            java.util.regex.Matcher r6 = r8.matcher(r6)
            java.lang.String r6 = r6.replaceAll(r9)
            java.util.regex.Matcher r6 = r10.matcher(r6)
            r14 = r21
            java.lang.String r6 = r6.replaceAll(r14)
        L_0x018d:
            if (r11 == 0) goto L_0x01b1
            r15 = 1
            if (r11 == r15) goto L_0x01ac
            r15 = 2
            if (r11 == r15) goto L_0x01a7
            r15 = 3
            if (r11 == r15) goto L_0x01a2
            if (r11 == r13) goto L_0x019d
            r15 = r31
            goto L_0x01b5
        L_0x019d:
            r15 = r31
            r15.e = r6
            goto L_0x01b5
        L_0x01a2:
            r15 = r31
            r15.d = r6
            goto L_0x01b5
        L_0x01a7:
            r15 = r31
            r15.f935c = r6
            goto L_0x01b5
        L_0x01ac:
            r15 = r31
            r15.b = r6
            goto L_0x01b5
        L_0x01b1:
            r15 = r31
            r15.f934a = r6
        L_0x01b5:
            int r6 = r12 + 1
            int r11 = r11 + 1
            r21 = r14
            r31 = r15
            goto L_0x015b
        L_0x01be:
            r14 = r21
            r15 = r31
            int r1 = r4.length()
            int r1 = r1 - r6
            if (r1 != 0) goto L_0x01cc
            r1 = r51
            goto L_0x01ec
        L_0x01cc:
            int r1 = r4.length()
            java.lang.String r1 = r4.substring(r6, r1)
            java.util.regex.Matcher r1 = r3.matcher(r1)
            java.lang.String r1 = r1.replaceAll(r7)
            java.util.regex.Matcher r1 = r8.matcher(r1)
            java.lang.String r1 = r1.replaceAll(r9)
            java.util.regex.Matcher r1 = r10.matcher(r1)
            java.lang.String r1 = r1.replaceAll(r14)
        L_0x01ec:
            if (r1 == 0) goto L_0x020a
            if (r11 == 0) goto L_0x0208
            r3 = 1
            if (r11 == r3) goto L_0x0205
            r3 = 2
            if (r11 == r3) goto L_0x0202
            r3 = 3
            if (r11 == r3) goto L_0x01ff
            if (r11 == r13) goto L_0x01fc
            goto L_0x020a
        L_0x01fc:
            r15.e = r1
            goto L_0x020a
        L_0x01ff:
            r15.d = r1
            goto L_0x020a
        L_0x0202:
            r15.f935c = r1
            goto L_0x020a
        L_0x0205:
            r15.b = r1
            goto L_0x020a
        L_0x0208:
            r15.f934a = r1
        L_0x020a:
            java.lang.String r1 = r15.f934a
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.b
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.f935c
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.d
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.e
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.f
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.g
            if (r1 != 0) goto L_0x022b
            java.lang.String r1 = r15.f936h
            if (r1 != 0) goto L_0x022b
            goto L_0x022e
        L_0x022b:
            r5.add(r15)
        L_0x022e:
            int r2 = r2 + 1
            r4 = r2
            goto L_0x0237
        L_0x0232:
            int r2 = r2 + 1
            r4 = r2
            r5 = r20
        L_0x0237:
            r1 = r24
            goto L_0x0020
        L_0x023b:
            java.lang.String r2 = "FN"
            r21 = r1
            r1 = 0
            r4 = 1
            java.util.ArrayList r2 = m(r2, r0, r4, r1)
            if (r20 == 0) goto L_0x02a7
            int r1 = r20.size()
            if (r1 <= 0) goto L_0x02a7
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r1.add(r4)
            java.util.Iterator r24 = r20.iterator()
        L_0x025e:
            boolean r25 = r24.hasNext()
            if (r25 == 0) goto L_0x02a0
            java.lang.Object r25 = r24.next()
            r26 = r1
            r1 = r25
            X2.q r1 = (X2.q) r1
            r25 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r27 = r10
            r10 = 100
            r2.<init>(r10)
            java.lang.String r10 = r1.d
            n(r2, r10)
            java.lang.String r10 = r1.b
            n(r2, r10)
            java.lang.String r10 = r1.f935c
            n(r2, r10)
            java.lang.String r10 = r1.f934a
            n(r2, r10)
            java.lang.String r1 = r1.e
            n(r2, r1)
            java.lang.String r1 = r2.toString()
            r4.add(r1)
            r2 = r25
            r1 = r26
            r10 = r27
            goto L_0x025e
        L_0x02a0:
            r26 = r1
            r25 = r2
            r27 = r10
            goto L_0x02ad
        L_0x02a7:
            r25 = r2
            r27 = r10
            r1 = r51
        L_0x02ad:
            int r2 = r0.length()
            r10 = r51
            r4 = 0
        L_0x02b4:
            r24 = r1
            if (r4 >= r2) goto L_0x02cf
            java.lang.String r1 = "(?:^|\n)SOUND(?:;([^:]*))?:"
            r26 = r2
            r2 = 2
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)
            java.util.regex.Matcher r1 = r1.matcher(r0)
            if (r4 <= 0) goto L_0x02c9
            int r4 = r4 + -1
        L_0x02c9:
            boolean r2 = r1.find(r4)
            if (r2 != 0) goto L_0x02d5
        L_0x02cf:
            r28 = r5
            r35 = r10
            goto L_0x04d1
        L_0x02d5:
            r2 = 0
            int r4 = r1.end(r2)
            r2 = 1
            java.lang.String r1 = r1.group(r2)
            X2.t r2 = new X2.t
            r2.<init>()
            if (r1 == 0) goto L_0x034d
            java.lang.String[] r1 = r5.split(r1)
            r28 = r5
            int r5 = r1.length
            r31 = r51
            r32 = r31
            r29 = r1
            r1 = 0
            r30 = 0
        L_0x02f6:
            if (r1 >= r5) goto L_0x0348
            r33 = r1
            r1 = r29[r33]
            r34 = r5
            if (r31 != 0) goto L_0x0309
            java.util.ArrayList r5 = new java.util.ArrayList
            r35 = r10
            r10 = 1
            r5.<init>(r10)
            goto L_0x030e
        L_0x0309:
            r35 = r10
            r10 = 1
            r5 = r31
        L_0x030e:
            r5.add(r1)
            r10 = 2
            java.lang.String[] r1 = r15.split(r1, r10)
            int r10 = r1.length
            r31 = r1
            r1 = 1
            if (r10 <= r1) goto L_0x033f
            r19 = 0
            r10 = r31[r19]
            r23 = r1
            r1 = r31[r23]
            boolean r31 = r14.equalsIgnoreCase(r10)
            if (r31 == 0) goto L_0x0333
            boolean r31 = r13.equalsIgnoreCase(r1)
            if (r31 == 0) goto L_0x0333
            r30 = 1
            goto L_0x033f
        L_0x0333:
            boolean r31 = r12.equalsIgnoreCase(r10)
            if (r31 == 0) goto L_0x033c
            r32 = r1
            goto L_0x033f
        L_0x033c:
            r11.equalsIgnoreCase(r10)
        L_0x033f:
            int r1 = r33 + 1
            r31 = r5
            r5 = r34
            r10 = r35
            goto L_0x02f6
        L_0x0348:
            r1 = r32
        L_0x034a:
            r35 = r10
            goto L_0x0354
        L_0x034d:
            r28 = r5
            r1 = r51
            r30 = 0
            goto L_0x034a
        L_0x0354:
            r5 = r4
        L_0x0355:
            r10 = 10
            int r5 = r0.indexOf(r10, r5)
            if (r5 < 0) goto L_0x03b0
            int r18 = r0.length()
            r23 = 1
            int r10 = r18 + -1
            if (r5 >= r10) goto L_0x0387
            int r10 = r5 + 1
            r18 = r11
            char r11 = r0.charAt(r10)
            r31 = r12
            r12 = 32
            if (r11 == r12) goto L_0x037e
            char r10 = r0.charAt(r10)
            r11 = 9
            if (r10 != r11) goto L_0x038f
            goto L_0x0380
        L_0x037e:
            r11 = 9
        L_0x0380:
            int r5 = r5 + 2
        L_0x0382:
            r11 = r18
            r12 = r31
            goto L_0x0355
        L_0x0387:
            r18 = r11
            r31 = r12
            r11 = 9
            r12 = 32
        L_0x038f:
            if (r30 == 0) goto L_0x03b6
            r10 = 1
            if (r5 < r10) goto L_0x03a0
            int r10 = r5 + -1
            char r10 = r0.charAt(r10)
            r11 = 61
            if (r10 == r11) goto L_0x03ad
        L_0x039e:
            r10 = 2
            goto L_0x03a3
        L_0x03a0:
            r11 = 61
            goto L_0x039e
        L_0x03a3:
            if (r5 < r10) goto L_0x03b6
            int r10 = r5 + -2
            char r10 = r0.charAt(r10)
            if (r10 != r11) goto L_0x03b6
        L_0x03ad:
            int r5 = r5 + 1
            goto L_0x0382
        L_0x03b0:
            r18 = r11
            r31 = r12
            r12 = 32
        L_0x03b6:
            if (r5 >= 0) goto L_0x03c4
            r5 = r21
            r4 = r26
            r1 = r27
            r10 = r35
            r21 = r6
            goto L_0x04bf
        L_0x03c4:
            if (r5 <= r4) goto L_0x04b3
            if (r35 != 0) goto L_0x03cf
            java.util.ArrayList r10 = new java.util.ArrayList
            r11 = 1
            r10.<init>(r11)
            goto L_0x03d2
        L_0x03cf:
            r11 = 1
            r10 = r35
        L_0x03d2:
            if (r5 < r11) goto L_0x03e1
            int r11 = r5 + -1
            char r11 = r0.charAt(r11)
            r12 = 13
            if (r11 != r12) goto L_0x03e3
            int r5 = r5 + -1
            goto L_0x03e3
        L_0x03e1:
            r12 = 13
        L_0x03e3:
            java.lang.String r4 = r0.substring(r4, r5)
            java.lang.String r4 = r4.trim()
            if (r30 == 0) goto L_0x03f1
            java.lang.String r4 = i(r4, r1)
        L_0x03f1:
            java.util.regex.Matcher r1 = r6.matcher(r4)
            r11 = 0
            r12 = 0
        L_0x03f7:
            boolean r30 = r1.find()
            if (r30 == 0) goto L_0x045a
            r30 = r1
            int r1 = r30.start()
            int r32 = r1 - r11
            if (r32 > 0) goto L_0x0412
            r11 = r51
            r32 = r1
            r1 = r27
            r27 = r5
            r5 = r21
            goto L_0x0436
        L_0x0412:
            java.lang.String r11 = r4.substring(r11, r1)
            java.util.regex.Matcher r11 = r3.matcher(r11)
            java.lang.String r11 = r11.replaceAll(r7)
            java.util.regex.Matcher r11 = r8.matcher(r11)
            java.lang.String r11 = r11.replaceAll(r9)
            r32 = r1
            r1 = r27
            java.util.regex.Matcher r11 = r1.matcher(r11)
            r27 = r5
            r5 = r21
            java.lang.String r11 = r11.replaceAll(r5)
        L_0x0436:
            if (r12 == 0) goto L_0x0447
            r21 = r6
            r6 = 1
            if (r12 == r6) goto L_0x0444
            r6 = 2
            if (r12 == r6) goto L_0x0441
            goto L_0x044b
        L_0x0441:
            r2.f939c = r11
            goto L_0x044b
        L_0x0444:
            r2.b = r11
            goto L_0x044b
        L_0x0447:
            r21 = r6
            r2.f938a = r11
        L_0x044b:
            int r11 = r32 + 1
            int r12 = r12 + 1
            r6 = r21
            r21 = r5
            r5 = r27
            r27 = r1
            r1 = r30
            goto L_0x03f7
        L_0x045a:
            r1 = r27
            r27 = r5
            r5 = r21
            r21 = r6
            int r6 = r4.length()
            int r6 = r6 - r11
            if (r6 != 0) goto L_0x046c
            r4 = r51
            goto L_0x048c
        L_0x046c:
            int r6 = r4.length()
            java.lang.String r4 = r4.substring(r11, r6)
            java.util.regex.Matcher r4 = r3.matcher(r4)
            java.lang.String r4 = r4.replaceAll(r7)
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r4 = r4.replaceAll(r9)
            java.util.regex.Matcher r4 = r1.matcher(r4)
            java.lang.String r4 = r4.replaceAll(r5)
        L_0x048c:
            if (r4 == 0) goto L_0x049f
            if (r12 == 0) goto L_0x049d
            r11 = 1
            if (r12 == r11) goto L_0x049a
            r6 = 2
            if (r12 == r6) goto L_0x0497
            goto L_0x049f
        L_0x0497:
            r2.f939c = r4
            goto L_0x049f
        L_0x049a:
            r2.b = r4
            goto L_0x049f
        L_0x049d:
            r2.f938a = r4
        L_0x049f:
            java.lang.String r4 = r2.f938a
            if (r4 != 0) goto L_0x04ac
            java.lang.String r4 = r2.b
            if (r4 != 0) goto L_0x04ac
            java.lang.String r4 = r2.f939c
            if (r4 != 0) goto L_0x04ac
            goto L_0x04af
        L_0x04ac:
            r10.add(r2)
        L_0x04af:
            int r2 = r27 + 1
            r4 = r2
            goto L_0x04bf
        L_0x04b3:
            r2 = r5
            r5 = r21
            r1 = r27
            r21 = r6
            int r2 = r2 + 1
            r4 = r2
            r10 = r35
        L_0x04bf:
            r27 = r1
            r11 = r18
            r6 = r21
            r1 = r24
            r2 = r26
            r12 = r31
            r21 = r5
            r5 = r28
            goto L_0x02b4
        L_0x04d1:
            if (r20 != 0) goto L_0x04d9
            if (r35 != 0) goto L_0x04d9
            r5 = r51
            goto L_0x054c
        L_0x04d9:
            if (r20 == 0) goto L_0x04e1
            if (r35 != 0) goto L_0x04e1
            r5 = r20
            goto L_0x054c
        L_0x04e1:
            if (r20 == 0) goto L_0x051a
            if (r35 == 0) goto L_0x051a
            int r1 = r20.size()
            int r2 = r35.size()
            if (r1 >= r2) goto L_0x04f4
            int r1 = r20.size()
            goto L_0x04f8
        L_0x04f4:
            int r1 = r35.size()
        L_0x04f8:
            r2 = 0
        L_0x04f9:
            if (r2 >= r1) goto L_0x051a
            r3 = r20
            java.lang.Object r4 = r3.get(r2)
            X2.q r4 = (X2.q) r4
            r10 = r35
            java.lang.Object r5 = r10.get(r2)
            X2.t r5 = (X2.t) r5
            java.lang.String r6 = r5.f938a
            r4.f = r6
            java.lang.String r6 = r5.b
            r4.g = r6
            java.lang.String r5 = r5.f939c
            r4.f936h = r5
            int r2 = r2 + 1
            goto L_0x04f9
        L_0x051a:
            r3 = r20
            r10 = r35
            if (r3 != 0) goto L_0x054b
            if (r10 == 0) goto L_0x054b
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            int r1 = r10.size()
            r2 = 0
        L_0x052c:
            if (r2 >= r1) goto L_0x054c
            X2.q r3 = new X2.q
            r3.<init>()
            java.lang.Object r4 = r10.get(r2)
            X2.t r4 = (X2.t) r4
            java.lang.String r6 = r4.f938a
            r3.f = r6
            java.lang.String r6 = r4.b
            r3.g = r6
            java.lang.String r4 = r4.f939c
            r3.f936h = r4
            r5.add(r3)
            int r2 = r2 + 1
            goto L_0x052c
        L_0x054b:
            r5 = r3
        L_0x054c:
            java.lang.String r1 = "SOUND"
            r2 = 1
            java.util.List r1 = k(r1, r0, r2, r2)
            java.lang.String r3 = "NICKNAME"
            r4 = 0
            java.util.List r3 = k(r3, r0, r2, r4)
            if (r3 != 0) goto L_0x055f
            r33 = r51
            goto L_0x056d
        L_0x055f:
            java.lang.Object r3 = r3.get(r4)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.util.regex.Pattern r6 = q
            java.lang.String[] r3 = r6.split(r3)
            r33 = r3
        L_0x056d:
            java.lang.String r3 = "TEL"
            java.util.ArrayList r3 = m(r3, r0, r2, r4)
            java.lang.String[] r2 = r(r3)
            if (r2 == 0) goto L_0x05a0
            r4 = 0
        L_0x057a:
            int r6 = r2.length
            if (r4 >= r6) goto L_0x05a0
            r6 = r2[r4]
            if (r6 == 0) goto L_0x059d
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x059d
            r6 = r2[r4]
            java.lang.String r7 = "[pP]"
            java.lang.String r8 = ","
            java.lang.String r6 = r6.replaceAll(r7, r8)
            r2[r4] = r6
            java.lang.String r7 = "[wW]"
            java.lang.String r8 = ";"
            java.lang.String r6 = r6.replaceAll(r7, r8)
            r2[r4] = r6
        L_0x059d:
            int r4 = r4 + 1
            goto L_0x057a
        L_0x05a0:
            java.lang.String r4 = "EMAIL"
            r6 = 0
            r11 = 1
            java.util.ArrayList r4 = m(r4, r0, r11, r6)
            java.lang.String r7 = "NOTE"
            java.util.List r7 = k(r7, r0, r6, r6)
            java.lang.String r8 = "ADR"
            java.util.ArrayList r9 = m(r8, r0, r11, r11)
            java.lang.String r10 = "ORG"
            java.util.List r10 = k(r10, r0, r11, r11)
            java.lang.String r12 = "BDAY"
            java.util.List r12 = k(r12, r0, r11, r6)
            java.lang.String r13 = "ANNIVERSARY"
            java.util.ArrayList r13 = m(r13, r0, r11, r6)
            java.lang.String r14 = "X-SAMSUNGADR"
            java.util.ArrayList r14 = l(r14, r0)
            int r15 = r14.size()
            if (r15 >= r11) goto L_0x05d6
            java.util.ArrayList r14 = l(r8, r0)
        L_0x05d6:
            java.lang.String r8 = "X-BDAY-SOLATYPE"
            java.util.List r8 = k(r8, r0, r11, r6)
            java.lang.String r15 = "X-BDAY-SOLADATE"
            java.util.List r15 = k(r15, r0, r11, r6)
            if (r12 == 0) goto L_0x05f2
            java.lang.Object r11 = r12.get(r6)
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            boolean r11 = j(r11)
            if (r11 != 0) goto L_0x05f2
            r12 = r51
        L_0x05f2:
            if (r12 == 0) goto L_0x0604
            X2.f r11 = new X2.f
            r11.<init>()
            java.lang.Object r16 = r12.get(r6)
            r6 = r16
            java.lang.String r6 = (java.lang.String) r6
            r11.f920c = r6
            goto L_0x0606
        L_0x0604:
            r11 = r51
        L_0x0606:
            if (r11 == 0) goto L_0x062f
            if (r8 == 0) goto L_0x062f
            r6 = 0
            java.lang.Object r16 = r8.get(r6)
            if (r16 == 0) goto L_0x062f
            if (r15 == 0) goto L_0x062f
            java.lang.Object r16 = r15.get(r6)
            java.lang.CharSequence r16 = (java.lang.CharSequence) r16
            boolean r16 = j(r16)
            if (r16 == 0) goto L_0x062f
            java.lang.Object r8 = r8.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            r11.f943a = r8
            java.lang.Object r8 = r15.get(r6)
            java.lang.String r8 = (java.lang.String) r8
            r11.b = r8
        L_0x062f:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            if (r13 == 0) goto L_0x0677
            java.util.Iterator r8 = r13.iterator()
        L_0x063a:
            boolean r13 = r8.hasNext()
            if (r13 == 0) goto L_0x0677
            java.lang.Object r13 = r8.next()
            java.util.List r13 = (java.util.List) r13
            if (r13 == 0) goto L_0x0672
            boolean r15 = r13.isEmpty()
            if (r15 != 0) goto L_0x0672
            r15 = 0
            java.lang.Object r16 = r13.get(r15)
            java.lang.CharSequence r16 = (java.lang.CharSequence) r16
            boolean r16 = j(r16)
            if (r16 == 0) goto L_0x0672
            r52 = r1
            X2.l r1 = new X2.l
            r1.<init>()
            java.lang.Object r13 = r13.get(r15)
            java.lang.String r13 = (java.lang.String) r13
            r1.f926c = r13
            X2.k r13 = X2.k.ANNIVERSARY
            r1.e = r13
            r6.add(r1)
            goto L_0x0674
        L_0x0672:
            r52 = r1
        L_0x0674:
            r1 = r52
            goto L_0x063a
        L_0x0677:
            r52 = r1
            java.lang.String r1 = "TITLE"
            r8 = 0
            r15 = 1
            java.util.List r1 = k(r1, r0, r15, r8)
            java.lang.String r13 = "URL"
            java.util.ArrayList r13 = m(r13, r0, r15, r8)
            r16 = r1
            java.lang.String r1 = "IMPP"
            java.util.List r1 = k(r1, r0, r15, r8)
            r17 = r1
            java.lang.String r1 = "GEO"
            java.util.List r1 = k(r1, r0, r15, r8)
            if (r1 != 0) goto L_0x069c
            r1 = r51
            goto L_0x06a8
        L_0x069c:
            java.lang.Object r1 = r1.get(r8)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.util.regex.Pattern r8 = r
            java.lang.String[] r1 = r8.split(r1)
        L_0x06a8:
            if (r1 == 0) goto L_0x06b1
            int r8 = r1.length
            r15 = 2
            if (r8 == r15) goto L_0x06b1
            r48 = r51
            goto L_0x06b3
        L_0x06b1:
            r48 = r1
        L_0x06b3:
            java.lang.String r1 = "X-ANDROID-CUSTOM"
            r8 = 0
            r15 = 1
            java.util.ArrayList r0 = m(r1, r0, r15, r8)
            if (r0 == 0) goto L_0x0795
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0795
            java.util.Iterator r0 = r0.iterator()
        L_0x06c7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0795
            java.lang.Object r1 = r0.next()
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x0786
            boolean r8 = r1.isEmpty()
            if (r8 != 0) goto L_0x0786
            r8 = 0
            java.lang.Object r15 = r1.get(r8)
            if (r15 == 0) goto L_0x0786
            java.lang.Object r1 = r1.get(r8)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r15 = r28
            java.lang.String[] r1 = r15.split(r1)
            if (r1 == 0) goto L_0x0783
            r19 = r8
            int r8 = r1.length
            r18 = r0
            r0 = 3
            if (r8 < r0) goto L_0x077c
            java.lang.String r0 = "vnd.android.cursor.item/contact_event"
            r8 = r1[r19]
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x077c
            r23 = 1
            r0 = r1[r23]
            boolean r0 = j(r0)
            if (r0 == 0) goto L_0x0777
            r22 = 2
            r0 = r1[r22]
            if (r0 == 0) goto L_0x0774
            X2.l r8 = new X2.l
            r8.<init>()
            r35 = r2
            r2 = r1[r23]
            r8.f926c = r2
            java.lang.String r2 = "1"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x072b
            X2.k r0 = X2.k.ANNIVERSARY
            r8.e = r0
            goto L_0x0749
        L_0x072b:
            java.lang.String r2 = "2"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0738
            X2.k r0 = X2.k.OTHER
            r8.e = r0
            goto L_0x0749
        L_0x0738:
            java.lang.String r2 = "0"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0745
            X2.k r0 = X2.k.CUSTOM
            r8.e = r0
            goto L_0x0749
        L_0x0745:
            X2.k r0 = X2.k.OTHER
            r8.e = r0
        L_0x0749:
            int r0 = r1.length
            r2 = 3
            if (r0 <= r2) goto L_0x075b
            r0 = r1[r2]
            if (r0 == 0) goto L_0x075b
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x075b
            r0 = r1[r2]
            r8.d = r0
        L_0x075b:
            int r0 = r1.length
            r2 = 16
            if (r0 != r2) goto L_0x0770
            r0 = 15
            r0 = r1[r0]
            if (r0 == 0) goto L_0x0770
            r2 = 14
            r1 = r1[r2]
            if (r1 == 0) goto L_0x0770
            r8.f943a = r0
            r8.b = r1
        L_0x0770:
            r6.add(r8)
            goto L_0x078d
        L_0x0774:
            r35 = r2
            goto L_0x078d
        L_0x0777:
            r35 = r2
            r22 = 2
            goto L_0x078d
        L_0x077c:
            r35 = r2
        L_0x077e:
            r22 = 2
            r23 = 1
            goto L_0x078d
        L_0x0783:
            r18 = r0
            goto L_0x077c
        L_0x0786:
            r18 = r0
            r35 = r2
            r15 = r28
            goto L_0x077e
        L_0x078d:
            r28 = r15
            r0 = r18
            r2 = r35
            goto L_0x06c7
        L_0x0795:
            r35 = r2
            r23 = 1
            X2.d r29 = new X2.d
            java.lang.String[] r30 = r(r25)
            if (r5 == 0) goto L_0x07b4
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x07a8
            goto L_0x07b4
        L_0x07a8:
            r8 = 0
            X2.q[] r0 = new X2.q[r8]
            java.lang.Object[] r0 = r5.toArray(r0)
            X2.q[] r0 = (X2.q[]) r0
            r31 = r0
            goto L_0x07b6
        L_0x07b4:
            r31 = r51
        L_0x07b6:
            java.lang.String[] r32 = r(r24)
            java.lang.String r34 = q(r52)
            java.lang.String[] r36 = p(r3)
            java.lang.String[] r37 = r(r4)
            java.lang.String[] r38 = p(r4)
            java.lang.String r39 = q(r17)
            java.lang.String r40 = q(r7)
            java.lang.String[] r41 = r(r9)
            if (r9 == 0) goto L_0x0877
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x07e0
            goto L_0x0877
        L_0x07e0:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r9.size()
            r0.<init>(r1)
            java.util.Iterator r1 = r9.iterator()
        L_0x07ed:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x086c
            java.lang.Object r2 = r1.next()
            java.util.List r2 = (java.util.List) r2
            r8 = 0
            java.lang.Object r3 = r2.get(r8)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L_0x0869
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0869
            r3 = r23
        L_0x080a:
            int r4 = r2.size()
            if (r3 >= r4) goto L_0x0836
            java.lang.Object r4 = r2.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            r5 = 61
            int r7 = r4.indexOf(r5)
            if (r7 >= 0) goto L_0x081f
            goto L_0x083a
        L_0x081f:
            java.lang.String r8 = "TYPE"
            r15 = 0
            java.lang.String r9 = r4.substring(r15, r7)
            boolean r8 = r8.equalsIgnoreCase(r9)
            if (r8 == 0) goto L_0x0833
            int r7 = r7 + 1
            java.lang.String r4 = r4.substring(r7)
            goto L_0x083a
        L_0x0833:
            int r3 = r3 + 1
            goto L_0x080a
        L_0x0836:
            r5 = 61
            r4 = r51
        L_0x083a:
            if (r4 == 0) goto L_0x0865
            java.util.regex.Pattern r2 = s
            java.util.regex.Matcher r2 = r2.matcher(r4)
            java.util.regex.Pattern r3 = t
            java.util.regex.Matcher r3 = r3.matcher(r4)
            boolean r7 = r2.find()
            if (r7 == 0) goto L_0x0857
            java.lang.String r2 = r2.group()
            java.lang.String r4 = h(r2)
            goto L_0x0865
        L_0x0857:
            boolean r2 = r3.find()
            if (r2 == 0) goto L_0x0865
            java.lang.String r2 = r3.group()
            java.lang.String r4 = h(r2)
        L_0x0865:
            r0.add(r4)
            goto L_0x07ed
        L_0x0869:
            r5 = 61
            goto L_0x07ed
        L_0x086c:
            java.lang.String[] r1 = X2.u.e
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r42 = r0
            goto L_0x0879
        L_0x0877:
            r42 = r51
        L_0x0879:
            java.lang.String r43 = q(r10)
            java.lang.String r44 = q(r12)
            java.lang.String r46 = q(r16)
            java.lang.String[] r47 = r(r13)
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L_0x0893
            r49 = r51
            r8 = 0
            goto L_0x089e
        L_0x0893:
            r8 = 0
            X2.e[] r0 = new X2.C0065e[r8]
            java.lang.Object[] r0 = r14.toArray(r0)
            X2.e[] r0 = (X2.C0065e[]) r0
            r49 = r0
        L_0x089e:
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x08a9
            r50 = r51
        L_0x08a6:
            r45 = r11
            goto L_0x08b5
        L_0x08a9:
            X2.l[] r0 = new X2.l[r8]
            java.lang.Object[] r0 = r6.toArray(r0)
            r3 = r0
            X2.l[] r3 = (X2.l[]) r3
            r50 = r3
            goto L_0x08a6
        L_0x08b5:
            r29.<init>(r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)
            return r29
        L_0x08b9:
            return r51
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.B.e(D0.e):X2.r");
    }
}
