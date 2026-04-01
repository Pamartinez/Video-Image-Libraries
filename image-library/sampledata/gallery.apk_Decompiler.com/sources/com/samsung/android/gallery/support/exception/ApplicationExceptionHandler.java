package com.samsung.android.gallery.support.exception;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import java.io.PrintWriter;
import java.lang.Thread;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ApplicationExceptionHandler implements Thread.UncaughtExceptionHandler, DumpAllHandler {
    public void dumpAll(PrintWriter printWriter) {
        Blackboard.dumpAll("", printWriter);
        Log.dumpHistoricalLogs(printWriter);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        PrintWriter printWriter = new PrintWriter(System.out);
        dumpAll(printWriter);
        printWriter.flush();
        printWriter.close();
    }
}
