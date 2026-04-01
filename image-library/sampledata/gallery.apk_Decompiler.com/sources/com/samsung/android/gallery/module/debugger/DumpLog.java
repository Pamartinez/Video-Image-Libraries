package com.samsung.android.gallery.module.debugger;

import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import i.C0212a;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DumpLog {
    public static void dumpLocalProvider(String str) {
        LocalProviderDebugHelper.dump(AppResources.getAppContext(), str);
    }

    private static void dumpLocalProviderLog(String str) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        PrintWriter printWriter;
        try {
            fileWriter = new FileWriter(str, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.write("[dump local provider : LocalProvider]\n");
            new LocalProviderDebugHelper(AppResources.getAppContext()).dumpLog(AppResources.getAppContext(), printWriter);
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            return;
            throw th;
            throw th;
            throw th;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static void dumpLogcat(String str) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append("logcat.log");
        String sb2 = s.toString();
        Features.printDebug();
        PreferenceFeatures.printDebug();
        PackageMonitorCompat.printDebug();
        dumpLogcat(sb2, new String[]{"logcat -d", "logcat -b events -v threadtime -v printable -v uid -t 200 -d *:v", "logcat -b crash -v threadtime -v printable -v uid -d *:v"});
        dumpLocalProviderLog(sb2);
    }

    private static void dumpLogcat(String str, String[] strArr) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(str), true);
            for (String str2 : strArr) {
                Log.e("DumpLog", str2);
                String readProcessOutput = Logger.readProcessOutput(str2);
                fileOutputStream.write(("[" + str2 + "]\n").getBytes());
                fileOutputStream.write(readProcessOutput.getBytes());
            }
            fileOutputStream.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
