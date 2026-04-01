package N4;

import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecyclerView.LayoutManager e;

    public /* synthetic */ c(int i2, RecyclerView.LayoutManager layoutManager) {
        this.d = i2;
        this.e = layoutManager;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        RecyclerView.LayoutManager layoutManager = this.e;
        RecyclerView recyclerView = (RecyclerView) obj;
        switch (i2) {
            case 0:
                recyclerView.setLayoutManager(layoutManager);
                return;
            case 1:
                recyclerView.setLayoutManager(layoutManager);
                return;
            default:
                recyclerView.setLayoutManager(layoutManager);
                return;
        }
    }
}
