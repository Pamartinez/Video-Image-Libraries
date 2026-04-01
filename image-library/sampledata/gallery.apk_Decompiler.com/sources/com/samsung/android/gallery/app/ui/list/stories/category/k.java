package com.samsung.android.gallery.app.ui.list.stories.category;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesCategory2Presenter e;

    public /* synthetic */ k(StoriesCategory2Presenter storiesCategory2Presenter, int i2) {
        this.d = i2;
        this.e = storiesCategory2Presenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        StoriesCategory2Presenter storiesCategory2Presenter = this.e;
        switch (i2) {
            case 0:
                storiesCategory2Presenter.lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
            default:
                storiesCategory2Presenter.onDateTimeChanged(obj, bundle);
                return;
        }
    }
}
