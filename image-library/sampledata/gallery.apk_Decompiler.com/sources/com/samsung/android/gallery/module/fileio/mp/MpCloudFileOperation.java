package com.samsung.android.gallery.module.fileio.mp;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.fileio.abstraction.LocalCloudFileOperation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpCloudFileOperation extends LocalCloudFileOperation {
    public int getType() {
        return 1;
    }

    public boolean support(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isCloudOnly() || fileItemInterface.isGroupShot()) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "MpCloudFileOp";
    }
}
