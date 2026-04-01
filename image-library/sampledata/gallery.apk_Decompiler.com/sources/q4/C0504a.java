package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.AlbumCreatePopupDialog;
import java.util.function.Consumer;

/* renamed from: q4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0504a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumCreatePopupDialog e;

    public /* synthetic */ C0504a(AlbumCreatePopupDialog albumCreatePopupDialog, int i2) {
        this.d = i2;
        this.e = albumCreatePopupDialog;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AlbumCreatePopupDialog albumCreatePopupDialog = this.e;
        View view = (View) obj;
        switch (i2) {
            case 0:
                albumCreatePopupDialog.lambda$inflateCreateViews$0(view);
                return;
            case 1:
                albumCreatePopupDialog.lambda$inflateCreateViews$1(view);
                return;
            default:
                albumCreatePopupDialog.lambda$inflateCreateViews$2(view);
                return;
        }
    }
}
