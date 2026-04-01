package com.google.common.util.concurrent;

/* renamed from: com.google.common.util.concurrent.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0123b {

    /* renamed from: c  reason: collision with root package name */
    public static final C0123b f1561c;
    public static final C0123b d;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f1562a;
    public final Throwable b;

    static {
        if (q.GENERATE_CANCELLATION_CAUSES) {
            d = null;
            f1561c = null;
            return;
        }
        d = new C0123b(false, (Throwable) null);
        f1561c = new C0123b(true, (Throwable) null);
    }

    public C0123b(boolean z, Throwable th) {
        this.f1562a = z;
        this.b = th;
    }
}
