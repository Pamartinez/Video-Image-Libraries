package com.samsung.android.gallery.support.utils;

import android.util.Log;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecureFile extends File {
    public SecureFile(String str) {
        super(SecureDigitalPolicy.getWritablePath(str));
    }

    public boolean canExecute() {
        try {
            return super.canExecute();
        } catch (SecurityException e) {
            Log.e("SecureFile", "canExecute failed e=" + e.getMessage());
            return false;
        }
    }

    public boolean canRead() {
        try {
            return super.canRead();
        } catch (SecurityException e) {
            Log.e("SecureFile", "canRead failed e=" + e.getMessage());
            return false;
        }
    }

    public boolean canWrite() {
        try {
            return super.canWrite();
        } catch (SecurityException e) {
            Log.e("SecureFile", "canWrite failed e=" + e.getMessage());
            return false;
        }
    }

    public String getAbsolutePath() {
        return SecureDigitalPolicy.getReadablePath(super.getAbsolutePath());
    }

    public String getCanonicalPath() {
        return SecureDigitalPolicy.getReadablePath(super.getCanonicalPath());
    }

    public String getParent() {
        return SecureDigitalPolicy.getReadablePath(super.getParent());
    }

    public File getParentFile() {
        return new SecureFile(super.getParent());
    }

    public SecureFile(String str, String str2) {
        super(SecureDigitalPolicy.getWritablePath(str), str2);
    }

    public SecureFile(File file, String str) {
        super(file, str);
    }
}
