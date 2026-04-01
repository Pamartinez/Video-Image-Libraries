package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MyFilesApi {
    static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_MY_FILES_API);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UriHolder {
        /* access modifiers changed from: private */
        public static final Uri URI = Uri.parse("content://myfiles/apps_get_file_list");
    }

    public static List<String> list(String... strArr) {
        String[] strArr2;
        Cursor query;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        try {
            strArr2 = strArr;
            try {
                query = AppResources.getAppContext().getContentResolver().query(UriHolder.URI, (String[]) null, (String) null, strArr2, (String) null, (CancellationSignal) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("_data");
                        do {
                            arrayList.add(query.getString(columnIndex));
                        } while (query.moveToNext());
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                a.A(new Object[]{"", Integer.valueOf(strArr2.length), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("list"), "MyFilesApi");
                return arrayList;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception e7) {
            e = e7;
            strArr2 = strArr;
            e.printStackTrace();
            a.A(new Object[]{"", Integer.valueOf(strArr2.length), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("list"), "MyFilesApi");
            return arrayList;
        }
        a.A(new Object[]{"", Integer.valueOf(strArr2.length), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("list"), "MyFilesApi");
        return arrayList;
        throw th;
    }

    public static void loadListFiles1stDepth(ArrayList<String> arrayList, String... strArr) {
        for (String next : list(strArr)) {
            if (new File(next).isFile()) {
                arrayList.add(next);
            }
        }
    }

    public static boolean makeDirectoryIfAbsent(File file) {
        boolean z;
        String str;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle call = AppResources.getAppContext().getContentResolver().call(Uri.parse("content://myfiles"), "CREATE_FOLDER", file.getAbsolutePath(), (Bundle) null);
            if (call == null || !call.getBoolean("result")) {
                z = false;
            } else {
                z = true;
            }
            String str2 = "";
            if (z) {
                str = str2;
            } else if (call != null) {
                str = "Bundle[" + call.getBoolean("exist") + ',' + call.getBoolean("permission") + ']';
            } else {
                str = "Bundle[null]";
            }
            StringBuilder sb2 = new StringBuilder("makeDirectoryIfAbsent");
            sb2.append(Logger.vt(Boolean.valueOf(z), Boolean.valueOf(file.exists()), str, Long.valueOf(currentTimeMillis)));
            if (!z) {
                str2 = " " + Logger.getEncodedString(file.getAbsolutePath());
            }
            sb2.append(str2);
            Log.d("MyFilesApi", sb2.toString());
        } catch (Exception e) {
            j.s(e, new StringBuilder("makeDirectoryIfAbsent failed. e="), "MyFilesApi");
        }
        return file.exists();
    }

    public static void updateTrashState(boolean z) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean("is_empty_trash", z);
                Log.d("MyFilesApi", "updateTrashState : isEmpty [" + z + "]");
                AppResources.getAppContext().getContentResolver().call(Uri.parse("content://myfiles/"), "SET_TRASH_STATUS", (String) null, bundle);
            } catch (Exception e) {
                Log.e("MyFilesApi", e.toString());
            }
        }
    }
}
