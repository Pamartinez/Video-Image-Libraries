package com.samsung.android.gallery.module.trash.factory;

import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.local.ReferenceDatabaseManager;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashBaseItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsTrashFactory {
    protected final String TAG = tag();
    final ArrayList<String> mLocalDelete = new ArrayList<>();
    final ArrayList<String> mLocalDeletePath = new ArrayList<>();
    final ReferenceDatabaseManager mReferenceManager;
    final ArrayList<String> mSecLocalDelete = new ArrayList<>();

    public AbsTrashFactory(Context context) {
        this.mReferenceManager = new ReferenceDatabaseManager(context.getContentResolver());
    }

    public abstract int[] bulkDelete(TrashDeleteItem trashDeleteItem, boolean z);

    public abstract int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z);

    public int bulkUpdate(TrashDeleteItem trashDeleteItem, boolean z) {
        return 0;
    }

    public int deleteAbnormal(TrashDeleteItem trashDeleteItem) {
        long currentTimeMillis = System.currentTimeMillis();
        int delete = this.mReferenceManager.delete(getDeleteUri(), "_id = ? ", new String[]{String.valueOf(getTargetId(trashDeleteItem))});
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("delete abnormal time [");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append("][");
        sb2.append(delete);
        j.y(sb2, "]", str);
        return delete;
    }

    public abstract boolean deleteCloudFromRef(FileItemInterface fileItemInterface);

    public abstract boolean deleteCloudRecordDeleteTable(String str);

    public abstract String getCloudThumbRootPath();

    public abstract Uri getDeleteUri();

    public String getDeleteWhere(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "_id IN (" + stringJoiner.toString() + ")";
    }

    public String getDeleteWherePath(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "_data IN (" + stringJoiner.toString() + ")";
    }

    public HashMap<String, File> getInternalTrash(String str) {
        HashMap<String, File> hashMap = new HashMap<>();
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            hashMap.put(FileUtils.EXTERNAL_STORAGE_DIR, appContext.getExternalFilesDir(str));
        }
        return hashMap;
    }

    public String getOriginFileHash(TrashBaseItem trashBaseItem) {
        return null;
    }

    public Uri getRefFilesUri() {
        return MediaUri.getInstance().getFilesUri();
    }

    public abstract long getTargetId(FileItemInterface fileItemInterface);

    public void putIds(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem != null) {
            this.mLocalDelete.add(String.valueOf(getTargetId(trashDeleteItem)));
        }
    }

    public abstract boolean supportTrash();

    public abstract String tag();

    public int updateIsTrashed(String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_trashed", Integer.valueOf(z ? 1 : 0));
        Bundle bundle = new Bundle();
        bundle.putInt("android:query-arg-match-trashed", 1);
        bundle.putString("android:query-arg-sql-selection", "_id in (" + str + ")");
        return this.mReferenceManager.update(MediaUri.getInstance().getSecMediaUri(), contentValues, bundle);
    }

    public boolean useCloudThumbMove() {
        return true;
    }

    public boolean useDBInsertAfter(TrashRestoreItem trashRestoreItem) {
        return true;
    }

    public boolean useDBInsertFirst(TrashRestoreItem trashRestoreItem) {
        return false;
    }

    public boolean useMediaDbDeletion() {
        if (!supportTrash() || Features.isEnabled(Features.IS_REQUEST_RAW_EXTERNAL_STORAGE_ENABLED)) {
            return true;
        }
        return false;
    }

    public boolean useMediaScanForMoveToTrash() {
        return false;
    }

    public boolean useMediaScanForRestore() {
        return false;
    }

    public abstract boolean useStoreApi();

    public void updateNoneDestructionDB(String str, NoneDestructionOperationType noneDestructionOperationType, boolean z) {
    }
}
