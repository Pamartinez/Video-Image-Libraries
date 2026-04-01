package F8;

import com.samsung.android.gallery.module.bgm.BgmUriService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmUriService e;

    public /* synthetic */ c(BgmUriService bgmUriService, int i2) {
        this.d = i2;
        this.e = bgmUriService;
    }

    public final void run() {
        int i2 = this.d;
        BgmUriService bgmUriService = this.e;
        switch (i2) {
            case 0:
                bgmUriService.onConnected();
                return;
            default:
                bgmUriService.downloadAllBgm();
                return;
        }
    }
}
