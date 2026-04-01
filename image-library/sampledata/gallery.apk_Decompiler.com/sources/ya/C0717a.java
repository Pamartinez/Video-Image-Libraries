package ya;

import com.samsung.android.gallery.plugins.compare.CompareActivity;
import java.util.ArrayList;

/* renamed from: ya.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0717a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CompareActivity e;
    public final /* synthetic */ ArrayList f;

    public /* synthetic */ C0717a(CompareActivity compareActivity, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = compareActivity;
        this.f = arrayList;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onActivityResult$8(this.f);
                return;
            default:
                this.e.lambda$onActivityResult$7(this.f);
                return;
        }
    }
}
