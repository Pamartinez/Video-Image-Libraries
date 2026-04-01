package com.samsung.android.gallery.module.data;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestData implements Copyable<SuggestData> {
    private static final SuggestData EMPTY = new SuggestData();
    private static final String TAG = "SuggestData";
    public int cleanOutDeleteGroupId = -1;
    public int cleanOutDeleteType = -1;
    public int cleanOutState = -1;
    public long cleanOutTotalSize = -1;
    public int extra = -1;
    public String extraData;
    public String extraValue;
    public String originalPath;
    public int remasterHeight;
    public long remasterResultType = -2;
    public long remasterType = -2;
    public int remasterWidth;
    public int suggestType;

    public static SuggestData of(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return EMPTY;
        }
        String str = TAG;
        SuggestData suggestData = (SuggestData) fileItemInterface.getTag(str);
        if (suggestData != null) {
            return suggestData;
        }
        SuggestData suggestData2 = new SuggestData();
        fileItemInterface.setTag(str, suggestData2);
        return suggestData2;
    }

    public SuggestData parse(CursorHolder cursorHolder) {
        this.suggestType = cursorHolder.getInt(cursorHolder.indexSuggestType, 0);
        this.extra = cursorHolder.getInt(cursorHolder.indexSuggestExtra, -1);
        this.extraData = cursorHolder.getString(cursorHolder.indexSuggestExtraData, (String) null);
        this.extraValue = cursorHolder.getString(cursorHolder.indexSuggestExtraValue, (String) null);
        this.cleanOutDeleteType = cursorHolder.getInt(cursorHolder.indexDeleteType, -1);
        return this;
    }

    public SuggestData parseCleanOut(CursorHolder cursorHolder) {
        int i2 = cursorHolder.indexDeleteType;
        if (i2 >= 0) {
            this.cleanOutDeleteType = cursorHolder.getInt(i2, -1);
            this.cleanOutState = cursorHolder.getInt(cursorHolder.indexCleanState, -1);
        }
        int i7 = cursorHolder.indexDeleteGroupId;
        if (i7 >= 0) {
            this.cleanOutDeleteGroupId = cursorHolder.getInt(i7, -1);
        }
        int i8 = cursorHolder.indexTotalSize;
        if (i8 >= 0) {
            this.cleanOutTotalSize = cursorHolder.getLong(i8, -1);
        }
        return this;
    }

    public SuggestData parseRemaster(CursorHolder cursorHolder) {
        this.remasterType = cursorHolder.getLong(cursorHolder.indexRevitalizedType, -2);
        this.originalPath = cursorHolder.getString(cursorHolder.indexOriginPath, (String) null);
        return this;
    }

    public SuggestData copyOf() {
        try {
            return (SuggestData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
