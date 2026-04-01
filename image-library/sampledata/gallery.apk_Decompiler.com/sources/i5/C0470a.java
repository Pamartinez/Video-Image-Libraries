package i5;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompletePresenterV2;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: i5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0470a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchAutoCompletePresenterV2 e;

    public /* synthetic */ C0470a(SearchAutoCompletePresenterV2 searchAutoCompletePresenterV2, int i2) {
        this.d = i2;
        this.e = searchAutoCompletePresenterV2;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SearchAutoCompletePresenterV2 searchAutoCompletePresenterV2 = this.e;
        switch (i2) {
            case 0:
                searchAutoCompletePresenterV2.onKeywordChanged(obj, bundle);
                return;
            default:
                searchAutoCompletePresenterV2.onAutoCompletePublished(obj, bundle);
                return;
        }
    }
}
