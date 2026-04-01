package androidx.fragment.app;

import Vf.O;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements O {
    public final /* synthetic */ Fragment d;
    public final /* synthetic */ WeakReference e;

    public /* synthetic */ e(Fragment fragment, WeakReference weakReference) {
        this.d = fragment;
        this.e = weakReference;
    }

    public final void a() {
        this.d.lambda$onCreateAnimation$0(this.e);
    }
}
