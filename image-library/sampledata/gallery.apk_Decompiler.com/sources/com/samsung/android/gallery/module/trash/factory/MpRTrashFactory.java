package com.samsung.android.gallery.module.trash.factory;

import A.a;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashBaseItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpRTrashFactory extends MpQTrashFactory {
    private static final Uri NONE_DESTRUCTION_URI = Uri.parse("content://secmedia/nondestruction");
    private final ArrayList<String> mNoneDestructionUpdate = new ArrayList<>();

    public MpRTrashFactory(Context context) {
        super(context);
    }

    private void addOriginFileHashList(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(this.TAG, "originFileHash is added");
            this.mNoneDestructionUpdate.add(str);
        }
    }

    private String getUpdateNoneDestructionDBWhere(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "hash IN (" + stringJoiner.toString() + ")";
    }

    public int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z) {
        StorageType storageType;
        long currentTimeMillis = System.currentTimeMillis();
        if (trashRestoreItem == null) {
            storageType = StorageType.None;
        } else {
            storageType = trashRestoreItem.getStorageType();
        }
        if (storageType == StorageType.LocalCloud) {
            return singleInsert(trashRestoreItem);
        }
        putValues(trashRestoreItem, storageType);
        int size = this.mCloudRestore.size();
        int i2 = 0;
        if (size < 100 && !z) {
            return 0;
        }
        if (!this.mCloudRestore.isEmpty()) {
            i2 = this.mReferenceManager.bulkInsert(getRefCloudUri(), (ContentValues[]) this.mCloudRestore.toArray(new ContentValues[0]));
            this.mCloudRestore.clear();
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        String str = this.TAG;
        Log.d(str, "bulk insert time [" + (currentTimeMillis2 - currentTimeMillis) + "][" + size + "][" + size + "][" + i2 + "][" + z + "]");
        return i2;
    }

    public String getColumnIdForMergeLC() {
        return BundleKey.GROUP_ID;
    }

    public HashMap<String, File> getInternalTrash(String str) {
        HashMap<String, File> hashMap = new HashMap<>();
        String str2 = FileUtils.EXTERNAL_STORAGE_DIR;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.EXTERNAL_STORAGE_DIR);
        String str3 = File.separator;
        C0086a.z(sb2, str3, "Android", str3, str);
        hashMap.put(str2, new File(C0212a.p(sb2, str3, "com.sec.android.gallery3d")));
        return hashMap;
    }

    public String getOriginFileHash(TrashBaseItem trashBaseItem) {
        Cursor query;
        Throwable th;
        long fileId = trashBaseItem.getFileId();
        String str = null;
        try {
            query = this.mReferenceManager.query(MediaUri.getInstance().getSecMediaUri(), new String[]{"original_file_hash"}, "_id = " + fileId, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    str = query.getString(0);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("getOriginHash failed e="), this.TAG);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
        throw th;
    }

    public void putExpiredTimeValue(NoneDestructionOperationType noneDestructionOperationType, ContentValues contentValues) {
        if (noneDestructionOperationType == NoneDestructionOperationType.MOVE_TO_TRASH) {
            contentValues.put("date_expires", Long.valueOf((System.currentTimeMillis() / 1000) + TimeUnit.DAYS.toSeconds(31)));
        } else if (noneDestructionOperationType == NoneDestructionOperationType.EMPTY) {
            contentValues.put("date_expires", Long.valueOf(System.currentTimeMillis() / 1000));
        } else if (noneDestructionOperationType == NoneDestructionOperationType.RESTORE) {
            contentValues.putNull("date_expires");
        }
    }

    public void setContentValuesDefaultForLocal(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        contentValues.put("mime_type", getNullIfEmpty(trashRestoreItem.getMimeType()));
        contentValues.put(IParameterKey.DATE_TAKEN, Long.valueOf(trashRestoreItem.getDateTaken()));
        contentValues.put("width", Integer.valueOf(trashRestoreItem.getWidth()));
        contentValues.put("height", Integer.valueOf(trashRestoreItem.getHeight()));
        contentValues.put("is_drm", Boolean.valueOf(trashRestoreItem.isDrm()));
        contentValues.put("is_favorite", Boolean.valueOf(trashRestoreItem.isFavourite()));
        contentValues.put("orientation", Integer.valueOf(trashRestoreItem.getOrientation()));
        contentValues.put("duration", Integer.valueOf(trashRestoreItem.getFileDuration()));
        contentValues.put(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, getNullIfEmpty(trashRestoreItem.getResolution()));
        contentValues.put("relative_path", trashRestoreItem.getRelativePath());
        if (trashRestoreItem.getNewPath() != null && !trashRestoreItem.getNewPath().isEmpty()) {
            contentValues.put("_display_name", new File(trashRestoreItem.getNewPath()).getName());
        }
    }

    public String tag() {
        return "MpRTrashFactory";
    }

    public void updateNoneDestructionDB(String str, NoneDestructionOperationType noneDestructionOperationType, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (noneDestructionOperationType == NoneDestructionOperationType.MOVE_TO_TRASH || !TrashProviderFactory.getInstance(AppResources.getAppContext()).isSameOriginFileHashExists(str) || z) {
            addOriginFileHashList(str);
            int size = this.mNoneDestructionUpdate.size();
            if ((size >= 33 || z) && !this.mNoneDestructionUpdate.isEmpty()) {
                ContentValues contentValues = new ContentValues();
                putExpiredTimeValue(noneDestructionOperationType, contentValues);
                if (!contentValues.isEmpty()) {
                    int update = this.mReferenceManager.update(NONE_DESTRUCTION_URI, contentValues, getUpdateNoneDestructionDBWhere(size), (String[]) this.mNoneDestructionUpdate.toArray(new String[0]));
                    String str2 = this.TAG;
                    StringBuilder sb2 = new StringBuilder("bulk update time [");
                    sb2.append(System.currentTimeMillis() - currentTimeMillis);
                    sb2.append("][");
                    sb2.append(size);
                    sb2.append("][");
                    sb2.append(update);
                    sb2.append("][");
                    sb2.append(noneDestructionOperationType);
                    j.y(sb2, "]", str2);
                } else {
                    String str3 = this.TAG;
                    Log.d(str3, "non-destruction: type [" + noneDestructionOperationType + "] value is empty");
                }
                this.mNoneDestructionUpdate.clear();
            }
        }
    }

    public boolean useCloudThumbMove() {
        return false;
    }

    public boolean useMediaScanForRestore() {
        return supportTrash();
    }
}
