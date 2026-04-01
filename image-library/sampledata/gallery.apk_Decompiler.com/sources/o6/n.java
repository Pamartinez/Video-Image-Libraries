package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilterApplyDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FilterApplyDelegate e;

    public /* synthetic */ n(FilterApplyDelegate filterApplyDelegate, int i2) {
        this.d = i2;
        this.e = filterApplyDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FilterApplyDelegate filterApplyDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                filterApplyDelegate.setFilter(objArr);
                return;
            case 1:
                filterApplyDelegate.applyFilterToBitmap(objArr);
                return;
            case 2:
                filterApplyDelegate.lambda$addListenEvent$0(objArr);
                return;
            default:
                filterApplyDelegate.lambda$addListenEvent$1(objArr);
                return;
        }
    }
}
