package T4;

import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsDragAndDropDelegate e;

    public /* synthetic */ a(AlbumsDragAndDropDelegate albumsDragAndDropDelegate, int i2) {
        this.d = i2;
        this.e = albumsDragAndDropDelegate;
    }

    public final void run() {
        int i2 = this.d;
        AlbumsDragAndDropDelegate albumsDragAndDropDelegate = this.e;
        switch (i2) {
            case 0:
                albumsDragAndDropDelegate.lambda$runStartDragOnUI$14();
                return;
            default:
                albumsDragAndDropDelegate.lambda$runStartDragOnUI$15();
                return;
        }
    }
}
