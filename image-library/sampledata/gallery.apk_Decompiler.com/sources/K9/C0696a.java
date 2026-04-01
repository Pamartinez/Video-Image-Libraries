package k9;

import android.view.View;
import androidx.core.util.Consumer;
import androidx.core.view.SeslTouchTargetDelegate;
import androidx.window.layout.WindowLayoutInfo;
import com.samsung.android.gallery.module.foldable.FoldStateManager;

/* renamed from: k9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0696a implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3278a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0696a(int i2, Object obj) {
        this.f3278a = i2;
        this.b = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.f3278a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                ((FoldStateManager) obj2).onLayoutStateChanged((WindowLayoutInfo) obj);
                return;
            default:
                ((View) obj2).setTouchDelegate((SeslTouchTargetDelegate) obj);
                return;
        }
    }
}
