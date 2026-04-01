package F6;

import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesPinchViewFragment e;

    public /* synthetic */ a(StoriesPinchViewFragment storiesPinchViewFragment, int i2) {
        this.d = i2;
        this.e = storiesPinchViewFragment;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$initView$0((BasePinView) obj);
                return;
            default:
                this.e.handePinEvent(((Integer) obj).intValue());
                return;
        }
    }
}
