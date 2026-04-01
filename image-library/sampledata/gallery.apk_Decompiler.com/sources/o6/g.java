package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.BottomDecoViewDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomDecoViewDelegate e;

    public /* synthetic */ g(BottomDecoViewDelegate bottomDecoViewDelegate, int i2) {
        this.d = i2;
        this.e = bottomDecoViewDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BottomDecoViewDelegate bottomDecoViewDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                bottomDecoViewDelegate.lambda$addListenEvent$2(objArr);
                return;
            case 1:
                bottomDecoViewDelegate.setVisible(objArr);
                return;
            case 2:
                bottomDecoViewDelegate.onViewPagerScrolled(objArr);
                return;
            case 3:
                bottomDecoViewDelegate.updateDurationText(objArr);
                return;
            case 4:
                bottomDecoViewDelegate.onKeepPauseStateChanged(objArr);
                return;
            default:
                bottomDecoViewDelegate.lambda$addListenEvent$1(objArr);
                return;
        }
    }
}
