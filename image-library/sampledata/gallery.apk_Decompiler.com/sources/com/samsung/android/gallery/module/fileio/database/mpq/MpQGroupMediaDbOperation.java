package com.samsung.android.gallery.module.fileio.database.mpq;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.fileio.database.abstraction.DbOperationInterface;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOpObject;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.module.fileio.type.FileInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpQGroupMediaDbOperation implements DbOperationInterface {
    public void updateDatabaseByCopyInsteadOfGroupMediaMove(Context context, FileItemInterface fileItemInterface, ArrayList<Pair<String, String>> arrayList, String str) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<Pair<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next().first;
            arrayList2.add(ContentProviderOperation.newDelete(getFilesUri(str2)).withSelection("_data = ?", new String[]{str2}).build());
        }
        getDatabaseOperation(context).add(DatabaseOpObject.delete((Uri) null).addValues(arrayList2.toArray(new ContentProviderOperation[0])));
        if (fileItemInterface.getStorageType() == StorageType.LocalCloud) {
            arrayList2.clear();
            Iterator<Pair<String, String>> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList2.add(ContentProviderOperation.newDelete(getCloudUri()).withSelection("cloud_server_id = ?", new String[]{String.valueOf(it2.next().second)}).build());
            }
            getDatabaseOperation(context).add(DatabaseOpObject.delete((Uri) null).addValues(arrayList2.toArray(new ContentProviderOperation[0])));
        }
    }

    public void updateDatabaseByGroupMediaCopy(Context context, FileInfo fileInfo, List<FileItemInterface> list, ArrayList<Pair<String, String>> arrayList) {
        Uri uri;
        boolean isCloudOnly = list.get(0).isCloudOnly();
        String directoryFromPath = FileUtils.getDirectoryFromPath(fileInfo.getDestPath(), false);
        DatabaseOperation databaseOperation = getDatabaseOperation(context);
        if (isCloudOnly) {
            uri = getCloudUri();
        } else {
            uri = getFilesUri(fileInfo.getDestPath());
        }
        databaseOperation.add(DatabaseOpObject.burkInsert(uri).setImmediate(isCloudOnly).addValues(getContentValues().getGroupMediaCopyValueArray(list, arrayList, directoryFromPath, fileInfo.getNewGroupId(), fileInfo.getFileMode())));
    }

    public void updateDatabaseByGroupMediaMove(Context context, List<FileItemInterface> list, ArrayList<Pair<String, String>> arrayList, String str, int i2) {
        getDatabaseOperation(context).add(DatabaseOpObject.update((Uri) null).setImmediate(list.get(0).isCloudOnly()).addValues(getContentValues().getGroupMediaMoveValueArray(list, arrayList, str, i2)));
    }
}
