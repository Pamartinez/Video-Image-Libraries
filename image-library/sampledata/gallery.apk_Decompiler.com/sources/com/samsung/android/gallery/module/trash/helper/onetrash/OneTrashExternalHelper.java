package com.samsung.android.gallery.module.trash.helper.onetrash;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.module.trash.helper.TrashExternalHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashExternalHelper extends TrashExternalHelper {
    public OneTrashExternalHelper(Context context) {
        super(context);
    }

    public int deleteCloudRecord() {
        return this.mTrashProvider.deleteTrash("is_cloud = 2 ", (String[]) null);
    }

    public void eraseCloudData(TrashRestoreItem trashRestoreItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_cloud", 1);
        contentValues.putNull("cloud_server_id");
        contentValues.put(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, trashRestoreItem.getRestoreExtraForLocalUpdate());
        if (!updateTrashDb(contentValues, "_data =? ", new String[]{trashRestoreItem.getPath()})) {
            Log.w(this.TAG, "no cloud record erased");
        }
    }

    public void loadTrashDataList(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        StringBuilder sb2;
        String str;
        Cursor trashCursor;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            trashCursor = this.mTrashProvider.getTrashCursor(false);
            if (trashCursor != null) {
                if (trashCursor.moveToFirst()) {
                    int columnIndex = trashCursor.getColumnIndex("_data");
                    int columnIndex2 = trashCursor.getColumnIndex(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
                    do {
                        arrayList.add(trashCursor.getString(columnIndex));
                        String string = trashCursor.getString(columnIndex2);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList2.add(string);
                        }
                    } while (trashCursor.moveToNext());
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
            str = this.TAG;
            sb2 = new StringBuilder("loadTrashDataList +");
        } catch (Exception e) {
            try {
                String str2 = this.TAG;
                Log.e(str2, "loadTrashDataList failed e=" + e.getMessage());
                str = this.TAG;
                sb2 = new StringBuilder("loadTrashDataList +");
            } catch (Throwable th) {
                a.x(new StringBuilder("loadTrashDataList +"), currentTimeMillis, this.TAG);
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        a.x(sb2, currentTimeMillis, str);
        return;
        throw th;
    }

    public String tag() {
        return "OneTrashExternalHelper";
    }
}
