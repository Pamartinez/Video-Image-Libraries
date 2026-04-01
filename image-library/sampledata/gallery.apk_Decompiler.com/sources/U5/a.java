package U5;

import Fa.V;
import com.samsung.android.gallery.module.settings.CmhProviderPermission;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Consumer e;

    public /* synthetic */ a(int i2, Consumer consumer) {
        this.d = i2;
        this.e = consumer;
    }

    public final void run() {
        int i2 = this.d;
        Consumer consumer = this.e;
        switch (i2) {
            case 0:
                consumer.accept(Boolean.FALSE);
                return;
            default:
                ThreadUtil.postOnUiThread(new V(consumer, 2, CmhProviderPermission.get()));
                return;
        }
    }
}
