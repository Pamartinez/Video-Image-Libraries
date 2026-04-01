package L7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.details.EditDetailsHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditDetailsHandler e;

    public /* synthetic */ t(EditDetailsHandler editDetailsHandler, int i2) {
        this.d = i2;
        this.e = editDetailsHandler;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        EditDetailsHandler editDetailsHandler = this.e;
        switch (i2) {
            case 0:
                editDetailsHandler.lambda$setToolbar$3(view);
                return;
            case 1:
                editDetailsHandler.onEditDateClick(view);
                return;
            case 2:
                editDetailsHandler.onDeleteCapturedUrl(view);
                return;
            case 3:
                editDetailsHandler.onAddLocation(view);
                return;
            default:
                editDetailsHandler.onDeleteLocation(view);
                return;
        }
    }
}
