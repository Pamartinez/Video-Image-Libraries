package com.samsung.android.gallery.app.service;

import A.a;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.album.AlbumContentBuilder;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.fileio.compat.FileOpError;
import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.fileio.compat.MediaStoreApi;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import com.samsung.android.gallery.module.service.message.FileOperationMsgMgr;
import com.samsung.android.gallery.module.service.message.FileOperationMsgParams;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MemoryUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileOpData {
    int albumId;
    Blackboard blackboard;
    int bucketId;
    boolean emptyAlbum;
    FileOpError errorCode = FileOpError.None;
    final ArrayList<FileOpJob> failCloudList = new ArrayList<>();
    final ArrayList<FileOpJob> failList = new ArrayList<>();
    String fileOpType;
    MediaItem[] items = new MediaItem[0];
    String locationKey;
    boolean newAlbum;
    int operation;
    final ArrayList<FileOpJob> passList = new ArrayList<>();
    final Queue<FileOpJob> queue = new ConcurrentLinkedQueue();
    final RestoreUserData restoreUserTag = new RestoreUserData();
    final AtomicBoolean scanState = new AtomicBoolean(false);
    final ArrayList<MediaItem> skipCloudList = new ArrayList<>();
    final ArrayList<MediaItem> skipPppList = new ArrayList<>();
    String storageName;
    final StorageState storageState = new StorageState();
    String summary;
    ContentValues targetAlbumContentValues;
    String targetDir;
    ArrayList<FileOpJob> totalList = new ArrayList<>();
    String volumeName;

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$log$10(FileOpJob fileOpJob) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(fileOpJob.source.getPath());
        if (fileOpJob.groupOp) {
            str = "(" + fileOpJob.source.getGroupMediaId() + ")";
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTargetAlbum$3(QueryParams queryParams) {
        queryParams.addAlbumId(FileUtils.getBucketId(this.targetDir));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanFolder$1(long j2, String str, Uri uri) {
        a.A(new Object[]{this.volumeName, Integer.valueOf(this.passList.size()), Long.valueOf(j2)}, new StringBuilder("onScanFolderComplete"), "FileOpData");
        this.restoreUserTag.restore();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$0() {
        this.blackboard.post("command://event/DataDirty", (Object) null);
        BlackboardUtils.removeDataChangeObservingDelay(this.blackboard);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toSummary$9(ArrayList arrayList, Integer num, List list) {
        String str;
        ArrayList arrayList2 = new ArrayList();
        ((Map) list.stream().collect(Collectors.groupingBy(new b(0)))).forEach(new c(arrayList2, 0));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileOpJob.alias(num.intValue()));
        sb2.append("=");
        sb2.append(list.size());
        if (!arrayList2.isEmpty()) {
            str = "(" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2) + ")";
        } else {
            str = "";
        }
        sb2.append(str);
        arrayList.add(sb2.toString());
    }

    private void log() {
        String str;
        StringBuilder sb2 = new StringBuilder("target=");
        sb2.append(this.targetDir);
        sb2.append(",source=[");
        sb2.append((String) this.totalList.stream().limit(5).map(new b(1)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        if (this.totalList.size() > 5) {
            str = ",+" + (this.totalList.size() - 5);
        } else {
            str = "";
        }
        String p6 = C0212a.p(sb2, str, "]");
        DebugLogger.getDeleteInstance().insertLog("[FileOpLog2024] " + toSummary() + " " + Logger.getEncodedString(p6));
    }

    private void moveToTargetAlbum() {
        Cursor query;
        int i2;
        String removeArgs = ArgumentsUtil.removeArgs((String) this.blackboard.read("location://variable/currentv1", ""));
        if (LocationKey.isAlbumPictures(removeArgs) || (PreferenceFeatures.OneUi5x.MX_ALBUMS && LocationKey.isAlbumSetting(removeArgs))) {
            try {
                query = DbCompat.query(DbKey.ALBUMS, new g(0, this));
                if (query != null) {
                    if (query.moveToFirst()) {
                        MediaItem load = MediaItemLoader.load(query);
                        this.blackboard.publish("data://albums/moveTo/target", load);
                        Blackboard blackboard2 = this.blackboard;
                        if (LocationKey.isAlbumPictures(removeArgs)) {
                            i2 = 104;
                        } else {
                            i2 = 2012;
                        }
                        blackboard2.postEvent(EventMessage.obtain(i2));
                        Log.d("FileOpData", "moveToTargetAlbum " + Logger.getEncodedString(load.getDisplayName()));
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Exception e) {
                a.s(e, new StringBuilder("moveToTargetAlbum failed. e="), "FileOpData");
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    private MediaItem[] queryAlbumData(int i2) {
        Cursor query;
        ArrayList arrayList = new ArrayList();
        try {
            query = DbCompat.query(DbKey.ALBUM_FILES, new e(i2));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(query));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            a.s(e, C0086a.o(i2, "query album=", " failed. e="), "FileOpData");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return (MediaItem[]) arrayList.toArray(new MediaItem[0]);
        throw th;
    }

    private void showResult(Context context) {
        int size = this.passList.size();
        int size2 = this.skipCloudList.size() + this.failCloudList.size() + this.failList.size();
        int size3 = this.totalList.size();
        String str = null;
        if (isAlbumRename()) {
            new LocalAlbumsApi().createData(this.targetAlbumContentValues);
            int loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(this.albumId), 12);
            if (loadSortBy != 12) {
                GalleryPreference.getInstance().saveSortBy(String.valueOf(this.bucketId), loadSortBy);
            }
            if (!this.passList.isEmpty() && this.passList.size() == this.totalList.size() && MediaStoreApi.getAlbumCount(this.albumId) == 0) {
                FileUtils.deleteEmptyDirectory(FileUtils.getDirectoryFromPath(this.items[0].getReferencePath()));
                GalleryPreference.getInstance().removeSortBy(String.valueOf(this.albumId));
                moveToTargetAlbum();
                if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                    LockedAlbum.getInstance().changeToRenamedBucketId(this.albumId, FileUtils.getBucketId(this.targetDir));
                }
            } else {
                FileOpError fileOpError = this.errorCode;
                if (fileOpError == FileOpError.OperationCanceled) {
                    str = FileOperationMsgMgr.getResultMessage(context, fileOpError, FileOperationMsgParams.builder().setAlbumPath(this.targetDir).setSuccessCount(size).setFailCount(size2).setTotalCount(size3).build());
                    Utils.showToast(context, str);
                }
            }
        } else {
            if (this.bucketId != 0) {
                PreferenceCache.AlbumLatestUpdatedId.setInt(FileUtils.getBucketId(this.targetDir));
                PreferenceCache.AlbumLatestUpdatedTime.setLong(System.currentTimeMillis() / 1000);
            }
            if (this.errorCode == FileOpError.None) {
                if (this.failCloudList.size() == size3) {
                    this.errorCode = FileOpError.CloudFailed;
                } else if (size2 == size3) {
                    this.errorCode = FileOpError.LocalFailed;
                } else if (!this.failCloudList.isEmpty()) {
                    this.errorCode = FileOpError.CloudPartialFailed;
                } else if (size2 > 0) {
                    this.errorCode = FileOpError.LocalPartialFailed;
                }
            }
            FileOpError fileOpError2 = this.errorCode;
            if (fileOpError2 == FileOpError.StorageNotEnough) {
                int count = (int) this.totalList.stream().filter(new f(0)).count();
                this.blackboard.post(CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", context.getString(R.string.not_enough_space)).appendArg("description", FileOperationMsgMgr.getNotEnoughStorageMessage(context, FileOperationMsgParams.builder().setIsCopy(isCopy()).setImageCount(this.totalList.size() - count).setVideoCount(count).setTotalCount(size3).build())).appendArg("option1", context.getString(R.string.ok)).appendArg("hideCancel", true).build()), (Object) null);
            } else {
                str = FileOperationMsgMgr.getResultMessage(context, fileOpError2, FileOperationMsgParams.builder().setAlbumPath(this.targetDir).setIsCopy(isCopy()).setSuccessCount(size).setFailCount(size2).setTotalCount(size3).build());
                Utils.showToast(context, str);
            }
        }
        Log.d("FileOpData", "showResult" + Logger.v(str) + " " + toSummary() + " target=" + Logger.getEncodedString(this.targetDir));
    }

    private String toSummary() {
        String str;
        String str2;
        String str3;
        if (this.summary == null) {
            String summary2 = toSummary(this.failList);
            String summary3 = toSummary(this.failCloudList);
            String str4 = (String) this.totalList.stream().filter(new f(1)).map(new V8.a(28)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            int size = this.skipCloudList.size() + this.skipPppList.size();
            StringBuilder sb2 = new StringBuilder("Summary{");
            sb2.append(this.fileOpType);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(FileOpJob.alias(this.operation));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.errorCode);
            sb2.append(",in=");
            sb2.append(this.items.length);
            sb2.append(",total=");
            C0086a.A(sb2, this.totalList, ",pass=");
            C0086a.A(sb2, this.passList, ",fail=");
            C0086a.A(sb2, this.failList, ",fail-cloud=");
            sb2.append(this.failCloudList.size());
            sb2.append(",skip=");
            sb2.append(size);
            String str5 = "";
            if (size == 0) {
                str = str5;
            } else {
                StringBuilder sb3 = new StringBuilder(",Skip[ppp=");
                C0086a.A(sb3, this.skipPppList, ",c2s=");
                sb3.append(this.skipCloudList.size());
                sb3.append("]");
                str = sb3.toString();
            }
            sb2.append(str);
            if (TextUtils.isEmpty(summary2)) {
                str2 = str5;
            } else {
                str2 = C0212a.m(",Fail[", summary2, "]");
            }
            sb2.append(str2);
            if (TextUtils.isEmpty(summary3)) {
                str3 = str5;
            } else {
                str3 = C0212a.m(",FailCloud[", summary3, "]");
            }
            sb2.append(str3);
            if (!TextUtils.isEmpty(str4)) {
                str5 = C0212a.m(",Group[", str4, "]");
            }
            sb2.append(str5);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.storageState);
            sb2.append("}");
            this.summary = sb2.toString();
        }
        return this.summary;
    }

    public void clear() {
        this.queue.clear();
        this.failList.clear();
        this.failCloudList.clear();
        this.passList.clear();
        this.skipPppList.clear();
        this.skipCloudList.clear();
        this.totalList = new ArrayList<>();
    }

    public boolean computeStorage(FileOpJob fileOpJob) {
        return this.storageState.computeStorage(fileOpJob);
    }

    public boolean init(Intent intent) {
        int i2;
        MediaItem[] mediaItemArr;
        this.blackboard = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.locationKey = intent.getStringExtra("location_key");
        String stringExtra = intent.getStringExtra("target_album_path");
        if (stringExtra != null && stringExtra.endsWith(File.separator)) {
            stringExtra = C0280e.d(1, 0, stringExtra);
        }
        if (stringExtra != null) {
            this.volumeName = FileUtils.getVolumeName(stringExtra);
            this.bucketId = FileUtils.getBucketId(stringExtra);
            this.storageName = FileUtils.getStorageName(stringExtra);
        }
        this.targetDir = stringExtra;
        this.newAlbum = intent.getBooleanExtra("is_new_album", false);
        this.albumId = intent.getIntExtra("selected_album_id", 0);
        this.emptyAlbum = intent.getBooleanExtra("is_empty_album", false);
        String stringExtra2 = intent.getStringExtra("operation_type");
        this.fileOpType = stringExtra2;
        if ("rename".equals(stringExtra2) || "move".equals(this.fileOpType)) {
            i2 = 2;
        } else if ("copy".equals(this.fileOpType)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        this.operation = i2;
        boolean z = PocFeatures.SUPPORT_PRIVATE_ALBUM;
        if (z && i2 == 0) {
            if ("move_to_secured".equals(this.fileOpType)) {
                this.operation = 65541;
            } else if ("move_from_secured".equals(this.fileOpType)) {
                this.operation = 131077;
            }
        }
        if (this.operation == 0) {
            this.items = new MediaItem[0];
        } else if (isAlbumRename()) {
            int i7 = this.albumId;
            if (i7 == 0) {
                mediaItemArr = new MediaItem[0];
            } else {
                mediaItemArr = queryAlbumData(i7);
            }
            this.items = mediaItemArr;
        } else {
            MediaItem[] mediaItemArr2 = (MediaItem[]) this.blackboard.pop("data://user/selected");
            if (mediaItemArr2 == null) {
                mediaItemArr2 = new MediaItem[0];
            }
            this.items = mediaItemArr2;
        }
        if (this.operation == 0 || this.items.length == 0 || TextUtils.isEmpty(stringExtra)) {
            return false;
        }
        if (z && (this.operation & 983040) > 0) {
            this.storageName = StorageInfo.getDefault().root;
            BlackboardUtils.collectExternalDataChangedEvent(this.blackboard, true);
            this.blackboard.postEvent(EventMessage.obtain(1003));
            return true;
        } else if (!FileUtils.isValidPath(stringExtra)) {
            return false;
        } else {
            if (isAlbumRename()) {
                this.targetAlbumContentValues = new AlbumContentBuilder().load(this.albumId).setPath(this.targetDir).build();
            }
            BlackboardUtils.collectExternalDataChangedEvent(this.blackboard, true);
            this.blackboard.postEvent(EventMessage.obtain(1003));
            return true;
        }
    }

    public void initJob(ArrayList<FileOpJob> arrayList) {
        this.totalList = arrayList;
        this.queue.addAll(arrayList);
    }

    public boolean initStorageState() {
        return this.storageState.init(this.storageName);
    }

    public boolean isAlbumRename() {
        return "rename".equals(this.fileOpType);
    }

    public boolean isCopy() {
        if (this.operation == 1) {
            return true;
        }
        return false;
    }

    public boolean isLowStorage() {
        return this.storageState.isLowStorage();
    }

    public boolean isRemovableStorage() {
        if (this.storageState.storageType == 1) {
            return true;
        }
        return false;
    }

    public boolean replaceIfChanged(Object obj) {
        Blackboard instance;
        Blackboard blackboard2;
        if (!(obj instanceof Intent) || (instance = Blackboard.getInstance(((Intent) obj).getStringExtra("blackboard_name"))) == null || instance == (blackboard2 = this.blackboard)) {
            return false;
        }
        BlackboardUtils.collectExternalDataChangedEvent(blackboard2, false);
        this.blackboard = instance;
        BlackboardUtils.collectExternalDataChangedEvent(instance, true);
        return true;
    }

    public void scanFolder(Context context) {
        if (!this.scanState.getAndSet(true)) {
            Log.d("FileOpData", "scanFolder", Integer.valueOf(this.passList.size()));
            this.restoreUserTag.prepare(this.passList);
            MediaScanner.scanFolder(context, this.targetDir, new a(this, System.currentTimeMillis()));
        }
    }

    public void start(Context context) {
        this.restoreUserTag.init(this.totalList);
    }

    public void stop(Context context) {
        scanFolder(context);
        showResult(context);
        log();
        Blackboard blackboard2 = this.blackboard;
        if (blackboard2 != null) {
            BlackboardUtils.collectExternalDataChangedEvent(blackboard2, false);
            ThreadUtil.postOnUiThreadDelayed(new d(0, this), 500);
            this.blackboard.erase("data://user/selected");
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FileOpData{");
        sb2.append(this.fileOpType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(FileOpJob.alias(this.operation));
        sb2.append(",volume=[");
        sb2.append(this.volumeName);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.storageName);
        sb2.append("],in=");
        sb2.append(this.items.length);
        sb2.append(",total=");
        C0086a.A(sb2, this.totalList, ",ppp=");
        C0086a.A(sb2, this.skipPppList, ",cloud=");
        sb2.append(this.skipCloudList);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.storageState);
        sb2.append("}");
        return sb2.toString();
    }

    public boolean computeStorage(List<FileOpJob> list) {
        return this.storageState.computeStorage(list);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StorageState {
        long requiredSize;
        long storageAvailable;
        String storageName;
        long storageReal;
        String storageState;
        int storageType;
        long totalRequiredSize;

        public boolean computeStorage(List<FileOpJob> list) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            long j2 = 0;
            long j3 = 0;
            for (FileOpJob next : list) {
                if (next.isCopy() || next.isMove()) {
                    if (next.source.isGroupShot()) {
                        arrayList.add(next);
                    } else {
                        j3 += next.source.getFileSize();
                    }
                }
            }
            this.totalRequiredSize = j3;
            if (!isLowStorage() && arrayList.size() > 0) {
                j2 = GroupItemLoader.queryTotalSize((String) arrayList.stream().map(new b(3)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), ((Boolean) arrayList.stream().filter(new f(2)).map(new b(4)).findFirst().orElse(Boolean.FALSE)).booleanValue());
            }
            this.totalRequiredSize += j2;
            StringBuilder sb2 = new StringBuilder("computeStorage");
            String str = this.totalRequiredSize < this.storageAvailable ? "ok" : "low storage";
            a.A(new Object[]{str, "in=" + list.size(), j.g(new StringBuilder("group="), arrayList), "required=" + this.totalRequiredSize, "available=" + this.storageAvailable, a.f("fs=", j3), a.f("gs=", j2), Long.valueOf(currentTimeMillis)}, sb2, "FileOpData");
            return this.totalRequiredSize < this.storageAvailable;
        }

        public boolean init(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.storageName = str;
                this.storageType = FileUtils.isInRemovableStorage(str) ? 1 : 0;
                String storageState2 = MemoryUtils.getStorageState(str);
                this.storageState = storageState2;
                long availableStorageSize = MemoryUtils.getAvailableStorageSize(storageState2, str);
                this.storageReal = availableStorageSize;
                long max = Math.max(availableStorageSize - 9437184, 0);
                this.storageAvailable = max;
                if (max > 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean isLowStorage() {
            if (this.totalRequiredSize >= this.storageAvailable) {
                return true;
            }
            return false;
        }

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder("StorageState[");
            if (this.storageType == 1) {
                str = "SDCARD";
            } else {
                str = "INTERNAL";
            }
            sb2.append(str);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.storageName);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.storageState);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.requiredSize);
            sb2.append("(");
            sb2.append(this.totalRequiredSize);
            sb2.append("),");
            sb2.append(this.storageAvailable);
            sb2.append("(");
            return C0212a.o(sb2, this.storageReal, ")]");
        }

        public boolean computeStorage(FileOpJob fileOpJob) {
            if (!fileOpJob.isCopy() && !fileOpJob.isMove()) {
                return true;
            }
            if (fileOpJob.source.getFileSize() + this.requiredSize >= this.storageAvailable) {
                Log.d("FileOpData", "computeStorage failed. low storage", Long.valueOf(fileOpJob.source.getFileSize() + this.requiredSize), Long.valueOf(this.storageAvailable));
                return false;
            }
            long fileSize = fileOpJob.getFileSize() + this.requiredSize;
            this.requiredSize = fileSize;
            if (fileSize < this.storageAvailable) {
                return true;
            }
            return false;
        }
    }

    private String toSummary(ArrayList<FileOpJob> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return "";
        }
        ArrayList arrayList2 = new ArrayList();
        ((Map) arrayList.stream().collect(Collectors.groupingBy(new b(2)))).forEach(new c(arrayList2, 1));
        return TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList2);
    }
}
