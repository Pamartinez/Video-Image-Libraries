package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialogV2;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateNewDialogV2 e;

    public /* synthetic */ f(CreateNewDialogV2 createNewDialogV2, int i2) {
        this.d = i2;
        this.e = createNewDialogV2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        CreateNewDialogV2 createNewDialogV2 = this.e;
        switch (i2) {
            case 0:
                createNewDialogV2.lambda$createVideoStudioButtonView$1((View) obj);
                return;
            default:
                createNewDialogV2.lambda$onListItemClicked$2((Integer) obj);
                return;
        }
    }
}
