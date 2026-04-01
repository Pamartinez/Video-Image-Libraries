package q2;

import android.view.ViewTreeObserver;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import g6.f;
import ge.s1;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onPreDraw() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                u uVar = (u) obj;
                ArrayList arrayList = new ArrayList();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                uVar.n.forEach(new f(7, new q(arrayList, linkedHashMap)));
                if (!arrayList.isEmpty() && !uVar.f1892C) {
                    uVar.f1892C = true;
                    uVar.B.postDelayed(new s1(uVar, arrayList), 10);
                    LogTagHelperKt.debug(uVar, "OnPreDrawListener invalidateRect=" + linkedHashMap);
                    linkedHashMap.forEach(new f(8, new o1.f(1, uVar)));
                }
                return true;
            default:
                return ((AGSLBlurEffect) obj).lambda$safeAttachPreDrawListener$1();
        }
    }
}
