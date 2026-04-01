package com.samsung.android.gallery.support.utils;

import i.C0212a;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BnRConstants {
    public static final String BACKUP_DIR;
    public static final String BASE_DIR;
    public static final String KEEP_RESTORE_DIR;
    public static final String RESTORE_DIR;

    static {
        String externalFilesDir = FileUtils.getExternalFilesDir("smartswitch");
        BASE_DIR = externalFilesDir;
        StringBuilder s = C0212a.s(externalFilesDir);
        String str = File.separator;
        BACKUP_DIR = C0212a.p(s, str, "backup");
        RESTORE_DIR = C0212a.B(externalFilesDir, str, "restore");
        KEEP_RESTORE_DIR = C0212a.B(externalFilesDir, str, "keepRestore");
    }
}
