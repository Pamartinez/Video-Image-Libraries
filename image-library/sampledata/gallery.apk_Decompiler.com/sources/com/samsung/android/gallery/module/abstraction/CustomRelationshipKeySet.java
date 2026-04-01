package com.samsung.android.gallery.module.abstraction;

import android.text.TextUtils;
import androidx.window.embedding.c;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomRelationshipKeySet {
    private static volatile CustomRelationshipKeySet sInstance;
    private final LinkedHashMap<String, String> mCloneData = new LinkedHashMap<>();
    private final LinkedHashMap<String, String> mData;

    public CustomRelationshipKeySet() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        this.mData = linkedHashMap;
        loadFromPreference(linkedHashMap);
    }

    public static CustomRelationshipKeySet getInstance() {
        if (sInstance == null) {
            synchronized (CustomRelationshipKeySet.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new CustomRelationshipKeySet();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static boolean isCustomRelationshipType(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("<custom>")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getTranslatedName$2(StringJoiner stringJoiner, String str) {
        LinkedHashMap<String, Integer> linkedHashMap = RelationshipKeySet.RELATIONSHIP_PRESET_LEGACY_MAP;
        if (linkedHashMap.containsKey(str)) {
            String string = AppResources.getString(linkedHashMap.get(str).intValue());
            if (!stringJoiner.toString().contains(string)) {
                stringJoiner.add(string);
                return;
            }
            return;
        }
        stringJoiner.add(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reassignPreference$0(String str) {
        Stream stream = Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        LinkedHashMap<String, Integer> linkedHashMap = RelationshipKeySet.RELATIONSHIP_MAP;
        Objects.requireNonNull(linkedHashMap);
        if (stream.noneMatch(new e(5, linkedHashMap))) {
            this.mData.put("<custom>".concat(str), str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reassignPreference$1(List list) {
        list.forEach(new a(1, this));
        updatePreference();
    }

    private void loadFromPreference(LinkedHashMap<String, String> linkedHashMap) {
        if (supportCustomRelationship()) {
            String loadString = GalleryPreference.getInstance().loadString(PreferenceName.CUSTOM_RELATIONSHIPS, "");
            if (!TextUtils.isEmpty(loadString)) {
                for (String str : loadString.split("/")) {
                    linkedHashMap.put("<custom>" + str, str);
                }
            }
        }
    }

    public String addDataFrom(String str, boolean z) {
        if (!this.mData.containsValue(str)) {
            LinkedHashMap<String, String> linkedHashMap = this.mData;
            linkedHashMap.put("<custom>" + str, str);
            if (z) {
                updatePreference();
            }
        }
        return C0212a.l("<custom>", str);
    }

    public void clearCloneData() {
        this.mCloneData.clear();
    }

    public void createCloneData() {
        loadFromPreference(this.mCloneData);
    }

    public boolean existName(String str) {
        if (this.mData.get(str) != null) {
            return true;
        }
        return false;
    }

    public Collection<String> getClonedValues() {
        return this.mCloneData.values();
    }

    public String getName(String str) {
        return getTranslatedName(this.mData.get(str));
    }

    public String getNameInCloneData(String str) {
        return this.mCloneData.get(str);
    }

    public String getTranslatedName(String str) {
        if (!isReducedRelationshipType()) {
            return str;
        }
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StringJoiner stringJoiner = new StringJoiner("/");
        Arrays.stream(split).forEach(new a(0, stringJoiner));
        return stringJoiner.toString();
    }

    public String getType(String str) {
        if (this.mData.containsValue(str)) {
            return C0212a.l("<custom>", str);
        }
        return null;
    }

    public boolean isReducedRelationshipType() {
        return PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE;
    }

    public void putToCloneData(String str) {
        LinkedHashMap<String, String> linkedHashMap = this.mCloneData;
        linkedHashMap.put("<custom>" + str, str);
    }

    public void reassignPreference(List<String> list) {
        if (supportCustomRelationship() || isReducedRelationshipType()) {
            SimpleThreadPool.getInstance().execute(new c(19, this, list));
        }
    }

    public void removeFromCloneData(String str) {
        LinkedHashMap<String, String> linkedHashMap = this.mCloneData;
        linkedHashMap.remove("<custom>" + str, str);
    }

    public void saveData(String str) {
        this.mData.put(str, str.substring(8));
        updatePreference();
    }

    public boolean supportCustomRelationship() {
        return PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP;
    }

    public void updatePreference() {
        GalleryPreference.getInstance().saveState(PreferenceName.CUSTOM_RELATIONSHIPS, TextUtils.join("/", new ArrayList(this.mData.values())));
    }
}
