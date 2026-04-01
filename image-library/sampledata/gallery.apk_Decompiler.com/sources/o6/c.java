package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BgmPlayerDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BgmPlayerDelegate e;

    public /* synthetic */ c(BgmPlayerDelegate bgmPlayerDelegate, int i2) {
        this.d = i2;
        this.e = bgmPlayerDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BgmPlayerDelegate bgmPlayerDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                bgmPlayerDelegate.lambda$addListenEvent$0(objArr);
                return;
            case 1:
                bgmPlayerDelegate.lambda$addListenEvent$1(objArr);
                return;
            case 2:
                bgmPlayerDelegate.lambda$addListenEvent$2(objArr);
                return;
            default:
                bgmPlayerDelegate.lambda$addListenEvent$3(objArr);
                return;
        }
    }
}
