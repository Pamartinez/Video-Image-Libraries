package com.samsung.android.sdk.moneta.memory.service;

import Be.a;
import Be.b;
import L1.d;
import Vf.C0873j;
import Vf.C0875l;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import com.samsung.android.sdk.moneta.common.Constants;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.IMemorySearchService;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.ContentBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.GraphPathNode;
import com.samsung.android.sdk.moneta.memory.entity.context.Person;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content.GraphPathNodeWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context.PersonWrapperV1;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content.GraphPathNodeWrapperV2;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context.PersonWrapperV2;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import ne.C1202t;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000Û\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004*\u000547:>C\b\u0001\u0018\u0000 Z2\u00020\u0001:\u0001ZB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u0007\u001a\u00020\fH@¢\u0006\u0004\b\u000e\u0010\u000fJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0007\u001a\u00020\fH@¢\u0006\u0004\b\u0011\u0010\u000fJ\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\u0006\u0010\u0007\u001a\u00020\u0012H@¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\b2\u0006\u0010\u0007\u001a\u00020\u0016H@¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\u001aH@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\u001eH@¢\u0006\u0004\b\u001f\u0010 J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\b2\u0006\u0010\u0007\u001a\u00020!H@¢\u0006\u0004\b#\u0010$J\u0018\u0010'\u001a\u00020&2\u0006\u0010\u0007\u001a\u00020%H@¢\u0006\u0004\b'\u0010(J\u0010\u0010*\u001a\u00020)H@¢\u0006\u0004\b*\u0010+J\u0010\u0010-\u001a\u00020,H@¢\u0006\u0004\b-\u0010+J\u000f\u0010/\u001a\u00020.H\u0002¢\u0006\u0004\b/\u00100J#\u00105\u001a\u0002042\u0012\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\b01H\u0002¢\u0006\u0004\b5\u00106J\u001d\u00108\u001a\u0002072\f\u00103\u001a\b\u0012\u0004\u0012\u00020&01H\u0002¢\u0006\u0004\b8\u00109J\u001d\u0010;\u001a\u00020:2\f\u00103\u001a\b\u0012\u0004\u0012\u00020)01H\u0002¢\u0006\u0004\b;\u0010<J\u001d\u0010?\u001a\u00020>2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001b0=H\u0002¢\u0006\u0004\b?\u0010@J1\u0010D\u001a\u00020C\"\u0004\b\u0000\u0010A2\u0012\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0=2\u0006\u0010B\u001a\u00020\tH\u0002¢\u0006\u0004\bD\u0010EJ+\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010A2\u0006\u0010B\u001a\u00020\t2\u0006\u0010G\u001a\u00020FH\u0002¢\u0006\u0004\bH\u0010IJ\u001f\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020K0J2\u0006\u0010B\u001a\u00020\tH\u0002¢\u0006\u0004\bL\u0010MJ\u001f\u0010P\u001a\n O*\u0004\u0018\u00010N0N2\u0006\u0010B\u001a\u00020\tH\u0002¢\u0006\u0004\bP\u0010QR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010R\u001a\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020,8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0018\u0010X\u001a\u0004\u0018\u00010W8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bX\u0010Y¨\u0006["}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl;", "Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "options", "", "", "getSearchIntent", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "searchEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "searchPerson", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "searchActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "searchContent", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;", "", "searchContentStat", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "searchEngramStat", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "searchGraphPath", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "getRecommendations", "(Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;Lqe/c;)Ljava/lang/Object;", "Lme/x;", "prepareSearchEngine", "(Lqe/c;)Ljava/lang/Object;", "", "bindService", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "LVf/j;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "continuation", "com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createKeywordInfoListResponse$1", "createKeywordInfoListResponse", "(LVf/j;)Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createKeywordInfoListResponse$1;", "com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createGetRecommendationsResponse$1", "createGetRecommendationsResponse", "(LVf/j;)Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createGetRecommendationsResponse$1;", "com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createPrepareSearchEngineResponse$1", "createPrepareSearchEngineResponse", "(LVf/j;)Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createPrepareSearchEngineResponse$1;", "Lqe/c;", "com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createCountResponse$1", "createCountResponse", "(Lqe/c;)Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createCountResponse$1;", "T", "className", "com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createSharedMemoryResponse$1", "createSharedMemoryResponse", "(Lqe/c;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createSharedMemoryResponse$1;", "Landroid/os/Parcel;", "parcel", "getDataList", "(Ljava/lang/String;Landroid/os/Parcel;)Ljava/util/List;", "Ljava/lang/Class;", "", "getClazz", "(Ljava/lang/String;)Ljava/lang/Class;", "Ljava/lang/ClassLoader;", "kotlin.jvm.PlatformType", "getClassLoader", "(Ljava/lang/String;)Ljava/lang/ClassLoader;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "isConnected", "Z", "Lcom/samsung/android/sdk/moneta/memory/IMemorySearchService;", "memorySearchService", "Lcom/samsung/android/sdk/moneta/memory/IMemorySearchService;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl implements MemorySearchService {
    private static final String ACTION_BIND_MEMORY_SEARCH_SERVICE = "com.samsung.android.moneta.service.intent.action.BIND_MEMORY_SEARCH_SERVICE";
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "Memory-MemorySearchServiceImpl";
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isConnected;
    /* access modifiers changed from: private */
    public IMemorySearchService memorySearchService;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "ACTION_BIND_MEMORY_SEARCH_SERVICE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public MemorySearchServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public final Object bindService(C1227c cVar) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[bindService] in");
        if (this.isConnected) {
            return Boolean.TRUE;
        }
        C0875l lVar = new C0875l(1, d.m(cVar));
        lVar.r();
        boolean bindService = getContext().bindService(getIntent(), new MemorySearchServiceImpl$bindService$2$connection$1(this, lVar), 1);
        logger.e$pde_sdk_1_0_40_release(TAG, "bind service result : " + bindService);
        if (!bindService) {
            lVar.resumeWith(Boolean.FALSE);
        }
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    /* access modifiers changed from: private */
    public final MemorySearchServiceImpl$createCountResponse$1 createCountResponse(C1227c cVar) {
        return new MemorySearchServiceImpl$createCountResponse$1(cVar);
    }

    /* access modifiers changed from: private */
    public final MemorySearchServiceImpl$createGetRecommendationsResponse$1 createGetRecommendationsResponse(C0873j jVar) {
        return new MemorySearchServiceImpl$createGetRecommendationsResponse$1(jVar);
    }

    private final MemorySearchServiceImpl$createKeywordInfoListResponse$1 createKeywordInfoListResponse(C0873j jVar) {
        return new MemorySearchServiceImpl$createKeywordInfoListResponse$1(jVar);
    }

    /* access modifiers changed from: private */
    public final MemorySearchServiceImpl$createPrepareSearchEngineResponse$1 createPrepareSearchEngineResponse(C0873j jVar) {
        return new MemorySearchServiceImpl$createPrepareSearchEngineResponse$1(jVar);
    }

    /* access modifiers changed from: private */
    public final <T> MemorySearchServiceImpl$createSharedMemoryResponse$1 createSharedMemoryResponse(C1227c cVar, String str) {
        return new MemorySearchServiceImpl$createSharedMemoryResponse$1(this, str, cVar);
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
        if (j.a(str, "Person")) {
            return Person.class.getClassLoader();
        }
        if (j.a(str, "GraphPathNode")) {
            return GraphPathNode.class.getClassLoader();
        }
        if (j.a(str, "String")) {
            return String.class.getClassLoader();
        }
        if (j.a(str, "EngramWrapper")) {
            return EngramWrapper.class.getClassLoader();
        }
        if (j.a(str, "PersonWrapperV1")) {
            return PersonWrapperV1.class.getClassLoader();
        }
        if (j.a(str, "PersonWrapperV2")) {
            return PersonWrapperV2.class.getClassLoader();
        }
        if (j.a(str, "PersonBundleWrapper")) {
            return PersonBundleWrapper.class.getClassLoader();
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
        if (j.a(str, "GraphPathNodeWrapperV1")) {
            return GraphPathNodeWrapperV1.class.getClassLoader();
        }
        if (j.a(str, "GraphPathNodeWrapperV2")) {
            return GraphPathNodeWrapperV2.class.getClassLoader();
        }
        if (j.a(str, "GraphPathNodeBundleWrapper")) {
            return GraphPathNodeBundleWrapper.class.getClassLoader();
        }
        if (j.a(str, "KeywordInfoBundleWrapper")) {
            return KeywordInfoBundleWrapper.class.getClassLoader();
        }
        throw new IllegalArgumentException(C0212a.l("Unknown class name: ", str));
    }

    private final Class<? extends Object> getClazz(String str) {
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
        if (j.a(str, "Person")) {
            return Person.class;
        }
        if (j.a(str, "GraphPathNode")) {
            return GraphPathNode.class;
        }
        if (j.a(str, "String")) {
            return String.class;
        }
        if (j.a(str, "EngramWrapper")) {
            return EngramWrapper.class;
        }
        if (j.a(str, "PersonWrapperV1")) {
            return PersonWrapperV1.class;
        }
        if (j.a(str, "PersonWrapperV2")) {
            return PersonWrapperV2.class;
        }
        if (j.a(str, "PersonBundleWrapper")) {
            return PersonBundleWrapper.class;
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
        if (j.a(str, "GraphPathNodeWrapperV1")) {
            return GraphPathNodeWrapperV1.class;
        }
        if (j.a(str, "GraphPathNodeWrapperV2")) {
            return GraphPathNodeWrapperV2.class;
        }
        if (j.a(str, "GraphPathNodeBundleWrapper")) {
            return GraphPathNodeBundleWrapper.class;
        }
        if (j.a(str, "KeywordInfoBundleWrapper")) {
            return KeywordInfoBundleWrapper.class;
        }
        throw new IllegalArgumentException(C0212a.l("Unknown class name: ", str));
    }

    /* access modifiers changed from: private */
    public final <T> List<T> getDataList(String str, Parcel parcel) {
        ClassLoader classLoader = getClassLoader(str);
        Class<? extends Object> clazz = getClazz(str);
        ArrayList arrayList = new ArrayList();
        List<T> arrayList2 = new ArrayList<>();
        if (!j.a(str, "String")) {
            arrayList2 = parcel.readParcelableList(arrayList, classLoader, clazz);
        } else if (!(arrayList2 instanceof a) || (arrayList2 instanceof b)) {
            parcel.readStringList(arrayList2);
        } else {
            y.e(arrayList2, "kotlin.collections.MutableList");
            throw null;
        }
        if (arrayList2.isEmpty()) {
            Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getDataList] dataList is emptyList");
            return C1202t.d;
        }
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "[getDataList] dataList size " + arrayList2.size());
        return arrayList2;
    }

    /* access modifiers changed from: private */
    public final Intent getIntent() {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release(TAG, "[getIntent] in");
        Intent intent = new Intent(ACTION_BIND_MEMORY_SEARCH_SERVICE);
        intent.setPackage(Constants.SMART_SUGGESTIONS_PACKAGE_NAME);
        return intent;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
        if (r12 == r2) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cc, code lost:
        if (r12 == r2) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ce, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e A[SYNTHETIC, Splitter:B:25:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getRecommendations(com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption r11, qe.C1227c r12) {
        /*
            r10 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r12 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getRecommendations$1
            if (r1 == 0) goto L_0x0015
            r1 = r12
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getRecommendations$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getRecommendations$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getRecommendations$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getRecommendations$1
            r1.<init>(r10, r12)
        L_0x001a:
            java.lang.Object r12 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            java.lang.String r5 = ""
            r6 = 2
            r7 = 1
            java.lang.String r8 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0053
            if (r3 == r7) goto L_0x0046
            if (r3 != r6) goto L_0x003e
            java.lang.Object r10 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption r10 = (com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption) r10
            java.lang.Object r10 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r10 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r10
            L2.a.A(r12)     // Catch:{ Exception -> 0x003b }
            goto L_0x00cf
        L_0x003b:
            r10 = move-exception
            goto L_0x00d2
        L_0x003e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0046:
            java.lang.Object r10 = r1.L$1
            r11 = r10
            com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption r11 = (com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption) r11
            java.lang.Object r10 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r10 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r10
            L2.a.A(r12)
            goto L_0x0086
        L_0x0053:
            L2.a.A(r12)
            com.samsung.android.sdk.moneta.common.Logger r12 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r9 = "[getRecommendations] in options: "
            r3.<init>(r9)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            r12.i$pde_sdk_1_0_40_release(r8, r3)
            java.util.List r12 = r11.getMediaIdList()
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0079
            com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse r10 = new com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse
            r10.<init>(r5, r4)
            return r10
        L_0x0079:
            r1.L$0 = r10
            r1.L$1 = r11
            r1.label = r7
            java.lang.Object r12 = r10.bindService(r1)
            if (r12 != r2) goto L_0x0086
            goto L_0x00ce
        L_0x0086:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00e7
            r1.L$0 = r10     // Catch:{ Exception -> 0x003b }
            r1.L$1 = r11     // Catch:{ Exception -> 0x003b }
            r1.label = r6     // Catch:{ Exception -> 0x003b }
            Vf.l r12 = new Vf.l     // Catch:{ Exception -> 0x003b }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x003b }
            r12.<init>(r7, r1)     // Catch:{ Exception -> 0x003b }
            r12.r()     // Catch:{ Exception -> 0x003b }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x003b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r3.<init>(r0)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r10.memorySearchService     // Catch:{ Exception -> 0x003b }
            r3.append(r0)     // Catch:{ Exception -> 0x003b }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x003b }
            r1.i$pde_sdk_1_0_40_release(r8, r0)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r10.memorySearchService     // Catch:{ Exception -> 0x003b }
            if (r0 == 0) goto L_0x00c8
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.RecommendationsGetOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.RecommendationsGetOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x003b }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.RecommendationsGetOptionBundleWrapper r11 = r1.fromOption(r11)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createGetRecommendationsResponse$1 r10 = r10.createGetRecommendationsResponse(r12)     // Catch:{ Exception -> 0x003b }
            r0.getRecommendationsV3(r11, r10)     // Catch:{ Exception -> 0x003b }
        L_0x00c8:
            java.lang.Object r12 = r12.q()     // Catch:{ Exception -> 0x003b }
            if (r12 != r2) goto L_0x00cf
        L_0x00ce:
            return r2
        L_0x00cf:
            com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse r12 = (com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse) r12     // Catch:{ Exception -> 0x003b }
            return r12
        L_0x00d2:
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = He.F.P(r10)
            java.lang.String r12 = "getRecommendations: Exception raised.\n "
            java.lang.String r10 = r12.concat(r10)
            r11.e$pde_sdk_1_0_40_release(r8, r10)
            com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse r10 = new com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse
            r10.<init>(r5, r4)
            return r10
        L_0x00e7:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r11 = "bind service fail"
            java.lang.IllegalStateException r10 = c0.C0086a.e(r10, r8, r11, r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.getRecommendations(com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getSearchIntent(com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "getSearchIntent : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getSearchIntent$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getSearchIntent$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getSearchIntent$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getSearchIntent$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$getSearchIntent$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00d5
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[getSearchIntent] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00e5
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchIntentOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchIntentOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchIntentOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "String"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.getSearchIntentV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            return r11
        L_0x00d5:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "getSearchIntent: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x00e5:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.getSearchIntent(com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption, qe.c):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        if (r8 == r2) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0090, code lost:
        if (r8.q() == r2) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0092, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a A[SYNTHETIC, Splitter:B:21:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object prepareSearchEngine(qe.C1227c r8) {
        /*
            r7 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r8 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$prepareSearchEngine$1
            if (r1 == 0) goto L_0x0015
            r1 = r8
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$prepareSearchEngine$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$prepareSearchEngine$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$prepareSearchEngine$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$prepareSearchEngine$1
            r1.<init>(r7, r8)
        L_0x001a:
            java.lang.Object r8 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 2
            java.lang.String r5 = "Memory-MemorySearchServiceImpl"
            r6 = 1
            if (r3 == 0) goto L_0x0044
            if (r3 == r6) goto L_0x003c
            if (r3 != r4) goto L_0x0034
            java.lang.Object r7 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r7 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r7
            L2.a.A(r8)     // Catch:{ Exception -> 0x0032 }
            goto L_0x00a2
        L_0x0032:
            r7 = move-exception
            goto L_0x0093
        L_0x0034:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003c:
            java.lang.Object r7 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r7 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r7
            L2.a.A(r8)
            goto L_0x0052
        L_0x0044:
            L2.a.A(r8)
            r1.L$0 = r7
            r1.label = r6
            java.lang.Object r8 = r7.bindService(r1)
            if (r8 != r2) goto L_0x0052
            goto L_0x0092
        L_0x0052:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x00a5
            r1.L$0 = r7     // Catch:{ Exception -> 0x0032 }
            r1.label = r4     // Catch:{ Exception -> 0x0032 }
            Vf.l r8 = new Vf.l     // Catch:{ Exception -> 0x0032 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0032 }
            r8.<init>(r6, r1)     // Catch:{ Exception -> 0x0032 }
            r8.r()     // Catch:{ Exception -> 0x0032 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0032 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0032 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0032 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r7.memorySearchService     // Catch:{ Exception -> 0x0032 }
            r3.append(r0)     // Catch:{ Exception -> 0x0032 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0032 }
            r1.i$pde_sdk_1_0_40_release(r5, r0)     // Catch:{ Exception -> 0x0032 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r7.memorySearchService     // Catch:{ Exception -> 0x0032 }
            if (r0 == 0) goto L_0x008c
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createPrepareSearchEngineResponse$1 r7 = r7.createPrepareSearchEngineResponse(r8)     // Catch:{ Exception -> 0x0032 }
            r0.prepareSearchEngineV3(r7)     // Catch:{ Exception -> 0x0032 }
        L_0x008c:
            java.lang.Object r7 = r8.q()     // Catch:{ Exception -> 0x0032 }
            if (r7 != r2) goto L_0x00a2
        L_0x0092:
            return r2
        L_0x0093:
            com.samsung.android.sdk.moneta.common.Logger r8 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r7 = He.F.P(r7)
            java.lang.String r0 = "prepareSearchEngine: Exception raised.\n "
            java.lang.String r7 = r0.concat(r7)
            r8.e$pde_sdk_1_0_40_release(r5, r7)
        L_0x00a2:
            me.x r7 = me.x.f4917a
            return r7
        L_0x00a5:
            com.samsung.android.sdk.moneta.common.Logger r7 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r8 = "bind service fail"
            java.lang.IllegalStateException r7 = c0.C0086a.e(r7, r5, r8, r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.prepareSearchEngine(qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchActivity(com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchActivity$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchActivity$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchActivity$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchActivity$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchActivity$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00fa
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchActivity] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x010b
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchActivityOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchActivityOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchActivityOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "ActivityWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.searchActivityV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r10 = 10
            int r10 = ne.C1196n.w0(r11, r10)     // Catch:{ Exception -> 0x0039 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00e5:
            boolean r11 = r10.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r11 == 0) goto L_0x00f9
            java.lang.Object r11 = r10.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper) r11     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.activity.Activity r11 = r11.toActivity()     // Catch:{ Exception -> 0x0039 }
            r9.add(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00e5
        L_0x00f9:
            return r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchActivity: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x010b:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchActivity(com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchContent(com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContent$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContent$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContent$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContent$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContent$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00fa
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchContent] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x010b
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "ContentWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.searchContentV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r10 = 10
            int r10 = ne.C1196n.w0(r11, r10)     // Catch:{ Exception -> 0x0039 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00e5:
            boolean r11 = r10.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r11 == 0) goto L_0x00f9
            java.lang.Object r11 = r10.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper) r11     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.content.Content r11 = r11.toContent()     // Catch:{ Exception -> 0x0039 }
            r9.add(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00e5
        L_0x00f9:
            return r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchContent: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x010b:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchContent(com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0087, code lost:
        if (r11 == r2) goto L_0x00d2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0092 A[SYNTHETIC, Splitter:B:26:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchContentStat(com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContentStat$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContentStat$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContentStat$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContentStat$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchContentStat$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x004f
            if (r3 == r6) goto L_0x0042
            if (r3 != r5) goto L_0x003a
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0037 }
            return r11
        L_0x0037:
            r9 = move-exception
            goto L_0x00d4
        L_0x003a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0042:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x008a
        L_0x004f:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchContentStat] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007d
            java.lang.Integer r9 = new java.lang.Integer
            r9.<init>(r4)
            return r9
        L_0x007d:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x008a
            goto L_0x00d2
        L_0x008a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00ea
            r1.L$0 = r9     // Catch:{ Exception -> 0x0037 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0037 }
            r1.label = r5     // Catch:{ Exception -> 0x0037 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0037 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0037 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0037 }
            r11.r()     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0037 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0037 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0037 }
            r3.append(r0)     // Catch:{ Exception -> 0x0037 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0037 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0037 }
            if (r0 == 0) goto L_0x00cc
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentStatOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentStatOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchContentStatOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createCountResponse$1 r9 = r9.createCountResponse(r11)     // Catch:{ Exception -> 0x0037 }
            r0.searchContentStatV3(r10, r9)     // Catch:{ Exception -> 0x0037 }
        L_0x00cc:
            java.lang.Object r9 = r11.q()     // Catch:{ Exception -> 0x0037 }
            if (r9 != r2) goto L_0x00d3
        L_0x00d2:
            return r2
        L_0x00d3:
            return r9
        L_0x00d4:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchContentStat: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            java.lang.Integer r9 = new java.lang.Integer
            r9.<init>(r4)
            return r9
        L_0x00ea:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchContentStat(com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchEngram(com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngram$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngram$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngram$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngram$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngram$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00fa
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchEngram] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x010b
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "EngramWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.searchEngramV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r10 = 10
            int r10 = ne.C1196n.w0(r11, r10)     // Catch:{ Exception -> 0x0039 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00e5:
            boolean r11 = r10.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r11 == 0) goto L_0x00f9
            java.lang.Object r11 = r10.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper) r11     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.Engram r11 = r11.toEngram()     // Catch:{ Exception -> 0x0039 }
            r9.add(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00e5
        L_0x00f9:
            return r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchEngram: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x010b:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchEngram(com.samsung.android.sdk.moneta.memory.option.EngramSearchOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0087, code lost:
        if (r11 == r2) goto L_0x00d2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0092 A[SYNTHETIC, Splitter:B:26:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchEngramStat(com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngramStat$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngramStat$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngramStat$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngramStat$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchEngramStat$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 0
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x004f
            if (r3 == r6) goto L_0x0042
            if (r3 != r5) goto L_0x003a
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0037 }
            return r11
        L_0x0037:
            r9 = move-exception
            goto L_0x00d4
        L_0x003a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0042:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x008a
        L_0x004f:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchEngramStat] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007d
            java.lang.Integer r9 = new java.lang.Integer
            r9.<init>(r4)
            return r9
        L_0x007d:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x008a
            goto L_0x00d2
        L_0x008a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00ea
            r1.L$0 = r9     // Catch:{ Exception -> 0x0037 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0037 }
            r1.label = r5     // Catch:{ Exception -> 0x0037 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0037 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0037 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0037 }
            r11.r()     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0037 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0037 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0037 }
            r3.append(r0)     // Catch:{ Exception -> 0x0037 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0037 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0037 }
            if (r0 == 0) goto L_0x00cc
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchEngramStatOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchEngramStatOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchEngramStatOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0037 }
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createCountResponse$1 r9 = r9.createCountResponse(r11)     // Catch:{ Exception -> 0x0037 }
            r0.searchEngramStatV3(r10, r9)     // Catch:{ Exception -> 0x0037 }
        L_0x00cc:
            java.lang.Object r9 = r11.q()     // Catch:{ Exception -> 0x0037 }
            if (r9 != r2) goto L_0x00d3
        L_0x00d2:
            return r2
        L_0x00d3:
            return r9
        L_0x00d4:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchEngramStat: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            java.lang.Integer r9 = new java.lang.Integer
            r9.<init>(r4)
            return r9
        L_0x00ea:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchEngramStat(com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchGraphPath(com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchGraphPath$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchGraphPath$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchGraphPath$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchGraphPath$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchGraphPath$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00fa
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchGraphPath] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x010b
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchGraphOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchGraphOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchGraphOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "GraphPathNodeBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.searchGraphPathV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r10 = 10
            int r10 = ne.C1196n.w0(r11, r10)     // Catch:{ Exception -> 0x0039 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00e5:
            boolean r11 = r10.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r11 == 0) goto L_0x00f9
            java.lang.Object r11 = r10.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GraphPathNodeBundleWrapper) r11     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.content.GraphPathNode r11 = r11.toContent()     // Catch:{ Exception -> 0x0039 }
            r9.add(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00e5
        L_0x00f9:
            return r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchGraphPath: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x010b:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchGraphPath(com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption, qe.c):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: com.samsung.android.sdk.moneta.memory.option.EngramSearchOption} */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0084, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        if (r11 == r2) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008f A[SYNTHETIC, Splitter:B:24:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchPerson(com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r10, qe.C1227c r11) {
        /*
            r9 = this;
            java.lang.String r0 = "memorySearchService : "
            boolean r1 = r11 instanceof com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchPerson$1
            if (r1 == 0) goto L_0x0015
            r1 = r11
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchPerson$1 r1 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchPerson$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchPerson$1 r1 = new com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$searchPerson$1
            r1.<init>(r9, r11)
        L_0x001a:
            java.lang.Object r11 = r1.result
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.label
            ne.t r4 = ne.C1202t.d
            r5 = 2
            r6 = 1
            java.lang.String r7 = "Memory-MemorySearchServiceImpl"
            if (r3 == 0) goto L_0x0051
            if (r3 == r6) goto L_0x0044
            if (r3 != r5) goto L_0x003c
            java.lang.Object r9 = r1.L$1
            com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r9 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchOption) r9
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00d2
        L_0x0039:
            r9 = move-exception
            goto L_0x00fa
        L_0x003c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0044:
            java.lang.Object r9 = r1.L$1
            r10 = r9
            com.samsung.android.sdk.moneta.memory.option.EngramSearchOption r10 = (com.samsung.android.sdk.moneta.memory.option.EngramSearchOption) r10
            java.lang.Object r9 = r1.L$0
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl r9 = (com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl) r9
            L2.a.A(r11)
            goto L_0x0087
        L_0x0051:
            L2.a.A(r11)
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "[searchPerson] in options: "
            r3.<init>(r8)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r11.i$pde_sdk_1_0_40_release(r7, r3)
            java.lang.String r11 = r10.getKeywords()
            java.lang.CharSequence r11 = Tf.n.R0(r11)
            java.lang.String r11 = r11.toString()
            int r11 = r11.length()
            if (r11 != 0) goto L_0x007a
            return r4
        L_0x007a:
            r1.L$0 = r9
            r1.L$1 = r10
            r1.label = r6
            java.lang.Object r11 = r9.bindService(r1)
            if (r11 != r2) goto L_0x0087
            goto L_0x00d1
        L_0x0087:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x010b
            r1.L$0 = r9     // Catch:{ Exception -> 0x0039 }
            r1.L$1 = r10     // Catch:{ Exception -> 0x0039 }
            r1.label = r5     // Catch:{ Exception -> 0x0039 }
            Vf.l r11 = new Vf.l     // Catch:{ Exception -> 0x0039 }
            qe.c r1 = L1.d.m(r1)     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r6, r1)     // Catch:{ Exception -> 0x0039 }
            r11.r()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            r3.append(r0)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0039 }
            r1.i$pde_sdk_1_0_40_release(r7, r0)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.IMemorySearchService r0 = r9.memorySearchService     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x00cb
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper$Companion r1 = com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper.Companion     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchOptionBundleWrapper r10 = r1.fromOption(r10)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "PersonBundleWrapper"
            com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl$createSharedMemoryResponse$1 r9 = r9.createSharedMemoryResponse(r11, r1)     // Catch:{ Exception -> 0x0039 }
            r0.searchPersonV3(r10, r9)     // Catch:{ Exception -> 0x0039 }
        L_0x00cb:
            java.lang.Object r11 = r11.q()     // Catch:{ Exception -> 0x0039 }
            if (r11 != r2) goto L_0x00d2
        L_0x00d1:
            return r2
        L_0x00d2:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0039 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0039 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x0039 }
            r10 = 10
            int r10 = ne.C1196n.w0(r11, r10)     // Catch:{ Exception -> 0x0039 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ Exception -> 0x0039 }
        L_0x00e5:
            boolean r11 = r10.hasNext()     // Catch:{ Exception -> 0x0039 }
            if (r11 == 0) goto L_0x00f9
            java.lang.Object r11 = r10.next()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper r11 = (com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper) r11     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.sdk.moneta.memory.entity.context.Person r11 = r11.toContext()     // Catch:{ Exception -> 0x0039 }
            r9.add(r11)     // Catch:{ Exception -> 0x0039 }
            goto L_0x00e5
        L_0x00f9:
            return r9
        L_0x00fa:
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r9 = He.F.P(r9)
            java.lang.String r11 = "searchPerson: Exception raised.\n "
            java.lang.String r9 = r11.concat(r9)
            r10.e$pde_sdk_1_0_40_release(r7, r9)
            return r4
        L_0x010b:
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r10 = "bind service fail"
            java.lang.IllegalStateException r9 = c0.C0086a.e(r9, r7, r10, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.memory.service.MemorySearchServiceImpl.searchPerson(com.samsung.android.sdk.moneta.memory.option.EngramSearchOption, qe.c):java.lang.Object");
    }
}
