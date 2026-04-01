package com.samsung.android.gallery.module.smartswitch;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum GalleryPreferenceEntryV2 {
    ;
    
    private static final String TAG = "GalleryPreferenceEntryV2";
    private Boolean mBooleanData;
    private Integer mIntegerData;
    private final String mKey;
    private String mStringData;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass1(String str) {
            this("SIMILAR_MODE", 0, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, false);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE, false);
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass2(String str) {
            this("TIMELINE_GRID_SIZE", 1, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.TIMELINE_GRID_SIZE, -1);
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass3(String str) {
            this("TIMELINE_GRID_SIZE_V30", 2, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V30, -1);
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass4(String str) {
            this("TIMELINE_GRID_SIZE_V60", 3, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V60, -1);
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass5(String str) {
            this("TIMELINE_GRID_SIZE_V70", 4, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V70, -1);
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass6(String str) {
            this("ALBUMS_GRID_SIZE", 5, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.ALBUMS_GRID_SIZE, -1);
        }

        private AnonymousClass6(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass7(String str) {
            this("STORIES_GRID_SIZE", 6, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, -1);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.STORIES_GRID_SIZE, -1);
        }

        private AnonymousClass7(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass8() {
            this("CURRENT_TAB", 7, "location://variable/currentv1");
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, "location://timeline");
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, "location://variable/currentv1", "location://timeline");
        }

        private AnonymousClass8(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.smartswitch.GalleryPreferenceEntryV2$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends GalleryPreferenceEntryV2 {
        public /* synthetic */ AnonymousClass9(String str) {
            this("SPLIT_MODE", 8, str);
        }

        public void backup(JSONObject jSONObject) {
            backup(jSONObject, false);
        }

        public boolean restore(JSONObject jSONObject) {
            return restore(jSONObject, PreferenceName.SPLIT_MODE, false);
        }

        private AnonymousClass9(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    private PreferenceFeatures getPreferenceFeature() {
        return (PreferenceFeatures) Arrays.stream(PreferenceFeatures.values()).filter(new d(this, 0)).findFirst().orElse((Object) null);
    }

    public abstract void backup(JSONObject jSONObject);

    public void backup(JSONObject jSONObject, boolean z) {
        Boolean valueOf = Boolean.valueOf(GalleryPreference.getInstance().loadBoolean(this.mKey, z));
        this.mBooleanData = valueOf;
        try {
            jSONObject.put(this.mKey, valueOf);
            Log.v(TAG, "backup " + this.mKey + "#" + this.mBooleanData);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public boolean exist() {
        return GalleryPreference.getInstance().hasPreference(this.mKey);
    }

    public abstract boolean restore(JSONObject jSONObject);

    public boolean restore(JSONObject jSONObject, PreferenceName preferenceName, boolean z) {
        try {
            this.mBooleanData = Boolean.valueOf(jSONObject.getBoolean(this.mKey));
            if (GalleryPreference.getInstance().loadBoolean(this.mKey, z) != this.mBooleanData.booleanValue()) {
                GalleryPreference.getInstance().saveState(preferenceName, this.mBooleanData.booleanValue());
                PreferenceFeatures preferenceFeature = getPreferenceFeature();
                if (!(preferenceFeature == null || preferenceFeature.isEnabled() == this.mBooleanData.booleanValue())) {
                    PreferenceFeatures.recycle(preferenceFeature);
                }
                Log.v(TAG, "restore " + this.mKey + "#" + this.mBooleanData);
                return true;
            }
            Log.v(TAG, "do not need to restore " + this.mKey + "#" + this.mBooleanData);
            return false;
        } catch (JSONException unused) {
            Log.w(TAG, "No value for " + this.mKey);
            return false;
        }
    }

    private GalleryPreferenceEntryV2(String str) {
        this.mKey = str;
    }

    public void backup(JSONObject jSONObject, int i2) {
        Integer valueOf = Integer.valueOf(GalleryPreference.getInstance().loadInt(this.mKey, i2));
        this.mIntegerData = valueOf;
        try {
            jSONObject.put(this.mKey, valueOf);
            Log.v(TAG, "backup " + this.mKey + "#" + this.mIntegerData);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void backup(JSONObject jSONObject, String str) {
        String loadString = GalleryPreference.getInstance().loadString(this.mKey, str);
        this.mStringData = loadString;
        if (!TextUtils.isEmpty(loadString)) {
            try {
                jSONObject.put(this.mKey, this.mStringData);
                Log.v(TAG, "backup " + this.mKey + "#" + this.mStringData);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public boolean restore(JSONObject jSONObject, PreferenceName preferenceName, int i2) {
        try {
            this.mIntegerData = Integer.valueOf(jSONObject.getInt(this.mKey));
            if (GalleryPreference.getInstance().loadInt(this.mKey, i2) != this.mIntegerData.intValue()) {
                GalleryPreference.getInstance().saveState(preferenceName, this.mIntegerData.intValue());
                Log.v(TAG, "restore " + this.mKey + "#" + this.mIntegerData);
                return true;
            }
            Log.v(TAG, "do not need to restore " + this.mKey + "#" + this.mIntegerData);
            return false;
        } catch (JSONException unused) {
            Log.w(TAG, "No value for " + this.mKey);
            return false;
        }
    }

    public static JSONObject backup() {
        JSONObject jSONObject = new JSONObject();
        Arrays.stream(values()).filter(new a(0)).forEach(new b(jSONObject, 0));
        return jSONObject;
    }

    public boolean restore(JSONObject jSONObject, String str, String str2) {
        try {
            this.mStringData = jSONObject.getString(this.mKey);
            if (!TextUtils.equals(GalleryPreference.getInstance().loadString(this.mKey, str2), this.mStringData)) {
                GalleryPreference.getInstance().saveState(str, this.mStringData);
                Log.v(TAG, "restore " + this.mKey + "#" + this.mStringData);
                return true;
            }
            Log.v(TAG, "do not need to restore " + this.mKey + "#" + this.mStringData);
            return false;
        } catch (JSONException unused) {
            Log.w(TAG, "No value for " + this.mKey);
            return false;
        }
    }

    public static boolean restore(String str) {
        JSONObject jSONObject = new JSONObject(str);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Arrays.stream(values()).forEach(new c(atomicBoolean, jSONObject));
        return atomicBoolean.get();
    }
}
