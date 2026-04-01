package com.samsung.android.gallery.support.exception;

import C0.j;
import D1.f;
import Yd.a;
import Yd.b;
import Yd.c;
import Yd.d;
import a.C0068a;
import ae.C0902a;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DiagMonLogger implements Thread.UncaughtExceptionHandler {
    private final String[] ADDITIONAL_CMDS = {C0086a.i(Process.myPid(), "logcat -d --pid="), "logcat -b events -v threadtime -v printable -v uid -t 200 -d *:v", "logcat -b crash -v threadtime -v printable -v uid -d *:v"};
    private final String ADDITIONAL_FILE_PATH;
    private final String LOG_DIR;
    private final String LOG_FILE_PATH;
    private boolean isAppend = false;

    public DiagMonLogger() {
        String str = AppResources.getAppContext().getFilesDir().getParent() + "/diagmon/";
        this.LOG_DIR = str;
        this.LOG_FILE_PATH = C0212a.A(str, "diagmon.log");
        this.ADDITIONAL_FILE_PATH = C0212a.A(str, "additional.log");
    }

    private String getFooterInfo() {
        PackageInfo packageInfo = PackageMonitorCompat.getInstance().getPackageInfo(AppResources.getAppContext().getPackageName(), 0);
        if (packageInfo == null) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder("versionCode = ");
        sb2.append(packageInfo.versionCode);
        sb2.append("\n");
        for (String str : this.ADDITIONAL_CMDS) {
            sb2.append("----------\n");
            sb2.append(Logger.readProcessOutput(str));
        }
        return sb2.toString();
    }

    private boolean writeFile(File file, Throwable th, String str) {
        PrintStream printStream;
        if (file == null || !file.exists() || th == null) {
            Log.d("DiagMonLogger", "[writeFile] Failed to log into file.");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, this.isAppend);
            try {
                printStream = new PrintStream(fileOutputStream);
                this.isAppend = true;
                if (str != null) {
                    printStream.println(str);
                } else {
                    th.printStackTrace(printStream);
                }
                printStream.close();
                fileOutputStream.close();
                return true;
            } catch (Throwable th2) {
                fileOutputStream.close();
                throw th2;
            }
        } catch (IOException e) {
            Log.e("DiagMonLogger", "[writeFile] : " + e.getMessage());
        } catch (Throwable th3) {
            th2.addSuppressed(th3);
        }
        throw th;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Yd.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v7, types: [Yd.a, java.lang.Object] */
    public DiagMonLogger init(Context context) {
        ? obj = new Object();
        obj.f3915c = "";
        obj.d = "";
        obj.e = "";
        obj.b = context;
        int i2 = 0;
        obj.f3914a = false;
        obj.d = C0068a.G(context);
        if (C0902a.a(context) == 1) {
            ? obj2 = new Object();
            obj2.b = false;
            obj2.f3913a = "";
            obj.f = obj2;
        }
        obj.e = "D";
        if (C0902a.a(context) == 1) {
            a aVar = (a) obj.f;
            String str = (String) obj.e;
            aVar.f3913a = str;
            if ("S".equals(str)) {
                aVar.f3913a = "Y";
            }
            if (aVar.f3913a.isEmpty()) {
                Log.w(C0902a.f3987a, "Empty agreement");
                aVar.b = false;
            } else if ("Y".equals(aVar.f3913a) || "D".equals(aVar.f3913a)) {
                aVar.b = true;
            } else {
                Log.w(C0902a.f3987a, "Wrong agreement : ".concat(str));
                aVar.b = false;
            }
        } else if ("D".equals((String) obj.e) || "S".equals((String) obj.e)) {
            obj.f3914a = true;
        } else {
            obj.f3914a = false;
        }
        obj.f3915c = "sgie2l9fjk";
        b bVar = d.f3916a;
        try {
            i2 = context.getPackageManager().getPackageInfo("com.sec.android.diagmonagent", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            f.o("DMA Client is not exist");
        }
        if (i2 == 0) {
            Log.w(C0902a.f3987a, "It is not supported : NO_DMA");
        } else {
            f.B((Context) obj.b, (String) obj.f3915c);
            if (d.f3917c == c.DEFAULT) {
                f.L("You can't use setConfiguration with enableDefaultConfiguration");
            } else {
                d.f3916a = obj;
                d.f3917c = c.CUSTOM;
                f.n("setConfiguration type : " + d.f3917c);
                try {
                    synchronized (d.class) {
                        d.b = d.a(d.f3916a);
                        i e = i.e();
                        Zd.a aVar2 = new Zd.a(d.f3916a, d.b);
                        e.getClass();
                        i.d(aVar2);
                    }
                } catch (Exception e7) {
                    f.o("failed to setConfiguration" + e7);
                }
            }
        }
        return this;
    }

    public void internalException(Throwable th, String str) {
        this.isAppend = false;
        FileUtils.makeDirectoryIfAbsent(this.LOG_DIR);
        writeFile(FileUtils.createNewFileIfAbsent(this.LOG_FILE_PATH), th, (String) null);
        writeFile(FileUtils.createNewFileIfAbsent(this.ADDITIONAL_FILE_PATH), th, getFooterInfo());
        reportForCrash(str);
        this.isAppend = false;
    }

    public void reportForCrash(String str) {
        if (!DeviceConfig.DEBUG_BINARY && !DeviceConfig.UNIT_TEST) {
            AppResources.getAppContext();
            AppResources.getAppContext();
            j jVar = new j(1);
            jVar.f102c = str;
            jVar.b = this.LOG_DIR;
            b bVar = d.f3916a;
            String str2 = C0902a.f3987a;
            Log.i(str2, "Request CustomEventReport");
            b bVar2 = d.f3916a;
            if (bVar2 == null) {
                Log.w(str2, "You first have to create DiagMonConfiguration");
                Log.w(str2, "CustomEventReport is aborted");
                return;
            }
            f.B((Context) bVar2.b, (String) bVar2.f3915c);
            if (d.f3917c == c.DEFAULT) {
                f.L("You can't use customEventReport with enableDefaultConfiguration");
                return;
            }
            i e = i.e();
            D0.f fVar = new D0.f(d.f3916a, d.b, jVar);
            e.getClass();
            i.d(fVar);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        internalException(th, "FATAL EXCEPTION");
    }
}
