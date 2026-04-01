package c2;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends ValueAnimator {
    public WeakReference d;
    public float[] e;
    public ArrayList f;
    public ArrayList g;

    /* JADX WARNING: type inference failed for: r0v0, types: [c2.p, android.animation.ValueAnimator] */
    public static p b(float... fArr) {
        ? valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(fArr);
        valueAnimator.e = fArr;
        valueAnimator.f = new ArrayList();
        valueAnimator.g = new ArrayList();
        return valueAnimator;
    }

    /* renamed from: a */
    public final p clone() {
        p b = b(this.e);
        ArrayList arrayList = this.f;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b.addUpdateListener((ValueAnimator.AnimatorUpdateListener) it.next());
            }
        }
        ArrayList arrayList2 = this.g;
        if (arrayList2 != null) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                b.addListener((Animator.AnimatorListener) it2.next());
            }
        }
        return b;
    }

    public final void addListener(Animator.AnimatorListener animatorListener) {
        super.addListener(animatorListener);
        this.g.add(animatorListener);
    }

    public final void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        super.addUpdateListener(animatorUpdateListener);
        this.f.add(animatorUpdateListener);
    }

    public final void setTarget(Object obj) {
        this.d = new WeakReference((View) obj);
        super.setTarget(obj);
    }
}
