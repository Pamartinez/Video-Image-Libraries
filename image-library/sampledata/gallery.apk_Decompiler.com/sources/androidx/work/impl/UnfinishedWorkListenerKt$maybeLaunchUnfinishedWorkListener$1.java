package androidx.work.impl;

import Ae.e;
import L2.a;
import Vf.D;
import Yf.h;
import androidx.work.Logger;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"LYf/h;", "", "", "throwable", "", "attempt", "<anonymous>", "(LYf/h;Ljava/lang/Throwable;J)Z"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1", f = "UnfinishedWorkListener.kt", l = {59}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1 extends i implements e {
    /* synthetic */ long J$0;
    /* synthetic */ Object L$0;
    int label;

    public UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1(C1227c cVar) {
        super(4, cVar);
    }

    public final Object invoke(h hVar, Throwable th, long j2, C1227c cVar) {
        UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1 unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1 = new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1(cVar);
        unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1.L$0 = th;
        unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1.J$0 = j2;
        return unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1.invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            long j2 = this.J$0;
            Logger.get().error(UnfinishedWorkListenerKt.TAG, "Cannot check for unfinished work", (Throwable) this.L$0);
            long min = Math.min(j2 * ((long) 30000), UnfinishedWorkListenerKt.MAX_DELAY_MS);
            this.label = 1;
            if (D.e(min, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boolean.TRUE;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((h) obj, (Throwable) obj2, ((Number) obj3).longValue(), (C1227c) obj4);
    }
}
