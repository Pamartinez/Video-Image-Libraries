package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPickerDelegate;

/* renamed from: o6.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0498b implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2680a;
    public final /* synthetic */ BgmPickerDelegate b;

    public /* synthetic */ C0498b(BgmPickerDelegate bgmPickerDelegate, int i2) {
        this.f2680a = i2;
        this.b = bgmPickerDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2680a;
        BgmPickerDelegate bgmPickerDelegate = this.b;
        switch (i2) {
            case 0:
                return bgmPickerDelegate.lambda$addRequestProvider$0(dataRequest, objArr);
            default:
                return bgmPickerDelegate.lambda$addRequestProvider$1(dataRequest, objArr);
        }
    }
}
