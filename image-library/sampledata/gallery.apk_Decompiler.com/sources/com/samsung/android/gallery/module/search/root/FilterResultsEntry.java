package com.samsung.android.gallery.module.search.root;

import A4.I;
import C3.C0392b;
import U5.b;
import V8.a;
import Z8.c;
import android.text.TextUtils;
import ba.C0582a;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterResultsEntry {
    /* access modifiers changed from: package-private */
    public static final ArrayList<String> ALLOW_SHOT_TYPE = new ArrayList<String>() {
        {
            add("video");
            add("selfie");
            add("portrait");
            add("motion_photo");
        }
    };
    protected final String TAG = getClass().getSimpleName();
    String mCurrentTitle;
    final ArrayList<FilterResultsEntity> mEntry = new ArrayList<>();
    boolean mIsEnableSearchToolbar;
    int mTotalCount;

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initEntry$0(FilterResultsEntity filterResultsEntity) {
        if (filterResultsEntity.isOnlyThem()) {
            return true;
        }
        if ((filterResultsEntity.getCount() <= 0 || filterResultsEntity.getCount() >= this.mTotalCount) && !filterResultsEntity.isPde()) {
            return false;
        }
        return true;
    }

    public void addEntity(FilterResultsEntity filterResultsEntity) {
        this.mEntry.add(filterResultsEntity);
    }

    public ArrayList<FilterResultsEntity> getAllItems() {
        return this.mEntry;
    }

    public int getCount() {
        return this.mEntry.size();
    }

    public FilterResultsEntity getItem(int i2) {
        return this.mEntry.get(i2);
    }

    public FilterResultsEntry initEntry(Builder builder) {
        this.mCurrentTitle = builder.currentTitle;
        this.mTotalCount = builder.mTotalCount;
        this.mIsEnableSearchToolbar = builder.mIsEnableSearchToolbar;
        builder.entire.values().stream().filter(new I(15, this)).limit((long) builder.mMaxFilterNum).iterator().forEachRemaining(new C0582a(1, this));
        if ((!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || (!this.mIsEnableSearchToolbar && !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71)) && !this.mEntry.isEmpty()) {
            this.mEntry.add(0, new FilterResultsEntity(AppResources.getString(R$string.all), "key_word"));
        }
        return this;
    }

    public boolean isEmpty() {
        return this.mEntry.isEmpty();
    }

    public boolean isEnableSearchToolbar() {
        return this.mIsEnableSearchToolbar;
    }

    public void removeEntity(FilterResultsEntity filterResultsEntity) {
        this.mEntry.remove(filterResultsEntity);
    }

    public String toString() {
        return this.TAG + ">>\n" + ((String) this.mEntry.stream().map(new a(24)).collect(Collectors.joining("\n")));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private static final Comparator<FilterResultsEntity> sCountComparator = new D6.a(20);
        private static final Comparator<FilterResultsEntity> sNameComparator = new D6.a(19);
        private final Comparator<FilterResultsEntity> countComparator = new D6.a(18);
        /* access modifiers changed from: private */
        public String currentTitle;
        /* access modifiers changed from: private */
        public final LinkedHashMap<String, FilterResultsEntity> entire = new LinkedHashMap<>();
        private final Blackboard mBlackboard;
        /* access modifiers changed from: private */
        public boolean mIsEnableSearchToolbar;
        /* access modifiers changed from: private */
        public int mMaxFilterNum;
        private Integer mMinCreatureItemCount;
        private LinkedHashMap<String, String> mPdeRecommends;
        private Comparator<FilterResultsEntity> mPrimaryComparator;
        private Comparator<FilterResultsEntity> mSecondaryComparator;
        /* access modifiers changed from: private */
        public int mTotalCount;

        public Builder(Blackboard blackboard) {
            this.mBlackboard = blackboard;
        }

        private void addEntity(String str, String str2, int i2) {
            if (!isExcludedCase(str, str2)) {
                String translatedString = TranslationManager.getInstance().getTranslatedString(str, str2);
                String entityKey = getEntityKey(str, translatedString);
                FilterResultsEntity orDefault = this.entire.getOrDefault(entityKey, new FilterResultsEntity(translatedString, getEntityFieldIcon(str, str2)));
                orDefault.addCategory(str);
                orDefault.addRawLabel(str2);
                orDefault.sumCount(i2);
                this.entire.put(entityKey, orDefault);
            }
        }

        private void addPdeEntity(String str, String str2) {
            PdeFilterResultsEntity pdeFilterResultsEntity = new PdeFilterResultsEntity(str, str2, "facet_event");
            this.entire.put(getEntityKey("facet_event", pdeFilterResultsEntity.getRawLabels()), pdeFilterResultsEntity);
        }

        private void addPdeRecommendEntity() {
            LinkedHashMap<String, String> linkedHashMap = this.mPdeRecommends;
            if (linkedHashMap != null) {
                for (Map.Entry next : linkedHashMap.entrySet()) {
                    addPdeEntity((String) next.getKey(), (String) next.getValue());
                }
            }
        }

        private void addPeopleEntity(String str, int i2) {
            PeopleFilterResultsEntity peopleFilterResultsEntity = new PeopleFilterResultsEntity(str, "recommended_id");
            peopleFilterResultsEntity.sumCount(i2);
            this.entire.put(getEntityKey("recommended_id", peopleFilterResultsEntity.getRawLabels()), peopleFilterResultsEntity);
        }

        private void addPetEntity(String str, int i2) {
            PetFilterResultsEntity petFilterResultsEntity = new PetFilterResultsEntity(str, "pet_recommended_id");
            petFilterResultsEntity.sumCount(i2);
            this.entire.put(getEntityKey("pet_recommended_id", petFilterResultsEntity.getRawLabels()), petFilterResultsEntity);
        }

        private void checkCreatureIdValidity(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            checkCreatureIdValidity(arrayList, true);
            checkCreatureIdValidity(arrayList2, false);
        }

        private void extract(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject("gallery").getJSONObject("buckets");
                String str2 = (String) this.mBlackboard.read("location://variable/currentv1", null);
                Iterator<String> it = IntelligentSearchIndexField.FACET_REQUEST_FIELDS.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (jSONObject.has(next)) {
                        JSONArray jSONArray = jSONObject.getJSONArray(next);
                        boolean z = true;
                        if (str2 != null) {
                            if (!ArgumentsUtil.getArgValue(str2, "searchToolbar", true)) {
                                z = false;
                            }
                        }
                        parseFilterResults(jSONArray, next, z);
                    }
                }
                if (Features.isEnabled(Features.SUPPORT_EVENT_FACET)) {
                    addPdeRecommendEntity();
                }
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    sortFilterResult();
                }
            } catch (JSONException e) {
                Log.se("Builder", e.toString());
            }
        }

        private IntelligentSearchIndexFieldIcon getEntityFieldIcon(String str, String str2) {
            return IntelligentSearchIndexFieldIcon.create(str, str2);
        }

        private String getEntityKey(String str, String str2) {
            return C0212a.B(str2, "_", str);
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

        private boolean ignoreSpecificShotType(String str, String str2) {
            if (PreferenceFeatures.OneUi8x.SUPPORT_GROUPING_SEARCH_FILTER) {
                str2.getClass();
                char c5 = 65535;
                switch (str2.hashCode()) {
                    case -639779342:
                        if (str2.equals("sef_file_type")) {
                            c5 = 0;
                            break;
                        }
                        break;
                    case -163871951:
                        if (str2.equals("recording_mode")) {
                            c5 = 1;
                            break;
                        }
                        break;
                    case 1939875509:
                        if (str2.equals(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE)) {
                            c5 = 2;
                            break;
                        }
                        break;
                }
                switch (c5) {
                    case 0:
                    case 1:
                    case 2:
                        return !FilterResultsEntry.ALLOW_SHOT_TYPE.contains(str);
                }
            }
            return false;
        }

        private boolean isExcludedCase(String str, String str2) {
            if (isImage(str, str2) || isTopLevelScene(str, str2)) {
                return true;
            }
            return false;
        }

        private boolean isImage(String str, String str2) {
            if (!ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE.equals(str) || !"image".equalsIgnoreCase(str2)) {
                return false;
            }
            return true;
        }

        private boolean isTopLevelScene(String str, String str2) {
            if (!"scenetag".equals(str) || !CategoryType.TOP_LEVEL_SCENE_TAG.stream().anyMatch(new C0392b(str2, 16))) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$checkCreatureIdValidity$4(boolean z, String str) {
            String str2;
            if (z) {
                str2 = "recommended_id";
            } else {
                str2 = "pet_recommended_id";
            }
            this.entire.remove(getEntityKey(str2, str));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ int lambda$sortFilterResult$3(Map.Entry entry, Map.Entry entry2) {
            return this.countComparator.compare((FilterResultsEntity) entry.getValue(), (FilterResultsEntity) entry2.getValue());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$static$1(FilterResultsEntity filterResultsEntity, FilterResultsEntity filterResultsEntity2) {
            return filterResultsEntity2.getCount() - filterResultsEntity.getCount();
        }

        private ArrayList<MediaItem> loadCreatureItemFromMediaData(ArrayList<String> arrayList, boolean z) {
            MediaData open;
            String str;
            ArrayList<MediaItem> arrayList2 = new ArrayList<>();
            try {
                open = MediaDataFactory.getInstance(this.mBlackboard).open(LocationKey.getCollectionDataKey(), true);
                if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                    str = "location://search/fileList/Category/PeopleAndPets";
                } else {
                    str = "location://search/fileList/Category/People";
                }
                MediaData childMediaData = open.getChildMediaData(str);
                if (childMediaData != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i2 = 0; i2 < childMediaData.getCount(); i2++) {
                        MediaItem read = childMediaData.read(i2);
                        if (read != null) {
                            if (z) {
                                if (read.isPeople()) {
                                }
                            } else if (!read.isPet()) {
                            }
                            arrayList.stream().filter(new C0392b(IdentityCreatureUtil.getUnifiedIdentityId(read.getSubCategory()) + "", 17)).forEach(new b(19, arrayList3, read));
                        }
                    }
                    if (!arrayList3.isEmpty()) {
                        arrayList2 = (ArrayList) arrayList3.clone();
                    }
                }
                open.close();
                return arrayList2;
            } catch (Error | Exception unused) {
                return arrayList2;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        private void parseFilterResults(JSONArray jSONArray, String str, boolean z) {
            String str2;
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String string = jSONObject.getString("key");
                if (str != null) {
                    str2 = str;
                } else {
                    str2 = jSONObject.getString("field");
                }
                int i7 = UnsafeCast.toInt(jSONObject.getString("count"), 0);
                if (!ignoreLocationField(isEnabled, str2) && !ignoreSpecificShotType(string, str2)) {
                    int i8 = 5;
                    if ("recommended_id".equals(str2)) {
                        Integer num = this.mMinCreatureItemCount;
                        if (num != null) {
                            i8 = num.intValue();
                        }
                        if (i7 >= i8) {
                            addPeopleEntity(string, i7);
                            arrayList.add(string);
                        }
                    } else if ("pet_recommended_id".equals(str2)) {
                        Integer num2 = this.mMinCreatureItemCount;
                        if (num2 != null) {
                            i8 = num2.intValue();
                        }
                        if (i7 >= i8) {
                            addPetEntity(string, i7);
                            arrayList2.add(string);
                        }
                    } else {
                        addEntity(str2, string, i7);
                    }
                }
            }
            if (z && IntelligentSearchIndexField.CREATURE_TERM.contains(str)) {
                checkCreatureIdValidity((ArrayList<String>) arrayList, (ArrayList<String>) arrayList2);
            }
        }

        private void sortFilterResult() {
            LinkedList<Map.Entry> linkedList = new LinkedList<>(this.entire.entrySet());
            linkedList.sort(new c(1, this));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : linkedList) {
                linkedHashMap.put((String) entry.getKey(), (FilterResultsEntity) entry.getValue());
            }
            this.entire.clear();
            this.entire.putAll(linkedHashMap);
        }

        public FilterResultsEntry build() {
            if (PreferenceFeatures.OneUi8x.SUPPORT_GROUPING_SEARCH_FILTER) {
                return new FilterResultsGroupEntry().initEntry(this);
            }
            return new FilterResultsEntry().initEntry(this);
        }

        public Builder setMaxFilterNum(int i2) {
            this.mMaxFilterNum = i2;
            return this;
        }

        public Builder setMinCreatureItemCount(int i2) {
            this.mMinCreatureItemCount = Integer.valueOf(i2);
            return this;
        }

        public Builder setSortByCount(boolean z) {
            Comparator<FilterResultsEntity> comparator;
            Comparator<FilterResultsEntity> comparator2;
            if (z) {
                comparator = sCountComparator;
            } else {
                comparator = sNameComparator;
            }
            this.mPrimaryComparator = comparator;
            if (z) {
                comparator2 = sNameComparator;
            } else {
                comparator2 = sCountComparator;
            }
            this.mSecondaryComparator = comparator2;
            return this;
        }

        private void checkCreatureIdValidity(ArrayList<String> arrayList, boolean z) {
            try {
                if (!arrayList.isEmpty()) {
                    ArrayList arrayList2 = (ArrayList) arrayList.clone();
                    ArrayList<MediaItem> loadCreatureItemFromMediaData = loadCreatureItemFromMediaData(arrayList2, z);
                    if (loadCreatureItemFromMediaData.isEmpty()) {
                        loadCreatureItemFromMediaData = z ? PeopleDataManager.loadItemsFromCache(arrayList) : PetDataManager.loadItemsFromCache(arrayList);
                    }
                    if (loadCreatureItemFromMediaData.isEmpty()) {
                        loadCreatureItemFromMediaData = z ? PeopleDataManager.loadItems(arrayList) : PetDataManager.loadItems(arrayList);
                    }
                    Iterator<MediaItem> it = loadCreatureItemFromMediaData.iterator();
                    while (it.hasNext()) {
                        MediaItem next = it.next();
                        String str = IdentityCreatureUtil.getUnifiedIdentityId(next.getSubCategory()) + "";
                        this.entire.get(getEntityKey(z ? "recommended_id" : "pet_recommended_id", str)).setMediaItem(next);
                        arrayList2.remove(str);
                    }
                    if (!arrayList2.isEmpty()) {
                        arrayList2.forEach(new E7.c(this, z, 6));
                    }
                }
            } catch (Error | Exception unused) {
            }
        }

        public Builder extract(String str, String str2, int i2, boolean z, LinkedHashMap<String, String> linkedHashMap) {
            this.mPdeRecommends = linkedHashMap;
            if (TextUtils.isEmpty(str)) {
                Log.sw("Builder", "Facets data is empty");
            } else {
                extract(str);
            }
            this.currentTitle = str2;
            this.mTotalCount = i2;
            this.mIsEnableSearchToolbar = z;
            return this;
        }
    }
}
