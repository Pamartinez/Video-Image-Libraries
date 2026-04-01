package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharedTransitionHandler e;

    public /* synthetic */ e(SharedTransitionHandler sharedTransitionHandler, int i2) {
        this.d = i2;
        this.e = sharedTransitionHandler;
    }

    public final void run() {
        int i2 = this.d;
        SharedTransitionHandler sharedTransitionHandler = this.e;
        switch (i2) {
            case 0:
                sharedTransitionHandler.lambda$onEnterTransitionEndV2$1();
                return;
            default:
                sharedTransitionHandler.lambda$transitionPostEnd$2();
                return;
        }
    }
}
