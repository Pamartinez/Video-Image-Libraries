package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ReplayDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ReplayDelegate e;

    public /* synthetic */ w(ReplayDelegate replayDelegate, int i2) {
        this.d = i2;
        this.e = replayDelegate;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$addListenEvent$0((Object[]) obj);
                return;
            case 1:
                this.e.onBottomSheetStateChanged((Object[]) obj);
                return;
            case 2:
                this.e.hideRelatedView((Object[]) obj);
                return;
            case 3:
                this.e.onPageSelected((Object[]) obj);
                return;
            default:
                this.e.onReplayClicked(((Integer) obj).intValue());
                return;
        }
    }
}
