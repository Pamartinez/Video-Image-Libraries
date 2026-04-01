package z2;

import X1.e;
import android.view.View;
import androidx.core.view.SeslTouchTargetDelegate;
import com.google.android.material.snackbar.SnackbarContentLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements Runnable {
    public final /* synthetic */ e d;

    public s(e eVar) {
        this.d = eVar;
    }

    public final void run() {
        e eVar = this.d;
        SeslTouchTargetDelegate seslTouchTargetDelegate = new SeslTouchTargetDelegate(((SnackbarContentLayout) eVar.e).f1496i);
        int measuredHeight = ((SnackbarContentLayout) eVar.e).e.getMeasuredHeight() / 2;
        seslTouchTargetDelegate.addTouchDelegate((View) ((SnackbarContentLayout) eVar.e).e, SeslTouchTargetDelegate.ExtraInsets.of(measuredHeight, measuredHeight, measuredHeight, measuredHeight));
        ((SnackbarContentLayout) eVar.e).f1496i.setTouchDelegate(seslTouchTargetDelegate);
    }
}
