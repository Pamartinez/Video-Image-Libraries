package q6;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.LastPageView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LastPageView e;

    public /* synthetic */ d(LastPageView lastPageView, int i2) {
        this.d = i2;
        this.e = lastPageView;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LastPageView lastPageView = this.e;
        switch (i2) {
            case 0:
                lastPageView.lambda$updateFocusedPosition$9((RecyclerView.LayoutManager) obj);
                return;
            default:
                lastPageView.lambda$initReference$0((Boolean) obj);
                return;
        }
    }
}
