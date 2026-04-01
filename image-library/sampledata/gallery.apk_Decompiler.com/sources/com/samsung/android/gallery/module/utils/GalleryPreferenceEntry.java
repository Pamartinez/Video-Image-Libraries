package com.samsung.android.gallery.module.utils;

import android.text.TextUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryPreferenceEntry {
    private Integer ALBUMS_GRID_SIZE;
    private String CURRENT_TAB;
    private Boolean SIMILAR_MODE;
    private Integer STORIES_GRID_SIZE;
    private Integer TIMELINE_GRID_SIZE;
    private Integer TIMELINE_GRID_SIZE_V30;
    private Integer TIMELINE_GRID_SIZE_V60;
    private boolean mAlbumChanged;
    private boolean mCurrentTabChanged;
    private boolean mRestoredBooleanValue;
    private int mRestoredIntegerValue;
    private String mRestoredStringValue;
    private boolean mSimilarModeChanged;
    private boolean mStoryChanged;
    private boolean mTimelineChanged;

    private void buildBoolean(JSONObject jSONObject, String str, Boolean bool) {
        if (bool != null) {
            jSONObject.put(str, bool);
        }
    }

    private void buildInteger(JSONObject jSONObject, String str, Integer num) {
        if (num != null && num.intValue() > -1) {
            jSONObject.put(str, num);
        }
    }

    private void buildString(JSONObject jSONObject, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(str, str2);
        }
    }

    private boolean changePreference(PreferenceName preferenceName, Integer num) {
        if (num != null) {
            GalleryPreference.getInstance().saveState(preferenceName, num.intValue());
            Log.d("GalleryPreferenceEntry", "changePreference [k,v][" + preferenceName.key() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + num + "]");
        }
        return num != null;
    }

    private void dump() {
    }

    private boolean needToChangePreference(JSONObject jSONObject, PreferenceName preferenceName, boolean z) {
        try {
            this.mRestoredBooleanValue = jSONObject.getBoolean(preferenceName.key());
            if (GalleryPreference.getInstance().loadBoolean(preferenceName, z) != this.mRestoredBooleanValue) {
                return true;
            }
            return false;
        } catch (JSONException unused) {
            Log.w("GalleryPreferenceEntry", "No value for " + preferenceName.key());
            return false;
        }
    }

    public JSONObject buildJson() {
        JSONObject jSONObject = new JSONObject();
        buildBoolean(jSONObject, PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE.key(), this.SIMILAR_MODE);
        buildInteger(jSONObject, PreferenceName.TIMELINE_GRID_SIZE.key(), this.TIMELINE_GRID_SIZE);
        buildInteger(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V30.key(), this.TIMELINE_GRID_SIZE_V30);
        buildInteger(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V60.key(), this.TIMELINE_GRID_SIZE_V60);
        buildInteger(jSONObject, PreferenceName.ALBUMS_GRID_SIZE.key(), this.ALBUMS_GRID_SIZE);
        buildInteger(jSONObject, PreferenceName.STORIES_GRID_SIZE.key(), this.STORIES_GRID_SIZE);
        buildString(jSONObject, "location://variable/currentv1", this.CURRENT_TAB);
        dump(jSONObject);
        return jSONObject;
    }

    public boolean isSimilarModeChanged() {
        return this.mSimilarModeChanged;
    }

    public GalleryPreferenceEntry loadJson(JSONObject jSONObject) {
        String str;
        Boolean bool;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5 = null;
        if (needToChangePreference(jSONObject, "location://variable/currentv1", "location://timeline")) {
            str = this.mRestoredStringValue;
        } else {
            str = null;
        }
        this.CURRENT_TAB = str;
        if (needToChangePreference(jSONObject, PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE, false)) {
            bool = Boolean.valueOf(this.mRestoredBooleanValue);
        } else {
            bool = null;
        }
        this.SIMILAR_MODE = bool;
        if (needToChangePreference(jSONObject, PreferenceName.TIMELINE_GRID_SIZE, -1)) {
            num = Integer.valueOf(this.mRestoredIntegerValue);
        } else {
            num = null;
        }
        this.TIMELINE_GRID_SIZE = num;
        if (needToChangePreference(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V30, -1)) {
            num2 = Integer.valueOf(this.mRestoredIntegerValue);
        } else {
            num2 = null;
        }
        this.TIMELINE_GRID_SIZE_V30 = num2;
        if (needToChangePreference(jSONObject, PreferenceName.TIMELINE_GRID_SIZE_V60, -1)) {
            num3 = Integer.valueOf(this.mRestoredIntegerValue);
        } else {
            num3 = null;
        }
        this.TIMELINE_GRID_SIZE_V60 = num3;
        if (needToChangePreference(jSONObject, PreferenceName.ALBUMS_GRID_SIZE, -1)) {
            num4 = Integer.valueOf(this.mRestoredIntegerValue);
        } else {
            num4 = null;
        }
        this.ALBUMS_GRID_SIZE = num4;
        if (needToChangePreference(jSONObject, PreferenceName.STORIES_GRID_SIZE, -1)) {
            num5 = Integer.valueOf(this.mRestoredIntegerValue);
        }
        this.STORIES_GRID_SIZE = num5;
        return this;
    }

    public GalleryPreferenceEntry loadPreference() {
        Boolean bool;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.TIMELINE_SIMILAR_PHOTO_MODE;
        String str = null;
        if (instance.hasPreference(preferenceName)) {
            bool = Boolean.valueOf(instance.loadBoolean(preferenceName));
        } else {
            bool = null;
        }
        this.SIMILAR_MODE = bool;
        PreferenceName preferenceName2 = PreferenceName.TIMELINE_GRID_SIZE;
        if (instance.hasPreference(preferenceName2)) {
            num = Integer.valueOf(instance.loadInt(preferenceName2, -1));
        } else {
            num = null;
        }
        this.TIMELINE_GRID_SIZE = num;
        PreferenceName preferenceName3 = PreferenceName.TIMELINE_GRID_SIZE_V30;
        if (instance.hasPreference(preferenceName3)) {
            num2 = Integer.valueOf(instance.loadInt(preferenceName3, -1));
        } else {
            num2 = null;
        }
        this.TIMELINE_GRID_SIZE_V30 = num2;
        PreferenceName preferenceName4 = PreferenceName.TIMELINE_GRID_SIZE_V60;
        if (instance.hasPreference(preferenceName4)) {
            num3 = Integer.valueOf(instance.loadInt(preferenceName4, -1));
        } else {
            num3 = null;
        }
        this.TIMELINE_GRID_SIZE_V60 = num3;
        PreferenceName preferenceName5 = PreferenceName.ALBUMS_GRID_SIZE;
        if (instance.hasPreference(preferenceName5)) {
            num4 = Integer.valueOf(instance.loadInt(preferenceName5, -1));
        } else {
            num4 = null;
        }
        this.ALBUMS_GRID_SIZE = num4;
        PreferenceName preferenceName6 = PreferenceName.STORIES_GRID_SIZE;
        if (instance.hasPreference(preferenceName6)) {
            num5 = Integer.valueOf(instance.loadInt(preferenceName6, -1));
        } else {
            num5 = null;
        }
        this.STORIES_GRID_SIZE = num5;
        if (instance.hasPreference("location://variable/currentv1")) {
            str = instance.loadString("location://variable/currentv1", "location://timeline");
        }
        this.CURRENT_TAB = str;
        dump();
        return this;
    }

    public boolean needToRecreate(String str) {
        if (this.mCurrentTabChanged) {
            return true;
        }
        if (this.mTimelineChanged && LocationKey.isTimeline(str)) {
            return true;
        }
        if (this.mAlbumChanged && LocationKey.isAlbumsMatch(str)) {
            return true;
        }
        if (!this.mStoryChanged || !LocationKey.isStoriesMatch(str)) {
            return false;
        }
        return true;
    }

    public boolean save() {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        this.mCurrentTabChanged = changePreference("location://variable/currentv1", this.CURRENT_TAB);
        if (this.SIMILAR_MODE != null) {
            z = true;
        } else {
            z = false;
        }
        this.mSimilarModeChanged = z;
        if (changePreference(PreferenceName.TIMELINE_GRID_SIZE, this.TIMELINE_GRID_SIZE) || this.mTimelineChanged) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mTimelineChanged = z3;
        if (changePreference(PreferenceName.TIMELINE_GRID_SIZE_V30, this.TIMELINE_GRID_SIZE_V30) || this.mTimelineChanged) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.mTimelineChanged = z7;
        if (changePreference(PreferenceName.TIMELINE_GRID_SIZE_V60, this.TIMELINE_GRID_SIZE_V60) || this.mTimelineChanged) {
            z9 = true;
        } else {
            z9 = false;
        }
        this.mTimelineChanged = z9;
        this.mAlbumChanged = changePreference(PreferenceName.ALBUMS_GRID_SIZE, this.ALBUMS_GRID_SIZE);
        this.mStoryChanged = changePreference(PreferenceName.STORIES_GRID_SIZE, this.STORIES_GRID_SIZE);
        dump();
        if (this.mCurrentTabChanged || this.mSimilarModeChanged || this.mTimelineChanged || this.mAlbumChanged || this.mStoryChanged) {
            return true;
        }
        return false;
    }

    private void dump(JSONObject jSONObject) {
    }

    private boolean changePreference(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            GalleryPreference.getInstance().saveState(str, str2);
            Log.d("GalleryPreferenceEntry", "changePreference [k,v][" + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str2 + "]");
        }
        return !TextUtils.isEmpty(str2);
    }

    private boolean needToChangePreference(JSONObject jSONObject, PreferenceName preferenceName, int i2) {
        try {
            this.mRestoredIntegerValue = jSONObject.getInt(preferenceName.key());
            if (GalleryPreference.getInstance().loadInt(preferenceName, i2) != this.mRestoredIntegerValue) {
                return true;
            }
            return false;
        } catch (JSONException unused) {
            Log.w("GalleryPreferenceEntry", "No value for " + preferenceName.key());
            return false;
        }
    }

    private boolean needToChangePreference(JSONObject jSONObject, String str, String str2) {
        try {
            this.mRestoredStringValue = jSONObject.getString(str);
            return !GalleryPreference.getInstance().loadString(str, str2).equals(this.mRestoredStringValue);
        } catch (JSONException unused) {
            Log.w("GalleryPreferenceEntry", "No value for " + str);
            return false;
        }
    }
}
