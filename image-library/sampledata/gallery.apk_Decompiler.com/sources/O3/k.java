package O3;

import com.samsung.android.gallery.app.controller.internals.DownloadCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DownloadCmd e;

    public /* synthetic */ k(DownloadCmd downloadCmd, int i2) {
        this.d = i2;
        this.e = downloadCmd;
    }

    public final void run() {
        int i2 = this.d;
        DownloadCmd downloadCmd = this.e;
        switch (i2) {
            case 0:
                downloadCmd.lambda$getResultReceiverForViewer$1();
                return;
            default:
                downloadCmd.lambda$startDownloadIfNetworkAvailable$2();
                return;
        }
    }
}
