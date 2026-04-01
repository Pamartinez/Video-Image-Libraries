package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesOnDemandDelegate e;
    public final /* synthetic */ String f;

    public /* synthetic */ o(StoriesOnDemandDelegate storiesOnDemandDelegate, String str, int i2) {
        this.d = i2;
        this.e = storiesOnDemandDelegate;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$continueOnDemand$0(this.f);
                return;
            default:
                this.e.lambda$showFailView$10(this.f);
                return;
        }
    }
}
