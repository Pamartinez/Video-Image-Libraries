package bc;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPager;
import java.util.function.Consumer;

/* renamed from: bc.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0586c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesViewPager e;

    public /* synthetic */ C0586c(StoriesViewPager storiesViewPager, int i2) {
        this.d = i2;
        this.e = storiesViewPager;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$setListAccessibilityDelegate$5((RecyclerView) obj);
                return;
            default:
                this.e.onAccessibilityStateChanged(((Boolean) obj).booleanValue());
                return;
        }
    }
}
