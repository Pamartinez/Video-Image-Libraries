package com.arcsoft.libarccommon.utils;

import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArcFolderUtils {
    private static final String TAG = "ArcSoft_ArcFolderUtils";

    public static boolean checkFolderEmpty(String str) {
        File file = new File(str);
        if (!file.isDirectory() || file.list().length <= 0) {
            return true;
        }
        return false;
    }

    public static void cleanDirectory(String str) {
        if (!new File(str).isFile()) {
            deleteDirFiles(str);
        }
    }

    public static boolean createDirectory(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return false;
        }
        file.getParentFile().mkdirs();
        file.mkdirs();
        return true;
    }

    private static void deleteDirFiles(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                for (String file2 : file.list()) {
                    File file3 = new File(str, file2);
                    if (file3.isDirectory()) {
                        deleteDirFiles(file3.getAbsolutePath());
                    }
                    file3.delete();
                }
            }
        }
    }
}
