package U4;

import android.content.Context;
import android.widget.PopupWindow;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListPopupMenuDelegate e;

    public /* synthetic */ a(ListPopupMenuDelegate listPopupMenuDelegate, int i2) {
        this.d = i2;
        this.e = listPopupMenuDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ListPopupMenuDelegate listPopupMenuDelegate = this.e;
        switch (i2) {
            case 0:
                listPopupMenuDelegate.lambda$initPreview$2((Context) obj);
                return;
            case 1:
                listPopupMenuDelegate.lambda$initListView$1((GalleryListView) obj);
                return;
            case 2:
                listPopupMenuDelegate.lambda$initDimView$0((Context) obj);
                return;
            case 3:
                listPopupMenuDelegate.lambda$destroy$6((GalleryListView) obj);
                return;
            default:
                listPopupMenuDelegate.lambda$setPopupWindowProperty$9((PopupWindow) obj);
                return;
        }
    }
}
