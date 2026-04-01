package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPlayerDelegate;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPlayerDelegate e;
    public final /* synthetic */ BgmExtraInfo f;

    public /* synthetic */ d(BgmPlayerDelegate bgmPlayerDelegate, BgmExtraInfo bgmExtraInfo, int i2) {
        this.d = i2;
        this.e = bgmPlayerDelegate;
        this.f = bgmExtraInfo;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleEvent$7(this.f);
                return;
            default:
                this.e.lambda$checkAudioPermission$8(this.f);
                return;
        }
    }
}
