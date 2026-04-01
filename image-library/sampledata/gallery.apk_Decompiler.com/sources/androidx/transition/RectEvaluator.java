package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    public Rect evaluate(float f, Rect rect, Rect rect2) {
        int i2 = rect.left;
        int i7 = i2 + ((int) (((float) (rect2.left - i2)) * f));
        int i8 = rect.top;
        int i10 = i8 + ((int) (((float) (rect2.top - i8)) * f));
        int i11 = rect.right;
        int i12 = i11 + ((int) (((float) (rect2.right - i11)) * f));
        int i13 = rect.bottom;
        int i14 = i13 + ((int) (((float) (rect2.bottom - i13)) * f));
        Rect rect3 = this.mRect;
        if (rect3 == null) {
            return new Rect(i7, i10, i12, i14);
        }
        rect3.set(i7, i10, i12, i14);
        return this.mRect;
    }
}
