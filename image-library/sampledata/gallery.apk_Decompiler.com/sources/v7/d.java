package v7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ContentDescriptionHandler;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ContentDescriptionHandler e;

    public /* synthetic */ d(ContentDescriptionHandler contentDescriptionHandler, int i2) {
        this.d = i2;
        this.e = contentDescriptionHandler;
    }

    public final Object get() {
        int i2 = this.d;
        ContentDescriptionHandler contentDescriptionHandler = this.e;
        switch (i2) {
            case 0:
                return contentDescriptionHandler.getDefaultContentDescription();
            default:
                return contentDescriptionHandler.getCustomUsageHint();
        }
    }
}
