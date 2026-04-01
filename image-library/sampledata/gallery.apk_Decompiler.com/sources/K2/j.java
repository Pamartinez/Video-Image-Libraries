package k2;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends MenuPopupHelper {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ m f1831a;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public j(k2.m r7, android.content.Context r8, androidx.appcompat.view.menu.MenuBuilder r9, android.view.View r10) {
        /*
            r6 = this;
            r6.f1831a = r7
            int r5 = androidx.appcompat.R$attr.actionOverflowBottomMenuStyle
            r4 = 1
            r0 = r6
            r1 = r8
            r2 = r9
            r3 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            r6 = 8388613(0x800005, float:1.175495E-38)
            r0.setGravity(r6)
            k2.k r6 = r7.f1834j
            r0.setPresenterCallback(r6)
            r0.setAnchorView(r3)
            r6 = 0
            r0.seslSetOverlapAnchor(r6)
            r6 = 1
            r0.seslForceShowUpper(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: k2.j.<init>(k2.m, android.content.Context, androidx.appcompat.view.menu.MenuBuilder, android.view.View):void");
    }

    public final void onDismiss() {
        m mVar = this.f1831a;
        MenuBuilder menuBuilder = mVar.d;
        if (menuBuilder != null) {
            menuBuilder.close();
        }
        mVar.k = null;
        super.onDismiss();
    }
}
