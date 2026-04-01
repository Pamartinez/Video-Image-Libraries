package com.samsung.android.gallery.app.controller.internals;

import N2.j;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevertOriginalVideoCmd extends RevertOriginalImageCmd {
    String mBackupFile;
    String mTempFile;

    private boolean backupFile(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        new File(str2).delete();
        if (!FileUtils.rename(str, str2)) {
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("backupFile failed");
            sb2.append(Logger.vt(currentTimeMillis));
            sb2.append(" ");
            sb2.append(Logger.getEncodedString(str + " > " + str2));
            Log.e(str3, sb2.toString());
            return false;
        }
        String str4 = this.TAG;
        Log.d(str4, "backupFile" + Logger.vt(currentTimeMillis) + "");
        return true;
    }

    public void cleanUpCache(MediaItem mediaItem) {
        if (!FileUtils.delete(this.mBackupFile)) {
            Log.e(this.TAG, "cleanUpCache delete-backup failed");
        }
    }

    public boolean revertFile() {
        String str;
        String[] splitPathAndName = FileUtils.splitPathAndName(this.mTarget);
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        sb2.append(splitPathAndName[0]);
        String str2 = File.separator;
        sb2.append(str2);
        sb2.append(".reverting-");
        sb2.append(splitPathAndName[1]);
        this.mTempFile = FileUtils.makeUniqueFilename(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        j.z(sb3, splitPathAndName[0], str2, ".edited-");
        sb3.append(splitPathAndName[1]);
        this.mBackupFile = FileUtils.makeUniqueFilename(sb3.toString());
        long currentTimeMillis = System.currentTimeMillis();
        if (copyFile(this.mSource, this.mTempFile) && backupFile(this.mTarget, this.mBackupFile) && renameFile(this.mTempFile, this.mTarget)) {
            z = true;
        }
        String str3 = this.TAG;
        StringBuilder sb4 = new StringBuilder("revertFile");
        if (z) {
            str = "";
        } else {
            str = " failed";
        }
        sb4.append(str);
        sb4.append(Logger.vt(currentTimeMillis));
        sb4.append(" ");
        sb4.append(FileUtils.info(this.mTarget));
        Log.d(str3, sb4.toString());
        return z;
    }
}
