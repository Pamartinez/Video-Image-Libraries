package z2;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    public final WeakReference d;
    public final WeakReference e;

    public m(r rVar, View view) {
        this.d = new WeakReference(rVar);
        this.e = new WeakReference(view);
    }

    public final void a() {
        WeakReference weakReference = this.e;
        if (weakReference.get() != null) {
            ((View) weakReference.get()).removeOnAttachStateChangeListener(this);
            View view = (View) weakReference.get();
            if (view != null) {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
        weakReference.clear();
        this.d.clear();
    }

    public final void onGlobalLayout() {
        WeakReference weakReference = this.d;
        if (weakReference.get() == null) {
            a();
            return;
        }
        int i2 = q.y;
        ((q) weakReference.get()).getClass();
    }

    public final void onViewAttachedToWindow(View view) {
        if (this.d.get() == null) {
            a();
        } else if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public final void onViewDetachedFromWindow(View view) {
        if (this.d.get() == null) {
            a();
        } else if (view != null) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }
}
