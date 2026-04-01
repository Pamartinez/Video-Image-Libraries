package com.samsung.android.gallery.module.trash.support;

import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashDeleteLogger extends TrashLogger {
    private int mAbnormal = 0;
    private boolean mAbnormalDeleteRequest = false;
    private int mCloudDeleted = 0;
    private final ArrayList<String> mFilePaths = new ArrayList<>();
    private int mLocalDeleted = 0;
    private int mMoveFailed = 0;
    private final ArrayList<String> mParents = new ArrayList<>();
    private int mPppAbnormal = 0;
    private int mRevertTrashUpdated = 0;
    private int mSecAbnormal = 0;
    private int mSecTrashUpdated = 0;

    public void deleteAbnormal(int i2, String str, boolean z) {
        this.mExtras.add(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + "\t[Delete Abnormal]\t\t" + str + "\t" + z);
        this.mAbnormal = this.mAbnormal + i2;
        this.mAbnormalDeleteRequest = true;
    }

    public void deleteMoveFail(StorageType storageType, String str, String str2, boolean z) {
        this.mExtras.add(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + "\t[Delete Move Fail]\t" + storageType + "\t" + str + "\t" + str2 + "\t" + z);
        this.mMoveFailed = this.mMoveFailed + 1;
    }

    public void deletePath(String str) {
        String parent = FileUtils.getParent(str);
        if (!this.mParents.contains(parent)) {
            this.mParents.add(parent);
        }
        if (this.mFilePaths.size() < 10 && !this.mFilePaths.contains(str)) {
            this.mFilePaths.add(str);
        }
    }

    public void deletePppAbnormal(int i2, String str, boolean z) {
        this.mExtras.add(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + "\t[Delete Ppp Abnormal]\t\t" + str + "\t" + z);
        this.mPppAbnormal = this.mPppAbnormal + i2;
        this.mAbnormalDeleteRequest = true;
    }

    public void deleteSecAbnormal(int i2, String str, boolean z) {
        this.mExtras.add(TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()) + "\t[Delete Sec Abnormal]\t\t" + str + "\t" + z);
        this.mSecAbnormal = this.mSecAbnormal + i2;
        this.mAbnormalDeleteRequest = true;
    }

    public void deleted(int[] iArr) {
        this.mLocalDeleted += iArr[0];
        this.mCloudDeleted += iArr[1];
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
        sb2.append("] LD[");
        sb2.append(this.mLocalDeleted);
        sb2.append("] CD[");
        sb2.append(this.mCloudDeleted);
        sb2.append("] MF[");
        sb2.append(this.mMoveFailed);
        sb2.append("] AB[");
        sb2.append(this.mAbnormal);
        sb2.append("] ABS[");
        sb2.append(this.mSecAbnormal);
        sb2.append("] ABPP[");
        sb2.append(this.mPppAbnormal);
        sb2.append("] TU[");
        sb2.append(this.mSecTrashUpdated);
        sb2.append("] ABR[");
        sb2.append(this.mAbnormalDeleteRequest);
        sb2.append("] RVT[");
        sb2.append(this.mRevertTrashUpdated);
        sb2.append("] RSS[");
        return C0086a.l(sb2, this.mRequestedScanSize, "]");
    }

    public boolean isAbnormalDeleteRequest() {
        return this.mAbnormalDeleteRequest;
    }

    public boolean isAbnormalDeleted() {
        if (this.mAbnormal + this.mSecAbnormal + this.mPppAbnormal > 0) {
            return true;
        }
        return false;
    }

    public void revertTrashUpdated(int i2) {
        this.mRevertTrashUpdated += i2;
    }

    public void trashUpdated(int i2) {
        this.mSecTrashUpdated += i2;
    }
}
