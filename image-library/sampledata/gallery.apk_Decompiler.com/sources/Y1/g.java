package Y1;

import R1.a;
import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends WindowInsetsAnimationCompat.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final View f952a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f953c;
    public final int[] d = new int[2];

    public g(View view) {
        super(0);
        this.f952a = view;
    }

    public final void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        if ((windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
            this.f952a.setTranslationY(0.0f);
        }
    }

    public final void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        if ((windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
            View view = this.f952a;
            int[] iArr = this.d;
            view.getLocationOnScreen(iArr);
            this.b = iArr[1];
        }
    }

    public final WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WindowInsetsAnimationCompat windowInsetsAnimationCompat = (WindowInsetsAnimationCompat) it.next();
            if ((windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
                int i2 = this.f953c;
                this.f952a.setTranslationY((float) a.c(windowInsetsAnimationCompat.getInterpolatedFraction(), i2, 0));
                break;
            }
        }
        return windowInsetsCompat;
    }

    public final WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        if ((windowInsetsAnimationCompat.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
            View view = this.f952a;
            int[] iArr = this.d;
            view.getLocationOnScreen(iArr);
            int i2 = this.b - iArr[1];
            this.f953c = i2;
            view.setTranslationY((float) i2);
        }
        return boundsCompat;
    }
}
