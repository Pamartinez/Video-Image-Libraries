package Xf;

import Ae.d;
import Qe.B;
import Vf.C0873j;
import cg.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final m f3902a = new m(-1, (m) null, (e) null, 0);
    public static final int b = a.j(32, 12, "kotlinx.coroutines.bufferedChannel.segmentSize");

    /* renamed from: c  reason: collision with root package name */
    public static final int f3903c = a.j(10000, 12, "kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations");
    public static final B d = new B("BUFFERED", 2);
    public static final B e = new B("SHOULD_BUFFER", 2);
    public static final B f = new B("S_RESUMING_BY_RCV", 2);
    public static final B g = new B("RESUMING_BY_EB", 2);

    /* renamed from: h  reason: collision with root package name */
    public static final B f3904h = new B("POISONED", 2);

    /* renamed from: i  reason: collision with root package name */
    public static final B f3905i = new B("DONE_RCV", 2);

    /* renamed from: j  reason: collision with root package name */
    public static final B f3906j = new B("INTERRUPTED_SEND", 2);
    public static final B k = new B("INTERRUPTED_RCV", 2);
    public static final B l = new B("CHANNEL_CLOSED", 2);
    public static final B m = new B("SUSPEND", 2);
    public static final B n = new B("SUSPEND_NO_WAITER", 2);

    /* renamed from: o  reason: collision with root package name */
    public static final B f3907o = new B("FAILED", 2);

    /* renamed from: p  reason: collision with root package name */
    public static final B f3908p = new B("NO_RECEIVE_RESULT", 2);
    public static final B q = new B("CLOSE_HANDLER_CLOSED", 2);
    public static final B r = new B("CLOSE_HANDLER_INVOKED", 2);
    public static final B s = new B("NO_CLOSE_CAUSE", 2);

    public static final boolean a(C0873j jVar, Object obj, d dVar) {
        B b5 = jVar.b(obj, dVar);
        if (b5 == null) {
            return false;
        }
        jVar.j(b5);
        return true;
    }
}
