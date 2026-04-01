package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.MusicPickerHandler;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MusicPickerHandler e;
    public final /* synthetic */ BgmExtraInfo f;

    public /* synthetic */ d(MusicPickerHandler musicPickerHandler, BgmExtraInfo bgmExtraInfo, int i2) {
        this.d = i2;
        this.e = musicPickerHandler;
        this.f = bgmExtraInfo;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleMusicPickDone$0(this.f);
                return;
            default:
                this.e.lambda$handleMusicPickDone$1(this.f);
                return;
        }
    }
}
