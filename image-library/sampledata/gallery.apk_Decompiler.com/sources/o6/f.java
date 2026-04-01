package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2682a;
    public final /* synthetic */ BottomDecoViewDelegate b;

    public /* synthetic */ f(BottomDecoViewDelegate bottomDecoViewDelegate, int i2) {
        this.f2682a = i2;
        this.b = bottomDecoViewDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2682a;
        BottomDecoViewDelegate bottomDecoViewDelegate = this.b;
        switch (i2) {
            case 0:
                return bottomDecoViewDelegate.lambda$addRequestProvider$3(dataRequest, objArr);
            case 1:
                return bottomDecoViewDelegate.lambda$addRequestProvider$4(dataRequest, objArr);
            case 2:
                return bottomDecoViewDelegate.lambda$addRequestProvider$5(dataRequest, objArr);
            case 3:
                return bottomDecoViewDelegate.lambda$addRequestProvider$6(dataRequest, objArr);
            default:
                return bottomDecoViewDelegate.lambda$addRequestProvider$7(dataRequest, objArr);
        }
    }
}
