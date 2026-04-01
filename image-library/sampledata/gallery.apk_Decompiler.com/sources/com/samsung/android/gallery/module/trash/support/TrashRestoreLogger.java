package com.samsung.android.gallery.module.trash.support;

import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRestoreLogger extends TrashLogger {
    private int mCloudRestoreFailed = 0;
    private final ArrayList<String> mFilePaths = new ArrayList<>();
    private int mInserted = 0;
    private int mMoveFailed = 0;
    private final ArrayList<String> mParents = new ArrayList<>();

    public TrashRestoreLogger(Context context) {
    }

    public String getDetail(boolean z) {
        StringBuilder sb2 = new StringBuilder(" FP[");
        Iterator<String> it = this.mFilePaths.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (z) {
                next = Logger.getEncodedString(next);
            }
            sb2.append(next);
            sb2.append(" ");
        }
        sb2.append("] PP[");
        Iterator<String> it2 = this.mParents.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            if (z) {
                next2 = Logger.getEncodedString(next2);
            }
            sb2.append(next2);
            sb2.append(" ");
        }
        sb2.append("] Insert[");
        sb2.append(this.mInserted);
        sb2.append("] CRF[");
        sb2.append(this.mCloudRestoreFailed);
        sb2.append("] MF[");
        sb2.append(this.mMoveFailed);
        sb2.append("]] RSS[");
        return C0086a.l(sb2, this.mRequestedScanSize, "]");
    }

    public void inserted(int i2) {
        this.mInserted += i2;
    }

    public void restoreCloudFail(StorageType storageType, CloudErrorType cloudErrorType, String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
        sb2.append("\t[Restore Cloud Fail]\t");
        sb2.append(storageType);
        sb2.append("\t");
        sb2.append(cloudErrorType);
        C0086a.z(sb2, "\t", str, "\t", str2);
        this.mExtras.add(C0212a.p(sb2, "\t", str3));
        this.mCloudRestoreFailed++;
    }

    public void restoreMoveFail(StorageType storageType, String str, String str2, String str3, boolean z) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
        sb2.append("\t[Restore Move Fail]\t");
        sb2.append(storageType);
        sb2.append("\t");
        sb2.append(str);
        C0086a.z(sb2, "\t", str2, "\t", str3);
        sb2.append("\t");
        sb2.append(z);
        this.mExtras.add(sb2.toString());
        this.mMoveFailed++;
    }

    public void restorePath(String str) {
        String parent = FileUtils.getParent(str);
        if (!this.mParents.contains(parent)) {
            this.mParents.add(parent);
        }
        if (this.mFilePaths.size() < 10 && !this.mFilePaths.contains(str)) {
            this.mFilePaths.add(str);
        }
    }
}
