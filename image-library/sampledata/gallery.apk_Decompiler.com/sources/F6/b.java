package F6;

import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPinchViewFragment e;

    public /* synthetic */ b(StoriesPinchViewFragment storiesPinchViewFragment, int i2) {
        this.d = i2;
        this.e = storiesPinchViewFragment;
    }

    public final void run() {
        int i2 = this.d;
        StoriesPinchViewFragment storiesPinchViewFragment = this.e;
        switch (i2) {
            case 0:
                storiesPinchViewFragment.startShrinkAnimation();
                return;
            default:
                storiesPinchViewFragment.lambda$startPinAutoScroll$2();
                return;
        }
    }
}
