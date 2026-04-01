package o1;

import Ae.c;
import B1.b;
import Tf.m;
import android.graphics.Rect;
import android.view.View;
import java.util.HashMap;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;
import q2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends k implements c {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i2, Object obj) {
        super(2);
        this.d = i2;
        this.e = obj;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, h1.a] */
    public void a(Class cls, Class cls2) {
        j.e(cls, "clazz");
        j.e(cls2, "mixin");
        m mVar = ((V0.j) ((b) this.e).e).g;
        if (((HashMap) mVar.e) == null) {
            mVar.e = new HashMap();
        }
        ? obj = new Object();
        obj.e = cls;
        String name = cls.getName();
        obj.d = name;
        obj.f = name.hashCode();
        ((HashMap) mVar.e).put(obj, cls2);
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                a((Class) obj, (Class) obj2);
                return x.f4917a;
            default:
                View view = (View) obj;
                Rect rect = (Rect) obj2;
                j.e(view, "view");
                j.e(rect, "rect");
                LinkedHashMap linkedHashMap = ((u) this.e).n;
                if (linkedHashMap.containsKey(view)) {
                    linkedHashMap.put(view, rect);
                }
                return x.f4917a;
        }
    }
}
