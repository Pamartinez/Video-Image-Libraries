package com.samsung.android.gallery.module.media;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import i.C0212a;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoConversionHelper {
    private final Context mAppContext;

    public VideoConversionHelper(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    private String getOriginalPath(FileItemInterface fileItemInterface) {
        if (fileItemInterface.isCloudOnly()) {
            return fileItemInterface.getCloudData2();
        }
        return fileItemInterface.getPath();
    }

    public String getFilePath(FileItemInterface fileItemInterface, String str) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(FileUtils.getBaseName(getOriginalPath(fileItemInterface)));
        s.append(O3DPConstant.MP4_EXTENSION);
        return s.toString();
    }

    public void savePath(String str) {
        PendingShare.set(str);
    }

    public void updateDatabase(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues c5 = C0086a.c("__absPath", str);
        c5.put("date_added", Long.valueOf(currentTimeMillis));
        new LocalProviderHelper(this.mAppContext.getContentResolver()).insertConvertedFile(c5);
        a.x(new StringBuilder("insert to local db +"), currentTimeMillis, "VideoConversionHelper");
    }
}
