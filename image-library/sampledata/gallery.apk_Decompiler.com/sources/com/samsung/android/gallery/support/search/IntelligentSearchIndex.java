package com.samsung.android.gallery.support.search;

import Ya.a;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSearchIndex {
    /* access modifiers changed from: private */
    public static final boolean SUPPORT_PET_CLUSTER = Features.isEnabled(Features.SUPPORT_PET_CLUSTER);
    private static final boolean SUPPORT_SCS_SEARCH = Features.isEnabled(Features.SUPPORT_SCS_SEARCH);
    /* access modifiers changed from: private */
    public static final boolean SUPPORT_SUGGEST_RELATIONSHIP = Features.isEnabled(Features.SUPPORT_SUGGEST_RELATIONSHIP);
    private static final IntelligentSearchIndex sInstance = new IntelligentSearchIndex();

    /* renamed from: com.samsung.android.gallery.support.search.IntelligentSearchIndex$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$support$search$IntelligentSearchIndex$IndexMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.support.search.IntelligentSearchIndex$IndexMode[] r0 = com.samsung.android.gallery.support.search.IntelligentSearchIndex.IndexMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$support$search$IntelligentSearchIndex$IndexMode = r0
                com.samsung.android.gallery.support.search.IntelligentSearchIndex$IndexMode r1 = com.samsung.android.gallery.support.search.IntelligentSearchIndex.IndexMode.APPEND     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$search$IntelligentSearchIndex$IndexMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.support.search.IntelligentSearchIndex$IndexMode r1 = com.samsung.android.gallery.support.search.IntelligentSearchIndex.IndexMode.REMOVE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.search.IntelligentSearchIndex.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum IndexMode {
        SET,
        APPEND,
        REMOVE
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TagType {
        PERSON(true, "persontag", "person_id", "recommended_id"),
        RELATIONSHIP(IntelligentSearchIndex.SUPPORT_SUGGEST_RELATIONSHIP, "relationship"),
        USER_TAG(true, "usertag"),
        STORY(!SdkConfig.atLeast(SdkConfig.SEM.T_MR1), "storytag"),
        EXPRESSIONS(true, "expressionstag"),
        SCENE(true, "scenetag"),
        SCENE_CAPTURE(true, "scene_name"),
        LOCATION(true, "locationtag", "facet_location"),
        PET(IntelligentSearchIndex.SUPPORT_PET_CLUSTER, "pet_tag", "pet_recommended_id"),
        DATE_SUGGESTION(true, "date_suggestion");
        
        private final boolean mRequestEnabled;
        private final String[] mValue;

        private TagType(boolean z, String... strArr) {
            this.mValue = strArr;
            this.mRequestEnabled = z;
        }

        public String getValue() {
            return getValue(0);
        }

        public Map<String, String> getValueMap(String... strArr) {
            HashMap hashMap = new HashMap();
            if (isRequestEnabled()) {
                int min = Math.min(strArr.length, this.mValue.length);
                for (int i2 = 0; i2 < min; i2++) {
                    String str = strArr[i2];
                    if (str != null) {
                        hashMap.put(this.mValue[i2], str);
                    }
                }
            }
            return hashMap;
        }

        public boolean isRequestEnabled() {
            return this.mRequestEnabled;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mValue));
            sb2.append(',');
            return C0086a.n(sb2, this.mRequestEnabled, ']');
        }

        public String getValue(int i2) {
            String[] strArr = this.mValue;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
            return null;
        }
    }

    public static IntelligentSearchIndex getInstance() {
        return sInstance;
    }

    public String getIndexMode(IndexMode indexMode) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$support$search$IntelligentSearchIndex$IndexMode[indexMode.ordinal()];
        if (i2 == 1) {
            return "+";
        }
        if (i2 == 2) {
            return "-";
        }
        throw new IllegalArgumentException("Illegal tag type");
    }

    public void indexing(long j2, Collection<String> collection, TagType tagType, IndexMode indexMode) {
        if (!tagType.isRequestEnabled()) {
            Log.w((CharSequence) "IntlSearchIndex", "indexing skip. no enabled indexer", tagType);
        } else {
            indexing((Collection<Long>) new ArrayList(Collections.singletonList(Long.valueOf(j2))), collection, tagType, indexMode);
        }
    }

    public void indexing(Collection<Long> collection, Collection<String> collection2, TagType tagType, IndexMode indexMode) {
        if (!tagType.isRequestEnabled()) {
            Log.w((CharSequence) "IntlSearchIndex", "indexing skip. no enabled indexer", tagType);
            return;
        }
        String value = tagType.getValue();
        HashMap hashMap = new HashMap();
        if (SUPPORT_SCS_SEARCH) {
            hashMap.put(tagType.getValue(), String.join("\u001f", collection2));
        } else {
            collection2.forEach(new a(0, value, hashMap));
        }
        indexing(collection, (Map<String, String>) hashMap, indexMode, (SearchIndexListener) null);
    }

    public void indexing(Collection<Long> collection, String str, TagType tagType, IndexMode indexMode) {
        if (!tagType.isRequestEnabled()) {
            Log.w((CharSequence) "IntlSearchIndex", "indexing skip. no enabled indexer", tagType);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(tagType.getValue(), str);
        indexing(collection, (Map<String, String>) hashMap, indexMode, (SearchIndexListener) null);
    }

    public void indexing(Collection<Long> collection, Map<String, String> map, IndexMode indexMode, SearchIndexListener searchIndexListener) {
        if (map.isEmpty()) {
            Log.w("IntlSearchIndex", "indexing skip. no enabled indexer");
            if (searchIndexListener != null) {
                searchIndexListener.onIndexComplete(-1);
                return;
            }
            return;
        }
        indexing(new ArrayList(Collections.singletonList(new BasicIndexParams((Collection) collection.stream().map(new V8.a(14)).collect(Collectors.toList()), map))), indexMode, searchIndexListener);
    }

    private void indexing(ArrayList<IndexParams> arrayList, IndexMode indexMode, SearchIndexListener searchIndexListener) {
        Context appContext = AppResources.getAppContext();
        boolean z = false;
        if (appContext == null || !Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH) || arrayList.isEmpty()) {
            if (appContext != null) {
                z = true;
            }
            Log.e((CharSequence) "IntlSearchIndex", "indexing failed. no params", Boolean.valueOf(z), Boolean.valueOf(Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)), Boolean.valueOf(arrayList.isEmpty()));
            return;
        }
        new SearchIndexingTask(appContext, arrayList, indexMode, searchIndexListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
