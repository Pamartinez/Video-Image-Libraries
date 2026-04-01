package com.samsung.android.gallery.app.ui.list.search.recommendation;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecommendationFragment.AnonymousClass2 e;

    public /* synthetic */ e(RecommendationFragment.AnonymousClass2 r1, int i2) {
        this.d = i2;
        this.e = r1;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        RecommendationFragment.AnonymousClass2 r1 = this.e;
        switch (i2) {
            case 0:
                r1.lambda$createSubscriberList$0(obj, bundle);
                return;
            default:
                r1.lambda$createSubscriberList$1(obj, bundle);
                return;
        }
    }
}
