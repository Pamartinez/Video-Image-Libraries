package com.samsung.android.gallery.module.fileio.cmh;

import android.content.Context;
import android.database.Cursor;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.module.fileio.util.FileApiUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sum.core.descriptor.b;
import f9.C0690a;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhGroupMediaFileOperation extends LocalCloudFileOperation {
    /* access modifiers changed from: private */
    /* renamed from: copyOperationCloud */
    public FileOpResult lambda$copyInternal$1(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long j2 = 0;
        for (FileItemInterface next : list) {
            String makeBurstShotName = FileApiUtils.makeBurstShotName(directoryFromPath, next.getDisplayName());
            StringBuilder s = C0212a.s(directoryFromPath);
            s.append(File.separator);
            s.append(makeBurstShotName);
            arrayList2.add(s.toString());
            arrayList.add(next.getCloudServerId());
            j2 += next.getCloudOriginalSize();
        }
        FileOpResult result = FileOpResult.toResult(SamsungCloudCompat.copy(context, (ArrayList<String>) arrayList, (ArrayList<String>) arrayList2));
        updateProgress(j2);
        return result;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0021  */
    /* renamed from: copyOperationLocalCloud */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.module.fileio.abstraction.FileOpResult lambda$copyInternal$0(android.content.Context r10, com.samsung.android.gallery.module.fileio.type.FileInfo r11, java.util.List<com.samsung.android.gallery.database.dbtype.FileItemInterface> r12) {
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
            if (r4 == 0) goto L_0x006a
            java.lang.Object r4 = r3.next()
            com.samsung.android.gallery.database.dbtype.FileItemInterface r4 = (com.samsung.android.gallery.database.dbtype.FileItemInterface) r4
            java.lang.String r5 = r4.getPath()
            java.lang.String r6 = r4.getDisplayName()
            java.lang.String r6 = com.samsung.android.gallery.module.fileio.util.FileApiUtils.makeBurstShotName(r0, r6)
            java.lang.StringBuilder r7 = i.C0212a.s(r0)
            java.lang.String r8 = java.io.File.separator
            java.lang.String r7 = i.C0212a.p(r7, r8, r6)
            android.util.Pair r6 = android.util.Pair.create(r7, r6)
            r1.add(r6)
            java.lang.String r4 = r4.getCloudServerId()
            android.util.Pair r4 = android.util.Pair.create(r5, r4)
            r2.add(r4)
            int r4 = r11.getFileMode()
            boolean r4 = r9.copyPrimitive(r5, r7, r4)
            if (r4 == 0) goto L_0x0067
            int r4 = r11.getNewGroupId()
            java.util.Map r4 = com.samsung.android.gallery.support.utils.SefFileUtils.getBurstShotSefDataMap(r4)
            boolean r4 = com.samsung.android.gallery.support.utils.SefFileUtils.updateFileWithSef(r4, r7)
            if (r4 != 0) goto L_0x001b
        L_0x0067:
            com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r9 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_LOCAL_FAIL
            return r9
        L_0x006a:
            com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface r3 = r9.getDbOperation()
            r3.updateDatabaseByGroupMediaCopy(r10, r11, r12, r1)
            int r12 = r11.getFileMode()
            r1 = 16
            r3 = 2
            int[] r1 = new int[]{r1, r3}
            boolean r12 = r9.isActiveAll(r12, r1)
            if (r12 == 0) goto L_0x008d
            com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface r9 = r9.getDbOperation()
            com.samsung.android.gallery.module.data.MediaItem r11 = r11.getMediaItem()
            r9.updateDatabaseByCopyInsteadOfGroupMediaMove(r10, r11, r2, r0)
        L_0x008d:
            com.samsung.android.gallery.module.fileio.abstraction.FileOpResult r9 = com.samsung.android.gallery.module.fileio.abstraction.FileOpResult.OP_LOCAL_OK
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.cmh.CmhGroupMediaFileOperation.lambda$copyInternal$0(android.content.Context, com.samsung.android.gallery.module.fileio.type.FileInfo, java.util.List):com.samsung.android.gallery.module.fileio.abstraction.FileOpResult");
    }

    private ArrayList<FileItemInterface> getItems(FileItemInterface fileItemInterface) {
        ArrayList<FileItemInterface> arrayList = new ArrayList<>();
        Cursor query = DbCompat.query(DbKey.FILES_BURSTSHOT, new b(24, fileItemInterface));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(query));
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    /* access modifiers changed from: private */
    public int getStorageTypeFromContentValues(FileItemInterface fileItemInterface) {
        StorageType storageType = StorageType.Cloud;
        if (storageType.equals(fileItemInterface.getStorageType())) {
            return storageType.toInt();
        }
        return StorageType.LocalCloud.toInt();
    }

    /* access modifiers changed from: private */
    /* renamed from: moveOperationCloud */
    public FileOpResult lambda$moveInternal$3(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long j2 = 0;
        for (FileItemInterface next : list) {
            String makeBurstShotName = FileApiUtils.makeBurstShotName(directoryFromPath, next.getDisplayName());
            StringBuilder s = C0212a.s(directoryFromPath);
            s.append(File.separator);
            s.append(makeBurstShotName);
            arrayList2.add(s.toString());
            arrayList.add(next.getCloudServerId());
            j2 += next.getCloudOriginalSize();
        }
        FileOpResult result = FileOpResult.toResult(SamsungCloudCompat.move(context, (ArrayList<String>) arrayList, (ArrayList<String>) arrayList2, true));
        updateProgress(j2);
        return result;
    }

    /* access modifiers changed from: private */
    /* renamed from: moveOperationLocalCloud */
    public FileOpResult lambda$moveInternal$2(Context context, FileInfo fileInfo, List<FileItemInterface> list) {
        if (isActiveMode(fileInfo.getFileMode(), 16)) {
            fileInfo.setNewGroupId(FileApiUtils.makeBurstShotGroupId(context));
            return lambda$copyInternal$0(context, fileInfo, list);
        }
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        ArrayList arrayList = new ArrayList(list.size());
        for (FileItemInterface next : list) {
            String path = next.getPath();
            String makeBurstShotName = FileApiUtils.makeBurstShotName(directoryFromPath, next.getDisplayName());
            String p6 = C0212a.p(C0212a.s(directoryFromPath), File.separator, makeBurstShotName);
            arrayList.add(Pair.create(p6, makeBurstShotName));
            if (!movePrimitive(path, p6)) {
                return FileOpResult.OP_LOCAL_FAIL;
            }
            if (StorageType.LocalCloud.equals(next.getStorageType()) && SamsungCloudCompat.isSyncOff(context, directoryFromPath)) {
                SamsungCloudCompat.delete(context, next.getCloudServerId());
            }
            updateProgress(next.getFileSize());
        }
        getDbOperation().updateDatabaseByGroupMediaMove(context, list, arrayList, directoryFromPath, fileInfo.getFileMode());
        return FileOpResult.OP_LOCAL_OK;
    }

    public FileOpResult copyInternal(Context context, FileInfo fileInfo) {
        Map map = (Map) getItems(fileInfo.getMediaItem()).stream().collect(Collectors.groupingBy(new com.samsung.android.motionphoto.utils.ex.b(18, this)));
        fileInfo.setNewGroupId(FileApiUtils.makeBurstShotGroupId(context));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.LocalCloud.toInt()))).ifPresent(new C0690a(this, context, fileInfo, 2));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.Cloud.toInt()))).ifPresent(new C0690a(this, context, fileInfo, 3));
        updateProgress(0);
        return FileOpResult.OP_LOCAL_OK;
    }

    public int getType() {
        return 2;
    }

    public FileOpResult moveInternal(Context context, FileInfo fileInfo) {
        Map map = (Map) getItems(fileInfo.getMediaItem()).stream().collect(Collectors.groupingBy(new com.samsung.android.motionphoto.utils.ex.b(18, this)));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.LocalCloud.toInt()))).ifPresent(new C0690a(this, context, fileInfo, 0));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.Cloud.toInt()))).ifPresent(new C0690a(this, context, fileInfo, 1));
        updateProgress(0);
        return FileOpResult.OP_LOCAL_OK;
    }

    public boolean support(FileItemInterface fileItemInterface) {
        return fileItemInterface.isBurstShot();
    }

    public String tag() {
        return "CmhGroupMediaFileOp";
    }
}
