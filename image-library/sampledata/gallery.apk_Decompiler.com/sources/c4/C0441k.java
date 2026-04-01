package c4;

import com.samsung.android.gallery.app.service.StoryBaseService;
import java.util.function.Consumer;

/* renamed from: c4.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0441k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoryBaseService e;

    public /* synthetic */ C0441k(StoryBaseService storyBaseService, int i2) {
        this.d = i2;
        this.e = storyBaseService;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.onProgressUpdated(((Integer) obj).intValue());
                return;
            default:
                this.e.onProgressCompleted((String) obj);
                return;
        }
    }
}
