package androidx.lifecycle;

import Ae.c;
import Vf.A;
import Vf.C;
import Vf.C0867e0;
import Vf.C0873j;
import Vf.C0886x;
import Vf.D;
import androidx.lifecycle.Lifecycle;
import fg.a;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/lifecycle/LifecycleOwner;", "<anonymous parameter 0>", "Landroidx/lifecycle/Lifecycle$Event;", "event", "Lme/x;", "onStateChanged", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1 implements LifecycleEventObserver {
    final /* synthetic */ A $$this$coroutineScope;
    final /* synthetic */ c $block;
    final /* synthetic */ Lifecycle.Event $cancelWorkEvent;
    final /* synthetic */ C0873j $cont;
    final /* synthetic */ u $launchedJob;
    final /* synthetic */ a $mutex;
    final /* synthetic */ Lifecycle.Event $startWorkEvent;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 8, 0})
    @C1273e(c = "androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1", f = "RepeatOnLifecycle.kt", l = {171, 110}, m = "invokeSuspend")
    /* renamed from: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass1 extends i implements c {
        Object L$0;
        Object L$1;
        int label;

        public final C1227c create(Object obj, C1227c cVar) {
            return new AnonymousClass1(aVar, cVar, cVar);
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass1) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        /* JADX WARNING: type inference failed for: r3v4, types: [fg.a] */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x003f, code lost:
            if (r7.c(r6) == r0) goto L_0x0053;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                re.a r0 = re.C1245a.COROUTINE_SUSPENDED
                int r1 = r6.label
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L_0x002c
                if (r1 == r3) goto L_0x001f
                if (r1 != r2) goto L_0x0017
                java.lang.Object r6 = r6.L$0
                fg.a r6 = (fg.a) r6
                L2.a.A(r7)     // Catch:{ all -> 0x0015 }
                goto L_0x0055
            L_0x0015:
                r7 = move-exception
                goto L_0x0061
            L_0x0017:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x001f:
                java.lang.Object r1 = r6.L$1
                Ae.c r1 = (Ae.c) r1
                java.lang.Object r3 = r6.L$0
                fg.a r3 = (fg.a) r3
                L2.a.A(r7)
                r7 = r3
                goto L_0x0042
            L_0x002c:
                L2.a.A(r7)
                fg.a r7 = r2
                Ae.c r1 = r3
                r6.L$0 = r7
                r6.L$1 = r1
                r6.label = r3
                fg.c r7 = (fg.c) r7
                java.lang.Object r3 = r7.c(r6)
                if (r3 != r0) goto L_0x0042
                goto L_0x0053
            L_0x0042:
                androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1 r3 = new androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$1     // Catch:{ all -> 0x005d }
                r3.<init>(r1, r4)     // Catch:{ all -> 0x005d }
                r6.L$0 = r7     // Catch:{ all -> 0x005d }
                r6.L$1 = r4     // Catch:{ all -> 0x005d }
                r6.label = r2     // Catch:{ all -> 0x005d }
                java.lang.Object r6 = Vf.D.d(r3, r6)     // Catch:{ all -> 0x005d }
                if (r6 != r0) goto L_0x0054
            L_0x0053:
                return r0
            L_0x0054:
                r6 = r7
            L_0x0055:
                fg.c r6 = (fg.c) r6
                r6.d(r4)
                me.x r6 = me.x.f4917a
                return r6
            L_0x005d:
                r6 = move-exception
                r5 = r7
                r7 = r6
                r6 = r5
            L_0x0061:
                fg.c r6 = (fg.c) r6
                r6.d(r4)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.RepeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        j.e(lifecycleOwner, "<anonymous parameter 0>");
        j.e(event, "event");
        if (event == this.$startWorkEvent) {
            u uVar = this.$launchedJob;
            A a7 = this.$$this$coroutineScope;
            final a aVar = this.$mutex;
            final c cVar = this.$block;
            uVar.d = D.n(a7, (C0886x) null, (C) null, new AnonymousClass1((C1227c) null), 3);
            return;
        }
        if (event == this.$cancelWorkEvent) {
            C0867e0 e0Var = (C0867e0) this.$launchedJob.d;
            if (e0Var != null) {
                e0Var.a((CancellationException) null);
            }
            this.$launchedJob.d = null;
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.$cont.resumeWith(x.f4917a);
        }
    }
}
