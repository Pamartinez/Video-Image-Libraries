package fe;

/* renamed from: fe.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0998a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ c e;

    public /* synthetic */ C0998a(c cVar, int i2) {
        this.d = i2;
        this.e = cVar;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                c cVar = this.e;
                synchronized (cVar) {
                    try {
                        if (cVar.j(d.SHUTDOWN)) {
                            cVar.o(cVar.f4340i, true);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
            default:
                c cVar2 = this.e;
                synchronized (cVar2) {
                    try {
                        if (cVar2.j(d.NOT_STARTED)) {
                            cVar2.n(d.SETUP);
                            cVar2.q.a();
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                return;
        }
    }
}
