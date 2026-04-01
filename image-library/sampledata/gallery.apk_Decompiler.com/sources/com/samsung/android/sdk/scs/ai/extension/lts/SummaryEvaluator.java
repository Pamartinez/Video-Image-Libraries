package com.samsung.android.sdk.scs.ai.extension.lts;

import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SummaryEvaluator {
    public static double calculateCompression(String str, String str2) {
        String[] normalizeAndTokenize = normalizeAndTokenize(str);
        String[] normalizeAndTokenize2 = normalizeAndTokenize(str2);
        if (normalizeAndTokenize2.length == 0) {
            return MapUtil.INVALID_LOCATION;
        }
        if (normalizeAndTokenize2.length != 1 || !normalizeAndTokenize2[0].isEmpty()) {
            return ((double) normalizeAndTokenize.length) / ((double) normalizeAndTokenize2.length);
        }
        return MapUtil.INVALID_LOCATION;
    }

    public static double calculateCoverage(String str, String str2) {
        HashSet hashSet = new HashSet(Arrays.asList(normalizeAndTokenize(str)));
        String[] normalizeAndTokenize = normalizeAndTokenize(str2);
        int i2 = 0;
        for (String contains : normalizeAndTokenize) {
            if (hashSet.contains(contains)) {
                i2++;
            }
        }
        if (normalizeAndTokenize.length == 0) {
            return MapUtil.INVALID_LOCATION;
        }
        return ((double) i2) / ((double) normalizeAndTokenize.length);
    }

    public static double calculateDensity(String str, String str2) {
        String[] normalizeAndTokenize = normalizeAndTokenize(str);
        String[] normalizeAndTokenize2 = normalizeAndTokenize(str2);
        ArrayList arrayList = new ArrayList();
        boolean[] zArr = new boolean[normalizeAndTokenize.length];
        int i2 = 0;
        for (String str3 : normalizeAndTokenize2) {
            int i7 = 0;
            while (true) {
                if (i7 < normalizeAndTokenize.length && (!str3.equals(normalizeAndTokenize[i7]) || !zArr[i7])) {
                    if (str3.equals(normalizeAndTokenize[i7]) && !zArr[i7]) {
                        arrayList.add(Integer.valueOf(i7));
                        zArr[i7] = true;
                        break;
                    }
                    i7++;
                } else {
                    break;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            int i8 = 1;
            for (int i10 = 1; i10 < arrayList.size(); i10++) {
                if (((Integer) arrayList.get(i10)).intValue() == ((Integer) arrayList.get(i10 - 1)).intValue() + 1) {
                    i8++;
                } else {
                    i2 = (i8 * i8) + i2;
                    i8 = 1;
                }
            }
            i2 += i8 * i8;
        }
        if (normalizeAndTokenize2.length == 0) {
            return MapUtil.INVALID_LOCATION;
        }
        return ((double) i2) / ((double) normalizeAndTokenize2.length);
    }

    public static String[] normalizeAndTokenize(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new String[0];
        }
        String trim = str.toLowerCase().replaceAll("[^\\p{L}\\p{N}\\s]", "").trim();
        if (trim.isEmpty()) {
            return new String[0];
        }
        return trim.split("\\s+");
    }
}
