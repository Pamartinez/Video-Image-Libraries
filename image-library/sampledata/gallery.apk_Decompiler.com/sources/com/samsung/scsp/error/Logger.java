package com.samsung.scsp.error;

import android.os.Build;
import android.util.Log;
import i.C0212a;
import java.util.Locale;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Logger {
    private final String tag;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final boolean DEBUG = (!"user".equals(Build.TYPE));
        /* access modifiers changed from: private */
        public static LoggerConfig LOGGER_CONFIG = new LoggerConfig("[SCSPSDK]");

        private LazyHolder() {
        }
    }

    private Logger(String str) {
        this.tag = C0212a.q(new StringBuilder(), LazyHolder.LOGGER_CONFIG.tag.get(), "[", str, "]");
    }

    public static Logger get(String str) {
        return new Logger(str);
    }

    public static void initialize(LoggerConfig loggerConfig) {
        LoggerConfig unused = LazyHolder.LOGGER_CONFIG = loggerConfig;
    }

    public void d(Supplier<String> supplier) {
        if (LazyHolder.DEBUG && supplier != null) {
            Log.d(this.tag, supplier.get());
        }
    }

    public void e(String str, Throwable th) {
        if (str != null && th != null) {
            Locale locale = Locale.US;
            Log.e(this.tag, C0212a.n("[E] ", str, " ", Log.getStackTraceString(th)));
        }
    }

    public void i(String str) {
        if (str != null) {
            Log.i(this.tag, str);
        }
    }

    public void w(String str) {
        if (str != null) {
            Log.w(this.tag, str);
        }
    }

    public void e(String str) {
        if (str != null) {
            Log.e(this.tag, str);
        }
    }
}
