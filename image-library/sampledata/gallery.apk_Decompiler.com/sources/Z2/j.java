package z2;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends FloatPropertyCompat {
    public final float getValue(Object obj) {
        return ((SnackbarContentLayout) obj).getScaleX();
    }

    public final void setValue(Object obj, float f) {
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) obj;
        snackbarContentLayout.setScaleX(f);
        snackbarContentLayout.setScaleY(f);
    }
}
