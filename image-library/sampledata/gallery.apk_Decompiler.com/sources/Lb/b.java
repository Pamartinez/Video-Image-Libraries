package Lb;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveMotionViewPager e;

    public /* synthetic */ b(LiveMotionViewPager liveMotionViewPager, int i2) {
        this.d = i2;
        this.e = liveMotionViewPager;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LiveMotionViewPager liveMotionViewPager = this.e;
        switch (i2) {
            case 0:
                liveMotionViewPager.lambda$isTouchUpOrCancel$4((Boolean) obj);
                return;
            default:
                liveMotionViewPager.lambda$setListAccessibilityDelegate$10((RecyclerView) obj);
                return;
        }
    }
}
