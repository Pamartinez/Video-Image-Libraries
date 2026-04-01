package T4;

import com.samsung.android.gallery.app.ui.list.dragdrop.PicturesDragAndDropDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PicturesDragAndDropDelegate e;

    public /* synthetic */ d(PicturesDragAndDropDelegate picturesDragAndDropDelegate, int i2) {
        this.d = i2;
        this.e = picturesDragAndDropDelegate;
    }

    public final void run() {
        int i2 = this.d;
        PicturesDragAndDropDelegate picturesDragAndDropDelegate = this.e;
        switch (i2) {
            case 0:
                picturesDragAndDropDelegate.lambda$startDragInner$1();
                return;
            default:
                picturesDragAndDropDelegate.lambda$startDragInner$0();
                return;
        }
    }
}
