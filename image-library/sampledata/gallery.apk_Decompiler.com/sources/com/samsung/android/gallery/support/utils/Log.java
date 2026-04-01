package com.samsung.android.gallery.support.utils;

import N2.j;
import android.os.Process;
import c0.C0086a;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Log {
    private static final AndroidPrinter PRINTER;
    private static final String[] SPACE_ARRAY = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    ", "                     ", "                      ", "                       ", "                        "};
    private static final boolean USE_REMOTE_LOG = false;
    private static CircularQueue<String> sErrorLogs;
    private static boolean sLogCallback;
    private static int sLogIndex;
    private static final CircularQueue<String> sMajorEvents = new CircularQueue<>(100, true);
    private static long sPerformanceLogInitTime;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AndroidPrinter {
        public void loge(String str, String str2) {
            android.util.Log.e(str, str2);
        }

        public void logi(String str, String str2) {
            android.util.Log.i(str, str2);
        }

        public void logv(String str, String str2) {
            android.util.Log.v(str, str2);
        }

        public void logw(String str, String str2) {
            android.util.Log.w(str, str2);
        }

        public void loge(String str, String str2, Throwable th) {
            android.util.Log.e(str, str2, th);
        }

        public void logw(String str, String str2, Throwable th) {
            android.util.Log.w(str, str2, th);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemoteServerHolder {
        static String address = getAddress();

        public static String getAddress() {
            String systemPropertiesString = SeApiCompat.getSystemPropertiesString("gallery.livelog.ip", "localhost");
            j.w("REMOTE LIVE LOG SERVER_ADDRESS = ", systemPropertiesString, "LiveLog");
            return systemPropertiesString;
        }
    }

    static {
        AndroidPrinter androidPrinter;
        if (DeviceConfig.UNIT_TEST) {
            androidPrinter = new JavaPrinter();
        } else {
            androidPrinter = new AndroidPrinter();
        }
        PRINTER = androidPrinter;
    }

    private static String addPerformanceLogElapsedTime(String str) {
        long currentTimeMillis = System.currentTimeMillis() - sPerformanceLogInitTime;
        if (currentTimeMillis < 4000) {
            return str + " ++" + currentTimeMillis;
        }
        sPerformanceLogInitTime = -1;
        return str;
    }

    public static void at(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryAT", getMsg(charSequence, str));
    }

    public static void ate(CharSequence charSequence, String str) {
        PRINTER.loge("GalleryAT", getMsg(charSequence, str));
    }

    public static void atw(CharSequence charSequence, String str) {
        PRINTER.logw("GalleryAT", getMsg(charSequence, str));
    }

    public static void b(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.logi("GalleryB", getMsg(charSequence, s.toString()));
    }

    public static void be(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.loge("GalleryB", getMsg(charSequence, s.toString()));
    }

    public static void bx(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryBX", getMsg(charSequence, str));
    }

    public static void bxe(CharSequence charSequence, String str) {
        PRINTER.loge("GalleryBX", getMsg(charSequence, str));
    }

    public static void d(CharSequence charSequence, String str) {
        PRINTER.logi("Gallery", getMsg(charSequence, str));
    }

    public static void dumpHistoricalLogs(PrintWriter printWriter) {
        try {
            ArrayList<String> lastErrors = getLastErrors();
            printWriter.println("== last errors ==");
            Iterator<String> it = lastErrors.iterator();
            while (it.hasNext()) {
                printWriter.println(it.next());
            }
            ArrayList<String> majorEvents = getMajorEvents();
            printWriter.println("== OPERATION HISTORY ==");
            Iterator<String> it2 = majorEvents.iterator();
            while (it2.hasNext()) {
                printWriter.println(it2.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(CharSequence charSequence, String str, Object... objArr) {
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        String msg = getMsg(charSequence, s.toString());
        PRINTER.loge("Gallery", msg);
        saveErrorLogs(msg);
    }

    public static void enableSaveErrorLogs() {
        if (sErrorLogs == null) {
            sErrorLogs = new CircularQueue<>(100, true);
        }
    }

    public static ArrayList<String> getLastErrors() {
        CircularQueue<String> circularQueue = sErrorLogs;
        if (circularQueue == null) {
            return new ArrayList<>();
        }
        return circularQueue.readAll();
    }

    public static int getLogIndex() {
        return sLogIndex;
    }

    public static ArrayList<String> getMajorEvents() {
        return sMajorEvents.readAll();
    }

    public static String getMsg(CharSequence charSequence, String str) {
        String str2;
        String str3;
        if (charSequence instanceof StringCompat) {
            str2 = ((StringCompat) charSequence).getTag();
        } else {
            str2 = null;
        }
        if (str2 != null) {
            str3 = "[" + getTag(charSequence, Math.max(19 - str2.length(), 15)) + '/' + str2 + "] " + str;
        } else {
            str3 = j.f(new StringBuilder("["), getTag(charSequence, 20), "] ", str);
        }
        if (!sLogCallback) {
            if (USE_REMOTE_LOG) {
                ThreadUtil.postOnBgThread(new C0673k(str3, 1));
            }
            return str3;
        }
        throw null;
    }

    private static String getTag(CharSequence charSequence, int i2) {
        int i7;
        Object obj;
        Object obj2;
        String str;
        int i8 = sLogIndex + 1;
        sLogIndex = i8;
        if (i8 > 9999) {
            sLogIndex = 0;
        }
        int i10 = sLogIndex;
        if (charSequence != null) {
            i7 = charSequence.length();
        } else {
            i7 = 0;
        }
        StringBuilder sb2 = new StringBuilder();
        if (i10 > 999) {
            obj = Integer.valueOf(i10);
        } else {
            if (i10 > 99) {
                str = " ";
            } else if (i10 > 9) {
                str = "  ";
            } else {
                str = "   ";
            }
            obj = C0086a.i(i10, str);
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (i7 == 0) {
            obj2 = SPACE_ARRAY[i2];
        } else if (i7 < i2) {
            obj2 = SPACE_ARRAY[i2 - i7] + charSequence;
        } else {
            obj2 = charSequence.subSequence(0, i2);
        }
        sb2.append(obj2);
        return sb2.toString();
    }

    public static void i(CharSequence charSequence, String str) {
        PRINTER.logi("Gallery", getMsg(charSequence, str));
    }

    public static void initPLog(long j2) {
        if (sPerformanceLogInitTime < 1) {
            sPerformanceLogInitTime = j2;
        }
    }

    public static void initPLogForce(long j2) {
        sPerformanceLogInitTime = j2;
    }

    public static void l(CharSequence charSequence, String str) {
        PRINTER.logi("Gallery", getMsg(charSequence, str));
    }

    public static void majorEvent(String str) {
        String msg = getMsg("Major", str);
        CircularQueue<String> circularQueue = sMajorEvents;
        circularQueue.add(TimeUtil.getTimestampMillis() + " " + Process.myPid() + "-" + Process.myTid() + " " + msg);
    }

    public static void mp(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryMP", getMsg(charSequence, str));
    }

    public static void mpe(CharSequence charSequence, String str) {
        String msg = getMsg(charSequence, str);
        PRINTER.loge("GalleryMP", msg);
        saveErrorLogs(msg);
    }

    public static void mpv(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryMP", getMsg(charSequence, str));
    }

    public static void mt(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryMT", getMsg(charSequence, str));
    }

    public static void mte(CharSequence charSequence, String str) {
        PRINTER.loge("GalleryMT", getMsg(charSequence, str));
    }

    public static void mtw(CharSequence charSequence, String str) {
        PRINTER.logw("GalleryMT", getMsg(charSequence, str));
    }

    public static void p(CharSequence charSequence, String str) {
        if (sPerformanceLogInitTime > 0) {
            str = addPerformanceLogElapsedTime(str);
        }
        PRINTER.logi("GalleryP", getMsg(charSequence, str));
    }

    public static void pk(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryPK", getMsg(charSequence, str));
    }

    public static void pke(CharSequence charSequence, String str) {
        PRINTER.loge("GalleryPK", getMsg(charSequence, str));
    }

    public static void printHistoricalLogs() {
        ArrayList<String> lastErrors = getLastErrors();
        e("DUMP", "== last errors ==\n" + String.join("\n", lastErrors));
        ArrayList<String> majorEvents = getMajorEvents();
        d("DUMP", "== OPERATION HISTORY ==\n" + String.join("\n", majorEvents));
    }

    public static void rm(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryRM", getMsg(charSequence, str));
    }

    public static void rme(CharSequence charSequence, String str) {
        String msg = getMsg(charSequence, str);
        PRINTER.loge("GalleryRM", msg);
        saveErrorLogs(msg);
    }

    public static void rmv(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryRM", getMsg(charSequence, str));
    }

    public static void s(CharSequence charSequence, String str) {
        PRINTER.logi("GallerySE", getMsg(charSequence, str));
    }

    private static void saveErrorLogs(String str) {
        CircularQueue<String> circularQueue = sErrorLogs;
        if (circularQueue != null) {
            circularQueue.add(TimeUtil.getTimestampMillis() + " " + Process.myPid() + "-" + Process.myTid() + " " + str);
        }
    }

    public static void se(CharSequence charSequence, String str) {
        PRINTER.loge("GallerySE", getMsg(charSequence, str));
    }

    /* access modifiers changed from: private */
    public static void sendLog(String str) {
        Socket socket;
        try {
            socket = new Socket(RemoteServerHolder.address, 8080);
            socket.getOutputStream().write(str.getBytes());
            socket.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void sh(CharSequence charSequence, String str) {
        PRINTER.logi("GallerySH", getMsg(charSequence, str));
    }

    public static void she(CharSequence charSequence, String str) {
        PRINTER.loge("GallerySH", getMsg(charSequence, str));
    }

    public static void shv(CharSequence charSequence, String str) {
        PRINTER.logv("GallerySH", getMsg(charSequence, str));
    }

    public static void sluggish(String str) {
        PRINTER.logi("VerificationLog", str);
    }

    public static void st(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryST", getMsg(charSequence, str));
    }

    public static void ste(CharSequence charSequence, String str) {
        PRINTER.loge("GalleryST", getMsg(charSequence, str));
    }

    public static void stv(CharSequence charSequence, String str) {
        PRINTER.logi("GalleryST", getMsg(charSequence, str));
    }

    public static void sw(CharSequence charSequence, String str) {
        PRINTER.logw("GallerySE", getMsg(charSequence, str));
    }

    public static void v(CharSequence charSequence, String str) {
        PRINTER.logi("Gallery", getMsg(charSequence, str));
    }

    public static void w(CharSequence charSequence, String str) {
        PRINTER.logw("Gallery", getMsg(charSequence, str));
    }

    public static void d(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.logi("Gallery", getMsg(charSequence, s.toString()));
    }

    public static void i(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.logi("Gallery", getMsg(charSequence, s.toString()));
    }

    public static void v(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.logi("Gallery", getMsg(charSequence, s.toString()));
    }

    public static void w(CharSequence charSequence, String str, Throwable th) {
        PRINTER.logw("Gallery", getMsg(charSequence, str), th);
    }

    public static void majorEvent(CharSequence charSequence, String str) {
        String msg = getMsg(charSequence, str);
        android.util.Log.i("Gallery", msg);
        CircularQueue<String> circularQueue = sMajorEvents;
        circularQueue.add(TimeUtil.getTimestampMillis() + " " + Process.myPid() + "-" + Process.myTid() + " " + msg);
    }

    public static void w(CharSequence charSequence, String str, Object... objArr) {
        AndroidPrinter androidPrinter = PRINTER;
        StringBuilder s = C0212a.s(str);
        s.append(Logger.v(objArr));
        androidPrinter.logw("Gallery", getMsg(charSequence, s.toString()));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class JavaPrinter extends AndroidPrinter {
        static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");

        private String getTimeString() {
            return LocalDateTime.now().format(FORMATTER);
        }

        public void loge(String str, String str2) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), " E/", str, " ");
            sb2.append(str2);
            printStream.println(sb2.toString());
        }

        public void logi(String str, String str2) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), " I/", str, " ");
            sb2.append(str2);
            printStream.println(sb2.toString());
        }

        public void logv(String str, String str2) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), "] V/", str, " ");
            sb2.append(str2);
            printStream.println(sb2.toString());
        }

        public void logw(String str, String str2) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), " W/", str, " ");
            sb2.append(str2);
            printStream.println(sb2.toString());
        }

        public void loge(String str, String str2, Throwable th) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), " E/", str, " ");
            sb2.append(str2);
            sb2.append(" ");
            sb2.append(th);
            printStream.println(sb2.toString());
        }

        public void logw(String str, String str2, Throwable th) {
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, getTimeString(), " W/", str, " ");
            sb2.append(str2);
            sb2.append(" ");
            sb2.append(th);
            printStream.println(sb2.toString());
        }
    }

    public static void e(CharSequence charSequence, String str) {
        String msg = getMsg(charSequence, str);
        PRINTER.loge("Gallery", msg);
        saveErrorLogs(msg);
    }

    public static void e(CharSequence charSequence, String str, Throwable th) {
        String msg = getMsg(charSequence, str);
        PRINTER.loge("Gallery", msg, th);
        saveErrorLogs(msg);
    }

    public static void local(CharSequence charSequence, String str) {
    }
}
