package androidx.core.view;

import Ae.c;
import L2.a;
import Sf.k;
import Sf.l;
import Sf.m;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.h;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"LSf/m;", "Landroid/view/View;", "Lme/x;", "<anonymous>", "(LSf/m;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.core.view.ViewKt$allViews$1", f = "View.kt", l = {410, 412}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewKt$allViews$1 extends h implements c {
    final /* synthetic */ View $this_allViews;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewKt$allViews$1(View view, C1227c cVar) {
        super(2, cVar);
        this.$this_allViews = view;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        ViewKt$allViews$1 viewKt$allViews$1 = new ViewKt$allViews$1(this.$this_allViews, cVar);
        viewKt$allViews$1.L$0 = obj;
        return viewKt$allViews$1;
    }

    public final Object invoke(m mVar, C1227c cVar) {
        return ((ViewKt$allViews$1) create(mVar, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object obj3 = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 != 0) {
            Object obj4 = x.f4917a;
            if (i2 == 1) {
                m mVar = (m) this.L$0;
                a.A(obj);
                View view = this.$this_allViews;
                if (view instanceof ViewGroup) {
                    k descendants = ViewGroupKt.getDescendants((ViewGroup) view);
                    this.L$0 = null;
                    this.label = 2;
                    mVar.getClass();
                    Iterator it = descendants.iterator();
                    l lVar = (l) mVar;
                    if (!it.hasNext()) {
                        obj2 = obj4;
                    } else {
                        lVar.f = it;
                        lVar.d = 2;
                        lVar.g = this;
                        obj2 = obj3;
                    }
                    if (obj2 != obj3) {
                        obj2 = obj4;
                    }
                    if (obj2 == obj3) {
                        return obj3;
                    }
                }
                return obj4;
            } else if (i2 == 2) {
                a.A(obj);
                return obj4;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } else {
            a.A(obj);
            m mVar2 = (m) this.L$0;
            View view2 = this.$this_allViews;
            this.L$0 = mVar2;
            this.label = 1;
            l lVar2 = (l) mVar2;
            lVar2.e = view2;
            lVar2.d = 3;
            lVar2.g = this;
        }
        return obj3;
    }
}
