package com.samsung.android.sdk.scs.ai.visual.c2pa;

import Bd.C0725a;
import Tf.i;
import Tf.v;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.motionphoto.utils.ex.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1200r;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\b\u0018\u0000 R2\u00020\u0001:\u0001RB#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J=\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0018\u0010\u0011J\r\u0010\u0019\u001a\u00020\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u0013¢\u0006\u0004\b\u001c\u0010\u001aJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u0011J\u0017\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\"\u0010!J\u000f\u0010#\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b#\u0010!J\u0013\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b$\u0010\u0011J!\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00020%2\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b'\u0010(J\u0019\u0010)\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00020%¢\u0006\u0004\b)\u0010*J\u001b\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u000e2\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b,\u0010-J\u001b\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b.\u0010-J\r\u00100\u001a\u00020/¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b2\u00103J\u001c\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b4\u0010*J0\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b7\u00103J\u0010\u00109\u001a\u000208HÖ\u0001¢\u0006\u0004\b9\u0010:J\u001a\u0010<\u001a\u00020\u00132\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b<\u0010=J\u001f\u0010?\u001a\u0004\u0018\u00010\u000f2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\b?\u0010@J\u001f\u0010A\u001a\u0004\u0018\u00010\u000f2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\bA\u0010@J\u000f\u0010B\u001a\u00020\u0002H\u0002¢\u0006\u0004\bB\u00103J\u001d\u0010E\u001a\u00020\u00132\f\u0010D\u001a\b\u0012\u0004\u0012\u00020C0\u000eH\u0002¢\u0006\u0004\bE\u0010FJ\u0017\u0010E\u001a\u00020\u00132\u0006\u0010H\u001a\u00020GH\u0002¢\u0006\u0004\bE\u0010IJ\u0017\u0010J\u001a\u00020/2\u0006\u0010\t\u001a\u00020\u0002H\u0002¢\u0006\u0004\bJ\u0010KJ#\u0010L\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\bL\u0010MR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010N\u001a\u0004\bO\u00103R#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010P\u001a\u0004\bQ\u0010*¨\u0006S"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifestList;", "", "", "activeManifest", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifest;", "manifests", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "manifestKey", "getSingleTreeC2paManifestList", "(Ljava/lang/String;)Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifestList;", "getSingleTreeC2paManifestListLatest", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifestList;", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "getAllActions", "()Ljava/util/List;", "actionType", "", "isAi", "isValid", "getFilteredActions", "(Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;)Ljava/util/List;", "getAllValidActions", "isAiGenerated", "()Z", "isEnhanced", "isEdited", "getManifestKeys", "getManifest", "(Ljava/lang/String;)Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifest;", "getLatestAiAction", "()Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "getLatestEditAction", "getSourceAction", "getAllEditActions", "", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Exif;", "getExif", "(Ljava/lang/String;)Ljava/util/Map;", "getExifFromSource", "()Ljava/util/Map;", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/IngredientManifestInfo;", "getIngredientManifestInfo", "(Ljava/lang/String;)Ljava/util/List;", "getActionsFromManifestKey", "Lme/x;", "calculateValidation", "()V", "component1", "()Ljava/lang/String;", "component2", "copy", "(Ljava/lang/String;Ljava/util/Map;)Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifestList;", "toString", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "actions", "getOldestAction", "(Ljava/util/List;)Lcom/samsung/android/sdk/scs/ai/visual/c2pa/Action;", "getLatestAction", "getRootParentManifestKey", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationStatus;", "validationStatusList", "checkInvalid", "(Ljava/util/List;)Z", "Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;", "validationResult", "(Lcom/samsung/android/sdk/scs/ai/visual/c2pa/ValidationResults;)Z", "setValidationStatus", "(Ljava/lang/String;)V", "sortedByManifest", "(Ljava/util/List;)Ljava/util/List;", "Ljava/lang/String;", "getActiveManifest", "Ljava/util/Map;", "getManifests", "Companion", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C2paManifestList {
    public static final Companion Companion = new Companion((e) null);
    public static final String EXIF_LABEL = "stds.exif";
    public static final String METADATA_LABEL = "c2pa.metadata";
    public static final String PARENT_RELATION = "parentOf";
    public static final String UNKNOWN_TIME = "1970-01-01T00:00:00+00:00";
    public static final String UNKNOWN_VALUE = "Unknown";
    private final String activeManifest;
    private final Map<String, C2paManifest> manifests;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paManifestList$Companion;", "", "<init>", "()V", "UNKNOWN_VALUE", "", "UNKNOWN_TIME", "EXIF_LABEL", "METADATA_LABEL", "PARENT_RELATION", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public C2paManifestList(String str, Map<String, C2paManifest> map) {
        j.e(str, "activeManifest");
        j.e(map, "manifests");
        this.activeManifest = str;
        this.manifests = map;
    }

    private final boolean checkInvalid(List<ValidationStatus> list) {
        C2paSoVersionInfo c2paSoVersionInfo;
        if (((Integer) Optional.ofNullable(SemFloatingFeature.getInstance()).map(new b(3, new Object())).orElse(-1)).intValue() >= 20261) {
            c2paSoVersionInfo = C2paSoVersionInfo.V2;
        } else {
            c2paSoVersionInfo = C2paSoVersionInfo.V1;
        }
        if (list.isEmpty()) {
            return false;
        }
        return C2paError.Companion.checkInvalid(C1194l.R0(list, "::", (String) null, (String) null, new C0725a(28), 30), c2paSoVersionInfo);
    }

    /* access modifiers changed from: private */
    public static final Integer checkInvalid$lambda$20(SemFloatingFeature semFloatingFeature) {
        return Integer.valueOf(semFloatingFeature.getInt("SEC_FLOATING_FEATURE_COMMON_CONFIG_AI_VERSION"));
    }

    /* access modifiers changed from: private */
    public static final Integer checkInvalid$lambda$21(Ae.b bVar, Object obj) {
        return (Integer) bVar.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final CharSequence checkInvalid$lambda$22(ValidationStatus validationStatus) {
        j.e(validationStatus, "it");
        return String.valueOf(validationStatus.getCode());
    }

    public static /* synthetic */ C2paManifestList copy$default(C2paManifestList c2paManifestList, String str, Map<String, C2paManifest> map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = c2paManifestList.activeManifest;
        }
        if ((i2 & 2) != 0) {
            map = c2paManifestList.manifests;
        }
        return c2paManifestList.copy(str, map);
    }

    public static /* synthetic */ List getFilteredActions$default(C2paManifestList c2paManifestList, List list, Boolean bool, Boolean bool2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = null;
        }
        if ((i2 & 2) != 0) {
            bool = null;
        }
        if ((i2 & 4) != 0) {
            bool2 = null;
        }
        return c2paManifestList.getFilteredActions(list, bool, bool2);
    }

    private final Action getLatestAction(List<Action> list) {
        return (Action) C1194l.N0(sortedByManifest(list));
    }

    private final Action getOldestAction(List<Action> list) {
        return (Action) C1194l.N0(sortedByManifest(list));
    }

    private final String getRootParentManifestKey() {
        List<Ingredients> list;
        String str = this.activeManifest;
        while (true) {
            for (boolean z = true; z; z = false) {
                C2paManifest c2paManifest = this.manifests.get(str);
                if (c2paManifest == null || (list = c2paManifest.getIngredients()) == null) {
                    list = C1202t.d;
                }
                for (Ingredients ingredients : list) {
                    if (j.a(ingredients.getRelationship(), PARENT_RELATION) && ingredients.getActiveManifest() != null) {
                        str = ingredients.getActiveManifest();
                    }
                }
            }
            return str;
        }
    }

    public static /* synthetic */ C2paManifestList getSingleTreeC2paManifestList$default(C2paManifestList c2paManifestList, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = c2paManifestList.activeManifest;
        }
        return c2paManifestList.getSingleTreeC2paManifestList(str);
    }

    private final void setValidationStatus(String str) {
        List<Ingredients> list;
        C2paManifest c2paManifest = this.manifests.get(str);
        if (c2paManifest != null) {
            c2paManifest.setInvalid(true);
            C2paManifest c2paManifest2 = this.manifests.get(str);
            if (c2paManifest2 != null) {
                list = c2paManifest2.getIngredients();
            } else {
                list = null;
            }
            if (list != null) {
                for (Ingredients activeManifest2 : list) {
                    String activeManifest3 = activeManifest2.getActiveManifest();
                    if (activeManifest3 != null) {
                        setValidationStatus(activeManifest3);
                    }
                }
            }
        }
    }

    private final List<Action> sortedByManifest(List<Action> list) {
        ArrayList arrayList = new ArrayList();
        j.e(list, "<this>");
        sortedByManifest$preOrderSearch(arrayList, new i(1, list), this, this.activeManifest);
        return arrayList;
    }

    private static final void sortedByManifest$preOrderSearch(List<Action> list, List<Action> list2, C2paManifestList c2paManifestList, String str) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list2) {
            if (j.a(((Action) next).getActiveManifest(), str)) {
                arrayList.add(next);
            }
        }
        list.addAll(arrayList);
        for (IngredientManifestInfo manifestKey : c2paManifestList.getIngredientManifestInfo(str)) {
            sortedByManifest$preOrderSearch(list, list2, c2paManifestList, manifestKey.getManifestKey());
        }
    }

    public final void calculateValidation() {
        List<Ingredients> list;
        String activeManifest2;
        ValidationResults validationResults;
        for (String str : getManifestKeys()) {
            C2paManifest c2paManifest = this.manifests.get(str);
            if (c2paManifest != null) {
                list = c2paManifest.getIngredients();
            } else {
                list = null;
            }
            if (list != null) {
                for (Ingredients next : list) {
                    List<ValidationStatus> validationStatus = next.getValidationStatus();
                    if (((validationStatus != null && checkInvalid(validationStatus)) || ((validationResults = next.getValidationResults()) != null && checkInvalid(validationResults))) && (activeManifest2 = next.getActiveManifest()) != null) {
                        setValidationStatus(activeManifest2);
                    }
                }
            }
        }
    }

    public final String component1() {
        return this.activeManifest;
    }

    public final Map<String, C2paManifest> component2() {
        return this.manifests;
    }

    public final C2paManifestList copy(String str, Map<String, C2paManifest> map) {
        j.e(str, "activeManifest");
        j.e(map, "manifests");
        return new C2paManifestList(str, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2paManifestList)) {
            return false;
        }
        C2paManifestList c2paManifestList = (C2paManifestList) obj;
        if (j.a(this.activeManifest, c2paManifestList.activeManifest) && j.a(this.manifests, c2paManifestList.manifests)) {
            return true;
        }
        return false;
    }

    public final List<Action> getActionsFromManifestKey(String str) {
        j.e(str, "manifestKey");
        ArrayList arrayList = new ArrayList();
        for (Object next : getAllActions()) {
            if (j.a(((Action) next).getActiveManifest(), str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final String getActiveManifest() {
        return this.activeManifest;
    }

    public final List<Action> getAllActions() {
        List<Action> list;
        for (Map.Entry next : this.manifests.entrySet()) {
            String str = (String) next.getKey();
            List<C2paAssertion> assertions = ((C2paManifest) next.getValue()).getAssertions();
            C1202t tVar = C1202t.d;
            if (assertions == null) {
                assertions = tVar;
            }
            for (C2paAssertion data : assertions) {
                Data data2 = data.getData();
                if (data2 == null || (list = data2.getActions()) == null) {
                    list = tVar;
                }
                for (Action activeManifest2 : list) {
                    activeManifest2.setActiveManifest(str);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (C2paManifest actions : this.manifests.values()) {
            C1200r.A0(actions.getActions(), arrayList);
        }
        return sortedByManifest(C1194l.k1(arrayList));
    }

    public final List<Action> getAllEditActions() {
        ArrayList m12 = C1194l.m1(getAllValidActions());
        ArrayList arrayList = new ArrayList();
        Iterator it = m12.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!j.a((Action) next, getSourceAction())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<Action> getAllValidActions() {
        return getFilteredActions((List<String>) null, (Boolean) null, Boolean.TRUE);
    }

    public final Map<Exif, String> getExif(String str) {
        JsonArray assertionsJsonArray;
        T t;
        JsonElement jsonElement;
        String asString;
        j.e(str, "manifestKey");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        C2paManifest manifest = getManifest(str);
        if (!(manifest == null || (assertionsJsonArray = manifest.getAssertionsJsonArray()) == null)) {
            Iterator<JsonElement> it = assertionsJsonArray.iterator();
            j.d(it, "iterator(...)");
            while (it.hasNext()) {
                JsonObject asJsonObject = it.next().getAsJsonObject();
                if (C1195m.q0(EXIF_LABEL, METADATA_LABEL).contains(asJsonObject.get("label").getAsString())) {
                    JsonObject asJsonObject2 = asJsonObject.get("data").getAsJsonObject();
                    for (Exif exif : Exif.values()) {
                        Set<String> keySet = asJsonObject2.keySet();
                        j.d(keySet, "keySet(...)");
                        if (!keySet.isEmpty()) {
                            Iterator<T> it2 = keySet.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                } else if (v.p0((String) it2.next(), exif.getStr(), true)) {
                                    Set<String> keySet2 = asJsonObject2.keySet();
                                    j.d(keySet2, "keySet(...)");
                                    Iterator<T> it3 = keySet2.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            t = null;
                                            break;
                                        }
                                        t = it3.next();
                                        if (v.p0((String) t, exif.getStr(), true)) {
                                            break;
                                        }
                                    }
                                    String str2 = (String) t;
                                    if (str2 != null && (jsonElement = asJsonObject2.get(str2)) != null && (asString = jsonElement.getAsString()) != null) {
                                        linkedHashMap.put(exif, asString);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public final Map<Exif, String> getExifFromSource() {
        return getExif(getRootParentManifestKey());
    }

    public final List<Action> getFilteredActions(List<String> list, Boolean bool, Boolean bool2) {
        ArrayList arrayList;
        ArrayList allActions = getAllActions();
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object next : allActions) {
                if (C1194l.G0(list, ((Action) next).getAction())) {
                    arrayList2.add(next);
                }
            }
            allActions = arrayList2;
        }
        if (bool != null) {
            if (bool.booleanValue()) {
                arrayList = new ArrayList();
                for (Object next2 : allActions) {
                    if (((Action) next2).isAiGenerated()) {
                        arrayList.add(next2);
                    }
                }
            } else {
                arrayList = new ArrayList();
                for (Object next3 : allActions) {
                    if (!((Action) next3).isAiGenerated()) {
                        arrayList.add(next3);
                    }
                }
            }
            allActions = arrayList;
        }
        if (bool2 == null) {
            return allActions;
        }
        if (bool2.booleanValue()) {
            ArrayList arrayList3 = new ArrayList();
            for (Object next4 : allActions) {
                if (j.a(((Action) next4).isInvalid(), Boolean.FALSE)) {
                    arrayList3.add(next4);
                }
            }
            return arrayList3;
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object next5 : allActions) {
            if (j.a(((Action) next5).isInvalid(), Boolean.TRUE)) {
                arrayList4.add(next5);
            }
        }
        return arrayList4;
    }

    public final List<IngredientManifestInfo> getIngredientManifestInfo(String str) {
        List<Ingredients> list;
        j.e(str, "manifestKey");
        ArrayList arrayList = new ArrayList();
        C2paManifest c2paManifest = this.manifests.get(str);
        if (c2paManifest == null || (list = c2paManifest.getIngredients()) == null) {
            list = C1202t.d;
        }
        for (Ingredients ingredients : list) {
            String activeManifest2 = ingredients.getActiveManifest();
            boolean a7 = j.a(ingredients.getRelationship(), PARENT_RELATION);
            if (activeManifest2 != null) {
                if (a7) {
                    arrayList.add(0, new IngredientManifestInfo(activeManifest2, a7));
                } else {
                    arrayList.add(new IngredientManifestInfo(activeManifest2, a7));
                }
            }
        }
        return arrayList;
    }

    public final Action getLatestAiAction() {
        Boolean bool = Boolean.TRUE;
        return getLatestAction(getFilteredActions((List<String>) null, bool, bool));
    }

    public final Action getLatestEditAction() {
        return (Action) C1194l.U0(getAllEditActions());
    }

    public final C2paManifest getManifest(String str) {
        j.e(str, "manifestKey");
        return this.manifests.get(str);
    }

    public final List<String> getManifestKeys() {
        return C1194l.k1(this.manifests.keySet());
    }

    public final Map<String, C2paManifest> getManifests() {
        return this.manifests;
    }

    public final C2paManifestList getSingleTreeC2paManifestList(String str) {
        j.e(str, "manifestKey");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String str2 = str;
        while (true) {
            C2paManifest manifest = getManifest(str2);
            if (manifest == null) {
                break;
            }
            linkedHashMap.put(str2, manifest);
            List<IngredientManifestInfo> ingredientManifestInfo = getIngredientManifestInfo(str2);
            if (ingredientManifestInfo.size() != 1) {
                break;
            }
            str2 = ingredientManifestInfo.get(0).getManifestKey();
        }
        return new C2paManifestList(str, linkedHashMap);
    }

    public final C2paManifestList getSingleTreeC2paManifestListLatest() {
        return getSingleTreeC2paManifestList(this.activeManifest);
    }

    public final Action getSourceAction() {
        String rootParentManifestKey = getRootParentManifestKey();
        for (Action next : getFilteredActions(C0246a.e0(C2paAction.C2PA_CREATED.getStr()), (Boolean) null, Boolean.TRUE)) {
            if (j.a(next.getActiveManifest(), rootParentManifestKey)) {
                return next;
            }
        }
        return null;
    }

    public int hashCode() {
        return this.manifests.hashCode() + (this.activeManifest.hashCode() * 31);
    }

    public final boolean isAiGenerated() {
        Iterable<Action> allValidActions = getAllValidActions();
        if ((allValidActions instanceof Collection) && ((Collection) allValidActions).isEmpty()) {
            return false;
        }
        for (Action isAiGenerated : allValidActions) {
            if (isAiGenerated.isAiGenerated()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isEdited() {
        Iterable<Action> allValidActions = getAllValidActions();
        if ((allValidActions instanceof Collection) && ((Collection) allValidActions).isEmpty()) {
            return false;
        }
        for (Action isEdited : allValidActions) {
            if (isEdited.isEdited()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isEnhanced() {
        Iterable<Action> allValidActions = getAllValidActions();
        if ((allValidActions instanceof Collection) && ((Collection) allValidActions).isEmpty()) {
            return false;
        }
        for (Action isEnhanced : allValidActions) {
            if (isEnhanced.isEnhanced()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String str = this.activeManifest;
        Map<String, C2paManifest> map = this.manifests;
        return "C2paManifestList(activeManifest=" + str + ", manifests=" + map + ")";
    }

    private final boolean checkInvalid(ValidationResults validationResults) {
        ValidationResultMap activeManifest2 = validationResults.getActiveManifest();
        Collection failure = activeManifest2 != null ? activeManifest2.getFailure() : null;
        if (failure == null || failure.isEmpty()) {
            return false;
        }
        ValidationResultMap activeManifest3 = validationResults.getActiveManifest();
        j.b(activeManifest3);
        List<ValidationStatus> failure2 = activeManifest3.getFailure();
        j.b(failure2);
        return checkInvalid(failure2);
    }
}
