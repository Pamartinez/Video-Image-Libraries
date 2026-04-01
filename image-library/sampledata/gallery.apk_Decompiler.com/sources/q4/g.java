package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.CreateNewDialogV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreateNewDialogV2 e;

    public /* synthetic */ g(CreateNewDialogV2 createNewDialogV2, int i2) {
        this.d = i2;
        this.e = createNewDialogV2;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        CreateNewDialogV2 createNewDialogV2 = this.e;
        switch (i2) {
            case 0:
                createNewDialogV2.onListItemClicked(view);
                return;
            default:
                createNewDialogV2.onVideoStudioButtonClicked(view);
                return;
        }
    }
}
