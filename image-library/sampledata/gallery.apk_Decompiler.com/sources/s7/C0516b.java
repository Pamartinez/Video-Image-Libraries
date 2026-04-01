package s7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;

/* renamed from: s7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0516b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TransitionDelegate e;

    public /* synthetic */ C0516b(TransitionDelegate transitionDelegate, int i2) {
        this.d = i2;
        this.e = transitionDelegate;
    }

    public final void run() {
        int i2 = this.d;
        TransitionDelegate transitionDelegate = this.e;
        switch (i2) {
            case 0:
                transitionDelegate.lambda$prepareGroupPanelReturnTransition$10();
                return;
            case 1:
                transitionDelegate.lambda$onBitmapLoaded$1();
                return;
            case 2:
                transitionDelegate.lambda$onApplyWindowInsets$2();
                return;
            case 3:
                transitionDelegate.lambda$onHandleEvent$0();
                return;
            default:
                transitionDelegate.lambda$prepareRemasterReturnTransition$8();
                return;
        }
    }
}
