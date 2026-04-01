package com.samsung.android.sdk.moneta.basicdomain.service;

import L1.d;
import Vf.C0875l;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ4\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH@¢\u0006\u0004\b\u0013\u0010\u0014J<\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH@¢\u0006\u0004\b\u0013\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/FeedbackServiceImpl;", "Lcom/samsung/android/sdk/moneta/basicdomain/service/FeedbackService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/content/ServiceConnection;", "bindService", "(Lqe/c;)Ljava/lang/Object;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "suggestedRelationShip", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "suggestedList", "selectedList", "Lme/x;", "sendRelationShipFeedback", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Ljava/util/List;Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Ljava/util/List;Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "basicDomainService", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FeedbackServiceImpl implements FeedbackService {
    private static final String ACTION_BIND_BASIC_DOMAIN_SERVICE = "com.samsung.android.moneta.service.intent.action.BIND_BASIC_DOMAIN_SERVICE";
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "BasicDomain-BasicDomainServiceImpl";
    /* access modifiers changed from: private */
    public IBasicDomainService basicDomainService;
    private final Context context;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/FeedbackServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_BIND_BASIC_DOMAIN_SERVICE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public FeedbackServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public final Object bindService(C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] in");
        C0875l lVar = new C0875l(1, d.m(cVar));
        lVar.r();
        boolean bindService = getContext().bindService(getIntent(), new FeedbackServiceImpl$bindService$2$connection$1(this, lVar), 1);
        if (!bindService) {
            lVar.resumeWith((Object) null);
        }
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] result : " + bindService);
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    /* access modifiers changed from: private */
    public final Intent getIntent() {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getIntent] in");
        Intent intent = new Intent(ACTION_BIND_BASIC_DOMAIN_SERVICE);
        intent.setPackage(Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        return intent;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.PersonType} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0083, code lost:
        if (r11 == r1) goto L_0x00fe;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object sendRelationShipFeedback(com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r7, com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r8, java.util.List r9, java.util.List r10, qe.C1227c r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$4
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$4 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$4) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$4 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$4
            r0.<init>(r6, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 == r5) goto L_0x004d
            if (r2 != r4) goto L_0x0045
            java.lang.Object r6 = r0.L$5
            android.content.ServiceConnection r6 = (android.content.ServiceConnection) r6
            java.lang.Object r7 = r0.L$4
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$2
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r7
            java.lang.Object r7 = r0.L$1
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r7
            java.lang.Object r7 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl r7 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl) r7
            L2.a.A(r11)
            goto L_0x0101
        L_0x0045:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x004d:
            java.lang.Object r6 = r0.L$4
            r10 = r6
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r6 = r0.L$3
            r9 = r6
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r6 = r0.L$2
            r8 = r6
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r8 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r7
            java.lang.Object r6 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl r6 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl) r6
            L2.a.A(r11)
            goto L_0x0087
        L_0x0069:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "[sendRelationShipFeedback] in"
            r11.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.label = r5
            java.lang.Object r11 = r6.bindService(r0)
            if (r11 != r1) goto L_0x0087
            goto L_0x00fe
        L_0x0087:
            android.content.ServiceConnection r11 = (android.content.ServiceConnection) r11
            if (r11 == 0) goto L_0x0109
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.L$5 = r11
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r6.basicDomainService
            if (r0 == 0) goto L_0x00f6
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r5 = ne.C1196n.w0(r9, r4)
            r3.<init>(r5)
            java.util.Iterator r9 = r9.iterator()
        L_0x00bc:
            boolean r5 = r9.hasNext()
            if (r5 == 0) goto L_0x00d0
            java.lang.Object r5 = r9.next()
            com.samsung.android.sdk.moneta.basicdomain.entity.Person r5 = (com.samsung.android.sdk.moneta.basicdomain.entity.Person) r5
            com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper r5 = com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapperKt.toPersonWrapper(r5)
            r3.add(r5)
            goto L_0x00bc
        L_0x00d0:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r9 = new java.util.ArrayList
            int r4 = ne.C1196n.w0(r10, r4)
            r9.<init>(r4)
            java.util.Iterator r10 = r10.iterator()
        L_0x00df:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto L_0x00f3
            java.lang.Object r4 = r10.next()
            com.samsung.android.sdk.moneta.basicdomain.entity.Person r4 = (com.samsung.android.sdk.moneta.basicdomain.entity.Person) r4
            com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper r4 = com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapperKt.toPersonWrapper(r4)
            r9.add(r4)
            goto L_0x00df
        L_0x00f3:
            r0.sendRelationShipFeedback(r7, r8, r3, r9)
        L_0x00f6:
            java.lang.Object r7 = r2.q()
            re.a r8 = re.C1245a.COROUTINE_SUSPENDED
            if (r7 != r1) goto L_0x00ff
        L_0x00fe:
            return r1
        L_0x00ff:
            r7 = r6
            r6 = r11
        L_0x0101:
            android.content.Context r7 = r7.context
            r7.unbindService(r6)
            me.x r6 = me.x.f4917a
            return r6
        L_0x0109:
            com.samsung.android.sdk.moneta.common.Logger r6 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r7 = "[confirmRelationShipByCandidates] bind service fail"
            java.lang.String r8 = "bind service fail"
            java.lang.IllegalStateException r6 = c0.C0086a.e(r6, r3, r7, r8)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl.sendRelationShipFeedback(com.samsung.android.sdk.moneta.basicdomain.entity.PersonType, com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip, java.util.List, java.util.List, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0078, code lost:
        if (r10 == r1) goto L_0x00f1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object sendRelationShipFeedback(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r7, java.util.List r8, java.util.List r9, qe.C1227c r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$1 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$1 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl$sendRelationShipFeedback$1
            r0.<init>(r6, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0060
            if (r2 == r5) goto L_0x0049
            if (r2 != r4) goto L_0x0041
            java.lang.Object r6 = r0.L$4
            android.content.ServiceConnection r6 = (android.content.ServiceConnection) r6
            java.lang.Object r7 = r0.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$2
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$1
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r7
            java.lang.Object r7 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl r7 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl) r7
            L2.a.A(r10)
            goto L_0x00f4
        L_0x0041:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0049:
            java.lang.Object r6 = r0.L$3
            r9 = r6
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r6 = r0.L$2
            r8 = r6
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r7
            java.lang.Object r6 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl r6 = (com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl) r6
            L2.a.A(r10)
            goto L_0x007c
        L_0x0060:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "[sendRelationShipFeedback] in"
            r10.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.label = r5
            java.lang.Object r10 = r6.bindService(r0)
            if (r10 != r1) goto L_0x007c
            goto L_0x00f1
        L_0x007c:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00fc
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r6.basicDomainService
            if (r0 == 0) goto L_0x00e9
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r5 = ne.C1196n.w0(r8, r4)
            r3.<init>(r5)
            java.util.Iterator r8 = r8.iterator()
        L_0x00af:
            boolean r5 = r8.hasNext()
            if (r5 == 0) goto L_0x00c3
            java.lang.Object r5 = r8.next()
            com.samsung.android.sdk.moneta.basicdomain.entity.Person r5 = (com.samsung.android.sdk.moneta.basicdomain.entity.Person) r5
            com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper r5 = com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapperKt.toPersonWrapper(r5)
            r3.add(r5)
            goto L_0x00af
        L_0x00c3:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r8 = new java.util.ArrayList
            int r4 = ne.C1196n.w0(r9, r4)
            r8.<init>(r4)
            java.util.Iterator r9 = r9.iterator()
        L_0x00d2:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x00e6
            java.lang.Object r4 = r9.next()
            com.samsung.android.sdk.moneta.basicdomain.entity.Person r4 = (com.samsung.android.sdk.moneta.basicdomain.entity.Person) r4
            com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper r4 = com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapperKt.toPersonWrapper(r4)
            r8.add(r4)
            goto L_0x00d2
        L_0x00e6:
            r0.feedbackRelationShipByCandidates(r3, r8, r7)
        L_0x00e9:
            java.lang.Object r7 = r2.q()
            re.a r8 = re.C1245a.COROUTINE_SUSPENDED
            if (r7 != r1) goto L_0x00f2
        L_0x00f1:
            return r1
        L_0x00f2:
            r7 = r6
            r6 = r10
        L_0x00f4:
            android.content.Context r7 = r7.context
            r7.unbindService(r6)
            me.x r6 = me.x.f4917a
            return r6
        L_0x00fc:
            com.samsung.android.sdk.moneta.common.Logger r6 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r7 = "[confirmRelationShipByCandidates] bind service fail"
            java.lang.String r8 = "bind service fail"
            java.lang.IllegalStateException r6 = c0.C0086a.e(r6, r3, r7, r8)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.FeedbackServiceImpl.sendRelationShipFeedback(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip, java.util.List, java.util.List, qe.c):java.lang.Object");
    }
}
