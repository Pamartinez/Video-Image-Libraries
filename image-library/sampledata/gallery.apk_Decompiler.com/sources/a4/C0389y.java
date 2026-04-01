package A4;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListKeyHandler;
import com.samsung.android.gallery.widget.listview.handler.DisHandler;
import java.util.List;
import java.util.function.Consumer;

/* renamed from: A4.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0389y implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListKeyHandler e;

    public /* synthetic */ C0389y(BaseListKeyHandler baseListKeyHandler, int i2) {
        this.d = i2;
        this.e = baseListKeyHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BaseListKeyHandler baseListKeyHandler = this.e;
        switch (i2) {
            case 0:
                baseListKeyHandler.lambda$onPenEvent$9((DisHandler) obj);
                return;
            case 1:
                baseListKeyHandler.invalidateOptionsMenu((Void) obj);
                return;
            default:
                baseListKeyHandler.lambda$onPenEvent$10((List) obj);
                return;
        }
    }
}
