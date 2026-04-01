package com.samsung.android.gallery.module.trash.factory;

import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import com.samsung.scsp.media.Media;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpTrashFactory extends AbsTrashFactory {
    final ArrayList<ContentValues> mCloudRestore = new ArrayList<>();
    final ArrayList<ContentValues> mLocalRestore = new ArrayList<>();

    /* renamed from: com.samsung.android.gallery.module.trash.factory.MpTrashFactory$1  reason: invalid class name */
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
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Cloud     // Catch:{ NoSuchFieldError -> 0x0012 }
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
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Local     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.factory.MpTrashFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public MpTrashFactory(Context context) {
        super(context);
    }

    private String getCloudHash(Media media, TrashRestoreItem trashRestoreItem) {
        String str;
        if (media != null) {
            str = media.hash;
        } else {
            str = null;
        }
        if (str == null) {
            return trashRestoreItem.getCloudHash();
        }
        return str;
    }

    private int getCloudRevision(Media media, TrashRestoreItem trashRestoreItem) {
        Integer num;
        if (media != null) {
            num = media.revision;
        } else {
            num = null;
        }
        if (num != null) {
            return num.intValue();
        }
        return trashRestoreItem.getCloudRevision();
    }

    private long getCloudTimestamp(Media media, TrashRestoreItem trashRestoreItem) {
        Long l;
        if (media != null) {
            l = media.clientTimestamp;
        } else {
            l = null;
        }
        if (l != null) {
            return l.longValue();
        }
        return trashRestoreItem.getCloudTimestamp();
    }

    private int getIsCloud(StorageType storageType) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType[storageType.ordinal()];
        if (i2 == 1) {
            return StorageType.Cloud.toInt();
        }
        if (i2 != 2) {
            return StorageType.Local.toInt();
        }
        return StorageType.LocalCloud.toInt();
    }

    private Uri getRefCloudDeletedUri() {
        return MediaUri.getInstance().getScloudDeleted();
    }

    private Uri getRefSecMediaUri() {
        return MediaUri.getInstance().getSecMediaUri();
    }

    private void putValues(TrashRestoreItem trashRestoreItem) {
        if (trashRestoreItem != null) {
            ContentValues restoreContentValues = getRestoreContentValues(trashRestoreItem);
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType[trashRestoreItem.getStorageType().ordinal()];
            if (i2 == 1) {
                this.mCloudRestore.add(restoreContentValues);
            } else if (i2 == 2 || i2 == 3) {
                this.mLocalRestore.add(restoreContentValues);
            }
        }
    }

    private void setContentValuesCloud(ContentValues contentValues, TrashRestoreItem trashRestoreItem, StorageType storageType) {
        if (StorageType.Cloud.equals(storageType) || StorageType.LocalCloud.equals(storageType)) {
            Media mediaResult = trashRestoreItem.getMediaResult();
            contentValues.put("cloud_server_id", trashRestoreItem.getCloudServerId());
            contentValues.put("cloud_server_path", getNullIfEmpty(trashRestoreItem.getCloudServerPath()));
            contentValues.put("cloud_thumb_path", trashRestoreItem.getCloudThumbPath());
            contentValues.put("cloud_original_size", Long.valueOf(trashRestoreItem.getCloudOriginalSize()));
            contentValues.put("cloud_revision", Integer.valueOf(getCloudRevision(mediaResult, trashRestoreItem)));
            contentValues.put("timestamp", Long.valueOf(getCloudTimestamp(mediaResult, trashRestoreItem)));
            contentValues.put("hash", getNullIfEmpty(getCloudHash(mediaResult, trashRestoreItem)));
        }
    }

    private void setContentValuesCloudOnly(ContentValues contentValues, TrashRestoreItem trashRestoreItem, StorageType storageType) {
        if (StorageType.Cloud.equals(storageType)) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            contentValues.put("date_added", Long.valueOf(currentTimeMillis));
            contentValues.put(IParameterKey.DATE_MODIFIED, Long.valueOf(currentTimeMillis));
            contentValues.put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(trashRestoreItem.getMediaType().toInt()));
            if (needToPutStorageId()) {
                contentValues.put("storage_id", 65537);
            }
            String cloudServerPath = trashRestoreItem.getCloudServerPath();
            if (!TextUtils.isEmpty(cloudServerPath)) {
                SecureFile secureFile = new SecureFile(cloudServerPath);
                File parentFile = secureFile.getParentFile();
                if (parentFile != null) {
                    contentValues.put("bucket_id", Integer.valueOf(parentFile.getAbsolutePath().toLowerCase().hashCode()));
                    contentValues.put("bucket_display_name", parentFile.getName());
                }
                String name = secureFile.getName();
                contentValues.put("_display_name", name);
                contentValues.put("title", FileUtils.getBaseName(name));
            }
        }
    }

    private void setContentValuesDefault(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        contentValues.put("mime_type", getNullIfEmpty(trashRestoreItem.getMimeType()));
        contentValues.put(IParameterKey.DATE_TAKEN, Long.valueOf(trashRestoreItem.getDateTaken()));
        contentValues.put("latitude", Double.valueOf(trashRestoreItem.getLatitude()));
        contentValues.put("longitude", Double.valueOf(trashRestoreItem.getLongitude()));
        contentValues.put("width", Integer.valueOf(trashRestoreItem.getWidth()));
        contentValues.put("height", Integer.valueOf(trashRestoreItem.getHeight()));
        contentValues.put("is_drm", Boolean.valueOf(trashRestoreItem.isDrm()));
        contentValues.put("is_favorite", Boolean.valueOf(trashRestoreItem.isFavourite()));
        contentValues.put("is_cloud", Integer.valueOf(getIsCloud(trashRestoreItem.getStorageType())));
        contentValues.put("sef_file_type", Integer.valueOf(trashRestoreItem.getSefFileType()));
        contentValues.put("sef_file_types", trashRestoreItem.getSefFileTypes());
        contentValues.put("sef_file_sub_type", Integer.valueOf(trashRestoreItem.getSefFileSubType()));
        contentValues.put("orientation", Integer.valueOf(trashRestoreItem.getOrientation()));
        contentValues.put(getColumnGroupId(), Long.valueOf(trashRestoreItem.getGroupMediaId()));
        contentValues.put("captured_url", getNullIfEmpty(DetailsData.of(trashRestoreItem).capturedUrl));
        contentValues.put("captured_app", getNullIfEmpty(DetailsData.of(trashRestoreItem).capturedApp));
        contentValues.put("duration", Integer.valueOf(trashRestoreItem.getFileDuration()));
        contentValues.put(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, getNullIfEmpty(trashRestoreItem.getResolution()));
        contentValues.put("is_360_video", Boolean.valueOf(trashRestoreItem.is360Video()));
        contentValues.put("is_hdr10_video", Boolean.valueOf(trashRestoreItem.isHdr10Video()));
        contentValues.put("recording_mode", Integer.valueOf(trashRestoreItem.getRecordingMode()));
        contentValues.put("recordingtype", Integer.valueOf(trashRestoreItem.getRecordingType()));
        addAdditionalValues(contentValues, trashRestoreItem);
    }

    private void setContentValuesLocal(ContentValues contentValues, TrashRestoreItem trashRestoreItem, StorageType storageType) {
        if (StorageType.Local.equals(storageType) || StorageType.LocalCloud.equals(storageType)) {
            contentValues.put("relative_path", FileUtils.getRelativePath(trashRestoreItem.getNewPath()));
            contentValues.put("volume_name", FileUtils.getVolumeName(trashRestoreItem.getNewPath()));
            contentValues.put("date_added", 0L);
            contentValues.put(IParameterKey.DATE_MODIFIED, 0L);
        }
    }

    public void addAdditionalValues(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        contentValues.put("best_image", Integer.valueOf(trashRestoreItem.getBestImage()));
    }

    public int[] bulkDelete(TrashDeleteItem trashDeleteItem, boolean z) {
        int i2;
        int[] iArr = new int[2];
        long currentTimeMillis = System.currentTimeMillis();
        putIds(trashDeleteItem);
        int size = this.mLocalDelete.size();
        if ((size >= 33 || z) && !this.mLocalDelete.isEmpty()) {
            i2 = this.mReferenceManager.delete(getRefSecMediaUri(), getDeleteWhere(size), (String[]) this.mLocalDelete.toArray(new String[0]));
            Log.d(this.TAG, "bulk delete time [" + (System.currentTimeMillis() - currentTimeMillis) + "][" + size + "][" + i2 + "]");
            this.mLocalDelete.clear();
        } else {
            i2 = 0;
        }
        iArr[0] = i2;
        return iArr;
    }

    public int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z) {
        int i2;
        int i7;
        long currentTimeMillis = System.currentTimeMillis();
        putValues(trashRestoreItem);
        int size = this.mCloudRestore.size();
        int size2 = this.mLocalRestore.size();
        int i8 = size + size2;
        int i10 = 0;
        if (i8 >= 100 || z) {
            if (!this.mCloudRestore.isEmpty()) {
                i7 = this.mReferenceManager.bulkInsert(getRefSecMediaUri(), (ContentValues[]) this.mCloudRestore.toArray(new ContentValues[0]));
                this.mCloudRestore.clear();
            } else {
                i7 = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (!this.mLocalRestore.isEmpty()) {
                i10 = this.mReferenceManager.bulkInsert(getRefFilesUri(), (ContentValues[]) this.mLocalRestore.toArray(new ContentValues[0]));
                this.mLocalRestore.clear();
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            Log.d(this.TAG, "bulk insert time [" + (currentTimeMillis2 - currentTimeMillis) + "][" + (currentTimeMillis3 - currentTimeMillis2) + "][" + i8 + "][" + size + "][" + i7 + "][" + size2 + "][" + i10 + "][" + z + "]");
            i2 = i10;
            i10 = i7;
        } else {
            i2 = 0;
        }
        return i10 + i2;
    }

    public boolean deleteCloudFromRef(FileItemInterface fileItemInterface) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.mReferenceManager.delete(getRefSecMediaUri(), "_id =? ", new String[]{String.valueOf(fileItemInterface.getFileId())}) > 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("delete from ref db ["));
        }
    }

    public boolean deleteCloudRecordDeleteTable(String str) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.mReferenceManager.delete(getRefCloudDeletedUri(), "cloud_server_id =? ", new String[]{str}) > 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("delete from cloud delete table ["));
        }
    }

    public String getCloudThumbRootPath() {
        return "/data/sec/cloud/0/thumbnail/";
    }

    public String getColumnGroupId() {
        return BundleKey.GROUP_ID;
    }

    public Uri getDeleteUri() {
        return getRefSecMediaUri();
    }

    public String getNullIfEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public ContentValues getRestoreContentValues(TrashRestoreItem trashRestoreItem) {
        ContentValues contentValues = new ContentValues();
        setContentValuesDefault(contentValues, trashRestoreItem);
        StorageType storageType = trashRestoreItem.getStorageType();
        setContentValuesLocal(contentValues, trashRestoreItem, storageType);
        setContentValuesCloud(contentValues, trashRestoreItem, storageType);
        setContentValuesCloudOnly(contentValues, trashRestoreItem, storageType);
        return contentValues;
    }

    public ContentValues getRestoreContentValuesForLocal(TrashRestoreItem trashRestoreItem) {
        ContentValues contentValues = new ContentValues();
        setContentValuesDefaultForLocal(contentValues, trashRestoreItem);
        StorageType storageType = trashRestoreItem.getStorageType();
        setContentValuesLocal(contentValues, trashRestoreItem, storageType);
        setContentValuesCloud(contentValues, trashRestoreItem, storageType);
        setContentValuesCloudOnly(contentValues, trashRestoreItem, storageType);
        return contentValues;
    }

    public long getTargetId(FileItemInterface fileItemInterface) {
        return fileItemInterface.getFileId();
    }

    public boolean needToPutStorageId() {
        return true;
    }

    public void setContentValuesDefaultForLocal(ContentValues contentValues, TrashRestoreItem trashRestoreItem) {
        setContentValuesDefault(contentValues, trashRestoreItem);
    }

    public boolean supportTrash() {
        return PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);
    }

    public String tag() {
        return "MpTrashFactory";
    }

    public boolean useStoreApi() {
        return false;
    }
}
