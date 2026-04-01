package X1;

import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.SnackbarContentLayout;
import z2.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewGroup e;

    public /* synthetic */ e(ViewGroup viewGroup, int i2) {
        this.d = i2;
        this.e = viewGroup;
    }

    public final void onGlobalLayout() {
        Button button;
        switch (this.d) {
            case 0:
                BottomNavigationView bottomNavigationView = (BottomNavigationView) this.e;
                bottomNavigationView.post(new d(bottomNavigationView));
                return;
            default:
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) this.e;
                snackbarContentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SnackbarContentLayout snackbarContentLayout2 = snackbarContentLayout.f1496i;
                if (snackbarContentLayout2 != null && (button = snackbarContentLayout.e) != null && button.getVisibility() == 0) {
                    snackbarContentLayout2.post(new s(this));
                    return;
                }
                return;
        }
    }
}
