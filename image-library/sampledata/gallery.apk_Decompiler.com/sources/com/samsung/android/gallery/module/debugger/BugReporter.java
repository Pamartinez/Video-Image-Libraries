package com.samsung.android.gallery.module.debugger;

import A.a;
import F3.b;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbDump;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.ZipCompat;
import i.C0212a;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BugReporter {
    private static final HashMap<Integer, String> sDecodeFailDirPath = new HashMap<>();
    private static final HashSet<String> sDecodeFailFilePath = new HashSet<>();
    private final CharSequence mDate;
    private final String mDumpPath;
    private final String mRoot;
    private final String mZipFile;

    public BugReporter(Context context) {
        String externalFilesDir = FileUtils.getExternalFilesDir("dump");
        this.mRoot = externalFilesDir;
        String fileTimestamp = TimeUtil.getFileTimestamp();
        this.mDate = fileTimestamp;
        StringBuilder s = C0212a.s(externalFilesDir);
        String str = File.separator;
        s.append(str);
        s.append(fileTimestamp);
        String sb2 = s.toString();
        this.mDumpPath = sb2;
        this.mZipFile = getDefaultPath() + str + "gallery-" + PackageMonitorCompat.getVersionName() + "-" + fileTimestamp + ".zip";
        a.u("BugReporter#construct ", sb2, "BugReporter");
    }

    public static void addDecodeFailPath(int i2, String str) {
        HashMap<Integer, String> hashMap = sDecodeFailDirPath;
        synchronized (hashMap) {
            try {
                HashSet<String> hashSet = sDecodeFailFilePath;
                if (hashSet.size() < 3 && FileUtils.isInRemovableStorage(str) && str != null && str.toLowerCase().endsWith("jpg")) {
                    hashSet.add(str);
                    hashMap.put(Integer.valueOf(i2), FileUtils.getDirectoryFromPath(str, false));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void showToast(Context context, String str) {
        if (context != null) {
            ThreadUtil.postOnUiThread(new b(context, str, 4));
        }
    }

    public static String validateJpgFile(String str, String str2) {
        DataInputStream dataInputStream;
        String str3;
        StringBuilder sb2 = new StringBuilder("validate file : ");
        File file = new File(str);
        if (str2 == null) {
            str2 = str;
        }
        sb2.append(str2);
        sb2.append(", exist=");
        sb2.append(file.exists());
        sb2.append(", size=");
        sb2.append(file.length());
        try {
            dataInputStream = new DataInputStream(new FileInputStream(str));
            int min = Math.min(128, (int) file.length());
            byte[] bArr = new byte[128];
            boolean z = false;
            int read = dataInputStream.read(bArr, 0, min);
            if (read == min) {
                z = true;
            }
            sb2.append(", read : ");
            if (z) {
                str3 = "valid";
            } else {
                str3 = "invalid(" + read + ")";
            }
            sb2.append(str3);
            int i2 = ByteBuffer.wrap(Arrays.copyOf(bArr, 4)).getInt();
            if (!(i2 == -2555936 || i2 == -2555935)) {
                if (i2 != -2555928) {
                    sb2.append(", header(non-jpeg) : 0x");
                    sb2.append(Integer.toHexString(i2));
                    sb2.append(System.lineSeparator());
                    sb2.append(StringCompat.valueOf(bArr, 64));
                    sb2.append(System.lineSeparator());
                    dataInputStream.close();
                    String sb3 = sb2.toString();
                    Log.e("BugReporter", sb3);
                    return sb3;
                }
            }
            sb2.append(", header(jpeg) : 0x");
            sb2.append(Integer.toHexString(i2));
            sb2.append(System.lineSeparator());
            sb2.append(StringCompat.valueOf(bArr, 64));
            sb2.append(System.lineSeparator());
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sb2.append(",file not found");
        } catch (IOException e7) {
            e7.printStackTrace();
            sb2.append(",io exception : ");
            sb2.append(e7.getMessage());
        } catch (Error | Exception e8) {
            try {
                sb2.append(", exception : ");
                sb2.append(e8.getMessage());
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        String sb32 = sb2.toString();
        Log.e("BugReporter", sb32);
        return sb32;
        throw th;
    }

    public void archiveCache(ZipCompat.OnProgressListener onProgressListener) {
        if (AppResources.getAppContext() == null) {
            Log.e("BugReporter", "archiveCache failed. null context");
            return;
        }
        try {
            String str = this.mRoot + File.separator + "gallery-" + PackageMonitorCompat.getVersionName() + "-cache-" + this.mDate + ".zip";
            ZipCompat zipCompat = new ZipCompat(AppResources.getAppContext().getCacheDir().getAbsolutePath());
            showToast(AppResources.getAppContext(), "start archiving disk-cache : " + zipCompat.getTotalCount() + " file(s)");
            zipCompat.archiveFolder(str, onProgressListener);
            showToast(AppResources.getAppContext(), "archiving disk-cache completed\n/Internal storage/" + FileUtils.getNameFromPath(str));
        } catch (Exception e) {
            a.s(e, new StringBuilder("archiveCache failed. e="), "BugReporter");
        }
    }

    public String archiveLogs(boolean z) {
        return archiveLogs(z, false);
    }

    public String getDefaultPath() {
        return StorageInfo.getDefault().getMediaPath("");
    }

    public String archiveLogs(boolean z, boolean z3) {
        long currentTimeMillis = System.currentTimeMillis();
        FileUtils.deleteDirectory(new File(this.mRoot));
        FileUtils.makeDirectoryIfAbsent(this.mDumpPath);
        FileUtils.makeDirectoryIfAbsent(this.mDumpPath + "/local");
        if (z) {
            DbDump.dumpTables(this.mDumpPath);
            DumpLog.dumpLocalProvider(this.mDumpPath + "/local");
            DbDump.createDatabase(this.mDumpPath + "/local", "local");
        }
        if (z3) {
            PrivateDatabase instance = PrivateDatabase.getInstance();
            instance.backup(this.mDumpPath + "/private/secured.db", "*/*");
        }
        Blackboard.getApplicationInstance().publish("debug://DumpDisplayMessage", "dump logcat");
        DumpLog.dumpLogcat(this.mDumpPath);
        Blackboard.getApplicationInstance().publish("debug://DumpDisplayMessage", "archive to zip");
        ZipCompat.archive(this.mZipFile, this.mDumpPath);
        Log.d("BugReporter", C0086a.j(currentTimeMillis, this.mZipFile, "} +", new StringBuilder("Dump completed {")));
        return this.mZipFile;
    }

    public static void saveLastDump() {
    }
}
