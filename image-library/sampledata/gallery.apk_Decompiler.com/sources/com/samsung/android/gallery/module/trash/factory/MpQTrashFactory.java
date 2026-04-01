package com.samsung.android.gallery.module.trash.factory;

import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.group.BundleKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpQTrashFactory extends MpTrashFactory {
    private static final boolean SUPPORT_CHANGE_BEST_ITEM = Features.isEnabled(Features.SUPPORT_CHANGE_BEST_ITEM);
    protected final ArrayList<String> mCloudDelete = new ArrayList<>();
    protected int mRequestCount = 0;
    private final ArrayList<ContentValues> mSdCardRestore = new ArrayList<>();

    /* renamed from: com.samsung.android.gallery.module.trash.factory.MpQTrashFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.database.dbtype.StorageType[] r0 = com.samsung.android.gallery.database.dbtype.StorageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType = r0
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Local     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.LocalCloud     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Cloud     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.factory.MpQTrashFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public MpQTrashFactory(Context context) {
        super(context);
    }

    private void addAdditionalValuesForLC(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        contentValues.put(IParameterKey.SIZE, Long.valueOf(trashRestoreItem.getFileSize()));
        String str = this.TAG;
        Log.d(str, "additional values added : " + contentValues);
    }

    private boolean checkDeleteWithPath(TrashDeleteItem trashDeleteItem) {
        if (!trashDeleteItem.isLocal() || trashDeleteItem.getMediaId() > 0) {
            return false;
        }
        return true;
    }

    private long getFileIdFromUri(Uri uri) {
        if (uri == null) {
            Log.e(this.TAG, "invalid uri, null");
            return 0;
        }
        long j2 = UnsafeCast.toLong(uri.getLastPathSegment(), 0);
        if (j2 != 0) {
            return j2;
        }
        String str = this.TAG;
        Log.e(str, "invalid uri. " + uri);
        return 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bulkInsert$1(AtomicInteger atomicInteger, String str, List list) {
        atomicInteger.addAndGet(this.mReferenceManager.bulkInsert(MediaUri.getInstance().getFilesUri(str), (ContentValues[]) list.toArray(new ContentValues[0])));
    }

    public void addAdditionalValues(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        if (trashRestoreItem.getGroupType() > 0) {
            contentValues.put(BundleKey.GROUP_TYPE, Integer.valueOf(trashRestoreItem.getGroupType()));
        }
        if (!SUPPORT_CHANGE_BEST_ITEM) {
            contentValues.put("best_image", Integer.valueOf(trashRestoreItem.getBestImage()));
        }
        contentValues.put(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME, Long.valueOf(trashRestoreItem.getDateTaken()));
        contentValues.put("relative_path", trashRestoreItem.getRelativePath());
    }

    public int[] bulkDelete(TrashDeleteItem trashDeleteItem, boolean z) {
        int i2;
        int i7;
        int i8;
        boolean z3 = z;
        long currentTimeMillis = System.currentTimeMillis();
        putIds(trashDeleteItem);
        int size = this.mCloudDelete.size();
        int size2 = this.mLocalDelete.size();
        int size3 = this.mLocalDeletePath.size();
        int i10 = this.mRequestCount + (z3 ^ true ? 1 : 0);
        this.mRequestCount = i10;
        int i11 = 0;
        if (i10 >= 33 || z3) {
            if (!this.mCloudDelete.isEmpty()) {
                i7 = delete(getRefCloudUri(), getDeleteWhere(size), (String[]) this.mCloudDelete.toArray(new String[0]));
                this.mCloudDelete.clear();
            } else {
                i7 = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (!useMediaDbDeletion() || (this.mLocalDelete.isEmpty() && this.mLocalDeletePath.isEmpty())) {
                i8 = 0;
                i2 = 0;
            } else {
                if (!this.mLocalDelete.isEmpty()) {
                    i8 = delete(getRefFilesUri(), getDeleteWhere(size2), (String[]) this.mLocalDelete.toArray(new String[0]));
                    this.mLocalDelete.clear();
                } else {
                    i8 = 0;
                }
                if (!this.mLocalDeletePath.isEmpty()) {
                    i2 = delete(getRefFilesUri(), getDeleteWherePath(size3), (String[]) this.mLocalDeletePath.toArray(new String[0]));
                    this.mLocalDeletePath.clear();
                } else {
                    i2 = 0;
                }
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("bulk delete time [");
            sb2.append(currentTimeMillis2 - currentTimeMillis);
            sb2.append("][");
            sb2.append(currentTimeMillis3 - currentTimeMillis2);
            sb2.append("][");
            j.x(sb2, this.mRequestCount, "][", size, "][");
            j.x(sb2, i7, "][", size2, "][");
            j.x(sb2, i8, "][", size3, "][");
            sb2.append(i2);
            sb2.append("][");
            sb2.append(z3);
            sb2.append("]");
            Log.d(str, sb2.toString());
            this.mRequestCount = 0;
            i11 = i8;
        } else {
            i7 = 0;
            i2 = 0;
        }
        return new int[]{i11 + i2, i7};
    }

    public int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z) {
        StorageType storageType;
        int i2;
        int i7;
        int i8;
        long j2;
        TrashRestoreItem trashRestoreItem2 = trashRestoreItem;
        boolean z3 = z;
        long currentTimeMillis = System.currentTimeMillis();
        if (trashRestoreItem2 == null) {
            storageType = StorageType.None;
        } else {
            storageType = trashRestoreItem2.getStorageType();
        }
        if (storageType == StorageType.LocalCloud) {
            return singleInsert(trashRestoreItem);
        }
        putValues(trashRestoreItem2, storageType);
        int size = this.mCloudRestore.size();
        int size2 = this.mLocalRestore.size();
        int size3 = this.mSdCardRestore.size();
        int i10 = size + size2 + size3;
        int i11 = 0;
        if (i10 >= 100 || z3) {
            if (!this.mCloudRestore.isEmpty()) {
                i8 = this.mReferenceManager.bulkInsert(getRefCloudUri(), (ContentValues[]) this.mCloudRestore.toArray(new ContentValues[0]));
                this.mCloudRestore.clear();
            } else {
                i8 = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (!this.mLocalRestore.isEmpty()) {
                i2 = this.mReferenceManager.bulkInsert(getRefFilesUri(), (ContentValues[]) this.mLocalRestore.toArray(new ContentValues[0]));
                this.mLocalRestore.clear();
            } else {
                i2 = 0;
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            if (!this.mSdCardRestore.isEmpty()) {
                AtomicInteger atomicInteger = new AtomicInteger(0);
                j2 = currentTimeMillis;
                ((Map) this.mSdCardRestore.stream().collect(Collectors.groupingBy(new a(4)))).forEach(new A9.a(23, this, atomicInteger));
                i11 = atomicInteger.get();
                this.mSdCardRestore.clear();
            } else {
                j2 = currentTimeMillis;
            }
            long currentTimeMillis4 = System.currentTimeMillis();
            Log.d(this.TAG, "bulk insert time [" + (currentTimeMillis2 - j2) + "][" + (currentTimeMillis3 - currentTimeMillis2) + "][" + (currentTimeMillis4 - currentTimeMillis3) + "][" + i10 + "][" + size + "][" + i8 + "][" + size2 + "][" + i2 + "][" + size3 + "][" + i11 + "][" + z3 + "]");
            i7 = i11;
            i11 = i8;
        } else {
            i7 = 0;
            i2 = 0;
        }
        return i11 + i2 + i7;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.mReferenceManager.delete(uri, str, strArr);
    }

    public String getColumnGroupId() {
        return "burst_group_id";
    }

    public String getColumnIdForMergeLC() {
        return "picasa_id";
    }

    public Uri getDeleteUri() {
        return getRefFilesUri();
    }

    public Uri getRefCloudUri() {
        return MediaUri.getInstance().getSecCloudUri();
    }

    public long getTargetId(FileItemInterface fileItemInterface) {
        return fileItemInterface.getMediaId();
    }

    public boolean needToPutStorageId() {
        return false;
    }

    public void putIds(TrashDeleteItem trashDeleteItem) {
        if (trashDeleteItem != null) {
            StorageType storageType = trashDeleteItem.getStorageType();
            String valueOf = String.valueOf(trashDeleteItem.getFileId());
            String valueOf2 = String.valueOf(trashDeleteItem.getMediaId());
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType[storageType.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (checkDeleteWithPath(trashDeleteItem)) {
                        this.mLocalDeletePath.add(trashDeleteItem.getPath());
                    } else {
                        this.mLocalDelete.add(valueOf2);
                    }
                    this.mCloudDelete.add(valueOf);
                } else if (i2 == 3) {
                    this.mCloudDelete.add(valueOf);
                }
            } else if (checkDeleteWithPath(trashDeleteItem)) {
                this.mLocalDeletePath.add(trashDeleteItem.getPath());
            } else {
                this.mLocalDelete.add(valueOf2);
            }
        }
    }

    public void putValues(TrashRestoreItem trashRestoreItem, StorageType storageType) {
        if (trashRestoreItem != null) {
            ContentValues restoreContentValues = getRestoreContentValues(trashRestoreItem);
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType[storageType.ordinal()];
            if (i2 != 1) {
                if (i2 == 3) {
                    this.mCloudRestore.add(restoreContentValues);
                }
            } else if (!trashRestoreItem.isPrimaryItem()) {
                this.mSdCardRestore.add(restoreContentValues);
            } else {
                this.mLocalRestore.add(restoreContentValues);
            }
        }
    }

    public int singleInsert(TrashRestoreItem trashRestoreItem) {
        Uri uri;
        if (trashRestoreItem == null) {
            return 0;
        }
        TrashRestoreItem trashRestoreItem2 = new TrashRestoreItem((FileItemInterface) trashRestoreItem);
        trashRestoreItem2.setNewPath(trashRestoreItem.getNewPath());
        trashRestoreItem2.setStorageType(StorageType.Cloud);
        ContentValues restoreContentValues = getRestoreContentValues(trashRestoreItem2);
        addAdditionalValuesForLC(restoreContentValues, trashRestoreItem2);
        Uri insert = this.mReferenceManager.insert(getRefCloudUri(), restoreContentValues);
        long fileIdFromUri = getFileIdFromUri(insert);
        if (fileIdFromUri != 0) {
            trashRestoreItem2.setStorageType(StorageType.Local);
            ContentValues restoreContentValuesForLocal = getRestoreContentValuesForLocal(trashRestoreItem2);
            restoreContentValuesForLocal.put(getColumnIdForMergeLC(), Long.valueOf(fileIdFromUri));
            uri = this.mReferenceManager.insert(getRefFilesUri(), restoreContentValuesForLocal);
            fileIdFromUri = getFileIdFromUri(uri);
        } else {
            uri = null;
        }
        String str = this.TAG;
        Log.d(str, "[" + insert + "][" + uri + "]");
        if (fileIdFromUri == 0) {
            return 0;
        }
        return 1;
    }

    public String tag() {
        return "MpQTrashFactory";
    }
}
