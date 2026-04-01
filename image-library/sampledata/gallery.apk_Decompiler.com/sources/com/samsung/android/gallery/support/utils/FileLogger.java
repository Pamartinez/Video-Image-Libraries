package com.samsung.android.gallery.support.utils;

import android.os.Process;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileLogger {
    private static final ArrayList<String> sList = new ArrayList<>();
    File file;
    Path filePath;

    public FileLogger(String str) {
        File file2 = new File(str);
        this.file = file2;
        if (!file2.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.filePath = this.file.toPath();
    }

    public static void add(String str) {
        try {
            sList.add(str);
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
    }

    private static void backupFileLogger(String str, String str2) {
        String A10 = C0212a.A(str, str2);
        if (FileUtils.exists(A10) && FileUtils.length(A10) > 10485760) {
            String B = C0212a.B(str, "keep.", str2);
            if (FileUtils.exists(B)) {
                FileUtils.delete(B);
            }
            FileUtils.rename(A10, B);
        }
    }

    public static String buildLog(CharSequence charSequence, String str) {
        return BiDirectionUnicode.LEFT_TO_RIGHT_MARK + TimeUtil.getTimestampMillis() + " " + Process.myPid() + "-" + Process.myTid() + " [" + charSequence + "] " + str + "\n";
    }

    public static void dumpToFile(String str) {
        ThreadUtil.postOnBgThread(new C0673k(str, 0));
    }

    /* access modifiers changed from: private */
    public static void dumpToFileAsync(String str) {
        try {
            if (PerformanceLog.isEnabled()) {
                ArrayList<String> arrayList = sList;
                if (arrayList.size() > 0) {
                    String str2 = getDefaultPath() + File.separator;
                    backupFileLogger(str2, str);
                    GsonTool.appendObjectToJson(str2 + str, arrayList);
                    arrayList.clear();
                }
            }
        } catch (Exception e) {
            Log.e("FileLogger", e.getMessage());
        }
    }

    public static String getDefaultPath() {
        return StorageInfo.getDefault().getMediaPath("");
    }

    public void append(String str) {
        Files.write(this.filePath, str.getBytes(StandardCharsets.UTF_8), new OpenOption[]{StandardOpenOption.APPEND});
    }
}
