package A4;

import android.view.DragEvent;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureView;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.DragAndDropHelper;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderItemController;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewerDragNDropDelegate;

/* renamed from: A4.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0378m implements View.OnDragListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2254a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0378m(int i2, Object obj) {
        this.f2254a = i2;
        this.b = obj;
    }

    public final boolean onDrag(View view, DragEvent dragEvent) {
        int i2 = this.f2254a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((BaseListFragment) obj).handleDrag(view, dragEvent);
            case 1:
                return ObjectCaptureView.setDragListener$lambda$1((ObjectCaptureView) obj, view, dragEvent);
            case 2:
                return ((ReorderItemController) obj).handleDrag(view, dragEvent);
            case 3:
                return ((ViewerDragNDropDelegate) obj).lambda$onBindView$0(view, dragEvent);
            default:
                return DragAndDropHelper.startDragAndDrop$lambda$0((DragAndDropHelper) obj, view, dragEvent);
        }
    }
}
