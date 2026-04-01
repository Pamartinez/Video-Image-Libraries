package com.samsung.android.gallery.module.fileio.mp;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.fileio.abstraction.FileOpResult;
import com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.motionphoto.utils.ex.b;
import i9.a;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpGroupMediaFileOperation extends LocalCloudFileOperation {
    /* access modifiers changed from: private */
    public int getStorageTypeFromContentValues(FileItemInterface fileItemInterface) {
        StorageType storageType = StorageType.Cloud;
        if (storageType.equals(fileItemInterface.getStorageType())) {
            return storageType.toInt();
        }
        return StorageType.LocalCloud.toInt();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$copyInternal$0(Context context, FileInfo fileInfo, List list) {
        copyGroupMedia(context, fileInfo, list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$copyInternal$1(Context context, FileInfo fileInfo, List list) {
        copyCloudGroupMedia(context, fileInfo, list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveInternal$2(Context context, FileInfo fileInfo, List list) {
        moveGroupMedia(context, fileInfo, list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveInternal$3(Context context, FileInfo fileInfo, List list) {
        moveCloudGroupMedia(context, fileInfo, list);
    }

    public FileOpResult copyInternal(Context context, FileInfo fileInfo) {
        Map map = (Map) fileInfo.getGroupSubItems().stream().collect(Collectors.groupingBy(new b(24, this)));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.LocalCloud.toInt()))).ifPresent(new a(this, context, fileInfo, 0));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.Cloud.toInt()))).ifPresent(new a(this, context, fileInfo, 1));
        updateProgress(0);
        return FileOpResult.OP_LOCAL_OK;
    }

    public int getType() {
        return 2;
    }

    public FileOpResult moveInternal(Context context, FileInfo fileInfo) {
        Map map = (Map) fileInfo.getGroupSubItems().stream().collect(Collectors.groupingBy(new b(24, this)));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.LocalCloud.toInt()))).ifPresent(new a(this, context, fileInfo, 2));
        Optional.ofNullable((List) map.get(Integer.valueOf(StorageType.Cloud.toInt()))).ifPresent(new a(this, context, fileInfo, 3));
        updateProgress(0);
        return FileOpResult.OP_LOCAL_OK;
    }

    public boolean support(FileItemInterface fileItemInterface) {
        return fileItemInterface.isGroupShot();
    }

    public String tag() {
        return "MpQGroupMediaFileOp";
    }
}
