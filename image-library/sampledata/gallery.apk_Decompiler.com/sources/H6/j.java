package H6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.RecapLastPageDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2339a;
    public final /* synthetic */ RecapLastPageDelegate b;

    public /* synthetic */ j(RecapLastPageDelegate recapLastPageDelegate, int i2) {
        this.f2339a = i2;
        this.b = recapLastPageDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2339a;
        RecapLastPageDelegate recapLastPageDelegate = this.b;
        switch (i2) {
            case 0:
                return recapLastPageDelegate.lambda$addRequestProvider$2(dataRequest, objArr);
            case 1:
                return recapLastPageDelegate.lambda$addRequestProvider$3(dataRequest, objArr);
            default:
                return recapLastPageDelegate.lambda$addRequestProvider$4(dataRequest, objArr);
        }
    }
}
