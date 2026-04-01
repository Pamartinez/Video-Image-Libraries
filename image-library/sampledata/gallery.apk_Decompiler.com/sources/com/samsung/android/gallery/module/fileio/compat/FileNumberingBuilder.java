package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import com.samsung.android.gallery.support.utils.FileUtils;
import i.C0212a;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileNumberingBuilder {
    static final Pattern PATTERN = Pattern.compile("(.*)\\s?\\((\\d+)\\)\\s?$");

    public static String build(String str) {
        String format;
        if (!new File(str).exists()) {
            return str;
        }
        String[] splitNameAndExtension = FileUtils.splitNameAndExtension(str);
        int i2 = 0;
        String p6 = C0212a.p(new StringBuilder(), splitNameAndExtension[0], " ");
        String str2 = splitNameAndExtension[1];
        try {
            Matcher matcher = PATTERN.matcher(splitNameAndExtension[0]);
            if (matcher.find()) {
                p6 = matcher.group(1);
                i2 = NumberFormat.getInstance(Locale.getDefault()).parse(matcher.group(2)).intValue();
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("UniqueFile#build failed. e="), "FileOpJob#Name");
        }
        do {
            i2++;
            format = String.format(Locale.getDefault(), "%s(%d).%s", new Object[]{p6, Integer.valueOf(i2), str2});
        } while (new File(format).exists());
        return format;
    }
}
