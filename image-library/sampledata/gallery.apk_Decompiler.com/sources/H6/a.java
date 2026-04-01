package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.DecorLayoutDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DecorLayoutDelegate e;

    public /* synthetic */ a(DecorLayoutDelegate decorLayoutDelegate, int i2) {
        this.d = i2;
        this.e = decorLayoutDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DecorLayoutDelegate decorLayoutDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                decorLayoutDelegate.updateVisibility(objArr);
                return;
            case 1:
                decorLayoutDelegate.lambda$addListenEvent$0(objArr);
                return;
            default:
                decorLayoutDelegate.lambda$addListenEvent$1(objArr);
                return;
        }
    }
}
