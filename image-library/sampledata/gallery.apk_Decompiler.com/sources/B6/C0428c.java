package b6;

import com.samsung.android.gallery.app.ui.list.stories.header.SelectionHelper;
import java.util.function.Consumer;

/* renamed from: b6.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0428c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SelectionHelper e;

    public /* synthetic */ C0428c(SelectionHelper selectionHelper, int i2) {
        this.d = i2;
        this.e = selectionHelper;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SelectionHelper selectionHelper = this.e;
        switch (i2) {
            case 0:
                selectionHelper.lambda$add$0(obj);
                return;
            default:
                selectionHelper.add(obj);
                return;
        }
    }
}
