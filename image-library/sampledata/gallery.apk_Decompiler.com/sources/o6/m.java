package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilterApplyDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.OsdUiDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ReplayDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ThemeViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2686a;
    public final /* synthetic */ Delegate b;

    public /* synthetic */ m(int i2, Delegate delegate) {
        this.f2686a = i2;
        this.b = delegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2686a;
        Delegate delegate = this.b;
        switch (i2) {
            case 0:
                return ((FilterApplyDelegate) delegate).lambda$addRequestProvider$2(dataRequest, objArr);
            case 1:
                return ((OsdUiDelegate) delegate).lambda$addRequestProvider$0(dataRequest, objArr);
            case 2:
                return ((ReplayDelegate) delegate).lambda$addRequestProvider$1(dataRequest, objArr);
            default:
                return ((ThemeViewDelegate) delegate).lambda$addRequestProvider$0(dataRequest, objArr);
        }
    }
}
