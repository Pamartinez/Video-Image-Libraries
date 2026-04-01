package com.samsung.android.sdk.moneta.basicdomain.service;

import L1.d;
import L2.a;
import Vf.C0875l;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.Pet;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapperKt;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.common.SafeJson;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import lg.C1174b;
import me.k;
import ne.C1202t;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\u000f\u0014\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\u000f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0015\u001a\u00020\u00142\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\fH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\rH@¢\u0006\u0004\b\u0017\u0010\bJ\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H@¢\u0006\u0004\b\u0018\u0010\bJ\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001a\u001a\u00020\u0019H@¢\u0006\u0004\b\u0018\u0010\u001bJ&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0019H@¢\u0006\u0004\b\u0018\u0010\u001eJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u0012H@¢\u0006\u0004\b \u0010\bJ\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0012H@¢\u0006\u0004\b!\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl;", "Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/content/ServiceConnection;", "bindService", "(Lqe/c;)Ljava/lang/Object;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "Lqe/c;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/MyProfile;", "continuation", "com/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createMyProfileResponse$1", "createMyProfileResponse", "(Lqe/c;)Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createMyProfileResponse$1;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "com/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createPersonListResponse$1", "createPersonListResponse", "(Lqe/c;)Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createPersonListResponse$1;", "getMyProfile", "getMyFamily", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;", "personType", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "relationShip", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonType;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Pet;", "getMyPets", "getCandidatePets", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "basicDomainService", "Lcom/samsung/android/sdk/moneta/basicdomain/IBasicDomainService;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileServiceImpl implements MyProfileService {
    private static final String ACTION_BIND_BASIC_DOMAIN_SERVICE = "com.samsung.android.moneta.service.intent.action.BIND_BASIC_DOMAIN_SERVICE";
    private static Uri BASIC_DOMAIN_PROVIDER_URI = Uri.parse("content://com.samsung.android.moneta.feature.basicdomain.BasicDomainProvider");
    public static final Companion Companion = new Companion((e) null);
    private static final String GET_CANDIDATE_PETS_METHOD = "getCandidatePets";
    private static final String GET_CANDIDATE_PETS_RESULT_KEY = "candidate_pets";
    private static final String GET_MY_PETS_METHOD = "getMyPets";
    private static final String GET_MY_PETS_RESULT_KEY = "my_pets";
    private static final String TAG = "BasicDomain-BasicDomainServiceImpl";
    /* access modifiers changed from: private */
    public IBasicDomainService basicDomainService;
    private final Context context;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_BIND_BASIC_DOMAIN_SERVICE", "BASIC_DOMAIN_PROVIDER_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "Landroid/net/Uri;", "GET_MY_PETS_METHOD", "GET_CANDIDATE_PETS_METHOD", "GET_MY_PETS_RESULT_KEY", "GET_CANDIDATE_PETS_RESULT_KEY", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MyProfileServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public final Object bindService(C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] in");
        C0875l lVar = new C0875l(1, d.m(cVar));
        lVar.r();
        boolean bindService = getContext().bindService(getIntent(), new MyProfileServiceImpl$bindService$2$connection$1(this, lVar), 1);
        if (!bindService) {
            lVar.resumeWith((Object) null);
        }
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] result : " + bindService);
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    /* access modifiers changed from: private */
    public final MyProfileServiceImpl$createMyProfileResponse$1 createMyProfileResponse(C1227c cVar) {
        return new MyProfileServiceImpl$createMyProfileResponse$1(cVar);
    }

    /* access modifiers changed from: private */
    public final MyProfileServiceImpl$createPersonListResponse$1 createPersonListResponse(C1227c cVar) {
        return new MyProfileServiceImpl$createPersonListResponse$1(cVar);
    }

    /* access modifiers changed from: private */
    public final Intent getIntent() {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getIntent] in");
        Intent intent = new Intent(ACTION_BIND_BASIC_DOMAIN_SERVICE);
        intent.setPackage(Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        return intent;
    }

    public Object getCandidatePets(C1227c cVar) {
        ArrayList<String> arrayList;
        Object obj;
        Pet pet;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getCandidatePets]");
        ArrayList arrayList2 = null;
        Bundle call = this.context.getContentResolver().call(BASIC_DOMAIN_PROVIDER_URI, GET_CANDIDATE_PETS_METHOD, (String) null, (Bundle) null);
        if (call != null) {
            arrayList = call.getStringArrayList(GET_CANDIDATE_PETS_RESULT_KEY);
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (String str : arrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(MyPetWrapper.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                MyPetWrapper myPetWrapper = (MyPetWrapper) obj;
                if (myPetWrapper != null) {
                    pet = MyPetWrapperKt.toPet(myPetWrapper);
                } else {
                    pet = null;
                }
                if (pet != null) {
                    arrayList3.add(pet);
                }
            }
            arrayList2 = arrayList3;
        }
        if (arrayList2 == null) {
            return C1202t.d;
        }
        return arrayList2;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.PersonType} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006d, code lost:
        if (r10 == r1) goto L_0x009d;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getMyFamily(com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$2
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$2 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$2 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$2
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r5) goto L_0x0040
            if (r2 != r4) goto L_0x0038
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r9 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r9
            L2.a.A(r10)
            goto L_0x00a2
        L_0x0038:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0040:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r8 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0070
        L_0x004d:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[getMyFamily] in personType "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0070
            goto L_0x009d
        L_0x0070:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00ab
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r8.basicDomainService
            if (r0 == 0) goto L_0x0097
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r3 = com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip.FAMILY_UNKNOWN
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$createPersonListResponse$1 r4 = r8.createPersonListResponse(r2)
            r0.getMyFamily(r3, r9, r4)
        L_0x0097:
            java.lang.Object r9 = r2.q()
            if (r9 != r1) goto L_0x009e
        L_0x009d:
            return r1
        L_0x009e:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00a2:
            r0 = r10
            java.util.List r0 = (java.util.List) r0
            android.content.Context r9 = r9.context
            r9.unbindService(r8)
            return r10
        L_0x00ab:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[getMyFamily] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r3, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl.getMyFamily(com.samsung.android.sdk.moneta.basicdomain.entity.PersonType, qe.c):java.lang.Object");
    }

    public Object getMyPets(C1227c cVar) {
        ArrayList<String> arrayList;
        Object obj;
        Pet pet;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getMyPets]");
        ArrayList arrayList2 = null;
        Bundle call = this.context.getContentResolver().call(BASIC_DOMAIN_PROVIDER_URI, GET_MY_PETS_METHOD, (String) null, (Bundle) null);
        if (call != null) {
            arrayList = call.getStringArrayList(GET_MY_PETS_RESULT_KEY);
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            ArrayList arrayList3 = new ArrayList();
            for (String str : arrayList) {
                SafeJson safeJson = SafeJson.INSTANCE;
                j.b(str);
                try {
                    C1174b json = SafeJson.INSTANCE.getJson();
                    json.getClass();
                    obj = json.a(MyPetWrapper.Companion.serializer(), str);
                } catch (Throwable th) {
                    obj = a.l(th);
                }
                Throwable a7 = k.a(obj);
                if (a7 != null) {
                    Logger.INSTANCE.e$pde_sdk_1_0_40_release(SafeJson.TAG, "[decodeFromString] failed. " + a7 + '.');
                }
                if (obj instanceof me.j) {
                    obj = null;
                }
                MyPetWrapper myPetWrapper = (MyPetWrapper) obj;
                if (myPetWrapper != null) {
                    pet = MyPetWrapperKt.toPet(myPetWrapper);
                } else {
                    pet = null;
                }
                if (pet != null) {
                    arrayList3.add(pet);
                }
            }
            arrayList2 = arrayList3;
        }
        if (arrayList2 == null) {
            return C1202t.d;
        }
        return arrayList2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        if (r8 == r1) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getMyProfile(qe.C1227c r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyProfile$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyProfile$1 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyProfile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyProfile$1 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyProfile$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r5) goto L_0x003c
            if (r2 != r4) goto L_0x0034
            java.lang.Object r7 = r0.L$1
            android.content.ServiceConnection r7 = (android.content.ServiceConnection) r7
            java.lang.Object r0 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r0 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r0
            L2.a.A(r8)
            goto L_0x0090
        L_0x0034:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003c:
            java.lang.Object r7 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r7 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r7
            L2.a.A(r8)
            goto L_0x0059
        L_0x0044:
            L2.a.A(r8)
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "[getMyProfile] in"
            r8.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r8 = r7.bindService(r0)
            if (r8 != r1) goto L_0x0059
            goto L_0x008b
        L_0x0059:
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            if (r8 != 0) goto L_0x0066
            com.samsung.android.sdk.moneta.common.Logger r7 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = "[getMyProfile] bind service fail"
            r7.e$pde_sdk_1_0_40_release(r3, r8)
            r7 = 0
            return r7
        L_0x0066:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r7.basicDomainService
            if (r0 == 0) goto L_0x0085
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$createMyProfileResponse$1 r3 = r7.createMyProfileResponse(r2)
            r0.getMyProfile(r3)
        L_0x0085:
            java.lang.Object r0 = r2.q()
            if (r0 != r1) goto L_0x008c
        L_0x008b:
            return r1
        L_0x008c:
            r6 = r0
            r0 = r7
            r7 = r8
            r8 = r6
        L_0x0090:
            r1 = r8
            com.samsung.android.sdk.moneta.basicdomain.entity.MyProfile r1 = (com.samsung.android.sdk.moneta.basicdomain.entity.MyProfile) r1
            android.content.Context r0 = r0.context
            r0.unbindService(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl.getMyProfile(qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.PersonType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0086, code lost:
        if (r11 == r1) goto L_0x00b6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getMyFamily(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r9, com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r10, qe.C1227c r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$5
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$5 r0 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$5 r0 = new com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$getMyFamily$5
            r0.<init>(r8, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "BasicDomain-BasicDomainServiceImpl"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0057
            if (r2 == r5) goto L_0x0045
            if (r2 != r4) goto L_0x003d
            java.lang.Object r8 = r0.L$3
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$2
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r9
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r9 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r9
            L2.a.A(r11)
            goto L_0x00bb
        L_0x003d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0045:
            java.lang.Object r8 = r0.L$2
            r10 = r8
            com.samsung.android.sdk.moneta.basicdomain.entity.PersonType r10 = (com.samsung.android.sdk.moneta.basicdomain.entity.PersonType) r10
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip r9 = (com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl r8 = (com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl) r8
            L2.a.A(r11)
            goto L_0x0089
        L_0x0057:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[getMyFamily] in relationShip "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r6 = " personType "
            r2.append(r6)
            r2.append(r10)
            r6 = 32
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r11.i$pde_sdk_1_0_40_release(r3, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r5
            java.lang.Object r11 = r8.bindService(r0)
            if (r11 != r1) goto L_0x0089
            goto L_0x00b6
        L_0x0089:
            android.content.ServiceConnection r11 = (android.content.ServiceConnection) r11
            if (r11 == 0) goto L_0x00c4
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r11
            r0.label = r4
            Vf.l r2 = new Vf.l
            qe.c r0 = L1.d.m(r0)
            r2.<init>(r5, r0)
            r2.r()
            com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService r0 = r8.basicDomainService
            if (r0 == 0) goto L_0x00b0
            com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl$createPersonListResponse$1 r3 = r8.createPersonListResponse(r2)
            r0.getMyFamily(r9, r10, r3)
        L_0x00b0:
            java.lang.Object r9 = r2.q()
            if (r9 != r1) goto L_0x00b7
        L_0x00b6:
            return r1
        L_0x00b7:
            r7 = r9
            r9 = r8
            r8 = r11
            r11 = r7
        L_0x00bb:
            r10 = r11
            java.util.List r10 = (java.util.List) r10
            android.content.Context r9 = r9.context
            r9.unbindService(r8)
            return r11
        L_0x00c4:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[getMyFamily] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r3, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl.getMyFamily(com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip, com.samsung.android.sdk.moneta.basicdomain.entity.PersonType, qe.c):java.lang.Object");
    }

    public /* synthetic */ Object getMyFamily(C1227c cVar) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getMyFamily]");
        return getMyFamily(PersonType.CONTACT, cVar);
    }
}
