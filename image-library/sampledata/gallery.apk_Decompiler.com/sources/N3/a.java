package N3;

import android.app.Activity;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DocumentScanCmd.DocumentScanSaveTask e;
    public final /* synthetic */ Activity f;
    public final /* synthetic */ Uri g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2401h;

    public /* synthetic */ a(DocumentScanCmd.DocumentScanSaveTask documentScanSaveTask, Activity activity, Uri uri, String str, int i2) {
        this.d = i2;
        this.e = documentScanSaveTask;
        this.f = activity;
        this.g = uri;
        this.f2401h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$execute$1(this.f, this.g, this.f2401h);
                return;
            default:
                this.e.lambda$execute$3(this.f, this.g, this.f2401h);
                return;
        }
    }
}
