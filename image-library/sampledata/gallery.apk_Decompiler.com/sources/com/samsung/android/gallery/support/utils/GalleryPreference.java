package com.samsung.android.gallery.support.utils;

import A.a;
import E9.b;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryPreference {
    private static final ConcurrentHashMap<PreferenceType, GalleryPreference> sInstanceMap = new ConcurrentHashMap<>();
    private final String TAG;
    /* access modifiers changed from: private */
    public final SharedPreferences mPreference;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder<T> {
        final ArrayList<Pair<String, T>> list = new ArrayList<>();
        String typeName = "unknown";

        public Builder<T> append(String str, T t) {
            if (t != null) {
                this.list.add(new Pair(str, t));
            }
            return this;
        }

        public void commit(GalleryPreference galleryPreference, String str) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!this.list.isEmpty()) {
                open(galleryPreference).commit();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(NumericEnum.SEP);
            sb2.append(this);
            sb2.append(" +");
            a.x(sb2, currentTimeMillis, "GalleryPref#Builder");
        }

        public SharedPreferences.Editor open(GalleryPreference galleryPreference) {
            SharedPreferences.Editor edit = galleryPreference.mPreference.edit();
            if (!this.list.isEmpty()) {
                Object obj = this.list.get(0).second;
                this.typeName = obj.getClass().getSimpleName();
                if (obj instanceof Boolean) {
                    this.list.forEach(new C0675m(edit, 1));
                    return edit;
                } else if (obj instanceof String) {
                    this.list.forEach(new C0675m(edit, 2));
                    return edit;
                } else if (obj instanceof Long) {
                    this.list.forEach(new C0675m(edit, 3));
                    return edit;
                } else if (obj instanceof Integer) {
                    this.list.forEach(new C0675m(edit, 4));
                    return edit;
                } else if (obj instanceof Float) {
                    this.list.forEach(new C0675m(edit, 5));
                    return edit;
                } else {
                    Log.e("GalleryPref#Builder", "editor#putString for unknown " + obj.getClass());
                    this.list.forEach(new C0675m(edit, 6));
                }
            }
            return edit;
        }

        public String toString() {
            return this.typeName + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.list.size() + "";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class GalleryPreferenceHolder {
        static final GalleryPreference instance = new GalleryPreference(PreferenceType.APP);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MajorKeys {
        static final ArrayList<String> booleanList = new ArrayList<String>() {
            {
                PreferenceName preferenceName;
                add(PreferenceName.IS_SCLOUD_SYNC_ON.key());
                add(PreferenceName.ONE_DRIVE_MIGRATION_SUPPORTED.key());
                add(PreferenceName.ONE_DRIVE_SYNC_ON.key());
                add(PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE.key());
                if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
                    preferenceName = PreferenceName.LOCATION_AUTH_V2;
                } else {
                    preferenceName = PreferenceName.LOCATION_AUTH;
                }
                add(preferenceName.key());
            }
        };
        static final List<String> intList = List.of(PreferenceName.SEARCH_LLM_QP_OPERATION.key());
        static final List<String> stringList;

        static {
            PreferenceName preferenceName;
            PreferenceName preferenceName2;
            String key = PreferenceName.ONE_DRIVE_LINK_STATE.key();
            boolean z = PreferenceFeatures.OneUi8x.COLLECTION_TAB;
            if (z) {
                preferenceName = PreferenceName.COLLECTION_CAT_DISABLED;
            } else {
                preferenceName = PreferenceName.SEARCH_CAT_DISABLED;
            }
            String key2 = preferenceName.key();
            if (z) {
                preferenceName2 = PreferenceName.COLLECTION_CAT_ORDER;
            } else {
                preferenceName2 = PreferenceName.SEARCH_CAT_ORDER;
            }
            stringList = List.of(key, key2, preferenceName2.key());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreferenceData {
        final List<PreferenceTypedValue> list;
        final String name;

        public PreferenceData(String str, List<PreferenceTypedValue> list2) {
            this.name = str;
            this.list = list2;
        }

        public String toString() {
            return "PreferenceData{" + this.name + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.list.size() + "}";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PreferenceType {
        APP("gallery_pref"),
        CACHE("gallery_pref_cache"),
        DEBUG("gallery_pref_debug"),
        ANALYTICS("gallery_pref_analytics");
        
        final String preferenceName;

        private PreferenceType(String str) {
            this.preferenceName = str;
        }

        public static PreferenceType of(String str) {
            str.getClass();
            char c5 = 65535;
            switch (str.hashCode()) {
                case -1693017210:
                    if (str.equals("analytics")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 0:
                    if (str.equals("")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 94416770:
                    if (str.equals("cache")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 95458899:
                    if (str.equals("debug")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    return ANALYTICS;
                case 1:
                    return APP;
                case 2:
                    return CACHE;
                case 3:
                    return DEBUG;
                default:
                    return null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreferenceTypedValue {
        final String k;
        final int t;
        final String v;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class TypeHolder {
            static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
                {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = TypeHolder.types;
                        if (i2 < strArr.length) {
                            put(strArr[i2], Integer.valueOf(i2));
                            i2++;
                        } else {
                            return;
                        }
                    }
                }
            };
            static final String[] types = {"String", "Boolean", "Integer", "Long", "Float"};

            public static int of(Object obj) {
                Integer num = map.get(obj.getClass().getSimpleName());
                if (num == null) {
                    return 0;
                }
                return num.intValue();
            }
        }

        public PreferenceTypedValue(String str, Object obj) {
            this.t = TypeHolder.of(obj);
            this.k = str;
            this.v = String.valueOf(obj);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$toList$0(Map.Entry entry) {
            if (entry.getValue() != null) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ PreferenceTypedValue lambda$toList$1(Map.Entry entry) {
            return new PreferenceTypedValue((String) entry.getKey(), entry.getValue());
        }

        public static List<PreferenceTypedValue> toList(List<Map.Entry<String, ?>> list) {
            return (List) list.stream().filter(new C0680s(0)).map(new C0670h(3)).collect(Collectors.toList());
        }

        public boolean equals(Object obj) {
            if (obj instanceof PreferenceTypedValue) {
                PreferenceTypedValue preferenceTypedValue = (PreferenceTypedValue) obj;
                if (this.t != preferenceTypedValue.t || !this.k.equals(preferenceTypedValue.k) || !this.v.equals(preferenceTypedValue.v)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("TypedValue{");
            sb2.append(TypeHolder.types[this.t]);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.k);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0212a.p(sb2, this.v, "}");
        }
    }

    public GalleryPreference(PreferenceType preferenceType) {
        this(preferenceType.preferenceName, AppResources.getAppContext().getSharedPreferences(preferenceType.preferenceName, 0));
    }

    public static String dump() {
        return getInstance().toDebugString() + "\n" + PreferenceCache.toDebugString();
    }

    /* access modifiers changed from: private */
    public static GalleryPreference getInstance(PreferenceType preferenceType) {
        if (PreferenceType.APP.equals(preferenceType)) {
            return GalleryPreferenceHolder.instance;
        }
        return sInstanceMap.computeIfAbsent(preferenceType, new C0670h(1));
    }

    public static GalleryPreference getInstanceAnalytics() {
        return getInstance(PreferenceType.ANALYTICS);
    }

    public static GalleryPreference getInstanceCache() {
        return getInstance(PreferenceType.CACHE);
    }

    public static GalleryPreference getInstanceDebug() {
        return getInstance(PreferenceType.DEBUG);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$backup$5(Predicate predicate, Map.Entry entry) {
        return !predicate.test((String) entry.getKey());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadAllPrefix$1(String str, StringBuilder sb2, String str2, Object obj) {
        if (str2.startsWith(str)) {
            sb2.append(str2);
            sb2.append(" -> ");
            sb2.append(obj);
            sb2.append("\n");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$removeStateIf$0(Predicate predicate, ArrayList arrayList, HashMap hashMap, String str, Object obj) {
        if (predicate.test(str)) {
            arrayList.add(str);
            hashMap.put(str, "" + obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restore$6(SharedPreferences.Editor editor, PreferenceTypedValue preferenceTypedValue) {
        int i2 = preferenceTypedValue.t;
        if (i2 == 0) {
            editor.putString(preferenceTypedValue.k, preferenceTypedValue.v);
        } else if (i2 == 1) {
            editor.putBoolean(preferenceTypedValue.k, Boolean.parseBoolean(preferenceTypedValue.v));
        } else if (i2 == 2) {
            editor.putInt(preferenceTypedValue.k, Integer.parseInt(preferenceTypedValue.v));
        } else if (i2 == 3) {
            editor.putLong(preferenceTypedValue.k, Long.parseLong(preferenceTypedValue.v));
        } else if (i2 != 4) {
            String str = this.TAG;
            Log.e(str, "wrong data-type " + preferenceTypedValue);
        } else {
            editor.putFloat(preferenceTypedValue.k, Float.parseFloat(preferenceTypedValue.v));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toDebugString$2(String str) {
        String str2;
        StringBuilder t = C0212a.t(str, "=");
        if (loadBoolean(str, false)) {
            str2 = "T";
        } else {
            str2 = "F";
        }
        t.append(str2);
        return t.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toDebugString$3(String str) {
        StringBuilder t = C0212a.t(str, "=");
        t.append(loadString(str, "n/a"));
        return t.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$toDebugString$4(String str) {
        StringBuilder t = C0212a.t(str, "=");
        t.append(loadInt(str, -1));
        return t.toString();
    }

    public static List<GalleryPreference> listOf() {
        return (List) Arrays.stream(PreferenceType.values()).map(new C0670h(2)).collect(Collectors.toList());
    }

    public static GalleryPreference of(String str) {
        if (str.isEmpty()) {
            return GalleryPreferenceHolder.instance;
        }
        PreferenceType of2 = PreferenceType.of(str);
        if (of2 == null) {
            return null;
        }
        return getInstance(of2);
    }

    public void backup(String str) {
        backup(str, (Predicate<String>) null);
    }

    public void clear() {
        this.mPreference.edit().clear().apply();
    }

    public boolean computeBooleanIfAbsent(String str, BooleanSupplier booleanSupplier) {
        if (this.mPreference.contains(str)) {
            return this.mPreference.getBoolean(str, false);
        }
        boolean asBoolean = booleanSupplier.getAsBoolean();
        this.mPreference.edit().putBoolean(str, asBoolean).apply();
        return asBoolean;
    }

    public int computeIntIfAbsent(String str, IntSupplier intSupplier) {
        int i2 = this.mPreference.getInt(str, Integer.MIN_VALUE);
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        int asInt = intSupplier.getAsInt();
        this.mPreference.edit().putInt(str, asInt).apply();
        return asInt;
    }

    public String computeStringIfAbsent(String str, Supplier<String> supplier) {
        String string = this.mPreference.getString(str, (String) null);
        if (string != null || supplier == null) {
            return string;
        }
        String str2 = supplier.get();
        this.mPreference.edit().putString(str, str2).apply();
        return str2;
    }

    public Map<String, ?> getAll() {
        return this.mPreference.getAll();
    }

    public Set<String> getAllKeySet() {
        try {
            return this.mPreference.getAll().keySet();
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "getAllKeySet failed. e=" + e.getMessage());
            return new HashSet();
        }
    }

    public String getName() {
        return this.TAG;
    }

    public boolean hasPreference(PreferenceName preferenceName) {
        return this.mPreference.contains(preferenceName.key());
    }

    public String loadAllPrefix(String str) {
        StringBuilder sb2 = new StringBuilder();
        this.mPreference.getAll().forEach(new C0677o(0, str, sb2));
        return sb2.toString();
    }

    public boolean loadBoolean(PreferenceName preferenceName, boolean z) {
        return this.mPreference.getBoolean(preferenceName.key(), z);
    }

    public int loadInt(String str, int i2) {
        return this.mPreference.getInt(str, i2);
    }

    public long loadLong(String str, long j2) {
        return this.mPreference.getLong(str, j2);
    }

    public int loadSharedSortBy(String str, int i2) {
        SharedPreferences sharedPreferences = this.mPreference;
        return sharedPreferences.getInt(PreferenceName.SHARED_SORT_BY + str, i2);
    }

    public int loadSortBy(String str, int i2) {
        SharedPreferences sharedPreferences = this.mPreference;
        return sharedPreferences.getInt(PreferenceName.SORT_BY + str, i2);
    }

    public String loadString(PreferenceName preferenceName, String str) {
        return loadString(preferenceName.key(), str);
    }

    public void removeSortBy(String str) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        edit.remove(PreferenceName.SORT_BY + str).apply();
    }

    public void removeState(PreferenceName preferenceName) {
        removeState(preferenceName.key());
    }

    public HashMap<String, String> removeStateIf(Predicate<String> predicate) {
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> hashMap = new HashMap<>();
        this.mPreference.getAll().forEach(new b(predicate, arrayList, hashMap, 3));
        SharedPreferences.Editor edit = this.mPreference.edit();
        Objects.requireNonNull(edit);
        arrayList.forEach(new C0675m(edit, 0));
        edit.apply();
        return hashMap;
    }

    public void removeStatePrefix(String str) {
        Set<String> keySet = this.mPreference.getAll().keySet();
        SharedPreferences.Editor edit = this.mPreference.edit();
        for (String next : keySet) {
            if (next.startsWith(str)) {
                edit.remove(next);
            }
        }
        edit.apply();
    }

    public void restore(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        PreferenceData preferenceData = (PreferenceData) GsonTool.fromJsonFile(PreferenceData.class, str);
        if (preferenceData == null || !TextUtils.equals(getName(), preferenceData.name)) {
            String str2 = this.TAG;
            Log.e(str2, "restore skip. wrong data" + Logger.vt(getName(), preferenceData, Long.valueOf(currentTimeMillis)));
            return;
        }
        if (!preferenceData.list.isEmpty()) {
            SharedPreferences.Editor edit = this.mPreference.edit();
            preferenceData.list.forEach(new C0679q(0, this, edit));
            edit.commit();
        }
        String str3 = this.TAG;
        a.A(new Object[]{preferenceData, Long.valueOf(currentTimeMillis)}, new StringBuilder("restore"), str3);
    }

    public void saveSharedSortBy(String str, int i2) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        edit.putInt(PreferenceName.SHARED_SORT_BY + str, i2).apply();
    }

    public void saveSortBy(String str, int i2) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        edit.putInt(PreferenceName.SORT_BY + str, i2).apply();
    }

    public void saveState(String str, int i2) {
        this.mPreference.edit().putInt(str, i2).apply();
    }

    public void saveStateBoolean(HashMap<String, Boolean> hashMap) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        Objects.requireNonNull(edit);
        hashMap.forEach(new C0676n(edit, 0));
        edit.apply();
    }

    public void saveStateInt(HashMap<String, Integer> hashMap) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        Objects.requireNonNull(edit);
        hashMap.forEach(new C0676n(edit, 3));
        edit.apply();
    }

    public void saveStateLong(HashMap<String, Long> hashMap) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        Objects.requireNonNull(edit);
        hashMap.forEach(new C0676n(edit, 2));
        edit.apply();
    }

    public String toDebugString() {
        ArrayList arrayList = new ArrayList();
        MajorKeys.booleanList.stream().map(new r(0, this)).forEach(new f4.a(arrayList, 17));
        MajorKeys.stringList.stream().map(new r(1, this)).forEach(new f4.a(arrayList, 17));
        MajorKeys.intList.stream().map(new r(2, this)).forEach(new f4.a(arrayList, 17));
        return "Preference=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]";
    }

    public GalleryPreference(String str, SharedPreferences sharedPreferences) {
        this.TAG = str;
        this.mPreference = sharedPreferences;
    }

    public void backup(String str, Predicate<String> predicate) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(getAll().entrySet());
        if (predicate != null) {
            arrayList.removeIf(new C0678p(0, predicate));
        }
        PreferenceData preferenceData = new PreferenceData(getName(), PreferenceTypedValue.toList(arrayList));
        FileUtils.writeFile(str, GsonTool.toJsonString(preferenceData).getBytes());
        String str2 = this.TAG;
        a.A(new Object[]{preferenceData, Long.valueOf(currentTimeMillis)}, new StringBuilder("backup"), str2);
    }

    public boolean hasPreference(String str) {
        return this.mPreference.contains(str);
    }

    public boolean loadBoolean(PreferenceName preferenceName) {
        return this.mPreference.getBoolean(preferenceName.key(), preferenceName.getBooleanDefault());
    }

    public int loadInt(PreferenceName preferenceName, int i2) {
        return this.mPreference.getInt(preferenceName.key(), i2);
    }

    public long loadLong(PreferenceName preferenceName) {
        return this.mPreference.getLong(preferenceName.key(), preferenceName.getLongDefault());
    }

    public String loadString(PreferenceName preferenceName) {
        return loadString(preferenceName.key(), preferenceName.getStringDefault());
    }

    public void removeState(String str) {
        this.mPreference.edit().remove(str).apply();
    }

    public void saveState(String str, long j2) {
        this.mPreference.edit().putLong(str, j2).apply();
    }

    public boolean loadBoolean(String str, boolean z) {
        return this.mPreference.getBoolean(str, z);
    }

    public int loadInt(PreferenceName preferenceName) {
        return this.mPreference.getInt(preferenceName.key(), preferenceName.getIntegerDefault());
    }

    public String loadString(String str, String str2) {
        return this.mPreference.getString(str, str2);
    }

    public void removeState(Collection<String> collection) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        for (String remove : collection) {
            edit.remove(remove);
        }
        edit.apply();
    }

    public void saveState(String str, String str2) {
        this.mPreference.edit().putString(str, str2).apply();
    }

    public static GalleryPreference getInstance() {
        return GalleryPreferenceHolder.instance;
    }

    public void saveState(PreferenceName preferenceName, boolean z) {
        saveState(preferenceName.key(), z);
    }

    public void saveState(PreferenceName preferenceName, int i2) {
        saveState(preferenceName.key(), i2);
    }

    public void saveState(PreferenceName preferenceName, String str) {
        saveState(preferenceName.key(), str);
    }

    public void saveState(String str, boolean z) {
        this.mPreference.edit().putBoolean(str, z).apply();
    }

    public void saveState(Consumer<SharedPreferences.Editor> consumer) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        consumer.accept(edit);
        edit.apply();
    }

    public void saveState(HashMap<String, String> hashMap) {
        SharedPreferences.Editor edit = this.mPreference.edit();
        Objects.requireNonNull(edit);
        hashMap.forEach(new C0676n(edit, 1));
        edit.apply();
    }
}
