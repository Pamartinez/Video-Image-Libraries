package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleData implements Copyable<HideRuleData> {
    private static final HideRuleData EMPTY = new HideRuleData();
    private static final String TAG = "HideRuleData";
    public long hideRuleEndTime = -1;
    public int hideRuleGroupId = -1;
    public int hideRuleId = -1;
    public int hideRuleIndividual = -1;
    public long hideRuleModifiedTime = -1;
    public int hideRulePersonId = -1;
    public String hideRulePrivateData = "";
    public long hideRuleStartTime = -1;
    public int hideRuleType = -1;

    public static boolean isHideDate(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || of(fileItemInterface).hideRuleType != 0) {
            return false;
        }
        return true;
    }

    public static boolean isHideScene(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || of(fileItemInterface).hideRuleType != 1) {
            return false;
        }
        return true;
    }

    public static HideRuleData of(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return EMPTY;
        }
        String str = TAG;
        HideRuleData hideRuleData = (HideRuleData) fileItemInterface.getTag(str);
        if (hideRuleData != null) {
            return hideRuleData;
        }
        HideRuleData hideRuleData2 = new HideRuleData();
        fileItemInterface.setTag(str, hideRuleData2);
        return hideRuleData2;
    }

    public HideRuleData parse(CursorHolder cursorHolder) {
        this.hideRuleId = cursorHolder.getInt(cursorHolder.indexStoryHideRuleId, -1);
        this.hideRuleType = cursorHolder.getInt(cursorHolder.indexStoryHideRuleType, -1);
        this.hideRuleStartTime = cursorHolder.getLong(cursorHolder.indexStoryHideRuleStartTime, -1);
        this.hideRuleEndTime = cursorHolder.getLong(cursorHolder.indexStoryHideRuleEndTime, -1);
        this.hideRuleModifiedTime = cursorHolder.getLong(cursorHolder.indexStoryHideRuleModifyTime, -1);
        this.hideRulePersonId = cursorHolder.getInt(cursorHolder.indexStoryHideRulePersonId, -1);
        this.hideRuleGroupId = cursorHolder.getInt(cursorHolder.indexStoryHideRuleGroupId, -1);
        this.hideRulePrivateData = cursorHolder.getString(cursorHolder.indexStoryHideRulePrivateData, "");
        if (PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION) {
            this.hideRuleIndividual = cursorHolder.getInt(cursorHolder.indexStoryHideRuleIndividual, -1);
        }
        return this;
    }

    public HideRuleData copyOf() {
        try {
            return (HideRuleData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
