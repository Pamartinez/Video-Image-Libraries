package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomSheetDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Delegate.DataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2684a;
    public final /* synthetic */ BottomSheetDelegate b;

    public /* synthetic */ j(BottomSheetDelegate bottomSheetDelegate, int i2) {
        this.f2684a = i2;
        this.b = bottomSheetDelegate;
    }

    public final Object get(DataRequest dataRequest, Object[] objArr) {
        int i2 = this.f2684a;
        BottomSheetDelegate bottomSheetDelegate = this.b;
        switch (i2) {
            case 0:
                return bottomSheetDelegate.lambda$addRequestProvider$0(dataRequest, objArr);
            default:
                return bottomSheetDelegate.lambda$addRequestProvider$1(dataRequest, objArr);
        }
    }
}
