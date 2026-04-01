package com.samsung.android.gallery.support.search;

import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureIndexingBuilder {
    private int mAppend = 0;
    private long mFileId;
    private String[] mRelationship = new String[2];
    private int mRemove = 0;
    private long[] mTagId = new long[2];
    private String[] mTagName = new String[2];
    private long mTargetGroupId;
    private long mTargetTagId;
    private long[] mUnifiedId = new long[2];

    public boolean anyChanged() {
        if (this.mAppend == 0 && this.mRemove == 0) {
            return false;
        }
        return true;
    }

    public int getAppendIndex() {
        return this.mAppend;
    }

    public long getFileId() {
        return this.mFileId;
    }

    public String getRelationship(int i2) {
        return this.mRelationship[i2];
    }

    public int getRemoveIndex() {
        return this.mRemove;
    }

    public long getTagId(int i2) {
        return this.mTagId[i2];
    }

    public String getTagName(int i2) {
        return this.mTagName[i2];
    }

    public long getTargetGroupId() {
        return this.mTargetGroupId;
    }

    public long getTargetTagId() {
        return this.mTargetTagId;
    }

    public long getUnifiedId(int i2) {
        return this.mUnifiedId[i2];
    }

    public CreatureIndexingBuilder relationship(String str, String str2) {
        if (!TextUtils.equals(str, str2)) {
            String[] strArr = this.mRelationship;
            strArr[0] = str;
            strArr[1] = str2;
            if (TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                this.mRemove |= 8;
            }
            if (!TextUtils.isEmpty(str2)) {
                this.mAppend |= 8;
            }
        }
        return this;
    }

    public CreatureIndexingBuilder removeFileId(long j2) {
        this.mFileId = j2;
        return this;
    }

    public CreatureIndexingBuilder tagId(long j2, long j3) {
        if (j2 != j3) {
            long[] jArr = this.mTagId;
            jArr[0] = j2;
            jArr[1] = j3;
            int i2 = (j3 > 1 ? 1 : (j3 == 1 ? 0 : -1));
            if (i2 == 0 || j2 != 1) {
                this.mRemove |= 2;
            }
            if (i2 != 0) {
                this.mAppend |= 2;
            }
        }
        return this;
    }

    public CreatureIndexingBuilder tagName(String str, String str2) {
        if (!TextUtils.equals(str, str2)) {
            String[] strArr = this.mTagName;
            strArr[0] = str;
            strArr[1] = str2;
            if (TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                this.mRemove |= 1;
            }
            if (!TextUtils.isEmpty(str2)) {
                this.mAppend |= 1;
            }
        }
        return this;
    }

    public CreatureIndexingBuilder targetGroupId(long j2) {
        this.mTargetGroupId = j2;
        return this;
    }

    public CreatureIndexingBuilder targetTagId(long j2) {
        this.mTargetTagId = j2;
        return this;
    }

    public CreatureIndexingBuilder unifiedId(long j2, long j3) {
        if (j2 != j3) {
            long[] jArr = this.mUnifiedId;
            jArr[0] = j2;
            jArr[1] = j3;
            int i2 = (j3 > Long.MIN_VALUE ? 1 : (j3 == Long.MIN_VALUE ? 0 : -1));
            if (i2 == 0 || j2 != Long.MIN_VALUE) {
                this.mRemove |= 4;
            }
            if (i2 != 0) {
                this.mAppend |= 4;
            }
        }
        return this;
    }
}
