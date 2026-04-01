package H6;

import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.RecapLastPageDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecapLastPageDelegate e;

    public /* synthetic */ i(RecapLastPageDelegate recapLastPageDelegate, int i2) {
        this.d = i2;
        this.e = recapLastPageDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        RecapLastPageDelegate recapLastPageDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                recapLastPageDelegate.lambda$addListenEvent$0(objArr);
                return;
            default:
                recapLastPageDelegate.lambda$addListenEvent$1(objArr);
                return;
        }
    }
}
