package com.samsung.android.gallery.module.fileio.abstraction;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.module.fileio.util.FileApiUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileAuth;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocalCloudFileOperation extends FileOperation {
    static boolean SUPPORT_CLEAR_DISK_CACHE;
    private static final boolean UPDATE_FILE_AUTHORITY;

    static {
        SdkConfig.GED ged = SdkConfig.GED.Q;
        UPDATE_FILE_AUTHORITY = SdkConfig.atLeast(ged);
        SUPPORT_CLEAR_DISK_CACHE = SdkConfig.atLeast(ged);
    }

    private FileOpResult copyInsteadOfMove(Context context, FileInfo fileInfo) {
        FileOpResult copyInternal = copyInternal(context, fileInfo);
        if (copyInternal == FileOpResult.OP_LOCAL_OK && supportRemoveDiskCache(fileInfo)) {
            removeDiskCache(fileInfo.getMediaItem());
        }
        return copyInternal;
    }

    private FileOpResult copyOperation(Context context, FileInfo fileInfo) {
        String str;
        String str2;
        MediaItem mediaItem = fileInfo.getMediaItem();
        if (mediaItem.isLocal()) {
            str = mediaItem.getPath();
        } else {
            str = mediaItem.getCloudThumbPath();
        }
        if (mediaItem.isLocal()) {
            str2 = fileInfo.getDestPath();
        } else {
            str2 = FileUtils.getNewFilePath(mediaItem.getCloudThumbPath());
        }
        if (!copyPrimitive(str, str2, fileInfo.getFileMode(), mediaItem.isLocal())) {
            return getResult(mediaItem, false);
        }
        if (UPDATE_FILE_AUTHORITY && mediaItem.isCloudOnly()) {
            FileAuth.setWritable(str2);
        }
        if (mediaItem.isLocal()) {
            FileApiUtils.keepFileModifiedTime(str, str2);
        }
        updateDataBaseByCopy(context, fileInfo);
        if (mediaItem.isCloudOnly()) {
            updateProgress(mediaItem.getCloudOriginalSize());
        }
        return getResult(mediaItem, true);
    }

    private FileOpResult getResult(FileItemInterface fileItemInterface, boolean z) {
        if (z) {
            if (fileItemInterface.isLocal()) {
                return FileOpResult.OP_LOCAL_OK;
            }
            return FileOpResult.OP_CLOUD_OK;
        } else if (fileItemInterface.isLocal()) {
            return FileOpResult.OP_LOCAL_FAIL;
        } else {
            return FileOpResult.OP_CLOUD_FAIL;
        }
    }

    private void updateDataBaseByCopy(Context context, FileInfo fileInfo) {
        if (fileInfo.isOverwriteMode()) {
            getDbOperation().updateDatabaseByOverWrite(context, fileInfo);
            FileApiUtils.changeOverwriteDummy(fileInfo.getDestPath());
        }
        getDbOperation().updateDatabaseByCopy(context, fileInfo);
        if (fileInfo.isMoveMode()) {
            getDbOperation().updateDatabaseByCopyInsteadOfMove(context, fileInfo);
        }
    }

    public void copyCloudGroupMedia(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        String destPath = fileInfo.getDestPath();
        String directoryFromPath = FileUtils.getDirectoryFromPath(destPath, false);
        ArrayList arrayList = new ArrayList(list.size());
        long j2 = 0;
        for (FileItemInterface next : list) {
            String srcPath = FileApiUtils.getSrcPath(next);
            String makeBurstShotName = FileApiUtils.makeBurstShotName(context, fileInfo.getMediaItem(), destPath, next);
            String fileDestPath = FileApiUtils.getFileDestPath(next, directoryFromPath, makeBurstShotName);
            arrayList.add(Pair.create(FileApiUtils.getLogicalDestPath(directoryFromPath, makeBurstShotName), FileApiUtils.getUpdatePath(next, fileDestPath)));
            if (copyPrimitive(srcPath, fileDestPath, fileInfo.getFileMode(), false)) {
                if (UPDATE_FILE_AUTHORITY) {
                    FileAuth.setWritable(fileDestPath);
                }
                j2 += next.getCloudOriginalSize();
            } else {
                return;
            }
        }
        getDbOperation().updateDatabaseByGroupMediaCopy(context, fileInfo, list, arrayList);
        updateProgress(j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyGroupMedia(android.content.Context r10, com.samsung.android.gallery.module.fileio.type.FileInfo r11, java.util.List<com.samsung.android.gallery.database.dbtype.FileItemInterface> r12) {
        /*
            r9 = this;
            java.lang.String r0 = r11.getDestPath()
            r1 = 0
            java.lang.String r0 = com.samsung.android.gallery.support.utils.FileUtils.getDirectoryFromPath(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r12.size()
            r1.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r3 = r12.iterator()
        L_0x001b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0072
            java.lang.Object r4 = r3.next()
            com.samsung.android.gallery.database.dbtype.FileItemInterface r4 = (com.samsung.android.gallery.database.dbtype.FileItemInterface) r4
            java.lang.String r5 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getSrcPath(r4)
            com.samsung.android.gallery.module.data.MediaItem r6 = r11.getMediaItem()
            java.lang.String r7 = r11.getDestPath()
            java.lang.String r6 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.makeBurstShotName(r10, r6, r7, r4)
            java.lang.String r7 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getFileDestPath(r4, r0, r6)
            java.lang.String r6 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getLogicalDestPath(r0, r6)
            java.lang.String r8 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getUpdatePath(r4, r7)
            android.util.Pair r6 = android.util.Pair.create(r6, r8)
            r1.add(r6)
            java.lang.String r4 = r4.getCloudServerId()
            android.util.Pair r4 = android.util.Pair.create(r5, r4)
            r2.add(r4)
            int r4 = r11.getFileMode()
            boolean r4 = r9.copyPrimitive(r5, r7, r4)
            if (r4 == 0) goto L_0x008a
            int r4 = r11.getGroupType()
            int r5 = r11.getNewGroupId()
            java.util.Map r4 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getSefDataMap(r4, r5)
            boolean r4 = com.samsung.android.gallery.support.utils.SefFileUtils.updateFileWithSef(r4, r7)
            if (r4 != 0) goto L_0x001b
            goto L_0x008a
        L_0x0072:
            com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface r3 = r9.getDbOperation()
            r3.updateDatabaseByGroupMediaCopy(r10, r11, r12, r1)
            boolean r12 = r11.isMoveMode()
            if (r12 == 0) goto L_0x008a
            com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface r9 = r9.getDbOperation()
            com.samsung.android.gallery.module.data.MediaItem r11 = r11.getMediaItem()
            r9.updateDatabaseByCopyInsteadOfGroupMediaMove(r10, r11, r2, r0)
        L_0x008a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation.copyGroupMedia(android.content.Context, com.samsung.android.gallery.module.fileio.type.FileInfo, java.util.List):void");
    }

    public FileOpResult copyInternal(Context context, FileInfo fileInfo) {
        String str;
        MediaItem mediaItem = fileInfo.getMediaItem();
        if (mediaItem.isLocal()) {
            str = mediaItem.getPath();
        } else {
            str = mediaItem.getCloudServerPath();
        }
        if (!FileUtils.equals(str, fileInfo.getDestPath())) {
            return copyOperation(context, fileInfo);
        }
        updateProgress(mediaItem.getFileSize());
        return getResult(mediaItem, true);
    }

    public final boolean copyPrimitive(String str, String str2, int i2) {
        if (!copyPrimitive(str, str2, i2, true)) {
            return false;
        }
        FileApiUtils.keepFileModifiedTime(str, str2);
        return true;
    }

    public final boolean deletePrimitive(String str) {
        return FileUtils.delete(str);
    }

    public final boolean isMpUpdatablePath(String str, String str2) {
        if (FileUtils.isInAndroidMediaDir(str) || FileUtils.isInAndroidMediaDir(str2)) {
            return false;
        }
        return true;
    }

    public void moveCloudGroupMedia(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        String destPath = fileInfo.getDestPath();
        String directoryFromPath = FileUtils.getDirectoryFromPath(destPath, false);
        ArrayList arrayList = new ArrayList(list.size());
        long j2 = 0;
        for (FileItemInterface next : list) {
            if (next.getCloudServerPath() == null) {
                Log.e(this.TAG, "cloud_server_path : null");
            } else {
                arrayList.add(Pair.create(FileApiUtils.getLogicalDestPath(directoryFromPath, FileApiUtils.makeBurstShotName(context, fileInfo.getMediaItem(), destPath, next)), (Object) null));
                j2 += next.getCloudOriginalSize();
            }
        }
        getDbOperation().updateDatabaseByGroupMediaMove(context, list, arrayList, directoryFromPath, fileInfo.getFileMode());
        updateProgress(j2);
    }

    public void moveGroupMedia(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        String destPath = fileInfo.getDestPath();
        if (fileInfo.isDiffStorageMoveMode()) {
            copyGroupMedia(context, fileInfo, list);
            return;
        }
        String directoryFromPath = FileUtils.getDirectoryFromPath(destPath, false);
        ArrayList arrayList = new ArrayList(list.size());
        for (FileItemInterface next : list) {
            String srcPath = FileApiUtils.getSrcPath(next);
            String makeBurstShotName = FileApiUtils.makeBurstShotName(context, fileInfo.getMediaItem(), destPath, next);
            String fileDestPath = FileApiUtils.getFileDestPath(next, directoryFromPath, makeBurstShotName);
            arrayList.add(Pair.create(FileApiUtils.getLogicalDestPath(directoryFromPath, makeBurstShotName), (Object) null));
            if (!PreferenceFeatures.SUPPORT_RENAME_BY_MP || next.isDrm() || !isMpUpdatablePath(srcPath, fileDestPath)) {
                if (!movePrimitive(srcPath, fileDestPath)) {
                    return;
                }
            } else if (!FileApiUtils.checkValidity(srcPath, fileDestPath) || !renameByUri(context, ContentUri.getWritableUri(next), fileDestPath)) {
                return;
            }
            updateProgress(next.getFileSize());
        }
        getDbOperation().updateDatabaseByGroupMediaMove(context, list, arrayList, directoryFromPath, fileInfo.getFileMode());
    }

    public FileOpResult moveInternal(Context context, FileInfo fileInfo) {
        long j2;
        MediaItem mediaItem = fileInfo.getMediaItem();
        String destPath = fileInfo.getDestPath();
        boolean isLocal = mediaItem.isLocal();
        if (!fileInfo.isCopyInsteadMoveMode()) {
            if (supportRenameByUri(fileInfo)) {
                if (!FileApiUtils.checkValidity(mediaItem.getPath(), destPath) || !renameByUri(context, ContentUri.getWritableUri(mediaItem), destPath)) {
                    return getResult(mediaItem, false);
                }
            } else if (isLocal && !movePrimitive(mediaItem.getPath(), destPath)) {
                return getResult(mediaItem, false);
            } else {
                getDbOperation().updateDatabaseByMove(context, fileInfo);
            }
            if (supportRemoveDiskCache(fileInfo)) {
                removeDiskCache(mediaItem);
            }
            if (mediaItem.isLocal()) {
                j2 = mediaItem.getFileSize();
            } else {
                j2 = mediaItem.getCloudOriginalSize();
            }
            updateProgress(j2);
            return getResult(mediaItem, true);
        } else if (!mediaItem.isCloudOnly()) {
            return copyInsteadOfMove(context, fileInfo);
        } else {
            Log.w(this.TAG, "can't overwrite cloud only, skip this");
            updateProgress(mediaItem.getCloudOriginalSize());
            return FileOpResult.OP_CLOUD_OK;
        }
    }

    public final boolean movePrimitive(String str, String str2) {
        if (FileApiUtils.checkValidity(str, str2)) {
            FileUtils.makeDirectoryIfAbsent(FileUtils.getDirectoryFromPath(str2, false));
            SecureFile secureFile = new SecureFile(str);
            SecureFile secureFile2 = new SecureFile(str2);
            long length = secureFile.length();
            boolean renameTo = secureFile.renameTo(secureFile2);
            long length2 = secureFile2.length();
            if (!renameTo || !FileApiUtils.checkValidFileSize(secureFile.getPath(), length, secureFile2.getPath(), length2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final void removeDiskCache(FileItemInterface fileItemInterface) {
        ThumbnailLoader.getInstance().removeDiskCache(fileItemInterface);
    }

    public boolean renameByUri(Context context, Uri uri, String str) {
        int i2;
        String nameFromPath = FileUtils.getNameFromPath(str);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("relative_path", FileUtils.getRelativePath(str));
            contentValues.put("_display_name", nameFromPath);
            contentValues.put("title", FileUtils.getBaseName(nameFromPath));
            i2 = context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.e((CharSequence) str2, "renameByUri failed {" + uri + ',' + Logger.getEncodedString(str) + '}', (Throwable) e);
            i2 = 0;
        }
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean supportRemoveDiskCache(FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        if (!SUPPORT_CLEAR_DISK_CACHE || !mediaItem.isLocal() || fileInfo.getFileMode() == 4) {
            return false;
        }
        if (!fileInfo.isCopyInsteadMoveMode() || !FileUtils.equals(mediaItem.getPath(), fileInfo.getDestPath())) {
            return true;
        }
        return false;
    }

    public boolean supportRenameByUri(FileInfo fileInfo) {
        MediaItem mediaItem = fileInfo.getMediaItem();
        if (!PreferenceFeatures.SUPPORT_RENAME_BY_MP || !mediaItem.isLocal() || fileInfo.getMediaItem().isDrm() || !isMpUpdatablePath(mediaItem.getPath(), fileInfo.getDestPath())) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ae, code lost:
        r10 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.checkValidFileSize(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b2, code lost:
        if (r5 == null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c0, code lost:
        com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00c3, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean copyPrimitive(java.lang.String r9, java.lang.String r10, int r11, boolean r12) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 != 0) goto L_0x0116
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L_0x0116
            java.lang.String r0 = "."
            boolean r0 = r10.contains(r0)
            if (r0 != 0) goto L_0x0017
            goto L_0x0116
        L_0x0017:
            r0 = 8
            boolean r2 = r8.isActiveMode(r11, r0)
            if (r2 == 0) goto L_0x0023
            if (r12 == 0) goto L_0x0023
            r2 = 1
            goto L_0x0024
        L_0x0023:
            r2 = r1
        L_0x0024:
            java.lang.String r10 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.getDstPath(r10, r2)
            com.samsung.android.gallery.support.utils.SecureFile r2 = new com.samsung.android.gallery.support.utils.SecureFile
            r2.<init>(r9)
            com.samsung.android.gallery.support.utils.SecureFile r3 = new com.samsung.android.gallery.support.utils.SecureFile
            r3.<init>(r10)
            boolean r4 = r3.exists()
            if (r4 == 0) goto L_0x004c
            boolean r4 = com.samsung.android.gallery.support.utils.FileUtils.equals((java.io.File) r3, (java.io.File) r2)
            if (r4 != 0) goto L_0x0044
            boolean r11 = r8.isActiveMode(r11, r0)
            if (r11 != 0) goto L_0x004c
        L_0x0044:
            java.lang.String r8 = r8.TAG
            java.lang.String r9 = "copyPrimitive: skip operation"
            com.samsung.android.gallery.support.utils.Log.w(r8, r9)
            return r1
        L_0x004c:
            java.lang.String r11 = com.samsung.android.gallery.support.utils.FileUtils.getDirectoryFromPath(r10, r1)
            com.samsung.android.gallery.support.utils.FileUtils.makeDirectoryIfAbsent((java.lang.String) r11)
            r11 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
            r0.<init>(r9)     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ all -> 0x009f }
            com.samsung.android.gallery.support.utils.SecureFile r4 = new com.samsung.android.gallery.support.utils.SecureFile     // Catch:{ all -> 0x009f }
            r4.<init>(r10)     // Catch:{ all -> 0x009f }
            r9.<init>(r4)     // Catch:{ all -> 0x009f }
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ all -> 0x00a1 }
            java.nio.channels.FileChannel r5 = r9.getChannel()     // Catch:{ all -> 0x00a3 }
            java.nio.ByteBuffer r11 = com.samsung.android.gallery.support.utils.ByteBufferHolder.computeIfAbsent()     // Catch:{ all -> 0x00a5 }
        L_0x006f:
            int r6 = r4.read(r11)     // Catch:{ all -> 0x00a5 }
            r7 = -1
            if (r6 == r7) goto L_0x00ae
            r11.flip()     // Catch:{ all -> 0x00a5 }
            r5.write(r11)     // Catch:{ all -> 0x00a5 }
            r11.clear()     // Catch:{ all -> 0x00a5 }
            boolean r7 = r8.isCancelled()     // Catch:{ all -> 0x00a5 }
            if (r7 == 0) goto L_0x00a7
            r8.deletePrimitive(r10)     // Catch:{ all -> 0x00a5 }
            r5.close()     // Catch:{ all -> 0x00a3 }
            r4.close()     // Catch:{ all -> 0x00a1 }
            r9.close()     // Catch:{ all -> 0x009f }
            r0.close()     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r11)
            return r1
        L_0x0098:
            r8 = move-exception
            goto L_0x0112
        L_0x009b:
            r8 = move-exception
            goto L_0x00ec
        L_0x009d:
            r9 = move-exception
            goto L_0x00f6
        L_0x009f:
            r9 = move-exception
            goto L_0x00e3
        L_0x00a1:
            r10 = move-exception
            goto L_0x00da
        L_0x00a3:
            r10 = move-exception
            goto L_0x00cf
        L_0x00a5:
            r10 = move-exception
            goto L_0x00c4
        L_0x00a7:
            if (r12 == 0) goto L_0x006f
            long r6 = (long) r6
            r8.updateProgress(r6)     // Catch:{ all -> 0x00a5 }
            goto L_0x006f
        L_0x00ae:
            boolean r10 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.checkValidFileSize(r2, r3)     // Catch:{ all -> 0x00a5 }
            if (r5 == 0) goto L_0x00b7
            r5.close()     // Catch:{ all -> 0x00a3 }
        L_0x00b7:
            r4.close()     // Catch:{ all -> 0x00a1 }
            r9.close()     // Catch:{ all -> 0x009f }
            r0.close()     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r11)
            return r10
        L_0x00c4:
            if (r5 == 0) goto L_0x00ce
            r5.close()     // Catch:{ all -> 0x00ca }
            goto L_0x00ce
        L_0x00ca:
            r12 = move-exception
            r10.addSuppressed(r12)     // Catch:{ all -> 0x00a3 }
        L_0x00ce:
            throw r10     // Catch:{ all -> 0x00a3 }
        L_0x00cf:
            if (r4 == 0) goto L_0x00d9
            r4.close()     // Catch:{ all -> 0x00d5 }
            goto L_0x00d9
        L_0x00d5:
            r12 = move-exception
            r10.addSuppressed(r12)     // Catch:{ all -> 0x00a1 }
        L_0x00d9:
            throw r10     // Catch:{ all -> 0x00a1 }
        L_0x00da:
            r9.close()     // Catch:{ all -> 0x00de }
            goto L_0x00e2
        L_0x00de:
            r9 = move-exception
            r10.addSuppressed(r9)     // Catch:{ all -> 0x009f }
        L_0x00e2:
            throw r10     // Catch:{ all -> 0x009f }
        L_0x00e3:
            r0.close()     // Catch:{ all -> 0x00e7 }
            goto L_0x00eb
        L_0x00e7:
            r10 = move-exception
            r9.addSuppressed(r10)     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
        L_0x00eb:
            throw r9     // Catch:{ FileNotFoundException -> 0x009d, Exception -> 0x009b }
        L_0x00ec:
            com.samsung.android.gallery.module.fileio.util.FileApiUtils.checkValidFileSize(r2, r3)     // Catch:{ all -> 0x0098 }
            r8.printStackTrace()     // Catch:{ all -> 0x0098 }
        L_0x00f2:
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r11)
            goto L_0x0111
        L_0x00f6:
            java.lang.String r8 = r8.TAG     // Catch:{ all -> 0x0098 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0098 }
            r10.<init>()     // Catch:{ all -> 0x0098 }
            java.lang.String r12 = "CopyInternal FileNotFoundException e="
            r10.append(r12)     // Catch:{ all -> 0x0098 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x0098 }
            r10.append(r9)     // Catch:{ all -> 0x0098 }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x0098 }
            com.samsung.android.gallery.support.utils.Log.e(r8, r9)     // Catch:{ all -> 0x0098 }
            goto L_0x00f2
        L_0x0111:
            return r1
        L_0x0112:
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r11)
            throw r8
        L_0x0116:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation.copyPrimitive(java.lang.String, java.lang.String, int, boolean):boolean");
    }
}
