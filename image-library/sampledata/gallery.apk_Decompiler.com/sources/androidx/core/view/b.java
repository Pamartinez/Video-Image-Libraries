package androidx.core.view;

import android.graphics.Rect;
import androidx.core.view.SeslTouchDelegateFactory;
import androidx.core.view.SeslTouchTargetDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SeslTouchDelegateFactory.Strategy {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f985a;
    public final /* synthetic */ Rect b;

    public /* synthetic */ b(Rect rect, int i2) {
        this.f985a = i2;
        this.b = rect;
    }

    public final SeslTouchTargetDelegate.ExtraInsets getExtraInsets(Rect rect, Rect rect2, Rect rect3) {
        int i2 = this.f985a;
        Rect rect4 = this.b;
        switch (i2) {
            case 0:
                return SeslTouchTargetDelegate.ExtraInsets.of(rect2.left - rect.right, rect2.top - rect4.top, Math.max(0, rect3.left - rect2.right) / 2, rect4.bottom - rect2.bottom);
            default:
                return SeslTouchTargetDelegate.ExtraInsets.of(rect2.left - rect4.left, rect2.top - rect.bottom, rect4.right - rect2.right, Math.max(0, rect3.top - rect2.bottom) / 2);
        }
    }
}
