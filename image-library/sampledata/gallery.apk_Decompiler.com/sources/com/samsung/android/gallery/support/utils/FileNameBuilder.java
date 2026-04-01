package com.samsung.android.gallery.support.utils;

import android.text.TextUtils;
import i.C0212a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileNameBuilder {
    private String baseName;
    private String dirPath;
    private String ext;
    private boolean findUnique;

    public FileNameBuilder(String str) {
        if (!TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf("/");
            this.dirPath = str.substring(0, lastIndexOf);
            String substring = str.substring(lastIndexOf + 1);
            String str2 = "";
            this.baseName = substring.replaceAll("\\.[^.]*$", str2);
            int lastIndexOf2 = substring.lastIndexOf(".");
            this.ext = lastIndexOf2 >= 0 ? substring.substring(lastIndexOf2 + 1) : str2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public String build() {
        String str;
        String str2 = this.dirPath + File.separator + this.baseName + "." + this.ext;
        if (!this.findUnique || !isExists(str2)) {
            return str2;
        }
        int i2 = 1;
        do {
            str = this.dirPath + File.separator + this.baseName + "(" + i2 + ")." + this.ext;
            i2++;
        } while (isExists(str));
        return str;
    }

    public String buildUnique() {
        unique();
        return build();
    }

    public boolean isExists(String str) {
        return new SecureFile(str).exists();
    }

    public FileNameBuilder setDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault());
        this.baseName += simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return this;
    }

    public FileNameBuilder setDirectoryPath(String str) {
        this.dirPath = str;
        return this;
    }

    public FileNameBuilder setExtension(String str) {
        this.ext = str;
        return this;
    }

    public FileNameBuilder setPostFix(String str) {
        this.baseName = C0212a.p(new StringBuilder(), this.baseName, str);
        return this;
    }

    public FileNameBuilder setPreFix(String str) {
        StringBuilder s = C0212a.s(str);
        s.append(this.baseName);
        this.baseName = s.toString();
        return this;
    }

    public FileNameBuilder unique() {
        this.findUnique = true;
        return this;
    }
}
