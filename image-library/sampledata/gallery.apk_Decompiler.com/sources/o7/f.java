package o7;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.KeyboardMouseDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnGenericMotionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ KeyboardMouseDelegate f2690a;

    public /* synthetic */ f(KeyboardMouseDelegate keyboardMouseDelegate) {
        this.f2690a = keyboardMouseDelegate;
    }

    public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
        return this.f2690a.onGenericMotion(view, motionEvent);
    }
}
