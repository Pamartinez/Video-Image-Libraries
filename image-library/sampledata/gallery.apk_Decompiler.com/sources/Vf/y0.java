package Vf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class y0 {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f3876a = new ThreadLocal();

    public static X a() {
        ThreadLocal threadLocal = f3876a;
        X x9 = (X) threadLocal.get();
        if (x9 != null) {
            return x9;
        }
        C0870g gVar = new C0870g(Thread.currentThread());
        threadLocal.set(gVar);
        return gVar;
    }
}
