package c7;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.map.staticmarker.MarkerViewHolder;

/* renamed from: c7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0445a implements View.OnLayoutChangeListener {
    public final /* synthetic */ MarkerViewHolder d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ C0445a(MarkerViewHolder markerViewHolder, Bitmap bitmap) {
        this.d = markerViewHolder;
        this.e = bitmap;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.d.lambda$addLayoutChangeListener$1(this.e, view, i2, i7, i8, i10, i11, i12, i13, i14);
    }
}
