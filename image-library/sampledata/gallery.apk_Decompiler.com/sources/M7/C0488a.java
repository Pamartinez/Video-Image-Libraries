package m7;

import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverMenuHandler;
import java.util.function.Consumer;

/* renamed from: m7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0488a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ FlipCoverMenuDelegate e;

    public /* synthetic */ C0488a(FlipCoverMenuDelegate flipCoverMenuDelegate, int i2) {
        this.d = i2;
        this.e = flipCoverMenuDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        FlipCoverMenuDelegate flipCoverMenuDelegate = this.e;
        switch (i2) {
            case 0:
                flipCoverMenuDelegate.lambda$initMenu$5((FlipCoverMenuHandler) obj);
                return;
            case 1:
                flipCoverMenuDelegate.lambda$onDestroy$4((ViewPager2) obj);
                return;
            default:
                flipCoverMenuDelegate.lambda$onBindView$3((ViewPager2) obj);
                return;
        }
    }
}
