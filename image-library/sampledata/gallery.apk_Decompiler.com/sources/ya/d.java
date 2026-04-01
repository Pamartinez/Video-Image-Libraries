package ya;

import com.samsung.android.gallery.plugins.compare.CompareActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CompareActivity e;

    public /* synthetic */ d(CompareActivity compareActivity, int i2) {
        this.d = i2;
        this.e = compareActivity;
    }

    public final void run() {
        int i2 = this.d;
        CompareActivity compareActivity = this.e;
        switch (i2) {
            case 0:
                compareActivity.finish();
                return;
            case 1:
                compareActivity.load();
                return;
            case 2:
                compareActivity.lambda$updateGridViews$13();
                return;
            default:
                compareActivity.lambda$load$10();
                return;
        }
    }
}
