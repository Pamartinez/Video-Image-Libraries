package oa;

import android.os.Bundle;
import com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SuggestedLocalUpdater e;

    public /* synthetic */ a(SuggestedLocalUpdater suggestedLocalUpdater, int i2) {
        this.d = i2;
        this.e = suggestedLocalUpdater;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SuggestedLocalUpdater suggestedLocalUpdater = this.e;
        switch (i2) {
            case 0:
                suggestedLocalUpdater.onCurrent(obj, bundle);
                return;
            default:
                suggestedLocalUpdater.onSuggestedDataChanged(obj, bundle);
                return;
        }
    }
}
