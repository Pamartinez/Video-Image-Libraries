package com.samsung.android.sdk.moneta.basicdomain.service;

import L1.d;
import Vf.C0875l;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0010\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\u0011\u001a\u00020\u00102\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0014\u001a\u00020\u0013H@¢\u0006\u0004\b\u0015\u0010\u0016J&\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0013H@¢\u0006\u0004\b\u0015\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendServiceImpl;", "Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/content/ServiceConnection;", "bindService", "(Lqe/c;)Ljava/lang/Object;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "Lqe/c;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "continuation", "com/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendServiceImpl$createPersonListResponse$1", "createPersonListResponse", "(Lqe/c;)Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendServiceImpl$createPersonListResponse$1;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "getFamilyCandidates", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "relationship", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "basicDomainService", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonRecommendServiceImpl implements PersonRecommendService {
    private static final String ACTION_BIND_BASIC_DOMAIN_SERVICE = "com.samsung.android.moneta.service.intent.action.BIND_BASIC_DOMAIN_SERVICE";
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "BasicDomain-BasicDomainServiceImpl";
    /* access modifiers changed from: private */
    public IBasicDomainService basicDomainService;
    private final Context context;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/PersonRecommendServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_BIND_BASIC_DOMAIN_SERVICE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public PersonRecommendServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public final Object bindService(C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] in");
        C0875l lVar = new C0875l(1, d.m(cVar));
        lVar.r();
        boolean bindService = getContext().bindService(getIntent(), new PersonRecommendServiceImpl$bindService$2$connection$1(this, lVar), 1);
        if (!bindService) {
            lVar.resumeWith((Object) null);
        }
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] result : " + bindService);
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    /* access modifiers changed from: private */
    public final PersonRecommendServiceImpl$createPersonListResponse$1 createPersonListResponse(C1227c cVar) {
        return new PersonRecommendServiceImpl$createPersonListResponse$1(cVar);
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.PersonType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006c, code lost:
        if (r10 == r1) goto L_0x009c;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getFamilyCandidates(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r8, com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9, qe.C1227c r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$getFamilyCandidates$2
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$getFamilyCandidates$2 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$getFamilyCandidates$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$getFamilyCandidates$2 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$getFamilyCandidates$2
            r0.<init>(r7, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r5) goto L_0x0044
            if (r2 != r4) goto L_0x003c
            java.lang.Object r7 = r0.L$3
            android.content.ServiceConnection r7 = (android.content.ServiceConnection) r7
            java.lang.Object r8 = r0.L$2
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r8 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r8
            java.lang.Object r8 = r0.L$1
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r8 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r8
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl r8 = (com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl) r8
            L2.a.A(r10)
            goto L_0x00a1
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            java.lang.Object r7 = r0.L$2
            r9 = r7
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r9
            java.lang.Object r7 = r0.L$1
            r8 = r7
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r8 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r8
            java.lang.Object r7 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl r7 = (com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl) r7
            L2.a.A(r10)
            goto L_0x006f
        L_0x0056:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "[getFamilyCandidates] in"
            r10.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r5
            java.lang.Object r10 = r7.bindService(r0)
            if (r10 != r1) goto L_0x006f
            goto L_0x009c
        L_0x006f:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00aa
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r7.basicDomainService
            if (r0 == 0) goto L_0x0096
            com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl$createPersonListResponse$1 r3 = r7.createPersonListResponse(r2)
            r0.getFamilyCandidates(r8, r9, r3)
        L_0x0096:
            java.lang.Object r8 = r2.q()
            if (r8 != r1) goto L_0x009d
        L_0x009c:
            return r1
        L_0x009d:
            r6 = r8
            r8 = r7
            r7 = r10
            r10 = r6
        L_0x00a1:
            r9 = r10
            java.util.List r9 = (java.util.List) r9
            android.content.Context r8 = r8.context
            r8.unbindService(r7)
            return r10
        L_0x00aa:
            com.samsung.android.sdk.moneta.common.Logger r7 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = "[getFamilyCandidates] bind service fail"
            java.lang.String r9 = "bind service fail"
            java.lang.IllegalStateException r7 = c0.C0086a.e(r7, r3, r8, r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl.getFamilyCandidates(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip, com.samsung.android.sdk.moneta.basicdomain.entity.PersonType, qe.c):java.lang.Object");
    }

    public /* synthetic */ Object getFamilyCandidates(PersonType personType, C1227c cVar) {
        return C1202t.d;
    }
}
