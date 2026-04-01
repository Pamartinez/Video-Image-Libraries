package com.samsung.android.app.sdk.deepsky.textextraction.util;

import Ae.b;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import Vf.Y;
import Vf.Z;
import cg.n;
import eg.f;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SingleThreadCoroutineSwitcher;", "", "<init>", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SingleThreadCoroutineSwitcher$Chain;", "Lme/x;", "newChain", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SingleThreadCoroutineSwitcher$Chain;", "LVf/Y;", "threadContext", "LVf/Y;", "Chain", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleThreadCoroutineSwitcher {
    public static final SingleThreadCoroutineSwitcher INSTANCE = new SingleThreadCoroutineSwitcher();
    /* access modifiers changed from: private */
    public static final Y threadContext;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B%\u0012\u001c\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J4\u0010\f\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0001\u0010\b2\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H@¢\u0006\u0004\b\f\u0010\rJ/\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0000\"\u0004\b\u0001\u0010\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0000\"\u0004\b\u0001\u0010\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u000fJ=\u0010\u0016\u001a\u00020\u00152\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00032\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0003¢\u0006\u0004\b\u0016\u0010\u0017R*\u0010\u0005\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SingleThreadCoroutineSwitcher$Chain;", "Param", "", "Lkotlin/Function1;", "Lqe/c;", "priorTask", "<init>", "(LAe/b;)V", "Return", "Lqe/h;", "context", "task", "doTask", "(Lqe/h;LAe/b;Lqe/c;)Ljava/lang/Object;", "onMain", "(LAe/b;)Lcom/samsung/android/app/sdk/deepsky/textextraction/util/SingleThreadCoroutineSwitcher$Chain;", "onBackground", "Lme/x;", "onSuccess", "", "onError", "LVf/e0;", "start", "(LAe/b;LAe/b;)LVf/e0;", "LAe/b;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Chain<Param> {
        /* access modifiers changed from: private */
        public final b priorTask;

        public Chain(b bVar) {
            j.e(bVar, "priorTask");
            this.priorTask = bVar;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: kotlin.jvm.internal.u} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: Ae.b} */
        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0075, code lost:
            if (Vf.D.w(r7, r2, r0) != r1) goto L_0x0078;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final <Return> java.lang.Object doTask(qe.C1232h r7, Ae.b r8, qe.C1227c r9) {
            /*
                r6 = this;
                boolean r0 = r9 instanceof com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$1
                if (r0 == 0) goto L_0x0013
                r0 = r9
                com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$1 r0 = (com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.label = r1
                goto L_0x0018
            L_0x0013:
                com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$1 r0 = new com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$1
                r0.<init>(r6, r9)
            L_0x0018:
                java.lang.Object r9 = r0.result
                re.a r1 = re.C1245a.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0047
                if (r2 == r4) goto L_0x0036
                if (r2 != r3) goto L_0x002e
                java.lang.Object r6 = r0.L$0
                kotlin.jvm.internal.u r6 = (kotlin.jvm.internal.u) r6
                L2.a.A(r9)
                goto L_0x0078
            L_0x002e:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0036:
                java.lang.Object r6 = r0.L$2
                kotlin.jvm.internal.u r6 = (kotlin.jvm.internal.u) r6
                java.lang.Object r7 = r0.L$1
                r8 = r7
                Ae.b r8 = (Ae.b) r8
                java.lang.Object r7 = r0.L$0
                qe.h r7 = (qe.C1232h) r7
                L2.a.A(r9)
                goto L_0x0063
            L_0x0047:
                L2.a.A(r9)
                kotlin.jvm.internal.u r9 = new kotlin.jvm.internal.u
                r9.<init>()
                Ae.b r6 = r6.priorTask
                r0.L$0 = r7
                r0.L$1 = r8
                r0.L$2 = r9
                r0.label = r4
                java.lang.Object r6 = r6.invoke(r0)
                if (r6 != r1) goto L_0x0060
                goto L_0x0077
            L_0x0060:
                r5 = r9
                r9 = r6
                r6 = r5
            L_0x0063:
                com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$2 r2 = new com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher$Chain$doTask$2
                r4 = 0
                r2.<init>(r6, r8, r9, r4)
                r0.L$0 = r6
                r0.L$1 = r4
                r0.L$2 = r4
                r0.label = r3
                java.lang.Object r7 = Vf.D.w(r7, r2, r0)
                if (r7 != r1) goto L_0x0078
            L_0x0077:
                return r1
            L_0x0078:
                java.lang.Object r6 = r6.d
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher.Chain.doTask(qe.h, Ae.b, qe.c):java.lang.Object");
        }

        public static /* synthetic */ C0867e0 start$default(Chain chain, b bVar, b bVar2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                bVar = null;
            }
            if ((i2 & 2) != 0) {
                bVar2 = null;
            }
            return chain.start(bVar, bVar2);
        }

        public final <Return> Chain<Return> onBackground(b bVar) {
            j.e(bVar, "task");
            return new Chain<>(new SingleThreadCoroutineSwitcher$Chain$onBackground$1(this, bVar, (C1227c) null));
        }

        public final <Return> Chain<Return> onMain(b bVar) {
            j.e(bVar, "task");
            return new Chain<>(new SingleThreadCoroutineSwitcher$Chain$onMain$1(this, bVar, (C1227c) null));
        }

        public final C0867e0 start(b bVar, b bVar2) {
            f fVar = M.f3843a;
            return D.n(D.a(n.f4030a), (C0886x) null, (C) null, new SingleThreadCoroutineSwitcher$Chain$start$1(this, bVar, bVar2, (C1227c) null), 3);
        }
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        j.d(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        threadContext = new Z(newSingleThreadExecutor);
    }

    private SingleThreadCoroutineSwitcher() {
    }

    public final Chain<x> newChain() {
        return new Chain<>(new SingleThreadCoroutineSwitcher$newChain$1((C1227c) null));
    }
}
