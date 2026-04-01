package com.samsung.android.gallery.module.fileio;

import com.samsung.android.gallery.module.fileio.abstraction.FileOperation;
import com.samsung.android.gallery.module.fileio.cmh.CmhCloudFileOperation;
import com.samsung.android.gallery.module.fileio.cmh.CmhGroupMediaFileOperation;
import com.samsung.android.gallery.module.fileio.cmh.CmhLocalCloudFileOperation;
import com.samsung.android.gallery.module.fileio.mp.MpCloudFileOperation;
import com.samsung.android.gallery.module.fileio.mp.MpGroupMediaFileOperation;
import com.samsung.android.gallery.module.fileio.mp.MpLocalCloudFileOperation;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileOperationFactory {
    public static FileOperation createCompat() {
        return (FileOperation) new ChainBuilder().append(new MpCloudFileOperation()).append(new MpLocalCloudFileOperation()).append(new MpGroupMediaFileOperation()).build();
    }

    public static FileOperation createCompat9() {
        return (FileOperation) new ChainBuilder().append(new CmhCloudFileOperation()).append(new CmhLocalCloudFileOperation()).append(new CmhGroupMediaFileOperation()).build();
    }

    public static FileOperation getInstance() {
        if (Features.isEnabled(Features.USE_NEWMP)) {
            return createCompat();
        }
        return createCompat9();
    }
}
