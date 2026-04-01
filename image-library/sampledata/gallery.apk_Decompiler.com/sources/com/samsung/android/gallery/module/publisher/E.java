package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataBasePublisher e;

    public /* synthetic */ E(SearchDataBasePublisher searchDataBasePublisher, int i2) {
        this.d = i2;
        this.e = searchDataBasePublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchDataBasePublisher searchDataBasePublisher = this.e;
        switch (i2) {
            case 0:
                searchDataBasePublisher.onContext(obj, bundle);
                return;
            case 1:
                searchDataBasePublisher.publishRecommendationData(obj, bundle);
                return;
            case 2:
                searchDataBasePublisher.publishVideoFiles(obj, bundle);
                return;
            case 3:
                searchDataBasePublisher.publishSmilesFiles(obj, bundle);
                return;
            case 4:
                searchDataBasePublisher.publishBlurryFiles(obj, bundle);
                return;
            case 5:
                searchDataBasePublisher.publishFromTimeFiles(obj, bundle);
                return;
            case 6:
                searchDataBasePublisher.publishLastLocationFiles(obj, bundle);
                return;
            case 7:
                searchDataBasePublisher.publishLatestCategoryFiles(obj, bundle);
                return;
            default:
                searchDataBasePublisher.publishLatestItemFiles(obj, bundle);
                return;
        }
    }
}
