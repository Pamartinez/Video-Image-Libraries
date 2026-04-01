package O8;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* renamed from: O8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0576a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;

    public /* synthetic */ C0576a(String str, String str2, int i2) {
        this.d = i2;
        this.e = str;
        this.f = str2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((BiConsumer) obj).accept(this.e, this.f);
                return;
            default:
                AnalyticsLogger.getInstance().postLog(((EventContext) obj).getScreenId(), this.e, this.f);
                return;
        }
    }
}
