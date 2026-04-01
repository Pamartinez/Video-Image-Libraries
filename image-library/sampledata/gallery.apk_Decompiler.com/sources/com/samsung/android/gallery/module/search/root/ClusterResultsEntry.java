package com.samsung.android.gallery.module.search.root;

import A4.I;
import Ad.C0720a;
import B8.d;
import C3.C0392b;
import D6.a;
import android.text.TextUtils;
import ba.C0582a;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultsEntry {
    private LinkedHashMap<String, ArrayList<ClusterResultsEntity>> mClusterInfoMap = new LinkedHashMap<>();
    private final ArrayList<ClusterResultsEntity> mEntry = new ArrayList<>();
    private boolean mHasCarouselData;
    ArrayList<ClusterResultsEntity> mPdcInfoList = new ArrayList<>();
    private String mPdcToken;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Extractor {
        /* access modifiers changed from: private */
        public LinkedHashMap<String, ArrayList<ClusterResultsEntity>> clusterInfoMap = new LinkedHashMap<>();
        /* access modifiers changed from: private */
        public final LinkedHashMap<String, ClusterResultsEntity> entire = new LinkedHashMap<>();
        Comparator<ClusterResultsEntity> mComparator = new a(17);
        /* access modifiers changed from: private */
        public boolean mHasCarouselData;
        private String mParsingField = null;
        private final int mPdcMax = 3;
        PriorityQueue<ClusterResultsEntity> mPdcPQ = new PriorityQueue<>(this.mComparator);
        /* access modifiers changed from: private */
        public String mPdcToken;

        private void addEntity(String str, String str2, String str3, int i2) {
            if (!isExcludedCase(str, str2)) {
                String entityKey = getEntityKey(str, str2);
                this.entire.put(entityKey, getClusterResultsEntity(str, str2, str3, i2, entityKey));
            }
        }

        private void addPDCEntity() {
            if (isPdcEnabled()) {
                int min = Math.min(this.mPdcPQ.size(), 3);
                int i2 = 0;
                while (!this.mPdcPQ.isEmpty()) {
                    ClusterResultsEntity poll = this.mPdcPQ.poll();
                    if (i2 < min && poll != null) {
                        this.entire.put(getEntityKey(poll.getCategory(), Integer.toString(i2)), poll);
                        i2++;
                    }
                }
            }
        }

        private void addPdcInPQ(String str, String str2, String str3, int i2, String str4, long[] jArr) {
            ClusterResultsEntity clusterResultsEntity = getClusterResultsEntity(str, str2, str3, i2, getEntityKey(str, str2));
            clusterResultsEntity.addPdcId(str4);
            clusterResultsEntity.addTimeInfo(jArr);
            clusterResultsEntity.addPdcToken(this.mPdcToken);
            this.mPdcPQ.add(clusterResultsEntity);
        }

        private void addTopRecommendToClusterResult(ArrayList<String> arrayList) {
            addEntity("top_result", "top_result", arrayList, 1);
        }

        private void extractInternal(Object obj) {
            extractOnScsSearch(obj);
        }

        private void extractOnScsSearch(Object obj) {
            Object obj2;
            Object obj3;
            Object[] objArr = (Object[]) obj;
            String str = (String) objArr[0];
            if (isPdcEnabled() && objArr.length > 3 && (obj3 = objArr[3]) != null) {
                extractPDCToken((String) obj3);
            }
            try {
                if (!TextUtils.isEmpty(str)) {
                    JSONObject jSONObject = new JSONObject(str).getJSONObject("buckets");
                    this.mHasCarouselData = false;
                    Iterator<String> it = IntelligentSearchIndexField.CLUSTER_RESULT_FIELDS.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (jSONObject.has(next)) {
                            String str2 = this.mParsingField;
                            if (str2 != null) {
                                if (str2.equals(next)) {
                                }
                            }
                            boolean parseClusterResults = parseClusterResults(jSONObject.getJSONArray(next), next);
                            if (!this.mHasCarouselData && parseClusterResults) {
                                this.mHasCarouselData = true;
                            }
                        }
                    }
                    addPDCEntity();
                } else {
                    Log.sw("Extractor", "clusters data is empty");
                }
            } catch (JSONException e) {
                Log.se("Extractor", e.toString());
            }
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH && (obj2 = objArr[1]) != null) {
                addTopRecommendToClusterResult((ArrayList) obj2);
            }
            printClusterEntry();
            setAllEntityMap();
        }

        private void extractPDCToken(String str) {
            try {
                this.mPdcToken = new JSONObject(str).getString("token");
            } catch (Exception e) {
                Log.se("Extractor", e.toString());
            }
        }

        private ClusterResultsEntity getClusterResultsEntity(String str, String str2, String str3, int i2, String str4) {
            ClusterResultsEntity orDefault = this.entire.getOrDefault(str4, new ClusterResultsEntity(str2));
            orDefault.addCategory(str);
            orDefault.addRawLabel(str2);
            orDefault.sumCount(i2);
            orDefault.addId(str3);
            return orDefault;
        }

        private String getEntityKey(String str, String str2) {
            return C0212a.B(str2, "_", str);
        }

        private long[] getTimeRange(JSONObject jSONObject) {
            try {
                JSONObject jSONObject2 = new JSONArray(jSONObject.getString("contextTimeList")).getJSONObject(0);
                long j2 = jSONObject2.getLong("endTimestamp");
                return new long[]{jSONObject2.getLong("startTimestamp"), j2};
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private boolean ignoreLocationField(boolean z, String str) {
            if (z) {
                return false;
            }
            if (Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
                return "poitag".equals(str);
            }
            if ("facet_location".equals(str) || "poitag".equals(str)) {
                return true;
            }
            return false;
        }

        private boolean ignorePdcField(String str) {
            if (isPdcEnabled() || !"pdc_cluster".equals(str)) {
                return false;
            }
            return true;
        }

        private boolean isExcludedCase(String str, String str2) {
            return isTopLevelScene(str, str2);
        }

        private boolean isPdcEnabled() {
            if (!Features.isEnabled(Features.SUPPORT_PDC_CLUSTER) || !SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
                return false;
            }
            return true;
        }

        private boolean isTopLevelScene(String str, String str2) {
            if (!"scenetag".equals(str) || !CategoryType.TOP_LEVEL_SCENE_TAG.stream().anyMatch(new C0392b(str2, 15))) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$new$2(ClusterResultsEntity clusterResultsEntity, ClusterResultsEntity clusterResultsEntity2) {
            if (clusterResultsEntity.getCount() == clusterResultsEntity2.getCount()) {
                return clusterResultsEntity.getRawLabels().get(0).compareTo(clusterResultsEntity2.getRawLabels().get(0));
            }
            return Integer.compare(clusterResultsEntity2.getCount(), clusterResultsEntity.getCount());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ArrayList lambda$setAllEntityMap$0(String str) {
            return new ArrayList();
        }

        private boolean parseClusterResults(JSONArray jSONArray, String str) {
            String str2;
            Extractor extractor;
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
            int i2 = 0;
            boolean z = false;
            while (i2 < jSONArray.length()) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String string = jSONObject.getString("name");
                String string2 = jSONObject.getString("ids");
                int i7 = UnsafeCast.toInt(jSONObject.getString("count"), 0);
                if (!this.ignoreLocationField(isEnabled, str) && !this.ignorePdcField(str)) {
                    if (!ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE.equals(str)) {
                        z = true;
                    }
                    if (!"recommended_id".equals(str)) {
                        if ("pdc_cluster".equals(str)) {
                            extractor = this;
                            str2 = str;
                            extractor.addPdcInPQ(str2, string, string2.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)[0].replace("[", "").replace("]", ""), i7, (String) null, this.getTimeRange(jSONObject));
                        } else {
                            extractor = this;
                            str2 = str;
                            extractor.addEntity(str2, string, string2, i7);
                        }
                        i2++;
                        this = extractor;
                        str = str2;
                    } else if (i7 >= 5) {
                        this.addEntity(str, string, string2, i7);
                    }
                }
                extractor = this;
                str2 = str;
                i2++;
                this = extractor;
                str = str2;
            }
            return z;
        }

        private void printClusterEntry() {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry<String, ClusterResultsEntity> value : this.entire.entrySet()) {
                sb2.append(value.getValue() + GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            Log.s("Extractor", "printClusterEntities: " + Logger.getEncodedString(sb2.toString()));
        }

        private void setAllEntityMap() {
            if (this.entire.size() > 1) {
                for (Map.Entry next : this.entire.entrySet()) {
                    this.clusterInfoMap.computeIfAbsent(((ClusterResultsEntity) next.getValue()).getCategory(), new V8.a(23)).add((ClusterResultsEntity) next.getValue());
                }
            }
        }

        public ClusterResultsEntry build() {
            return new ClusterResultsEntry(this);
        }

        public Extractor extract(Object obj) {
            return extract(obj, (String) null);
        }

        public Extractor extract(Object obj, String str) {
            this.mParsingField = str;
            extractInternal(obj);
            return this;
        }

        private void addEntity(String str, String str2, ArrayList<String> arrayList, int i2) {
            String entityKey = getEntityKey(str, str2);
            ClusterResultsEntity orDefault = this.entire.getOrDefault(entityKey, new ClusterResultsEntity(str2));
            orDefault.addCategory(str);
            orDefault.setRawLabel(arrayList);
            orDefault.sumCount(i2);
            this.entire.put(entityKey, orDefault);
        }
    }

    public ClusterResultsEntry(Extractor extractor) {
        initEntity(extractor);
    }

    /* access modifiers changed from: private */
    public boolean filter(ClusterResultsEntity clusterResultsEntity) {
        if (clusterResultsEntity.getCount() > 0) {
            return true;
        }
        return false;
    }

    private ArrayList<String> getNames(String str) {
        ArrayList arrayList = this.mClusterInfoMap.get(str);
        if (arrayList != null) {
            return (ArrayList) arrayList.stream().map(new V8.a(22)).collect(Collectors.toCollection(new C0720a(1)));
        }
        return new ArrayList<>();
    }

    private void initEntity(Extractor extractor) {
        Iterator it = extractor.entire.values().stream().filter(new I(14, this)).iterator();
        ArrayList<ClusterResultsEntity> arrayList = this.mEntry;
        Objects.requireNonNull(arrayList);
        it.forEachRemaining(new d(arrayList, 26));
        this.mHasCarouselData = extractor.mHasCarouselData;
        this.mPdcToken = extractor.mPdcToken;
        this.mClusterInfoMap = extractor.clusterInfoMap;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasOcrEntityOnly$0(ClusterResultsEntity clusterResultsEntity) {
        return !clusterResultsEntity.isMediaType();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPdcList$2(ArrayList arrayList) {
        this.mPdcInfoList.addAll(arrayList);
    }

    public ArrayList<String> getAlbumNames() {
        return getNames("album_cluster");
    }

    public ArrayList<ClusterResultsEntity> getAllItems() {
        return this.mEntry;
    }

    public ClusterResultsEntity getItem(int i2) {
        return this.mEntry.get(i2);
    }

    public ArrayList<String> getLocationNames() {
        return getNames("facet_location");
    }

    public String getOcrCount() {
        ArrayList arrayList = this.mClusterInfoMap.get("ocrtext");
        if (arrayList != null) {
            return String.valueOf(((ClusterResultsEntity) arrayList.get(0)).getCount());
        }
        return "";
    }

    public ClusterResultsEntity getOcrEntity() {
        if (isEmpty()) {
            return null;
        }
        return this.mEntry.stream().filter(new S3.d(23)).findFirst().orElse((Object) null);
    }

    public String getOcrIds() {
        ArrayList arrayList = this.mClusterInfoMap.get("ocrtext");
        if (arrayList != null) {
            return C0212a.p(new StringBuilder("\""), ((ClusterResultsEntity) arrayList.get(0)).getId().replace("[", "").replace("]", ""), "\"");
        }
        return "";
    }

    public String getOcrTitle() {
        ArrayList arrayList = this.mClusterInfoMap.get("ocrtext");
        if (arrayList != null) {
            return ((ClusterResultsEntity) arrayList.get(0)).getName();
        }
        return "";
    }

    public String getPdcCount(String str) {
        Iterator<ClusterResultsEntity> it = this.mPdcInfoList.iterator();
        while (it.hasNext()) {
            ClusterResultsEntity next = it.next();
            if (TextUtils.equals(next.getId(), str)) {
                return String.valueOf(next.getCount());
            }
        }
        return null;
    }

    public String getPdcIds() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<ClusterResultsEntity> it = this.mPdcInfoList.iterator();
        while (it.hasNext()) {
            sb2.append("\"" + it.next().getId() + "\"");
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
        return sb2.toString();
    }

    public long[] getPdcTime(String str) {
        Iterator<ClusterResultsEntity> it = this.mPdcInfoList.iterator();
        while (it.hasNext()) {
            ClusterResultsEntity next = it.next();
            if (TextUtils.equals(next.getId(), str)) {
                return next.getTimeInfo();
            }
        }
        return null;
    }

    public String getPdcTitle(String str) {
        Iterator<ClusterResultsEntity> it = this.mPdcInfoList.iterator();
        while (it.hasNext()) {
            ClusterResultsEntity next = it.next();
            if (TextUtils.equals(next.getId(), str)) {
                return next.getName();
            }
        }
        return null;
    }

    public String getPdcToken() {
        return this.mPdcToken;
    }

    public ArrayList<String> getPeopleNames() {
        return getNames("person_cluster");
    }

    public ArrayList<String> getPetNames() {
        return getNames("pet_cluster");
    }

    public ArrayList<String> getTagNames() {
        return getNames("usertag");
    }

    public boolean hasCarouselData() {
        return this.mHasCarouselData;
    }

    public boolean hasOcrEntity() {
        return this.mEntry.stream().anyMatch(new S3.d(23));
    }

    public boolean hasOcrEntityOnly() {
        if (isEmpty() || isOnlyMediaTypeEntity() || !this.mEntry.stream().filter(new S3.d(22)).allMatch(new S3.d(23))) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return this.mEntry.isEmpty();
    }

    public boolean isOnlyMediaTypeEntity() {
        return this.mEntry.stream().allMatch(new S3.d(21));
    }

    public void setPdcList() {
        this.mPdcInfoList.clear();
        Optional.ofNullable(this.mClusterInfoMap.get("pdc_cluster")).ifPresent(new C0582a(0, this));
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ClusterResultsEntry >> \n");
        Iterator<ClusterResultsEntity> it = this.mEntry.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().toString());
            sb2.append("\n");
        }
        return sb2.toString();
    }
}
