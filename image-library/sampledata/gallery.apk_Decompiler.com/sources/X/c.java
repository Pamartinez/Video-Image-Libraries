package x;

import android.view.View;
import androidx.core.util.Consumer;
import androidx.core.view.SeslTouchTargetDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f2025a;
    public final /* synthetic */ SeslTouchTargetDelegate.ExtraInsets b;

    public /* synthetic */ c(View view, SeslTouchTargetDelegate.ExtraInsets extraInsets) {
        this.f2025a = view;
        this.b = extraInsets;
    }

    public final void accept(Object obj) {
        ((SeslTouchTargetDelegate) obj).addTouchDelegate(this.f2025a, this.b);
    }
}
