package U3;

import com.samsung.android.gallery.app.controller.story.SaveStoryLiveEffectCmd;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveStoryLiveEffectCmd e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ e(SaveStoryLiveEffectCmd saveStoryLiveEffectCmd, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = saveStoryLiveEffectCmd;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onExecute$0(this.f);
                return;
            default:
                this.e.lambda$saveLiveEffect$2(this.f);
                return;
        }
    }
}
