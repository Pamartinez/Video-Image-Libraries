package U6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.trash.container.TrashContainerFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TrashContainerFragment e;

    public /* synthetic */ a(TrashContainerFragment trashContainerFragment, int i2) {
        this.d = i2;
        this.e = trashContainerFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        TrashContainerFragment trashContainerFragment = this.e;
        switch (i2) {
            case 0:
                trashContainerFragment.lambda$onBindView$2(view);
                return;
            default:
                trashContainerFragment.lambda$onBindView$4(view);
                return;
        }
    }
}
