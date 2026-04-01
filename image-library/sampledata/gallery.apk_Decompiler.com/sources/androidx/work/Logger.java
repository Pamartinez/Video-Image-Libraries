package androidx.work;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Logger {
    private static final int MAX_PREFIXED_TAG_LENGTH = 20;
    private static final Object sLock = new Object();
    private static volatile Logger sLogger;

    public Logger(int i2) {
    }

    public static Logger get() {
        Logger logger;
        synchronized (sLock) {
            try {
                if (sLogger == null) {
                    sLogger = new LogcatLogger(3);
                }
                logger = sLogger;
            } catch (Throwable th) {
                throw th;
            }
        }
        return logger;
    }

    public static void setLogger(Logger logger) {
        synchronized (sLock) {
            try {
                if (sLogger == null) {
                    sLogger = logger;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String tagWithPrefix(String str) {
        int length = str.length();
        StringBuilder sb2 = new StringBuilder(23);
        sb2.append("WM-");
        int i2 = MAX_PREFIXED_TAG_LENGTH;
        if (length >= i2) {
            sb2.append(str.substring(0, i2));
        } else {
            sb2.append(str);
        }
        return sb2.toString();
    }

    public abstract void debug(String str, String str2);

    public abstract void debug(String str, String str2, Throwable th);

    public abstract void error(String str, String str2);

    public abstract void error(String str, String str2, Throwable th);

    public abstract void info(String str, String str2);

    public abstract void info(String str, String str2, Throwable th);

    public abstract void verbose(String str, String str2);

    public abstract void warning(String str, String str2);

    public abstract void warning(String str, String str2, Throwable th);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LogcatLogger extends Logger {
        private final int mLoggingLevel;

        public LogcatLogger(int i2) {
            super(i2);
            this.mLoggingLevel = i2;
        }

        public void debug(String str, String str2) {
            if (this.mLoggingLevel <= 3) {
                Log.d(str, str2);
            }
        }

        public void error(String str, String str2) {
            if (this.mLoggingLevel <= 6) {
                Log.e(str, str2);
            }
        }

        public void info(String str, String str2) {
            if (this.mLoggingLevel <= 4) {
                Log.i(str, str2);
            }
        }

        public void verbose(String str, String str2) {
            if (this.mLoggingLevel <= 2) {
                Log.v(str, str2);
            }
        }

        public void warning(String str, String str2) {
            if (this.mLoggingLevel <= 5) {
                Log.w(str, str2);
            }
        }

        public void debug(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 3) {
                Log.d(str, str2, th);
            }
        }

        public void error(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 6) {
                Log.e(str, str2, th);
            }
        }

        public void info(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 4) {
                Log.i(str, str2, th);
            }
        }

        public void warning(String str, String str2, Throwable th) {
            if (this.mLoggingLevel <= 5) {
                Log.w(str, str2, th);
            }
        }
    }
}
