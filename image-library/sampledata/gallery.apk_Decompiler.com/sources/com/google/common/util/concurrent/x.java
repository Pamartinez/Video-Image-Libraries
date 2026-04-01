package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x {

    /* renamed from: a  reason: collision with root package name */
    public final Object f1572a = new Object();
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public volatile Logger f1573c;

    public x(Class cls) {
        this.b = cls.getName();
    }

    public final Logger a() {
        Logger logger = this.f1573c;
        if (logger != null) {
            return logger;
        }
        synchronized (this.f1572a) {
            try {
                Logger logger2 = this.f1573c;
                if (logger2 != null) {
                    return logger2;
                }
                Logger logger3 = Logger.getLogger(this.b);
                this.f1573c = logger3;
                return logger3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
