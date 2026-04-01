package androidx.window.java.layout;

import Ae.c;
import L2.a;
import Vf.A;
import Yf.g;
import androidx.core.util.Consumer;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 6, 0})
@C1273e(c = "androidx.window.java.layout.WindowInfoTrackerCallbackAdapter$addListener$1$1", f = "WindowInfoTrackerCallbackAdapter.kt", l = {96}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WindowInfoTrackerCallbackAdapter$addListener$1$1 extends i implements c {
    final /* synthetic */ Consumer<T> $consumer;
    final /* synthetic */ g $flow;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WindowInfoTrackerCallbackAdapter$addListener$1$1(g gVar, Consumer<T> consumer, C1227c cVar) {
        super(2, cVar);
        this.$flow = gVar;
        this.$consumer = consumer;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WindowInfoTrackerCallbackAdapter$addListener$1$1(this.$flow, this.$consumer, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WindowInfoTrackerCallbackAdapter$addListener$1$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            g gVar = this.$flow;
            WindowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1 windowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1 = new WindowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1(this.$consumer);
            this.label = 1;
            if (gVar.collect(windowInfoTrackerCallbackAdapter$addListener$1$1$invokeSuspend$$inlined$collect$1, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return x.f4917a;
    }
}
