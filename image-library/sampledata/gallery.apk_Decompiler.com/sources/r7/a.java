package r7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.share.ShareSheetDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ShareSheetDelegate e;

    public /* synthetic */ a(ShareSheetDelegate shareSheetDelegate, int i2) {
        this.d = i2;
        this.e = shareSheetDelegate;
    }

    public final void run() {
        int i2 = this.d;
        ShareSheetDelegate shareSheetDelegate = this.e;
        switch (i2) {
            case 0:
                shareSheetDelegate.lambda$onPrepareShareSheet$0();
                return;
            default:
                shareSheetDelegate.lambda$onPrepareShareSheet$2();
                return;
        }
    }
}
