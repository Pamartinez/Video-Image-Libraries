package q4;

import android.view.View;
import com.samsung.android.gallery.app.ui.dialog.SortByAlbumDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SortByAlbumDialog e;

    public /* synthetic */ x(SortByAlbumDialog sortByAlbumDialog, int i2) {
        this.d = i2;
        this.e = sortByAlbumDialog;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SortByAlbumDialog sortByAlbumDialog = this.e;
        switch (i2) {
            case 0:
                sortByAlbumDialog.onClickPositive(view);
                return;
            default:
                sortByAlbumDialog.onClickNegative(view);
                return;
        }
    }
}
