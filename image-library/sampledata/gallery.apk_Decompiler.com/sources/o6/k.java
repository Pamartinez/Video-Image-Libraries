package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.DefaultThemeDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2685a;
    public final /* synthetic */ DefaultThemeDelegate b;

    public /* synthetic */ k(DefaultThemeDelegate defaultThemeDelegate, int i2) {
        this.f2685a = i2;
        this.b = defaultThemeDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2685a;
        DefaultThemeDelegate defaultThemeDelegate = this.b;
        switch (i2) {
            case 0:
                return defaultThemeDelegate.lambda$addRequestProvider$0(dataRequest, objArr);
            case 1:
                return defaultThemeDelegate.lambda$addRequestProvider$1(dataRequest, objArr);
            case 2:
                return defaultThemeDelegate.lambda$addRequestProvider$2(dataRequest, objArr);
            case 3:
                return defaultThemeDelegate.lambda$addRequestProvider$3(dataRequest, objArr);
            case 4:
                return defaultThemeDelegate.lambda$addRequestProvider$4(dataRequest, objArr);
            default:
                return defaultThemeDelegate.lambda$addRequestProvider$5(dataRequest, objArr);
        }
    }
}
