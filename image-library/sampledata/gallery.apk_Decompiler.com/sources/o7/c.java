package o7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ExitGestureDelegate;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ExitGestureDelegate e;
    public final /* synthetic */ Blackboard f;

    public /* synthetic */ c(ExitGestureDelegate exitGestureDelegate, Blackboard blackboard, int i2) {
        this.d = i2;
        this.e = exitGestureDelegate;
        this.f = blackboard;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleShrinkToCamera$0(this.f);
                return;
            default:
                this.e.lambda$handleShrinkToCamera$3(this.f);
                return;
        }
    }
}
