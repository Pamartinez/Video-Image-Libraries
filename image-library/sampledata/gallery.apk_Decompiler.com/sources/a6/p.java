package a6;

import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.module.story.ondemand.OnDemandResultData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesOnDemandDelegate e;
    public final /* synthetic */ OnDemandResultData f;

    public /* synthetic */ p(StoriesOnDemandDelegate storiesOnDemandDelegate, OnDemandResultData onDemandResultData, int i2) {
        this.d = i2;
        this.e = storiesOnDemandDelegate;
        this.f = onDemandResultData;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleFail$7(this.f);
                return;
            default:
                this.e.lambda$handleFail$6(this.f);
                return;
        }
    }
}
