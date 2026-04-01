package com.samsung.android.gallery.support.utils;

import N2.j;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.view.View;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.lang.Thread;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThreadUtil {
    private static final ThreadExceptionHandler sBgThreadExceptionHandler = new ThreadExceptionHandler();
    private static final HashSet<String> sDefaultMethodsSet = new HashSet<String>() {
        {
            add("block");
            add("unmanagedBlock");
            add("managedBlock");
            add("park");
            add("parkNanos");
            add("parkFor$");
            add("await");
            add("awaitFulfill");
            add("awaitNanos");
            add("transfer");
            add("take");
            add("poll");
            add("getTask");
            add("runWorker");
            add("run");
            add("wait");
            add("loop");
            add("next");
            add("nextLegacy");
            add("nativePollOnce");
            add("xfer");
            add("xferLifo");
            add("loopOnce");
        }
    };
    private static volatile Handler sHandler;
    private static volatile Handler sHandlerNoDebug;
    private static volatile HandlerThread sHandlerThread;
    private static volatile Handler sMediaPlayHandler;
    private static volatile HandlerThread sMediaPlayThread;
    private static final ConcurrentLinkedQueue<Runnable> sPostponedQueue = new ConcurrentLinkedQueue<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyLooper {
        static final Looper looper = ThreadUtil.createBackgroundThreadLooper("LazyLooper");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MainHandlerHolder {
        static final Handler sInstance = new Handler(Looper.getMainLooper());
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ThreadUtilHandler extends Handler {
        final boolean mIsDebuggable;

        public ThreadUtilHandler(Looper looper, boolean z) {
            super(looper);
            this.mIsDebuggable = z;
        }

        public void handleMessage(Message message) {
            if (message.what == 9000) {
                Log.w("ThreadUtil", "Postponed handler timeout");
                ThreadUtil.startPostponedHandler();
            }
            super.handleMessage(message);
        }

        public boolean sendMessageAtTime(Message message, long j2) {
            if (this.mIsDebuggable) {
                ThreadUtil.saveCallStackForDebug();
            }
            return super.sendMessageAtTime(message, j2);
        }
    }

    public static Looper createBackgroundThreadLooper(String str) {
        return createBackgroundThreadLooper(str, false);
    }

    public static Handler createLazyHandler() {
        return new Handler(LazyLooper.looper);
    }

    public static Handler createMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static StackTraceElement[] dumpThreads(Consumer<String> consumer, String str) {
        AtomicReference atomicReference = new AtomicReference();
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StringJoiner stringJoiner2 = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Consumer<String> consumer2 = consumer;
        String str2 = str;
        Thread.getAllStackTraces().forEach(new U(Thread.currentThread(), str2, stringJoiner, consumer2, atomicReference, stringJoiner2));
        if (str2 == null) {
            consumer2.accept("Idle threads : " + stringJoiner);
            consumer2.accept("extra threads : " + stringJoiner2);
        }
        return (StackTraceElement[]) atomicReference.get();
    }

    public static Handler getBackgroundThreadHandler() {
        return new Handler(getBackgroundThreadLooper()) {
            public boolean sendMessageAtTime(Message message, long j2) {
                if (Logger.isAllowDebug()) {
                    ThreadUtil.saveCallStackForDebug(ThreadUtil.getBgThread());
                }
                return super.sendMessageAtTime(message, j2);
            }
        };
    }

    public static Looper getBackgroundThreadLooper() {
        Looper looper = getBgThread().getLooper();
        if (looper != null) {
            return looper;
        }
        Log.e("ThreadUtil", "sHandlerThread lost looper");
        sHandlerThread.quitSafely();
        sHandlerThread = null;
        return getBgThread().getLooper();
    }

    public static HandlerThread getBgThread() {
        if (sHandlerThread == null) {
            synchronized (ThreadUtil.class) {
                try {
                    if (sHandlerThread == null) {
                        HandlerThreadWatched handlerThreadWatched = new HandlerThreadWatched("ThreadUtil", 10);
                        handlerThreadWatched.start();
                        if (Logger.isAllowDebug()) {
                            handlerThreadWatched.setUncaughtExceptionHandler(sBgThreadExceptionHandler);
                        }
                        sHandlerThread = handlerThreadWatched;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sHandlerThread;
    }

    public static Handler getBgThreadHandler() {
        Handler handler;
        if (sHandler == null) {
            synchronized (ThreadUtil.class) {
                try {
                    if (sHandler == null) {
                        if (Logger.isAllowDebug()) {
                            handler = new ThreadUtilHandler(getBackgroundThreadLooper(), Logger.isAllowDebug());
                        } else {
                            handler = getBgThreadNoDebugHandler();
                        }
                        sHandler = handler;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sHandler;
    }

    private static Handler getBgThreadNoDebugHandler() {
        if (sHandlerNoDebug == null) {
            synchronized (ThreadUtil.class) {
                try {
                    if (sHandlerNoDebug == null) {
                        sHandlerNoDebug = new ThreadUtilHandler(getBackgroundThreadLooper(), false);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sHandlerNoDebug;
    }

    public static String getCallStack() {
        return getCallStack(3, 20);
    }

    public static String getCaller() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        return Logger.getSimpleName(stackTraceElement.getClassName()) + "::" + stackTraceElement.getMethodName() + "#" + stackTraceElement.getLineNumber();
    }

    public static String getCallerMethodName(int i2, int i7) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String str = "";
        for (int i8 = 0; i8 < i7; i8++) {
            int i10 = i2 + i8 + 1;
            if (i10 >= stackTrace.length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i10];
            StringBuilder s = C0212a.s(str);
            s.append(stackTraceElement.getClassName());
            s.append("::");
            s.append(stackTraceElement.getMethodName());
            s.append("\n");
            str = s.toString();
        }
        return str;
    }

    public static String getLogFromStack(int i2, int i7, StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb2 = new StringBuilder(100);
        int min = Math.min(stackTraceElementArr.length, i7);
        while (i2 < min) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i2];
            sb2.append(stackTraceElement.getClassName().replace("com.sec.android.gallery3d", ""));
            sb2.append("::");
            sb2.append(stackTraceElement.getMethodName());
            sb2.append(" ");
            sb2.append(stackTraceElement.getLineNumber());
            if (i2 != min - 1) {
                sb2.append(System.lineSeparator());
                sb2.append("          ");
            }
            i2++;
        }
        return sb2.toString();
    }

    private static Handler getMainThreadHandler() {
        return MainHandlerHolder.sInstance;
    }

    public static Looper getMainThreadLooper() {
        return Looper.getMainLooper();
    }

    private static HandlerThread getMediaPlayThread() {
        if (sMediaPlayThread == null) {
            synchronized (ThreadUtil.class) {
                try {
                    if (sMediaPlayThread == null) {
                        HandlerThread handlerThread = new HandlerThread("MpThread", 10);
                        handlerThread.start();
                        if (Logger.isAllowDebug()) {
                            handlerThread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
                        }
                        sMediaPlayThread = handlerThread;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sMediaPlayThread;
    }

    private static Handler getMediaPlayThreadHandler() {
        if (sMediaPlayHandler == null) {
            synchronized (ThreadUtil.class) {
                try {
                    if (sMediaPlayHandler == null) {
                        sMediaPlayHandler = new ThreadUtilHandler(getMediaPlayThread().getLooper(), Logger.isAllowDebug());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sMediaPlayHandler;
    }

    private static boolean isDefaultStack(StackTraceElement[] stackTraceElementArr) {
        for (StackTraceElement methodName : stackTraceElementArr) {
            if (!sDefaultMethodsSet.contains(methodName.getMethodName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        return false;
    }

    private static boolean isTrackingThreads(String str) {
        if (str.startsWith("thread-pool") || str.startsWith("AsyncTask") || str.startsWith("ThreadUtil") || str.startsWith("MpThread") || str.startsWith("BG_") || str.startsWith("latch") || str.equals("main") || str.startsWith("thumb") || str.startsWith("ServiceCallbackThread") || str.startsWith("Thread-") || str.startsWith("NDK MediaCodec_looper") || str.startsWith("VideoRenderer") || str.startsWith("RequestOperationTask") || str.startsWith("REMOTE") || str.startsWith("MpView") || str.startsWith("VideoThread") || str.startsWith("Cache")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dumpThreads$2(Thread thread, String str, StringJoiner stringJoiner, Consumer consumer, AtomicReference atomicReference, StringJoiner stringJoiner2, Thread thread2, StackTraceElement[] stackTraceElementArr) {
        if (thread != thread2) {
            String name = thread2.getName();
            if ((str != null || !isTrackingThreads(name)) && (str == null || !name.startsWith(str))) {
                stringJoiner2.add(name);
            } else if (isDefaultStack(stackTraceElementArr)) {
                stringJoiner.add(name);
            } else {
                StringBuilder k = j.k("Thread : ", name, ", length = ");
                k.append(stackTraceElementArr.length);
                k.append("\n          ");
                k.append(getLogFromStack(0, 15, stackTraceElementArr));
                consumer.accept(k.toString());
                atomicReference.set(stackTraceElementArr);
            }
        }
    }

    public static void postOnBgThread(Runnable runnable) {
        getBgThreadHandler().post(runnable);
    }

    public static void postOnBgThreadDelayed(Runnable runnable, long j2) {
        getBgThreadHandler().postDelayed(runnable, j2);
    }

    public static void postOnBgThreadNoDebug(Runnable runnable) {
        getBgThreadNoDebugHandler().post(runnable);
    }

    public static void postOnMediaPlayThread(Runnable runnable) {
        getMediaPlayThreadHandler().post(runnable);
    }

    public static void postOnNewBgThread(Runnable runnable, String str) {
        new Thread(runnable, C0212a.l("ThreadUtil_", str)).start();
    }

    public static void postOnUiThread(Runnable runnable) {
        getMainThreadHandler().post(runnable);
    }

    public static void postOnUiThreadDelayed(Runnable runnable, long j2) {
        getMainThreadHandler().postDelayed(runnable, j2);
    }

    public static void postponeOnUiThread(Runnable runnable) {
        sPostponedQueue.add(runnable);
        getBgThreadHandler().removeMessages(9000);
        getBgThreadHandler().sendMessageDelayed(getBgThreadHandler().obtainMessage(9000), 500);
    }

    public static void removeCallbackOnBgThread(Runnable runnable) {
        getBgThreadHandler().removeCallbacks(runnable);
    }

    public static void removeCallbackOnUiThread(Runnable runnable) {
        getMainThreadHandler().removeCallbacks(runnable);
    }

    public static void runOnBgThread(Runnable runnable) {
        if (isMainThread()) {
            getBackgroundThreadHandler().post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runOnHandler(Handler handler, Runnable runnable) {
        if (handler.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    public static void runOnHandlerDelayed(Handler handler, Runnable runnable, long j2) {
        if (!handler.getLooper().isCurrentThread() || j2 != 0) {
            handler.postDelayed(runnable, j2);
        } else {
            runnable.run();
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            getMainThreadHandler().post(runnable);
        }
    }

    /* access modifiers changed from: private */
    public static void saveCallStackForDebug() {
        if (Logger.isAllowDebug()) {
            sBgThreadExceptionHandler.saveCallTimeStack(Thread.currentThread().getStackTrace());
        }
    }

    public static void setDefaultPriority() {
        Process.setThreadPriority(10);
    }

    public static void setHighPriority() {
        Process.setThreadPriority(0);
    }

    public static void startPostponedHandler() {
        ConcurrentLinkedQueue<Runnable> concurrentLinkedQueue = sPostponedQueue;
        if (!concurrentLinkedQueue.isEmpty()) {
            getBgThreadHandler().removeMessages(9000);
            Log.d("ThreadUtil", "startPostponedHandler " + concurrentLinkedQueue.size());
            while (true) {
                Runnable poll = sPostponedQueue.poll();
                if (poll != null) {
                    postOnUiThread(poll);
                } else {
                    return;
                }
            }
        }
    }

    public static Looper createBackgroundThreadLooper(String str, boolean z) {
        HandlerThread handlerThread;
        if (z) {
            handlerThread = new HandlerThreadWatched(C0212a.l("BG_", str), 10);
        } else {
            handlerThread = new HandlerThread(C0212a.l("BG_", str), 10);
        }
        handlerThread.start();
        if (Logger.isAllowDebug()) {
            handlerThread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        }
        return handlerThread.getLooper();
    }

    public static String getCallStack(int i2, int i7) {
        return getLogFromStack(i2, i7, Thread.currentThread().getStackTrace());
    }

    public static void saveCallStackForDebug(Thread thread) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        if (uncaughtExceptionHandler instanceof ThreadExceptionHandler) {
            ((ThreadExceptionHandler) uncaughtExceptionHandler).saveCallTimeStack(Thread.currentThread().getStackTrace());
        }
    }

    public static void runOnHandlerDelayed(Handler handler, Object obj, Runnable runnable, long j2) {
        if (!handler.getLooper().isCurrentThread() || j2 != 0) {
            handler.postDelayed(runnable, obj, j2);
        } else {
            runnable.run();
        }
    }

    public static void runOnUiThread(View view, Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else if (view != null) {
            view.post(runnable);
        } else {
            Log.w("ThreadUtil", "fail run on ui thread : " + runnable);
        }
    }

    public static void assertBgThread(String str) {
    }

    public static void assertUiThread(String str) {
    }
}
