package com.samsung.android.gallery.module.fileio.compat;

import A.a;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.fileio.database.abstraction.ContentValuesFactory;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SecMediaStoreApi {
    static final Uri FILES_URI = MediaUri.getInstance().getSecMediaUri();
    static final Uri SEC_CLOUD_URI = MediaUri.getInstance().getSecCloudUri();

    public static boolean deleteCloudByServerId(String str) {
        if (AppResources.getAppContext().getContentResolver().delete(SEC_CLOUD_URI, "cloud_server_id=?", new String[]{str}) > 0) {
            return true;
        }
        Log.e("SecMediaStoreApi", "delete by s-id failed " + Logger.getEncodedString(str));
        return false;
    }

    public static boolean deleteCloudByServerPath(String str) {
        if (AppResources.getAppContext().getContentResolver().delete(SEC_CLOUD_URI, "cloud_server_path=?", new String[]{str}) > 0) {
            return true;
        }
        Log.e("SecMediaStoreApi", "delete by s-path failed " + Logger.getEncodedString(str));
        return false;
    }

    public static void load(String str, String[] strArr, int i2, List<String> list, Consumer<Cursor> consumer) {
        Cursor query;
        int i7;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder o2 = C0086a.o(i2, "bucket_id=", " AND _display_name IN (");
        o2.append(TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, Collections.nCopies(list.size(), "?")));
        o2.append(")");
        try {
            query = AppResources.getAppContext().getContentResolver().query(FILES_URI, strArr, o2.toString(), (String[]) list.toArray(new String[0]), (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        consumer.accept(query);
                    } while (query.moveToNext());
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("load");
            Integer valueOf = Integer.valueOf(i2);
            if (query != null) {
                i7 = query.getCount();
            } else {
                i7 = -1;
            }
            sb2.append(Logger.vt(valueOf, Integer.valueOf(i7), Long.valueOf(currentTimeMillis)));
            Log.d("SecMediaStoreApi", sb2.toString());
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            a.s(e, new StringBuilder("load failed. e="), "SecMediaStoreApi");
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static boolean renameCloud(FileItemInterface fileItemInterface, String str) {
        if (AppResources.getAppContext().getContentResolver().update(ContentUris.withAppendedId(SEC_CLOUD_URI, fileItemInterface.getFileId()), ContentValuesFactory.getFactory().getMoveValues(fileItemInterface, str, -1), (String) null, (String[]) null) > 0) {
            return true;
        }
        return false;
    }
}
