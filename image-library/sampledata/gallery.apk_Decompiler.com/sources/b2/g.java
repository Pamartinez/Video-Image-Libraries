package B2;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends C0340g {

    /* renamed from: A  reason: collision with root package name */
    public static final /* synthetic */ int f46A = 0;
    public f z;

    public final void e(Canvas canvas) {
        if (this.z.q.isEmpty()) {
            super.e(canvas);
            return;
        }
        canvas.save();
        canvas.clipOutRect(this.z.q);
        super.e(canvas);
        canvas.restore();
    }

    public final Drawable mutate() {
        this.z = new f(this.z);
        return this;
    }

    public final void p(float f, float f5, float f8, float f10) {
        RectF rectF = this.z.q;
        if (f != rectF.left || f5 != rectF.top || f8 != rectF.right || f10 != rectF.bottom) {
            rectF.set(f, f5, f8, f10);
            invalidateSelf();
        }
    }
}
