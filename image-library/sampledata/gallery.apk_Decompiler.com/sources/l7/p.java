package L7;

import com.samsung.android.gallery.widget.details.DetailsBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsBehavior e;

    public /* synthetic */ p(DetailsBehavior detailsBehavior, int i2) {
        this.d = i2;
        this.e = detailsBehavior;
    }

    public final void run() {
        int i2 = this.d;
        DetailsBehavior detailsBehavior = this.e;
        switch (i2) {
            case 0:
                detailsBehavior.onDismissedKeyguard();
                return;
            default:
                detailsBehavior.lambda$onDismissedKeyguard$0();
                return;
        }
    }
}
