package D5;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.DecorViewDelegate;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public /* synthetic */ a(View view, int i2) {
        this.d = i2;
        this.e = view;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ViewUtils.setVisibleOrInvisible(this.e, true);
                return;
            case 1:
                this.e.setPressed(false);
                return;
            case 2:
                ViewUtils.setVisibleOrInvisible(this.e, true);
                return;
            case 3:
                CoverGradientBlur.applyInternal(this.e);
                return;
            case 4:
                CoverGradientBlur.applyRecapInternal(this.e);
                return;
            case 5:
                this.e.performAccessibilityAction(64, (Bundle) null);
                return;
            case 6:
                View view = this.e;
                ((InputMethodManager) ContextCompat.getSystemService(view.getContext(), InputMethodManager.class)).showSoftInput(view, 1);
                return;
            case 7:
                DecorViewDelegate.lambda$showToolbarWithANim$5(this.e);
                return;
            default:
                this.e.setX((float) ViewMarginUtils.getStartMargin(this.e));
                return;
        }
    }
}
