package ge;

import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J0 extends WeakReference {
    public static final boolean f = Boolean.parseBoolean(System.getProperty("io.grpc.ManagedChannel.enableAllocationTracking", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
    public static final RuntimeException g;

    /* renamed from: a  reason: collision with root package name */
    public final ReferenceQueue f4449a;
    public final ConcurrentMap b;

    /* renamed from: c  reason: collision with root package name */
    public final String f4450c;
    public final SoftReference d;
    public final AtomicBoolean e = new AtomicBoolean();

    static {
        RuntimeException runtimeException = new RuntimeException("ManagedChannel allocation site not recorded.  Set -Dio.grpc.ManagedChannel.enableAllocationTracking=true to enable it");
        runtimeException.setStackTrace(new StackTraceElement[0]);
        g = runtimeException;
    }

    public J0(K0 k0, F0 f02, ReferenceQueue referenceQueue, ConcurrentMap concurrentMap) {
        super(k0, referenceQueue);
        RuntimeException runtimeException;
        if (f) {
            runtimeException = new RuntimeException("ManagedChannel allocation site");
        } else {
            runtimeException = g;
        }
        this.d = new SoftReference(runtimeException);
        this.f4450c = f02.toString();
        this.f4449a = referenceQueue;
        this.b = concurrentMap;
        concurrentMap.put(this, this);
        a(referenceQueue);
    }

    public static void a(ReferenceQueue referenceQueue) {
        while (true) {
            J0 j02 = (J0) referenceQueue.poll();
            if (j02 != null) {
                SoftReference softReference = j02.d;
                RuntimeException runtimeException = (RuntimeException) softReference.get();
                super.clear();
                j02.b.remove(j02);
                softReference.clear();
                if (!j02.e.get()) {
                    Level level = Level.SEVERE;
                    Logger logger = K0.e;
                    if (logger.isLoggable(level)) {
                        LogRecord logRecord = new LogRecord(level, "*~*~*~ Previous channel {0} was garbage collected without being shut down! ~*~*~*" + System.getProperty("line.separator") + "    Make sure to call shutdown()/shutdownNow()");
                        logRecord.setLoggerName(logger.getName());
                        logRecord.setParameters(new Object[]{j02.f4450c});
                        logRecord.setThrown(runtimeException);
                        logger.log(logRecord);
                    }
                }
            } else {
                return;
            }
        }
    }

    public final void clear() {
        super.clear();
        this.b.remove(this);
        this.d.clear();
        a(this.f4449a);
    }
}
