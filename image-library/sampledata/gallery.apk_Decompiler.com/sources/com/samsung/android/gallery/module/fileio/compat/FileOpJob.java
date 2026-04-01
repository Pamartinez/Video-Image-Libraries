package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import E5.b;
import android.database.Cursor;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOpJob {
    public FileOpError errorCode;
    public String errorReason;
    public ArrayList<FileOpJob> failList;
    public ArrayList<? extends FileItemInterface> groupList;
    public final boolean groupOp;
    public int newGroupId;
    public int operation;
    public ArrayList<FileOpJob> passList;
    public int prepMode;
    public final FileItemInterface source;
    public String target;
    public String targetCustom;
    public final String targetDir;

    public FileOpJob(FileItemInterface fileItemInterface, String str, int i2, boolean z) {
        this.errorCode = FileOpError.None;
        this.source = fileItemInterface;
        this.targetDir = str;
        this.operation = i2;
        this.groupOp = z;
    }

    public static String alias(int i2) {
        String str;
        String str2;
        int i7 = i2 & ScoverState.TYPE_NFC_SMART_COVER;
        StringBuilder sb2 = new StringBuilder();
        if (i7 == 1) {
            str = "cp";
        } else if (i7 == 2) {
            str = "rn";
        } else if (i7 == 5) {
            str = "mv";
        } else if (i7 == 4) {
            str = "rm";
        } else {
            str = C0086a.i(i7, "#");
        }
        sb2.append(str);
        if ((i2 & 256) > 0) {
            str2 = "+o";
        } else {
            str2 = "";
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public String buildNewName() {
        if (this.source.isCloudOnly()) {
            return SCloudHelper.getNewFileFullPath(AppResources.getAppContext(), this.source, this.target);
        }
        return FileNumberingBuilder.build(this.target);
    }

    public void e(String str, FileOpError fileOpError, String str2) {
        this.errorCode = fileOpError;
        this.errorReason = C0212a.B(str, ": ", str2);
        Log.e("FileOpJob", fileOpError + " " + str2);
    }

    public long getFileSize() {
        if (this.groupOp) {
            return loadGroupItems().stream().mapToLong(new b(13)).sum();
        }
        return this.source.getFileSize();
    }

    public FileOpJob inSameStorage(String str) {
        String str2;
        if (isRename()) {
            if (this.source.isCloudOnly()) {
                str2 = StorageInfo.getDefault().root;
            } else {
                str2 = this.source.getPath();
            }
            if (str2 != null && !str2.startsWith(str)) {
                this.operation = 5;
            }
        }
        return this;
    }

    public boolean isCopy() {
        if ((this.operation & ScoverState.TYPE_NFC_SMART_COVER) == 1) {
            return true;
        }
        return false;
    }

    public boolean isMove() {
        if ((this.operation & ScoverState.TYPE_NFC_SMART_COVER) == 5) {
            return true;
        }
        return false;
    }

    public boolean isOverWrite() {
        if ((this.operation & 256) > 0) {
            return true;
        }
        return false;
    }

    public boolean isRename() {
        if ((this.operation & ScoverState.TYPE_NFC_SMART_COVER) == 2) {
            return true;
        }
        return false;
    }

    public boolean isSecured() {
        if ((this.operation & 983040) > 0) {
            return true;
        }
        return false;
    }

    public Cursor loadGroupContents(FileItemInterface fileItemInterface) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !fileItemInterface.isPrivateItem()) {
            return DbCompatGroup.getGroupCursor(fileItemInterface, fileItemInterface.getAlbumID());
        }
        return PrivateDatabase.getInstance().queryGroup(fileItemInterface);
    }

    public ArrayList<? extends FileItemInterface> loadGroupItems() {
        if (this.groupList == null) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<? extends FileItemInterface> arrayList = new ArrayList<>();
            FileItemInterface fileItemInterface = this.source;
            if (fileItemInterface == null || fileItemInterface.getGroupMediaId() == 0) {
                Log.e("FileOpJob", "loadGroupItems failed. invalid data");
                arrayList.add(this.source);
            } else {
                Cursor loadGroupContents = loadGroupContents(this.source);
                if (loadGroupContents != null) {
                    try {
                        if (loadGroupContents.moveToFirst()) {
                            do {
                                arrayList.add(MediaItemBuilder.create(loadGroupContents));
                            } while (loadGroupContents.moveToNext());
                        }
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
                if (loadGroupContents != null) {
                    loadGroupContents.close();
                }
                a.A(new Object[]{Long.valueOf(this.source.getGroupMediaId()), Integer.valueOf(this.source.getAlbumID()), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("loadGroupItems"), "FileOpJob");
            }
            this.groupList = arrayList;
        }
        return this.groupList;
        throw th;
    }

    public boolean prepareOrWait(int i2) {
        if (this.source.isGroupShot()) {
            i2 = 2;
        } else if (i2 == 0) {
            i2 = this.prepMode;
        }
        String str = FileUtils.splitPathAndName(this.source.getReferencePath())[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.targetDir);
        String p6 = C0212a.p(sb2, File.separator, str);
        this.target = p6;
        if (i2 != 0) {
            if (i2 == 1) {
                if (this.source.isCloudOnly()) {
                    this.operation = 0;
                } else {
                    this.operation |= 256;
                }
            } else if (i2 == 3) {
                String str2 = this.targetCustom;
                if (str2 == null) {
                    str2 = FileNumberingBuilder.build(p6);
                }
                this.target = str2;
            } else if (i2 == 2) {
                if (this.source.isCloudOnly()) {
                    this.target = SCloudHelper.getNewFileFullPath(AppResources.getAppContext(), this.source, p6);
                } else {
                    this.target = FileNumberingBuilder.build(p6);
                }
            } else if (i2 == 4 && FileUtils.exists(p6)) {
                this.operation = 0;
            }
            return true;
        } else if ((!this.source.isCloudOnly() || !SCloudHelper.hasSameCloudItem(AppResources.getAppContext(), this.source, p6)) && !FileUtils.exists(p6)) {
            return true;
        } else {
            return false;
        }
    }

    public String toGroupSummary() {
        int i2;
        int i7;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(this.source.getGroupMediaId());
        sb2.append("=(id:");
        sb2.append(this.newGroupId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<? extends FileItemInterface> arrayList = this.groupList;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 1;
        }
        sb2.append(i2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<FileOpJob> arrayList2 = this.passList;
        int i8 = 0;
        if (arrayList2 != null) {
            i7 = arrayList2.size();
        } else {
            i7 = 0;
        }
        sb2.append(i7);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<FileOpJob> arrayList3 = this.failList;
        if (arrayList3 != null) {
            i8 = arrayList3.size();
        }
        return C0086a.l(sb2, i8, ")");
    }

    public String toSimpleString() {
        String str;
        StringBuilder sb2 = new StringBuilder("FileOpJob{");
        sb2.append(alias(this.operation));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.source.getFileId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.source.getStorageType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.source.getFileSize());
        String str2 = "";
        if (this.errorCode != FileOpError.None) {
            StringBuilder sb3 = new StringBuilder(",E[");
            sb3.append(this.errorCode);
            sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            str = C0212a.p(sb3, this.errorReason, "]");
        } else {
            str = str2;
        }
        sb2.append(str);
        if (this.groupOp) {
            str2 = ",G[" + this.source.getGroupType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.source.getGroupMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.source.getCount() + "]";
        }
        return C0212a.p(sb2, str2, "}");
    }

    public String toString() {
        return toSimpleString() + " " + Logger.getEncodedString(this.source.getPath() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.target);
    }

    public FileOpJob(FileItemInterface fileItemInterface, String str, int i2) {
        this(fileItemInterface, str, i2, fileItemInterface.isGroupShot());
    }
}
