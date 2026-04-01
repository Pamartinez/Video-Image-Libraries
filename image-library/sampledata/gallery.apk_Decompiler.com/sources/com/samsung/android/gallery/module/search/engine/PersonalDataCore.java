package com.samsung.android.gallery.module.search.engine;

import E5.b;
import N2.j;
import Tf.w;
import V8.a;
import Vf.A;
import Vf.D;
import W9.c;
import W9.d;
import W9.e;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.PeopleApi;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.basicdomain.BasicDomainProvider;
import com.samsung.android.sdk.moneta.basicdomain.entity.Person;
import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.service.FeedbackService;
import com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendService;
import com.samsung.android.sdk.moneta.memory.MemoryProvider;
import com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption;
import com.samsung.android.sdk.moneta.memory.service.MemorySearchService;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qe.C1227c;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PersonalDataCore {
    private static final boolean SUPPORT_EVENT_FACET = Features.isEnabled(Features.SUPPORT_EVENT_FACET);
    private static final List<Long>[] TEST_RECOMMEND_ID = {List.of(100201L, 100202L, 100203L), List.of(100204L, 100205L)};
    private static int index = 0;
    private static final ConcurrentHashMap<String, PersonalDataCore> sMap = new ConcurrentHashMap<>();
    private final String TAG = getClass().getSimpleName();
    private final HashMap<String, List<Person>> mCandidateMap = new HashMap<>();
    private FeedbackService mFeedbackService;
    private MemorySearchService mMemorySearchService;
    private String mPdcToken;
    private PersonRecommendService mPersonRecommendService;

    private List<Long> extractFaceGroupIds(List<Person> list) {
        int i2;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            i2 = list.size();
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            Person person = list.get(i7);
            if (!(person == null || person.getFaceGroupID() == null)) {
                arrayList.add(person.getFaceGroupID());
                if (arrayList.size() == 3) {
                    break;
                }
            }
        }
        Log.d(this.TAG, "extractFaceGroupIds(" + arrayList + ")");
        return arrayList;
    }

    private void getCandidatePeopleBy(Context context, String str, Consumer<List<Long>> consumer) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        try {
            List list = (List) D.r(C1233i.d, new c(this, context, str));
            List<Long> extractFaceGroupIds = extractFaceGroupIds(list);
            if (!extractFaceGroupIds.isEmpty()) {
                HashMap<Long, Long> recommendedIds = new PeopleApi().getRecommendedIds(extractFaceGroupIds.stream().mapToLong(new b(1)).toArray());
                if (!recommendedIds.isEmpty()) {
                    extractFaceGroupIds.forEach(new U5.b(3, arrayList, recommendedIds));
                }
            }
            this.mCandidateMap.put(str, list);
            String str2 = this.TAG;
            Log.d(str2, "getCandidatePeopleBy(" + str + "): " + arrayList + ", +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            j.v("getCandidatePeopleBy : ", e, this.TAG);
        }
        consumer.accept(arrayList);
    }

    private FeedbackService getFeedbackService(Context context) {
        if (this.mFeedbackService == null) {
            this.mFeedbackService = BasicDomainProvider.getFeedbackService(context);
        }
        return this.mFeedbackService;
    }

    public static PersonalDataCore getInstance(Blackboard blackboard) {
        return sMap.computeIfAbsent(blackboard.getName(), new a(9));
    }

    private MemorySearchService getMemorySearchService() {
        if (this.mMemorySearchService == null) {
            this.mMemorySearchService = MemoryProvider.getMemorySearchService(AppResources.getAppContext());
        }
        return this.mMemorySearchService;
    }

    private PersonRecommendService getPersonRecommendService(Context context) {
        if (this.mPersonRecommendService == null) {
            this.mPersonRecommendService = BasicDomainProvider.getPersonRecommendService(context);
        }
        return this.mPersonRecommendService;
    }

    private RecommendationsGetOption getRecommendOptions(Bundle bundle, ArrayList<String> arrayList) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(RecommendationsGetOption.EXTRAS_BUNDLE_KEY_ORIGIN_REQUEST, bundle);
        return new RecommendationsGetOption.Builder().mediaIdList(arrayList).extras(bundle2).build();
    }

    private static List<Person> getSelectedPersonList(List<String> list, List<Person> list2) {
        ArrayList arrayList = new ArrayList();
        if (!(list2 == null || list == null)) {
            list2.forEach(new d(list, arrayList, 0));
        }
        return arrayList;
    }

    private boolean isPdcEnabled() {
        return SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$composePdcInfoForKeywordSearch$9(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        long identityId = IdentityCreatureUtil.getIdentityId(str);
        if (IdentityCreatureUtil.isAssignedCreature(str)) {
            jSONArray.put(identityId);
        } else {
            jSONArray2.put(identityId);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getCandidatePeopleBy$5(Context context, String str, A a7, C1227c cVar) {
        return getPersonRecommendService(context).getFamilyCandidates(RelationshipKeySet.PDC_RELATIONSHIP_MAP.get(str), PersonType.GALLERY, cVar);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PersonalDataCore lambda$getInstance$0(String str) {
        return new PersonalDataCore();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getRecommendMap$11(MemorySearchService memorySearchService, Bundle bundle, ArrayList arrayList, A a7, C1227c cVar) {
        return memorySearchService.getRecommendations(getRecommendOptions(bundle, arrayList), cVar);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getRecommendMap$12(Bundle bundle, String str, LinkedHashMap linkedHashMap) {
        StringBuilder sb2;
        String str2;
        String str3;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            MemorySearchService memorySearchService = getMemorySearchService();
            boolean z = false;
            if (bundle == null || TextUtils.isEmpty(str)) {
                String str4 = this.TAG;
                Boolean valueOf = Boolean.valueOf(TextUtils.isEmpty(str));
                if (bundle != null) {
                    z = true;
                }
                Log.d(str4, "getRecommendMap ignored", valueOf, Boolean.valueOf(z));
            } else {
                String[] stringArray = bundle.getStringArray("android:query-arg-sql-selection-args");
                String str5 = this.TAG;
                if (stringArray == null || stringArray.length <= 0) {
                    str3 = null;
                } else {
                    str3 = Logger.getEncodedString(stringArray[0]);
                }
                Log.d(str5, "getRecommendMap start", str3);
                List<KeywordInfo> keywords = ((GetRecommendationsResponse) D.r(C1233i.d, new e(this, memorySearchService, bundle, new ArrayList(Arrays.asList(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)))))).getKeywords();
                if (!keywords.isEmpty()) {
                    for (KeywordInfo next : keywords) {
                        linkedHashMap.put(next.getKeyword(), next.getExtra().getString(KeywordInfo.EXTRA_BUNDLE_KEY_TIME_RANGES));
                    }
                }
            }
            String str6 = this.TAG;
            Log.d(str6, "getRecommendMap complete" + Logger.vt(currentTimeMillis));
            return;
        } catch (InterruptedException e) {
            String str7 = this.TAG;
            Log.e(str7, "getRecommendMap InterruptedException = " + e.getMessage());
            str2 = this.TAG;
            sb2 = new StringBuilder("getRecommendMap complete");
        } catch (Exception e7) {
            String str8 = this.TAG;
            Log.e(str8, "getRecommendMap failed = " + e7.getMessage());
            str2 = this.TAG;
            sb2 = new StringBuilder("getRecommendMap complete");
        } catch (Throwable th) {
            String str9 = this.TAG;
            Log.d(str9, "getRecommendMap complete" + Logger.vt(currentTimeMillis));
            throw th;
        }
        sb2.append(Logger.vt(currentTimeMillis));
        Log.d(str2, sb2.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getSelectedPersonList$8(List list, List list2, Person person) {
        if (list.contains(IdentityCreatureUtil.create(person.getFaceGroupID().longValue(), -1, -1, IdentityCreatureUtil.Category.PEOPLE))) {
            list2.add(person);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCandidatePeopleBy$2(Context context, String str, LinkedHashMap linkedHashMap, String str2) {
        getCandidatePeopleBy(context, str, new U5.b(5, linkedHashMap, str2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCandidatePeopleBy$3(List list, LinkedHashMap linkedHashMap, long j2, Consumer consumer) {
        String str = this.TAG;
        Log.d(str, "loadCandidatePeopleBy" + Logger.vt(Logger.getEncodedString((Object) list), Integer.valueOf(linkedHashMap.size()), Long.valueOf(j2)) + "");
        consumer.accept(linkedHashMap);
        String str2 = this.TAG;
        SearchLog.d(str2, "PDC IN=" + list + "\nOUT=" + linkedHashMap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$prepareSearchEngine$14(A a7, C1227c cVar) {
        return getMemorySearchService().prepareSearchEngine(cVar);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareSearchEngine$15() {
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                D.r(C1233i.d, new w(1, this));
            }
        } catch (Exception e) {
            j.v("prepareSearchEngine : ", e, this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$sendRelationShipFeedback$7(Context context, String str, List list, List list2, A a7, C1227c cVar) {
        return getFeedbackService(context).sendRelationShipFeedback(RelationshipKeySet.PDC_RELATIONSHIP_MAP.get(str), list, list2, cVar);
    }

    public String composePdcInfoForKeywordSearch(String str, List<String> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "recommend_relationship");
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            list.forEach(new U5.b(4, jSONArray2, jSONArray));
            jSONObject2.put("group_ids", jSONArray);
            jSONObject2.put("person_ids", jSONArray2);
            jSONObject2.put("relationship", str);
            jSONObject.put(ActionHandler.PARAMS, jSONObject2);
            jSONObject.put("token", this.mPdcToken);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getRecommendMap(Bundle bundle, String str, Consumer<LinkedHashMap<String, String>> consumer) {
        if (SUPPORT_EVENT_FACET && Build.VERSION.SDK_INT >= 33) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LatchBuilder timeout = new LatchBuilder("PDC#getRecommendMap").setTimeout(2000);
            timeout.addWorker(new A6.a((Object) this, (Object) bundle, (Object) str, (Object) linkedHashMap, 25));
            timeout.setPostExecutor((Runnable) new U5.c(19, consumer, linkedHashMap));
            timeout.start();
        }
    }

    public void loadCandidatePeopleBy(Context context, List<String> list, Consumer<Map<String, List<Long>>> consumer, Supplier<Boolean> supplier) {
        Context context2;
        PersonalDataCore personalDataCore;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (isPdcEnabled()) {
            long currentTimeMillis = System.currentTimeMillis();
            LatchBuilder timeout = new LatchBuilder("PDC#CandidatePeopleBy").setCancelSignal(supplier).setTimeout(Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
            boolean z = false;
            for (String next : list) {
                linkedHashMap.put(next, new ArrayList());
                LinkedHashMap linkedHashMap2 = linkedHashMap;
                String relationshipTypes = RelationshipKeySet.getRelationshipTypes(next);
                if (RelationshipKeySet.PDC_RELATIONSHIP_MAP.get(relationshipTypes) != null) {
                    personalDataCore = this;
                    context2 = context;
                    timeout.addWorker(new B5.c(personalDataCore, context2, relationshipTypes, linkedHashMap2, next, 16));
                    z = true;
                } else {
                    personalDataCore = this;
                    context2 = context;
                    Log.w((CharSequence) personalDataCore.TAG, "loadCandidatePeopleBy : not matched PDC relationship!", relationshipTypes);
                }
                this = personalDataCore;
                context = context2;
                linkedHashMap = linkedHashMap2;
            }
            LinkedHashMap linkedHashMap3 = linkedHashMap;
            Consumer<Map<String, List<Long>>> consumer2 = consumer;
            LinkedHashMap linkedHashMap4 = linkedHashMap;
            timeout.setPostExecutor((Runnable) new M9.e(this, (List) list, linkedHashMap, currentTimeMillis, (Consumer) consumer2));
            if (z) {
                timeout.start();
            } else {
                consumer2.accept(linkedHashMap4);
            }
        } else {
            Consumer<Map<String, List<Long>>> consumer3 = consumer;
            LinkedHashMap linkedHashMap5 = linkedHashMap;
            Log.d(this.TAG, "loadCandidatePeopleBy : PdcSetting[off]");
            for (String put : list) {
                linkedHashMap5.put(put, new ArrayList());
            }
            consumer3.accept(linkedHashMap5);
        }
    }

    public void prepareSearchEngine() {
        if (SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
            SimpleThreadPool.getInstance().execute(new V3.b(10, this));
        }
    }

    public void release() {
        this.mPersonRecommendService = null;
        this.mFeedbackService = null;
        this.mCandidateMap.clear();
        this.mPdcToken = null;
    }

    public void sendRelationShipFeedback(Context context, String str, List<String> list) {
        PersonalDataCore personalDataCore;
        String str2;
        if (isPdcEnabled()) {
            List remove = this.mCandidateMap.remove(str);
            try {
                List<Person> selectedPersonList = getSelectedPersonList(list, remove);
                personalDataCore = this;
                str2 = str;
                try {
                    D.r(C1233i.d, new W9.b(personalDataCore, context, str2, remove, selectedPersonList));
                } catch (Exception e) {
                    e = e;
                    Log.e((CharSequence) personalDataCore.TAG, C0212a.l("sendRelationShipFeedback failed: ", str2), remove, e);
                }
            } catch (Exception e7) {
                e = e7;
                personalDataCore = this;
                str2 = str;
                Log.e((CharSequence) personalDataCore.TAG, C0212a.l("sendRelationShipFeedback failed: ", str2), remove, e);
            }
        }
    }

    public void setPdcToken(String str) {
        this.mPdcToken = str;
    }
}
