package L7;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsTouchListener;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2391a;
    public final /* synthetic */ DetailsTouchListener b;

    public /* synthetic */ q(DetailsTouchListener detailsTouchListener, int i2) {
        this.f2391a = i2;
        this.b = detailsTouchListener;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2391a;
        DetailsTouchListener detailsTouchListener = this.b;
        switch (i2) {
            case 0:
                detailsTouchListener.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                detailsTouchListener.lambda$addActionInvokeListener$1(objArr);
                return;
            default:
                detailsTouchListener.lambda$addActionInvokeListener$2(objArr);
                return;
        }
    }
}
