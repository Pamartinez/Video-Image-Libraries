package F6;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPinchViewPresenter e;

    public /* synthetic */ c(StoriesPinchViewPresenter storiesPinchViewPresenter, int i2) {
        this.d = i2;
        this.e = storiesPinchViewPresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoriesPinchViewPresenter storiesPinchViewPresenter = this.e;
        switch (i2) {
            case 0:
                storiesPinchViewPresenter.updateStoryTheme(obj, bundle);
                return;
            default:
                storiesPinchViewPresenter.setPendingStoriesDataUpdate(obj, bundle);
                return;
        }
    }
}
