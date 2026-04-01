package com.samsung.android.sdk.globalpostprocmgr;

import android.content.ContentValues;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PPPluginData implements IGPPDBInterface.IPluginColumns {
    private String mDescription;
    private final long mId;
    private String mName;
    private int mType;
    private long mUpdateTime;
    private long mVersion;

    public PPPluginData(long j2, String str, int i2, String str2, long j3, long j8) {
        this.mId = j2;
        this.mName = str;
        this.mType = i2;
        this.mDescription = str2;
        this.mVersion = j3;
        this.mUpdateTime = j8;
    }

    public ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.mName);
        contentValues.put("type", Integer.valueOf(this.mType));
        contentValues.put("description", this.mDescription);
        contentValues.put("version", Long.valueOf(this.mVersion));
        contentValues.put("update_time", Long.valueOf(this.mUpdateTime));
        return contentValues;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getName() {
        return this.mName;
    }

    public int getType() {
        return this.mType;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public long getVersion() {
        return this.mVersion;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    public void setUpdateTime(long j2) {
        this.mUpdateTime = j2;
    }

    public void setVersion(long j2) {
        this.mVersion = j2;
    }

    public String toString() {
        return "PPPluginData{mId=" + this.mId + ", mName='" + this.mName + "', mType=" + this.mType + ", mDescription='" + this.mDescription + "', mVersion=" + this.mVersion + ", mUpdateTime=" + this.mUpdateTime + '}';
    }
}
