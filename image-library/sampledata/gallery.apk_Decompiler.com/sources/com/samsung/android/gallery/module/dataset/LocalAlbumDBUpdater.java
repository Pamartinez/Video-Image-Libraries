package com.samsung.android.gallery.module.dataset;

import A.a;
import A8.C0545b;
import Fa.C0571z;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.cache.Crc;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalAlbumDBUpdater {
    private static final boolean IS_QOS = SdkConfig.atLeast(SdkConfig.GED.Q);
    private static final boolean SUPPORT_MX_ALBUMS = PreferenceFeatures.OneUi5x.MX_ALBUMS;
    private final String TAG = getClass().getSimpleName();
    private final String mAuthority = new LocalAlbumsApi().getTableUri().getAuthority();
    private final Blackboard mBlackboard;
    private String mLocalPath;

    public LocalAlbumDBUpdater(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private int addAndUpdateIfOverflowed(Context context, ArrayList<ContentProviderOperation> arrayList, ContentProviderOperation contentProviderOperation) {
        arrayList.add(contentProviderOperation);
        if (arrayList.size() >= 500) {
            return updateInBatch(context, arrayList);
        }
        return 0;
    }

    private void addToLocalDb(Context context, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        Blackboard blackboard = getBlackboard();
        BlackboardUtils.collectExternalDataChangedEvent(blackboard, true);
        blackboard.publish("local_db_updating", Boolean.TRUE);
        upsertLocalDatabase(context, arrayList, arrayList2);
        blackboard.publish("local_db_updating", Boolean.FALSE);
        BlackboardUtils.collectExternalDataChangedEvent(blackboard, false);
    }

    private boolean compare(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!mediaItem.isEmptyAlbum() || !mediaItem2.isEmptyAlbum()) {
            if (isAlbumHide(mediaItem) == isAlbumHide(mediaItem2) && mediaItem.getFileId() == mediaItem2.getFileId() && mediaItem.getStorageType() == mediaItem2.getStorageType() && mediaItem.getMediaType() == mediaItem2.getMediaType() && mediaItem.getOrientation() == mediaItem2.getOrientation() && mediaItem.getAlbumOrder() == mediaItem2.getAlbumOrder() && mediaItem.getCount() == mediaItem2.getCount() && mediaItem.getDisplayName().equals(mediaItem2.getDisplayName()) && mediaItem.getAlbumID() == mediaItem2.getAlbumID() && RectUtils.equalsRectF(mediaItem.getCropRect(), mediaItem2.getCropRect()) && mediaItem.getWidthInDB() == mediaItem2.getWidthInDB() && mediaItem.getHeightInDB() == mediaItem2.getHeightInDB() && mediaItem.getDateModified() == mediaItem2.getDateModified() && mediaItem.getDateTaken() == mediaItem2.getDateTaken() && mediaItem.getSefFileType() == mediaItem2.getSefFileType() && mediaItem.isDrm() == mediaItem2.isDrm() && mediaItem.getFileDuration() == mediaItem2.getFileDuration() && mediaItem.getFileSize() == mediaItem2.getFileSize() && mediaItem.getCloudOriginalSize() == mediaItem2.getCloudOriginalSize() && mediaItem.isAlbumLvFirst() == mediaItem2.isAlbumLvFirst()) {
                return true;
            }
            return false;
        } else if (isAlbumHide(mediaItem) == isAlbumHide(mediaItem2) && mediaItem.getAlbumOrder() == mediaItem2.getAlbumOrder() && mediaItem.getDisplayName().equals(mediaItem2.getDisplayName()) && mediaItem.isAlbumLvFirst() == mediaItem2.isAlbumLvFirst()) {
            return true;
        } else {
            return false;
        }
    }

    private ContentProviderOperation createDeleteOp(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
        return new LocalAlbumsApi().getDeleteOperation(arrayList);
    }

    private ContentProviderOperation createFolderDeleteOp(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
        return new LocalAlbumsApi().getDeleteOperation(arrayList);
    }

    private ContentProviderOperation createFolderInsertOp(MediaItem mediaItem, MediaItem mediaItem2) {
        return new LocalAlbumsApi().getInsertOperation(getFolderContentValues(mediaItem, mediaItem2));
    }

    private ContentProviderOperation createFolderUpdateOp(MediaItem mediaItem, MediaItem mediaItem2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem2.getAlbumID()));
        return new LocalAlbumsApi().getUpdateOperation(getFolderContentValues(mediaItem, mediaItem2), arrayList);
    }

    private ContentProviderOperation createInsertOp(MediaItem mediaItem, MediaItem mediaItem2) {
        return new LocalAlbumsApi().getInsertOperation(getContentValues(mediaItem, mediaItem2));
    }

    private ContentProviderOperation createUpdateOp(MediaItem mediaItem, MediaItem mediaItem2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem2.getAlbumID()));
        return new LocalAlbumsApi().getUpdateOperation(getContentValues(mediaItem, mediaItem2), arrayList);
    }

    private boolean differentiate(String str, String str2) {
        if (str == null || str.equals(str2)) {
            return false;
        }
        return true;
    }

    private boolean equalsFolder(MediaItem mediaItem, MediaItem mediaItem2, MediaItem mediaItem3, MediaItem mediaItem4) {
        if (mediaItem3 != null && mediaItem4 != null) {
            String displayName = mediaItem3.getDisplayName();
            String displayName2 = mediaItem4.getDisplayName();
            if (displayName == null || !displayName.equals(displayName2) || mediaItem3.getAlbumID() != mediaItem4.getAlbumID() || !equalsFolder(mediaItem, mediaItem2)) {
                return false;
            }
            return true;
        } else if (mediaItem3 == null && mediaItem4 == null) {
            return equalsFolder(mediaItem, mediaItem2);
        } else {
            return false;
        }
    }

    private boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2, MediaItem mediaItem3, MediaItem mediaItem4) {
        if (mediaItem3 != null && mediaItem4 != null) {
            String displayName = mediaItem3.getDisplayName();
            String displayName2 = mediaItem4.getDisplayName();
            if (displayName == null || !displayName.equals(displayName2) || mediaItem3.getAlbumID() != mediaItem4.getAlbumID() || !equalsItem(mediaItem, mediaItem2)) {
                return false;
            }
            return true;
        } else if (mediaItem3 == null && mediaItem4 == null) {
            return equalsItem(mediaItem, mediaItem2);
        } else {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchDataFromList(java.util.List<com.samsung.android.gallery.module.data.MediaItem> r8, java.util.HashMap<java.lang.Integer, com.samsung.android.gallery.module.data.MediaItem> r9, java.util.HashMap<java.lang.Integer, com.samsung.android.gallery.module.data.MediaItem> r10, java.util.HashMap<java.lang.Integer, com.samsung.android.gallery.module.data.MediaItem> r11, com.samsung.android.gallery.module.data.MediaItem r12) {
        /*
            r7 = this;
            java.util.Iterator r8 = r8.iterator()
        L_0x0004:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x005d
            java.lang.Object r0 = r8.next()
            r6 = r0
            com.samsung.android.gallery.module.data.MediaItem r6 = (com.samsung.android.gallery.module.data.MediaItem) r6
            boolean r0 = r6.isFolder()
            if (r0 != 0) goto L_0x002e
            boolean r0 = r6.isMergedAlbum()
            if (r0 == 0) goto L_0x001e
            goto L_0x002e
        L_0x001e:
            int r0 = r6.getAlbumID()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9.put(r0, r6)
        L_0x0029:
            r1 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            goto L_0x004b
        L_0x002e:
            int r0 = r6.getAlbumID()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r11.put(r0, r6)
            com.samsung.android.gallery.module.data.MediaItem[] r0 = r6.getItemsInFolder()
            int r1 = r0.length
            if (r1 <= 0) goto L_0x0029
            java.util.List r2 = java.util.Arrays.asList(r0)
            r1 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            r1.fetchDataFromList(r2, r3, r4, r5, r6)
        L_0x004b:
            if (r12 == 0) goto L_0x0058
            int r7 = r6.getAlbumID()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r4.put(r7, r12)
        L_0x0058:
            r7 = r1
            r9 = r3
            r10 = r4
            r11 = r5
            goto L_0x0004
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.LocalAlbumDBUpdater.fetchDataFromList(java.util.List, java.util.HashMap, java.util.HashMap, java.util.HashMap, com.samsung.android.gallery.module.data.MediaItem):void");
    }

    private ContentValues getContentValues(MediaItem mediaItem, MediaItem mediaItem2) {
        int i2;
        if (mediaItem2.isEmptyAlbum()) {
            return getContentValuesForEmptyAlbum(mediaItem, mediaItem2);
        }
        String path = mediaItem2.getPath();
        ContentValues contentValues = new ContentValues();
        if (mediaItem != null) {
            contentValues.put("folder_id", Integer.valueOf(mediaItem.getFolderID()));
            contentValues.put("folder_name", mediaItem.getFolderName());
        } else {
            contentValues.putNull("folder_id");
            contentValues.putNull("folder_name");
        }
        if (mediaItem2.isCustomCover()) {
            contentValues.put("cover_path", mediaItem2.getAlbumCover());
            contentValues.putNull("default_cover_path");
        } else {
            contentValues.put("default_cover_path", path);
            contentValues.putNull("cover_path");
        }
        contentValues.put("cover_rect", AlbumDataHelper.getStringForCropRect(RectUtils.toString(mediaItem2.getCropRect()), mediaItem2));
        contentValues.put("album_order", Long.valueOf(mediaItem2.getAlbumOrder()));
        contentValues.put("__Title", mediaItem2.getTitle());
        contentValues.put("album_count", Integer.valueOf(mediaItem2.getCount()));
        contentValues.put("__bucketID", Integer.valueOf(mediaItem2.getAlbumID()));
        if (isAlbumHide(mediaItem2)) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        contentValues.put("__ishide", Integer.valueOf(i2));
        contentValues.put("__sefFileType", Integer.valueOf(mediaItem2.getSefFileType()));
        contentValues.put("__isDrm", Integer.valueOf(mediaItem2.isDrm() ? 1 : 0));
        contentValues.put("__dateModified", Long.valueOf(mediaItem2.getDateModified()));
        if (SUPPORT_MX_ALBUMS) {
            contentValues.put("__albumLevel", Integer.valueOf(mediaItem2.isAlbumLvFirst() ? 1 : 0));
            contentValues.put("__albumType", Integer.valueOf(mediaItem2.getAlbumType().toInt()));
        }
        try {
            if (!PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER || (path != null && !path.startsWith(this.mLocalPath))) {
                contentValues.put("__absPath", FileUtils.getDirectoryFromPath(path, false));
            }
        } catch (Exception unused) {
            contentValues.put("__absPath", "");
            Log.e(this.TAG, "album Path is null");
        }
        if (mediaItem2.getVolumeName() != null) {
            contentValues.put("__volumeName", mediaItem2.getVolumeName().toLowerCase());
        } else {
            contentValues.put("__volumeName", FileUtils.getVolumeName(path));
        }
        return contentValues;
    }

    private ContentValues getContentValuesForEmptyAlbum(MediaItem mediaItem, MediaItem mediaItem2) {
        int i2;
        ContentValues contentValues = new ContentValues();
        if (mediaItem != null) {
            contentValues.put("folder_id", Integer.valueOf(mediaItem.getFolderID()));
            contentValues.put("folder_name", mediaItem.getFolderName());
        } else {
            contentValues.putNull("folder_id");
            contentValues.putNull("folder_name");
        }
        contentValues.put("album_order", Long.valueOf(mediaItem2.getAlbumOrder()));
        contentValues.put("__Title", mediaItem2.getTitle());
        contentValues.put("album_count", Integer.valueOf(mediaItem2.getCount()));
        contentValues.put("__bucketID", Integer.valueOf(mediaItem2.getAlbumID()));
        if (isAlbumHide(mediaItem2)) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        contentValues.put("__ishide", Integer.valueOf(i2));
        contentValues.put("__dateModified", Long.valueOf(mediaItem2.getDateModified()));
        if (SUPPORT_MX_ALBUMS) {
            contentValues.put("__albumLevel", Integer.valueOf(mediaItem2.isAlbumLvFirst() ? 1 : 0));
            contentValues.put("__albumType", Integer.valueOf(mediaItem2.getAlbumType().toInt()));
        }
        return contentValues;
    }

    private ContentValues getFolderContentValues(MediaItem mediaItem, MediaItem mediaItem2) {
        AlbumType albumType;
        ContentValues contentValues = new ContentValues();
        contentValues.put("__bucketID", Integer.valueOf(mediaItem2.getFolderID()));
        contentValues.put("__Title", mediaItem2.getFolderName());
        contentValues.put("album_order", Long.valueOf(mediaItem2.getAlbumOrder()));
        contentValues.put("album_count", -1);
        if (mediaItem != null) {
            contentValues.put("folder_id", Integer.valueOf(mediaItem.getFolderID()));
            contentValues.put("folder_name", mediaItem.getFolderName());
        } else {
            contentValues.putNull("folder_id");
            contentValues.putNull("folder_name");
        }
        if (SUPPORT_MX_ALBUMS) {
            contentValues.put("__albumLevel", Integer.valueOf(mediaItem2.isAlbumLvFirst() ? 1 : 0));
            if (mediaItem2.getAlbumType() != AlbumType.None) {
                albumType = mediaItem2.getAlbumType();
            } else {
                albumType = AlbumType.Folder;
            }
            contentValues.put("__albumType", Integer.valueOf(albumType.toInt()));
        }
        return contentValues;
    }

    private long getGroupHashValue(HashMap<Integer, MediaItem> hashMap) {
        return Crc.getCrc64Long((String) hashMap.values().stream().sorted(Comparator.comparingInt(new C0545b(2))).filter(new C0598e(3)).filter(new C0571z(12)).map(new K(6)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
    }

    private void insertLocalDatabase(Context context, ArrayList<MediaItem> arrayList) {
        int addAndUpdateIfOverflowed;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList();
        Iterator<MediaItem> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (next.isFolder() || next.isMergedAlbum()) {
                for (MediaItem createInsertOp : next.getItemsInFolder()) {
                    i2 += addAndUpdateIfOverflowed(context, arrayList2, createInsertOp(next, createInsertOp));
                }
                addAndUpdateIfOverflowed = addAndUpdateIfOverflowed(context, arrayList2, createFolderInsertOp((MediaItem) null, next));
            } else {
                addAndUpdateIfOverflowed = addAndUpdateIfOverflowed(context, arrayList2, createInsertOp((MediaItem) null, next));
            }
            i2 += addAndUpdateIfOverflowed;
        }
        if (!arrayList2.isEmpty()) {
            i2 += updateInBatch(context, arrayList2);
        }
        Log.i(this.TAG, "insert database" + Logger.vt(Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
    }

    private boolean isAlbumHide(MediaItem mediaItem) {
        if (!mediaItem.isAlbumHide() || BucketUtils.contains(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    private boolean isBackupSecondaryMediaItem(MediaItem mediaItem) {
        String dataPath = mediaItem.getDataPath();
        if (TextUtils.isEmpty(dataPath)) {
            dataPath = mediaItem.getPath();
        }
        if (!IS_QOS || dataPath == null || ((!FileUtils.isInExternalDir(dataPath) && "external_primary".equals(mediaItem.getVolumeName())) || FileUtils.isInExternalDir(dataPath) || (FileUtils.hasSdcardVolume() && FileUtils.isInRemovableStorage(dataPath)))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getGroupHashValue$1(MediaItem mediaItem) {
        return mediaItem instanceof FolderItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getGroupHashValue$2(MediaItem mediaItem) {
        return mediaItem.getAlbumID() + NumericEnum.SEP + mediaItem.getTitle();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getGroupHashValue$3(MediaItem mediaItem) {
        StringBuilder sb2 = new StringBuilder("{");
        sb2.append(mediaItem.getAlbumID());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.getTitle());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.getCount());
        sb2.append(",[");
        return C0212a.p(sb2, (String) mediaItem.getChildItems().stream().sorted(Comparator.comparingInt(new C0545b(2))).map(new K(7)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]}");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$updateOnUi$0(Context context, ArrayList arrayList, ArrayList arrayList2, ThreadPool.JobContext jobContext) {
        this.mLocalPath = getLocalPath(context);
        addToLocalDb(context, arrayList, arrayList2);
        if (Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS)) {
            getBlackboard().publish("album_sync_data_changed", Boolean.FALSE);
        }
        return Boolean.TRUE;
    }

    private void notifyGroupUriIfChanged(Context context, HashMap<Integer, MediaItem> hashMap) {
        PreferenceCache preferenceCache = PreferenceCache.AlbumGroupCrc;
        long j2 = preferenceCache.getLong();
        long groupHashValue = getGroupHashValue(hashMap);
        if (j2 != groupHashValue) {
            preferenceCache.setLong(groupHashValue);
            context.getContentResolver().notifyChange(Uri.parse("content://com.sec.android.gallery3d.provider2/album_group"), (ContentObserver) null);
        }
    }

    private int updateInBatch(Context context, ArrayList<ContentProviderOperation> arrayList) {
        int i2;
        try {
            ContentProviderResult[] applyBatch = context.getContentResolver().applyBatch(this.mAuthority, arrayList);
            Log.d(this.TAG, "updateInBatch", Integer.valueOf(arrayList.size()), Integer.valueOf(applyBatch.length));
            i2 = applyBatch.length;
        } catch (OperationApplicationException | SQLiteFullException | RemoteException | NullPointerException e) {
            a.s(e, new StringBuilder("updateInBatch failed. e="), this.TAG);
            i2 = 0;
        }
        arrayList.clear();
        return i2;
    }

    private void updateLocalDatabase(Context context, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        Iterator it;
        long j2;
        int addAndUpdateIfOverflowed;
        HashMap hashMap;
        Context context2 = context;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList3 = new ArrayList();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        HashMap hashMap6 = new HashMap();
        HashMap hashMap7 = new HashMap();
        fetchDataFromList(arrayList, hashMap2, hashMap4, hashMap6, (MediaItem) null);
        HashMap hashMap8 = hashMap2;
        HashMap hashMap9 = hashMap4;
        HashMap hashMap10 = hashMap6;
        HashMap hashMap11 = hashMap3;
        HashMap hashMap12 = hashMap5;
        HashMap hashMap13 = hashMap7;
        fetchDataFromList(arrayList2, hashMap11, hashMap12, hashMap13, (MediaItem) null);
        Iterator it2 = hashMap11.values().iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            MediaItem mediaItem = (MediaItem) it2.next();
            Iterator it3 = it2;
            MediaItem mediaItem2 = (MediaItem) hashMap8.get(Integer.valueOf(mediaItem.getAlbumID()));
            if (mediaItem2 != null) {
                int i7 = i2;
                MediaItem mediaItem3 = (MediaItem) hashMap12.get(Integer.valueOf(mediaItem.getAlbumID()));
                if (!equalsItem(mediaItem2, mediaItem, (MediaItem) hashMap9.get(Integer.valueOf(mediaItem.getAlbumID())), mediaItem3)) {
                    String str = this.TAG;
                    MediaItem mediaItem4 = mediaItem2;
                    i7 += addAndUpdateIfOverflowed(context2, arrayList3, createUpdateOp(mediaItem3, mediaItem));
                    Log.d(str, "Update " + AlbumDataHelper.toDebugLog(mediaItem4) + ArcCommonLog.TAG_COMMA + AlbumDataHelper.toDebugLog(mediaItem));
                }
                i2 = i7;
            } else {
                i2 += addAndUpdateIfOverflowed(context2, arrayList3, createInsertOp((MediaItem) hashMap12.get(Integer.valueOf(mediaItem.getAlbumID())), mediaItem));
                String str2 = this.TAG;
                Log.d(str2, "Insert " + MediaItemUtil.getDebugLog(mediaItem));
            }
            it2 = it3;
        }
        int i8 = i2;
        Iterator it4 = hashMap8.values().iterator();
        while (it4.hasNext()) {
            MediaItem mediaItem5 = (MediaItem) it4.next();
            Iterator it5 = it4;
            if (hashMap11.containsKey(Integer.valueOf(mediaItem5.getAlbumID())) || isBackupSecondaryMediaItem(mediaItem5)) {
                hashMap = hashMap11;
            } else {
                i2 += addAndUpdateIfOverflowed(context2, arrayList3, createDeleteOp(mediaItem5));
                String str3 = this.TAG;
                hashMap = hashMap11;
                Log.d(str3, "Delete " + MediaItemUtil.getDebugLog(mediaItem5));
            }
            it4 = it5;
            hashMap11 = hashMap;
        }
        Iterator it6 = hashMap13.values().iterator();
        while (it6.hasNext()) {
            MediaItem mediaItem6 = (MediaItem) it6.next();
            MediaItem mediaItem7 = (MediaItem) hashMap10.get(Integer.valueOf(mediaItem6.getAlbumID()));
            if (mediaItem7 != null) {
                it = it6;
                int i10 = i2;
                MediaItem mediaItem8 = (MediaItem) hashMap12.get(Integer.valueOf(mediaItem6.getAlbumID()));
                if (!equalsFolder(mediaItem7, mediaItem6, (MediaItem) hashMap9.get(Integer.valueOf(mediaItem6.getAlbumID())), mediaItem8)) {
                    int addAndUpdateIfOverflowed2 = i10 + addAndUpdateIfOverflowed(context2, arrayList3, createFolderUpdateOp(mediaItem8, mediaItem6));
                    String str4 = this.TAG;
                    int i11 = addAndUpdateIfOverflowed2;
                    j2 = currentTimeMillis;
                    Log.d(str4, "Update(f) " + AlbumDataHelper.toDebugLog(mediaItem7) + ArcCommonLog.TAG_COMMA + AlbumDataHelper.toDebugLog(mediaItem6));
                    addAndUpdateIfOverflowed = i11;
                } else {
                    j2 = currentTimeMillis;
                    addAndUpdateIfOverflowed = i10;
                }
            } else {
                it = it6;
                j2 = currentTimeMillis;
                addAndUpdateIfOverflowed = i2 + addAndUpdateIfOverflowed(context2, arrayList3, createFolderInsertOp((MediaItem) hashMap12.get(Integer.valueOf(mediaItem6.getAlbumID())), mediaItem6));
                String str5 = this.TAG;
                Log.d(str5, "Insert " + MediaItemUtil.getDebugLog(mediaItem6));
            }
            it6 = it;
            currentTimeMillis = j2;
        }
        int i12 = i2;
        long j3 = currentTimeMillis;
        for (MediaItem mediaItem9 : hashMap10.values()) {
            if (!hashMap13.containsKey(Integer.valueOf(mediaItem9.getFolderID()))) {
                i2 += addAndUpdateIfOverflowed(context2, arrayList3, createFolderDeleteOp(mediaItem9));
                String str6 = this.TAG;
                Log.d(str6, "Delete " + MediaItemUtil.getDebugLog(mediaItem9));
            }
        }
        if (!arrayList3.isEmpty()) {
            i2 += updateInBatch(context2, arrayList3);
        }
        String str7 = this.TAG;
        Log.i(str7, "update database" + Logger.vt(Integer.valueOf(arrayList2.size()), Integer.valueOf(i2), Long.valueOf(j3)));
        notifyGroupUriIfChanged(context2, hashMap13);
    }

    private void upsertLocalDatabase(Context context, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            insertLocalDatabase(context, arrayList2);
        } else {
            updateLocalDatabase(context, arrayList, arrayList2);
        }
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public String getLocalPath(Context context) {
        if (context != null) {
            return (String) Optional.ofNullable(context.getExternalFilesDir(".album")).map(new K(5)).orElse((Object) null);
        }
        return null;
    }

    public void updateOnUi(Context context, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        ThreadPool.getInstance().submit(new q0(2, arrayList, this, context, arrayList2));
    }

    private boolean equalsFolder(MediaItem mediaItem, MediaItem mediaItem2) {
        return TextUtils.equals(mediaItem.getFolderName(), mediaItem2.getFolderName()) && mediaItem.getAlbumOrder() == mediaItem2.getAlbumOrder() && mediaItem.isAlbumLvFirst() == mediaItem2.isAlbumLvFirst();
    }

    private boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.isCustomCover() != mediaItem.isCustomCover() || differentiate(mediaItem2.getPath(), mediaItem.getPath())) {
            return false;
        }
        if (IS_QOS && !mediaItem2.isEmptyAlbum() && !mediaItem.isEmptyAlbum()) {
            if (differentiate(mediaItem2.getVolumeName(), mediaItem.getVolumeName())) {
                return false;
            }
            if (mediaItem2.getVolumeName() == null && mediaItem.getVolumeName() != null) {
                return false;
            }
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || PreferenceFeatures.MxAlbumsMergeNames.isEnabled() || mediaItem2.getFolderID() != -10 || mediaItem.getFolderID() == mediaItem2.getFolderID()) {
            return compare(mediaItem, mediaItem2);
        }
        return false;
    }
}
