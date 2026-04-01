package B7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover.FlipCoverDecorLayoutHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2268a;
    public final /* synthetic */ FlipCoverDecorLayoutHandler b;

    public /* synthetic */ c(FlipCoverDecorLayoutHandler flipCoverDecorLayoutHandler, int i2) {
        this.f2268a = i2;
        this.b = flipCoverDecorLayoutHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2268a;
        FlipCoverDecorLayoutHandler flipCoverDecorLayoutHandler = this.b;
        switch (i2) {
            case 0:
                flipCoverDecorLayoutHandler.initView(objArr);
                return;
            default:
                flipCoverDecorLayoutHandler.lambda$addActionInvokeListener$0(objArr);
                return;
        }
    }
}
