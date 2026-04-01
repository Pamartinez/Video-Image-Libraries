package z2;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends ViewOutlineProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ float f2231a;
    public final /* synthetic */ SnackbarContentLayout b;

    public t(SnackbarContentLayout snackbarContentLayout, float f) {
        this.b = snackbarContentLayout;
        this.f2231a = f;
    }

    public final void getOutline(View view, Outline outline) {
        SnackbarContentLayout snackbarContentLayout = this.b;
        outline.setRoundRect(0, 0, snackbarContentLayout.f1496i.getMeasuredWidth(), snackbarContentLayout.f1496i.getMeasuredHeight(), this.f2231a);
    }
}
