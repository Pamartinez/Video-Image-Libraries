package com.samsung.android.gallery.module.trash.helper;

import A.a;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.module.trash.factory.AbsTrashFactory;
import com.samsung.android.gallery.module.trash.support.TrashExternalLogger;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashExternalHelper extends TrashHelper {
    private final TrashExternalLogger mLogger;

    public TrashExternalHelper(Context context) {
        super(context);
        this.mLogger = new TrashExternalLogger(context);
    }

    private void deleteRemainedTrashFiles2() {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        try {
            Iterator<File> it = this.mTrashDirs.iterator();
            while (it.hasNext()) {
                ArrayList arrayList = (ArrayList) FileUtils.list(it.next().getAbsolutePath());
                if (arrayList != null && arrayList.size() > 0) {
                    this.mLogger.increaseExtra1(arrayList.size());
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (FileUtils.delete((String) it2.next())) {
                            i2++;
                            this.mLogger.increaseExtra2();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i2 > 0) {
            this.mLogger.setChanged();
        }
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(this.mTrashDirs.size()), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("deleteRemainedTrashFiles2"), str);
    }

    private void deleteTrashDatabase() {
        long currentTimeMillis = System.currentTimeMillis();
        int deleteAllTrash = this.mTrashProvider.deleteAllTrash();
        if (deleteAllTrash > 0) {
            this.mLogger.setChanged();
        }
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(deleteAllTrash), Long.valueOf(currentTimeMillis)}, new StringBuilder("deleteTrashDatabase"), str);
    }

    private void deleteTrashFile(ArrayList<String> arrayList) {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<String> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && FileUtils.delete(next)) {
                i2++;
                this.mLogger.increaseExtra2();
            }
        }
        if (i2 > 0) {
            this.mLogger.setChanged();
        }
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("deleteTrashFile"), str);
    }

    private void updateNoneDestructionDB(ArrayList<String> arrayList) {
        StringBuilder sb2;
        String str;
        boolean z;
        if (arrayList != null && arrayList.size() != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                int size = arrayList.size() - 1;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String optString = new JSONObject(arrayList.get(i2)).optString("__origin_file_hash");
                    AbsTrashFactory absTrashFactory = this.mTrashFactory;
                    NoneDestructionOperationType noneDestructionOperationType = NoneDestructionOperationType.EMPTY;
                    if (i2 == size) {
                        z = true;
                    } else {
                        z = false;
                    }
                    absTrashFactory.updateNoneDestructionDB(optString, noneDestructionOperationType, z);
                }
                str = this.TAG;
                sb2 = new StringBuilder("updateNoneDestructionDB +");
            } catch (Exception e) {
                Log.e(this.TAG, "updateNoneDestructionDB failed e=" + e.getMessage());
                str = this.TAG;
                sb2 = new StringBuilder("updateNoneDestructionDB +");
            } catch (Throwable th) {
                a.x(new StringBuilder("updateNoneDestructionDB +"), currentTimeMillis, this.TAG);
                throw th;
            }
            a.x(sb2, currentTimeMillis, str);
        }
    }

    public void clearTrash() {
        ArrayList arrayList = new ArrayList();
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            ArrayList arrayList2 = new ArrayList();
            loadTrashDataList(arrayList, arrayList2);
            updateNoneDestructionDB(arrayList2);
        }
        deleteTrashDatabase();
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            deleteTrashFile(arrayList);
        }
        if (Features.isEnabled(Features.SUPPORT_MY_FILES_API)) {
            deleteRemainedTrashFiles2();
        } else {
            deleteRemainedTrashFiles();
        }
    }

    public int deleteCloudRecord() {
        return this.mTrashProvider.deleteTrash("__storageType = 2 ", (String[]) null);
    }

    public final void deleteRemainedTrashFiles() {
        File[] fileArr;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<File> it = this.mTrashDirs.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                File next = it.next();
                if (next != null) {
                    fileArr = next.listFiles();
                } else {
                    fileArr = null;
                }
                if (fileArr != null && fileArr.length > 0) {
                    this.mLogger.increaseExtra1(fileArr.length);
                    for (File delete : fileArr) {
                        if (FileUtils.delete(delete)) {
                            i2++;
                            this.mLogger.increaseExtra2();
                        }
                    }
                }
            }
            if (i2 > 0) {
                this.mLogger.setChanged();
            }
            Log.d(this.TAG, "deleteRemainedTrashFiles" + Logger.vt(Integer.valueOf(this.mTrashDirs.size()), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            j.s(e, new StringBuilder("deleteRemainedTrashFiles failed. e="), this.TAG);
        }
    }

    public void done() {
        String str = this.TAG;
        Log.d(str, "external done. " + this.mLogger.getDumpString());
        super.done();
    }

    public void eraseCloudData(TrashRestoreItem trashRestoreItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__storageType", 1);
        contentValues.putNull("__cloudServerId");
        contentValues.putNull("__cloudTP");
        contentValues.put("__restoreExtra", trashRestoreItem.getRestoreExtraForLocalUpdate());
        if (!updateTrashDb(contentValues, "__absPath =? ", new String[]{trashRestoreItem.getPath()})) {
            Log.w(this.TAG, "no cloud record erased");
        }
    }

    public void eraseCloudRecord() {
        Cursor trashCursor;
        this.mLogger.setTask(TrashExternalLogger.Task.SIGNED_OUT);
        try {
            trashCursor = this.mTrashProvider.getTrashCursor(false);
            if (trashCursor != null) {
                if (trashCursor.moveToFirst()) {
                    do {
                        TrashRestoreItem trashRestoreItem = new TrashRestoreItem(trashCursor);
                        if (isCloudOnly(trashRestoreItem.getStorageType())) {
                            if (isCopyOrDeleteCloudThumbnail(trashRestoreItem)) {
                                deleteCloudThumbnail(trashRestoreItem.getPath());
                            }
                            this.mLogger.increaseExtra1(1);
                        } else if (isLocalCloud(trashRestoreItem.getStorageType())) {
                            if (isCopyOrDeleteCloudThumbnail(trashRestoreItem)) {
                                deleteCloudThumbnail(trashRestoreItem.getLCThumbPath());
                            }
                            eraseCloudData(trashRestoreItem);
                            this.mLogger.increaseExtra2();
                        }
                    } while (trashCursor.moveToNext());
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (deleteCloudRecord() > 0) {
            this.mLogger.setChanged();
            return;
        }
        return;
        throw th;
    }

    public ArrayList<File> getTrashDirs() {
        return this.mTrashDirs;
    }

    public void loadTrashDataList(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        StringBuilder sb2;
        String str;
        Cursor trashCursor;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            trashCursor = this.mTrashProvider.getTrashCursor(false);
            if (trashCursor != null) {
                if (trashCursor.moveToFirst()) {
                    int columnIndex = trashCursor.getColumnIndex("__absPath");
                    int columnIndex2 = trashCursor.getColumnIndex("__restoreExtra");
                    do {
                        arrayList.add(trashCursor.getString(columnIndex));
                        String string = trashCursor.getString(columnIndex2);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList2.add(string);
                        }
                    } while (trashCursor.moveToNext());
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
            str = this.TAG;
            sb2 = new StringBuilder("loadTrashDataList +");
        } catch (Exception e) {
            try {
                String str2 = this.TAG;
                Log.e(str2, "loadTrashDataList failed e=" + e.getMessage());
                str = this.TAG;
                sb2 = new StringBuilder("loadTrashDataList +");
            } catch (Throwable th) {
                a.x(new StringBuilder("loadTrashDataList +"), currentTimeMillis, this.TAG);
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        a.x(sb2, currentTimeMillis, str);
        return;
        throw th;
    }

    public String tag() {
        return "TrashExternalHelper";
    }

    public void turnOffTrash() {
        this.mLogger.setTask(TrashExternalLogger.Task.TRASH_OFF);
        clearTrash();
    }

    public TrashExternalLogger getLogger() {
        return this.mLogger;
    }
}
