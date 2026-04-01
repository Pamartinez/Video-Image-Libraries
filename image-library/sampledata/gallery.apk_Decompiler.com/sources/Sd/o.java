package Sd;

import D6.a;
import N2.j;
import android.content.ContentProviderClient;
import android.content.Context;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f3712a = new Object();
    public static volatile Context b;

    /* renamed from: c  reason: collision with root package name */
    public static f f3713c;
    public static volatile Context d;

    public static void a(n nVar) {
        synchronized (f3712a) {
            try {
                if (b == null) {
                    Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournal]", "append: journal not initialized (context is null)");
                    return;
                }
                String format = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                f(b);
                File d2 = d(b, format);
                if (d2 == null) {
                    Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournal]", "append: failed to calculate journal file path for date=" + format);
                    return;
                }
                String w = w(nVar);
                if (w != null) {
                    z(d2, w, nVar);
                }
            } catch (Throwable th) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournal]", "append: exception occurred", th);
            }
        }
    }

    public static void b(StringBuilder sb2, String str, String str2) {
        if (sb2.length() > 1) {
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        C0086a.z(sb2, "\"", str, "\"", NumericEnum.SEP);
        sb2.append(str2);
    }

    public static void c(StringBuilder sb2, String str, String str2) {
        if (sb2.length() > 1) {
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        C0086a.z(sb2, "\"", str, "\"", NumericEnum.SEP);
        j.z(sb2, "\"", str2, "\"");
    }

    public static File d(Context context, String str) {
        try {
            File n = n(context);
            if (n == null) {
                return null;
            }
            return new File(n, "journal_" + str + ".log");
        } catch (Throwable th) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalDirectory]", "calculateJournalFileForDate: exception occurred for date=" + str, th);
            return null;
        }
    }

    public static q e(Context context, ParcelFileDescriptor parcelFileDescriptor, File file) {
        ContentProviderClient acquireUnstableContentProviderClient;
        q o2;
        Bundle bundle = new Bundle();
        bundle.putParcelable("galleryLogPfd", parcelFileDescriptor);
        try {
            acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(l.f3708a);
            if (acquireUnstableContentProviderClient == null) {
                Log.w("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "callSCAppProvider: SC app Provider is not available");
                o2 = q.FAILURE;
                if (acquireUnstableContentProviderClient == null) {
                    return o2;
                }
            } else {
                o2 = o(context, acquireUnstableContentProviderClient.call("com.samsung.android.scloud.gallery", "send_gallery_logs", context.getPackageName(), bundle), file);
            }
            acquireUnstableContentProviderClient.close();
            return o2;
        } catch (Exception e) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "callSCAppProvider: exception during Provider call", e);
            g(file);
            return q.FAILURE;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void f(Context context) {
        try {
            File n = n(context);
            if (n == null) {
                return;
            }
            if (n.exists()) {
                File[] listFiles = n.listFiles();
                if (listFiles != null) {
                    ArrayList arrayList = new ArrayList();
                    for (File file : listFiles) {
                        String name = file.getName();
                        if (name.startsWith("journal_") && name.endsWith(".log")) {
                            arrayList.add(file);
                        }
                    }
                    if (arrayList.size() > 7) {
                        arrayList.sort(new a(6));
                        int size = arrayList.size() - 7;
                        int i2 = 0;
                        for (int i7 = 0; i7 < size; i7++) {
                            File file2 = (File) arrayList.get(i7);
                            String m = m(file2.getName());
                            if (file2.delete()) {
                                i2++;
                            } else {
                                Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "cleanupOldFiles: failed to delete old file. date=" + m);
                            }
                        }
                        if (i2 > 0) {
                            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "cleanupOldFiles: deleted " + i2 + " old file(s) (keeping " + 7 + " files)");
                        }
                    }
                }
            }
        } catch (Throwable th) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "cleanupOldFiles: exception occurred", th);
        }
    }

    public static void g(File file) {
        if (file.exists() && file.getName().equals("journal_merged.tmp")) {
            synchronized (f3712a) {
                if (file.exists()) {
                    if (!file.delete()) {
                        Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "clearMergedFile: failed to delete merged file");
                    }
                }
            }
        }
    }

    public static void h(Context context, File file) {
        if (file.exists()) {
            if (file.getName().equals("journal_merged.tmp")) {
                if (file.exists() && !file.delete()) {
                    Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "clearMergedFile: failed to delete merged file");
                }
                try {
                    File n = n(context);
                    if (n == null) {
                        return;
                    }
                    if (n.exists()) {
                        File[] listFiles = n.listFiles();
                        if (listFiles != null) {
                            int i2 = 0;
                            for (File file2 : listFiles) {
                                String name = file2.getName();
                                if (name.startsWith("journal_") && name.endsWith(".log") && file2.isFile()) {
                                    if (file2.delete()) {
                                        i2++;
                                    } else {
                                        Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "deleteAllDateFiles: failed to delete date file: " + name);
                                    }
                                }
                            }
                            if (i2 > 0) {
                                Log.i("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "deleteAllDateFiles: deleted " + i2 + " date file(s)");
                            }
                        }
                    }
                } catch (Throwable th) {
                    Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "deleteAllDateFiles: exception occurred while deleting date files", th);
                }
            } else if (!file.delete()) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalCleaner]", "clearMergedFileAndDateFiles: failed to delete original file");
            }
        }
    }

    public static void i(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "closePfdSafely: exception closing PFD", e);
            }
        }
    }

    public static void j(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static File k(Context context) {
        if (context == null) {
            try {
                Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournalMerger]", "createMergedJournalFile: appContext is null");
                return null;
            } catch (Throwable th) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalMerger]", "createMergedJournalFile: exception occurred", th);
                return null;
            }
        } else {
            File n = n(context);
            if (n != null) {
                if (n.exists()) {
                    ArrayList arrayList = new ArrayList();
                    File[] listFiles = n.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            for (File file : listFiles) {
                                String name = file.getName();
                                if (name.startsWith("journal_") && name.endsWith(".log") && file.isFile() && file.length() > 0) {
                                    arrayList.add(file);
                                }
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        if (arrayList.size() == 1) {
                            return (File) arrayList.get(0);
                        }
                        return s(n, arrayList);
                    }
                }
            }
            return null;
        }
    }

    public static String l(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder(str.length() * 2);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 9) {
                sb2.append("\\t");
            } else if (charAt == 10) {
                sb2.append("\\n");
            } else if (charAt == 13) {
                sb2.append("\\r");
            } else if (charAt == '\"') {
                sb2.append("\\\"");
            } else if (charAt != '\\') {
                sb2.append(charAt);
            } else {
                sb2.append("\\\\");
            }
        }
        return sb2.toString();
    }

    public static String m(String str) {
        if (str == null || !str.startsWith("journal_") || !str.endsWith(".log")) {
            return null;
        }
        return C0280e.d(4, 8, str);
    }

    public static File n(Context context) {
        try {
            File file = new File(context.getFilesDir(), "scgallery_log");
            if (file.exists() || file.mkdirs() || file.exists()) {
                return file;
            }
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalDirectory]", "getOrCreateJournalDirectory: failed to create journal directory: " + file.getAbsolutePath());
            return null;
        } catch (Throwable th) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalDirectory]", "getOrCreateJournalDirectory: exception occurred", th);
            return null;
        }
    }

    public static q o(Context context, Bundle bundle, File file) {
        if (bundle == null) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "handleProviderResult: Provider call returned null");
            return q.FAILURE;
        }
        int i2 = bundle.getInt("rcode", -1);
        int i7 = bundle.getInt("result", -1);
        if (i2 == 20000000 || i7 == 1) {
            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogSender]", A.a.d(i2, i7, "handleProviderResult: SC app returned success (rcode=", ", resultCode=", ")"));
            synchronized (f3712a) {
                h(context, file);
            }
            return q.SUCCESS;
        }
        Log.w("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "handleProviderResult: Provider call failed with rcode=" + i2 + ", result=" + i7);
        g(file);
        return q.FAILURE;
    }

    public static void p(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogManager]", "initialize: getApplicationContext() returned null, initialization failed");
                d = null;
                return;
            }
            d = applicationContext;
            b = applicationContext;
            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogJournal]", "initialize: journal initialized");
            p.a(applicationContext);
            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogManager]", "initialize: initialization completed successfully");
        } catch (Throwable th) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogManager]", "initialize: exception occurred", th);
            d = null;
        }
    }

    public static void q(int i2, int i7, String str) {
        String str2;
        String str3;
        Integer valueOf;
        if (i2 == 0) {
            str2 = "impression";
        } else if (i2 == 1) {
            str2 = "click";
        } else {
            String str4 = str;
            Log.w("[SCG-SDK][0.0.0019][SCGalleryLogManager]", "logEvent: invalid eventType=" + i2 + ", ignoring");
            return;
        }
        String str5 = str2;
        try {
            String r = r(str);
            if (i7 == -123456) {
                valueOf = null;
            } else {
                valueOf = Integer.valueOf(i7);
            }
            str3 = str;
            try {
                a(new n(str5, r, str3, valueOf, System.currentTimeMillis()));
            } catch (Throwable th) {
                th = th;
                StringBuilder u = C0212a.u("logEvent: exception occurred. inventoryId=", str3, ", eventType=", i2, ", slotId=");
                u.append(i7);
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogManager]", u.toString(), th);
            }
        } catch (Throwable th2) {
            th = th2;
            str3 = str;
            StringBuilder u3 = C0212a.u("logEvent: exception occurred. inventoryId=", str3, ", eventType=", i2, ", slotId=");
            u3.append(i7);
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogManager]", u3.toString(), th);
        }
    }

    public static String r(String str) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    if (!str.endsWith("_tip_card")) {
                        if (!str.endsWith("_tipcard")) {
                            if (str.endsWith("_sync")) {
                                return "gallery_setting";
                            }
                            Log.w("[SCG-SDK][0.0.0019][SCGalleryLogScreenIdMapper]", "mapToScreenId: unknown inventoryId: " + str + ", using default");
                        }
                    }
                    return "gallery_main";
                }
            } catch (Throwable th) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogScreenIdMapper]", "mapToScreenId: exception occurred", th);
                return "gallery_main";
            }
        }
        Log.w("[SCG-SDK][0.0.0019][SCGalleryLogScreenIdMapper]", "mapToScreenId: inventoryId is null or empty, using default");
        return "gallery_main";
    }

    public static File s(File file, ArrayList arrayList) {
        BufferedWriter bufferedWriter;
        arrayList.sort(new a(7));
        File file2 = new File(file, "journal_merged.tmp");
        try {
            int i2 = 0;
            bufferedWriter = new BufferedWriter(new FileWriter(file2, false));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i2 += y(bufferedWriter, (File) it.next());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            if (i2 == 0) {
                file2.delete();
                return null;
            }
            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogJournalMerger]", "mergeMultipleFiles: merged " + arrayList.size() + " files into " + i2 + " lines");
            return file2;
        } catch (IOException e) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalMerger]", "mergeMultipleFiles: failed to create merged file", e);
            if (file2.exists()) {
                file2.delete();
            }
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static File t(Context context) {
        File k;
        synchronized (f3712a) {
            k = k(context);
        }
        if (k == null || !k.exists()) {
            return null;
        }
        long length = k.length();
        if (length == 0) {
            g(k);
            return null;
        }
        Log.i("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "prepareJournalFile: sending journal file, size=" + length);
        return k;
    }

    public static q u(Context context, File file) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
            if (open == null) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "createPfdFromFile: failed to open journal file");
            } else {
                parcelFileDescriptor = open;
            }
        } catch (Exception e) {
            try {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "createPfdFromFile: exception opening journal file", e);
            } catch (Exception e7) {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "sendJournalFileToSCApp: exception opening journal file", e7);
                g(file);
                q qVar = q.FAILURE;
                i(parcelFileDescriptor);
                return qVar;
            } catch (Throwable th) {
                i(parcelFileDescriptor);
                throw th;
            }
        }
        if (parcelFileDescriptor == null) {
            g(file);
            q qVar2 = q.FAILURE;
            i(parcelFileDescriptor);
            return qVar2;
        } else if (parcelFileDescriptor.getStatSize() == 0) {
            Log.w("[SCG-SDK][0.0.0019][SCGalleryLogSender]", "validatePfd: journal file is empty");
            i(parcelFileDescriptor);
            g(file);
            q qVar3 = q.SUCCESS_NO_LOGS;
            i(parcelFileDescriptor);
            return qVar3;
        } else {
            q e8 = e(context, parcelFileDescriptor, file);
            i(parcelFileDescriptor);
            return e8;
        }
    }

    public static void v(int i2, int i7, String str) {
        try {
            if (d == null) {
                Log.w("[SCG-SDK][0.0.0019][SCGalleryLog]", "sendLog: not initialized, ignoring log. inventoryId=" + str + ", eventType=" + i2 + ", slotId=" + i7);
                return;
            }
            if (str != null) {
                if (!str.isEmpty()) {
                    q(i2, i7, str);
                    return;
                }
            }
            Log.w("[SCG-SDK][0.0.0019][SCGalleryLog]", "sendLog: invalid inventoryId (null or empty), ignoring. eventType=" + i2 + ", slotId=" + i7);
        } catch (Throwable th) {
            StringBuilder u = C0212a.u("sendLog: exception occurred. inventoryId=", str, ", eventType=", i2, ", slotId=");
            u.append(i7);
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLog]", u.toString(), th);
        }
    }

    public static String w(n nVar) {
        String str = nVar.f3711c;
        try {
            String x9 = x(nVar);
            if (!x9.trim().isEmpty()) {
                return x9;
            }
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "serializeEvent: JSON string is null or empty. inventoryId=" + str + ", eventType=" + nVar.f3710a);
            return null;
        } catch (JSONException e) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "serializeEvent: JSON serialization failed. inventoryId=" + str, e);
            return null;
        } catch (IllegalArgumentException e7) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "serializeEvent: event validation failed. inventoryId=" + str, e7);
            return null;
        }
    }

    public static String x(n nVar) {
        String str = nVar.f3711c;
        String str2 = nVar.b;
        String str3 = nVar.f3710a;
        if (str != null) {
            StringBuilder s = C0212a.s("{");
            c(s, "eventType", l(str3));
            c(s, "screenId", l(str2));
            c(s, "inventoryId", l(str));
            Integer num = nVar.d;
            if (num != null) {
                b(s, "slotId", Integer.toString(num.intValue()));
            }
            b(s, "createdAt", Long.toString(nVar.e));
            s.append("}");
            return s.toString();
        }
        throw new IllegalArgumentException("inventoryId cannot be null");
    }

    public static int y(BufferedWriter bufferedWriter, File file) {
        BufferedReader bufferedReader;
        int i2 = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return i2;
                } else if (!readLine.trim().isEmpty()) {
                    bufferedWriter.write(readLine);
                    bufferedWriter.newLine();
                    i2++;
                }
            }
        } catch (IOException e) {
            Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournalMerger]", "writeFileContent: failed to read file: " + file.getName(), e);
            return i2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void z(File file, String str, n nVar) {
        BufferedWriter bufferedWriter;
        try {
            File parentFile = file.getParentFile();
            if (parentFile == null || parentFile.exists() || parentFile.mkdirs() || parentFile.exists()) {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                if (!file.exists()) {
                    Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "writeToFile: file was not created after write. path=" + file.getAbsolutePath());
                    return;
                } else if (file.length() == 0) {
                    Log.w("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "writeToFile: file exists but size is 0. path=" + file.getAbsolutePath());
                    return;
                } else {
                    return;
                }
            } else {
                Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "writeToFile: failed to create parent directory: " + parentFile.getAbsolutePath());
                return;
            }
        } catch (IOException e) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogJournalWriter]", "writeToFile: file write failed. inventoryId=" + nVar.f3711c + ", path=" + file.getAbsolutePath(), e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
