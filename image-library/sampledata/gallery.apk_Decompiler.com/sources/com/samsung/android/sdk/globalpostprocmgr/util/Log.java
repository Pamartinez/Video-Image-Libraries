package com.samsung.android.sdk.globalpostprocmgr.util;

import c0.C0086a;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Log {
    private static String COMMONPREFIX = "[#GPPM PPSDK#]";
    private static volatile boolean smDLoggingEnabled = true;
    private static volatile boolean smELoggingEnabled = true;
    private static volatile boolean smILoggingEnabled = true;
    private static final ConcurrentHashMap<String, Table> smLogCount = new ConcurrentHashMap<>();
    private static final boolean smProfileLogs = false;
    private static final boolean smVLoggingEnabled = true;
    private static final boolean smWLoggingEnabled = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Table {
        AtomicInteger dLogs = new AtomicInteger(0);
        AtomicInteger eLogs = new AtomicInteger(0);
        AtomicInteger iLogs = new AtomicInteger(0);
        AtomicInteger tLogs = new AtomicInteger(0);
        AtomicInteger vLogs = new AtomicInteger(0);
        AtomicInteger wLogs = new AtomicInteger(0);

        public static Table getInstance() {
            return new Table();
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (smDLoggingEnabled) {
            StringBuffer stringBuffer = new StringBuffer();
            if (objArr != null) {
                for (Object append : objArr) {
                    stringBuffer.append(append);
                }
            }
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, COMMONPREFIX, " ", str2, " ");
            sb2.append(stringBuffer);
            android.util.Log.d(str, sb2.toString());
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (smELoggingEnabled) {
            StringBuffer stringBuffer = new StringBuffer();
            if (objArr != null) {
                for (Object append : objArr) {
                    stringBuffer.append(append);
                }
            }
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, COMMONPREFIX, " ", str2, " ");
            sb2.append(stringBuffer);
            android.util.Log.e(str, sb2.toString());
        }
    }

    public static boolean getDebugFlag() {
        return smDLoggingEnabled;
    }

    private static Table getEntry(String str) {
        Table table;
        ConcurrentHashMap<String, Table> concurrentHashMap = smLogCount;
        Table table2 = concurrentHashMap.get(str);
        if (table2 == null) {
            table2 = Table.getInstance();
            table = concurrentHashMap.putIfAbsent(str, table2);
        } else {
            table = null;
        }
        if (table != null) {
            return table;
        }
        return table2;
    }

    public static void i(String str, String str2, Object... objArr) {
        if (smILoggingEnabled) {
            StringBuffer stringBuffer = new StringBuffer();
            if (objArr != null) {
                for (Object append : objArr) {
                    stringBuffer.append(append);
                }
            }
            StringBuilder sb2 = new StringBuilder();
            C0086a.z(sb2, COMMONPREFIX, " ", str2, " ");
            sb2.append(stringBuffer);
            android.util.Log.i(str, sb2.toString());
        }
    }

    public static void setCommonPrefix(String str) {
        COMMONPREFIX = str;
    }

    public static void setDebugLogging(boolean z) {
        smDLoggingEnabled = z;
    }

    public static void setErrorLogging(boolean z) {
        smELoggingEnabled = z;
    }

    public static void setInfoLogging(boolean z) {
        smILoggingEnabled = z;
    }

    public static void throwable(String str, String str2, Throwable th) {
        StringBuilder t = C0212a.t(str2, ": ");
        t.append(th.toString());
        if (th.getMessage() != null) {
            t.append(" (");
            t.append(th.getMessage());
            t.append(")");
        }
        t.append("\n");
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            t.append("\tat ");
            t.append(stackTraceElement.getClassName());
            t.append(".");
            t.append(stackTraceElement.getMethodName());
            t.append(" (");
            t.append(stackTraceElement.getFileName());
            t.append(NumericEnum.SEP);
            t.append(stackTraceElement.getLineNumber());
            t.append(")\n");
        }
        e(str, t.toString(), new Object[0]);
    }

    public static void v(String str, String str2, Object... objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (objArr != null) {
            for (Object append : objArr) {
                stringBuffer.append(append);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        C0086a.z(sb2, COMMONPREFIX, " ", str2, " ");
        sb2.append(stringBuffer);
        android.util.Log.v(str, sb2.toString());
    }

    public static void w(String str, String str2, Object... objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (objArr != null) {
            for (Object append : objArr) {
                stringBuffer.append(append);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        C0086a.z(sb2, COMMONPREFIX, " ", str2, " ");
        sb2.append(stringBuffer);
        android.util.Log.w(str, sb2.toString());
    }

    public static void dumpStats(PrintWriter printWriter) {
    }
}
