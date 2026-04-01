package com.samsung.android.gallery.module.trash.factory;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.trash.abstraction.NoneDestructionOperationType;
import com.samsung.android.gallery.module.trash.abstraction.TrashBaseItem;
import com.samsung.android.gallery.support.providers.MediaUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpVTrashFactory extends MpUTrashFactory {
    public MpVTrashFactory(Context context) {
        super(context);
    }

    public String getOriginFileHash(TrashBaseItem trashBaseItem) {
        Cursor query;
        long fileId = trashBaseItem.getFileId();
        Bundle bundle = new Bundle();
        bundle.putInt("android:query-arg-match-trashed", 1);
        bundle.putString("android:query-arg-sql-selection", a.e(fileId, "_id in (", ")"));
        String str = null;
        try {
            query = this.mReferenceManager.query(MediaUri.getInstance().getSecMediaUri(), new String[]{"original_file_hash"}, bundle);
            if (query != null) {
                if (query.moveToFirst()) {
                    str = query.getString(0);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("getOriginHash failed e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
        throw th;
    }

    public void putExpiredTimeValue(NoneDestructionOperationType noneDestructionOperationType, ContentValues contentValues) {
        if (noneDestructionOperationType == NoneDestructionOperationType.RESTORE) {
            contentValues.putNull("date_expires");
        }
    }
}
