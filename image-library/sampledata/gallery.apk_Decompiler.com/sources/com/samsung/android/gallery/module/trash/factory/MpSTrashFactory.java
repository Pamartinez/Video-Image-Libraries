package com.samsung.android.gallery.module.trash.factory;

import Ya.a;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import i.C0212a;
import java.io.File;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpSTrashFactory extends MpRTrashFactory {
    public MpSTrashFactory(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getInternalTrash$0(HashMap hashMap, String str, String str2) {
        StringBuilder s = C0212a.s(str2);
        String str3 = File.separator;
        C0086a.z(s, str3, "Android", str3, str);
        hashMap.put(str2, new File(C0212a.p(s, str3, "com.sec.android.gallery3d")));
    }

    public HashMap<String, File> getInternalTrash(String str) {
        HashMap<String, File> hashMap = new HashMap<>();
        FileUtils.getExternalStorageNameList().forEach(new a(1, str, hashMap));
        return hashMap;
    }

    public String tag() {
        return "MpSTrashFactory";
    }

    public boolean useDBInsertAfter(TrashRestoreItem trashRestoreItem) {
        if (trashRestoreItem.getStorageType() != StorageType.LocalCloud) {
            return true;
        }
        return false;
    }

    public boolean useDBInsertFirst(TrashRestoreItem trashRestoreItem) {
        if (trashRestoreItem.getStorageType() == StorageType.LocalCloud) {
            return true;
        }
        return false;
    }
}
