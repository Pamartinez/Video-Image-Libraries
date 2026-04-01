package com.samsung.android.gallery.support.exception;

import android.os.Process;
import android.util.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.PrintStream;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InternalAnr extends AssertionError {
    public InternalAnr(Object obj) {
        super(obj);
    }

    private String getStackLog(StackTraceElement[] stackTraceElementArr) {
        try {
            StringBuilder sb2 = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                sb2.append("\tat ");
                sb2.append(stackTraceElement.getClassName());
                sb2.append(".");
                sb2.append(stackTraceElement.getMethodName());
                sb2.append("(");
                sb2.append(stackTraceElement.getFileName());
                sb2.append(NumericEnum.SEP);
                sb2.append(stackTraceElement.getLineNumber());
                sb2.append(")");
                sb2.append("\n");
            }
            return sb2.toString();
        } catch (Exception unused) {
            return "FAIL GET STACK";
        }
    }

    public String[] getHeader(StackTraceElement[] stackTraceElementArr) {
        try {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                if (stackTraceElement.getClassName().contains("gallery")) {
                    return new String[]{stackTraceElement.getFileName() + NumericEnum.SEP + stackTraceElement.getLineNumber(), stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName(), ThreadUtil.getLogFromStack(0, 1, stackTraceElementArr)};
                }
            }
            String logFromStack = ThreadUtil.getLogFromStack(0, 1, stackTraceElementArr);
            if (logFromStack.startsWith("android.os.BinderProxy::transactNative")) {
                logFromStack = ThreadUtil.getLogFromStack(2, 3, stackTraceElementArr);
            }
            return new String[]{"com.sec.android.gallery3d", "undefined method", logFromStack};
        } catch (Exception unused) {
            return new String[]{"com.sec.android.gallery3d", "undefined method", "undefined reason"};
        }
    }

    public StackTraceElement[] getStackTraceElements() {
        return ThreadUtil.getMainThreadLooper().getThread().getStackTrace();
    }

    public void post() {
        GalleryUncaughtExceptionHandler.getInstance().reportInternalAnr(this);
    }

    public void printStackTrace() {
        StackTraceElement[] stackTraceElements = getStackTraceElements();
        String[] header = getHeader(stackTraceElements);
        Log.e("Gallery", "ANR class: " + header[0]);
        Log.e("Gallery", "ANR method: " + header[1]);
        Log.e("Gallery", "PID: " + Process.myPid());
        Log.e("Gallery", "Reason: " + tag() + header[2]);
        Log.e("AndroidRuntime", getStackLog(stackTraceElements));
    }

    public String tag() {
        return "InternalAnr:";
    }

    public void printStackTrace(PrintStream printStream) {
        StackTraceElement[] stackTraceElements = getStackTraceElements();
        String[] header = getHeader(stackTraceElements);
        printStream.println("Gallery: ANR class: " + header[0]);
        printStream.println("Gallery: ANR method: " + header[1]);
        printStream.println("Gallery: PID: " + Process.myPid());
        printStream.println("Gallery: Reason: " + tag() + header[2]);
        StringBuilder sb2 = new StringBuilder("AndroidRuntime: ");
        sb2.append(getStackLog(stackTraceElements));
        printStream.println(sb2.toString());
    }

    public void printStackTrace(PrintWriter printWriter) {
        StackTraceElement[] stackTraceElements = getStackTraceElements();
        String[] header = getHeader(stackTraceElements);
        printWriter.println("Gallery: ANR class: " + header[0]);
        printWriter.println("Gallery: ANR method: " + header[1]);
        printWriter.println("Gallery: PID: " + Process.myPid());
        printWriter.println("Gallery: Reason: " + tag() + header[2]);
        StringBuilder sb2 = new StringBuilder("AndroidRuntime: ");
        sb2.append(getStackLog(stackTraceElements));
        printWriter.println(sb2.toString());
    }
}
