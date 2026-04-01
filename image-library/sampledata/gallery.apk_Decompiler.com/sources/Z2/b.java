package Z2;

import D0.e;
import X2.r;
import X2.u;
import java.util.HashMap;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends u {
    public static final String[] f = {"https://m.dana.id/", "https://qr.dana.id/"};
    public static final Pattern g = Pattern.compile("^[A-Za-z0-9]+$");

    public static boolean h(String str) {
        if (!Pattern.compile("[0-9]*").matcher(str).matches()) {
            return false;
        }
        return true;
    }

    public static String i(HashMap hashMap, String str, String str2, int i2) {
        if (str == null) {
            return null;
        }
        try {
            int length = str.length();
            int indexOf = str.indexOf(str2);
            if (indexOf >= 0) {
                int length2 = indexOf + str2.length();
                char charAt = str.charAt(length2);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(charAt);
                if (h(sb2.toString())) {
                    int i7 = length2 + 1;
                    char charAt2 = str.charAt(i7);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(charAt2);
                    if (h(sb3.toString())) {
                        char charAt3 = str.charAt(length2);
                        char charAt4 = str.charAt(i7);
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(charAt3);
                        sb4.append(charAt4);
                        int parseInt = Integer.parseInt(sb4.toString());
                        int i8 = length2 + 2;
                        if (i8 + parseInt > length) {
                            if (str2.equals("63")) {
                                parseInt = (length - 1) - i7;
                            }
                        }
                        hashMap.put(Integer.valueOf(Integer.parseInt(str2)), String.copyValueOf(str.toCharArray(), i8, parseInt));
                        int i10 = (i8 + parseInt) - (i2 * 2);
                        if (i10 < 0) {
                            i10 = 0;
                        }
                        return str.substring(i10);
                    }
                }
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static boolean j(HashMap hashMap) {
        if (hashMap.get(0) == null || hashMap.get(52) == null || hashMap.get(53) == null || hashMap.get(58) == null || hashMap.get(59) == null || hashMap.get(60) == null || hashMap.get(63) == null) {
            return false;
        }
        return true;
    }

    public final r e(e eVar) {
        int i2;
        e eVar2 = eVar;
        String str = (String) eVar2.e;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.isEmpty() || !str.startsWith("upi://")) {
            if (str.startsWith("0002") && str.contains("52") && str.contains("53") && str.contains("58") && str.contains("59") && str.contains("60") && str.contains("63")) {
                HashMap hashMap = new HashMap();
                int length = str.length();
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        break;
                    }
                    try {
                        char charAt = str.charAt(i7);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(charAt);
                        if (!h(sb2.toString())) {
                            break;
                        }
                        int i8 = i7 + 1;
                        char charAt2 = str.charAt(i8);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(charAt2);
                        if (h(sb3.toString())) {
                            char charAt3 = str.charAt(i7);
                            char charAt4 = str.charAt(i8);
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(charAt3);
                            sb4.append(charAt4);
                            int parseInt = Integer.parseInt(sb4.toString());
                            int i10 = i7 + 2;
                            char charAt5 = str.charAt(i10);
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(charAt5);
                            if (!h(sb5.toString())) {
                                break;
                            }
                            int i11 = i7 + 3;
                            char charAt6 = str.charAt(i11);
                            int i12 = i7;
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(charAt6);
                            if (!h(sb6.toString())) {
                                break;
                            }
                            char charAt7 = str.charAt(i10);
                            char charAt8 = str.charAt(i11);
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append(charAt7);
                            sb7.append(charAt8);
                            int parseInt2 = Integer.parseInt(sb7.toString());
                            int i13 = i12 + 4;
                            if (i13 + parseInt2 > length) {
                                if (parseInt != 63) {
                                    break;
                                }
                                parseInt2 = (length - 1) - i11;
                            }
                            String copyValueOf = String.copyValueOf(str.toCharArray(), i13, parseInt2);
                            i7 = parseInt2 + i13;
                            hashMap.put(Integer.valueOf(parseInt), copyValueOf);
                        } else {
                            break;
                        }
                    } catch (Exception unused) {
                    }
                }
                boolean j2 = j(hashMap);
                if (!j2) {
                    String replaceAll = str.replaceAll("[\\x0A\\x0D\\x20-\\x7E]", "");
                    if (replaceAll.length() <= 0 || (i2 = replaceAll.length()) >= str.length()) {
                        i2 = 0;
                    }
                    hashMap = new HashMap();
                    i(hashMap, i(hashMap, i(hashMap, i(hashMap, i(hashMap, i(hashMap, i(hashMap, i(hashMap, i(hashMap, str, "00", i2), "26", i2), "27", i2), "52", i2), "53", i2), "58", i2), "59", i2), "60", i2), "63", i2);
                    j2 = j(hashMap);
                }
                if (j2) {
                    if (str.contains("A000000524") && (hashMap.get(26) != null || hashMap.get(27) != null)) {
                        return new a(eVar2, d.EMVCo_BHARAT);
                    }
                    if (str.contains("5802ID")) {
                        return new a(eVar2, d.EMVCo_IND);
                    }
                    if (str.toLowerCase().contains("0014br.gov.bcb.pix")) {
                        return new a(eVar2, d.EMVCo_PIX);
                    }
                    return new a(eVar2, d.EMVCo_OTHER);
                }
            }
            if (str.startsWith("281005") && g.matcher(str).matches()) {
                return new a(eVar2, d.PAYTM);
            }
            for (int i14 = 0; i14 < 2; i14++) {
                if (str.contains(f[i14])) {
                    return new a(eVar2, d.DANA);
                }
            }
            return null;
        } else if ((str.contains("Paytmqr") || str.contains("Paytmqr".toLowerCase()) || str.contains("Paytmqr".toUpperCase())) && str.contains("281005")) {
            return new a(eVar2, d.UPI_PAYTM);
        } else {
            return new a(eVar2, d.UPI);
        }
    }
}
