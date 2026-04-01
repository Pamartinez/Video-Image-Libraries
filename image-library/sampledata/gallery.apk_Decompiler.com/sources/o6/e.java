package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPlayerDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2681a;
    public final /* synthetic */ BgmPlayerDelegate b;

    public /* synthetic */ e(BgmPlayerDelegate bgmPlayerDelegate, int i2) {
        this.f2681a = i2;
        this.b = bgmPlayerDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2681a;
        BgmPlayerDelegate bgmPlayerDelegate = this.b;
        switch (i2) {
            case 0:
                return bgmPlayerDelegate.lambda$addRequestProvider$4(dataRequest, objArr);
            case 1:
                return bgmPlayerDelegate.lambda$addRequestProvider$5(dataRequest, objArr);
            default:
                return bgmPlayerDelegate.lambda$addRequestProvider$6(dataRequest, objArr);
        }
    }
}
