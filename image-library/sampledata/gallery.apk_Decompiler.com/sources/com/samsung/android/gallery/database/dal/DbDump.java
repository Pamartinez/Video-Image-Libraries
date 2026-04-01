package com.samsung.android.gallery.database.dal;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.CmhCompat;
import com.samsung.android.gallery.database.dal.mp.SecMpCompat;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i.C0212a;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import k8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DbDump {
    public static Pair<String, String>[] buildTimelineCountDetail() {
        Cursor rawQuery = new SecMpQueryExecutor().rawQuery("select media_type, count(*) from files where media_type in (1,3) group by media_type", "sa count");
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    long j2 = 0;
                    long j3 = 0;
                    do {
                        int i2 = rawQuery.getInt(1);
                        if (rawQuery.getInt(0) == 1) {
                            j2 = (long) i2;
                        } else {
                            j3 = (long) i2;
                        }
                    } while (rawQuery.moveToNext());
                    Pair<String, String>[] pairArr = {new Pair(AnalyticsDetailKey.IMAGE_COUNT.toString(), AnalyticsUtils.convertLargeNumberToString(j2)), new Pair(AnalyticsDetailKey.VIDEO_COUNT.toString(), AnalyticsUtils.convertSmallNumberToString(j3))};
                    rawQuery.close();
                    return pairArr;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (rawQuery == null) {
            return null;
        }
        rawQuery.close();
        return null;
        throw th;
    }

    public static void createDatabase(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = str + "/" + str2 + ".db";
        Blackboard.getApplicationInstance().publish("debug://DumpDisplayMessage", "createDatabase : " + str2);
        try {
            FileUtils.rename(str + "/tag_view.sql", str + "/tag_view.sql_backup");
            String str4 = str + "/all_" + str2;
            execCommand("echo 'begin transaction;' > " + str4);
            Blackboard.getApplicationInstance().publish("debug://DumpDisplayMessage", "createDatabase : merge sql " + str2);
            execCommand("cat " + str + "/*.sql >> " + str4);
            execCommand("echo 'commit;' >> " + str4);
            Log.v("DbDump", "all_sql : ", Logger.vt(Long.valueOf(FileUtils.length(str4)), Long.valueOf(currentTimeMillis)));
            FileUtils.rename(str + "/files_ged.sql_ged", str + "/files_ged.sql");
            FileUtils.rename(str4, str4 + ".sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("DbDump", "done\n", Logger.vt(Long.valueOf(FileUtils.length(str3)), Long.valueOf(currentTimeMillis)));
    }

    private static void dumpGedMp(String str) {
        CursorHelper.dumpTableToFile(AppResources.getAppContext(), MediaUri.getInstance().getFilesUri(), "files", str, "_ged");
    }

    public static void dumpQuery(String str, String str2, boolean z) {
        Logger.writeStringToFile(str2, TimeUtil.getTimestamp() + System.lineSeparator() + new SecMpQueryExecutor().logQuery(str), z);
    }

    public static void dumpTables(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getDumpPath();
        }
        FileUtils.makeDirectoryIfAbsent(str);
        FileUtils.makeDirectoryIfAbsent(str + "/mp");
        FileUtils.makeDirectoryIfAbsent(str + "/ged");
        SecMpCompat secMpCompat = new SecMpCompat(new QueryParams());
        secMpCompat.dump(str + "/mp");
        dumpTrashMp(str + "/mp");
        dumpGedMp(C0212a.A(str, "/ged"));
        createDatabase(str + "/mp", "mp");
        createDatabase(str + "/ged", "ged");
        if (!Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            FileUtils.makeDirectoryIfAbsent(str + "/cmh");
            CmhCompat cmhCompat = new CmhCompat(new QueryParams());
            cmhCompat.dump(str + "/cmh");
            createDatabase(str + "/cmh", "cmh");
        }
    }

    private static void dumpTrashMp(String str) {
        if (MediaUri.getInstance().getSecTrashUri() != null) {
            CursorHelper.dumpTableToFile(AppResources.getAppContext(), MediaUri.getInstance().getSecTrashUri(), "trashes", str, "");
        }
    }

    private static void execCommand(String str) {
        Process exec = Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", str});
        LatchBuilder latchBuilder = new LatchBuilder("db");
        latchBuilder.setCurrent(new a(exec, 0));
        latchBuilder.addWorker(new a(exec, 1));
        Objects.requireNonNull(exec);
        latchBuilder.setPostExecutor((Runnable) new a(exec, 2));
        latchBuilder.setTimeout(20000);
        latchBuilder.start();
    }

    private static String getDumpPath() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().download);
        return C0212a.p(sb2, File.separator, "SamsungGallery");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execCommand$0(Process process) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    Log.d("execCommand", "std=" + readLine);
                } else {
                    bufferedReader.close();
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execCommand$1(Process process) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            if (bufferedReader.ready()) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        Log.d("execCommand", "err=" + readLine);
                    }
                }
                bufferedReader.close();
                return;
            }
            bufferedReader.close();
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
