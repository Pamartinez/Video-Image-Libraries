package V7;

import android.view.MotionEvent;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewZoomDelegate;
import com.samsung.android.gallery.widget.gesture.OnShrinkGestureListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements OnShrinkGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SelectionViewZoomDelegate f2459a;

    public /* synthetic */ b(SelectionViewZoomDelegate selectionViewZoomDelegate) {
        this.f2459a = selectionViewZoomDelegate;
    }

    public final boolean onShrinkGesture(MotionEvent motionEvent) {
        return this.f2459a.onShrinkGesture(motionEvent);
    }
}
