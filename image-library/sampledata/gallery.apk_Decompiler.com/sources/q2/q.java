package q2;

import Ae.c;
import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends k implements c {
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ LinkedHashMap e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q(ArrayList arrayList, LinkedHashMap linkedHashMap) {
        super(2);
        this.d = arrayList;
        this.e = linkedHashMap;
    }

    public final Object invoke(Object obj, Object obj2) {
        View view = (View) obj;
        Rect rect = (Rect) obj2;
        j.e(view, "view");
        j.e(rect, "prePos");
        Rect rect2 = new Rect();
        if (view.getGlobalVisibleRect(rect2) && !rect.equals(rect2)) {
            this.d.add(view);
            this.e.put(view, rect2);
        }
        return x.f4917a;
    }
}
