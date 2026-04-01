package Fa;

import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;

/* renamed from: Fa.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0548b implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BasePreferenceFragment e;

    public /* synthetic */ C0548b(BasePreferenceFragment basePreferenceFragment, int i2) {
        this.d = i2;
        this.e = basePreferenceFragment;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int i2 = this.d;
        BasePreferenceFragment basePreferenceFragment = this.e;
        switch (i2) {
            case 0:
                return basePreferenceFragment.onApplyWindowInsets(view, windowInsets);
            default:
                return basePreferenceFragment.lambda$initOverlayView$1(view, windowInsets);
        }
    }
}
