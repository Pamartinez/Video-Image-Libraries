package androidx.work;

import Ae.c;
import L2.a;
import Vf.A;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.ListenableFutureKt$launchFuture$1$2", f = "ListenableFuture.kt", l = {42}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ListenableFutureKt$launchFuture$1$2 extends i implements c {
    final /* synthetic */ c $block;
    final /* synthetic */ CallbackToFutureAdapter.Completer<T> $completer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ListenableFutureKt$launchFuture$1$2(c cVar, CallbackToFutureAdapter.Completer<T> completer, C1227c cVar2) {
        super(2, cVar2);
        this.$block = cVar;
        this.$completer = completer;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        ListenableFutureKt$launchFuture$1$2 listenableFutureKt$launchFuture$1$2 = new ListenableFutureKt$launchFuture$1$2(this.$block, this.$completer, cVar);
        listenableFutureKt$launchFuture$1$2.L$0 = obj;
        return listenableFutureKt$launchFuture$1$2;
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((ListenableFutureKt$launchFuture$1$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            A a7 = (A) this.L$0;
            c cVar = this.$block;
            this.label = 1;
            obj = cVar.invoke(a7, this);
            if (obj == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            try {
                a.A(obj);
            } catch (CancellationException unused) {
                this.$completer.setCancelled();
            } catch (Throwable th) {
                this.$completer.setException(th);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$completer.set(obj);
        return x.f4917a;
    }
}
