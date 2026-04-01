package ge;

import He.F;
import ee.C0971d;
import ee.C0985s;
import ee.C0986t;
import ee.C0990x;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* renamed from: ge.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1036k {

    /* renamed from: c  reason: collision with root package name */
    public static final Logger f4527c = Logger.getLogger(C0971d.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final Object f4528a = new Object();
    public final C0990x b;

    public C1036k(C0990x xVar, long j2, String str) {
        F.n(str, "description");
        this.b = xVar;
        String concat = str.concat(" created");
        C0985s sVar = C0985s.CT_INFO;
        F.n(concat, "description");
        F.n(sVar, "severity");
        b(new C0986t(concat, sVar, j2, (C1031i0) null));
    }

    public static void a(C0990x xVar, Level level, String str) {
        Logger logger = f4527c;
        if (logger.isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, "[" + xVar + "] " + str);
            logRecord.setLoggerName(logger.getName());
            logRecord.setSourceClassName(logger.getName());
            logRecord.setSourceMethodName("log");
            logger.log(logRecord);
        }
    }

    public final void b(C0986t tVar) {
        Level level;
        int i2 = C1033j.f4525a[tVar.b.ordinal()];
        if (i2 == 1) {
            level = Level.FINE;
        } else if (i2 != 2) {
            level = Level.FINEST;
        } else {
            level = Level.FINER;
        }
        synchronized (this.f4528a) {
        }
        a(this.b, level, tVar.f4309a);
    }
}
