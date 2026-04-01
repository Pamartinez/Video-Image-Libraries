package U6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ TrashContainerFragment e;

    public /* synthetic */ b(TrashContainerFragment trashContainerFragment, int i2) {
        this.d = i2;
        this.e = trashContainerFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        TrashContainerFragment trashContainerFragment = this.e;
        View view = (View) obj;
        switch (i2) {
            case 0:
                trashContainerFragment.lambda$onBindView$3(view);
                return;
            case 1:
                trashContainerFragment.lambda$onBindView$5(view);
                return;
            case 2:
                trashContainerFragment.lambda$onBindView$6(view);
                return;
            default:
                trashContainerFragment.lambda$onBindView$7(view);
                return;
        }
    }
}
