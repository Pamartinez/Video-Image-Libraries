package androidx.media3.common.util;

import android.text.TextUtils;
import i.C0212a;
import java.net.UnknownHostException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Log {
    private static final Object lock = new Object();
    private static int logLevel = 0;
    private static boolean logStackTraces = true;
    private static Logger logger = Logger.DEFAULT;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            public void d(String str, String str2, Throwable th) {
                android.util.Log.d(str, Log.appendThrowableString(str2, th));
            }

            public void e(String str, String str2, Throwable th) {
                android.util.Log.e(str, Log.appendThrowableString(str2, th));
            }

            public void i(String str, String str2, Throwable th) {
                android.util.Log.i(str, Log.appendThrowableString(str2, th));
            }

            public void w(String str, String str2, Throwable th) {
                android.util.Log.w(str, Log.appendThrowableString(str2, th));
            }
        };

        void d(String str, String str2, Throwable th);

        void e(String str, String str2, Throwable th);

        void i(String str, String str2, Throwable th);

        void w(String str, String str2, Throwable th);
    }

    public static String appendThrowableString(String str, Throwable th) {
        String throwableString = getThrowableString(th);
        if (TextUtils.isEmpty(throwableString)) {
            return str;
        }
        StringBuilder t = C0212a.t(str, "\n  ");
        t.append(throwableString.replace("\n", "\n  "));
        t.append(10);
        return t.toString();
    }

    public static void d(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel == 0) {
                    logger.d(str, str2, (Throwable) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void e(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 3) {
                    logger.e(str, str2, (Throwable) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String getThrowableString(Throwable th) {
        if (th == null) {
            return null;
        }
        synchronized (lock) {
            try {
                if (isCausedByUnknownHostException(th)) {
                    return "UnknownHostException (no network)";
                }
                if (!logStackTraces) {
                    String message = th.getMessage();
                    return message;
                }
                String replace = android.util.Log.getStackTraceString(th).trim().replace("\t", "    ");
                return replace;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static void i(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 1) {
                    logger.i(str, str2, (Throwable) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean isCausedByUnknownHostException(Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static void w(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 2) {
                    logger.w(str, str2, (Throwable) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel == 0) {
                    logger.d(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel <= 3) {
                    logger.e(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel <= 2) {
                    logger.w(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
