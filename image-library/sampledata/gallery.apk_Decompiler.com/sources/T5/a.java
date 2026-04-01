package T5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBasePresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesBasePresenter e;

    public /* synthetic */ a(StoriesBasePresenter storiesBasePresenter, int i2) {
        this.d = i2;
        this.e = storiesBasePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoriesBasePresenter storiesBasePresenter = this.e;
        switch (i2) {
            case 0:
                storiesBasePresenter.blockEvent(obj, bundle);
                return;
            default:
                storiesBasePresenter.onViewChanged(obj, bundle);
                return;
        }
    }
}
