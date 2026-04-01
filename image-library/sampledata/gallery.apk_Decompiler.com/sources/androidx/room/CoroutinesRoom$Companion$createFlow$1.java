package androidx.room;

import Ae.c;
import He.F;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0886x;
import Vf.D;
import Xf.e;
import Yf.h;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "LYf/h;", "Lme/x;", "<anonymous>", "(LYf/h;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", f = "CoroutinesRoom.kt", l = {111}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CoroutinesRoom$Companion$createFlow$1 extends i implements c {
    final /* synthetic */ Callable<R> $callable;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 8, 0})
    @C1273e(c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", f = "CoroutinesRoom.kt", l = {137}, m = "invokeSuspend")
    /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass1 extends i implements c {
        private /* synthetic */ Object L$0;
        int label;

        public final C1227c create(Object obj, C1227c cVar) {
            AnonymousClass1 r0 = new AnonymousClass1(z, roomDatabase, hVar, strArr, callable, cVar);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass1) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            C0886x xVar;
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            int i2 = this.label;
            x xVar2 = x.f4917a;
            if (i2 == 0) {
                a.A(obj);
                A a7 = (A) this.L$0;
                final e c5 = F.c(-1, (Xf.a) null, 6);
                final CoroutinesRoom$Companion$createFlow$1$1$observer$1 coroutinesRoom$Companion$createFlow$1$1$observer$1 = new CoroutinesRoom$Companion$createFlow$1$1$observer$1(strArr, c5);
                c5.e(xVar2);
                if (a7.getCoroutineContext().get(TransactionElement.Key) == null) {
                    if (z) {
                        xVar = CoroutinesRoomKt.getTransactionDispatcher(roomDatabase);
                    } else {
                        xVar = CoroutinesRoomKt.getQueryDispatcher(roomDatabase);
                    }
                    final e c6 = F.c(0, (Xf.a) null, 7);
                    final RoomDatabase roomDatabase = roomDatabase;
                    final Callable<R> callable = callable;
                    D.n(a7, xVar, (C) null, new c((C1227c) null) {
                        Object L$0;
                        int label;

                        public final C1227c create(Object obj, C1227c cVar) {
                            return 

                            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                            public CoroutinesRoom$Companion$createFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Callable<R> callable, C1227c cVar) {
                                super(2, cVar);
                                this.$inTransaction = z;
                                this.$db = roomDatabase;
                                this.$tableNames = strArr;
                                this.$callable = callable;
                            }

                            public final C1227c create(Object obj, C1227c cVar) {
                                CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, cVar);
                                coroutinesRoom$Companion$createFlow$1.L$0 = obj;
                                return coroutinesRoom$Companion$createFlow$1;
                            }

                            public final Object invoke(h hVar, C1227c cVar) {
                                return ((CoroutinesRoom$Companion$createFlow$1) create(hVar, cVar)).invokeSuspend(x.f4917a);
                            }

                            public final Object invokeSuspend(Object obj) {
                                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                                int i2 = this.label;
                                if (i2 == 0) {
                                    a.A(obj);
                                    final h hVar = (h) this.L$0;
                                    final boolean z = this.$inTransaction;
                                    final RoomDatabase roomDatabase = this.$db;
                                    final String[] strArr = this.$tableNames;
                                    final Callable<R> callable = this.$callable;
                                    AnonymousClass1 r32 = new AnonymousClass1((C1227c) null);
                                    this.label = 1;
                                    if (D.d(r32, this) == aVar) {
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
