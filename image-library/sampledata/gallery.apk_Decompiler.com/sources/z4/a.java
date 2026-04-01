package z4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AddTagDialog e;

    public /* synthetic */ a(AddTagDialog addTagDialog, int i2) {
        this.d = i2;
        this.e = addTagDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        AddTagDialog addTagDialog = this.e;
        switch (i2) {
            case 0:
                addTagDialog.lambda$bindViews$0(view);
                return;
            default:
                addTagDialog.lambda$bindViews$1(view);
                return;
        }
    }
}
