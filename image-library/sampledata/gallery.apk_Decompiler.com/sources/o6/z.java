package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ViewPagerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2688a;
    public final /* synthetic */ ViewPagerDelegate b;

    public /* synthetic */ z(ViewPagerDelegate viewPagerDelegate, int i2) {
        this.f2688a = i2;
        this.b = viewPagerDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2688a;
        ViewPagerDelegate viewPagerDelegate = this.b;
        switch (i2) {
            case 0:
                return viewPagerDelegate.lambda$addRequestProvider$7(dataRequest, objArr);
            case 1:
                return viewPagerDelegate.lambda$addRequestProvider$8(dataRequest, objArr);
            case 2:
                return viewPagerDelegate.lambda$addRequestProvider$9(dataRequest, objArr);
            case 3:
                return viewPagerDelegate.lambda$addRequestProvider$10(dataRequest, objArr);
            case 4:
                return viewPagerDelegate.lambda$addRequestProvider$11(dataRequest, objArr);
            default:
                return viewPagerDelegate.lambda$addRequestProvider$12(dataRequest, objArr);
        }
    }
}
