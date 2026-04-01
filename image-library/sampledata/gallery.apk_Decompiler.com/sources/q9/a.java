package q9;

import android.content.Context;
import com.samsung.android.gallery.module.knox.KnoxOperationTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ KnoxOperationTask e;
    public final /* synthetic */ Context f;

    public /* synthetic */ a(KnoxOperationTask knoxOperationTask, Context context, int i2) {
        this.d = i2;
        this.e = knoxOperationTask;
        this.f = context;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$operate$3(this.f);
                return;
            default:
                this.e.lambda$operate$2(this.f);
                return;
        }
    }
}
