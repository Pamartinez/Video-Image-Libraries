package o7;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.KeyboardMouseDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ KeyboardMouseDelegate e;
    public final /* synthetic */ ViewPager2 f;

    public /* synthetic */ e(KeyboardMouseDelegate keyboardMouseDelegate, ViewPager2 viewPager2, int i2) {
        this.d = i2;
        this.e = keyboardMouseDelegate;
        this.f = viewPager2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$onKeyDownAirGesture$0(this.f, (RecyclerView.Adapter) obj);
                return;
            default:
                this.e.lambda$onKeyDownAirGesture$1(this.f, (RecyclerView.Adapter) obj);
                return;
        }
    }
}
