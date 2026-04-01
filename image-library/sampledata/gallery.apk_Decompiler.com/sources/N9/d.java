package N9;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.publisher.retrieval.SQLiteRetrieval;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2852h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f2853i;

    public /* synthetic */ d(Object obj, Object obj2, Object obj3, boolean z, boolean z3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f2852h = z;
        this.f2853i = z3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SQLiteRetrieval) this.e).lambda$getTimelineFileIds$13((AtomicReference) this.f, (String) this.g, this.f2852h, this.f2853i);
                return;
            case 1:
                ((SQLiteRetrieval) this.e).lambda$getTimelineFileIds$15((AtomicReference) this.f, (String) this.g, this.f2852h, this.f2853i);
                return;
            default:
                ((ViewLiveEffectCmd) this.e).lambda$onExecute$0((MediaItem) this.f, (Bitmap) this.g, this.f2852h, this.f2853i);
                return;
        }
    }
}
