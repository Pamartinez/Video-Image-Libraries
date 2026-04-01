package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.LastPageDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LastPageDelegate e;

    public /* synthetic */ s(LastPageDelegate lastPageDelegate, int i2) {
        this.d = i2;
        this.e = lastPageDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LastPageDelegate lastPageDelegate = this.e;
        switch (i2) {
            case 0:
                lastPageDelegate.lambda$handleSaveOnDemandStory$7(obj);
                return;
            case 1:
                lastPageDelegate.onStoryChanged((Object[]) obj);
                return;
            case 2:
                lastPageDelegate.prepareStoryChange((Object[]) obj);
                return;
            case 3:
                lastPageDelegate.lambda$addListenEvent$0((Object[]) obj);
                return;
            case 4:
                lastPageDelegate.lambda$addListenEvent$1((Object[]) obj);
                return;
            default:
                lastPageDelegate.lambda$addListenEvent$2((Object[]) obj);
                return;
        }
    }
}
