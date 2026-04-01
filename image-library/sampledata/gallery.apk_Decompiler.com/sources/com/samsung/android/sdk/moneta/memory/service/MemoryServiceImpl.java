package com.samsung.android.sdk.moneta.memory.service;

import L1.d;
import Vf.C0875l;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.IMemoryService;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.TravelStateBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context.PlaceWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context.TravelStateWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context.PlaceWrapperV2;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000½\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0012\b\u0001\u0018\u0000 K2\u00020\u0001:\u0001KB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H@¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ1\u0010\u0013\u001a\u00020\u0012\"\u0004\b\u0000\u0010\f2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J+\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\"\u0004\b\u0000\u0010\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u00192\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001f\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001d2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u001f\u0010 J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\u0006\u0010\"\u001a\u00020!H@¢\u0006\u0004\b$\u0010%J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\u0006\u0010\"\u001a\u00020&H@¢\u0006\u0004\b'\u0010(J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020#0\u000e2\u0006\u0010\"\u001a\u00020!H@¢\u0006\u0004\b)\u0010%J\u001e\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u000e2\u0006\u0010\"\u001a\u00020*H@¢\u0006\u0004\b,\u0010-J\u001e\u00100\u001a\b\u0012\u0004\u0012\u00020/0\u000e2\u0006\u0010\"\u001a\u00020.H@¢\u0006\u0004\b0\u00101J\u001e\u00104\u001a\b\u0012\u0004\u0012\u0002030\u000e2\u0006\u0010\"\u001a\u000202H@¢\u0006\u0004\b4\u00105J\u001e\u00108\u001a\b\u0012\u0004\u0012\u0002070\u000e2\u0006\u0010\"\u001a\u000206H@¢\u0006\u0004\b8\u00109J\u001a\u0010<\u001a\u0004\u0018\u00010;2\u0006\u0010\"\u001a\u00020:H@¢\u0006\u0004\b<\u0010=J\u001a\u0010@\u001a\u0004\u0018\u00010?2\u0006\u0010>\u001a\u000207H@¢\u0006\u0004\b@\u0010AJ\u001e\u0010C\u001a\b\u0012\u0004\u0012\u0002030\u000e2\u0006\u0010\"\u001a\u00020BH@¢\u0006\u0004\bC\u0010DR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010E\u001a\u0004\bF\u0010GR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bI\u0010J¨\u0006L"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemoryServiceImpl;", "Lcom/samsung/android/sdk/moneta/memory/service/MemoryService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/content/ServiceConnection;", "bindService", "(Lqe/c;)Ljava/lang/Object;", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "T", "Lqe/c;", "", "continuation", "", "className", "com/samsung/android/sdk/moneta/memory/service/MemoryServiceImpl$createSharedMemoryResponse$1", "createSharedMemoryResponse", "(Lqe/c;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/service/MemoryServiceImpl$createSharedMemoryResponse$1;", "Landroid/os/Parcel;", "parcel", "getDataList", "(Ljava/lang/String;Landroid/os/Parcel;)Ljava/util/List;", "Ljava/lang/Class;", "Landroid/os/Parcelable;", "getClazz", "(Ljava/lang/String;)Ljava/lang/Class;", "Ljava/lang/ClassLoader;", "kotlin.jvm.PlatformType", "getClassLoader", "(Ljava/lang/String;)Ljava/lang/ClassLoader;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;", "options", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "queryEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "queryTravelEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;Lqe/c;)Ljava/lang/Object;", "queryEngramByLocation", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "queryActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledTravel;", "queryScheduledTravelActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "queryContent", "(Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "queryPlace", "(Lcom/samsung/android/sdk/moneta/memory/option/PlaceQueryOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "getCurrentTravelState", "(Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;Lqe/c;)Ljava/lang/Object;", "place", "", "updatePlace", "(Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;", "queryMusicPlayedInExercising", "(Lcom/samsung/android/sdk/moneta/memory/option/ExercisingQueryOption;Lqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/samsung/android/sdk/moneta/memory/IMemoryService;", "memoryService", "Lcom/samsung/android/sdk/moneta/memory/IMemoryService;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemoryServiceImpl implements MemoryService {
    private static final String ACTION_BIND_MEMORY_SERVICE = "com.samsung.android.moneta.service.intent.action.BIND_MEMORY_SERVICE";
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "Memory-MemoryServiceImpl";
    private final Context context;
    /* access modifiers changed from: private */
    public IMemoryService memoryService;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemoryServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_BIND_MEMORY_SERVICE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MemoryServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public final Object bindService(C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] in");
        C0875l lVar = new C0875l(1, d.m(cVar));
        lVar.r();
        boolean bindService = getContext().bindService(getIntent(), new MemoryServiceImpl$bindService$2$connection$1(this, lVar), 1);
        logger.e$pde_sdk_1_0_40_release(TAG, "[bindService] bind service result : " + bindService);
        if (!bindService) {
            lVar.resumeWith((Object) null);
        }
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    /* access modifiers changed from: private */
    public final <T> MemoryServiceImpl$createSharedMemoryResponse$1 createSharedMemoryResponse(C1227c cVar, String str) {
        return new MemoryServiceImpl$createSharedMemoryResponse$1(this, str, cVar);
    }

    private final ClassLoader getClassLoader(String str) {
        if (j.a(str, "Engram")) {
            return Engram.class.getClassLoader();
        }
        if (j.a(str, "Activity")) {
            return Activity.class.getClassLoader();
        }
        if (j.a(str, "Content")) {
            return Content.class.getClassLoader();
        }
        if (j.a(str, "Place")) {
            return Place.class.getClassLoader();
        }
        if (j.a(str, "EngramWrapper")) {
            return EngramWrapper.class.getClassLoader();
        }
        if (j.a(str, "ActivityWrapper")) {
            return ActivityWrapper.class.getClassLoader();
        }
        if (j.a(str, "ContentWrapper")) {
            return ContentWrapper.class.getClassLoader();
        }
        if (j.a(str, "ActivityBundleWrapper")) {
            return ActivityBundleWrapper.class.getClassLoader();
        }
        if (j.a(str, "ContentBundleWrapper")) {
            return ContentBundleWrapper.class.getClassLoader();
        }
        if (j.a(str, "PlaceWrapperV1")) {
            return PlaceWrapperV1.class.getClassLoader();
        }
        if (j.a(str, "PlaceWrapperV2")) {
            return PlaceWrapperV2.class.getClassLoader();
        }
        if (j.a(str, "PlaceBundleWrapper")) {
            return PlaceBundleWrapper.class.getClassLoader();
        }
        if (j.a(str, "TravelStateWrapperV1")) {
            return TravelStateWrapperV1.class.getClassLoader();
        }
        if (j.a(str, "TravelStateBundleWrapper")) {
            return TravelStateBundleWrapper.class.getClassLoader();
        }
        throw new IllegalArgumentException(C0212a.l("Unknown class name: ", str));
    }

    private final Class<? extends Parcelable> getClazz(String str) {
        if (j.a(str, "Engram")) {
            return Engram.class;
        }
        if (j.a(str, "Activity")) {
            return Activity.class;
        }
        if (j.a(str, "Content")) {
            return Content.class;
        }
        if (j.a(str, "Place")) {
            return Place.class;
        }
        if (j.a(str, "EngramWrapper")) {
            return EngramWrapper.class;
        }
        if (j.a(str, "ActivityWrapper")) {
            return ActivityWrapper.class;
        }
        if (j.a(str, "ContentWrapper")) {
            return ContentWrapper.class;
        }
        if (j.a(str, "ActivityBundleWrapper")) {
            return ActivityBundleWrapper.class;
        }
        if (j.a(str, "ContentBundleWrapper")) {
            return ContentBundleWrapper.class;
        }
        if (j.a(str, "PlaceWrapperV1")) {
            return PlaceWrapperV1.class;
        }
        if (j.a(str, "PlaceWrapperV2")) {
            return PlaceWrapperV2.class;
        }
        if (j.a(str, "PlaceBundleWrapper")) {
            return PlaceBundleWrapper.class;
        }
        if (j.a(str, "TravelStateWrapperV1")) {
            return TravelStateWrapperV1.class;
        }
        if (j.a(str, "TravelStateBundleWrapper")) {
            return TravelStateBundleWrapper.class;
        }
        throw new IllegalArgumentException(C0212a.l("Unknown class name: ", str));
    }

    /* access modifiers changed from: private */
    public final <T> List<T> getDataList(String str, Parcel parcel) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getDataList] in");
        List<T> j2 = parcel.readParcelableList(new ArrayList(), getClassLoader(str), getClazz(str));
        j.d(j2, "readParcelableList(...)");
        if (j2.isEmpty()) {
            logger.i$pde_sdk_1_0_40_release(TAG, "[getDataList] dataList is emptyList");
            return C1202t.d;
        }
        logger.i$pde_sdk_1_0_40_release(TAG, "[getDataList] dataList size " + j2.size());
        return j2;
    }

    /* access modifiers changed from: private */
    public final Intent getIntent() {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getIntent] in");
        Intent intent = new Intent(ACTION_BIND_MEMORY_SERVICE);
        intent.setPackage(Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        return intent;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getCurrentTravelState(com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$getCurrentTravelState$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$getCurrentTravelState$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$getCurrentTravelState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$getCurrentTravelState$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$getCurrentTravelState$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00dd
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[getCurrentTravelState] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00fa
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelStateQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelStateQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelStateQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "TravelStateBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.getCurrentTravelStateV3(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.TravelStateBundleWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.TravelStateBundleWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.context.TravelState r10 = r10.toContext()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            java.lang.Object r8 = ne.C1194l.N0(r8)     // Catch:{ Exception -> 0x0039 }
            return r8
        L_0x00dd:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = He.F.P(r8)
            java.lang.String r0 = "getCurrentTravelState: Exception raised.\n "
            java.lang.String r10 = r0.concat(r10)
            r9.e$pde_sdk_1_0_40_release(r4, r10)
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r8 = r0.concat(r8)
            r9.<init>(r8)
            throw r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[getCurrentTravelState] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.getCurrentTravelState(com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryActivity(com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryActivity$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryActivity$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryActivity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryActivity$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryActivity$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryActivity] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ActivityQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ActivityQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ActivityQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "ActivityBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryActivityV4(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ActivityBundleWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ActivityBundleWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.activity.Activity r10 = r10.toActivity()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryActivity: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryActivity] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryActivity(com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.ContentQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryContent(com.samsung.android.sdk.moneta.memory.option.ContentQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryContent$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryContent$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryContent$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryContent$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.ContentQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ContentQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.ContentQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ContentQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryContent] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ContentQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ContentQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ContentQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "ContentBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryContentV4(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.content.Content r10 = r10.toContent()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryContent: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryContent] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryContent(com.samsung.android.sdk.moneta.memory.option.ContentQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryEngram(com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngram$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngram$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngram$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngram$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngram$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryEngram] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "EngramWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryEngramV4(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.Engram r10 = r10.toEngram()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryEngram: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryEngram] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryEngram(com.samsung.android.sdk.moneta.memory.option.EngramQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryEngramByLocation(com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngramByLocation$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngramByLocation$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngramByLocation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngramByLocation$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryEngramByLocation$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.EngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryEngramByLocation] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "EngramWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryEngramByLocation(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.Engram r10 = r10.toEngram()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryEngramByLocation: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryEngramByLocation] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryEngramByLocation(com.samsung.android.sdk.moneta.memory.option.EngramQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryMusicPlayedInExercising(com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryMusicPlayedInExercising$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryMusicPlayedInExercising$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryMusicPlayedInExercising$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryMusicPlayedInExercising$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryMusicPlayedInExercising$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryFrequentMusicInExercising] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ExercisingQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ExercisingQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ExercisingQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "ContentBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryMusicPlayedInExercising(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.content.Content r10 = r10.toContent()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "[queryMusicPlayedInExercising] Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryFrequentMusicInExercising] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryMusicPlayedInExercising(com.samsung.android.sdk.moneta.memory.option.ExercisingQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryPlace(com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryPlace$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryPlace$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryPlace$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryPlace$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryPlace$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryPlace] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.PlaceQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.PlaceQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.PlaceQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "PlaceBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryPlaceV3(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.context.Place r10 = r10.toContext()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryPlace: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryPlace] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryPlace(com.samsung.android.sdk.moneta.memory.option.PlaceQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryScheduledTravelActivity(com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryScheduledTravelActivity$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryScheduledTravelActivity$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryScheduledTravelActivity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryScheduledTravelActivity$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryScheduledTravelActivity$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00e0
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryScheduledTravel] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00f2
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ScheduledTravelActivityQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ScheduledTravelActivityQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.ScheduledTravelActivityQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "ActivityWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryScheduledTravelActivityV3(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00df
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.activity.Activity r10 = r10.toActivity()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = "null cannot be cast to non-null type com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledTravel"
            kotlin.jvm.internal.j.c(r10, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledTravel r10 = (com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledTravel) r10     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00df:
            return r8
        L_0x00e0:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryScheduledTravel: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00f2:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryScheduledTravel] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryScheduledTravelActivity(com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0071, code lost:
        if (r10 == r1) goto L_0x00a7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[SYNTHETIC, Splitter:B:21:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca A[Catch:{ Exception -> 0x0039 }, LOOP:0: B:30:0x00c4->B:32:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object queryTravelEngram(com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption r9, qe.C1227c r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryTravelEngram$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryTravelEngram$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryTravelEngram$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryTravelEngram$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$queryTravelEngram$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r5) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$2
            android.content.ServiceConnection r8 = (android.content.ServiceConnection) r8
            java.lang.Object r9 = r0.L$1
            com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption) r9
            java.lang.Object r9 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r9
            L2.a.A(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00ac
        L_0x0039:
            r8 = move-exception
            goto L_0x00d9
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption r9 = (com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption) r9
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r10)
            goto L_0x0074
        L_0x0051:
            L2.a.A(r10)
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "[queryTravelEngram] in options: "
            r2.<init>(r6)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r10.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = r8.bindService(r0)
            if (r10 != r1) goto L_0x0074
            goto L_0x00a7
        L_0x0074:
            android.content.ServiceConnection r10 = (android.content.ServiceConnection) r10
            if (r10 == 0) goto L_0x00eb
            r0.L$0 = r8     // Catch:{ Exception -> 0x0039 }
            r0.L$1 = r9     // Catch:{ Exception -> 0x0039 }
            r0.L$2 = r10     // Catch:{ Exception -> 0x0039 }
            r0.label = r3     // Catch:{ Exception -> 0x0039 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0039 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0039 }
            r2.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r8.memoryService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00a1
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelEngramQueryOptionBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelEngramQueryOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.TravelEngramQueryOptionBundleWrapper r9 = r3.fromOption(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = "EngramWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$createSharedMemoryResponse$1 r3 = r8.createSharedMemoryResponse(r2, r3)     // Catch:{ Exception -> 0x0039 }
            r0.queryTravelEngramV4(r9, r3)     // Catch:{ Exception -> 0x0039 }
        L_0x00a1:
            java.lang.Object r9 = r2.q()     // Catch:{ Exception -> 0x0039 }
            if (r9 != r1) goto L_0x00a8
        L_0x00a7:
            return r1
        L_0x00a8:
            r7 = r9
            r9 = r8
            r8 = r10
            r10 = r7
        L_0x00ac:
            java.util.List r10 = (java.util.List) r10     // Catch:{ Exception -> 0x0039 }
            android.content.Context r9 = r9.context     // Catch:{ Exception -> 0x0039 }
            r9.unbindService(r8)     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r9 = 10
            int r9 = ne.C1196n.w0(r10, r9)     // Catch:{ Exception -> 0x0039 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00c4:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r10 == 0) goto L_0x00d8
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper r10 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper) r10     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.Engram r10 = r10.toEngram()     // Catch:{ Exception -> 0x0039 }
            r8.add(r10)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00c4
        L_0x00d8:
            return r8
        L_0x00d9:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = He.F.P(r8)
            java.lang.String r10 = "queryTravelEngram: Exception raised.\n "
            java.lang.String r8 = r10.concat(r8)
            r9.e$pde_sdk_1_0_40_release(r4, r8)
            ne.t r8 = ne.C1202t.d
            return r8
        L_0x00eb:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = "[queryTravelEngram] bind service fail"
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r8 = c0.C0086a.e(r8, r4, r9, r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.queryTravelEngram(com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: com.samsung.android.sdk.moneta.memory.entity.context.Place} */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
        if (r9 == r1) goto L_0x0099;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006b A[SYNTHETIC, Splitter:B:21:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updatePlace(com.samsung.android.sdk.moneta.memory.entity.context.Place r8, qe.C1227c r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$1 r0 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$1 r0 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$1
            r0.<init>(r7, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            java.lang.String r4 = "Memory-MemoryServiceImpl"
            r5 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 == r5) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r7 = r0.L$2
            android.content.ServiceConnection r7 = (android.content.ServiceConnection) r7
            java.lang.Object r8 = r0.L$1
            com.samsung.android.sdk.moneta.memory.entity.context.Place r8 = (com.samsung.android.sdk.moneta.memory.entity.context.Place) r8
            java.lang.Object r8 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r8 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r8
            L2.a.A(r9)     // Catch:{ Exception -> 0x0038 }
            goto L_0x009e
        L_0x0038:
            r7 = move-exception
            goto L_0x00ae
        L_0x003b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0043:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            com.samsung.android.sdk.moneta.memory.entity.context.Place r8 = (com.samsung.android.sdk.moneta.memory.entity.context.Place) r8
            java.lang.Object r7 = r0.L$0
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl r7 = (com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl) r7
            L2.a.A(r9)
            goto L_0x0067
        L_0x0050:
            L2.a.A(r9)
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "[updatePlace] in"
            r9.i$pde_sdk_1_0_40_release(r4, r2)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r5
            java.lang.Object r9 = r7.bindService(r0)
            if (r9 != r1) goto L_0x0067
            goto L_0x0099
        L_0x0067:
            android.content.ServiceConnection r9 = (android.content.ServiceConnection) r9
            if (r9 == 0) goto L_0x00c1
            r0.L$0 = r7     // Catch:{ Exception -> 0x0038 }
            r0.L$1 = r8     // Catch:{ Exception -> 0x0038 }
            r0.L$2 = r9     // Catch:{ Exception -> 0x0038 }
            r0.label = r3     // Catch:{ Exception -> 0x0038 }
            Vf.l r2 = new Vf.l     // Catch:{ Exception -> 0x0038 }
            qe.c r0 = L1.d.m(r0)     // Catch:{ Exception -> 0x0038 }
            r2.<init>(r5, r0)     // Catch:{ Exception -> 0x0038 }
            r2.r()     // Catch:{ Exception -> 0x0038 }
            com.samsung.android.sdk.moneta.memory.IMemoryService r0 = r7.memoryService     // Catch:{ Exception -> 0x0038 }
            if (r0 == 0) goto L_0x0093
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper$Companion r3 = com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper.Companion     // Catch:{ Exception -> 0x0038 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper r8 = r3.fromContext(r8)     // Catch:{ Exception -> 0x0038 }
            com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$2$1 r3 = new com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl$updatePlace$2$1     // Catch:{ Exception -> 0x0038 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0038 }
            r0.updatePlaceV1(r8, r3)     // Catch:{ Exception -> 0x0038 }
        L_0x0093:
            java.lang.Object r8 = r2.q()     // Catch:{ Exception -> 0x0038 }
            if (r8 != r1) goto L_0x009a
        L_0x0099:
            return r1
        L_0x009a:
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r6
        L_0x009e:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ Exception -> 0x0038 }
            boolean r9 = r9.booleanValue()     // Catch:{ Exception -> 0x0038 }
            android.content.Context r8 = r8.context     // Catch:{ Exception -> 0x0038 }
            r8.unbindService(r7)     // Catch:{ Exception -> 0x0038 }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r9)     // Catch:{ Exception -> 0x0038 }
            return r7
        L_0x00ae:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r7 = He.F.P(r7)
            java.lang.String r9 = "updatePlace: Exception raised.\n "
            java.lang.String r7 = r9.concat(r7)
            r8.e$pde_sdk_1_0_40_release(r4, r7)
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            return r7
        L_0x00c1:
            com.samsung.android.sdk.moneta.common.Logger r7 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = "[updatePlace] bind service fail"
            java.lang.String r9 = "bind service fail"
            java.lang.IllegalStateException r7 = c0.C0086a.e(r7, r4, r8, r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemoryServiceImpl.updatePlace(com.samsung.android.sdk.moneta.memory.entity.context.Place, qe.c):java.lang.Object");
    }
}
