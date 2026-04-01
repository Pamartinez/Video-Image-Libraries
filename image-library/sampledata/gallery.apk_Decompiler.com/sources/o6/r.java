package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.LastPageDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2687a;
    public final /* synthetic */ LastPageDelegate b;

    public /* synthetic */ r(LastPageDelegate lastPageDelegate, int i2) {
        this.f2687a = i2;
        this.b = lastPageDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2687a;
        LastPageDelegate lastPageDelegate = this.b;
        switch (i2) {
            case 0:
                return lastPageDelegate.lambda$addRequestProvider$3(dataRequest, objArr);
            case 1:
                return lastPageDelegate.lambda$addRequestProvider$4(dataRequest, objArr);
            default:
                return lastPageDelegate.lambda$addRequestProvider$5(dataRequest, objArr);
        }
    }
}
