package com.samsung.android.gallery.support.utils;

import N2.j;
import android.util.Log;
import java.lang.Thread;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final Thread.UncaughtExceptionHandler mDefault = Thread.getDefaultUncaughtExceptionHandler();
    private StackTraceElement[] mStackTraceElements;

    private void printCallTimeStack() {
        if (this.mStackTraceElements != null) {
            StringBuilder sb2 = new StringBuilder("==============");
            String str = LINE_SEPARATOR;
            j.z(sb2, str, "Fatal Exception on BG Thread. print stack when requested", str);
            int i2 = 0;
            for (StackTraceElement stackTraceElement : this.mStackTraceElements) {
                if (i2 > 2) {
                    sb2.append("   at ");
                    sb2.append(stackTraceElement.toString());
                    sb2.append(LINE_SEPARATOR);
                }
                i2++;
            }
            Log.e("ThreadException", sb2.toString());
        }
    }

    public void saveCallTimeStack(StackTraceElement[] stackTraceElementArr) {
        this.mStackTraceElements = stackTraceElementArr;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            printCallTimeStack();
        } catch (Exception e) {
            Log.e("ThreadExceptionHandler", "Exception inside of Exception Handler");
            e.printStackTrace();
        }
        this.mDefault.uncaughtException(thread, th);
    }
}
