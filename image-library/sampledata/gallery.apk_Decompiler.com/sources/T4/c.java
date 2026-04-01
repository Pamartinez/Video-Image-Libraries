package T4;

import android.content.Context;
import com.samsung.android.gallery.app.ui.list.dragdrop.ListDragAndDropDelegate;
import com.samsung.scsp.common.ContextFactory;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ c(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final Object get() {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                return ListDragAndDropDelegate.lambda$new$0(context);
            case 1:
                return ContextFactory.lambda$attachBaseContext$0(context);
            case 2:
                return ContextFactory.lambda$attachApplicationContext$1(context);
            default:
                return ContextFactory.lambda$attachApplicationContext$2(context);
        }
    }
}
