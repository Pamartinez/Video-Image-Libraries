package R1;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final SimpleArrayMap f642a = new SimpleArrayMap();
    public final SimpleArrayMap b = new SimpleArrayMap();

    public static b a(Context context, int i2) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i2);
            if (loadAnimator instanceof AnimatorSet) {
                return b(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return b(arrayList);
        } catch (Exception e) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i2), e);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [R1.c, java.lang.Object] */
    public static b b(ArrayList arrayList) {
        b bVar = new b();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Animator animator = (Animator) arrayList.get(i2);
            if (animator instanceof ObjectAnimator) {
                ObjectAnimator objectAnimator = (ObjectAnimator) animator;
                bVar.b.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
                String propertyName = objectAnimator.getPropertyName();
                long startDelay = objectAnimator.getStartDelay();
                long duration = objectAnimator.getDuration();
                TimeInterpolator interpolator = objectAnimator.getInterpolator();
                if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                    interpolator = a.b;
                } else if (interpolator instanceof AccelerateInterpolator) {
                    interpolator = a.f641c;
                } else if (interpolator instanceof DecelerateInterpolator) {
                    interpolator = a.d;
                }
                ? obj = new Object();
                obj.d = 0;
                obj.e = 1;
                obj.f643a = startDelay;
                obj.b = duration;
                obj.f644c = interpolator;
                obj.d = objectAnimator.getRepeatCount();
                obj.e = objectAnimator.getRepeatMode();
                bVar.f642a.put(propertyName, obj);
                i2++;
            } else {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
        }
        return bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        return this.f642a.equals(((b) obj).f642a);
    }

    public final int hashCode() {
        return this.f642a.hashCode();
    }

    public final String toString() {
        return "\n" + b.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f642a + "}\n";
    }
}
