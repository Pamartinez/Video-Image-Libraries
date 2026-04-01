package i6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.bgm.BgmPlayerV2;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPlayerV2 e;
    public final /* synthetic */ MediaPlayerCompat f;

    public /* synthetic */ a(BgmPlayerV2 bgmPlayerV2, MediaPlayerCompat mediaPlayerCompat, int i2) {
        this.d = i2;
        this.e = bgmPlayerV2;
        this.f = mediaPlayerCompat;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$clearPlayer$0(this.f);
                return;
            default:
                this.e.lambda$notifyFail$3(this.f);
                return;
        }
    }
}
