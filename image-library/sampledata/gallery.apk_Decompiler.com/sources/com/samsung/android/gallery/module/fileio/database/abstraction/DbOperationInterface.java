package com.samsung.android.gallery.module.fileio.database.abstraction;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DbOperationInterface {
    Uri getCloudUri() {
        return MediaUri.getInstance().getSecCloudUri();
    }

    ContentValuesFactory getContentValues() {
        return ContentValuesFactory.getFactory();
    }

    DatabaseOperation getDatabaseOperation(Context context) {
        return DatabaseOperation.getInstance(context);
    }

    Uri getFilesUri() {
        return MediaUri.getInstance().getFilesUri();
    }

    Uri getImageUri() {
        return MediaUri.getInstance().getImageUri();
    }

    Uri getVideoUri() {
        return MediaUri.getInstance().getVideoUri();
    }

    void unsupported() {
        if (!DeviceConfig.UNIT_TEST) {
            throw new IllegalStateException("not supported function");
        }
    }

    void updateDatabaseByCopy(Context context, FileInfo fileInfo) {
        unsupported();
    }

    void updateDatabaseByCopyInsteadOfGroupMediaMove(Context context, FileItemInterface fileItemInterface, ArrayList<Pair<String, String>> arrayList, String str) {
        unsupported();
    }

    void updateDatabaseByCopyInsteadOfMove(Context context, FileInfo fileInfo) {
        unsupported();
    }

    void updateDatabaseByGroupMediaCopy(Context context, FileInfo fileInfo, List<FileItemInterface> list, ArrayList<Pair<String, String>> arrayList) {
        unsupported();
    }

    void updateDatabaseByGroupMediaMove(Context context, List<FileItemInterface> list, ArrayList<Pair<String, String>> arrayList, String str, int i2) {
        unsupported();
    }

    void updateDatabaseByMove(Context context, FileInfo fileInfo) {
        unsupported();
    }

    void updateDatabaseByOverWrite(Context context, FileInfo fileInfo) {
        unsupported();
    }

    Uri getFilesUri(String str) {
        return MediaUri.getFilesUri(str);
    }
}
