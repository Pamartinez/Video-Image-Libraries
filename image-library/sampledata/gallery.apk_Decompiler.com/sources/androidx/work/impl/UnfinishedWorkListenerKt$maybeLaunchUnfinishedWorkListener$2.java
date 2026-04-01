package androidx.work.impl;

import Ae.c;
import L2.a;
import android.content.Context;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.utils.PackageManagerHelper;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "hasUnfinishedWork", "Lme/x;", "<anonymous>", "(Z)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2", f = "UnfinishedWorkListener.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2 extends i implements c {
    final /* synthetic */ Context $appContext;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2(Context context, C1227c cVar) {
        super(2, cVar);
        this.$appContext = context;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2 unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2 = new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2(this.$appContext, cVar);
        unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2.Z$0 = ((Boolean) obj).booleanValue();
        return unfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Boolean) obj).booleanValue(), (C1227c) obj2);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            PackageManagerHelper.setComponentEnabled(this.$appContext, RescheduleReceiver.class, this.Z$0);
            return x.f4917a;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(boolean z, C1227c cVar) {
        return ((UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2) create(Boolean.valueOf(z), cVar)).invokeSuspend(x.f4917a);
    }
}
