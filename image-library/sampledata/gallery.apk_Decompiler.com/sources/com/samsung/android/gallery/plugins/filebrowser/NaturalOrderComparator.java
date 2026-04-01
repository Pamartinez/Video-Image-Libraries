package com.samsung.android.gallery.plugins.filebrowser;

import V8.a;
import com.samsung.android.gallery.plugins.filebrowser.FileInfo;
import com.samsung.android.gallery.support.utils.Log;
import java.math.BigInteger;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NaturalOrderComparator<T extends FileInfo> implements Comparator<T> {
    static final HashMap<Locale, Collator> sCollatorFactory = new HashMap<>();
    private final Collator collator = sCollatorFactory.computeIfAbsent(Locale.getDefault(), new a(13));

    private int compareWithLocale(String str, String str2) {
        return this.collator.compare(str, str2);
    }

    private int compareWithNumber(String str, String str2) {
        int i2;
        int length = str.length();
        int length2 = str2.length();
        int i7 = 0;
        int i8 = 0;
        while (i7 < length && i8 < length2) {
            String chunk = getChunk(str, length, i7);
            i7 += chunk.length();
            String chunk2 = getChunk(str2, length2, i8);
            i8 += chunk2.length();
            if (!isDigit(chunk.charAt(0)) || !isDigit(chunk2.charAt(0))) {
                i2 = compareWithLocale(chunk, chunk2);
                continue;
            } else {
                i2 = chunk.length() - chunk2.length();
                if (i2 == 0) {
                    i2 = new BigInteger(chunk).compareTo(new BigInteger(chunk2));
                    continue;
                } else {
                    continue;
                }
            }
            if (i2 != 0) {
                return i2;
            }
        }
        return length - length2;
    }

    private String getChunk(String str, int i2, int i7) {
        StringBuilder sb2 = new StringBuilder();
        char charAt = str.charAt(i7);
        sb2.append(charAt);
        int i8 = i7 + 1;
        if (isDigit(charAt)) {
            while (i8 < i2) {
                char charAt2 = str.charAt(i8);
                if (!isDigit(charAt2)) {
                    break;
                }
                sb2.append(charAt2);
                i8++;
            }
        } else {
            while (i8 < i2) {
                char charAt3 = str.charAt(i8);
                if (isDigit(charAt3)) {
                    break;
                }
                sb2.append(charAt3);
                i8++;
            }
        }
        return sb2.toString();
    }

    public static boolean isDigit(char c5) {
        if (c5 < '0' || c5 > '9') {
            return false;
        }
        return true;
    }

    public int compare(FileInfo fileInfo, FileInfo fileInfo2) {
        if (fileInfo.name.equals(fileInfo2.name)) {
            return 0;
        }
        String upperCase = fileInfo.name.toUpperCase();
        Normalizer.Form form = Normalizer.Form.NFD;
        String normalize = Normalizer.normalize(upperCase, form);
        String normalize2 = Normalizer.normalize(fileInfo2.name.toUpperCase(), form);
        try {
            return compareWithNumber(normalize, normalize2);
        } catch (NullPointerException e) {
            Log.e("NaturalOrderComparator", "compare failed. e=" + e.getMessage());
            return this.collator.compare(normalize, normalize2);
        }
    }
}
