package k2;

import U1.a;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* renamed from: k2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0223a implements View.OnLayoutChangeListener {
    public final /* synthetic */ d d;

    public C0223a(d dVar) {
        this.d = dVar;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        a aVar;
        d dVar = this.d;
        ImageView imageView = dVar.s;
        if (imageView.getVisibility() == 0 && (aVar = dVar.L) != null) {
            Rect rect = new Rect();
            imageView.getDrawingRect(rect);
            aVar.setBounds(rect);
            aVar.i(imageView, (FrameLayout) null);
        }
    }
}
