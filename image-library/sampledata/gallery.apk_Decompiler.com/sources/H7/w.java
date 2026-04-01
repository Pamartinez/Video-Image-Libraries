package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.PageScroll3dEffect;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2356a;
    public final /* synthetic */ PageScroll3dEffect b;

    public /* synthetic */ w(PageScroll3dEffect pageScroll3dEffect, int i2) {
        this.f2356a = i2;
        this.b = pageScroll3dEffect;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2356a;
        PageScroll3dEffect pageScroll3dEffect = this.b;
        switch (i2) {
            case 0:
                pageScroll3dEffect.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                pageScroll3dEffect.onPageScrolled(objArr);
                return;
            default:
                pageScroll3dEffect.onPageScrollDone(objArr);
                return;
        }
    }
}
