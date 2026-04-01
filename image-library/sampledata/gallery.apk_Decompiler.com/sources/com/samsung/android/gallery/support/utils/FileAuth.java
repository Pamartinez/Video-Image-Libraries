package com.samsung.android.gallery.support.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileAuth {
    public static boolean setOnlyReadable(String str) {
        try {
            Files.setPosixFilePermissions(Paths.get(new File(str).getAbsolutePath(), new String[0]), PosixFilePermissions.fromString("r--r-----"));
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) "FileAuth", "setOnlyReadable failed", (Throwable) e);
            return false;
        }
    }

    public static void setWritable(String str) {
        try {
            Files.setPosixFilePermissions(Paths.get(new File(str).getAbsolutePath(), new String[0]), PosixFilePermissions.fromString("rw-rw----"));
        } catch (Exception e) {
            Log.e((CharSequence) "FileAuth", "setWritable failed", (Throwable) e);
        }
    }
}
