package x0;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WeakReference f2066a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2067c;
    public final /* synthetic */ String d;

    public /* synthetic */ m(WeakReference weakReference, Context context, int i2, String str) {
        this.f2066a = weakReference;
        this.b = context;
        this.f2067c = i2;
        this.d = str;
    }

    public final Object call() {
        Context context = (Context) this.f2066a.get();
        if (context == null) {
            context = this.b;
        }
        return n.e(context, this.f2067c, this.d);
    }
}
