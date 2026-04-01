package A4;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.abstraction.ListViewServiceBixby;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Z implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListViewServiceBixby e;
    public final /* synthetic */ Context f;

    public /* synthetic */ Z(ListViewServiceBixby listViewServiceBixby, Context context, int i2) {
        this.d = i2;
        this.e = listViewServiceBixby;
        this.f = context;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleSimilar$2(this.f);
                return;
            default:
                this.e.lambda$handleSimilar$1(this.f);
                return;
        }
    }
}
