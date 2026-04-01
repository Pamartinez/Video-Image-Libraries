package P1;

import com.samsung.android.gallery.app.ui.map.picker.google.a;
import com.samsung.android.gallery.app.ui.map.picker.google.b;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f573a = 0;
    public final Executor b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f574c = new Object();
    public final Object d;

    public f(Executor executor, b bVar) {
        this.b = executor;
        this.d = bVar;
    }

    public final void a(h hVar) {
        switch (this.f573a) {
            case 0:
                synchronized (this.f574c) {
                }
                this.b.execute(new e(0, this, hVar));
                return;
            case 1:
                if (!hVar.g()) {
                    synchronized (this.f574c) {
                    }
                    this.b.execute(new e(1, this, hVar));
                    return;
                }
                return;
            default:
                if (hVar.g()) {
                    synchronized (this.f574c) {
                    }
                    this.b.execute(new e(2, this, hVar));
                    return;
                }
                return;
        }
    }

    public f(Executor executor, a aVar) {
        this.b = executor;
        this.d = aVar;
    }

    public f(Executor executor, b bVar) {
        this.b = executor;
        this.d = bVar;
    }
}
