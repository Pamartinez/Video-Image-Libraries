package X2;

import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends u {
    public static final Pattern f = Pattern.compile("[IOQ]");
    public static final Pattern g = Pattern.compile("[A-Z0-9]{17}");

    public static String h(String str) {
        char charAt = str.charAt(0);
        char charAt2 = str.charAt(1);
        if (charAt != '9') {
            if (charAt != 'S') {
                if (charAt != 'Z') {
                    switch (charAt) {
                        case '1':
                        case '4':
                        case '5':
                            return "US";
                        case '2':
                            return "CA";
                        case '3':
                            if (charAt2 < 'A' || charAt2 > 'W') {
                                return null;
                            }
                            return "MX";
                        default:
                            switch (charAt) {
                                case 'J':
                                    if (charAt2 < 'A' || charAt2 > 'T') {
                                        return null;
                                    }
                                    return "JP";
                                case 'K':
                                    if (charAt2 < 'L' || charAt2 > 'R') {
                                        return null;
                                    }
                                    return "KO";
                                case 'L':
                                    return "CN";
                                case 'M':
                                    if (charAt2 < 'A' || charAt2 > 'E') {
                                        return null;
                                    }
                                    return "IN";
                                default:
                                    switch (charAt) {
                                        case 'V':
                                            if (charAt2 >= 'F' && charAt2 <= 'R') {
                                                return "FR";
                                            }
                                            if (charAt2 < 'S' || charAt2 > 'W') {
                                                return null;
                                            }
                                            return "ES";
                                        case 'W':
                                            return "DE";
                                        case 'X':
                                            if (charAt2 == '0') {
                                                return "RU";
                                            }
                                            if (charAt2 < '3' || charAt2 > '9') {
                                                return null;
                                            }
                                            return "RU";
                                        default:
                                            return null;
                                    }
                            }
                    }
                } else if (charAt2 < 'A' || charAt2 > 'R') {
                    return null;
                } else {
                    return "IT";
                }
            } else if (charAt2 >= 'A' && charAt2 <= 'M') {
                return "UK";
            } else {
                if (charAt2 < 'N' || charAt2 > 'T') {
                    return null;
                }
                return "DE";
            }
        } else if (charAt2 >= 'A' && charAt2 <= 'E') {
            return "BR";
        } else {
            if (charAt2 < '3' || charAt2 > '9') {
                return null;
            }
            return "BR";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008f, code lost:
        throw new java.lang.IllegalArgumentException();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X2.r e(D0.e r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.Object r1 = r0.f
            W2.a r1 = (W2.a) r1
            W2.a r2 = W2.a.CODE_39
            if (r1 == r2) goto L_0x000c
            goto L_0x011d
        L_0x000c:
            java.lang.Object r0 = r0.e
            java.lang.String r0 = (java.lang.String) r0
            java.util.regex.Pattern r1 = f
            java.util.regex.Matcher r0 = r1.matcher(r0)
            java.lang.String r1 = ""
            java.lang.String r0 = r0.replaceAll(r1)
            java.lang.String r0 = r0.trim()
            java.util.regex.Pattern r1 = g
            java.util.regex.Matcher r1 = r1.matcher(r0)
            boolean r1 = r1.matches()
            if (r1 != 0) goto L_0x002e
            goto L_0x011d
        L_0x002e:
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0031:
            int r4 = r0.length()     // Catch:{ IllegalArgumentException -> 0x011d }
            r5 = 57
            r6 = 82
            r7 = 74
            r8 = 65
            r9 = 17
            r10 = 10
            r11 = 8
            r12 = 9
            r13 = 48
            if (r2 >= r4) goto L_0x0096
            int r4 = r2 + 1
            r14 = 1
            if (r4 < r14) goto L_0x0054
            r14 = 7
            if (r4 > r14) goto L_0x0054
            int r10 = 9 - r4
            goto L_0x0061
        L_0x0054:
            if (r4 != r11) goto L_0x0057
            goto L_0x0061
        L_0x0057:
            if (r4 != r12) goto L_0x005b
            r10 = r1
            goto L_0x0061
        L_0x005b:
            if (r4 < r10) goto L_0x0090
            if (r4 > r9) goto L_0x0090
            int r10 = 19 - r4
        L_0x0061:
            char r2 = r0.charAt(r2)     // Catch:{ IllegalArgumentException -> 0x011d }
            if (r2 < r8) goto L_0x006e
            r8 = 73
            if (r2 > r8) goto L_0x006e
            int r2 = r2 + -64
            goto L_0x0086
        L_0x006e:
            if (r2 < r7) goto L_0x0075
            if (r2 > r6) goto L_0x0075
            int r2 = r2 + -73
            goto L_0x0086
        L_0x0075:
            r6 = 83
            if (r2 < r6) goto L_0x0080
            r6 = 90
            if (r2 > r6) goto L_0x0080
            int r2 = r2 + -81
            goto L_0x0086
        L_0x0080:
            if (r2 < r13) goto L_0x008a
            if (r2 > r5) goto L_0x008a
            int r2 = r2 + -48
        L_0x0086:
            int r10 = r10 * r2
            int r3 = r3 + r10
            r2 = r4
            goto L_0x0031
        L_0x008a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x011d }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x011d }
            throw r0     // Catch:{ IllegalArgumentException -> 0x011d }
        L_0x0090:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x011d }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x011d }
            throw r0     // Catch:{ IllegalArgumentException -> 0x011d }
        L_0x0096:
            char r2 = r0.charAt(r11)     // Catch:{ IllegalArgumentException -> 0x011d }
            r4 = 11
            int r3 = r3 % r4
            if (r3 >= r10) goto L_0x00a2
            int r3 = r3 + r13
            char r3 = (char) r3     // Catch:{ IllegalArgumentException -> 0x011d }
            goto L_0x00a6
        L_0x00a2:
            if (r3 != r10) goto L_0x0117
            r3 = 88
        L_0x00a6:
            if (r2 != r3) goto L_0x011d
            r2 = 3
            java.lang.String r14 = r0.substring(r1, r2)     // Catch:{ IllegalArgumentException -> 0x011d }
            X2.C r13 = new X2.C     // Catch:{ IllegalArgumentException -> 0x011d }
            java.lang.String r15 = r0.substring(r2, r12)     // Catch:{ IllegalArgumentException -> 0x011d }
            java.lang.String r16 = r0.substring(r12, r9)     // Catch:{ IllegalArgumentException -> 0x011d }
            java.lang.String r17 = h(r14)     // Catch:{ IllegalArgumentException -> 0x011d }
            r0.substring(r2, r11)     // Catch:{ IllegalArgumentException -> 0x011d }
            char r1 = r0.charAt(r12)     // Catch:{ IllegalArgumentException -> 0x011d }
            r2 = 69
            if (r1 < r2) goto L_0x00cf
            r2 = 72
            if (r1 > r2) goto L_0x00cf
            int r1 = r1 + 1915
        L_0x00cc:
            r18 = r1
            goto L_0x0105
        L_0x00cf:
            if (r1 < r7) goto L_0x00d8
            r2 = 78
            if (r1 > r2) goto L_0x00d8
            int r1 = r1 + 1914
            goto L_0x00cc
        L_0x00d8:
            r2 = 80
            if (r1 != r2) goto L_0x00df
            r1 = 1993(0x7c9, float:2.793E-42)
            goto L_0x00cc
        L_0x00df:
            if (r1 < r6) goto L_0x00e8
            r2 = 84
            if (r1 > r2) goto L_0x00e8
            int r1 = r1 + 1912
            goto L_0x00cc
        L_0x00e8:
            r2 = 86
            if (r1 < r2) goto L_0x00f3
            r2 = 89
            if (r1 > r2) goto L_0x00f3
            int r1 = r1 + 1911
            goto L_0x00cc
        L_0x00f3:
            r2 = 49
            if (r1 < r2) goto L_0x00fc
            if (r1 > r5) goto L_0x00fc
            int r1 = r1 + 1952
            goto L_0x00cc
        L_0x00fc:
            if (r1 < r8) goto L_0x0111
            r2 = 68
            if (r1 > r2) goto L_0x0111
            int r1 = r1 + 1945
            goto L_0x00cc
        L_0x0105:
            char r19 = r0.charAt(r10)     // Catch:{ IllegalArgumentException -> 0x011d }
            java.lang.String r20 = r0.substring(r4)     // Catch:{ IllegalArgumentException -> 0x011d }
            r13.<init>(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ IllegalArgumentException -> 0x011d }
            return r13
        L_0x0111:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x011d }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x011d }
            throw r0     // Catch:{ IllegalArgumentException -> 0x011d }
        L_0x0117:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x011d }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x011d }
            throw r0     // Catch:{ IllegalArgumentException -> 0x011d }
        L_0x011d:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X2.D.e(D0.e):X2.r");
    }
}
