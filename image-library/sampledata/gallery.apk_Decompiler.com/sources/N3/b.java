package N3;

import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FetchContentsForKnoxCmd e;

    public /* synthetic */ b(FetchContentsForKnoxCmd fetchContentsForKnoxCmd, int i2) {
        this.d = i2;
        this.e = fetchContentsForKnoxCmd;
    }

    public final void run() {
        int i2 = this.d;
        FetchContentsForKnoxCmd fetchContentsForKnoxCmd = this.e;
        switch (i2) {
            case 0:
                fetchContentsForKnoxCmd.lambda$showProgressDialog$2();
                return;
            default:
                fetchContentsForKnoxCmd.lambda$dismissProgressDialog$3();
                return;
        }
    }
}
