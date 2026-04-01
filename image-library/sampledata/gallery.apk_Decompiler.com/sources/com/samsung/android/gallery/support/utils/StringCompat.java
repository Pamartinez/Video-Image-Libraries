package com.samsung.android.gallery.support.utils;

import A.a;
import A8.C0545b;
import E5.b;
import N2.j;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import c0.C0086a;
import com.adobe.internal.xmp.XMPConst;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StringCompat implements CharSequence {
    static final boolean DECIMAL_UNIT;
    static final long GB;
    static final long KB;
    static final long MB;
    static final long PB;
    static final long TB;
    static final long UNIT;
    private String tag;
    private final String value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CharArrayHolder {
        static final char[] value = "0123456789ABCDEF".toCharArray();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NonArabianNumberLocales {
        static final HashSet<String> locales = new HashSet<>(List.of(new String[]{"ar", "as", "bn", "ckb", "dz", "fa", "ig", "ks", "lrc", "mni", "mr", "my", "mzn", "ne", "pa", "ps", "sa", "th", "sat", "sd", "ur", "uz", "raj", "bgc", "bho"}));

        public static boolean contains(Locale locale) {
            return locales.contains(locale.getLanguage());
        }
    }

    static {
        long j2;
        boolean moreThan = SdkConfig.moreThan(SdkConfig.GED.T);
        DECIMAL_UNIT = moreThan;
        if (moreThan) {
            j2 = 1000;
        } else {
            j2 = ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER;
        }
        UNIT = j2;
        KB = j2;
        long j3 = j2 * j2;
        MB = j3;
        long j8 = j3 * j2;
        GB = j8;
        long j10 = j8 * j2;
        TB = j10;
        PB = j10 * j2;
    }

    public StringCompat(String str) {
        this.value = str;
    }

    public static String capitalize(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static boolean endsWithIgnoreCase(String str, String str2) {
        if (str == null || !str.toLowerCase().endsWith(str2)) {
            return false;
        }
        return true;
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null && charSequence2 == null) {
            return true;
        }
        if (charSequence == null || !charSequence.equals(charSequence2)) {
            return false;
        }
        return true;
    }

    public static boolean equalsList(List<String> list, List<String> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (String contains : list) {
            if (!list2.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public static String excludePrefix(String str, String str2) {
        if (str2 == null || str == null || !str.startsWith(str2)) {
            return str;
        }
        return str.substring(str2.length());
    }

    private static int findBreakIndex(String str, Paint paint, float f) {
        int length = str.length();
        int i2 = 0;
        int i7 = 0;
        while (i2 <= length) {
            int i8 = (i2 + length) / 2;
            if (paint.measureText(str.substring(0, i8)) <= f) {
                i2 = i8 + 1;
                i7 = i8;
            } else {
                length = i8 - 1;
            }
        }
        return i7;
    }

    public static String formatFileSize(long j2) {
        if (DECIMAL_UNIT) {
            return formatFileSize(Locale.getDefault(), j2, 0);
        }
        return toReadableSize((double) j2);
    }

    public static String getBeforeLastSegment(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        String[] segmentedString = getSegmentedString(charSequence, charSequence2);
        if (segmentedString == null || segmentedString.length < 2) {
            return charSequence3.toString();
        }
        String str = segmentedString[segmentedString.length - 2];
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return charSequence3.toString();
    }

    public static float getDrawWidth(String str, float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return paint.measureText(str);
    }

    public static String getLastSegment(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        String[] segmentedString = getSegmentedString(charSequence, charSequence2);
        if (segmentedString == null) {
            return charSequence3.toString();
        }
        String str = segmentedString[segmentedString.length - 1];
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return charSequence3.toString();
    }

    private static String[] getSegmentedString(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence)) {
            return null;
        }
        String[] split = charSequence.toString().split(charSequence2.toString());
        if (split.length == 0 || TextUtils.isEmpty(split[split.length - 1])) {
            return null;
        }
        return split;
    }

    public static boolean hasSpecialCharacter(CharSequence charSequence) {
        if (charSequence != null) {
            try {
                if (charSequence.length() != 0) {
                    String charSequence2 = charSequence.toString();
                    if (!charSequence2.equals(charSequence2.replaceAll("[\\\\/:*?\"<>|]", ""))) {
                        return true;
                    }
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("string check failed, e="), "StringCompat");
            }
        }
        return false;
    }

    public static boolean includeSpecialCharacter(String str) {
        if (str != null) {
            for (char isSpecialCharacter : str.toCharArray()) {
                if (isSpecialCharacter(isSpecialCharacter)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isArabic(String str) {
        if (str != null) {
            int i2 = 0;
            while (i2 < str.length()) {
                int codePointAt = str.codePointAt(i2);
                if (codePointAt >= 1536 && codePointAt <= 1791) {
                    return true;
                }
                i2 += Character.charCount(codePointAt);
            }
        }
        return false;
    }

    public static boolean isKorean(String str) {
        if (str != null) {
            int i2 = 0;
            while (i2 < str.length()) {
                int codePointAt = str.codePointAt(i2);
                if (codePointAt >= 44032 && codePointAt <= 55215) {
                    return true;
                }
                i2 += Character.charCount(codePointAt);
            }
        }
        return false;
    }

    public static boolean isSpecialCharacter(char c5) {
        if (23 == Character.getType(c5) || !Character.isUnicodeIdentifierPart(c5)) {
            return true;
        }
        return false;
    }

    public static boolean isSpecialLocale(Locale locale) {
        String language = locale.getLanguage();
        if ("ar".equals(language) || "ar-rIL".equals(language) || "fa".equals(language) || "ur".equals(language) || "ko".equals(language)) {
            return true;
        }
        return false;
    }

    public static boolean isStartWithDot(CharSequence charSequence, int i2) {
        if (i2 == 0 && charSequence.length() > 0 && charSequence.charAt(0) == '.') {
            return true;
        }
        return false;
    }

    public static <T> String joinText(CharSequence charSequence, Collection<T> collection) {
        return (collection == null || collection.size() <= 0) ? "" : joinText(charSequence, collection.iterator());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$wrapText$0(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append("#");
    }

    public static byte[] parseHex(String str) {
        int i2;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.length();
        }
        if (i2 <= 0) {
            return null;
        }
        byte[] bArr = new byte[(i2 / 2)];
        for (int i7 = 0; i7 < i2 - 1; i7 += 2) {
            bArr[i7 / 2] = (byte) (Character.digit(str.charAt(i7 + 1), 16) + (Character.digit(str.charAt(i7), 16) << 4));
        }
        return bArr;
    }

    public static boolean startsWith(String str, String str2) {
        if (str == null || !str.startsWith(str2)) {
            return false;
        }
        return true;
    }

    public static String subStringByDrawWidth(String str, float f, float f5, boolean z) {
        float drawWidth = getDrawWidth(str, f);
        if (drawWidth <= f5) {
            return str;
        }
        float f8 = f5 / drawWidth;
        if (z) {
            return str.substring(0, (int) (((float) str.length()) * f8));
        }
        int length = (int) ((((float) str.length()) * f8) - 3.0f);
        return str.substring(0, length) + "...";
    }

    public static String substring(String str, int i2) {
        if (str != null) {
            return str.substring(0, Math.min(i2, str.length()));
        }
        return null;
    }

    public static int[] toIntArray(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Stream.of(str.split(str2)).mapToInt(new C0545b(3)).toArray();
            } catch (Exception e) {
                a.s(e, j.k("toIntArray failed {", str, "} e="), "StringCompat");
            }
        }
        return new int[0];
    }

    public static long[] toLongArray(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Stream.of(str.split(str2)).mapToLong(new b(7)).toArray();
            } catch (Exception e) {
                a.s(e, j.k("toLongArray failed {", str, "} e="), "StringCompat");
            }
        }
        return new long[0];
    }

    public static String toReadableCount(int i2) {
        return toReadableCount(Locale.getDefault(), i2);
    }

    public static String toReadableNumber(long j2) {
        return toReadableNumber(Locale.getDefault(), j2);
    }

    public static String toReadablePercentage(int i2) {
        return toReadablePercentage(Locale.getDefault(), i2);
    }

    public static String toReadableProgress(int i2, int i7) {
        return toReadableProgress(Locale.getDefault(), i2, i7);
    }

    public static String toReadableSize(double d) {
        return toReadableSize(Locale.getDefault(), d);
    }

    public static String toReadableSizeInGB(double d) {
        return String.format(Locale.getDefault(), "%.2f%s", new Object[]{Double.valueOf(d / ((double) GB)), AppResources.getString(R$string.giga_byte)});
    }

    public static String toRtlDisplayName(String str) {
        if (isArabic(str)) {
            return C0212a.p(new StringBuilder(), BiDirectionUnicode.LEFT_TO_RIGHT_EMBEDDING, str);
        }
        return str;
    }

    public static String toRtlDisplayPath(String str) {
        StringBuilder sb2 = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(str, File.separator);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            sb2.insert(0, File.separator).insert(0, nextToken);
            if (isArabic(nextToken)) {
                sb2.insert(0, BiDirectionUnicode.LEFT_TO_RIGHT_EMBEDDING);
            }
        }
        sb2.insert(0, BiDirectionUnicode.LEFT_TO_RIGHT_EMBEDDING);
        return sb2.toString();
    }

    public static String valueOf(String str, String str2) {
        return str != null ? str : str2;
    }

    public static List<String> wrapText(String str, Typeface typeface, float f, float f5) {
        String str2;
        ArrayList arrayList = new ArrayList();
        Paint paint = new Paint();
        paint.setTextSize(f);
        paint.setTypeface(typeface);
        String[] split = str.split(" ");
        StringBuilder sb2 = new StringBuilder();
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str3 = split[i2];
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2.toString());
            if (sb2.length() > 0) {
                str2 = " ";
            } else {
                str2 = "";
            }
            sb3.append(str2);
            sb3.append(str3);
            if (paint.measureText(sb3.toString()) <= f5) {
                if (sb2.length() > 0) {
                    sb2.append(" ");
                }
                sb2.append(str3);
            } else {
                if (sb2.length() > 0) {
                    arrayList.add(sb2.toString());
                    sb2.setLength(0);
                }
                if (paint.measureText(str3) > f5) {
                    while (true) {
                        if (str3.length() > 0) {
                            int findBreakIndex = findBreakIndex(str3, paint, f5);
                            if (findBreakIndex > 0) {
                                arrayList.add(str3.substring(0, findBreakIndex));
                                str3 = str3.substring(findBreakIndex);
                            }
                            if (paint.measureText(str3) <= f5) {
                                sb2.append(str3);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    sb2.append(str3);
                }
            }
        }
        if (sb2.length() > 0) {
            arrayList.add(sb2.toString());
        }
        arrayList.forEach(new Q(new StringBuilder(), 0));
        return arrayList;
    }

    public char charAt(int i2) {
        return this.value.charAt(i2);
    }

    public String getTag() {
        return this.tag;
    }

    public int length() {
        return this.value.length();
    }

    public void setTag(Object obj) {
        this.tag = String.valueOf(obj);
    }

    public CharSequence subSequence(int i2, int i7) {
        return this.value.substring(i2, i7);
    }

    public String toString() {
        return this.value;
    }

    public static <T> String joinText(CharSequence charSequence, Iterator<T> it) {
        if (it == null || !it.hasNext()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(it.next());
        while (it.hasNext()) {
            sb2.append(charSequence);
            sb2.append(it.next());
        }
        return sb2.toString();
    }

    public static String toReadableCount(Locale locale, int i2) {
        String language = locale.getLanguage();
        if ("ar".equals(language) || "fa".equals(language)) {
            return toReadableNumber(locale, (long) i2);
        }
        return String.valueOf(i2);
    }

    public static String toReadableNumber(Locale locale, long j2) {
        return NonArabianNumberLocales.contains(locale) ? String.format(locale, "%d", new Object[]{Long.valueOf(j2)}) : String.valueOf(j2);
    }

    public static String toReadablePercentage(Locale locale, int i2) {
        String language = locale.getLanguage();
        if ("ar".equals(language) || "fa".equals(language)) {
            return toReadableNumber(locale, (long) i2) + "%";
        } else if ("tr".equals(language)) {
            return C0086a.i(i2, "%");
        } else {
            return i2 + "%";
        }
    }

    public static String toReadableProgress(Locale locale, int i2, int i7) {
        String language = locale.getLanguage();
        if ("ar".equals(language) || "fa".equals(language)) {
            return toReadableNumber(locale, (long) i2) + "/" + toReadableNumber(locale, (long) i7);
        }
        return i2 + "/" + i7;
    }

    public static String toReadableSize(Locale locale, double d) {
        if (isSpecialLocale(locale)) {
            long j2 = PB;
            if (d >= ((double) j2)) {
                return String.format(locale, "%.2f%s", new Object[]{Double.valueOf(d / ((double) j2)), AppResources.getString(R$string.peta_byte)});
            }
            long j3 = TB;
            if (d >= ((double) j3)) {
                return String.format(locale, "%.2f%s", new Object[]{Double.valueOf(d / ((double) j3)), AppResources.getString(R$string.tera_byte)});
            }
            long j8 = GB;
            if (d >= ((double) j8)) {
                return String.format(locale, "%.2f%s", new Object[]{Double.valueOf(d / ((double) j8)), AppResources.getString(R$string.giga_byte)});
            }
            long j10 = MB;
            if (d >= ((double) j10)) {
                return String.format(locale, "%.2f%s", new Object[]{Double.valueOf(d / ((double) j10)), AppResources.getString(R$string.mega_byte)});
            }
            long j11 = KB;
            if (d >= ((double) j11)) {
                return String.format(locale, "%.2f%s", new Object[]{Double.valueOf(d / ((double) j11)), AppResources.getString(R$string.kilo_byte)});
            }
            return String.format(locale, "%d%s", new Object[]{Integer.valueOf((int) d), AppResources.getString(R$string.bytes)});
        }
        long j12 = PB;
        if (d >= ((double) j12)) {
            return String.format(locale, "%.2f %s", new Object[]{Double.valueOf(d / ((double) j12)), AppResources.getString(R$string.peta_byte)});
        }
        long j13 = TB;
        if (d >= ((double) j13)) {
            return String.format(locale, "%.2f %s", new Object[]{Double.valueOf(d / ((double) j13)), AppResources.getString(R$string.tera_byte)});
        }
        long j14 = GB;
        if (d >= ((double) j14)) {
            return String.format(locale, "%.2f %s", new Object[]{Double.valueOf(d / ((double) j14)), AppResources.getString(R$string.giga_byte)});
        }
        long j15 = MB;
        if (d >= ((double) j15)) {
            return String.format(locale, "%.2f %s", new Object[]{Double.valueOf(d / ((double) j15)), AppResources.getString(R$string.mega_byte)});
        }
        long j16 = KB;
        if (d >= ((double) j16)) {
            return String.format(locale, "%.2f %s", new Object[]{Double.valueOf(d / ((double) j16)), AppResources.getString(R$string.kilo_byte)});
        }
        return String.format(locale, "%d %s", new Object[]{Integer.valueOf((int) d), AppResources.getString(R$string.bytes)});
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.function.LongFunction, java.lang.Object] */
    public static String toString(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        return (String) Arrays.stream(jArr).mapToObj(new Object()).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
    }

    public static <T> T valueOf(String str, T t) {
        if (str != null) {
            try {
                if (t instanceof Integer) {
                    return Integer.valueOf(Integer.parseInt(str));
                }
                if (t instanceof Float) {
                    return Float.valueOf(Float.parseFloat(str));
                }
                if (t instanceof Double) {
                    return Double.valueOf(Double.parseDouble(str));
                }
                if (t instanceof Long) {
                    return Long.valueOf(Long.parseLong(str));
                }
            } catch (Exception unused) {
            }
        }
        return t;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0079, code lost:
        if ((r13 & 1) != 0) goto L_0x007b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String formatFileSize(java.util.Locale r10, long r11, int r13) {
        /*
            int r0 = com.samsung.android.gallery.support.R$string.bytes
            r1 = 0
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            r2 = 1
            if (r1 >= 0) goto L_0x000b
            r1 = r2
            goto L_0x000c
        L_0x000b:
            r1 = 0
        L_0x000c:
            if (r1 == 0) goto L_0x000f
            long r11 = -r11
        L_0x000f:
            float r11 = (float) r11
            r12 = 1147207680(0x44610000, float:900.0)
            int r3 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            r4 = 1
            if (r3 <= 0) goto L_0x0021
            int r0 = com.samsung.android.gallery.support.R$string.kilo_byte
            long r6 = KB
            long r8 = UNIT
            float r3 = (float) r8
            float r11 = r11 / r3
            goto L_0x0022
        L_0x0021:
            r6 = r4
        L_0x0022:
            int r3 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x002e
            int r0 = com.samsung.android.gallery.support.R$string.mega_byte
            long r6 = MB
            long r8 = UNIT
            float r3 = (float) r8
            float r11 = r11 / r3
        L_0x002e:
            int r3 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x003a
            int r0 = com.samsung.android.gallery.support.R$string.giga_byte
            long r6 = GB
            long r8 = UNIT
            float r3 = (float) r8
            float r11 = r11 / r3
        L_0x003a:
            int r3 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x0046
            int r0 = com.samsung.android.gallery.support.R$string.tera_byte
            long r6 = TB
            long r8 = UNIT
            float r3 = (float) r8
            float r11 = r11 / r3
        L_0x0046:
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r12 <= 0) goto L_0x0052
            int r0 = com.samsung.android.gallery.support.R$string.peta_byte
            long r6 = PB
            long r8 = UNIT
            float r12 = (float) r8
            float r11 = r11 / r12
        L_0x0052:
            int r12 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            java.lang.String r3 = "%.0f"
            if (r12 == 0) goto L_0x007b
            r12 = 1120403456(0x42c80000, float:100.0)
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r12 < 0) goto L_0x005f
            goto L_0x007b
        L_0x005f:
            r12 = 1065353216(0x3f800000, float:1.0)
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            java.lang.String r4 = "%.2f"
            if (r12 >= 0) goto L_0x0069
        L_0x0067:
            r3 = r4
            goto L_0x007b
        L_0x0069:
            r12 = 1092616192(0x41200000, float:10.0)
            int r12 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x0077
            r12 = r13 & 1
            if (r12 == 0) goto L_0x0067
            java.lang.String r12 = "%.1f"
            r3 = r12
            goto L_0x007b
        L_0x0077:
            r12 = r13 & 1
            if (r12 == 0) goto L_0x0067
        L_0x007b:
            if (r1 == 0) goto L_0x007e
            float r11 = -r11
        L_0x007e:
            boolean r12 = isSpecialLocale(r10)
            if (r12 == 0) goto L_0x008b
            java.lang.String r12 = "%s"
        L_0x0086:
            java.lang.String r12 = r3.concat(r12)
            goto L_0x008e
        L_0x008b:
            java.lang.String r12 = " %s"
            goto L_0x0086
        L_0x008e:
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            java.lang.String r13 = com.samsung.android.gallery.support.utils.AppResources.getString(r0)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r13}
            java.lang.String r10 = java.lang.String.format(r10, r12, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.StringCompat.formatFileSize(java.util.Locale, long, int):java.lang.String");
    }

    public static float getDrawWidth(String str, Typeface typeface, float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        paint.setTypeface(typeface);
        return paint.measureText(str);
    }

    public static String toString(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        return (String) Arrays.stream(iArr).mapToObj(new D(2)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
    }

    public static String subStringByDrawWidth(String str, Typeface typeface, float f, float f5, boolean z) {
        float drawWidth = getDrawWidth(str, typeface, f);
        if (drawWidth <= f5) {
            return str;
        }
        float f8 = f5 / drawWidth;
        if (z) {
            return str.substring(0, (int) (((float) str.length()) * f8));
        }
        return str.substring(0, (int) ((((float) str.length()) * f8) - 3.0f)) + "...";
    }

    public static <T> String toString(T[] tArr) {
        if (tArr == null || tArr.length == 0) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        return (String) Arrays.stream(tArr).map(new X(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
    }

    public static String valueOf(byte[] bArr, int i2) {
        if (bArr == null || bArr.length == 0) {
            return "0:null";
        }
        return bArr.length + NumericEnum.SEP + valueOf(bArr, 0, i2, ' ');
    }

    public static <T> String toString(List<T> list) {
        if (list == null || list.isEmpty()) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        return (String) list.stream().map(new X(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
    }

    public static String valueOf(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? "0:null" : valueOf(bArr, bArr.length);
    }

    public static String valueOf(byte[] bArr, int i2, int i7, char c5) {
        try {
            int min = Math.min(bArr.length, i7);
            int i8 = min * 3;
            char[] cArr = new char[(i8 + 12)];
            int i10 = 0;
            while (i2 < min) {
                byte b = bArr[i2];
                char[] cArr2 = CharArrayHolder.value;
                cArr[i10] = cArr2[(b & 255) >>> 4];
                cArr[i10 + 1] = cArr2[b & 15];
                cArr[i10 + 2] = c5;
                i2++;
                i10 += 3;
            }
            return new String(cArr, 0, i8 - 1);
        } catch (Exception unused) {
            return "null";
        }
    }
}
