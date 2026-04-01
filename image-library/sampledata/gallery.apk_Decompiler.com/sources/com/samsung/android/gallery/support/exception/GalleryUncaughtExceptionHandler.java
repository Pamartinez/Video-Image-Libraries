package com.samsung.android.gallery.support.exception;

import java.lang.Thread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static volatile GalleryUncaughtExceptionHandler sInstance;
    private final Thread.UncaughtExceptionHandler mDefault = Thread.getDefaultUncaughtExceptionHandler();
    private DiagMonLogger mDiagmon;
    private volatile boolean mOnWork;
    private Runnable mSaveDumpRun;
    private final CopyOnWriteArrayList<Thread.UncaughtExceptionHandler> mUncaughtExceptionHandlers = new CopyOnWriteArrayList<>();

    private GalleryUncaughtExceptionHandler() {
    }

    public static GalleryUncaughtExceptionHandler getInstance() {
        if (sInstance == null) {
            synchronized (GalleryUncaughtExceptionHandler.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new GalleryUncaughtExceptionHandler();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private void reportToDiagmon(Throwable th, String str) {
        if (this.mDiagmon != null) {
            if (!this.mOnWork) {
                this.mOnWork = true;
                Thread currentThread = Thread.currentThread();
                th.printStackTrace();
                Iterator<Thread.UncaughtExceptionHandler> it = this.mUncaughtExceptionHandlers.iterator();
                while (it.hasNext()) {
                    it.next().uncaughtException(currentThread, th);
                }
                this.mDiagmon.internalException(th, str);
            }
            this.mOnWork = false;
        }
    }

    public void addExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (!this.mUncaughtExceptionHandlers.contains(uncaughtExceptionHandler)) {
            this.mUncaughtExceptionHandlers.add(uncaughtExceptionHandler);
        }
    }

    public CopyOnWriteArrayList<Thread.UncaughtExceptionHandler> getExceptionHandlers() {
        return this.mUncaughtExceptionHandlers;
    }

    public void removeExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.mUncaughtExceptionHandlers.remove(uncaughtExceptionHandler);
    }

    public void reportInternalAnr(Throwable th) {
        if (!this.mOnWork) {
            reportToDiagmon(th, "INTERNAL ANR");
        }
    }

    public void reportInternalException(Throwable th) {
        if (!this.mOnWork) {
            reportToDiagmon(th, "INTERNAL EXCEPTION");
        }
    }

    public void setDiagmon(DiagMonLogger diagMonLogger) {
        this.mDiagmon = diagMonLogger;
    }

    public void setSaveDumpRun(Runnable runnable) {
        this.mSaveDumpRun = runnable;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.mOnWork) {
            this.mOnWork = true;
            NativeCrashLogger.onJavaException();
            Iterator<Thread.UncaughtExceptionHandler> it = this.mUncaughtExceptionHandlers.iterator();
            while (it.hasNext()) {
                it.next().uncaughtException(thread, th);
            }
            DiagMonLogger diagMonLogger = this.mDiagmon;
            if (diagMonLogger != null) {
                diagMonLogger.uncaughtException(thread, th);
            }
        }
        this.mDefault.uncaughtException(thread, th);
        this.mOnWork = false;
    }
}
